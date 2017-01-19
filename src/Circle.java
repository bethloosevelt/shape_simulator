import javafx.geometry.Point2D;

class Circle extends Shape {

    private int radius;

    Circle(Point2D center) {
        setRadius(CircleConfig.getInstance().radius);
        setVertexOffsets();
        setPosition(center);
        setVelocity(new Point2D(0, 0));
    }
    void setVertexOffsets() {
        this.vertexOffsets = CircleConfig.getInstance().getVertexOffsets();
    }
    void setRadius(int newRadius) {
        this.radius = newRadius;
    }
    int distanceToEdgeFromCenter() {
        return radius;
    }

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

    Point2D[] getVertexOffsets() {
        return this.vertexOffsets;
    }
}