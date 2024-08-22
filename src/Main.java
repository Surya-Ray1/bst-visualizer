import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create the frame (window)
        JFrame frame = new JFrame("BST Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create the BST panel and add it to the frame
        BSTPanel panel = new BSTPanel();
        frame.add(panel);

        // Set the frame to be visible
        frame.setVisible(true);
    }
}
