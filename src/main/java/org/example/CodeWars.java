package org.example;

public class CodeWars {

    public static String histogram(final int results[]) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = results.length - 1; i >= 0; i--) {
            stringBuilder.append(i+1)
                    .append("|")
                    .append("#".repeat(Math.max(0, results[i])));
            if (results[i] > 0) {
                stringBuilder.append(" ").append(results[i]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
