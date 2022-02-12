package hospital;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MinBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;
    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MinBinHeapER() {

        _heap = new ArrayList<>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO: overloaded constructor
    public MinBinHeapER(Prioritized<V, P>[] initialEntries )
    {
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
        return _heap.size();
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
        _heap.add(new Patient(value));
        if (_heap.size() > 1) {
            int current = _heap.size()-1;
            while (current > 0) {
                int parent = (current-1)/2;
                if ((Integer) _heap.get(current).getPriority() < (Integer) _heap.get(parent).getPriority()) {
                    swap(current, parent);
                    current = parent;
                }
                else {
                    break;
                }
            }
        }
    }

    // TODO: dequeue
    @Override
    public V dequeue() {
        if (_heap.isEmpty()) {
            return null;
        }
        else if (size()==1) {
            V minValue = _heap.remove(0).getValue();
            return minValue;
        }
        else {
            V minValue = getMin();
            _heap.remove(0);
            bubbleDown();
            return minValue;
        }
    }

    private boolean isLeaf(int pos) {
        return (pos >= (_heap.size()-1)/2 && pos <= _heap.size()-1);
    }

    private void bubbleDown() {
        Prioritized replace = _heap.remove(size()-1);
        _heap.add(0, replace);
        int current = 0;
        while (!isLeaf(current)) {
            int left = 2*current+1;
            int right = 2*current+2;
            if (left < _heap.size() && right < _heap.size()) {
                if ((Integer) _heap.get(left).getPriority() < (Integer) _heap.get(right).getPriority()) {
                    if ((Integer) _heap.get(current).getPriority() > (Integer) _heap.get(left).getPriority()) {
                        swap(current, left);
                        current = left;
                    }
                }
                else {
                    if ((Integer) _heap.get(current).getPriority() > (Integer) _heap.get(right).getPriority()) {
                        swap(current, right);
                        current = right;
                    }
                }
            }
            else if (left < _heap.size() || right < _heap.size()) {
                if ((Integer) _heap.get(current).getPriority() > (Integer) _heap.get(left).getPriority()) {
                    swap(current, left);
                }
            }
            else
                break;
        }
    }

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
