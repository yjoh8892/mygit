import java.util.LinkedList;

/**
 * Red black tree implementation with a Node inner class for representing
 * the nodes within a red black tree.  You can use this class' insert
 * method to build a red black tree, and its toString method to display
 * the level order (breadth first) traversal of values in that tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always be maintained.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild; 
        public Node<T> rightChild;
        public boolean isBlack = false;
        public Node(T data) { this.data = data; }
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }
        /**
         * This method performs a level order traversal of the tree rooted
         * at the current node.  The string representations of each data value
         * within this tree are assembled into a comma separated string within
         * brackets (similar to many implementations of java.util.Collection).
         * @return string containing the values of this tree in level order
         */
        @Override
        public String toString() { // display subtree in order traversal
            String output = "[";
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if(!q.isEmpty()) output += ", ";
            }
            return output + "]";
        }
    }

    protected Node<T> root; // reference to root node of tree, null when empty

    /**
     * Performs a naive insertion into a Red black tree: adding the input
     * data value to a new node in a leaf position within the tree.  After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this red black tree
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the tree already contains data
     */
    public void insert(T data) throws NullPointerException,
				      IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
            "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if(root == null) { root = newNode; } // add first node to an empty tree
        else insertHelper(newNode,root); // recursively insert into subtree
        root.isBlack = true;
    }

    private void enforceRBTreePropertiesAfterInsert(Node<T> node) {
        Node<T> parent = node.parent;
        if(parent != null && !node.isBlack && !parent.isBlack) {
            Node<T> parentSibling;
            if(parent.isLeftChild()) {
                parentSibling = parent.parent.rightChild;
            } else {
                parentSibling = parent.parent.leftChild;
            }
            if(parentSibling == null || parentSibling.isBlack){
                Node<T> grandParent = parent.parent;
                if((node.isLeftChild() && parent.isLeftChild()) || (!node.isLeftChild() && !parent.isLeftChild())) {
                    rotate(parent, grandParent);
                    boolean color = grandParent.isBlack;
                    grandParent.isBlack = parent.isBlack;
                    parent.isBlack = color;
                } else {
                    rotate(node, parent);
                    boolean color = node.parent.isBlack;
                    node.parent.isBlack = node.isBlack;
                    node.isBlack = color;
                    rotate(node, node.parent);
                }
            } else {
                parent.isBlack = true;
                parentSibling.isBlack = true;
                if(parent.parent != root){
                    parent.parent.isBlack = !parent.parent.isBlack;
                    enforceRBTreePropertiesAfterInsert(parent.parent);
                }

            }
        }
    }

    /** 
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the 
     *      newNode should be inserted as a descenedent beneath
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references (as defined by Comparable.compareTo())
     */
    private void insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) throw new IllegalArgumentException(
            "This RedBlackTree already contains that value.");

        // store newNode within left subtree of subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
            // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else { 
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
            // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.rightChild);
        }
        enforceRBTreePropertiesAfterInsert(newNode);
    }

    /**
     * This method performs a level order traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * @return string containing the values of this tree in level order
     */
    @Override
    public String toString() { return root.toString(); }

    /**
     * Performs the rotation operation on the provided nodes within this BST.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation (sometimes called a left-right 
     * rotation).  When the provided child is a rightChild of the provided 
     * parent, this method will perform a left rotation (sometimes called a 
     * right-left rotation).  When the provided nodes are not related in one 
     * of these ways, this method will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        if (child == null || parent== null || child.parent != parent) {
            throw new IllegalArgumentException();
        }
        if (child.isLeftChild()) {
            Node<T> childRightChild = child.rightChild;
            if (childRightChild != null) {
                childRightChild.parent = parent;
            }
            parent.leftChild = childRightChild;
            if (parent.parent == null) {
                root = child;
            }
            else {
                if (parent.isLeftChild()) {
                    parent.parent.leftChild = child;
                }
                else {
                    parent.parent.rightChild = child;
                }
                child.parent = parent.parent;
            }

            parent.parent = child;
            child.rightChild = parent;
        }
        else {
            Node<T> childLeftChild = child.leftChild;
            if (childLeftChild != null) {
                childLeftChild.parent = parent;
            }
            parent.rightChild = childLeftChild;
            if (parent.parent == null) {
                root = child;
            }
            else {
                if (parent.isLeftChild()) {
                    parent.parent.leftChild = child;
                }
                else {
                    parent.parent.rightChild = child;
                }
                child.parent = parent.parent;
            }
            parent.parent = child;
            child.leftChild = parent;
        }
    }
}
