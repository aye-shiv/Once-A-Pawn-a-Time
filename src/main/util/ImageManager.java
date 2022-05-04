package main.util;

import javax.swing.ImageIcon;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;

public class ImageManager {


    public static Image loadImage(String filename){
        return new ImageIcon(filename).getImage();
    }


    public static BufferedImage loadBufferedImage(String filename) {
		BufferedImage image = null;

		File file = new File (filename);
		try {
			image = ImageIO.read(file);
		}
		catch (Exception e) {
			System.out.println ("Error opening file " + filename);
            e.printStackTrace();
		}
		return image;
	}

}
