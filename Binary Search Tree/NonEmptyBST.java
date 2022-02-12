package a5;


import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;
	private int _size;

	public NonEmptyBST(T element) {

		_left = new EmptyBST<>();
		_right = new EmptyBST<>();
		_element = element;
		_size = 0;
	}


	// TODO: size
	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		}
		else {
			return _left.size() + 1 + _right.size();
		}
	}

	// TODO: findMin
	@Override
	public T findMin() {
		if (_left.isEmpty()) {
			return _element;
		}
		else {
			return _left.findMin();
		}
	}

	// TODO: findMax
	@Override
	public T findMax() {
		if (_right.isEmpty()) {
			return _element;
		}
		else {
			return _right.findMax();
		}
	}

	// TODO: contains
	@Override
	public boolean contains(T element) {
		if (isEmpty()) {
			return false;
		}
		else if (element.compareTo(_element) < 0) {
			return _left.contains(element);
		}
		else if (element.compareTo(_element) > 0) {
			return _right.contains(element);
		}
		else {
			return true;
		}
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element) {

		if (isEmpty()) {
			return new NonEmptyBST(element);
		}
		else {
			if (element.compareTo(_element) < 0) {
				if (_left.isEmpty()) {
					_left = new NonEmptyBST<>(element);
					return this;
				}
				_left.insert(element);
				return this;
			} else {
				if (_right.isEmpty()) {
					_right = new NonEmptyBST<>(element);
					return this;
				}
				_right.insert(element);
				return this;
			}
		}
	}


	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if (element.compareTo(_element) > 0) {
			_right = _right.remove(element);
		}
		else if (element.compareTo(_element) < 0) {
			_left = _left.remove(element);
		}
		else {
			if (_right.isEmpty()) {
				return _left;
			}
			else if (_left.isEmpty()) {
				return _right;
			}
			else {
				T minToReplaceWith = _right.findMin();
				_element = minToReplaceWith;
				_right = _right.remove(minToReplaceWith);
			}
		}
		return this;
	}

	//TODO: printInOrderTraversal
	//@Override
	public void printInOrderTraversal()
	{
		getLeft().printInOrderTraversal();

		System.out.print(getElement() + " ");

		getRight().printInOrderTraversal();

		//System.out.println();
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		System.out.print(_element + " ");
		_left.printPreOrderTraversal();
		_right.printPreOrderTraversal();

		//System.out.println();
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		_left.printPostOrderTraversal();
		_right.printPostOrderTraversal();
		System.out.print(_element + " ");

		//System.out.println();
	}

	// TODO: printBreadthFirstTraversal
	@Override
	public void printBreadthFirstTraversal() {

		Queue<BST<T>> levelOrder = new LinkedList<>();
		if (_left.isEmpty() && _right.isEmpty()) {
			System.out.println(_element + " ");
			return;
		}
		System.out.print(_element + " ");

		if (!_left.isEmpty() || !_right.isEmpty()) {
			levelOrder.offer(_left);
			levelOrder.offer(_right);

			while (!levelOrder.isEmpty()) {
				BST<T> temp = levelOrder.poll();
				System.out.print(temp.getElement() + " ");
				if (!temp.getLeft().isEmpty()) {
					levelOrder.add(temp.getLeft());
				}

				if (!temp.getRight().isEmpty()) {
					levelOrder.add(temp.getRight());
				}
			}
		}

		//System.out.println();

		/**int h = getHeight();

		if (isEmpty()) {
			return;
		}

		System.out.print(_element + " ");

		for (int i=1; i<=h; i++) {
			breadthHelper(i);
		}**/
	}

	private void breadthHelper(BST<T> root, int i) {
		if (_right.isEmpty() && _left.isEmpty()) {
			return;
		}
		if (i == 1) {
			System.out.print(root.getElement() + " ");
		}
		else if (i > 1) {
			breadthHelper(root.getLeft(), i-1);
			breadthHelper(root.getRight(), i-1);
		}
	}

	@Override
	public int getHeight() {
		return Math.max(_left.getHeight(), _right.getHeight())+1;
	}


	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
