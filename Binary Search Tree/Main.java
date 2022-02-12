package a5;


public class Main {
  public static void main(String[] args) {
    /*
    This is a basic example of how to create a BST and add values to it.
    You should add more examples and use this class to debug your code
    */
    BST<Integer> bst = new NonEmptyBST<>(3);
      //bst.printInOrderTraversal();
      bst = bst.insert(8);
      bst = bst.insert(1);
      bst = bst.insert(9);
      bst = bst.insert(4);
    //System.out.println("SIZE BST: " + bst.size());

    BST<Integer> bst2 = new NonEmptyBST<>(11);
      bst2 = bst2.insert(12);
      bst2 = bst2.insert(8);
      bst2 = bst2.insert(24);
      bst2 = bst2.insert(16);
      bst2 = bst2.insert(32);
      bst2 = bst2.insert(13);
      bst2 = bst2.insert(20);
      bst2 = bst2.insert(28);
      bst2 = bst2.insert(36);
      bst2 = bst2.insert(14);
      bst2 = bst2.insert(26);
      bst2 = bst2.insert(29);
      bst2 = bst2.insert(27);
      bst2 = bst2.insert(7);
      bst2 = bst2.insert(9);
      bst2 = bst2.insert(5);

      System.out.println("MIN: " + bst2.findMin());
      System.out.println("MAX: " + bst2.findMax());
      System.out.println("CONTAINS 29? " + bst2.contains(29));
      System.out.println("BEFORE REMOVAL:" );
    bst2.printBreadthFirstTraversal();
    System.out.println();
    bst2 = bst2.remove(24);
      System.out.println("AFTER REMOVAL: ");
      //bst.printInOrderTraversal();
      bst2.printBreadthFirstTraversal();

    BST<Integer> bst3 = new NonEmptyBST<>(8);
    /**bst3 = bst3.insert(7);
    bst3 = bst3.insert(6);
    bst3 = bst3.insert(5);
    bst3 = bst3.insert(4);
    bst3 = bst3.insert(3);
    System.out.println("BST 3 BEFORE REMOVAL: ");**/
    System.out.println();
    //bst3.printBreadthFirstTraversal();
    //System.out.println("BST 3 AFTER REMOVAL: ");
    bst3 = bst3.remove(8);
    //bst3.printBreadthFirstTraversal();


  }

}
