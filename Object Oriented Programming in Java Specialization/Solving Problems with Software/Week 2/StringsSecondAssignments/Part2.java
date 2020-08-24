
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int count = 0;
        int currIndex = stringb.indexOf(stringa);
        if(currIndex == -1){
            return count;
        }
        while(true){
            if(currIndex != -1){
                count = count + 1;
                currIndex = stringb.indexOf(stringa, currIndex + stringa.length());
            }
            else{
                break;
            }
        }
        return count;
    }
    public void testHowMany(){
        String stringa1 = "GAA";
        String stringb1 = "ATGAACGAATTGAATC";
        
        String stringa2 = "AA";
        String stringb2 = "ATAAAA";
        
        System.out.println(howMany(stringa1,stringb1));
        System.out.println(howMany(stringa2,stringb2));
    }
}
