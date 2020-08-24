package CaesarCipher;

import edu.duke.FileResource;

public class CaesarCipher {
    public static String encrypt(String input, int key) {
        StringBuilder sb = new StringBuilder(input);

        String alphabet = "ABCDEFGHİJKLMNOPQRSTUVWXYZ"; // ** EXCEPTION (i,İ,ı,I)
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        // String alphabetLower = alphabetUpper.toLowerCase();
        // String shiftedAlphabetLower = alphabetLower.substring(key) +
        // alphabetLower.substring(0, key);

        // System.out.println(alphabet);
        // System.out.println(shiftedAlphabet);

        // System.out.println(alphabetLower);
        // System.out.println(shiftedAlphabetLower);

        for (int i = 0; i < sb.length(); i++) {
            char currChar = input.charAt(i);
            int index = -1;
            if (Character.isUpperCase(currChar)) {
                index = alphabet.indexOf(currChar);
            }
            if (Character.isLowerCase(currChar)) {
                index = alphabet.toLowerCase().indexOf(currChar);
            }
            // System.out.println("index: "+ index + "| " + currChar);
            if (index != -1) {
                if (Character.isUpperCase(currChar)) {
                    sb.setCharAt(i, shiftedAlphabet.charAt(index));
                }
                if (Character.isLowerCase(currChar)) {
                    sb.setCharAt(i, shiftedAlphabet.toLowerCase().charAt(index));
                }

            }

        }

        return sb.toString();
    }

    public static String encryptTwoKeys(String input, int key1, int key2) {
        String sb = "";
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                sb = sb + encrypt(Character.toString(input.charAt(i)), key1);
            }
            if (i % 2 == 1) {
                sb = sb + encrypt(Character.toString(input.charAt(i)), key2);
            }
        }
        return sb;
    }

    public static void main(String[] args) {
        System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
        FileResource fr = new FileResource();
        String message = fr.asString();
        // System.out.println("key is " + 23 + "\n" + encrypted);
        System.out.println(encrypt(message, 23)); // Cfopq Ibdflk
        // System.out.println(encrypt("First Legion", 23)); // Cfopq Ibdflk

    }
}