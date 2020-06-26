package com.marcelomnc.score10pinbowling.common;

public class StringUtils {
    public static String center(String text, int len) {
        String out = String.format("%" + len + "s%s%" + len + "s", "", text, "");
        float mid = (out.length() / 2);
        float start = mid - (len / 2);
        float end = start + len;
        return out.substring((int) start, (int) end);
    }

    public static String justifyLeft(String text, int len) {
        return String.format("%-" + len + "s", text);
    }
}
