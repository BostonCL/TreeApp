import java.io.*;
import java.util.*; // for Stack class

////////////////////////////////////////////////////////////////
class Node {
  public int iData; //key
  public Node leftChild; // this node's left child
  public Node rightChild; // this node's right child
  // -------------------------------------------------------------

  public void display() // display yourself
  {
    System.out.print("{");
    System.out.print(iData);
    System.out.print("} ");
  }
  // -------------------------------------------------------------
} // end class Node

////////////////////////////////////////////////////////////////
class Tree {
  private Node root; // first node of tree
  // -------------------------------------------------------------
  public Tree() // constructor
  {
    root = null;
  } // no nodes in tree yet
  // -------------------------------------------------------------


  public Node search(int key) {
    //store root in currNode
    Node currNode = root;
    //loop through the tree till null pointer occur
    while (currNode != null) {
      //if currentnode is euqal to key then that's it we got our node
      if (currNode.iData == key)
        return currNode;
      //if current node data is greater than key then it means that our node is in left subtree
      else if (currNode.iData > key)
        currNode = currNode.leftChild;
      //otherwise we'll search in right subtree
      else
        currNode = currNode.rightChild;

    }
    return null;
  }
  // -------------------------------------------------------------

  public void insert(int id) {
    //first create a node
    Node newNode = new Node();
    newNode.iData = id;

    //check if Tree has no root then intialize root
    if (root == null)
      root = newNode;
    else {

      Node currNode = root;
      Node parNode = null;

      while (currNode != null) {
        parNode = currNode;
        //if current node data is less than key it means correct position for new node should be in right subtree
        if (currNode.iData < id)
          currNode = currNode.rightChild;
        // right subtree otherwise
        else
          currNode = currNode.leftChild;

      }

      //now we got parent node
      //now we check in which subtree our new node should be placed
      if (parNode.iData < id)
        parNode.rightChild = newNode;
      else
        parNode.leftChild = newNode;

    }

  } // end insert()
  // -------------------------------------------------------------
  
  public Node delete(Node root, int key) {

    //base case
    if (root == null) {
      return null;
    }

    //search for the node if current node is greater tyhan key than our node lies in right subtree
    if (key < root.iData) {
      root.leftChild = delete(root.leftChild, key);
    }
    //otherwise left subtree
    else if (key > root.iData) {
      root.rightChild = delete(root.rightChild, key);
    } else {
      //now we've to delete the node there are 3 cases
      //case 1:- if given node has no left child (then we simply replace it with right child)
      if (root.leftChild == null) {
        return root.rightChild;
        //case 2:- if given node has no right child (then we simply replace it with left child)
      } else if (root.rightChild == null) {
        return root.leftChild;
      }

      /*case 3:-
       * if given node has both right and left child(we can not delete this node directly,
       * so either we've to find inorder predecessor or inorder successor
       * for that we've created another method which return inorder successor
       */
      Node minNode = find_successor(root.rightChild);
      root.iData = minNode.iData;
      root.rightChild = delete(root.rightChild, root.iData);
    }
    return root;
  }

  //method which return inorder successor of given node
  private Node find_successor(Node node) {
    while (node.leftChild != null) {
      node = node.leftChild;
    }
    return node;
  }

  public boolean remove(int key) // delete node with given key
  { // (assumes non-empty list)

    //first search for the node if that node is not present in tree then return false;
    if (search(key) == null)
      return false;
    //if it is present then cal delete method which simply delete that node
    else
      delete(root, key);
    return true;

  } // end delete()
  // ----------------------------------------------------------

  // -------------------------------------------------------------
  public void traverse(int traverseType) {
    switch (traverseType) {
    case 1:
      System.out.print("Preorder traversal:  ");
      preOrder(root);
      System.out.print(" ");
      break;
    case 2:
      System.out.print("Inorder traversal:  ");
      inOrder(root);
      //System.out.print(" ");
      break;
    case 3:
      System.out.print("Postorder traversal:  ");
      postOrder(root);
      System.out.print(" ");
      break;
    }
    System.out.println();
  }
  // -------------------------------------------------------------
  private void preOrder(Node localRoot) {
    if (localRoot != null) {
      System.out.print(localRoot.iData + " ");
      preOrder(localRoot.leftChild);
      preOrder(localRoot.rightChild);
    }
  }
  // -------------------------------------------------------------
  private void inOrder(Node localRoot) {
    if (localRoot != null) {
      inOrder(localRoot.leftChild);
      System.out.print(localRoot.iData + " ");
      System.out.print(" ");
      inOrder(localRoot.rightChild);
    }
  }
  // -------------------------------------------------------------
  private void postOrder(Node localRoot) {
    if (localRoot != null) {
      postOrder(localRoot.leftChild);
      postOrder(localRoot.rightChild);
      //System.out.print(" ");
      System.out.print(" " + localRoot.iData + " ");

    }
  }
  // -------------------------------------------------------------
  public void displayTree() {
    Stack < Node > globalStack = new Stack < Node > ();
    globalStack.push(root);
    int nBlanks = 32;
    boolean isRowEmpty = false;
    System.out.println("......................................................");
    while (isRowEmpty == false) {
      Stack < Node > localStack = new Stack < Node > ();
      isRowEmpty = true;
      for (int j = 0; j < nBlanks; j++) System.out.print(" ");
      while (globalStack.isEmpty() == false) {
        Node temp = globalStack.pop();
        if (temp != null) {
          System.out.print(temp.iData);
          localStack.push(temp.leftChild);
          localStack.push(temp.rightChild);
          if (temp.leftChild != null || temp.rightChild != null)
            isRowEmpty = false;
        } else {
          System.out.print("--");
          localStack.push(null);
          localStack.push(null);
        }
        for (int j = 0; j < nBlanks * 2 - 2; j++)
          System.out.print(' ');
      } // end while globalStack not empty
      System.out.println();
      nBlanks /= 2;
      while (localStack.isEmpty() == false)
        globalStack.push(localStack.pop());
    } // end while isRowEmpty is false
    System.out.println("......................................................");
  } // end displayTree()
  // -------------------------------------------------------------

}
////////////////////////////////////////////////////////////////
public class TreeApp {

  public static void main(String args[]) {
    Scanner scnr = new Scanner(System.in);
    String task = "";
    int data = -1;
    boolean wasRemoved = false;
    Tree tree = new Tree();

    String line = scnr.nextLine();
    while (!line.equals("-1")) {

      task = line.split(" ")[0];
      data = Integer.parseInt(line.split(" ")[1]);

      if (task.equals("i")) { //insert
        tree.insert(data);
        System.out.println("Inserting " + data + ": complete.");
        tree.traverse(2);
      } else if (task.equals("s")) { //search
        Node node = tree.search(data);
        if (node == null) System.out.println("Searching for " + data + ": not found.");
        else System.out.println("Searching for " + data + ": found.");
      } else if (task.equals("r")) { //remove
        wasRemoved = tree.remove(data);
        if (wasRemoved) {
          System.out.println("Removing " + data + ": complete.");
        } else System.out.println("Removing " + data + ": not found.");
        tree.traverse(2);
      } else if (task.equals("t")) { //traverse
        tree.traverse(data);
      }

      line = scnr.nextLine();
    }
    tree.displayTree();

  }
}
