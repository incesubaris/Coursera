
/**
 * 1. Create a new Java project named StringsThirdAssignments. 
 * You can put all the classes for this programming exercise in this project.

 * 2. Create a new Java Class named Part1. 
 * Copy and paste the code from your Part1 class in your StringsSecondAssignments project into this class.

 * 3. Make a copy of the printAllGenes method called getAllGenes.
 * Instead of printing the genes found, this method should create and return a StorageResource containing the genes found. 
 * Remember to import the edu.duke libraries otherwise you will get an error message cannot find the class StorageResource.

 * 4. Make sure you test your getAllGenes method.

 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

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
    
    public StorageResource getAllGenes(String dna){
        StorageResource sr = new StorageResource();
        int startPoint = 0;
        
        while(true){
            String currGene = findGene(dna, startPoint);
            if(currGene.isEmpty()){
                break;
            }
            sr.add(currGene);
            startPoint = dna.indexOf(currGene, startPoint) + currGene.length();
            
        }
        return sr;
    }
    
    public void testGetAllGenes(){
        String dna1 = "xxATGyyytttxxxyyytttxxTGAxTAAtttwwATGwpppoooppprrreeexxxyyytttzzTAGzxxxTGAxxxyytttATGxxTAAxyyyzzz";
        
        StorageResource sr = new StorageResource();
        
        sr = getAllGenes(dna1);
        
        for (String item : sr.data()) {
            System.out.println(item);
        }
    }
    
        public double cgRatio(String dna){
        char[] charArray = dna.toCharArray();
        double gc = 0.0;
        for(int i = 0; i < charArray.length; i++){
            if(charArray[i] == 'C' || charArray[i] == 'G'){
                gc++;
            }
        }

        return gc / charArray.length;
    }
    
    public void testcgRatio(){
        String dna = "ATGCCATAG";
        
        System.out.println(cgRatio(dna));
    }
    
    public void processGenes(StorageResource sr){
        int genecounter = 0;
        int counta = 0;
        int countb = 0;
        int tempLength = 0 ;
        for(String item: sr.data()){
            if(!findGene(item,0).isEmpty()){
                if(findGene(item,0).length() > 60){
                    System.out.println("the gene in DNA that are longer than 9 characters: " + item);
                    counta++;
                }
                if(cgRatio(findGene(item,0)) > 0.35){
                    System.out.println("the gene in DNA whose C-G-ratio is higher than 0.35: " + item);
                    countb++;
                }
                if(findGene(item,0).length() > tempLength){
                    tempLength = findGene(item,0).length();
                }
            }
            genecounter++;
        }
        System.out.println("Number of gene in DNA that are longer than 9 characters: " + counta);
        System.out.println("Number of gene in DNA whose C-G-ratio is higher than 0.35: " + countb);
        System.out.println("the length of the longest gene in DNA: " + tempLength);
        System.out.println("Number of gene in DNA: " + genecounter);
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        
        StorageResource geneList = getAllGenes(dna);
        processGenes(geneList);
    
    }
    
}
