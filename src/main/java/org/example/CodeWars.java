package org.example;


import org.example.models.Node;

import java.util.HashMap;

public class CodeWars {


    /**
     * 5 kyu
     * Can you get the loop ?
     * https://www.codewars.com/kata/52a89c2ea8ddc5547a000863/train/java
     * @param node
     * @return
     * Надо было просто сложить все в ArrayList, так как индекс ==  count
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

    private static int geArmyStrength(String amounts, int[]strength) {
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
