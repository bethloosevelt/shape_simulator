import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.HashMap;

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
    static final String DEFAULT_PLANET = "Earth";

    // PHYSICS
    // units => inches/second^2
    private static final Point2D SUN_GRAVITY = new Point2D(0, 10787.40);
    private static final Point2D MERCURY_GRAVITY = new Point2D(0, 149.61);
    private static final Point2D VENUS_GRAVITY = new Point2D(0, 346.46);
    private static final Point2D EARTH_GRAVITY = new Point2D(0, 385.83);
    private static final Point2D MOON_GRAVITY = new Point2D(0, 63.78);
    private static final Point2D MARS_GRAVITY = new Point2D(0, 149.61);
    private static final Point2D JUPITER_GRAVITY = new Point2D(0, 12795);
    private static final Point2D SATURN_GRAVITY = new Point2D(0, 409.45);
    private static final Point2D URANUS_GRAVITY = new Point2D(0, 409.45);
    private static final Point2D NEPTUNE_GRAVITY = new Point2D(0, 543.31);

    static final HashMap<String, Point2D> GRAVITY_CONSTANTS;
    static {
        GRAVITY_CONSTANTS = new HashMap<>();
        GRAVITY_CONSTANTS.put(PLANETS.get(0), SUN_GRAVITY);
        GRAVITY_CONSTANTS.put(PLANETS.get(1), MERCURY_GRAVITY);
        GRAVITY_CONSTANTS.put(PLANETS.get(2), VENUS_GRAVITY);
        GRAVITY_CONSTANTS.put(PLANETS.get(3), EARTH_GRAVITY);
        GRAVITY_CONSTANTS.put(PLANETS.get(4), MOON_GRAVITY);
        GRAVITY_CONSTANTS.put(PLANETS.get(5), MARS_GRAVITY);
        GRAVITY_CONSTANTS.put(PLANETS.get(6), JUPITER_GRAVITY);
        GRAVITY_CONSTANTS.put(PLANETS.get(7), SATURN_GRAVITY);
        GRAVITY_CONSTANTS.put(PLANETS.get(8), URANUS_GRAVITY);
        GRAVITY_CONSTANTS.put(PLANETS.get(9), NEPTUNE_GRAVITY);
    }

    // shape sizes
    static final int HEIGHT_DEFAULT_RECTANGLE = 30;
    static final int WIDTH_DEFAULT_RECTANGLE = 20;

    // generic constants
    static final double PI = 3.14159;
    static final int DEFAULT_CIRCLE_SIDES = 200;
    static final int DEFAULT_CIRCLE_RADIUS = 30;
}