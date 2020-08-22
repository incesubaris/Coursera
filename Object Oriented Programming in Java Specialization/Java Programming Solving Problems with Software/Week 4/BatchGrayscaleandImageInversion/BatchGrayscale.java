
/**
 * Write a description of BatchGrayscale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class BatchGrayscale {
    public ImageResource makeGrayscale(ImageResource image){
        ImageResource outImage = new ImageResource(image.getWidth(), image.getHeight());
        
        for(Pixel pixel : image.pixels()){
            
            int x = pixel.getX();
            int y = pixel.getY();
            
            int red = pixel.getRed();
            int green = pixel.getGreen();
            int blue = pixel.getBlue();
            
            int average = (red + green + blue) / 3;
            
            Pixel outPixel = outImage.getPixel(x,y);
            
            outPixel.setRed(average);
            outPixel.setGreen(average);
            outPixel.setBlue(average);
            
            outImage.setPixel(x,y,outPixel);
            
        }
    
        return outImage;
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()){
        
            ImageResource image = new ImageResource(f);
            ImageResource outImage = makeGrayscale(image);
            
            outImage.draw();
            
            String fName = image.getFileName();
            String newName = "gray-" + fName;
            
            outImage.setFileName(newName);
            outImage.save();
        }
    }
}
