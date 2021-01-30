package com.istone.testbuy.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ruansu
 * on 2020/9/18 5:22 PM
 */
public class TestUtil {

    private static final char[] CHARS = new char[]{'-', '+'};

    public static String getResult(String input) {
        String regex = "%s";
        System.out.println(input + " " + regex);
        String[] inputs = input.split(regex);
        List<Pair> pairs = new ArrayList<>();
        for (String s : inputs) {
            if (isSymbol(s.charAt(0))) {
                int index = getLastNumberIndex(s);
                if (index != 0) {
                    String text = s.substring(0, index);
                    pairs.add(new Pair(regex + text, calculate(text)));
                }
            }
        }
        for (Pair pair : pairs) {
            input = input.replace(pair.getKey(), pair.getValue());
        }
        return input.replace(regex, getDate());
    }

    private static boolean isSymbol(char s) {
        for (char c : CHARS)
            if (Character.valueOf(s).equals(c))
                return true;
        return false;
    }

    private static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    private static int getLastNumberIndex(String text) {
        for (int i = 1; i < text.length(); i++)
            if (!isNumber(String.valueOf(text.charAt(i))))
                return i;
        return text.length();
    }

    private static String calculate(String text) {
        int value = Integer.parseInt(text);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + value);
        DateFormat format = new SimpleDateFormat("MMdd");
        return format.format(calendar.getTime());
    }

    private static String getDate() {
        Calendar calendar = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("MMdd");
        return format.format(calendar.getTime());
    }

    protected static class Pair {
        private String key, value;

        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

}
