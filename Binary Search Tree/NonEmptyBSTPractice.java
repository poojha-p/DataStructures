package a5;


import javax.print.DocFlavor;

public class NonEmptyBSTPractice<T extends Comparable<T>> implements BST<T> {
    private Node<T> _root;
    private int _size;

    public NonEmptyBSTPractice(T element) {

        _root = new Node(element);
        _size = 0;
    }


    // TODO: size
    @Override
    public int size() {

        if (isEmpty()) {
            return _size=0;
        }
        else {
            _size = _root._left.size() + _root._right.size() + 1;
            return _size;
        }
    }

    // TODO: findMin
    @Override
    public T findMin() {
        if (_root._right == null && _root._left == null) {
            return _root._element;
        }
        else {
            return _root._left.getLeft().findMin();
        }
    }

    // TODO: findMax
    @Override
    public T findMax() {
        if (_root._right == null && _root._left == null) {
            return _root._element;
        }
        else {
            return _root._right.getRight().findMax();
        }
    }

    // TODO: contains
    @Override
    public boolean contains(T element) {
        if (element.compareTo(_root._element) == 0) {
            return true;
        }
        else if (element.compareTo(getElement()) < 0) {
            return contains(element, _root._left);
        }
        else if (element.compareTo(getElement()) > 0) {
            return contains(element, _root._right);
        }
        else
            return false;
    }

    private boolean contains(T element, BST<T> root) {
        if (element.compareTo(root.getElement()) == 0) {
            return true;
        }
        else if (element.compareTo(root.getElement()) < 0) {
            return contains(element, root.getLeft());
        }
        else {
            return contains(element, root.getRight());
        }
    }

    // TODO: insert
    @Override
    public BST<T> insert(T element) {
        if (_root == null) {
            return new NonEmptyBSTPractice(element);
        }
        else if (_root._left == null && _root._right == null) {
            if (element.compareTo(_root._element) >= 0) {
                _root._right = new NonEmptyBSTPractice<T>(element);
                return _root._right;
            } else {
                _root._left = new NonEmptyBSTPractice<T>(element);
                return _root._left;
            }
        } else if (element.compareTo(_root._element) < 0) {
            return _root._left.insert(element);
        }
        else
            return _root._right.insert(element);
    }


    // TODO: remove
    @Override
    public BST<T> remove(T element) {
        if (_root == null) {
            return new EmptyBST<>();
        }
        else if (element.compareTo(_root._element) < 0) {
            _root._right = _root._right.remove(element);
        }
        else if (element.compareTo(_root._element) < 0) {
            _root._left = _root._left.remove(element);
        }
        else {
            if (_root._right == null) {
                return _root._left;
            }
            if (_root._left == null) {
                return _root._right;
            }

            _root._element = removeReplacer(_root._right);
            _root._right = _root._right.remove(_root._element);
        }
        return new NonEmptyBSTPractice<>(_root._element);
    }

    private T removeReplacer (BST<T> tree) {
        T min = tree.getElement();
        while (tree.getLeft() != null) {
            tree = tree.getLeft();
            min = tree.getElement();
        }

        return min;
    }

    //TODO: printInOrderTraversal
    //@Override
    public void printInOrderTraversal()
    {
        Node current = _root;
        if (current._left == null) {
            return;
        }

        current._left.printInOrderTraversal();

        System.out.print(current._element + " ");

        current._right.printInOrderTraversal();


    }

    // TODO: printPreOrderTraversal
    @Override
    public void printPreOrderTraversal() {
    }

    // TODO: printPostOrderTraversal
    @Override
    public void printPostOrderTraversal() {
    }

    // TODO: printBreadthFirstTraversal
    @Override
    public void printBreadthFirstTraversal() {
    }

    @Override
    public int getHeight() {
        return Math.max(_root._left.getHeight(), _root._right.getHeight())+1;
    }


    @Override
    public BST<T> getLeft() {
        return _root._left;
    }

    @Override
    public BST<T> getRight() {
        return _root._right;
    }

    @Override
    public T getElement() {
        return _root._element;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}

class Node<T extends Comparable<T>>
{
    public T _element;
    public BST<T> _left;
    public BST<T> _right;

    public Node (T element) {
        _element = element;
        _left = _right = null;
    }
}
