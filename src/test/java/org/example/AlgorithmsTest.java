package org.example;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AlgorithmsTest {

    @Test
    public void testBubbleSort() {
        int[] array = new int[]{1, 2, 5, 4, 3, 9, 6, 8, 7};
        Algorithms.bubbleSort(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, array);
    }

    @Test
    public void testFileSearch() {
        ArrayList<File> fileList = new ArrayList<>();
        Algorithms.fileSearch(new File("src"), fileList);
        assertEquals("Algorithms.java", fileList.get(0).getName());
    }

    @Test
    public void shouldGetMinValueArray() {
        assertEquals(1, Algorithms.min(new int[]{1, 2, 5, 4, 3, 9, 6, 8, 7}));
    }

    @Test
    public void shouldGetSortedArrayBySelect() {
        int[] array = new int[]{1, 2, 5, 4, 3, 9, 6, 8, 7};
        Algorithms.sortBySelect(array);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, array);
    }

    @Test
    public void shouldGetSortedArrayByQuick() {
        int[] array = new int[]{1, 2, 5, 4, 3, 9, 6, 8, 7};
        Algorithms.quickSort(array, 0, array.length - 1);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, array);
    }

    @Test
    public void shouldGetSortedArrayArrayBySelect() {
        int[][] array = new int[][]{{2, 6}, {1, 3}, {8, 10}, {15, 18}};
        Algorithms.arraySort(array);
        assertArrayEquals(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}, array);
    }

    @Test
    public void shouldGetMergedArrayArrayBySelect() {
        int[][] array = new int[][]{{2, 6}, {1, 3}, {8, 10}, {15, 18}};
        List<int[]> ints = Algorithms.arrayMerge(array);
        assertArrayEquals(new int[]{1, 6}, ints.get(0));
        assertArrayEquals(new int[]{8, 10}, ints.get(1));
        assertArrayEquals(new int[]{15, 18}, ints.get(2));
    }

    @Test
    public void shouldReturnUnicValue() {
        assertEquals(1, Algorithms.researchXor(new int[]{2, 2, 1}));
        assertEquals(4, Algorithms.researchXor(new int[]{4, 1, 2, 1, 2}));
        assertEquals(1, Algorithms.researchXor(new int[]{1}));

    }
}