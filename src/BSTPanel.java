import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BSTPanel extends JPanel {
    private BST bst = new BST();
    private JTextField inputField;
    private JButton insertButton, deleteButton, searchButton;
    private JButton inOrderButton, preOrderButton, postOrderButton;
    private JTextArea traversalOutput;

    public BSTPanel() {
        setLayout(new BorderLayout());

        // Create input field and buttons
        JPanel controlPanel = new JPanel();
        inputField = new JTextField(5);
        insertButton = new JButton("Insert");
        deleteButton = new JButton("Delete");
        searchButton = new JButton("Search");
        inOrderButton = new JButton("In-Order Traversal");
        preOrderButton = new JButton("Pre-Order Traversal");
        postOrderButton = new JButton("Post-Order Traversal");

        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(new JLabel("Value:"));
        controlPanel.add(inputField);
        controlPanel.add(insertButton);
        controlPanel.add(deleteButton);
        controlPanel.add(searchButton);
        controlPanel.add(inOrderButton);
        controlPanel.add(preOrderButton);
        controlPanel.add(postOrderButton);

        add(controlPanel, BorderLayout.NORTH);

        // Create the traversal output area
        traversalOutput = new JTextArea(5, 30);
        traversalOutput.setEditable(false);
        add(new JScrollPane(traversalOutput), BorderLayout.SOUTH);

        // Set button actions
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(inputField.getText());
                bst.insert(value);
                repaint(); // Repaint the panel to show the updated tree
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(inputField.getText());
                bst.delete(value);
                repaint(); // Repaint the panel to show the updated tree
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = Integer.parseInt(inputField.getText());
                boolean found = bst.search(value);
                if (found) {
                    traversalOutput.setText("Element " + value + " found in the tree.");
                } else {
                    traversalOutput.setText("Element " + value + " not found in the tree.");
                }
            }
        });

        inOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> result = bst.inOrderTraversal();
                traversalOutput.setText("In-Order: " + result.toString());
            }
        });

        preOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> result = bst.preOrderTraversal();
                traversalOutput.setText("Pre-Order: " + result.toString());
            }
        });

        postOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> result = bst.postOrderTraversal();
                traversalOutput.setText("Post-Order: " + result.toString());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, bst.getRoot());
    }

    private void drawTree(Graphics g, BST.Node node) {
        if (node != null) {
            // Draw the node
            g.setColor(Color.BLACK);
            g.drawOval(node.x - 15, node.y - 15, 30, 30);
            g.setColor(Color.BLUE);
            g.drawString(Integer.toString(node.value), node.x - 7, node.y + 5);

            // Draw the lines to the children
            if (node.left != null) {
                g.drawLine(node.x, node.y, node.left.x, node.left.y);
            }
            if (node.right != null) {
                g.drawLine(node.x, node.y, node.right.x, node.right.y);
            }

            // Recursively draw the left and right children
            drawTree(g, node.left);
            drawTree(g, node.right);
        }
    }
}
