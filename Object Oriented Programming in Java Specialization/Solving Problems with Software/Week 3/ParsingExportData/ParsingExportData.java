
/**
 * Write a description of ParsingExportData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

import org.apache.commons.csv.*; 

public class ParsingExportData {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //listExportersTwoProducts(parser, "cotton", "flowers" );
        //System.out.println(numberOfExporters(parser, "cocoa"));
        bigExporters(parser, "$999,999,999,999");
        //countryInfo(parser, "Nauru");
    }
    
    public void countryInfo(CSVParser parser, String country){
        //Country,Exports,Value (dollars)
        for(CSVRecord record : parser){
            
            if(record.get("Country").equals(country)){
                
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                
                if(exports.isEmpty()){
                    exports = "NOT FOUND";
                }
                
                if(value.isEmpty()){
                    value = "NOT FOUND";
                }
                System.out.println(country + ": " + exports + ": " + value);
            
            }
            
        }
    
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        
        for(CSVRecord record : parser){
            
            //String exports = record.get("Exports");
            if(record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)){
                
                String country = record.get("Country");
                System.out.println(country);
            
            }
        
        }
    
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int counter = 0;
        for(CSVRecord record : parser){
            
            //String exports = record.get("Exports");
            if(record.get("Exports").contains(exportItem)){
                counter++;
            }
        }
        return counter;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()){
                String country = record.get("Country");
                System.out.println(country + " " + value );
            }
        }
    }

}
