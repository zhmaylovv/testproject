package org.example;

public class CodeWars {

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


}
