# Hand Gesture OS Controller

A Python application that controls your computer using hand gestures detected through your camera.

## Features

- **Pointer Control**: Use your index finger to move the mouse cursor
- **Hover Click**: Hover in one position for 3 seconds to perform a click
- **Scroll Control**: Use two fingers to scroll up and down
- **Real-time Feedback**: Visual feedback showing detected gestures

## Requirements

- Python 3.7+
- Camera (webcam)
- Windows, macOS, or Linux

## Installation

1. Install the required packages:
```bash
pip install -r requirements.txt
```

2. Run the application:
```bash
python main.py
```

## Usage

### Gestures

1. **Single Finger (Index Finger)**
   - Move your index finger to control the mouse cursor
   - Hold the cursor in one position for 3 seconds to click
   - The cursor movement is mirrored for natural control

2. **Two Fingers (Index and Middle)**
   - Show two fingers to enable scroll mode
   - Move your hand up to scroll up
   - Move your hand down to scroll down

### Controls

- **'q'**: Quit the application
- **'l'**: Toggle landmark visualization
- **'i'**: Toggle information overlay

## How It Works

1. **Hand Detection**: Uses MediaPipe to detect hand landmarks
2. **Gesture Recognition**: Identifies different hand poses and finger counts
3. **Cursor Control**: Maps hand position to screen coordinates
4. **Action Execution**: Performs mouse movements, clicks, and scrolling

## Configuration

You can modify the following parameters in the code:

- `hover_threshold`: Time in seconds to trigger hover click (default: 3.0)
- `scroll_threshold`: Minimum movement to trigger scrolling
- `smoothing_factor`: Cursor movement smoothing (0.0-1.0)

## Troubleshooting

1. **Camera not working**: Make sure your webcam is connected and not used by another application
2. **Hand not detected**: Ensure good lighting and your hand is clearly visible to the camera
3. **Cursor movement is jittery**: Try adjusting the `smoothing_factor` in `gesture_controller.py`

## Safety

- The application includes a failsafe: move mouse to corner of screen to stop
- Press 'q' to quit the application at any time
- The application only controls mouse movement and basic interactions

## Dependencies

- OpenCV: Camera capture and image processing
- MediaPipe: Hand landmark detection
- PyAutoGUI: Mouse and keyboard control
- NumPy: Numerical operations
