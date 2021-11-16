public class LinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;


    /**
     * Remove the node at index i of the list.
     * Note that the first element is at index 0
     * If i is larger than the size of the list, throw an IndexOutOfBounds Exception
     *
     * ex: list: A -> B -> C -> D
     *     i: 1
     *     list after removeAtIndex: A -> C -> D
     *
     * @param i    - index of node to remove
     */
    public void removeAtIndex(int i)
    {
        Node<T> current = head;
        int counter = 0;
        if (i > size())
            throw new IndexOutOfBoundsException();
        else {
            boolean inCounter = false;
            while (current.hasNext()) {
                if (counter == i) {
                    remove(current.getValue());
                    inCounter = true;
                    break;
                } else {
                    current = current.getNext();
                    counter++;
                }
            }

            if (!inCounter) {
                remove(current.getValue());
            }
        }


    }

    /**
     * Return true if this linked list is equal to the list argument, false otherwise.
     * Two lists are equal if they have the same size, and the same
     * elements in the same order.
     * ex:  list: 1 -> 4 -> 2
     *      list2: 1 -> 4 -> 2
     *      return: true
     *
     *      list: 1 -> 5
     *      list2: 2 -> 5
     *      return false;
     *
     * @param list2 - the list to merge into the current list
     * @return true if the lists have the same elements in the same order, false otherwise
     */
    public boolean isEqual(LinkedList<T> list2)
    {
        Node<T> current = head;
        Node<T> current2 = list2.getHead();

        if (size != list2.size())
            return false;
        else if (size == 0 && list2.size() == 0) {
            return true;
        }
        else if (size == 0 && list2.size() != 0) {
            return false;
        }
        else {
            if (!current.getValue().equals(current2.getValue()))
                return false;
            do {
                current = current.getNext();
                current2 = current2.getNext();
                if (!current.getValue().equals(current2.getValue()))
                    return false;
            }
            while (current.hasNext());
        }
        return true;
    }


    /**
     * Return true if the list is symmetrical, false otherwise
     * ex: list: 1 -> 2 -> 3 -> 2 -> 1
     *     return: true
     *
     *     list: a -> b -> c -> b -> f
     *     return: false
     *
     * @return true if the list is symmetrical, false otherwise
     */

    public boolean isSymmetrical() {

        T [] array = toArray();

        for (int i=0; i< array.length/2; i++) {
            if (!(array[i]==array[array.length-1-i]))
                return false;
        }
        return true;
    }


    /**
     * Stretch the list so that each element in the list is represented factor times
     * If the factor is 0 the list should be cleared (have 0 nodes)
     * ex: list: 1 -> 2 ->3
     *     factor: 3
     *     list after multiply: 1 -> 1 -> 1 -> 2 -> 2 -> 2 -> 3 -> 3 -> 3
     *
     * @param factor the amount to multiply the number of occurrences of each element by
     */
    public void multiply(int factor) {
        T [] array = toArray();
        clear();
        if (factor != 0) {
            Object [] multiplied = new Object [array.length*factor];
            int i = 0;
            int j = 0;
            while (i < multiplied.length) {
                while (j < array.length) {
                    int k = 0;
                    while (k < factor) {
                        multiplied[i] = array[j];
                        i++;
                        k++;
                    }
                    j++;
                }
            }

            for (int n=0; n<multiplied.length; n++) {
                add((T) multiplied[n]);
            }
        }
    }

    /**
     * Given a sorted linked list, remove the duplicate values from the list
     * ex: list: 5 -> 6 -> 7 -> 7 -> 7 -> 8 -> 8 -> 9
     *     list after removeRepeats: 5 -> 6 -> 7 -> 8 -> 9
     *
     */
    public void removeRepeats() {
        Node<T> current = head;
        if (head != null) {
            while (current.hasNext()) {
                if (current.getValue().equals(current.getNext().getValue()))
                    remove(current.getValue());
                current = current.getNext();
            }
        }
    }


    /**
     * Reverse the list
     *
     * ex list:  10 -> 9 -> 8 -> 7
     *    list after reverse: 7 -> 8 -> 9 -> 10
     *
     */
    public void reverse() {
        T [] array = toArray();
        Object [] finalArray = new Object [size];
        int finalCounter = 0;

        for (int i=array.length-1; i>=0; i--) {
            finalArray[finalCounter] = array[i];
            finalCounter++;
        }

        clear();
        for (int i=0; i<finalArray.length; i++) {
            add((T)finalArray[i]);
        }

    }

    /**
     * Return true if the list contains a cycle, false otherwise
     * ex: list: 1 -> 2 -> 3 - > 4 --       (4 points to 2)
     *                ^              |
     *                |              |
     *                ---------------
     *      return: true
     *
     *      list: 1 -> 2 -> 3 -> 4
     *      return: false
     *
     * @return true if the list contains a cycle, false otherwise
     */
    public boolean containsCycle() {
        Node<T> current = head;
        int counter = 0;

        while (counter < size-1) {
            current = current.getNext();
            counter++;
        }

        return current.hasNext();
    }

    /**
     * Merge the given linked list into the current list. The 2 lists will always be
     * either the same size, or the current list will be longer than list2.
     * The examples below show how to handle each case.
     *
     * Note: Do NOT create and return a new list, merge the second list into the first one.
     *
     * ex: list: 1 -> 2 -> 3
     *     list2: 4 -> 5 -> 6
     *     return: 1 -> 4 -> 2 -> 5 -> 3 -> 6
     *
     *     list: 1 -> 2 -> 3 -> 4
     *     list2: 5 -> 6
     *     return 1 -> 5 -> 2 -> 6 -> 3 -> 4
     *
     * @param list2
     */
    public void merge(LinkedList list2) {
        T [] list1array = toArray();
        Object [] list2Array = list2.toArray();
        Object[] merge = new Object[list1array.length + list2Array.length];

        if (size == 0) {
            head = list2.getHead();
        }
        else {
            int index = 0;
            for (int i = 0; i < merge.length; i++) {
                if (index >= list1array.length) {
                    merge[i] = list2Array[index];
                    index++;
                }
                else if (index >= list2Array.length) {
                    merge[i] = (Object) list1array[index];
                    index++;
                }
                else {
                    merge[i] = (Object) list1array[index];
                    i++;
                    merge[i] = list2Array[index];
                    index++;
                }
            }
        }

        clear();
        for (int i=0; i<merge.length; i++) {
            add((T)merge[i]);
        }
    }


    /* Implementation given to you. Do not modify below this. */
    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(Object element) {
        Node<T> current = head;
        while(current != null) {
            if(current.getValue().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public T[] toArray() {
        T[] arr =  (T[]) new Object[size()];
        Node<T> current = head;
        int i = 0;
        if(isEmpty()) {
            return arr;
        }
        while(current != null){
            arr[i] = current.getValue();
            current = current.getNext();
            i++;
        }
        return arr;
    }

    public void add(Object element) {
        Node<T> newNode = new NodeImpl<T>((T) element, null);
        if(isEmpty()) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }

    }

    public boolean remove(Object element) {
        Node<T> current = head;
        if(isEmpty()) {
            return false;
        }
        if(current.getValue() == element){
            head = head.getNext();
            size--;
            return true;
        }
        while(current.getNext().getValue() != element) {
            current = current.getNext();
            if(current == null) {
                return false;
            }
        }
        if(current.getNext().getNext() == null) {
            tail = current;
        }
        current.setNext(current.getNext().getNext());
        size--;
        return true;
    }

    public T get(int index) {
        validIndex(index);
        Node<T> current = head;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        return current.getValue();
    }

    public T set(int index, Object element) {
        validIndex(index);
        Node<T> current = head;
        T prevValue = null;
        int i = 0;
        if(index == 0) {
            prevValue = head.getValue();
            head.setValue((T) element);
        } else {
            while(current != null) {
                if(i == index) {
                    prevValue = current.getValue();
                    current.setValue((T) element);
                    return prevValue;
                }
                current = current.getNext();
                i++;
            }
        }

        return prevValue;
    }

    public void add(int index, Object element) {
        if(index > size) {
            validIndex(index);
        }
        Node<T> current = head;
        int i = 0;
        if(index == 0) {
            if(isEmpty()) {
                add(element);
                return;
            } else {
                Node<T> newNode = new NodeImpl<T>((T) element, head);
                head = newNode;
                size++;
                return;
            }

        }  else if(index == size) {
            add(element);
            return;
        }
        while(current != null) {
            if(i == (index - 1)) {
                Node<T> temp = current.getNext();
                Node<T> newNode = new NodeImpl<T>((T) element, temp);
                current.setNext(newNode);
                size++;
                return;
            } else {
                current = current.getNext();
                i++;
            }
        }
    }

    public int indexOf(Object element) {
        Node<T> current = head;
        int index = 0;
        while(current != null) {
            if(current.getValue().equals((T) element)) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }

    public int lastIndexOf(Object element) {
        Node<T> current = head;
        int index = -1;
        int i = 0;
        while(current != null) {
            if(current.getValue().equals ((T) element)) {
                index = i;
            }
            i++;
            current = current.getNext();
        }
        return index;
    }

    public void validIndex(int i) {
        if(i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    public Node<T> getHead() {
        return head;
    }

    @Override
    public String toString() {
        String list = "";
        Node<T> current = head;
        while(current != null) {
            if(current.getNext() == null)
                list+= current.getValue();
            else
                list += current.getValue() + " -> ";
            current = current.getNext();
        }
        return list;
    }

}
