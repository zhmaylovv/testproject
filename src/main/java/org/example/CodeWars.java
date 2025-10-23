package org.example;

public class CodeWars {

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

    public static int[] findMine(int[][] field){
        int[] result = new int[2];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 1) result = new int[]{i, j};
            }
        }
        return result;
    }


}
