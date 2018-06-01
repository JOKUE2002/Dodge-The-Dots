package de.sanoj.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private static BufferedImage sprite;
	
	public SpriteSheet(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height){
		BufferedImage img = sprite.getSubimage((row * 32)- 32, (col * 32)- 32, width, height);
		return img;
	}

}
