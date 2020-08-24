
/**
 * Write a description of BatchInversions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class BatchInversions {
    
    public ImageResource makeInversion(ImageResource image){
        ImageResource outImage = new ImageResource(image.getWidth(), image.getHeight());
        
        for(Pixel pixel : image.pixels()){
            
            int x = pixel.getX();
            int y = pixel.getY();
            
            int newRed = 255 - pixel.getRed();
            int newGreen = 255 - pixel.getGreen();
            int newBlue = 255 - pixel.getBlue();
            
            Pixel outPixel = outImage.getPixel(x,y);
            
            outPixel.setRed(newRed);
            outPixel.setGreen(newGreen);
            outPixel.setBlue(newBlue);
            
            outImage.setPixel(x,y,outPixel);
            
        }
    
        return outImage;
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()){
        
            ImageResource image = new ImageResource(f);
            ImageResource outImage = makeInversion(image);
            
            outImage.draw();
            
            String fName = image.getFileName();
            String newName = "inversion-" + fName;
            
            outImage.setFileName(newName);
            outImage.save();
        }
    }
}
