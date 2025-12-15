package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Algorithms {

    public static void main(String[] args) {
    }

    public static void bubbleSort(int[] array) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    int temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    isSorted = false;
                }
            }
        }
    }

    public static void fileSearch(File rootFile, List<File> fileList) {
        if (rootFile.isDirectory()) {
            File[] files = rootFile.listFiles();
            assert files != null;
            for (File file : files) {
                if (file.isDirectory()) {
                    fileSearch(file, fileList);
                } else {
                    if (file.getName().toLowerCase().endsWith(".java")) {
                        fileList.add(file);
                    }
                }
            }
        }
    }

    public static int min(int[] array) {
        int minIndex = 0;
        int minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minIndex = i;
                minValue = array[i];
            }
        }
        return minValue;
    }

    public static void sortBySelect(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minValue = array[i];
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < minValue) {
                    minValue = array[j];
                    minIndex = j;
                }
            }
            array[minIndex] = array[i];
            array[i] = minValue;
        }
    }


    public static void quickSort(int[] array, int from, int to) {
        if (from < to) {
            int index = partition(array, from, to);
            quickSort(array, from, index - 1);
            quickSort(array, index, to);
        }
    }

    private static int partition(int[] array, int from, int to) {
        int fromIndex = from;
        int toIndex = to;
        int point = array[from + (to - from)];
        while (toIndex >= fromIndex){
            while (array[fromIndex] < point) {
                fromIndex++;
            }
            while (array[toIndex] > point) {
                toIndex--;
            }
            if (fromIndex <= toIndex) {
                swap(array, toIndex, fromIndex);
                fromIndex++;
                toIndex--;
            }
        }
        return fromIndex;
    }

    private static void swap(int[] array, int toIndex, int fromIndex) {
        int temp = array[toIndex];
        array[toIndex] = array[fromIndex];
        array[fromIndex] = temp;
    }

    public static int researchXor(int[] nums) {
        int result = 0;
        for (int num : nums) {
            String d = Integer.toBinaryString(num);
            result ^= num;
        }
        return result;
    }

    public static void arraySort(int[][] array) {
        Arrays.sort(array, (a, b) -> Integer.compare(a[0], b[0]));
    }

    public static List<int[]> arrayMerge(int[][] array) {
        Arrays.sort(array, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new ArrayList<>();
        int[] start = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i][0] <= start[1]) {
                start[1] = array[i][1];
            } else {
                list.add(start);
                start = array[i];
            }
        }
        list.add(start);
        return list;
    }
}
