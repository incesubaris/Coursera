
/**
 * Write a description of ParsingWeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*; // File Class

public class ParsingWeatherData {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestRecord = null;
        double coldestTemperature = 0;
        
        for(CSVRecord currRecord : parser){
            
            double currColdest = Double.parseDouble(currRecord.get("TemperatureF"));
            if(coldestRecord == null && currColdest != -9999){ //** First Temperature = null State
                coldestRecord = currRecord;
                coldestTemperature = currColdest;
            }
            if(currColdest < coldestTemperature && currColdest != -9999){
                coldestRecord = currRecord;
                coldestTemperature = currColdest;
            }
            
        }
        return coldestRecord;
    }
    
    public String fileWithColdestTemperature(){
        String fileName = "";
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestDay = null; 
        double coldestTemp = 0 ;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            
            CSVRecord currDay = coldestHourInFile(parser);
            double currTemp = Double.parseDouble(currDay.get("TemperatureF"));
            
            if(coldestDay == null){
                coldestDay = currDay;
                coldestTemp = currTemp;
            }
            if(currTemp < coldestTemp){
                coldestDay = currDay;
                coldestTemp = currTemp;
                fileName = f.getAbsolutePath();
            }
        }
        return fileName;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestRecord = null;
        double lowestHumidity = 0;
        
        for(CSVRecord currRecord : parser){
            if(!currRecord.get("Humidity").equals("N/A")){
                double currLowest = Double.parseDouble(currRecord.get("Humidity"));
                if(lowestRecord == null){
                    lowestRecord = currRecord;
                    lowestHumidity = currLowest;
                }
                if(currLowest < lowestHumidity){
                    lowestRecord = currRecord;
                    lowestHumidity = currLowest;
                }
            }
        }
        return lowestRecord;
    }
    
    public CSVRecord lowestHumidityInManyFiles(){        
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestDay = null; 
        double lowestHumidity = 0 ;
        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            
            CSVRecord currDay = lowestHumidityInFile(parser);
            double currHumidity = Double.parseDouble(currDay.get("Humidity"));
            
            if(lowestDay == null){
                
                lowestDay = currDay;
                lowestHumidity = currHumidity;
                
            }
            if(currHumidity < lowestHumidity){
                
                lowestDay = currDay;
                lowestHumidity = currHumidity;
            }
        }
        
        return lowestDay;
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        
        double average = 0;
        double total = 0;
        int count = 0;
        
        for(CSVRecord currRecord : parser){
            double temp = Double.parseDouble(currRecord.get("TemperatureF"));
            
            if(temp != -9999){
                total = temp + total;
                count++;

            }
        }
        
        return total/count;
    }
    
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        
        double average = 0;
        double total = 0;
        int count = 0;
        
        for(CSVRecord currRecord : parser){
        
            int currHumidity = Integer.parseInt(currRecord.get("Humidity"));
            double temp = Double.parseDouble(currRecord.get("TemperatureF"));
               
            if(currHumidity >= value && temp != -9999){
                total = temp + total;
                count++;
                
            }
            
            
        }
        if(count == 0){
            return 0;
        }
        return total / count;
    }
    ///******************** TEST METHODS ********************///
    public void testColdestHourInFile(){
    
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        CSVRecord coldestTest = coldestHourInFile(parser);
        System.out.println(coldestTest.get("TemperatureF") + " on " + coldestTest.get("TimeEDT"));
        
    
    }
    
    public void testFileWithColdestTemperature(){
        
        String fileWithColdestTemp = fileWithColdestTemperature();
        File f = new File(fileWithColdestTemp);
        FileResource fr = new FileResource(f);
        
        System.out.println("Coldest day was in file " + f.getName());
        
        System.out.println("Coldest temperature on that day was " + coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        
        for(CSVRecord record : fr.getCSVParser()){
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
        
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
        // Lowest Humidity was 24 at 2014-01-20 19:51:00
    
    }
    
    public void testLowestHumidityInManyFiles(){
        
        CSVRecord csv = lowestHumidityInManyFiles();
        
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
        // Lowest Humidity was 24 at 2014-01-20 19:51:00
    }
    
    public void testAverageTemperatureInFile(){
    
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        double average = averageTemperatureInFile(parser);
        
        System.out.println("Average temperature in file is " + average);
        // Average temperature in file is 44.93333333333334
    
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        
        if(average != 0){
            System.out.println("Average Temp when high Humidity is " + average);
        }else{
            System.out.println("No temperatures with that humidity");
        }
   
    
    }
}
