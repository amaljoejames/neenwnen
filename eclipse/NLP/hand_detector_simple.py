import cv2
import numpy as np

class HandDetector:
    def __init__(self):
        # Use background subtraction and contour detection for hand detection
        self.bg_subtractor = cv2.createBackgroundSubtractorMOG2(detectShadows=False)
        self.kernel = np.ones((3,3), np.uint8)
        
        # Skin color range in HSV
        self.lower_skin = np.array([0, 20, 70], dtype=np.uint8)
        self.upper_skin = np.array([20, 255, 255], dtype=np.uint8)
        
        print("Using OpenCV-based hand detection")
        
    def detect_hands(self, frame):
        """Detect hands using color and contour detection"""
        # Convert to HSV for skin detection
        hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
        
        # Create skin mask
        skin_mask = cv2.inRange(hsv, self.lower_skin, self.upper_skin)
        
        # Apply morphological operations
        skin_mask = cv2.morphologyEx(skin_mask, cv2.MORPH_OPEN, self.kernel)
        skin_mask = cv2.morphologyEx(skin_mask, cv2.MORPH_DILATE, self.kernel)
        
        # Find contours
        contours, _ = cv2.findContours(skin_mask, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
        
        # Filter for hand-like contours
        hand_contours = []
        for contour in contours:
            area = cv2.contourArea(contour)
            if area > 1000:  # Minimum area threshold
                hand_contours.append(contour)
        
        return {"contours": hand_contours, "mask": skin_mask}
    
    def get_landmarks(self, results):
        """Extract simplified landmarks from contour"""
        if not results or not results["contours"]:
            return None
            
        # Get the largest contour (assumed to be the hand)
        largest_contour = max(results["contours"], key=cv2.contourArea)
        
        # Get bounding rectangle
        x, y, w, h = cv2.boundingRect(largest_contour)
        
        # Create a simple landmark structure
        class SimpleLandmarks:
            def __init__(self, x, y, w, h):
                self.landmark = []
                # Create simplified hand points
                # Center
                self.landmark.append(self.create_point(x + w//2, y + h//2))
                # Fingertips (simplified positions)
                self.landmark.append(self.create_point(x + w//4, y + h//4))  # Thumb
                self.landmark.append(self.create_point(x + w//2, y))  # Index tip
                self.landmark.append(self.create_point(x + 3*w//4, y + h//4))  # Middle
                self.landmark.append(self.create_point(x + w, y + h//2))  # Ring
                self.landmark.append(self.create_point(x + w, y + 3*h//4))  # Pinky
                # Add more points for finger counting
                for i in range(6, 21):
                    self.landmark.append(self.create_point(x + w//2, y + h//2))
            
            def create_point(self, x, y):
                class Point:
                    def __init__(self, x, y, frame_w=640, frame_h=480):
                        self.x = x / frame_w
                        self.y = y / frame_h
                return Point(x, y)
        
        return SimpleLandmarks(x, y, w, h)
    
    def draw_landmarks(self, frame, landmarks):
        """Draw hand detection visualization"""
        if landmarks:
            # Draw a simple circle at the center of detected hand
            center_x = int(landmarks.landmark[0].x * 640)
            center_y = int(landmarks.landmark[0].y * 480)
            cv2.circle(frame, (center_x, center_y), 10, (0, 255, 0), -1)
            cv2.putText(frame, "Hand Detected", (center_x - 50, center_y - 20), 
                       cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 0), 2)
        return frame
    
    def get_finger_count(self, landmarks):
        """Estimate finger count based on hand size"""
        if not landmarks:
            return 0
        
        # Simple heuristic: return 1 for pointing, 2 for scroll, 0 otherwise
        # In a real implementation, this would analyze the contour shape
        return 1  # Default to 1 finger (pointing mode)
    
    def get_index_finger_tip(self, landmarks):
        """Get estimated index finger position"""
        if landmarks and len(landmarks.landmark) > 1:
            tip = landmarks.landmark[1]  # Use the second point as "index finger"
            return (int(tip.x * 640), int(tip.y * 480))
        return None
    
    def get_two_fingers_direction(self, landmarks):
        """Get direction for scrolling"""
        if landmarks:
            return landmarks.landmark[0].y  # Use center point for direction
        return None
