package ObjectOrientedCaesarCipher;

import edu.duke.*;

public class TestCaesarCipher {
    public static int[] countLetters(String message) {

        int[] counts = new int[26];
        String alphabet = "ABCDEFGHİJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < message.length(); i++) {
            char currChar = message.charAt(i);
            int index = 0;
            if (Character.isUpperCase(currChar)) {
                index = alphabet.indexOf(currChar);
            } else {
                index = alphabet.toLowerCase().indexOf(currChar);
            }

            if (index != -1) {
                counts[index]++;
            }

        }
        return counts;
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

    public static void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipher cc = new CaesarCipher(24);
        String encrypted = cc.encrypt(input);
        String decrypted = cc.decrypt(encrypted);

        System.out.println(input);
        System.out.println(encrypted);
        System.out.println(decrypted);

        // FileResource fr2 = new FileResource();
        // String input2 = fr.asString();

        System.out.println(breakCaesarCipher(encrypted));
    }

    public static String breakCaesarCipher(String input) {
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        int[] counts = countLetters(input);
        int key = (maxIndex(counts) - 4 + 26) % 26;
        System.out.println(key);

        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(input);
    }

    public static void main(String[] args) {
        simpleTests();
    }
}