package com.github.fellipecaetano.minmaxheap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

class MinMaxHeapTest {
    @Test
    void sortedAscending() {
        assertValid(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    }

    @Test
    void sortedDescending() {
        assertValid(new Integer[]{7, 6, 5, 4, 3, 2, 1});
    }

    @Test
    void singleElement() {
        assertValid(new Integer[]{42});
    }

    @Test
    void twoElements() {
        assertValid(new Integer[]{5, 3});
    }

    @Test
    void allEqual() {
        assertValid(new Integer[]{4, 4, 4, 4, 4});
    }

    @Test
    void randomSmall() {
        assertValid(new Integer[]{3, 1, 4, 1, 5, 9, 2, 6});
    }

    @Test
    void negativeNumbers() {
        assertValid(new Integer[]{-5, -1, -3, -2, -4});
    }

    @Test
    void mixedPosNeg() {
        assertValid(new Integer[]{-3, 7, -1, 5, 0, -8, 4});
    }

    @Test
    void powersOfTwo() {
        assertValid(new Integer[]{8, 4, 16, 2, 32, 1, 64});
    }

    @Test
    void largerRandom() {
        assertValid(new Integer[]{15, 3, 27, 9, 1, 40, 6, 22, 13, 50, 8, 33});
    }

    @Test
    void strings() {
        assertValid(new String[]{"banana", "apple", "cherry", "date", "elderberry"});
    }

    @Test
    void extractMinThrowsWhenEmpty() {
        assertThrows(NoSuchElementException.class,
            () -> new MinMaxHeap<>(new Integer[]{}).extractMin());
    }

    @Test
    void extractMinSingleElement() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(new Integer[]{42});
        assertEquals(42, heap.extractMin());
        assertEquals(0, heap.size());
        assertThrows(NoSuchElementException.class, heap::extractMin);
    }

    @Test
    void extractMinDecrementsSize() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(new Integer[]{3, 1, 4, 1, 5});
        int sizeBefore = heap.size();
        heap.extractMin();
        assertEquals(sizeBefore - 1, heap.size());
    }

    @Test
    void extractMinReturnsMinimum() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(new Integer[]{3, 1, 4, 1, 5, 9, 2, 6});
        Integer expectedMin = heap.findMin();
        assertEquals(expectedMin, heap.extractMin());
    }

    @Test
    void extractMinLeavesHeapValid() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(new Integer[]{3, 1, 4, 1, 5, 9, 2, 6});
        heap.extractMin();
        assertTrue(heap.isValid());
    }

    @Test
    void repeatedExtractionsAreSorted() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(new Integer[]{5, 3, 8, 1, 9, 2, 7});
        int prev = heap.extractMin();
        while (heap.size() > 0) {
            int next = heap.extractMin();
            assertTrue(prev <= next);
            prev = next;
        }
    }

    @Test
    void extractMaxThrowsWhenEmpty() {
        assertThrows(NoSuchElementException.class,
            () -> new MinMaxHeap<>(new Integer[]{}).extractMax());
    }

    @Test
    void extractMaxSingleElement() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(new Integer[]{42});
        assertEquals(42, heap.extractMax());
        assertEquals(0, heap.size());
        assertThrows(NoSuchElementException.class, heap::extractMax);
    }

    @Test
    void extractMaxDecrementsSize() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(new Integer[]{3, 1, 4, 1, 5});
        int sizeBefore = heap.size();
        heap.extractMax();
        assertEquals(sizeBefore - 1, heap.size());
    }

    @Test
    void extractMaxReturnsMaximum() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(new Integer[]{3, 1, 4, 1, 5, 9, 2, 6});
        Integer expectedMax = heap.findMax();
        assertEquals(expectedMax, heap.extractMax());
    }

    @Test
    void extractMaxLeavesHeapValid() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(new Integer[]{3, 1, 4, 1, 5, 9, 2, 6});
        heap.extractMax();
        assertTrue(heap.isValid());
    }

    @Test
    void repeatedExtractMaxDescending() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(new Integer[]{5, 3, 8, 1, 9, 2, 7});
        int prev = heap.extractMax();
        while (heap.size() > 0) {
            int next = heap.extractMax();
            assertTrue(prev >= next);
            prev = next;
        }
    }

    private static <T extends Comparable<T>> void assertValid(T[] input) {
        assertTrue(new MinMaxHeap<>(input).isValid());
    }
}
