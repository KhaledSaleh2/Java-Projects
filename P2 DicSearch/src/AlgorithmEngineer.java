import java.util.LinkedList;
import java.util.Stack;
import java.util.NoSuchElementException;

/**
 * Red-Black Tree implementation with a Node inner class for representing the nodes of the tree.
 * Currently, this implements a Binary Search Tree that we will turn into a red black tree by
 * modifying the insert functionality. In this activity, we will start with implementing rotations
 * for the binary search tree insert algorithm.
 */
public class AlgorithmEngineer<T extends Comparable<T>> implements AlgorithmEngineerInterface<T> {

  /**
   * This class represents a node holding a single value within a binary tree.
   */
  protected static class Node<T> {
    public T data;
    public int blackHeight;
    // The context array stores the context of the node in the tree:
    // - context[0] is the parent reference of the node,
    // - context[1] is the left child reference of the node,
    // - context[2] is the right child reference of the node.
    // The @SupressWarning("unchecked") annotation is used to supress an unchecked
    // cast warning. Java only allows us to instantiate arrays without generic
    // type parameters, so we use this cast here to avoid future casts of the
    // node type's data field.
    @SuppressWarnings("unchecked")
    public Node<T>[] context = (Node<T>[]) new Node[3];

    public Node(T data) {
      this.data = data;
    }

    /**
     * @return true when this node has a parent and is the right child of that parent, otherwise
     *         return false
     */
    public boolean isRightChild() {
      return context[0] != null && context[0].context[2] == this;
    }

    /**
     * @return true when this node has a parent and is the left child of that parent, otherwise
     *         return false
     */
    public boolean isLeftChild() {
      return context[0] != null && context[0].context[1] == this;
    }
  }

  protected Node<T> root; // reference to root node of tree, null when empty
  protected int size = 0; // the number of values in the tree

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
   * 
   * @param data to be added into this binary search tree
   * @return true if the value was inserted, false if not
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when data is already contained in the tree
   */
  public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");

    Node<T> newNode = new Node<>(data);
    if (this.root == null) {
      // add first node to an empty tree
      root = newNode;
      size++;
      enforceRBTreePropertiesAfterInsert(newNode);
      return true;
    } else {
      // insert into subtree
      Node<T> current = this.root;
      while (true) {
        int compare = newNode.data.compareTo(current.data);
        if (compare == 0) {
          throw new IllegalArgumentException(
              "This RedBlackTree already contains value " + data.toString());
        } else if (compare < 0) {
          // insert in left subtree
          if (current.context[1] == null) {
            // empty space to insert into
            current.context[1] = newNode;
            newNode.context[0] = current;
            this.size++;
            enforceRBTreePropertiesAfterInsert(newNode);
            return true;
          } else {
            // no empty space, keep moving down the tree
            current = current.context[1];
          }
        } else {
          // insert in right subtree
          if (current.context[2] == null) {
            // empty space to insert into
            current.context[2] = newNode;
            newNode.context[0] = current;
            this.size++;
            enforceRBTreePropertiesAfterInsert(newNode);
            return true;
          } else {
            // no empty space, keep moving down the tree
            current = current.context[2];
          }
        }
      }
    }
  }

  /**
   * Performs the rotation operation on the provided nodes within this tree. When the provided child
   * is a left child of the provided parent, this method will perform a right rotation. When the
   * provided child is a right child of the provided parent, this method will perform a left
   * rotation. When the provided nodes are not related in one of these ways, this method will throw
   * an IllegalArgumentException.
   * 
   * @param child  is the node being rotated from child to parent position (between these two node
   *               arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *               arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *                                  initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    // TODO: Implement this method.
    Node<T> tmpParent = parent.context[0];

    // if parent is a root node, set the child node to the new root node
    if (parent != this.root) {
      // special handling if the parent node is not the root node, get parent node's parent and set
      // its child node accordingly
      if (parent.isLeftChild()) {
        parent.context[0].context[1] = child;
      } else if (parent.isRightChild()) {
        parent.context[0].context[2] = child;
      }
    } else {
      this.root = child;
    }

    if (child.isRightChild()) {
      // perform left rotation
      Node<T> temp_child_left = child.context[1];
      child.context[1] = parent;
      parent.context[2] = temp_child_left;
      child.context[0] = tmpParent;

    } else if (child.isLeftChild()) {
      // perform right rotation
      Node<T> temp_child_right = child.context[2];
      child.context[2] = parent;
      parent.context[1] = temp_child_right;
      child.context[0] = tmpParent;
    } else {
      throw new IllegalArgumentException("Invalid parent and child");
    }
    // child becomes the parent
    parent.context[0] = child;
  }

  /**
   * Get the size of the tree (its number of nodes).
   * 
   * @return the number of nodes in the tree
   */
  public int size() {
    return size;
  }

  /**
   * Method to check if the tree is empty (does not contain any node).
   * 
   * @return true of this.size() return 0, false if this.size() > 0
   */
  public boolean isEmpty() {
    return this.size() == 0;
  }

  /**
   * Removes the value data from the tree if the tree contains the value. This method will not
   * attempt to rebalance the tree after the removal and should be updated once the tree uses
   * Red-Black Tree insertion.
   * 
   * @return true if the value was remove, false if it didn't exist
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when data is not stored in the tree
   */
  public void remove(T data) throws NullPointerException, IllegalArgumentException { // null
                                                                                     // references
                                                                                     // will not be
                                                                                     // stored
                                                                                     // within this
                                                                                     // tree
    if (data == null) {
      throw new NoSuchElementException("This RedBlackTree cannot store null references.");
    } else {
      Node<T> nodeWithData = this.findNodeWithData(data);
      // throw exception if node with data does not exist
      if (nodeWithData == null) {
        throw new NoSuchElementException("The following value is not in the tree and"
            + " cannot be deleted: " + data.toString());
      }

      boolean hasRightChild = (nodeWithData.context[2] != null);
      boolean hasLeftChild = (nodeWithData.context[1] != null);
      if (hasRightChild && hasLeftChild) {
        // has 2 children
        Node<T> successorNode = this.findMinOfRightSubtree(nodeWithData);
        // replace value of node with value of successor node
        nodeWithData.data = successorNode.data;


        // remove successor node
        if (successorNode.context[2] == null) {
          // successor has no children, replace with null
          this.replaceNode(successorNode, null);

          if (successorNode.blackHeight == 1) {
            Node<T> nilNode = new Node<T>(null);
            nilNode.blackHeight = 2;
            rebalanceTreeAfterNodeRemoval(nilNode);
          }

          // parent of successor is null


        } else {
          // successor has a right child, replace successor with its child
          this.replaceNode(successorNode, successorNode.context[2]);
          rebalanceTreeAfterNodeRemoval(successorNode.context[2]);
        }
      } else if (hasRightChild) {
        // only right child, replace with right child
        this.replaceNode(nodeWithData, nodeWithData.context[2]);
        rebalanceTreeAfterNodeRemoval(nodeWithData.context[2]);

      } else if (hasLeftChild) {
        // only left child, replace with left child
        this.replaceNode(nodeWithData, nodeWithData.context[1]);
        rebalanceTreeAfterNodeRemoval(nodeWithData.context[1]);
      } else {
        // no children, replace node with a null node
        this.replaceNode(nodeWithData, null);
      }
      this.size--;
    }
  }

  /**
   * A method tht recursively rebalances the red black tree following a deterministic algorithim
   * after a remove operation is performed.
   * 
   * @param node - The node that rebalancing begins at, which was either where the remove operation
   *             was performed, or where the last rebalancing ended
   */
  private void rebalanceTreeAfterNodeRemoval(Node<T> node) {
    // A quick refrence to the node's sibling
    Node<T> sibling;
    if (node != root && node.blackHeight != 0) {
      if (node.isRightChild()) {
        // Uses rebalance operations if node is a right child
        sibling = node.context[0].context[1];
        if (sibling.blackHeight == 0) {
          // If sibling is red, performs a left rotation on the node and recolor
          sibling.blackHeight = 1;
          node.context[0].blackHeight = 0;
          rotate(node, node.context[0]);
          sibling = node.context[0].context[1];
        }
        if (sibling.context[1].blackHeight == 0 || sibling.context[2].blackHeight == 0) {
          if (sibling.context[2].blackHeight == 1) {
            // if Sibling has a black right child, perform a right rotation on sibling and a left
            sibling.blackHeight = 0;
            sibling.context[1].blackHeight = 1;
            rotate(sibling.context[1], sibling);
            sibling = node.context[0].context[1];
          }
          // If sibling has a red child, perform a left rotation on the node before recoloring
          node.context[0].blackHeight = 1;
          sibling.blackHeight = node.context[0].blackHeight;
          sibling.context[2].blackHeight = 1;
          rotate(node, node.context[0]);
          node.blackHeight = 1;
          return; // Rebalancing is over
        } else {
          node = node.context[0];
          sibling.blackHeight = 0;
        }
      } else {
        // If node is left child, uses left child rebalancing operations
        sibling = node.context[0].context[1];
        if (sibling.blackHeight == 0) {
          // if sibling is red, rotates node right and recolors
          node.context[0].blackHeight = 0;
          sibling.blackHeight = 1;
          rotate(node.context[0].context[1], node.context[0]);
          sibling = node.context[0].context[1];
        }
        if (sibling.context[2].blackHeight == 1 && sibling.context[1].blackHeight == 1) {
          // if sibling has two red children, recolor
          sibling.blackHeight = 0;
          node = node.context[0];
        } else {
          if (sibling.context[1].blackHeight == 1) {
            // If sibling has a black left child, rotate sibling left and recolor
            sibling.blackHeight = 0;
            sibling.context[2].blackHeight = 1;
            rotate(sibling.context[2], sibling);
            sibling = node.context[0].context[1];
          }
          // If sibling does not have two red children, rotate node right
          sibling.blackHeight = node.context[0].blackHeight;
          sibling.context[1].blackHeight = 1;
          node.context[0].blackHeight = 1;
          rotate(node.context[0].context[1], node.context[0]);
          node.blackHeight = 1;
          return; // Rebalancing is over
        }
      }
      // Repeats rebalancing with a node closer to the root
      rebalanceTreeAfterNodeRemoval(node);
    } else {
      node.blackHeight = 1;
    }
  }

  /**
   * Checks whether the tree contains the value *data*.
   * 
   * @param data the data value to test for
   * @return true if *data* is in the tree, false if it is not in the tree
   */
  public boolean contains(T data) {
    // null references will not be stored within this tree
    if (data == null) {
      throw new NullPointerException("This RedBlackTree cannot store null references.");
    } else {
      Node<T> nodeWithData = this.findNodeWithData(data);
      // return false if the node is null, true otherwise
      return (nodeWithData != null);
    }
  }

  /**
   * Helper method that will replace a node with a replacement node. The replacement node may be
   * null to remove the node from the tree.
   * 
   * @param nodeToReplace   the node to replace
   * @param replacementNode the replacement for the node (may be null)
   */
  protected void replaceNode(Node<T> nodeToReplace, Node<T> replacementNode) {
    if (nodeToReplace == null) {
      throw new NullPointerException("Cannot replace null node.");
    }
    if (nodeToReplace.context[0] == null) {
      // we are replacing the root
      if (replacementNode != null)
        replacementNode.context[0] = null;
      this.root = replacementNode;
    } else {
      // set the parent of the replacement node
      if (replacementNode != null)
        replacementNode.context[0] = nodeToReplace.context[0];
      // do we have to attach a new left or right child to our parent?
      if (nodeToReplace.isRightChild()) {
        nodeToReplace.context[0].context[2] = replacementNode;
      } else {
        nodeToReplace.context[0].context[1] = replacementNode;
      }
    }
  }

  /**
   * Helper method that will return the inorder successor of a node with two children.
   * 
   * @param node the node to find the successor for
   * @return the node that is the inorder successor of node
   */
  protected Node<T> findMinOfRightSubtree(Node<T> node) {
    if (node.context[1] == null && node.context[2] == null) {
      throw new IllegalArgumentException("Node must have two children");
    }
    // take a steop to the right
    Node<T> current = node.context[2];
    while (true) {
      // then go left as often as possible to find the successor
      if (current.context[1] == null) {
        // we found the successor
        return current;
      } else {
        current = current.context[1];
      }
    }
  }

  /**
   * Helper method that will return the node in the tree that contains a specific value. Returns
   * null if there is no node that contains the value.
   * 
   * @return the node that contains the data, or null of no such node exists
   */
  protected Node<T> findNodeWithData(T node) {
    Node<T> current = this.root;
    if (node instanceof Word) {
      while (current != null) {
        int compare = ((Word) node).getWord().compareTo(((Word) current.data).getWord());
        if (compare == 0) {
          // we found our value
          return current;
        } else if (compare < 0) {
          // keep looking in the left subtree
          current = current.context[1];
        } else {
          // keep looking in the right subtree
          current = current.context[2];
        }
      }
    } else {
      while (current != null) {
        int compare = node.compareTo(current.data);
        if (compare == 0) {
          // we found our value
          return current;
        } else if (compare < 0) {
          // keep looking in the left subtree
          current = current.context[1];
        } else {
          // keep looking in the right subtree
          current = current.context[2];
        }
      }
    }

    // we're at a null node and did not find data, so it's not in the tree
    return null;
  }

  /**
   * This method performs an inorder traversal of the tree. The string representations of each data
   * value within this tree are assembled into a comma separated string within brackets (similar to
   * many implementations of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
   * 
   * @return string containing the ordered values of this tree (in-order traversal)
   */
  public String toInOrderString() {
    // generate a string of all values of the tree in (ordered) in-order
    // traversal sequence
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    if (this.root != null) {
      Stack<Node<T>> nodeStack = new Stack<>();
      Node<T> current = this.root;
      while (!nodeStack.isEmpty() || current != null) {
        if (current == null) {
          Node<T> popped = nodeStack.pop();
          sb.append(popped.data.toString());
          if (!nodeStack.isEmpty() || popped.context[2] != null)
            sb.append(", ");
          current = popped.context[2];
        } else {
          nodeStack.add(current);
          current = current.context[1];
        }
      }
    }
    sb.append(" ]");
    return sb.toString();
  }

  /**
   * This method performs a level order traversal of the tree. The string representations of each
   * data value within this tree are assembled into a comma separated string within brackets
   * (similar to many implementations of java.util.Collection). This method will be helpful as a
   * helper for the debugging and testing of your rotation implementation.
   * 
   * @return string containing the values of this tree in level order
   */
  public String toLevelOrderString() {
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    if (this.root != null) {
      LinkedList<Node<T>> q = new LinkedList<>();
      q.add(this.root);
      while (!q.isEmpty()) {
        Node<T> next = q.removeFirst();
        if (next.context[1] != null)
          q.add(next.context[1]);
        if (next.context[2] != null)
          q.add(next.context[2]);
        sb.append(next.data.toString());
        if (!q.isEmpty())
          sb.append(", ");
      }
    }
    sb.append(" ]");
    return sb.toString();
  }

  public String toString() {
    return "level order: " + this.toLevelOrderString() + "\nin order: " + this.toInOrderString();
  }

  /**
   * Uses comparable to check if the passed node is equal to any node in the tree and returns that
   * node
   * 
   * @param node that will be used to compare to nodes in the tree
   * @return the node that is marked passed node by comparable
   */
  @Override
  public T get(T node) {
    Node<T> target = findNodeWithData(node);
    if (target == null) {
      return null;
    } else {
      return target.data;
    }
  }

  /**
   * Removes the passed node from the red black tree
   * 
   * @param node - the node to search for and remove
   * @throws NoSuchElementException if the passed node is not in the tree
   */
  @Override
  public void removeNode(T node) throws NoSuchElementException {
    try {
      remove(node);
    } catch (IllegalArgumentException e) {
      throw new NoSuchElementException(e.getMessage());
    }
  }


  /**
   * this method fixes any red black tree violations that appear after inserting a red node
   * 
   * @param newNode - the node that is currently being inserted (red)
   */
  protected void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
    Node<T> parent = newNode.context[0];

    if (parent == null || parent.blackHeight == 1) {
      return;
    }

    Node<T> grandparent = parent.context[0];

    // case 1: parent's sibling is black or null, and parent and newNode are same side --> rotate
    // and color swap
    if (newNode.isLeftChild() && parent.isLeftChild()
        && (grandparent.context[2] == null || grandparent.context[2].blackHeight == 1)
        || newNode.isRightChild() && parent.isRightChild()
            && (grandparent.context[1] == null || grandparent.context[1].blackHeight == 1)) {
      rotate(parent, grandparent);
      parent.blackHeight = 1;
      grandparent.blackHeight = 0;
    }

    // case 2: parent's sibling is black or null, and parent and newNode are not same side -->
    // rotate parent and newNode, then apply case 1 procedure
    else if (newNode.isRightChild() && parent.isLeftChild()
        && (grandparent.context[2] == null || grandparent.context[2].blackHeight == 1)
        || newNode.isLeftChild() && parent.isRightChild()
            && (grandparent.context[1] == null || grandparent.context[1].blackHeight == 1)) {
      rotate(newNode, parent);
      rotate(newNode, grandparent);
      newNode.blackHeight = 1;
      grandparent.blackHeight = 0;
    }

    // case 3: parent's sibling is red --> change color of parent, parent's parent, and parent's
    // sibling, then check for and resolve any new violations using recursion

    // parent is a left child
    else if (parent.isLeftChild() && grandparent.context[2].blackHeight == 0) {
      parent.blackHeight = 1;
      grandparent.blackHeight = 0;
      grandparent.context[2].blackHeight = 1;
      enforceRBTreePropertiesAfterInsert(grandparent);
    }

    // parent is a right child
    else if (parent.isRightChild() && grandparent.context[1].blackHeight == 0) {
      parent.blackHeight = 1;
      grandparent.blackHeight = 0;
      grandparent.context[1].blackHeight = 1;
      enforceRBTreePropertiesAfterInsert(grandparent);
    }

    root.blackHeight = 1;
  }
}
