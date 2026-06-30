package com.github.fellipecaetano.minmaxheap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNull;

class MinMaxHeapTest {

    @Test void sortedAscending()  { assertValid(new Integer[]{1, 2, 3, 4, 5, 6, 7}); }
    @Test void sortedDescending() { assertValid(new Integer[]{7, 6, 5, 4, 3, 2, 1}); }
    @Test void singleElement()    { assertValid(new Integer[]{42}); }
    @Test void twoElements()      { assertValid(new Integer[]{5, 3}); }
    @Test void allEqual()         { assertValid(new Integer[]{4, 4, 4, 4, 4}); }
    @Test void randomSmall()      { assertValid(new Integer[]{3, 1, 4, 1, 5, 9, 2, 6}); }
    @Test void negativeNumbers()  { assertValid(new Integer[]{-5, -1, -3, -2, -4}); }
    @Test void mixedPosNeg()      { assertValid(new Integer[]{-3, 7, -1, 5, 0, -8, 4}); }
    @Test void powersOfTwo()      { assertValid(new Integer[]{8, 4, 16, 2, 32, 1, 64}); }
    @Test void largerRandom()     { assertValid(new Integer[]{15, 3, 27, 9, 1, 40, 6, 22, 13, 50, 8, 33}); }
    @Test void strings()          { assertValid(new String[]{"banana", "apple", "cherry", "date", "elderberry"}); }

    private <T extends Comparable<T>> void assertValid(T[] input) {
        assertNull(validate(new MinMaxHeap<>(input).getHeap()));
    }

    private <T extends Comparable<T>> String validate(T[] heap) {
        int n = heap.length;
        for (int i = 0; i < n; i++) {
            boolean minLevel = level(i) % 2 == 0;
            for (int d : descendants(i, n)) {
                if (minLevel && heap[i].compareTo(heap[d]) > 0) {
                    return "min-level node heap[" + i + "]=" + heap[i] + " > descendant heap[" + d + "]=" + heap[d];
                }
                if (!minLevel && heap[i].compareTo(heap[d]) < 0) {
                    return "max-level node heap[" + i + "]=" + heap[i] + " < descendant heap[" + d + "]=" + heap[d];
                }
            }
        }
        return null;
    }

    private int[] descendants(int i, int n) {
        int[] tmp = new int[n];
        int count = 0;
        int[] queue = new int[n];
        int head = 0, tail = 0;
        queue[tail++] = i;
        while (head < tail) {
            int cur = queue[head++];
            int l = cur * 2 + 1, r = cur * 2 + 2;
            if (l < n) { tmp[count++] = l; queue[tail++] = l; }
            if (r < n) { tmp[count++] = r; queue[tail++] = r; }
        }
        return Arrays.copyOf(tmp, count);
    }

    private int level(int i) {
        return (Integer.SIZE - Integer.numberOfLeadingZeros(i + 1)) - 1;
    }
}
