import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SortData {
    private static final String FILE_NAME = "data.txt";

    public static void main(String[] args) {
        int[] data = readDataFromFile(FILE_NAME);

        // Сортировка пузырьком
        int[] bubbleSortData = Arrays.copyOf(data, data.length);
        long bubbleSortComparisons = bubbleSort(bubbleSortData);
        System.out.println("Bubble Sort");
        System.out.println("Theoretical complexity: O(n^2)");
        System.out.println("Comparisons: " + bubbleSortComparisons);
        System.out.println("Swaps: " + (bubbleSortComparisons - bubbleSortData.length));
        System.out.println("Execution time: " + measureExecutionTime(() -> bubbleSort(bubbleSortData)));

        // Сортировка выбором
        int[] selectionSortData = Arrays.copyOf(data, data.length);
        long selectionSortComparisons = selectionSort(selectionSortData);
        System.out.println("Selection Sort");
        System.out.println("Theoretical complexity: O(n^2)");
        System.out.println("Comparisons: " + selectionSortComparisons);
        System.out.println("Swaps: " + (selectionSortComparisons - selectionSortData.length));
        System.out.println("Execution time: " + measureExecutionTime(() -> selectionSort(selectionSortData)));

        // Быстрая сортировка
        int[] quickSortData = Arrays.copyOf(data, data.length);
        long[] quickSortMetrics = quickSort(quickSortData, 0, quickSortData.length - 1);
        System.out.println("Quick Sort");
        System.out.println("Theoretical complexity: O(n*log(n))");
        System.out.println("Comparisons: " + quickSortMetrics[0]);
        System.out.println("Swaps: " + quickSortMetrics[1]);
        System.out.println("Execution time: " + quickSortMetrics[2] + " milliseconds");
    }

    private static int[] readDataFromFile(String fileName) {
        int[] data = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String[] strData = reader.readLine().split(",");
            data = new int[strData.length];
            for (int i = 0; i < strData.length; i++) {
                data[i] = Integer.parseInt(strData[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static long bubbleSort(int[] data) {
        long comparisons = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                comparisons++;
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
        return comparisons;
    }

    private static long selectionSort(int[] data) {
        long comparisons = 0;
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                comparisons++;
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
        }
        return comparisons;
    }

    private static long[] quickSort(int[] data, int left, int right) {
        long comparisons = 0;
        long swaps = 0;
        if (left < right) {
            int pivotIndex = left + (right - left) / 2;
            int pivotValue = data[pivotIndex];
            int i = left;
            int j = right;
            while (i <= j) {
                while (data[i] < pivotValue) {
                    comparisons++;
                    i++;
                }
                while (data[j] > pivotValue) {
                    comparisons++;
                    j--;
                }
                if (i <= j) {
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                    swaps++;
                    i++;
                    j--;
                }
            }
            long[] leftMetrics = quickSort(data, left, j);
            long[] rightMetrics = quickSort(data, i, right);
            comparisons += leftMetrics[0] + rightMetrics[0];
            swaps += leftMetrics[1] + rightMetrics[1];
        }
        return new long[] {comparisons, swaps, measureExecutionTime(() -> {})};
    }

    private static long measureExecutionTime(Runnable code) {
        long startTime = System.currentTimeMillis();
        code.run();
        return System.currentTimeMillis() - startTime;
    }
}
