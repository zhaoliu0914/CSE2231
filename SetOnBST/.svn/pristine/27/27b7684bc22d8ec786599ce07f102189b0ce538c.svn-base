import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 *
 * @author Zhao Liu
 * @author Zishu Ling
 *
 */
public class Set3a<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        boolean exist = false;

        if (t.size() > 0) {
            BinaryTree<T> leftNode = t.newInstance();
            BinaryTree<T> rightNode = t.newInstance();
            T node = t.disassemble(leftNode, rightNode);

            // checking for left nodes or right nodes.
            if (x.compareTo(node) < 0) {
                exist = isInTree(leftNode, x);
            } else if (x.compareTo(node) > 0) {
                exist = isInTree(rightNode, x);
            } else {
                exist = true;
            }

            // restore the original node.
            t.assemble(node, leftNode, rightNode);
        }

        return exist;
    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        T nodeValue;
        BinaryTree<T> leftNode = t.newInstance();
        BinaryTree<T> rightNode = t.newInstance();

        if (t.size() != 0) {
            // recursive to find the correct position for insertion element.
            nodeValue = t.disassemble(leftNode, rightNode);

            if (x.compareTo(nodeValue) <= 0) {
                // recursive loop the left node.
                insertInTree(leftNode, x);
            } else {
                // recursive loop the right node.
                insertInTree(rightNode, x);
            }

            // keeps the binary search tree balanced
            t.inOrderAssemble(nodeValue, leftNode, rightNode);
        } else {
            // insertion is the smallest or largest value to insert.
            t.assemble(x, leftNode, rightNode);
        }
    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";
        assert t.size() > 0 : "Violation of: |t| > 0";

        T smallestKey = null;
        if (t.size() > 0) {
            BinaryTree<T> leftNode = t.newInstance();
            BinaryTree<T> rightNode = t.newInstance();

            int currentNodeSize = t.size();

            T nodeValue = t.disassemble(leftNode, rightNode);

            if (currentNodeSize == 1) {
                // node is far left and does not have children nodes.
                smallestKey = nodeValue;
            } else if (leftNode.size() > 0) {
                // there is still some left children nodes.
                // continue to recursive into left nodes.
                smallestKey = removeSmallest(leftNode);

                t.assemble(nodeValue, leftNode, rightNode);
            } else {
                // current node only has right node.
                // make right node to replace the current node.
                smallestKey = nodeValue;

                // replacement operation.
                BinaryTree<T> newLeftNode = rightNode.newInstance();
                BinaryTree<T> newRightNode = rightNode.newInstance();
                T newNodeValue = rightNode.disassemble(newLeftNode,
                        newRightNode);
                t.assemble(newNodeValue, newLeftNode, newRightNode);
            }
        }

        return smallestKey;
    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        assert t.size() > 0 : "Violation of: x is in labels(t)";

        T removedValue = null;
        BinaryTree<T> leftNode = t.newInstance();
        BinaryTree<T> rightNode = t.newInstance();

        if (t.size() != 0) {
            // recursive to find the correct position for insertion element.
            T nodeValue = t.disassemble(leftNode, rightNode);

            if (x.compareTo(nodeValue) == 0) {
                removedValue = nodeValue;

                // when right node and left node do not exist.
                // the removed value is located at the far end the tree.
                // just disassemble it and do not assemble.

                if (rightNode.size() != 0) {
                    // when there exist right node.
                    // replace the smallest node on the right to removed value.
                    T smallestValue = removeSmallest(rightNode);

                    t.assemble(smallestValue, leftNode, rightNode);
                } else if (leftNode.size() != 0) {
                    // when right node does not exist and left node exist
                    // replace the next left node to removed value.
                    BinaryTree<T> newLeftNode = rightNode.newInstance();
                    BinaryTree<T> newRightNode = rightNode.newInstance();
                    T newNodeValue = leftNode.disassemble(newLeftNode,
                            newRightNode);

                    t.assemble(newNodeValue, newLeftNode, newRightNode);
                }
            } else if (x.compareTo(nodeValue) < 0) {
                // recursive loop the left node.
                removedValue = removeFromTree(leftNode, x);

                t.assemble(nodeValue, leftNode, rightNode);
            } else {
                // recursive loop the right node.
                removedValue = removeFromTree(rightNode, x);

                t.assemble(nodeValue, leftNode, rightNode);
            }
        }
        return removedValue;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        this.tree = new BinaryTree1<>();

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3a() {

        this.createNewRep();

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3a<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3a<T> localSource = (Set3a<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";

        insertInTree(this.tree, x);
    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        T temp = removeFromTree(this.tree, x);
        return temp;
    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";

        T temp = removeSmallest(this.tree);
        return temp;
    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";

        return isInTree(this.tree, x);
    }

    @Override
    public final int size() {
        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.tree.iterator();
    }

}
