import javafx.scene.paint.Color;

class GlobalConfig {

    // accounting for the size of the title bar
    static final int WINDOW_HEIGHT = 800;
    static final int CANVAS_HEIGHT = 800;
    static final int WINDOW_WIDTH = 800;
    static final int CANVAS_WIDTH = 800;

    static final int Y_VALUE_OF_BOTTOM = 800;
    static final Color DEFAULT_COLOR = Color.GREEN;

    // PHYSICS
    // units == pixels / millisecond^2
    static final double EARTH_GRAVITY = 49;
    static final double REFRESH_RATE = 50;

    // shape sizes
    static final int HEIGHT_DEFAULT_RECTANGLE = 30;
    static final int WIDTH_DEFAULT_RECTANGLE = 20;

    // generic constants
    static final double PI = 3.14159;
    static final int DEFAULT_CIRCLE_SIDES = 2000;
    static final int DEFAULT_CIRCLE_RADIUS = 30;
}