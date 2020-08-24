
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        
        int currIndex = dna.indexOf(stopCodon,startIndex + 3);
        while(currIndex != -1){
            if((currIndex - startIndex) % 3 == 0){
                return currIndex;
            }
            currIndex = dna.indexOf(stopCodon,currIndex + 3);
        }
        return dna.length();
    }
    
    public void testFindStopCodon(){
        
        String dna1 = "ATGxxxzzzyyTAAyTAAwww"; //15
        String dna2 = "ATGxxxzTAGzzyyTGAyTAAwww"; //24
        String dna3 = "ATGxxxzzzyyTAGyTAAwww"; //21
        String dna4 = "xATGxTAAxzzzyyTGAyTAAwww"; //24
        String dna5 = "zzzATGxxxzzzyyTGAyTAAwww"; //24
        
        System.out.println(findStopCodon(dna1, 0, "TAA"));
        System.out.println(findStopCodon(dna2, 0, "TGA"));
        System.out.println(findStopCodon(dna3, 0, "TAG"));
        System.out.println(findStopCodon(dna4, 1, "TAA"));
        System.out.println(findStopCodon(dna5, 3, "TGA"));
    }
    
    public String findGene(String dna, int start){
        int startIndex = dna.indexOf("ATG", start);
        if(startIndex == -1){ 
            return ""; 
        }
        else{
            int taaIndex = findStopCodon(dna, startIndex, "TAA");
            int tagIndex = findStopCodon(dna, startIndex, "TAG");
            int tgaIndex = findStopCodon(dna, startIndex, "TGA");
            
            int temp = Math.min(taaIndex, tagIndex);
            int stopIndex = Math.min(temp, tgaIndex);
            
            if(stopIndex == dna.length()){
                return "";
            }
            return dna.substring(startIndex, stopIndex + 3);
        }
    }
    
    public void testFindGene(){
    
        String dna1 = "ATGxxxzzzyyTAAyTAAwww"; //15
        String dna2 = "ATGxxxzTAGzzyyTGATAAww"; //24
        String dna3 = "ATGxxxzzzyyTAGyTAAwww"; //21
        String dna4 = "xATGxTAAxzzzyyTGAyTAAwww"; //24
        String dna5 = "zzzxxxzzzyyTGAyTAAwww"; //24
        
        System.out.println("DNA: " + dna1);
        System.out.println("Gene: " + findGene(dna1, 0));
        System.out.println("DNA: " + dna2);
        System.out.println("Gene: " + findGene(dna2, 0));
        System.out.println("DNA: " + dna3);
        System.out.println("Gene: " + findGene(dna3, 0));
        System.out.println("DNA: " + dna4);
        System.out.println("Gene: " + findGene(dna4, 0));
        System.out.println("DNA: " + dna5);
        System.out.println("Gene: " + findGene(dna5, 0));
    }
    
    public void printAllGenes(String dna){
        int startPoint = 0;
        while(true){
            String currGene = findGene(dna, startPoint);
            if(currGene.isEmpty()){
                break;
            }
            System.out.println(currGene);
            System.out.println("-------------------");
            startPoint = dna.indexOf(currGene, startPoint) + currGene.length();
            
        }
    
    }
    
    public void testPrintAllGenes(){
        String dna1 = "xxATGyyytttxxxyyytttxxTGAxTAAtttwwATGwpppoooppprrreeexxxyyytttzzTAGzxxxTGAxxxyytttATGxxTAAxyyyzzz";
        printAllGenes(dna1);
    }
}
