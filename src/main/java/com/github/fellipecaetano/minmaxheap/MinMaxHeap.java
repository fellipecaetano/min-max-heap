package com.github.fellipecaetano.minmaxheap;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Set;

public class MinMaxHeap<T extends Comparable<T>> {
    private T[] heap;
    private static final int UNKNOWN = -1;

    public MinMaxHeap(T[] arr) {
        heap = Arrays.copyOf(arr, arr.length);

        int lastInternalNode = (int) Math.floor(heap.length / 2) - 1;
        for (int i = lastInternalNode; i >= 0; i--) {
            trickleDown(i);
        }
    }

    public int size() {
        return heap.length;
    }

    public T findMin() {
        if (heap.length == 0) throw new NoSuchElementException();
        return heap[0];
    }

    public T findMax() {
        if (heap.length == 0) throw new NoSuchElementException();
        if (heap.length == 1) return heap[0];
        if (heap.length == 2) return heap[1];
        return heap[1].compareTo(heap[2]) >= 0 ? heap[1] : heap[2];
    }

    boolean isValid() {
        for (int i = 0; i < heap.length; i++) {
            boolean minLevel = isMinLevel(i);
            for (int d : allDescendants(i)) {
                if (minLevel  && heap[i].compareTo(heap[d]) > 0) return false;
                if (!minLevel && heap[i].compareTo(heap[d]) < 0) return false;
            }
        }
        return true;
    }

    private int[] allDescendants(int i) {
        int[] tmp = new int[heap.length];
        int count = 0;
        int[] queue = new int[heap.length];
        int head = 0, tail = 0;
        queue[tail++] = i;
        while (head < tail) {
            int cur = queue[head++];
            int l = cur * 2 + 1, r = cur * 2 + 2;
            if (l < heap.length) { tmp[count++] = l; queue[tail++] = l; }
            if (r < heap.length) { tmp[count++] = r; queue[tail++] = r; }
        }
        return Arrays.copyOf(tmp, count);
    }

    private void trickleDown(int index) {
        if (isMinLevel(index)) {
            trickleDownMin(index);
        } else {
            trickleDownMax(index);
        }
    }

    private boolean isMinLevel(int index) {
        return level(index) % 2 == 0;
    }

    private void trickleDownMin(int index) {
        int smallestIndex = indexOfSmallest(childrenAndGrandchildren(index));
        if (smallestIndex == UNKNOWN) {
            return;
        }
        if (heap[smallestIndex].compareTo(heap[index]) < 0) {
            swap(heap, index, smallestIndex);

            if (isGrandchild(smallestIndex, index)) {
                int parentIndex = (int) Math.floor((smallestIndex - 1) / 2);
                if (heap[smallestIndex].compareTo(heap[parentIndex]) > 0) {
                    swap(heap, smallestIndex, parentIndex);
                }
                trickleDownMin(smallestIndex);
            }
        }
    }

    private void trickleDownMax(int index) {
        int largestIndex = indexOfLargest(childrenAndGrandchildren(index));
        if (largestIndex == UNKNOWN) {
            return;
        }
        if (heap[largestIndex].compareTo(heap[index]) > 0) {
            swap(heap, index, largestIndex);

            if (isGrandchild(largestIndex, index)) {
                int parentIndex = (int) Math.floor((largestIndex - 1) / 2);
                if (heap[largestIndex].compareTo(heap[parentIndex]) < 0) {
                    swap(heap, largestIndex, parentIndex);
                }
                trickleDownMax(largestIndex);
            }
        }
    }

    private int indexOfSmallest(Set<Integer> candidateIndexes) {
        int result = UNKNOWN;
        for (int index : candidateIndexes) {
            if (index < 0 || index >= heap.length) continue;
            if (result == UNKNOWN || heap[index].compareTo(heap[result]) < 0) {
                result = index;
            }
        }
        return result;
    }

    private int indexOfLargest(Set<Integer> candidateIndexes) {
        int result = UNKNOWN;
        for (int index : candidateIndexes) {
            if (index < 0 || index >= heap.length) continue;
            if (result == UNKNOWN || heap[index].compareTo(heap[result]) > 0) {
                result = index;
            }
        }
        return result;
    }

    private static int level(int index) {
        return (Integer.SIZE - Integer.numberOfLeadingZeros(index + 1)) - 1;
    }

    private static boolean isGrandchild(int index, int parentIndex) {
        return index != parentIndex * 2 + 1 && index != parentIndex * 2 + 2;
    }

    private static Set<Integer> childrenAndGrandchildren(int index) {
        return Set.of(
            index * 2 + 1,
            index * 2 + 2,
            index * 4 + 3,
            index * 4 + 4,
            index * 4 + 5,
            index * 4 + 6
        );
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
