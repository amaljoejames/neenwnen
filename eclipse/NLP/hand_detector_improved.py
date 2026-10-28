import cv2
import numpy as np
from collections import deque

class HandDetector:
    def __init__(self):
        # Background subtractor for motion detection
        self.bg_subtractor = cv2.createBackgroundSubtractorMOG2(detectShadows=False)
        
        # Skin color ranges for different lighting conditions
        self.skin_ranges = [
            (np.array([0, 20, 70], dtype=np.uint8), np.array([20, 255, 255], dtype=np.uint8)),
            (np.array([0, 30, 60], dtype=np.uint8), np.array([25, 255, 255], dtype=np.uint8)),
            (np.array([0, 15, 80], dtype=np.uint8), np.array([15, 255, 255], dtype=np.uint8))
        ]
        
        # Position tracking for stability
        self.position_history = deque(maxlen=5)
        self.stable_position = None
        self.stability_threshold = 30
        
        # Morphological operations
        self.kernel_open = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (5, 5))
        self.kernel_close = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (15, 15))
        
        print("Using improved OpenCV hand detection with virtual pointer")
        
    def detect_hands(self, frame):
        """Detect hands with improved accuracy"""
        # Convert to HSV
        hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
        
        # Create combined skin mask
        combined_mask = np.zeros(hsv.shape[:2], dtype=np.uint8)
        
        for lower, upper in self.skin_ranges:
            skin_mask = cv2.inRange(hsv, lower, upper)
            combined_mask = cv2.bitwise_or(combined_mask, skin_mask)
        
        # Apply morphological operations
        combined_mask = cv2.morphologyEx(combined_mask, cv2.MORPH_OPEN, self.kernel_open)
        combined_mask = cv2.morphologyEx(combined_mask, cv2.MORPH_CLOSE, self.kernel_close)
        
        # Find contours
        contours, _ = cv2.findContours(combined_mask, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
        
        # Filter for hand-like contours
        hand_contours = []
        for contour in contours:
            area = cv2.contourArea(contour)
            if area > 2000:  # Increased minimum area
                # Check aspect ratio and solidity for hand-like shape
                x, y, w, h = cv2.boundingRect(contour)
                aspect_ratio = float(w) / h
                if 0.3 < aspect_ratio < 3.0:  # Reasonable aspect ratio
                    hand_contours.append(contour)
        
        return {"contours": hand_contours, "mask": combined_mask}
    
    def get_stable_position(self, position):
        """Get stable position using moving average"""
        if position is None:
            return None
            
        self.position_history.append(position)
        
        if len(self.position_history) >= 3:
            # Calculate average position
            avg_x = sum(p[0] for p in self.position_history) / len(self.position_history)
            avg_y = sum(p[1] for p in self.position_history) / len(self.position_history)
            
            # Check if positions are stable
            max_diff = max(abs(p[0] - avg_x) + abs(p[1] - avg_y) for p in self.position_history)
            
            if max_diff < self.stability_threshold:
                self.stable_position = (int(avg_x), int(avg_y))
            else:
                self.stable_position = None
                
        return self.stable_position
    
    def get_landmarks(self, results):
        """Create landmarks from hand detection"""
        if not results or not results["contours"]:
            return None
            
        # Get the largest contour
        largest_contour = max(results["contours"], key=cv2.contourArea)
        
        # Get bounding rectangle and center
        x, y, w, h = cv2.boundingRect(largest_contour)
        center_x = x + w // 2
        center_y = y + h // 2
        
        # Get stable position
        stable_pos = self.get_stable_position((center_x, center_y))
        if stable_pos:
            center_x, center_y = stable_pos
        
        # Create landmark structure
        class ImprovedLandmarks:
            def __init__(self, center_x, center_y, w, h):
                self.landmark = []
                # Center point
                self.landmark.append(self.create_point(center_x, center_y))
                # Virtual pointer tip (slightly above center)
                self.landmark.append(self.create_point(center_x, center_y - h//4))
                # Additional points for gesture detection
                for i in range(2, 21):
                    self.landmark.append(self.create_point(center_x, center_y))
            
            def create_point(self, x, y, frame_w=640, frame_h=480):
                class Point:
                    def __init__(self, x, y, frame_w, frame_h):
                        self.x = x / frame_w
                        self.y = y / frame_h
                return Point(x, y, frame_w, frame_h)
        
        return ImprovedLandmarks(center_x, center_y, w, h)
    
    def draw_landmarks(self, frame, landmarks):
        """Draw virtual pointer and hand detection"""
        if landmarks:
            # Get center position
            center_x = int(landmarks.landmark[0].x * 640)
            center_y = int(landmarks.landmark[0].y * 480)
            
            # Draw virtual pointer (crosshair)
            cv2.drawMarker(frame, (center_x, center_y), (0, 255, 0), 
                          cv2.MARKER_CROSS, 20, 2)
            
            # Draw pointer circle
            cv2.circle(frame, (center_x, center_y), 8, (0, 255, 0), 2)
            
            # Draw status text
            status = "STABLE" if self.stable_position else "MOVING"
            color = (0, 255, 0) if self.stable_position else (0, 0, 255)
            cv2.putText(frame, f"Pointer: {status}", (center_x - 40, center_y - 30), 
                       cv2.FONT_HERSHEY_SIMPLEX, 0.5, color, 2)
            
            # Draw bounding box
            if len(landmarks.landmark) > 1:
                tip_x = int(landmarks.landmark[1].x * 640)
                tip_y = int(landmarks.landmark[1].y * 480)
                cv2.line(frame, (center_x, center_y), (tip_x, tip_y), (255, 255, 0), 2)
                cv2.circle(frame, (tip_x, tip_y), 5, (255, 255, 0), -1)
        
        return frame
    
    def get_finger_count(self, landmarks):
        """Determine gesture based on hand position"""
        if not landmarks:
            return 0
        
        # Simple gesture detection based on position
        center_y = landmarks.landmark[0].y
        
        # If hand is in upper part of frame, assume pointing (1 finger)
        if center_y < 0.4:
            return 1
        # If hand is in middle, assume scroll (2 fingers)
        elif center_y < 0.7:
            return 2
        else:
            return 1
    
    def get_index_finger_tip(self, landmarks):
        """Get virtual pointer position"""
        if landmarks and len(landmarks.landmark) > 1:
            tip = landmarks.landmark[1]  # Virtual pointer tip
            return (int(tip.x * 640), int(tip.y * 480))
        return None
    
    def get_two_fingers_direction(self, landmarks):
        """Get scroll direction"""
        if landmarks:
            return landmarks.landmark[0].y
        return None
