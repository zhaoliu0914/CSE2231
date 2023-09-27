/**
 *
 * @author Zhao Liu
 *
 */
public class HeapSorting {

    /**
     * data representation.
     */
    private int[] array;

    private int size;

    private final int INITIAL_LENHTH = 16;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    public HeapSorting() {
        this.array = new int[this.INITIAL_LENHTH];
        this.size = 0;
    }

    /**
     *
     * @param index
     */
    private void siftUp(int index) {

        if (index > 0) {

            // get the parent node index.
            // if current node is left node, which means current index is odd.
            //    parent node index = left node index / 2
            // if current node is right node, which means current index is even.
            //    parent node index = (left node index / 2) - 1
            int parentNodeIndex = index / 2;
            if (index % 2 == 0) {
                parentNodeIndex--;
            }

            int currentNode = this.array[index];
            int parentNode = this.array[parentNodeIndex];

            // if current node is less than parent node,
            //    we should switch current node and parent node,
            //    and then recursively sift up parent node.
            // if current node is greater or equals than parent node,
            //    this means current node is located at right place.
            if (currentNode < parentNode) {
                this.array[index] = parentNode;
                this.array[parentNodeIndex] = currentNode;

                this.siftUp(parentNodeIndex);
            }
        }

    }

    /**
     *
     * @param index
     */
    private void siftDown(int index) {

        int leftNodeIndex = (index * 2) + 1;
        int rightNodeIndex = (index * 2) + 2;

        // There are 3 situations.
        // 1. the index of rightNode is greater or equals to last index,
        //     this means current node has 2 children,
        //     we have to compare 2 child node with current node.
        // 2. the index of leftNode is equals to last index,
        //     this means current node only has 1 left child node,
        //     we only have to compare left child node to current node.
        // 3. current node is a far ended node,
        //     we should replace this node with last node.

        if (rightNodeIndex <= this.size) {
            int leftNode = this.array[leftNodeIndex];
            int rightNode = this.array[rightNodeIndex];

            if (leftNode <= rightNode) {
                // if the left node less or equals than right node,
                // the parent node will be replaced by left node.
                this.array[index] = leftNode;

                // recursively sift down the array
                this.siftDown(leftNodeIndex);
            } else {
                // if the right node less than left node,
                // the parent node will be replaced by right node.
                this.array[index] = rightNode;

                // recursively sift down the array
                this.siftDown(rightNodeIndex);
            }

        } else if (leftNodeIndex == this.size) {
            int leftNode = this.array[leftNodeIndex];

            this.array[index] = leftNode;
        } else {
            this.array[index] = this.array[this.size - 1];
        }
    }

    /**
     *
     * @param element
     */
    public void add(int element) {
        if (this.size < this.array.length) {
            this.array[this.size] = element;

            this.siftUp(this.size);

            this.size++;
        }
    }

    /**
     *
     * @return
     */
    public int removeFirst() {
        int removedValue = this.array[0];

        this.siftDown(0);

        this.size--;

        return removedValue;
    }

    /**
     *
     * @param element
     * @return
     */
    public boolean contains(int element) {
        boolean isContain = false;

        for (int i = 0; i < this.size; i++) {
            int temp = this.array[i];
            if (temp == element) {
                isContain = true;
            }
        }

        return isContain;
    }

    /**
     *
     */
    public void printHeap() {
        System.out.print("[");
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.array[i]);
            if (i < this.size - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        HeapSorting hs = new HeapSorting();

        hs.add(13);
        hs.printHeap();

        hs.add(21);
        hs.printHeap();

        hs.add(16);
        hs.printHeap();

        hs.add(24);
        hs.printHeap();

        hs.add(31);
        hs.printHeap();

        hs.add(19);
        hs.printHeap();

        hs.add(68);
        hs.printHeap();

        hs.add(65);
        hs.printHeap();

        hs.add(26);
        hs.printHeap();

        hs.add(32);
        hs.printHeap();

        System.out.println("Test for adding 14.");

        hs.add(14);
        hs.printHeap();

        System.out.println("Test removing the smallest");

        HeapSorting hs1 = new HeapSorting();
        hs1.add(13);
        hs1.add(14);
        hs1.add(16);
        hs1.add(19);
        hs1.add(21);
        hs1.add(19);
        hs1.add(68);
        hs1.add(65);
        hs1.add(26);
        hs1.add(32);
        hs1.add(31);

        hs1.printHeap();
        int removedValue = hs1.removeFirst();
        System.out.println("removed value = " + removedValue);
        hs1.printHeap();
    }

}
