import org.example.CodeWars;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static org.example.CodeWars.findMine;
import static org.junit.Assert.*;

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

    @Test
    public void staticTests() {
        assertTrue(CodeWars.substring("samurai", "ai"));
        assertFalse(CodeWars.substring("sumo", "omo"));
        assertTrue(CodeWars.substring("ninja", "ja"));
        assertTrue(CodeWars.substring("sensei", "i"));
        assertFalse(CodeWars.substring("samurai", "ra"));
        assertFalse(CodeWars.substring("abc", "abcd"));
        assertTrue(CodeWars.substring("abc", "abc"));
        assertTrue(CodeWars.substring("abcabc", "bc"));
        assertFalse(CodeWars.substring("ails", "fails"));
        assertTrue(CodeWars.substring("fails", "ails"));
        assertFalse(CodeWars.substring("this", "fails"));
        assertTrue(CodeWars.substring("this", ""));
        assertFalse(CodeWars.substring(":-)", ":-("));
        assertTrue(CodeWars.substring("!@#$%^&*() :-)", ":-)"));
        assertFalse(CodeWars.substring("abc\n", "abc"));
    }

    @Test
    public void basicTests(){
        assertEquals(0,CodeWars.simplePairOfSum(0));
        assertEquals(1,CodeWars.simplePairOfSum(1));
        assertEquals(18,CodeWars.simplePairOfSum(18));
        assertEquals(11,CodeWars.simplePairOfSum(29));
        assertEquals(33,CodeWars.simplePairOfSum(1140));
        assertEquals(68,CodeWars.simplePairOfSum(50000000));
    }

    @Test
    public void Tests() {
        assertEquals("makeReadable(0)", "00:00:00", CodeWars.makeReadable(0));
        assertEquals("makeReadable(5)", "00:00:05", CodeWars.makeReadable(5));
        assertEquals("makeReadable(60)", "00:01:00", CodeWars.makeReadable(60));
        assertEquals("makeReadable(86399)", "23:59:59", CodeWars.makeReadable(86399));
        assertEquals("makeReadable(359999)", "99:59:59", CodeWars.makeReadable(359999));
    }

    @Test
    public void testEvilWin() {
        assertEquals("Evil should win", "Battle Result: Evil eradicates all trace of Good",
                CodeWars.battle("1 1 1 1 1 1", "1 1 1 1 1 1 1"));
        assertEquals("Good should win", "Battle Result: Good triumphs over Evil",
                CodeWars.battle("0 0 0 0 0 10", "0 1 1 1 1 0 0"));
        assertEquals("Should be a tie", "Battle Result: No victor on this battle field",
                CodeWars.battle("1 0 0 0 0 0", "1 0 0 0 0 0 0"));
    }

    @Test
    public void sampleTests() {
        //tester(input, expected)
        tester("I Love You", 170);
        tester("ILoveYou", 170);
        tester("ARE YOU HUNGRY?", 356);
        tester("oops, i did it again!", 152);
        tester("Give me 5!", 73);
        tester("Give me five!", 110);
    }
    void tester(String input, int expected){
        String message = String.format("Failed for input: %s",input);
        assertEquals(message, expected, CodeWars.lettersToNumbers(input));
    }




    @Test
    public void testPins() {
        for (String entered : expectations.keySet()) {
            test(entered, Arrays.asList(expectations.get(entered)), CodeWars.getPINs(entered));
        }
    }
    public static HashMap<String, String[]> expectations = new HashMap<String, String[]>() {
        {
            put("8", new String[]{"5", "7", "8", "9", "0"});
            put("11", new String[]{"11", "21", "41", "12", "22", "42", "14", "24", "44"});
            put("369", new String[]{"236", "238", "239", "256", "258", "259", "266", "268", "269", "296", "298", "299", "336", "338", "339", "356", "358", "359", "366", "368", "369", "396", "398", "399", "636", "638", "639", "656", "658", "659", "666", "668", "669", "696", "698", "699"});
        }
    };
    private final static Comparator<String> comp = (s1, s2) -> s1.compareTo(s2);
    private void test(String entered, List<String> expected, List<String> result) {
        result.sort(comp);
        expected.sort(comp);
        assertEquals("For observed PIN " + entered, expected, result);
    }

}
