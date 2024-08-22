import java.util.ArrayList;
import java.util.List;

public class BST {
    class Node {
        int value;
        Node left, right;
        int x, y; // Coordinates for visualization

        public Node(int value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }
    }

    private Node root;
    private List<Integer> traversalResult = new ArrayList<>();

    public void insert(int value) {
        root = insertRec(root, value, 400, 50, 200);
    }

    private Node insertRec(Node node, int value, int x, int y, int offset) {
        if (node == null) {
            return new Node(value, x, y);
        }
        if (value < node.value) {
            node.left = insertRec(node.left, value, x - offset, y + 50, offset / 2);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value, x + offset, y + 50, offset / 2);
        }
        return node;
    }

    public void delete(int value) {
        root = deleteRec(root, value);
    }

    private Node deleteRec(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.value) {
            node.left = deleteRec(node.left, value);
        } else if (value > node.value) {
            node.right = deleteRec(node.right, value);
        } else {
            // Node with only one child or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            node.value = minValue(node.right);

            // Delete the inorder successor
            node.right = deleteRec(node.right, node.value);
        }

        return node;
    }

    private int minValue(Node node) {
        int minValue = node.value;
        while (node.left != null) {
            minValue = node.left.value;
            node = node.left;
        }
        return minValue;
    }

    public boolean search(int value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node node, int value) {
        if (node == null) {
            return false;
        }
        if (value < node.value) {
            return searchRec(node.left, value);
        } else if (value > node.value) {
            return searchRec(node.right, value);
        } else {
            return true;
        }
    }

    public List<Integer> inOrderTraversal() {
        traversalResult.clear();
        inOrderRec(root);
        return traversalResult;
    }

    private void inOrderRec(Node node) {
        if (node != null) {
            inOrderRec(node.left);
            traversalResult.add(node.value);
            inOrderRec(node.right);
        }
    }

    public List<Integer> preOrderTraversal() {
        traversalResult.clear();
        preOrderRec(root);
        return traversalResult;
    }

    private void preOrderRec(Node node) {
        if (node != null) {
            traversalResult.add(node.value);
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    public List<Integer> postOrderTraversal() {
        traversalResult.clear();
        postOrderRec(root);
        return traversalResult;
    }

    private void postOrderRec(Node node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            traversalResult.add(node.value);
        }
    }

    public Node getRoot() {
        return root;
    }
}
