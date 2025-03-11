import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

/**
 * Class representing an Oval shape.
 */
public class Oval extends Shape {

    public Oval(Point point1, Point point2, Color color) {
        super(point1, point2, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        int x = Math.min(point1.x, point2.x);
        int y = Math.min(point1.y, point2.y);
        int width = Math.abs(point1.x - point2.x);
        int height = Math.abs(point1.y - point2.y);
        g.drawOval(x, y, width, height);
    }
}
