package com.github.fellipecaetano.minmaxheap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

    private <T extends Comparable<T>> void assertValid(T[] input) {
        assertTrue(new MinMaxHeap<>(input).isValid());
    }
}
