package BreakingtheCaesarCipher;

import edu.duke.*;
import CaesarCipher.CaesarCipher;

public class CaesarBreaker {
    // countLetters, maxIndex, and decrypt
    public static void countLetters(String message, int[] counts) {

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < message.length(); i++) {
            int index = alphabet.indexOf(message.charAt(i));
            if (index != -1) {
                counts[index]++;
            }

        }
    }

    public static int maxIndex(int[] counts) {
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[index] <= counts[i]) {
                index = i;
            }
        }
        return index;
    }

    public static String decrypt(String message) {
        // String decrypt;

        // FileResource resource = new FileResource();

        // String message = resource.asString().toLowerCase();

        message = message.toLowerCase();
        int[] alphabet = new int[26];
        countLetters(message, alphabet);

        int freq = maxIndex(alphabet);
        int potentialKey = (freq - 4 + 26) % 26;

        String alphabet1 = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(alphabet1.charAt(freq));
        System.out.println(freq);
        System.out.println(potentialKey);

        CaesarCipher cc = new CaesarCipher();
        String unlocked = cc.encrypt(message, 26 - potentialKey);

        System.out.println(unlocked);

        return unlocked;
    }

    public static String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if (i % 2 == start) {
                char currChar = message.charAt(i);
                sb.append(currChar);
            }
        }
        return sb.toString();
    }

    public static int getKey(String s) {

        s = s.toLowerCase();
        int[] alphabet = new int[26];
        countLetters(s, alphabet); // freq

        int freq = maxIndex(alphabet);
        return (freq - 4 + 26) % 26;
    }

    public static String decryptTwoKeys(String encrypted) {

        StringBuilder sb = new StringBuilder();

        String part1 = halfOfString(encrypted, 0);
        String part2 = halfOfString(encrypted, 1);

        int key1 = getKey(part1);
        int key2 = getKey(part2);

        int part1index = 0;
        int part2index = 0;

        CaesarCipher cc = new CaesarCipher();
        String part1encrypted = cc.encrypt(part1, 26 - key1);
        String part2encrypted = cc.encrypt(part2, 26 - key2);

        for (int i = 0; i < encrypted.length(); i++) {
            if (i % 2 == 0) {
                char currChar = part1encrypted.charAt(part1index);
                sb.append(currChar);
                part1index++;
            }
            if (i % 2 == 1) {
                char currChar = part2encrypted.charAt(part2index);
                sb.append(currChar);
                part2index++;
            }
        }

        return sb.toString();

        /*
         * for (int i = 0; i < encrypted.length(); i++) {
         * 
         * CaesarCipher cc = new CaesarCipher();
         * 
         * if (i % 2 == 0) { char currChar = cc.encrypt(part1.charAt(i), 26 - key1);
         * sb.append(currChar); } if (i % 2 == 1) { char currChar = message.charAt(i);
         * sb.append(currChar); } }
         */

    }

    public static void main(String[] args) {

        CaesarCipher cc = new CaesarCipher();
        String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        // String message = "just a test string with lots of eeeeeeeeeeeeeeeees";

        // System.out.println(message);

        String locked = cc.encryptTwoKeys(message, 24, 13);
        System.out.println(locked);
        // decrypt(locked);

        // System.out.println(halfOfString("Qbkm Zgis", 0));
        // System.out.println(halfOfString("Qbkm Zgis", 1));

        System.out.println(decryptTwoKeys("Hhqg n gcfr qgpvlt jggf jbrf bd crcrcrcrcrcrcrcrcf"));
    }
}
