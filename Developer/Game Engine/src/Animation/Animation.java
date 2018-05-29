package Animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;


public class Animation {

    private ArrayList<BufferedImage> image;
    private int current = 0;
    private int delay;
    private int initialDelay;

    public Animation(){

        image = new ArrayList<>();

        current = 0;
        delay   = 0;

        initialDelay = 0;
    }


    public void update(){
      if(isAnimationOver())
          reset();
    }

    public Animation(String file, String format,int length, int duration) {

        this.initialDelay = duration;
        delay = duration;
        image = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            String fileName = file + i + format;
            try {
                image.add(ImageIO.read(new File(fileName)));
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
    }

    public void setTime(int length){
        initialDelay = length;
        delay        = length;
    }

    public void setInitialDelay(int d){
        initialDelay += d;
        delay        += d;
    }


    public Image nextImage() {

        if(current != image.size()-1) {
            if (delay == 0) {
                current++;

                delay = initialDelay;
            } else
                delay--;
        }
        return image.get(current);
    }

    public void reset(){
        current = 0;
    }

    public Image previousImage() {

        if (current > 0){
            if (delay == 0) {
                current--;
                delay = initialDelay;
            } else
                delay--;
        }
        return image.get(current);
    }

    public Image getCurrentImg(){ return image.get(current);}

    public boolean isAnimationOver(){ return current == image.size()-1; }

    public int getIndex(){
        return current;
    }

    public int getLength(){ return image.size(); }

    public void addImg(BufferedImage img){ image.add(img);}

    public void addImg(String file){
        try{
            image.add(ImageIO.read(new File(file)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getCurrent() {
        return current;
    }


    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
        initialDelay = delay;
    }
}
