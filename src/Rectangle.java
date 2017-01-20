import javafx.geometry.Point2D;

public class Rectangle extends Shape {
    private int height = 60;
    private int width = 30;

    Rectangle(Point2D center) {
        setVertexOffsets();
        setPosition(center);
        setVelocity(new Point2D(0, 0));
    }
    void setVertexOffsets() {
        this.vertexOffsets = new Point2D[4];
        this.vertexOffsets[0] = new Point2D(0, 0);
        this.vertexOffsets[1] = new Point2D(60, 0);
        this.vertexOffsets[2] = new Point2D(60, 30);
        this.vertexOffsets[3] = new Point2D(0, 30);
    }
    int distanceToEdgeFromCenter(){
        return height / 2;
    }
}
