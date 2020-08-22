
/**
 * Write a description of Part3 here.
 * 
 * @author incesubaris 
 * @version 12.08.2020
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int count = 1;
        int firstIndex = stringb.indexOf(stringa);
        
        if(firstIndex == -1){
            count = 0;
            return false;
        }
        
        if(count == 1 && (stringb.indexOf(stringa,firstIndex + stringa.length())) == -1){
           return false;
        }
        return true;
    }
    
    public String lastPart(String stringa, String stringb){
        if(stringb.indexOf(stringa) == -1){
            return stringb;
        }
        else{
            return stringb.substring(stringb.indexOf(stringa)+stringa.length());
        }
    }
    
    public void testOccurrences(){
        System.out.println(twoOccurrences("by", "A story by Abby Long"));   //true
        System.out.println(twoOccurrences("a", "banana"));                  //true
        System.out.println(twoOccurrences("atg", "ctgtatgta"));             //false
        
        System.out.println(lastPart("an", "banana"));                       //ana
        System.out.println(lastPart("zoo", "forest"));                      //forest
    }
}
