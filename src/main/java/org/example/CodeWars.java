package org.example;


import org.example.models.Hand;
import org.example.models.Node;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CodeWars {

    /**
     * Не буду рефакторить- что бы потом, когда-нибудь, было стыдно.
     * https://www.codewars.com/kata/524c74f855025e2495000262/train/java
     * 3 kyu
     * Texas Hold'em Hands
     *
     * @param holeCards
     * @param communityCards
     * @return
     */

    private static final Map<String, Integer> CARDS_VALUE = new LinkedHashMap<>() {{
        put("A", 14);
        put("K", 13);
        put("Q", 12);
        put("J", 11);
        put("10", 10);
        put("9", 9);
        put("8", 8);
        put("7", 7);
        put("6", 6);
        put("5", 5);
        put("4", 4);
        put("3", 3);
        put("2", 2);
    }};

    public static Hand hand(String[] holeCards, String[] communityCards) {
        Map<String, List<String>> cardMaps = getCardsMap(holeCards, communityCards);

        if (isItStraightFlush(holeCards, communityCards)) {
            return getStraightFlushHand(holeCards, communityCards);
        } else if (isItFour(cardMaps)) {
            return getFourHand(cardMaps);
        } else if (isItFullHouse(cardMaps)) {
            return getFullHouseHand(cardMaps);
        } else if (isItFlush(holeCards, communityCards)) {
            return getFlushHand(holeCards, communityCards);
        } else if (isItStraight(holeCards, communityCards)) {
            return getStraightHand(holeCards, communityCards);
        } else if (isItThree(cardMaps)) {
            return getThreeHand(cardMaps);
        } else if (isTwoPair(cardMaps)) {
            return getTwoPairHand(cardMaps);
        } else if (isPair(cardMaps)) {
            return getPairHand(cardMaps);
        } else return getNothing(holeCards, communityCards);
    }

    private static Hand getNothing(String[] holeCards, String[] communityCards) {
        String[] allCards = getAllCards(holeCards, communityCards);
        List<String> cardList = Arrays.stream(allCards)
                .map(s -> s.substring(0, s.length() - 1))
                .collect(Collectors.toList());
        String[] cardsByRank = getCardsByRank(cardList);
        Set<String> unic = new LinkedHashSet<>(List.of(cardsByRank));
        return new Hand("nothing", Arrays.copyOfRange(unic.toArray(new String[0]), 0, 5));
    }

    private static Hand getPairHand(Map<String, List<String>> cardMaps) {
        List<String> pairs = cardMaps.entrySet().stream()
                .filter(e -> e.getValue().size() == 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        List<String> exceptList = new ArrayList<>();
        exceptList.add(pairs.get(0));
        String first = getHighestExcept(cardMaps, exceptList);
        exceptList.add(first);
        String second = getHighestExcept(cardMaps, exceptList);
        exceptList.add(second);
        String third = getHighestExcept(cardMaps, exceptList);
        return new Hand("pair", new String[]{pairs.get(0), first, second, third});
    }

    private static boolean isPair(Map<String, List<String>> cardMaps) {
        return cardMaps.values().stream().anyMatch(l -> l.size() == 2);
    }

    private static Hand getTwoPairHand(Map<String, List<String>> cardMaps) {
        List<String> pairs = cardMaps.entrySet().stream()
                .filter(e -> e.getValue().size() == 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        pairs.sort(Comparator.comparingInt(CARDS_VALUE::get).reversed());
        String kicker = getHighestExcept(cardMaps, List.of(new String[]{pairs.get(0), pairs.get(1)}));
        return new Hand("two pair", new String[]{pairs.get(0), pairs.get(1), kicker});
    }

    private static boolean isTwoPair(Map<String, List<String>> cardMaps) {
        return cardMaps.values().stream().filter(l -> l.size() == 2).count() >= 2
                && cardMaps.values().stream().noneMatch(l -> l.size() == 3);
    }

    private static Hand getThreeHand(Map<String, List<String>> cardMaps) {
        List<String> thirds = cardMaps.entrySet().stream()
                .filter(e -> e.getValue().size() == 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        String second = getHighestExcept(cardMaps, List.of(new String[]{thirds.get(0)}));
        String third = getHighestExcept(cardMaps, List.of(new String[]{thirds.get(0), second}));
        return new Hand("three-of-a-kind", new String[]{thirds.get(0), second, third});
    }

    private static boolean isItThree(Map<String, List<String>> cardMaps) {
        return cardMaps.values().stream().anyMatch(l -> l.size() == 3);
    }

    private static Hand getStraightHand(String[] holeCards, String[] communityCards) {
        List<String> cards = Stream.of(getAllCards(holeCards, communityCards))
                .map(s -> s.substring(0, s.length() - 1))
                .collect(Collectors.toList());
        String[] cardsRow = getCardsRow(getCardsByRank(cards));
        return new Hand("straight", cardsRow);
    }

    private static boolean isItStraight(String[] holeCards, String[] communityCards) {
        List<String> cards = Stream.of(getAllCards(holeCards, communityCards))
                .map(s -> s.substring(0, s.length() - 1))
                .collect(Collectors.toList());
        return isFiveCardsOnRaw(getCardsByRank(cards));
    }

    private static Hand getFlushHand(String[] holeCards, String[] communityCards) {
        Map<String, List<String>> types = getFlushMap(holeCards, communityCards);
        String[] cards = getCardsByRank(types.values().stream().filter(l -> l.size() >= 5).findFirst().orElseThrow());
        return new Hand("flush", Arrays.copyOfRange(cards, 0, 5));
    }

    private static Hand getFullHouseHand(Map<String, List<String>> cardMaps) {
        List<String> thirds = cardMaps.entrySet().stream()
                .filter(e -> e.getValue().size() == 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        String first = null;
        String second = null;
        for (String s : CARDS_VALUE.keySet()) {
            if (thirds.contains(s) && first == null) {
                first = s;
            } else if (thirds.contains(s) && first != null) {
                second = s;
            }
        }

        if (second == null) {
            List<String> twos = cardMaps.entrySet().stream()
                    .filter(e -> e.getValue().size() == 2)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            for (String s : CARDS_VALUE.keySet()) {
                if (twos.contains(s)) {
                    second = s;
                    break;
                }
            }
        }

        return new Hand("full house", new String[]{first, second});
    }

    private static boolean isItFullHouse(Map<String, List<String>> cardMaps) {
        return (cardMaps.values().stream().filter(l -> l.size() == 3).count() == 2) ||
                ((cardMaps.values().stream().anyMatch(l -> l.size() == 3))
                        && (cardMaps.values().stream().anyMatch(l -> l.size() == 2)));
    }

    private static Hand getFourHand(Map<String, List<String>> cardMaps) {
        String four = cardMaps.entrySet().stream()
                .filter(e -> e.getValue().size() == 4)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow();
        String highest = getHighestExcept(cardMaps, Collections.singletonList(four));
        return new Hand("four-of-a-kind", new String[]{four, highest});
    }

    private static String getHighestExcept(Map<String, List<String>> cardMaps, List<String> except) {
        for (String s : CARDS_VALUE.keySet()) {
            if (cardMaps.containsKey(s) && !except.contains(s)) {
                return s;
            }
        }
        throw new RuntimeException();
    }

    private static boolean isItFour(Map<String, List<String>> cardMaps) {
        return cardMaps.values().stream().anyMatch(l -> l.size() == 4);
    }

    private static Map<String, List<String>> getCardsMap(String[] holeCards, String[] communityCards) {
        Map<String, List<String>> types = new HashMap<>();
        for (String s : holeCards) {
            String type = s.substring(0, s.length() - 1);
            if (!types.containsKey(type)) {
                types.put(type, new ArrayList<>());
            }
            types.get(type).add(s.substring(s.length() - 1));
        }
        for (String s : communityCards) {
            String type = s.substring(0, s.length() - 1);
            if (!types.containsKey(type)) {
                types.put(type, new ArrayList<>());
            }
            types.get(type).add(s.substring(s.length() - 1));
        }
        return types;
    }

    private static String[] getAllCards(String[] holeCards, String[] communityCards) {
        String[] result = new String[7];
        System.arraycopy(holeCards, 0, result, 0, 2);
        System.arraycopy(communityCards, 0, result, 2, 5);
        return result;
    }

    private static Hand getStraightFlushHand(String[] holeCards, String[] communityCards) {
        return new Hand("straight-flush", getStraightCards(holeCards, communityCards));
    }

    private static String[] getStraightCards(String[] holeCards, String[] communityCards) {
        Map<String, List<String>> flushMap = getFlushMap(holeCards, communityCards);
        String[] cards = new String[7];
        for (List<String> v : flushMap.values()) {
            if (v.size() >= 5) {
                cards = getCardsRow(getCardsByRank(v));
            }
        }
        return Arrays.copyOfRange(cards, 0, 5);
    }

    private static boolean isItStraightFlush(String[] holeCards, String[] communityCards) {
        if (isItFlush(holeCards, communityCards)) {
            Map<String, List<String>> flushMap = getFlushMap(holeCards, communityCards);
            for (List<String> v : flushMap.values()) {
                if (v.size() >= 5) {
                    String[] cards = getCardsByRank(v);
                    if (isFiveCardsOnRaw(cards)) {
                        return true;
                    }
                }
                ;
            }
        }
        return false;
    }

    private static boolean isFiveCardsOnRaw(String[] cards) {
        String[] cardsRow = getCardsRow(cards);
        return cardsRow.length >= 5;
    }

    private static String[] getCardsRow(String[] cards) {
        Set<String> result = new LinkedHashSet<>();
        for (int i = 0; i < cards.length - 1; i++) {
            if (result.size() == 5) return result.toArray(new String[0]);
            if (CARDS_VALUE.get(cards[i]) - CARDS_VALUE.get(cards[i + 1]) == 1) {
                result.add(cards[i]);
            } else if (CARDS_VALUE.get(cards[i]) - CARDS_VALUE.get(cards[i + 1]) > 1) {
                result.clear();
            }
            if (result.size() == 4) {
                result.add(cards[i + 1]);
                return result.toArray(new String[0]);
            }
        }
        return result.toArray(new String[0]);
    }

    private static String[] getCardsByRank(List<String> cards) {
        cards.sort(Comparator.comparingInt(CARDS_VALUE::get).reversed());
        return cards.toArray(new String[0]);
    }


    private static boolean isItFlush(String[] holeCards, String[] communityCards) {
        Map<String, List<String>> types = getFlushMap(holeCards, communityCards);
        for (List<String> v : types.values()) {
            if (v.size() >= 5) return true;
        }
        return false;
    }

    private static Map<String, List<String>> getFlushMap(String[] holeCards, String[] communityCards) {
        Map<String, List<String>> types = new HashMap<>();
        for (String s : holeCards) {
            String type = s.substring(s.length() - 1);
            if (!types.containsKey(type)) {
                types.put(type, new ArrayList<>());
            }
            types.get(type).add(s.substring(0, s.length() - 1));
        }
        for (String s : communityCards) {
            String type = s.substring(s.length() - 1);
            if (!types.containsKey(type)) {
                types.put(type, new ArrayList<>());
            }
            types.get(type).add(s.substring(0, s.length() - 1));
        }
        return types;
    }

    /**
     * 4 kyu
     * The observed PIN
     * https://www.codewars.com/kata/5263c6999e0f40dee200059d/train/java
     *
     * @param observed
     * @return
     */
    public static List<String> getPINs(String observed) {
        Map<String, String> numbers = new HashMap<String, String>() {{
            put("1", "124");
            put("2", "2135");
            put("3", "326");
            put("4", "4157");
            put("5", "54268");
            put("6", "6953");
            put("7", "748");
            put("8", "87590");
            put("9", "986");
            put("0", "08");
        }};
        Set<String> result = new HashSet<>();
        Set<String> temp = new HashSet<>();
        for (int i = 0; i < observed.length(); i++) {
            String[] split = observed.split("");
            String nums = numbers.get(split[i]);
            if (result.isEmpty()) {
                result.addAll(Arrays.asList(nums.split("")));
                continue;
            }
            for (String r : result) {
                for (String n : nums.split("")) {
                    temp.add(r + n);
                }
            }
            result = temp;
            temp = new HashSet<>();
        }
        return new ArrayList<>(result);
    }

    public static int lettersToNumbers(String s) {
        int sum = 0;
        char[] split = s.toCharArray();
        for (char c : split) {
            if (c >= 48 && c <= 57) sum = sum + c - 48;
            else if (c >= 65 && c <= 90) sum = sum + ((c - 64) * 2);
            else if (c >= 97 && c <= 122) sum = sum + c - 96;
        }
        return sum;
    }


    /**
     * 5 kyu
     * Can you get the loop ?
     * https://www.codewars.com/kata/52a89c2ea8ddc5547a000863/train/java
     *
     * @param node
     * @return Надо было просто сложить все в ArrayList, так как индекс ==  count
     */
    public int loopSize(Node node) {
        int count = 0;
        HashMap<Node, Integer> nodeIntegerHashMap = new HashMap<>();
        do {
            nodeIntegerHashMap.put(node, count++);
            node = node.getNext();
        } while (!nodeIntegerHashMap.containsKey(node));
        return nodeIntegerHashMap.size() - nodeIntegerHashMap.get(node);
    }


    /**
     * 6 kyu
     * Good vs Evil
     * https://www.codewars.com/kata/52761ee4cffbc69732000738/train/java
     **/
    public static String battle(String goodAmounts, String evilAmounts) {
        int[] goodStrength = {1, 2, 3, 3, 4, 10};
        int[] evilStrength = {1, 2, 2, 2, 3, 5, 10};
        String goodWin = "Battle Result: Good triumphs over Evil";
        String evilWin = "Battle Result: Evil eradicates all trace of Good";
        String drawWin = "Battle Result: No victor on this battle field";
        int goodArmyStrength = geArmyStrength(goodAmounts, goodStrength);
        int evilArmyStrength = geArmyStrength(evilAmounts, evilStrength);
        if (goodArmyStrength == evilArmyStrength) return drawWin;
        return goodArmyStrength > evilArmyStrength ? goodWin : evilWin;
    }

    private static int geArmyStrength(String amounts, int[] strength) {
        int sum = 0;
        String[] amountsArray = amounts.split(" ");
        for (int i = 0; i < amountsArray.length; i++) {
            sum += Integer.parseInt(amountsArray[i]) * strength[i];
        }
        return sum;
    }

    public static String makeReadable(int seconds) {
        String pattern = "%s:%s:%s";
        String hh = getHours(seconds);
        String mm = getMins(seconds);
        String ss = getSeconds(seconds);
        return String.format(pattern, hh, mm, ss);
    }

    private static String getSeconds(int seconds) {
        int sec = seconds % 60;
        return sec / 10 > 0 ? String.valueOf(sec) : "0" + sec;
    }

    private static String getMins(int seconds) {
        int mins = (seconds % 3600) / 60;
        return mins / 10 > 0 ? String.valueOf(mins) : "0" + mins;
    }

    private static String getHours(int seconds) {
        int hours = seconds / 3600;
        return hours / 10 > 0 ? String.valueOf(hours) : "0" + hours;
    }

    public static int zadSlavi(int[] deployGraph, int vacationLength) {
        int[] sum = new int[deployGraph.length];
        int count = 0;

        for (int i = 0; i < deployGraph.length; i++) {
            if (deployGraph[i] == 0) {
                count++;
            } else {
                sum[i] = count;
                count = 0;
            }
            if (i == deployGraph.length - 1) {
                sum[i] = count;
            }
        }

        int result = 0;

        for (int j : sum) {
            if (j >= vacationLength) {
                result = result + (j - vacationLength + 1);
                for (int i = j; i > vacationLength; i--) {
                    result = result + (j - i + 1);
                }
            }
        }
        return result;
    }

    public static String histogram(final int results[]) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = results.length - 1; i >= 0; i--) {
            stringBuilder.append(i + 1)
                    .append("|")
                    .append("#".repeat(Math.max(0, results[i])));
            if (results[i] > 0) {
                stringBuilder.append(" ").append(results[i]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static String histogramPercent(final int results[]) {
        StringBuilder stringBuilder = new StringBuilder();
        int sum = 0;
        for (int num : results) sum = sum + num;
        sum = sum == 0 ? 1 : sum;
        for (int i = results.length - 1; i >= 0; i--) {
            stringBuilder.append(i + 1)
                    .append("|")
                    .append("█".repeat(results[i] * 100 / sum * 50 / 100));
            if (results[i] > 0) {
                stringBuilder.append(" ").append(results[i] * 100 / sum).append("%");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static int[] findMine(int[][] field) {
        int[] result = new int[2];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 1) result = new int[]{i, j};
            }
        }
        return result;
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 1) return 1;
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (!stringBuilder.toString().contains(s.substring(j, j + 1))) {
                    stringBuilder.append(s.charAt(j));
                } else {
                    if (stringBuilder.length() > count) {
                        count = stringBuilder.length();
                    }
                    stringBuilder.delete(0, stringBuilder.length());
                    break;
                }

            }
        }
        return count;
    }

    public static boolean substring(String str, String ending) {
        if (ending.length() > str.length()) {
            return false;
        }
        return ending.equals(str.substring(str.length() - ending.length()));
    }


    /**
     * 6 kyu
     * Simple sum of pairs
     * https://www.codewars.com/kata/5bc027fccd4ec86c840000b7/train/java
     *
     * @param n
     * @return
     */
    public static int simplePairOfSum(long n) {
        if (n < 10) return (int) n;
        long raz = String.valueOf(n).length() - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < raz; i++) {
            sb.append(9);
        }
        long first = Long.parseLong(sb.toString());
        int result = 0;
        long c = n - first;
        while (c != 0) {
            result += (int) (c % 10);
            c = (c / 10);
        }
        return result + (int) raz * 9;
    }
}
