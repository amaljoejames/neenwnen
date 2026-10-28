import cv2
import mediapipe as mp
import numpy as np

class HandDetector:
    def __init__(self):
        # Use MediaPipe Tasks API for newer versions
        BaseOptions = mp.tasks.BaseOptions
        HandLandmarker = mp.tasks.vision.HandLandmarker
        HandLandmarkerOptions = mp.tasks.vision.HandLandmarkerOptions
        VisionRunningMode = mp.tasks.vision.RunningMode
        
        # Download the model automatically
        model_url = "https://storage.googleapis.com/mediapipe-models/hand_landmarker/hand_landmarker/float16/1/hand_landmarker.task"
        
        self.base_options = BaseOptions(model_asset_path=model_url)
        self.options = HandLandmarkerOptions(
            base_options=self.base_options,
            running_mode=VisionRunningMode.IMAGE,
            num_hands=1
        )
        
        try:
            self.landmarker = HandLandmarker.create_from_options(self.options)
            self.use_tasks_api = True
        except Exception as e:
            print(f"Error creating landmarker: {e}")
            # Fallback to basic hand detection
            self.use_tasks_api = False
            self.init_legacy_detector()
    
    def init_legacy_detector(self):
        """Fallback method for legacy MediaPipe"""
        try:
            self.mp_hands = mp.solutions.hands
            self.hands = self.mp_hands.Hands(
                static_image_mode=False,
                max_num_hands=1,
                min_detection_confidence=0.7,
                min_tracking_confidence=0.5
            )
            self.mp_drawing = mp.solutions.drawing_utils
            print("Using legacy MediaPipe solutions API")
        except Exception as e:
            print(f"Could not initialize legacy detector: {e}")
            raise Exception("No hand detection method available")
        
    def detect_hands(self, frame):
        """Detect hands and return landmarks"""
        if self.use_tasks_api:
            # Convert frame to MediaPipe image format
            mp_image = mp.Image(image_format=mp.ImageFormat.SRGB, data=frame)
            results = self.landmarker.detect(mp_image)
            return results
        elif hasattr(self, 'hands'):
            # Use legacy API
            rgb_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
            results = self.hands.process(rgb_frame)
            return results
        else:
            return None
    
    def get_landmarks(self, results):
        """Extract hand landmarks from detection results"""
        if self.use_tasks_api:
            if results.hand_landmarks:
                # Convert to legacy format for compatibility
                class LegacyLandmarks:
                    def __init__(self, landmarks):
                        self.landmark = landmarks
                
                return LegacyLandmarks(results.hand_landmarks[0])
        else:
            if results.multi_hand_landmarks:
                return results.multi_hand_landmarks[0]
        return None
    
    def draw_landmarks(self, frame, landmarks):
        """Draw hand landmarks on frame"""
        if landmarks and hasattr(self, 'mp_drawing') and hasattr(self, 'mp_hands'):
            self.mp_drawing.draw_landmarks(
                frame, landmarks, self.mp_hands.HAND_CONNECTIONS)
        return frame
    
    def get_finger_count(self, landmarks):
        """Count number of extended fingers"""
        if not landmarks:
            return 0
            
        finger_tips = [4, 8, 12, 16, 20]
        finger_pips = [3, 6, 10, 14, 18]
        
        count = 0
        
        # Thumb
        if landmarks.landmark[finger_tips[0]].x > landmarks.landmark[finger_pips[0]].x:
            count += 1
            
        # Other fingers
        for i in range(1, 5):
            if landmarks.landmark[finger_tips[i]].y < landmarks.landmark[finger_pips[i]].y:
                count += 1
                
        return count
    
    def get_index_finger_tip(self, landmarks):
        """Get index finger tip coordinates"""
        if landmarks:
            tip = landmarks.landmark[8]
            return (int(tip.x * 640), int(tip.y * 480))
        return None
    
    def get_two_fingers_direction(self, landmarks):
        """Get direction of movement for two fingers (index and middle)"""
        if not landmarks:
            return None
            
        index_tip = landmarks.landmark[8]
        middle_tip = landmarks.landmark[12]
        
        # Calculate average position
        avg_y = (index_tip.y + middle_tip.y) / 2
        
        return avg_y
