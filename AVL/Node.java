package a7;

public class Node<T extends Comparable>{
    private T parent;
    private SelfBalancingBST left;
    private SelfBalancingBST right;

    public Node() {
        parent = null;
        left = null;
        right = null;
    }

    public Node(T _parent, SelfBalancingBST _left, SelfBalancingBST _right) {
        parent = _parent;
        left = _left;
        right = _right;
    }
}
