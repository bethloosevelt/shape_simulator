import javafx.geometry.Point2D;

import java.util.TimerTask;

class Physics extends TimerTask {

    public void run() {
        ShapeCanvas.getInstance().erase();
        ShapeCanvas.getInstance().emptyQueue();
        ShapeCanvas.getInstance().shapes.forEach( (currentShape) -> {
            if (!currentShape.forcesBalanced) {
                this.moveShape(currentShape);
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

    private void moveShape(Shape someShape) {
        if (someShape instanceof Circle) {
            // (milliseconds) / (pixels / millisecond^2) => pixels/second
            double velocityIncrease = GlobalConfig.REFRESH_RATE/ GlobalConfig.EARTH_GRAVITY;
            someShape.velocity = someShape.velocity.add(0, velocityIncrease);
            someShape.nextPosition();
            ShapeCanvas.getInstance().drawShape(someShape);
        }
    }
}