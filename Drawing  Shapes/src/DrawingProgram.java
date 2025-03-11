
// Aasmit Thapa
//Shapes
import javax.swing.*;
import java.awt.*;

public class DrawingProgram extends JFrame {

    public DrawingProgram() {
        /**
         * Constructor for the DrawingProgram class.
         * It sets up the JFrame, adds a DrawingArea for shape drawing, and displays
         * keyboard instructions for the user at the bottom of the window.
         */
        setTitle("Shape Drawing Program");
        DrawingArea drawingArea = new DrawingArea(); // Create an instance of DrawingArea
        add(drawingArea, BorderLayout.CENTER); // Add drawingArea to the frame

        // Display keyboard options at the bottom
        JLabel instructions = new JLabel(
                "(E) Erase (T) Trails (L) Line (B) Box (O) Oval (C) Color (S) Save (R) Restore");
        instructions.setHorizontalAlignment(SwingConstants.CENTER);
        add(instructions, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DrawingProgram();
    }
}
