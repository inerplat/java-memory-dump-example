package com.example.demo.util;

import java.util.Arrays;

public class LargeMemoryUtil {
    public static int next = 1;
    public static byte[] generate(int size) {
        byte[] bytes = new byte[size];
        Arrays.fill(bytes, (byte) 123);
        return bytes;
    }

    public static byte[] generate() {
        byte[] bytes = new byte[next];
        Arrays.fill(bytes, (byte) 123);
        return bytes;
    }

    public static void increase() {
        next = next * 2;
    }
}
