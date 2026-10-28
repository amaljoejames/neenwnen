import pyautogui
import time
import numpy as np
from collections import deque

class GestureController:
    def __init__(self):
        pyautogui.FAILSAFE = True
        self.screen_width, self.screen_height = pyautogui.size()
        
        # Hover detection
        self.hover_threshold = 1.0  # seconds
        self.hover_start_time = None
        self.last_position = None
        self.position_history = deque(maxlen=10)
        
        # Scroll detection
        self.scroll_threshold = 0.05
        self.last_scroll_y = None
        self.scroll_cooldown = 0.5
        self.last_scroll_time = 0
        
        # Smoothing
        self.smoothing_factor = 0.2
        self.cursor_speed_multiplier = 1.5
        
    def map_to_screen(self, hand_x, hand_y, frame_width=640, frame_height=480):
        """Map hand coordinates to screen coordinates"""
        # Flip x coordinate for natural movement
        screen_x = self.screen_width - (hand_x / frame_width) * self.screen_width
        screen_y = (hand_y / frame_height) * self.screen_height
        
        # Apply cursor speed multiplier for better range
        if self.last_position:
            # Calculate movement delta
            delta_x = screen_x - self.last_position[0]
            delta_y = screen_y - self.last_position[1]
            
            # Apply speed multiplier
            screen_x = self.last_position[0] + delta_x * self.cursor_speed_multiplier
            screen_y = self.last_position[1] + delta_y * self.cursor_speed_multiplier
            
            # Apply minimal smoothing
            screen_x = (self.smoothing_factor * screen_x + 
                       (1 - self.smoothing_factor) * self.last_position[0])
            screen_y = (self.smoothing_factor * screen_y + 
                       (1 - self.smoothing_factor) * self.last_position[1])
        
        self.last_position = (screen_x, screen_y)
        return int(screen_x), int(screen_y)
    
    def move_cursor(self, hand_x, hand_y):
        """Move cursor to hand position"""
        screen_x, screen_y = self.map_to_screen(hand_x, hand_y)
        pyautogui.moveTo(screen_x, screen_y, duration=0.1)
        
        # Store position for hover detection
        self.position_history.append((screen_x, screen_y))
        
    def check_hover_and_click(self):
        """Check if cursor has been hovering for click threshold"""
        current_time = time.time()
        
        if len(self.position_history) < 10:
            return False
            
        # Check if position is stable
        positions = list(self.position_history)
        x_variance = np.var([p[0] for p in positions])
        y_variance = np.var([p[1] for p in positions])
        
        is_stable = x_variance < 100 and y_variance < 100
        
        if is_stable:
            if self.hover_start_time is None:
                self.hover_start_time = current_time
            elif current_time - self.hover_start_time >= self.hover_threshold:
                # Perform click
                pyautogui.click()
                self.hover_start_time = None
                return True
        else:
            self.hover_start_time = None
            
        return False
    
    def scroll(self, direction, finger_y):
        """Perform scroll based on finger movement"""
        current_time = time.time()
        
        if current_time - self.last_scroll_time < self.scroll_cooldown:
            return
            
        if self.last_scroll_y is not None:
            y_diff = finger_y - self.last_scroll_y
            
            if abs(y_diff) > self.scroll_threshold:
                if y_diff > 0:  # Moving down
                    pyautogui.scroll(-5)  # Scroll down
                else:  # Moving up
                    pyautogui.scroll(5)   # Scroll up
                    
                self.last_scroll_time = current_time
                
        self.last_scroll_y = finger_y
    
    def reset_scroll_tracking(self):
        """Reset scroll tracking when gesture changes"""
        self.last_scroll_y = None
