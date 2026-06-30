import java.util.Arrays;

public class MinMaxHeapTest {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        test("sorted ascending",        new Integer[]{1, 2, 3, 4, 5, 6, 7});
        test("sorted descending",       new Integer[]{7, 6, 5, 4, 3, 2, 1});
        test("single element",          new Integer[]{42});
        test("two elements",            new Integer[]{5, 3});
        test("all equal",               new Integer[]{4, 4, 4, 4, 4});
        test("random small",            new Integer[]{3, 1, 4, 1, 5, 9, 2, 6});
        test("negative numbers",        new Integer[]{-5, -1, -3, -2, -4});
        test("mixed pos/neg",           new Integer[]{-3, 7, -1, 5, 0, -8, 4});
        test("powers of two",           new Integer[]{8, 4, 16, 2, 32, 1, 64});
        test("larger random",           new Integer[]{15, 3, 27, 9, 1, 40, 6, 22, 13, 50, 8, 33});
        test("strings",                 new String[]{"banana", "apple", "cherry", "date", "elderberry"});

        System.out.println("\n" + passed + "/" + (passed + failed) + " tests passed.");
    }

    static <T extends Comparable<T>> void test(String name, T[] input) {
        MinMaxHeap<T> h = new MinMaxHeap<>(input);
        T[] heap = h.getHeap();
        String error = validate(heap);
        if (error == null) {
            System.out.println("[PASS] " + name);
            passed++;
        } else {
            System.out.println("[FAIL] " + name);
            System.out.println("       input: " + Arrays.toString(input));
            System.out.println("       heap:  " + Arrays.toString(heap));
            System.out.println("       error: " + error);
            failed++;
        }
    }

    static <T extends Comparable<T>> String validate(T[] heap) {
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

    static int[] descendants(int i, int n) {
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

    static int level(int i) {
        return (Integer.SIZE - Integer.numberOfLeadingZeros(i + 1)) - 1;
    }
}
