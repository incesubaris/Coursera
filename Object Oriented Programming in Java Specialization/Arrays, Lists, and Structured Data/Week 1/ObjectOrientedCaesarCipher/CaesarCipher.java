package ObjectOrientedCaesarCipher;

public class CaesarCipher {
    private static String alphabet;
    private static String shiftedAlphabet;
    private static int mainKey;

    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHİJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public static String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);

        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            if (Character.isUpperCase(currChar)) {
                int index = alphabet.indexOf(currChar);
                if (index != -1) {
                    sb.setCharAt(i, shiftedAlphabet.charAt(index));
                }
            } else {
                int index = alphabet.toLowerCase().indexOf(currChar);
                if (index != -1) {
                    sb.setCharAt(i, shiftedAlphabet.toLowerCase().charAt(index));
                }
            }
        }
        return sb.toString();

    }

    public static String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);

    }

}