import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BinaryTree {

    TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    public void insertRoot(Object data) {
        if (this.root == null) {
            this.root = new TreeNode(data);
        }
    }

    public TreeNode search(Object data) {
        return searchHelper(this.root, data);
    }

    private TreeNode searchHelper(TreeNode current, Object data) {
        if (current == null) {
            return null;
        }
        if (current.data != null && current.data.equals(data)) {
            return current;
        }
        TreeNode leftResult = searchHelper(current.left, data);
        if (leftResult != null) {
            return leftResult;
        }
        return searchHelper(current.right, data);
    }

    public boolean insertLeft(Object parent, Object data) {
        TreeNode parentNode = search(parent);
        if (parentNode == null || parentNode.left != null) {
            return false;
        }
        parentNode.left = new TreeNode(data);
        return true;
    }

    public boolean insertRight(Object parent, Object data) {
        TreeNode parentNode = search(parent);
        if (parentNode == null || parentNode.right != null) {
            return false;
        }
        parentNode.right = new TreeNode(data);
        return true;
    }

    public void buildTree(Scanner scanner) {
        System.out.print("Raiz: ");
        String line = scanner.nextLine().trim();
        if (line.isEmpty() || line.equals("-")) {
            this.root = null;
            return;
        }

        this.root = new TreeNode(line);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            System.out.print("Hijo izquierdo de " + current.data + ": ");
            line = scanner.nextLine().trim();
            if (!line.isEmpty() && !line.equals("-")) {
                current.left = new TreeNode(line);
                queue.add(current.left);
            }

            System.out.print("Hijo derecho de " + current.data + ": ");
            line = scanner.nextLine().trim();
            if (!line.isEmpty() && !line.equals("-")) {
                current.right = new TreeNode(line);
                queue.add(current.right);
            }
        }
    }

    public void printTree() {
        if (root == null) {
            System.out.println("(Arbol vacio)");
            return;
        }
        printTreeHelper(this.root, "", true, false);
    }

    private void printTreeHelper(TreeNode node, String prefix, boolean isTail, boolean showBalance) {
        if (node == null) return;

        String label = node.data.toString();
        if (showBalance) {
            label += " (FE: " + balanceFactor(node) + ")";
        }

        System.out.println(prefix + (isTail ? "└── " : "├── ") + label);

        List<TreeNode> children = new ArrayList<>();
        if (node.left != null || node.right != null) {
            children.add(node.left);
            children.add(node.right);
        }

        for (int i = 0; i < children.size(); i++) {
            TreeNode child = children.get(i);
            boolean isLast = (i == children.size() - 1);
            String newPrefix = prefix + (isTail ? "    " : "│   ");

            if (child != null) {
                printTreeHelper(child, newPrefix, isLast, showBalance);
            } else {
                System.out.println(newPrefix + (isLast ? "└── " : "├── ") + "-");
            }
        }
    }

    public String preorder() {
        List<String> list = new ArrayList<>();
        preorderHelper(this.root, list);
        return String.join(" ", list);
    }

    private void preorderHelper(TreeNode node, List<String> list) {
        if (node == null) return;
        list.add(node.data.toString());
        preorderHelper(node.left, list);
        preorderHelper(node.right, list);
    }

    public String inorder() {
        List<String> list = new ArrayList<>();
        inorderHelper(this.root, list);
        return String.join(" ", list);
    }

    private void inorderHelper(TreeNode node, List<String> list) {
        if (node == null) return;
        inorderHelper(node.left, list);
        list.add(node.data.toString());
        inorderHelper(node.right, list);
    }

    public String postorder() {
        List<String> list = new ArrayList<>();
        postorderHelper(this.root, list);
        return String.join(" ", list);
    }

    private void postorderHelper(TreeNode node, List<String> list) {
        if (node == null) return;
        postorderHelper(node.left, list);
        postorderHelper(node.right, list);
        list.add(node.data.toString());
    }

    public int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int balanceFactor(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.right) - height(node.left);
    }

    public boolean isBalanced() {
        return isBalancedHelper(this.root);
    }

    private boolean isBalancedHelper(TreeNode node) {
        if (node == null) return true;
        int bf = balanceFactor(node);
        if (bf < -1 || bf > 1) {
            return false;
        }
        return isBalancedHelper(node.left) && isBalancedHelper(node.right);
    }

    public List<Object> unbalancedNodes() {
        List<Object> list = new ArrayList<>();
        unbalancedNodesHelper(this.root, list);
        return list;
    }

    private void unbalancedNodesHelper(TreeNode node, List<Object> list) {
        if (node == null) return;
        int bf = balanceFactor(node);
        if (bf < -1 || bf > 1) {
            list.add(node.data);
        }
        unbalancedNodesHelper(node.left, list);
        unbalancedNodesHelper(node.right, list);
    }

    public void printBalanceFactors() {
        if (root == null) {
            System.out.println("(Arbol vacio)");
            return;
        }
        printTreeHelper(this.root, "", true, true);
    }

    public TreeNode rotateRight(TreeNode node) {
        if (node == null || node.left == null) return node;
        TreeNode newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }

    public TreeNode rotateLeft(TreeNode node) {
        if (node == null || node.right == null) return node;
        TreeNode newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }

    public TreeNode rotateLeftRight(TreeNode node) {
        if (node == null || node.left == null) return node;
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    public TreeNode rotateRightLeft(TreeNode node) {
        if (node == null || node.right == null) return node;
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    public void balance() {
        System.out.println("\n--- Arbol Original con Factores de Equilibrio ---");
        printBalanceFactors();

        List<Object> unb = unbalancedNodes();
        System.out.println("\nNodos que requieren balanceo: " + (unb.isEmpty() ? "Ninguno" : unb));

        this.root = balanceSubtree(this.root);

        System.out.println("\n--- Arbol Despues del Balanceo (AVL) ---");
        printBalanceFactors();
    }

    private TreeNode balanceSubtree(TreeNode node) {
        if (node == null) return null;

        node.left = balanceSubtree(node.left);
        node.right = balanceSubtree(node.right);

        int bf = balanceFactor(node);

        if (bf < -1) {
            if (balanceFactor(node.left) <= 0) {
                return rotateRight(node);
            } else {
                return rotateLeftRight(node);
            }
        }

        if (bf > 1) {
            if (balanceFactor(node.right) >= 0) {
                return rotateLeft(node);
            } else {
                return rotateRightLeft(node);
            }
        }

        return node;
    }
}