import java.awt.*;
import java.io.Serializable;

/**
 * Abstract class representing a shape defined by two points and a color.
 */
public abstract class Shape implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Point point1;
    protected Point point2;
    protected Color color;

    public Shape(Point point1, Point point2, Color color) {
        this.point1 = point1;
        this.point2 = point2;
        this.color = color;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public abstract void draw(Graphics g);
}
