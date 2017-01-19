import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

class GlobalConfig {

    static final int SCREEN_HEIGHT_DEFAULT = 1920;
    static final int SCREEN_WIDTH_DEFAULT = 1080;
    static final double SCREEN_DIAGONAL_DEFAULT = 13;

    static final String SCREEN_HEIGHT_DEFAULT_TEXT = "1920";
    static final String SCREEN_WIDTH_DEFAULT_TEXT = "1080";
    static final String SCREEN_DIAGONAL_DEFAULT_TEXT = "13";

    static final String NUMBER_SIDES_DEFAULT_TEXT = "200";
    static final String RADIUS_DEFAULT_TEXT = "30";

    static final int CANVAS_HEIGHT = 800;
    static final int CANVAS_WIDTH = 800;
    static final ObservableList<String> PLANETS = FXCollections.observableArrayList(
            "Sun", "Mercury", "Venus", "Earth", "Moon", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune");

    static final Color DEFAULT_COLOR = Color.GREEN;

    // PHYSICS
    // units => pixels/second^2 (given a 1080 13in display; ppi = 169)
    static final Point2D SUN_GRAVITY = new Point2D(0, 140233.2);
    static final Point2D MERCURY_GRAVITY = new Point2D(0, 1944.84);
    static final Point2D VENUS_GRAVITY = new Point2D(0, 4503.84);
    static final Point2D EARTH_GRAVITY = new Point2D(0, 5019);
    static final Point2D MOON_GRAVITY = new Point2D(0, 830);
    static final Point2D MARS_GRAVITY = new Point2D(0, 1944.84);
    static final Point2D JUPITER_GRAVITY = new Point2D(0, 12795);
    static final Point2D SATURN_GRAVITY = new Point2D(0, 5322.72);
    static final Point2D URANUS_GRAVITY = new Point2D(0, 5322.72);
    static final Point2D NEPTUNE_GRAVITY = new Point2D(0, 7062.84);

    // shape sizes
    static final int HEIGHT_DEFAULT_RECTANGLE = 30;
    static final int WIDTH_DEFAULT_RECTANGLE = 20;

    // generic constants
    static final double PI = 3.14159;
    static final int DEFAULT_CIRCLE_SIDES = 200;
    static final int DEFAULT_CIRCLE_RADIUS = 30;
}