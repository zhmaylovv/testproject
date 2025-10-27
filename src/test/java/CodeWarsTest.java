import org.example.CodeWars;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.example.CodeWars.findMine;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CodeWarsTest {
    @Test
    public void histogram() {
        final String expected =
                "6|##### 5\n"+
                        "5|\n"+
                        "4|# 1\n"+
                        "3|########## 10\n"+
                        "2|### 3\n"+
                        "1|####### 7\n";
        assertEquals(expected, CodeWars.histogram(new int[]{7,3,10,1,0,5}));
    }

    @Test
    public void example() {
        final String expected =
                "6|██ 5%\n"+
                        "5|\n"+
                        "4|███████ 15%\n"+
                        "3|███████████████████████████████████ 70%\n"+
                        "2|█ 3%\n"+
                        "1|███ 7%\n";
        assertEquals(expected, CodeWars.histogramPercent(new int[]{7,3,70,15,0,5}));
    }

    @Test
    public void histogramPercent() {
        final String expected =
                "6|██ 5%\n"+
                        "5|\n"+
                        "4|███████ 15%\n"+
                        "3|███████████████████████████████████ 70%\n"+
                        "2|█ 3%\n"+
                        "1|███ 7%\n";
        assertEquals(expected, CodeWars.histogramPercent(new int[]{0,0,0,0,0,0}));
    }

    @Test()
    public void basicTestCases() {
        assertArrayEquals(new int[] {0,0}, findMine( new int[][] { {1, 0}, {0, 0} } ));
        assertArrayEquals(new int[] {0,0}, findMine( new int[][] { {1, 0, 0}, {0, 0, 0}, {0, 0, 0} } ));
        assertArrayEquals(new int[] {2,2}, findMine( new int[][] { {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 0} } ));

    }

    @Test()
    public void zadSlaviTest() {
        assertEquals(3, CodeWars.zadSlavi(new int[]{0, 0, 0, 1, 1, 0}, 2));
        assertEquals(6, CodeWars.zadSlavi(new int[]{0, 0, 0, 0, 1, 1, 0}, 2));
        assertEquals(4, CodeWars.zadSlavi(new int[]{0, 0, 0, 1, 1, 0, 0}, 2));
        assertEquals(9, CodeWars.zadSlavi(new int[]{0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0}, 2));
        assertEquals(28, CodeWars.zadSlavi(new int[]{0, 0, 0, 0, 0, 0, 0, 0}, 2));
        assertEquals(21, CodeWars.zadSlavi(new int[]{0, 0, 0, 0, 0, 0, 0, 0}, 3));
    }

    @Test
    public void lengthOfLongestSubstring(){
        assertEquals(2, CodeWars.lengthOfLongestSubstring("aab"));
        assertEquals(3, CodeWars.lengthOfLongestSubstring("dvdf"));
        assertEquals(1, CodeWars.lengthOfLongestSubstring("aa"));
        assertEquals(3, CodeWars.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, CodeWars.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, CodeWars.lengthOfLongestSubstring("pwwkew"));
    }
}
