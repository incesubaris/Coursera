package CaesarCipher;

public class WordPlay {
    public static boolean isVowel(char ch){
        String key = "aeiouAEIOU";
        
        if(key.indexOf(ch) != -1){
            return true;
        }
        else {
            return false;
        }
    }
    
    public static String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        
        for(int i = 0; i < phrase.length(); i++){
        
            if(isVowel(sb.charAt(i))){
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    public static String emphasize(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        
        Character lower = Character.toLowerCase(ch);
        Character upper = Character.toUpperCase(ch);
        
        for(int i = 0; i < phrase.length(); i++){
            if(sb.charAt(i) == lower || sb.charAt(i) == upper){
                if((i+1) % 2 == 1){
                    sb.setCharAt(i, '*');
                }
                if((i+1) % 2 == 0){
                    sb.setCharAt(i, '+');
                }
            }
        }
        return sb.toString();
    }
    
    public void test(){
        //System.out.println(isVowel('F')); // false
        //System.out.println(isVowel('a')); // true
        
        //System.out.println(replaceVowels("Hello World", '*')); // H*ll* W*rld
        System.out.println(emphasize("dna ctgaaactga",  'a')); // dn* ctg+*+ctg+
        System.out.println(emphasize("Mary Bella Abracadabra", 'a')); // M+ry Bell+ +br*c*d*br+
        
    }

    public static void main(String[] args) {
        //System.out.println(isVowel('F')); // false
        //System.out.println(isVowel('a')); // true
        
        //System.out.println(replaceVowels("Hello World", '*')); // H*ll* W*rld
        System.out.println(emphasize("dna ctgaaactga",  'a')); // dn* ctg+*+ctg+
        System.out.println(emphasize("Mary Bella Abracadabra", 'a')); // M+ry Bell+ +br*c*d*br+
    }
}