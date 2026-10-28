import cv2
import time
from hand_detector_improved import HandDetector
from gesture_controller import GestureController

class HandGestureController:
    def __init__(self):
        self.detector = HandDetector()
        self.controller = GestureController()
        self.cap = cv2.VideoCapture(0)
        
        # State tracking
        self.current_gesture = "none"
        self.last_gesture = "none"
        self.gesture_cooldown = 0.5
        self.last_gesture_time = 0
        
        # Display settings
        self.show_landmarks = True
        self.show_info = True
        
    def process_gesture(self, landmarks):
        """Process hand landmarks and control computer"""
        if not landmarks:
            self.current_gesture = "none"
            return
            
        finger_count = self.detector.get_finger_count(landmarks)
        index_tip = self.detector.get_index_finger_tip(landmarks)
        
        current_time = time.time()
        
        # Gesture: Single finger (pointer)
        if finger_count == 1 and index_tip:
            self.current_gesture = "pointer"
            
            # Move cursor
            self.controller.move_cursor(index_tip[0], index_tip[1])
            
            # Check for hover click
            self.controller.check_hover_and_click()
            
        # Gesture: Two fingers (scroll)
        elif finger_count == 2:
            self.current_gesture = "scroll"
            
            # Get scroll direction
            finger_y = self.detector.get_two_fingers_direction(landmarks)
            if finger_y is not None:
                self.controller.scroll("vertical", finger_y)
                
        # Reset scroll tracking when changing from scroll gesture
        if self.last_gesture == "scroll" and self.current_gesture != "scroll":
            self.controller.reset_scroll_tracking()
            
        self.last_gesture = self.current_gesture
        
    def draw_info(self, frame):
        """Draw information overlay on frame"""
        if not self.show_info:
            return frame
            
        # Gesture status
        gesture_text = f"Gesture: {self.current_gesture}"
        cv2.putText(frame, gesture_text, (10, 30), 
                   cv2.FONT_HERSHEY_SIMPLEX, 0.7, (0, 255, 0), 2)
        
        # Instructions
        instructions = [
            "1 finger: Move cursor + hover 3s to click",
            "2 fingers: Scroll up/down",
            "Press 'q' to quit, 'l' to toggle landmarks, 'i' to toggle info"
        ]
        
        for i, instruction in enumerate(instructions):
            cv2.putText(frame, instruction, (10, 70 + i*25), 
                       cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 1)
        
        return frame
    
    def run(self):
        """Main application loop"""
        print("Starting Hand Gesture Controller...")
        print("Make sure your camera is working and your hand is visible.")
        
        try:
            while True:
                ret, frame = self.cap.read()
                if not ret:
                    print("Failed to capture frame from camera")
                    break
                
                # Flip frame horizontally for mirror effect
                frame = cv2.flip(frame, 1)
                
                # Detect hands
                results = self.detector.detect_hands(frame)
                landmarks = self.detector.get_landmarks(results)
                
                # Process gestures
                self.process_gesture(landmarks)
                
                # Draw landmarks
                if self.show_landmarks and landmarks:
                    frame = self.detector.draw_landmarks(frame, landmarks)
                
                # Draw info overlay
                frame = self.draw_info(frame)
                
                # Display frame
                cv2.imshow('Hand Gesture Controller', frame)
                
                # Handle keyboard input
                key = cv2.waitKey(1) & 0xFF
                if key == ord('q'):
                    break
                elif key == ord('l'):
                    self.show_landmarks = not self.show_landmarks
                elif key == ord('i'):
                    self.show_info = not self.show_info
                    
        except KeyboardInterrupt:
            print("\nApplication interrupted by user")
        except Exception as e:
            print(f"Error occurred: {e}")
        finally:
            self.cleanup()
    
    def cleanup(self):
        """Clean up resources"""
        print("Cleaning up...")
        self.cap.release()
        cv2.destroyAllWindows()

if __name__ == "__main__":
    app = HandGestureController()
    app.run()
