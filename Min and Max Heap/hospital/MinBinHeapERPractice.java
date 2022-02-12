package hospital;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MinBinHeapERPractice  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;
    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MinBinHeapERPractice() {

        _heap = new ArrayList<>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO: overloaded constructor
    public MinBinHeapERPractice(Prioritized<V, P>[] initialEntries )
    {
        /**for (Prioritized<V,P> patient: initialEntries) {
         enqueue(patient.getValue(), patient.getPriority());
         }**/
        _heap = new ArrayList<>();
        for (int i=0; i< initialEntries.length; i++) {
            enqueue(initialEntries[i].getValue(), initialEntries[i].getPriority());
        }
    }

    @Override
    public int size() {
        if (_heap.isEmpty()) {
            return 0;
        }
        return _heap.size()-1;
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {

        _heap.add(new Patient(value, priority));
        int current = _heap.size() - 1;
        int parent = (current-1)/2;
        while ((Integer) _heap.get(current).getPriority() < (Integer) _heap.get(parent).getPriority()) {
            swap(current, parent);
            current = parent;
            if (current != 0)
                parent = (current-1)/2;
        }
    }

    private void swap(int x, int y) {
        Prioritized<V,P> temp = _heap.get(x);
        _heap.set(x, new Patient(_heap.get(y).getValue(),_heap.get(y).getPriority()));
        _heap.set(y, new Patient(temp.getValue(), temp.getPriority()));
    }

    // TODO: enqueue
    public void enqueue(V value) {
        if (_heap.size()==0)
            _heap.add(new Patient(value));
        else {
            _heap.add(new Patient(value));
            int current = _heap.size() - 1;
            //int current = size();
            int parent = (current - 1) / 2;
            while ((Integer) _heap.get(current).getPriority() < (Integer) _heap.get(parent).getPriority()) {
                swap(current, parent);
                current = parent;
                parent = (current - 1) / 2;
            }
        }
        //fixHeap();
    }

    // TODO: dequeue
    @Override
    public V dequeue() {
        if (_heap.isEmpty()) {
            return null;
        }

        if (_heap.size() == 1) {
            V minValue = getMin();
            _heap.remove(0);
            return minValue;
        }

        else {
            V minValue = getMin();
            _heap.remove(0);
            fixHeap(0);
            return minValue;
        }
    }

    private void fixHeap(int c) {
        if (!(c < 0)) {
            int parent = c;
            int leftChild = 2*parent+1;
            int rightChild = 2*parent+2;
            if (leftChild < _heap.size() && rightChild < _heap.size()) {
                if ((Integer) _heap.get(leftChild).getPriority() < (Integer) _heap.get(parent).getPriority()) {
                    swap(parent, leftChild);
                    fixHeap(leftChild);
                } else if ((Integer) _heap.get(rightChild).getPriority() < (Integer) _heap.get(parent).getPriority()) {
                    swap(parent, rightChild);
                    fixHeap(rightChild);
                }
                else {
                    if (parent < _heap.size()-1)
                        fixHeap(parent+1);
                }
            }
        }
    }

    /**private void fixHeap(int whichChild) {
     int parent = whichChild;
     int leftChild = 2*parent + 1;
     int rightChild = 2*parent + 2;
     if (leftChild < _heap.size() && rightChild < _heap.size()) {
     if ((Integer) _heap.get(leftChild).getPriority() < (Integer) _heap.get(parent).getPriority() || (Integer) _heap.get(rightChild).getPriority() < (Integer) _heap.get(parent).getPriority()) {
     if ((Integer) _heap.get(leftChild).getPriority() < (Integer) _heap.get(parent).getPriority()) {
     swap(parent, leftChild);
     fixHeap(leftChild);
     } else {
     swap(parent, rightChild);
     fixHeap(rightChild);
     }
     }
     }
     }**/

    // TODO: getMin
    @Override
    public V getMin() {
        if (size()==0) {
            return null;
        }
        return _heap.get(0).getValue();
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }






}
