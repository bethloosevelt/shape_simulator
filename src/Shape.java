import javafx.geometry.Point2D;

abstract class Shape {
    Point2D vertices[];
    Point2D center;
    Point2D velocity;
    boolean forcesBalanced = false;

    void nextPosition() {
        center = center.add(velocity);
    }
    void setPosition(Point2D newCenter) {
        center = newCenter;
    }
    void setVelocity(Point2D newVelocity) {
        velocity = newVelocity;
    }
    double[] getXs() {
        double xValues[] = new double[vertices.length];
        for (int vertex = 0; vertex< vertices.length; vertex++) {
            xValues[vertex] = vertices[vertex].getX();
        }
        return xValues;
    }

    double[] getYs() {
        double yValues[] = new double[vertices.length];
        for (int vertex = 0; vertex< vertices.length; vertex++) {
            yValues[vertex] = vertices[vertex].getY();
        }
        return yValues;
    }
    abstract int distanceToEdgeFromCenter();
    abstract void placeAtRest(Point2D restingCenter);
}

class Circle extends Shape {

    private int radius;

    Circle(Point2D center) {
        setPosition(center);
        setVelocity(new Point2D(0, 0));
        radius = CircleConfig.getInstance().radius;
    }

    @Override
    void setPosition(Point2D newCenter) {
        this.vertices = CircleConfig.getInstance().getVerticies(newCenter);
        this.center = newCenter;
    }

    @Override
    void nextPosition() {
        center = center.add(velocity);
        updateVerticies();
    }

    void updateVerticies() {
        for (int currentVertex = 0; currentVertex< vertices.length; currentVertex++){
            vertices[currentVertex] = vertices[currentVertex].add(velocity);
        }
    }

    @Override
    int distanceToEdgeFromCenter() {
        return radius;
    }

    @Override
    void placeAtRest(Point2D restingCenter) {
        setPosition(restingCenter);
        setVelocity(new Point2D(0, 0));
        forcesBalanced = true;
    }
}

class CircleConfig {
    private static CircleConfig ourInstance = new CircleConfig(GlobalConfig.DEFAULT_CIRCLE_SIDES, GlobalConfig.DEFAULT_CIRCLE_RADIUS);
    public static CircleConfig getInstance() {
        return ourInstance;
    }

    private Point2D vertexOffsets[];
    int configuredNumberOfSides;
    int radius;

    private CircleConfig(int numberOfSides, int radius) {
        this.radius = radius;
        this.configuredNumberOfSides = numberOfSides;
        updateTessellationResolution(numberOfSides);
    }

    public void updateRadius(int newRadius) {
        this.radius = newRadius;
    }

    public void updateTessellationResolution(int numberOfSides) {
        this.configuredNumberOfSides = numberOfSides;
        Point2D vertices[] = new Point2D[numberOfSides];

        double theta = (2*GlobalConfig.PI)/numberOfSides;
        for (int sideNumber = 0; sideNumber < numberOfSides; sideNumber++) {
            int xCurrent = (int) (Math.cos(sideNumber * theta) * this.radius);
            int yCurrent = (int) (Math.sin(sideNumber * theta) * this.radius);
            vertices[sideNumber] = new Point2D(xCurrent, yCurrent);
        }
        this.vertexOffsets = vertices;
    }

    Point2D[] getVerticies(Point2D center) {

        Point2D coordinates[] = new Point2D[configuredNumberOfSides];
        for (int coordinateNumber=0; coordinateNumber<configuredNumberOfSides; coordinateNumber++) {
            coordinates[coordinateNumber] = new Point2D(vertexOffsets[coordinateNumber].getX() + center.getX(), vertexOffsets[coordinateNumber].getY() + center.getY());
        }
        return coordinates;
    }
}