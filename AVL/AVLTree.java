package a7;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Put your fields here.
    private T parent;
    private AVLTree<T> left;
    private AVLTree<T> right;

    public AVLTree() {
        // You code for constructor here.
        parent = null;
        left= null;
        right = null;
    }

    public AVLTree(T p, AVLTree l, AVLTree r) {
        parent = p;
        left = l;
        right = r;
    }

    public AVLTree(T element) {
        parent = element;
        left = new AVLTree<>();
        right = new AVLTree<>();
    }

    /**
     *
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
    
     private AVLTree<T> rotateLeft() {
         // You should implement left rotation and then use this 
         // method as needed when fixing imbalances.
         AVLTree tempRight = right;
         AVLTree tempLeft = left;
         left = new AVLTree<>(parent, tempLeft, tempRight.left);
         right = tempRight.right;
         parent = (T) tempRight.getValue();

         return this;
     }
    
    /**
     *
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */ 
     
     private AVLTree<T> rotateRight() {
         // You should implement right rotation and then use this 
         // method as needed when fixing imbalances.
         AVLTree tempRight = right;
         AVLTree tempLeft = left;
         right = new AVLTree<>(parent, tempLeft.right, tempRight);
         left = tempLeft.left;
         parent = (T) tempLeft.getValue();

         return this;
     }

    @Override
    public boolean isEmpty() {
         if (parent==null) {
             return true;
         }
        return false;
    }

    @Override
    public int height() {
        if (isEmpty()) {
            return 0;
        }
        else if (left.isEmpty() && right.isEmpty()) {
            return 1;
        }
        else if (left.isEmpty()) {
            return right.height()+1;
        }
        else if (right.isEmpty()) {
            return left.height()+1;
        }
        else
        {
            return Math.max(left.height(), right.height()) +1;
        }
    }

    @Override
    public int size() {
        if (isEmpty()) {
            return 0;
        }

        if (left.isEmpty() && right.isEmpty()) {
            return 1;
        }

        return left.size() + 1 + right.size();
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
         if (isEmpty()) {
             return new AVLTree<>(element);
         }
         else {
             if (left.isEmpty() && right.isEmpty()) {
                 if (element.compareTo(parent)<0) {
                     left = new AVLTree<>(element);
                 }
                 else {
                     right = new AVLTree<>(element);
                 }
             }
             else {
                 if (left.isEmpty()) {
                     if (element.compareTo(parent)<0) {
                         left = new AVLTree<>(element);
                     }
                     else {
                         right = (AVLTree) right.insert(element);
                     }
                 }
                 else  if (right.isEmpty()) {
                     if (element.compareTo(parent)<0) {
                         left = (AVLTree) left.insert(element);
                     }
                     else {
                         right = new AVLTree<>(element);
                     }
                 }
                 else {
                     if (element.compareTo(parent) < 0) {
                         left = (AVLTree) left.insert(element);
                     }
                     else {
                         right = (AVLTree) right.insert(element);
                     }
                 }
             }

             fixTree(element);
             return this;
         }
     }

    private void fixTree(T element) {
         int balance = balanceFactor();
         if (!left.isEmpty() && balance > 1) {
             if (element.compareTo(left.getValue()) > 0) {
                 left = left.rotateLeft();
             }
             rotateRight();
             return;
         }

         if (!right.isEmpty() && balance < -1) {
             if (element.compareTo(right.getValue()) < 0) {
                 right = right.rotateRight();
             }
             rotateLeft();
             return;
         }
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
         if (!isEmpty()) {
             if (element.compareTo(parent) > 0) {
                 right = (AVLTree) right.remove(element);
             }
             else if (element.compareTo(parent) < 0) {
                 left = (AVLTree) left.remove(element);
             }
             else {
                 if (right.isEmpty()) {
                     return left;
                 }
                 else if (left.isEmpty()) {
                     return right;
                 }
                 else {
                     T minToReplaceWith = right.findMin();
                     parent = minToReplaceWith;
                     right = (AVLTree) right.remove(minToReplaceWith);
                 }
             }
             fixTreeDeletion();
         }

         return this;
    }

    private void fixTreeDeletion() {
         int balance = balanceFactor();

         if (!right.isEmpty() && balance < -1) {
             if (right.balanceFactor() > 0) {
                 right = right.rotateRight();
             }
             rotateLeft();
         }

         if (!left.isEmpty() && balance > 1) {
             if (left.balanceFactor() < 0) {
                 left = left.rotateLeft();
             }
             rotateRight();
         }
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            return null;
        }

        if (left.isEmpty()) {
            return parent;
        }

        return left.findMin();
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            return null;
        }

        if (right.isEmpty()) {
            if (!left.isEmpty()) {
                return left.getValue();
            }

            return parent;
        }

        return right.findMax();
    }

    @Override
    public boolean contains(T element) {
        if (isEmpty()) {
            return false;
        }
        else if (parent.equals(element)) {
            return true;
        }
        else {
            if (element.compareTo(parent) < 0) {
                return left.contains(element);
            }
            else {
                return right.contains(element);
            }
        }
     }

    @Override
    public T getValue() {
        return parent;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        return left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        return right;
    }

    // Your code for public methods here.
    public int balanceFactor() {
         if (left.isEmpty()) {
             return -right.height();
         }
         else if (right.isEmpty()) {
             return left.height();
         }
         else {
             return left.height()-right.height();
         }
    }
}
