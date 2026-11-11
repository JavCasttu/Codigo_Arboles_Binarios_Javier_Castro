import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TreeNode a = new TreeNode("A");
        BinaryTree tree = new BinaryTree(a);

        a.left = new TreeNode("B");
        a.right = new TreeNode("C");
        a.left.left = new TreeNode("D");
        a.left.right = new TreeNode("E");
        a.right.left = new TreeNode("F");
        a.right.right = new TreeNode("G");
        a.left.left.left = new TreeNode("H");
        a.left.left.right = new TreeNode("I");

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Construir arbol");
            System.out.println("2. Mostrar arbol");
            System.out.println("3. Recorrido Preorden");
            System.out.println("4. Recorrido Inorden");
            System.out.println("5. Recorrido Posorden");
            System.out.println("6. Mostrar factores de equilibrio");
            System.out.println("7. Balancear arbol (AVL)");
            System.out.println("8. Salir");
            System.out.println("==========================");
            System.out.print("Seleccione una opcion: ");

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    tree.buildTree(scanner);
                    break;
                case "2":
                    System.out.println();
                    tree.printTree();
                    break;
                case "3":
                    System.out.println("Preorden: " + tree.preorder());
                    break;
                case "4":
                    System.out.println("Inorden: " + tree.inorder());
                    break;
                case "5":
                    System.out.println("Posorden: " + tree.postorder());
                    break;
                case "6":
                    System.out.println();
                    tree.printBalanceFactors();
                    break;
                case "7":
                    tree.balance();
                    break;
                case "8":
                    exit = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
                    break;
            }
        }
    }
}