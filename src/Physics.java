import javafx.geometry.Point2D;
import javafx.animation.AnimationTimer;

class Physics extends AnimationTimer {
    Point2D gravitationalForce = inchesPerSecondPerSecondToPixels(GlobalConfig.GRAVITY_CONSTANTS.get(GlobalConfig.DEFAULT_PLANET));

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
        someShape.applyForce(elapsedSeconds, gravitationalForce);
        someShape.nextPosition();
        ShapeCanvas.getInstance().drawShape(someShape);
    }

    Point2D inchesPerSecondPerSecondToPixels(Point2D gravitationalForce) {

        // this is the pythagorean theorem essentially
        double diagonal = ShapeCanvas.getInstance().diagonalScreeninInches;
        int width = ShapeCanvas.getInstance().widthScreenInPixels;
        int height = ShapeCanvas.getInstance().heighScreenInPixels;
        int pixelsPerInchSquared = (int) Math.floor(Math.sqrt((width*width) + (height*height)) / diagonal);
        int pixelsPerInch = (int) Math.sqrt(pixelsPerInchSquared);
        gravitationalForce = gravitationalForce.multiply(pixelsPerInch);
        return gravitationalForce;
    }

    void updateGravity(String planet) {
        this.gravitationalForce = inchesPerSecondPerSecondToPixels(GlobalConfig.GRAVITY_CONSTANTS.get(planet));
    }
}