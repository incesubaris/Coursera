import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyNames {
    
    public void totalBirths(int year){
        int total = 0;
        int boys = 0;
        int girls = 0;
        
        int girlscounter = 0;
        int boyscounter = 0;
        String fileName = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        //String fileName = "us_babynames/us_babynames_test/yob" + year + "short.csv";
        FileResource fr = new FileResource(fileName);
        
        CSVParser parser = fr.getCSVParser(false);
        
        for(CSVRecord record : parser){
            int temp = Integer.parseInt(record.get(2));
            if(record.get(1).equals("F")){
                girlscounter++;
                girls = girls + temp;
            }
            if(record.get(1).equals("M")){
                boyscounter++;
                boys = boys +temp;
            }
            total = total + temp;
        }
        System.out.println(girls + " Girls birth in " + year);
        System.out.println(boys + " Boys birth in " + year);
        System.out.println(total + " Total birth in " + year);
        System.out.println(boyscounter + " Boys name in " + year);
        System.out.println( girlscounter + " Girls name in " + year);
        
    }
    
    public int getRank(int year, String name, String gender){
        
        String fileName = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        //String fileName = "us_babynames/us_babynames_test/yob" + year + "short.csv";
        FileResource fr = new FileResource(fileName);
        
        CSVParser parser = fr.getCSVParser(false);
        
        int rank = 1;
        boolean matchStatus = false;
        
        for(CSVRecord record : parser){
            if(record.get(0).equals(name) && record.get(1).equals(gender)){
                matchStatus = true;
            }
            if(!matchStatus && record.get(1).equals(gender)){
                rank++;
                //break;
            }
        }
        if(!matchStatus) {
            return -1;
        }
        return rank;
        
    }
    
    public String getName(int year, int rank, String gender){

        String fileName = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        //String fileName = "us_babynames/us_babynames_test/yob" + year + "short.csv";
        FileResource fr = new FileResource(fileName);
        
        CSVParser parser = fr.getCSVParser(false);
        
        String name = "";
        int tempRank = 0;
        boolean matchStatus = false;
        
        for(CSVRecord record : parser){
            if(record.get(1).equals(gender)){
                tempRank++;
            
            }
            if(rank == tempRank){
                name = record.get(0);
                matchStatus = true;
            }
        }
        if(!matchStatus) {
            return "NO NAME";
        }
        
        return name;
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        
        int rank = getRank(year, name, gender);
        
        String newName = getName(newYear, rank, gender);
        
        System.out.println(name + " born in year would be " + newName + " if she was born in " + newYear);
    
        
    }
    
    public int yearOfHighestRank(String name, String gender){
        
        DirectoryResource dr = new DirectoryResource();
        
        int highestRank = 0;
        int highestYear = 0;
        
        for(File f : dr.selectedFiles()){
            String fileName = f.getName();
            //System.out.println(fileName);
            int begin = fileName.indexOf("yob");
            int end = fileName.indexOf(".csv");
            //int end = fileName.indexOf("short.csv");
            
            int year = Integer.parseInt(fileName.substring(begin+3,end));
            
            int currRank = getRank(year,name,gender);
            
            
            if( currRank != -1 && highestRank == 0){
                highestRank = currRank;
                highestYear = year;
            }
            
            if(currRank != -1 && currRank < highestRank){
                highestRank = currRank;
                highestYear = year;
            
            }
            
            
        }
        return highestYear;
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        
        double total = 0;
        double counter = 0;
        
        for(File f : dr.selectedFiles()){
            String fileName = f.getName();
            //System.out.println(fileName);
            int begin = fileName.indexOf("yob");
            int end = fileName.indexOf(".csv");
            //int end = fileName.indexOf("short.csv");
            
            
            int year = Integer.parseInt(fileName.substring(begin+3,end));
            
            int currRank = getRank(year,name,gender);
            
            total = total + currRank;
            counter++;
        }
        return total / counter;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){

        String fileName = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        //String fileName = "us_babynames/us_babynames_test/yob" + year + "short.csv";
        FileResource fr = new FileResource(fileName);
        
        CSVParser parser = fr.getCSVParser(false);
        
        int total = 0;
        int rank = getRank(year,name,gender);
        int i = 1;
        for(CSVRecord record : parser){
            
            if(record.get(1).equals(gender)){
                /*
                String currName = record.get(0);
                int currRank = getRank(year,currName,gender);
                if(currRank < rank){
                    total = total + Integer.parseInt(record.get(2));
                }*/
                
                if(i < rank){
                    
                    total = total + Integer.parseInt(record.get(2));
                    i++;
                }
                
            }
            
        }
        return total;
    }
    
    public void test(){
        //totalBirths(2013);
        //System.out.println(getRank(2014, "Amelia", "F"));
        //System.out.println(getName(2014, 2, "M"));
        //whatIsNameInYear("Isabella",2012,2014,"F"); // Isabella born in year would be Sophia if she was born in 2014
        
        //System.out.println(yearOfHighestRank("Mason", "M")); // 2
        //System.out.println(getAverageRank("Mason", "M")); // 3.0
        //System.out.println(getTotalBirthsRankedHigher(2013,"Mason", "M")); // 15
        
        //totalBirths(1900); //2225
        //totalBirths(1905); //1421
        //System.out.println(getRank(1960, "Emily", "F")); //251
        //System.out.println(getRank(1971, "Frank", "M")); //54
        //System.out.println(getName(1980, 350, "F")); //Mia
        //System.out.println(getName(1982, 450, "M")); //Forrest
        //whatIsNameInYear("Susan",1972,2014,"F"); //Addison
        //whatIsNameInYear("Owen",1974,2014,"M"); //Leonel
        
        System.out.println(yearOfHighestRank("Genevieve", "F")); // 1914
        System.out.println(yearOfHighestRank("Mich", "M")); // 1960
        //System.out.println(getAverageRank("Susan", "F")); // 173.51
        //System.out.println(getAverageRank("Robert", "M")); // 10.75
        
        //System.out.println(getTotalBirthsRankedHigher(1990,"Emily", "F")); // 323200
        //System.out.println(getTotalBirthsRankedHigher(1990,"Drew", "M")); // 1498074
        
        
    }

}
