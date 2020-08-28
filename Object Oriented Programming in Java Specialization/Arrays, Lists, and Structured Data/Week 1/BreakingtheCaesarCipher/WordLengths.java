package BreakingtheCaesarCipher;

import edu.duke.FileResource;

public class WordLengths {
    public static void countWordLengths(FileResource resource, int[] counts) {
        // 7 harfli sayıların sayısı arrayde olacak
        // 1 2 3 4 5 --> 1 uzunluk -> counts[0]
        for (String s : resource.words()) {
            int index = s.length();
            if (!Character.isLetter(s.charAt(0))) {
                index = index - 1;
            }
            if (!Character.isLetter(s.charAt(s.length() - 1))) {
                index = index - 1;
            }

            if (index >= counts.length) {
                counts[counts.length - 1]++;
            } else {
                counts[index]++;
            }

        }
    }

    public static int indexOfMax(int[] values) {
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            if (index < values[i]) {
                index = values[i];
            }
        }
        return index;
    }

    public static void main(String[] args) {
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);

        System.out.println(indexOfMax(counts));

    }
}