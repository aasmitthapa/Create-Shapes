import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

/**
 * Class representing a Line shape.
 */
public class Line extends Shape {

    public Line(Point point1, Point point2, Color color) {
        super(point1, point2, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(point1.x, point1.y, point2.x, point2.y);
    }
}
