import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class DrawingArea extends JComponent {

    private ArrayList<Shape> shapes;
    private Shape currentShape;
    private Color currentColor;
    private ShapeType currentShapeType;
    private boolean trailsEnabled;

    public DrawingArea() {
        shapes = new ArrayList<>();
        currentColor = Color.BLACK;
        currentShapeType = ShapeType.LINE;  // Default shape
        trailsEnabled = false;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point start = e.getPoint();
                currentShape = createShape(start, start, currentColor);
                shapes.add(currentShape);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point end = e.getPoint();
                if (!trailsEnabled) {
                    // Update the last shape's end point (rubber band effect)
                    currentShape.setPoint2(end);
                } else {
                    // Create a new shape on each drag event
                    shapes.add(createShape(currentShape.getPoint1(), end, currentColor));
                }
                repaint();
            }
        });

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (Character.toUpperCase(e.getKeyChar())) {
                    case 'E': // Erase all shapes
                        shapes.clear();
                        repaint();
                        break;
                    case 'T': // Toggle trails
                        trailsEnabled = !trailsEnabled;
                        break;
                    case 'L': // Set current shape type to Line
                        currentShapeType = ShapeType.LINE;
                        break;
                    case 'B': // Set current shape type to Box
                        currentShapeType = ShapeType.BOX;
                        break;
                    case 'O': // Set current shape type to Oval
                        currentShapeType = ShapeType.OVAL;
                        break;
                    case 'C': // Change color
                        currentColor = JColorChooser.showDialog(null, "Choose a color", currentColor);
                        break;
                    case 'S': // Save shapes to file
                        saveShapes();
                        break;
                    case 'R': // Restore shapes from file
                        loadShapes();
                        break;
                }
            }
        });
    }

    private Shape createShape(Point p1, Point p2, Color color) {
        switch (currentShapeType) {
            case LINE:
                return new Line(p1, p2, color);
            case BOX:
                return new Box(p1, p2, color);
            case OVAL:
                return new Oval(p1, p2, color);
            default:
                throw new IllegalArgumentException("Unknown shape type");
        }
    }

    private void saveShapes() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(shapes);
                JOptionPane.showMessageDialog(this, "Drawing saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void loadShapes() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                shapes = (ArrayList<Shape>) ois.readObject();
                repaint();
                JOptionPane.showMessageDialog(this, "Drawing loaded successfully!");
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Error loading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }
}
