package de.sanoj.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	BufferedImage image;
	
	public BufferedImageLoader() {
		
	}
	
	public BufferedImage loadImage(String path){
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
