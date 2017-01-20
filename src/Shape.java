import javafx.geometry.Point2D;

abstract class Shape {
    Point2D vertexOffsets[];
    Point2D verticies[];
    Point2D center;
    Point2D velocity;
    boolean forcesBalanced = false;

    void setVelocity(Point2D newVelocity) {
        velocity = newVelocity;
    }
    void applyForce(double secondsPassed, Point2D forceApplied) {
        velocity = velocity.add(forceApplied.getX()*secondsPassed, forceApplied.getY()*secondsPassed);
    }
    double[] getXs() {
        double xValues[] = new double[verticies.length];
        for (int vertex = 0; vertex< verticies.length; vertex++) {
            xValues[vertex] = verticies[vertex].getX();
        }
        return xValues;
    }
    double[] getYs() {
        double yValues[] = new double[verticies.length];
        for (int vertex = 0; vertex< verticies.length; vertex++) {
            yValues[vertex] = verticies[vertex].getY();
        }
        return yValues;
    }
    void setPosition(Point2D newCenter) {
        center = newCenter;
        if (verticies == null) {
            verticies = new Point2D[vertexOffsets.length];
        }
        for (int vertex = 0; vertex< vertexOffsets.length; vertex++) {
            verticies[vertex] = vertexOffsets[vertex].add(newCenter);
        }
    }
    void nextPosition() {
        setPosition(center.add(velocity));
    }

    void placeAtRest(Point2D restingCenter) {
        setPosition(restingCenter);
        setVelocity(new Point2D(0, 0));
        forcesBalanced = true;
    }
    abstract int distanceToEdgeFromCenter();
    abstract void setVertexOffsets();
}