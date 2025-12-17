package org.example;

import org.example.models.Hand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.example.CodeWars.hand;
import static org.junit.Assert.assertArrayEquals;

@RunWith(MockitoJUnitRunner.class)
public class CardsTest {
    @Test
    public void testStraightFlush() {
        Hand result = hand(new String[]{"8♠", "6♠"}, new String[]{"7♠", "5♠", "9♠", "J♠", "10♠"});
        assertArrayEquals(new String[]{"J", "10", "9", "8", "7"}, result.getArray());
    }

    @Test
    public void testStraightFlush2() {
        Hand result = hand(new String[]{"10♠", "Q♠"}, new String[]{"J♠", "K♠", "9♠", "K♥", "A♠"});
        assertArrayEquals(new String[]{"A", "K", "Q", "J", "9"}, result.getArray());
    }

    @Test
    public void testFour() {
        Hand result = hand(new String[]{"2♠", "3♦"}, new String[]{"2♣", "2♥", "3♠", "3♥", "2♦"});
        assertArrayEquals(new String[]{"2", "3"}, result.getArray());
    }

    @Test
    public void testFullHouse() {
        Hand result = hand(new String[]{"A♠", "A♦"}, new String[]{"K♣", "K♥", "A♥", "3♥", "3♣"});
        assertArrayEquals(new String[]{"A", "K"}, result.getArray());
    }

    @Test
    public void testFlush() {
        Hand result = hand(new String[]{"A♠", "K♦"}, new String[]{"J♥", "5♥", "10♥", "Q♥", "3♥"});
        assertArrayEquals(new String[]{"Q", "J", "10", "5", "3"}, result.getArray());
    }

    @Test
    public void testFlush2() {
        Hand result = hand(new String[]{"8♠", "6♠"}, new String[]{"7♦", "5♠", "9♠", "J♠", "10♠"});
        assertArrayEquals(new String[]{"J", "10", "9", "8", "6"}, result.getArray());
    }

    @Test
    public void testFlush3() {
        Hand result = hand(new String[]{"10♣", "5♥"}, new String[]{"7♣", "2♣", "9♦", "Q♣", "K♣"});
        assertArrayEquals(new String[]{"K", "Q", "10", "7", "2"}, result.getArray());
    }

    @Test
    public void testStraight() {
        Hand result = hand(new String[]{"Q♠", "2♦"}, new String[]{"J♣", "10♥", "9♥", "K♥", "3♦"});
        assertArrayEquals(new String[]{"K", "Q", "J", "10", "9"}, result.getArray());
    }

    @Test
    public void testStraight2() {
        Hand result = hand(new String[]{"8♠", "10♦"}, new String[]{"A♣", "K♥", "J♥", "9♦", "Q♦"});
        assertArrayEquals(new String[]{"A", "K", "Q", "J", "10"}, result.getArray());
    }

    @Test
    public void testStraight3() {
        Hand result = hand(new String[]{"A♥", "A♠"}, new String[]{"A♣", "K♥", "J♥", "Q♥", "10♦"});
        assertArrayEquals(new String[]{"A", "K", "Q", "J", "10"}, result.getArray());
    }

    @Test
    public void testStraight4() {
        Hand result = hand(new String[]{"9♣", "10♥"}, new String[]{"8♣", "J♠", "10♦", "A♠", "Q♦"});
        assertArrayEquals(new String[]{"Q", "J", "10", "9", "8"}, result.getArray());
    }

    @Test
    public void testThree() {
        Hand result = hand(new String[]{"4♠", "9♦"}, new String[]{"J♣", "Q♥", "Q♠", "2♥", "Q♦"});
        assertArrayEquals(new String[]{"Q", "J", "9"}, result.getArray());
    }


    @Test
    public void testTwoPair() {
        Hand result = hand(new String[]{"K♠", "J♦"}, new String[]{"J♣", "K♥", "9♥", "2♥", "3♦"});
        assertArrayEquals(new String[]{"K", "J", "9"}, result.getArray());
    }


    @Test
    public void testTwoPair2() {
        Hand result = hand(new String[]{"K♠", "J♦"}, new String[]{"J♣", "K♥", "9♥", "2♥", "2♦"});
        assertArrayEquals(new String[]{"K", "J", "9"}, result.getArray());
    }


    @Test
    public void testPair() {
        Hand result = hand(new String[]{"K♠", "Q♦"}, new String[]{"J♣", "Q♥", "9♥", "2♥", "3♦"});
        assertArrayEquals(new String[]{"Q", "K", "J", "9"}, result.getArray());
    }

    @Test
    public void testPair2() {
        Hand result = hand(new String[]{"10♥", "2♦"}, new String[]{"6♥", "9♦", "3♥", "3♠", "J♠"});
        assertArrayEquals(new String[]{"3", "J", "10", "9"}, result.getArray());
    }

    @Test
    public void testNothing() {
        Hand result = hand(new String[]{"K♠", "A♦"}, new String[]{"J♣", "Q♥", "9♥", "2♥", "3♦"});
        assertArrayEquals(new String[]{"A", "K", "Q", "J", "9"}, result.getArray());
    }
}
