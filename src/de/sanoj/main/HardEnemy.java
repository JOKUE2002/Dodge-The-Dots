package de.sanoj.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class HardEnemy extends GameObject{

	private Handler handler;
	
	public HardEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX = 7;
		velY = 7;

		this.handler = handler;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32){
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 16){
			velX *= -1;
		}
		
		handler.addObject(new BasicTrail(x, y, ID.BasicTrail, 16, 16, 0.02f, Color.yellow, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y,16,16);
	}

}
