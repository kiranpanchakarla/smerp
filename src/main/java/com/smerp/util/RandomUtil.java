package com.smerp.util;

import java.util.Random;

public class RandomUtil {
	
	public static String referenceId() {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] numbers = "1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        for (int i = 0; i < 6; i++) {
            char c = numbers[random.nextInt(numbers.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }

}
