
/**
 * Write a description of Part2 here.
 * 
 * @author incesubaris 
 * @version 12.08.2020
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String gene;
        boolean geneStatus = true;
        
        if(isStringUpper(dna)){
            int startIndex = dna.indexOf(startCodon.toUpperCase());
            int stopIndex = dna.indexOf(stopCodon.toUpperCase(), startIndex + 3);
            if(startIndex == -1 || stopIndex == -1){
                geneStatus = false;
            }
            if(((stopIndex - startIndex) % 3 == 0) && geneStatus){
                gene = dna.substring(startIndex,stopIndex +3);
            }
            else{
                gene = "**gene does not found**";
            }
        }
        else{
            int startIndex = dna.indexOf(startCodon.toLowerCase());
            int stopIndex = dna.indexOf(stopCodon.toLowerCase(), startIndex + 3);
            if(startIndex == -1 || stopIndex == -1){
                geneStatus = false;
            }
            if(((stopIndex - startIndex) % 3 == 0) && geneStatus){
                gene = dna.substring(startIndex,stopIndex +3);
            }
            else{
                gene = "**gene does not found**";
            }
        }

        return gene;
    }
    
        public boolean isStringUpper(String s){
        //convert String to char array
        char[] charArray = s.toCharArray();
        
        for(int i=0; i < charArray.length; i++){
            
            //if any character is in lower case, return false
            if( !Character.isUpperCase( charArray[i] )){
                return false;
            }
        }
        return true;
    }
    
    public void testSimpleGene(){
       
        String dna1 = "TGAAATTTATATATAGGTAAGGGTTTA"; // aAatGTgatgtaaatg ** Exception!
        String dna2 = "TGAAATGTATATATAGGTAGGGTTTA";
        String dna3 = "TGAAAGGTATATATAGGTATA";
        String dna4 = "tggaatgaaatttgggtaatga";
        String dna5 = "aaatgtgatgtaaatg";
        
        
        System.out.println(dna1);
        System.out.println(findSimpleGene(dna1, "atg", "taa"));
        
        System.out.println(dna2);
        System.out.println(findSimpleGene(dna2, "ATG", "TAA"));
        
        System.out.println(dna3);
        System.out.println(findSimpleGene(dna3, "ATG", "TAA"));
        
        System.out.println(dna4);
        System.out.println(findSimpleGene(dna4, "ATG", "TAA"));
        
        System.out.println(dna5);
        System.out.println(findSimpleGene(dna5, "ATG", "TAA"));
    }
    

}
