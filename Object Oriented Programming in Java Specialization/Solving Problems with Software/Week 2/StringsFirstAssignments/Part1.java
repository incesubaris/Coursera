
/**
 * Write a description of Part1 here.
 * 
 * @author incesubaris
 * @version 12.08.2020
 */
public class Part1 {
    public String findSimpleGene(String dna){
        String gene;
        boolean geneStatus = true;
        int startIndex = dna.indexOf("ATG");
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if(startIndex == -1 || stopIndex == -1){
            geneStatus = false;
        }
        if(((stopIndex - startIndex) % 3 == 0) && geneStatus){  //2-(-1)=3 ** Exception!
            gene = dna.substring(startIndex,stopIndex +3);
        }
        else{
            gene = "**gene does not found**";
        }
        return gene;
    }
    
    public void testSimpleGene(){
        String dna1 = "TGAAATTTATATATAGGTAAGGGTTTA"; // gene does not found
        String dna2 = "TGAAATGTATATATAGGTAGGGTTTA"; // gene does not found
        String dna3 = "TGAAAGGTATATATAGGTATA"; // gene does not found
        String dna4 = "TGGAATGAAATTTGGGTAATGA"; // ATGAAATTTGGGTAA
        String dna5 = "AAATGTGATGTAAATG"; // gene does not found
        
        System.out.println(dna1);
        System.out.println(findSimpleGene(dna1));
        
        System.out.println(dna2);
        System.out.println(findSimpleGene(dna2));
        
        System.out.println(dna3);
        System.out.println(findSimpleGene(dna3));
        
        System.out.println(dna4);
        System.out.println(findSimpleGene(dna4));
        
        System.out.println(dna5);
        System.out.println(findSimpleGene(dna5));
    }
}
