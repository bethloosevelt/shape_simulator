import javafx.geometry.Point2D;
import javafx.animation.AnimationTimer;

class Physics extends AnimationTimer {
    Point2D gravitationalForce = GlobalConfig.EARTH_GRAVITY;
    long lastTimestamp = 0;
    public void handle(long timestamp) {
        ShapeCanvas.getInstance().erase();
        ShapeCanvas.getInstance().emptyQueue();
        long delta = timestamp - lastTimestamp;
        lastTimestamp = timestamp;
        ShapeCanvas.getInstance().shapes.forEach( (currentShape) -> {
            if (!currentShape.forcesBalanced) {
                this.moveShape(currentShape, delta);
                checkFloorCollision(currentShape);
            } else {
                ShapeCanvas.getInstance().drawShape(currentShape);
            }
        });
    }

    private void checkFloorCollision(Shape someShape) {
        if (someShape.center.getY() + someShape.distanceToEdgeFromCenter() >= ShapeCanvas.getInstance().getHeight()) {
            placeShapeOnBottom(someShape);
        }
    }

    private void placeShapeOnBottom(Shape someShape) {
        someShape.placeAtRest(new Point2D(someShape.center.getX(), ShapeCanvas.getInstance().getHeight() - someShape.distanceToEdgeFromCenter()));
        ShapeCanvas.getInstance().drawShape(someShape);
    }

    private void moveShape(Shape someShape, long elapsedTime) {
        // elapsed time is in nanoseconds
        double elapsedSeconds = elapsedTime / 1000000000.0;
        someShape.updateVelocity(elapsedSeconds, gravitationalForce);
        someShape.nextPosition();
        ShapeCanvas.getInstance().drawShape(someShape);
    }

    void updateGravity(String planet) {
        switch (planet) {
            case "Sun": gravitationalForce = GlobalConfig.SUN_GRAVITY;
                break;
            case "Mercury": gravitationalForce = GlobalConfig.MERCURY_GRAVITY;
                break;
            case "Venus": gravitationalForce = GlobalConfig.VENUS_GRAVITY;
                break;
            case "Earth": gravitationalForce = GlobalConfig.EARTH_GRAVITY;
                break;
            case "Moon": gravitationalForce = GlobalConfig.MOON_GRAVITY;
                break;
            case "Mars": gravitationalForce = GlobalConfig.MARS_GRAVITY;
                break;
            case "Jupiter": gravitationalForce = GlobalConfig.JUPITER_GRAVITY;
                break;
            case "Saturn": gravitationalForce = GlobalConfig.SATURN_GRAVITY;
                break;
            case "Uranus": gravitationalForce = GlobalConfig.URANUS_GRAVITY;
                break;
            case "Neptune": gravitationalForce = GlobalConfig.NEPTUNE_GRAVITY;
        }
    }
}