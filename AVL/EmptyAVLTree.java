package a7;

public class EmptyAVLTree<T extends Comparable<T>> extends AVLTree<T> {

    public boolean isEmpty() {
        return true;
    }


    public int height() {
        return 0;
    }


    public int size() {
        return 0;
    }


    public SelfBalancingBST<T> insert(T element) {
        return new AVLTree(element);
    }


    public SelfBalancingBST<T> remove(T element) {
        return this;
    }


    public T findMin() {
        throw new UnsupportedOperationException();
    }


    public T findMax() {
        throw new UnsupportedOperationException();
    }


    public boolean contains(T element) {
        return false;
    }


    public T getValue() {
        throw new UnsupportedOperationException();
    }


    public SelfBalancingBST<T> getLeft() {
        throw new UnsupportedOperationException();
    }


    public SelfBalancingBST<T> getRight() {
        throw new UnsupportedOperationException();
    }
}
