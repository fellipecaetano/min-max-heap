import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter array (e.g. [1, 2, 3]): ");
        String line = scanner.nextLine().trim();
        scanner.close();

        line = line.replaceAll("[\\[\\]\\s]", "");
        String[] parts = line.split(",");

        Integer[] arr = new Integer[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }

        System.out.print("Array: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i < arr.length - 1 ? " " : "\n"));
        }

        MinMaxHeap<Integer> minMaxHeap = new MinMaxHeap<>(arr);
        System.out.print("Min-Max Heap: ");
        minMaxHeap.print();
    }
}
