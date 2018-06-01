package de.sanoj.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy_Bullet extends GameObject{

	private Handler handler;
	private Random r = new Random();
	
	public BossEnemy_Bullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX = r.nextInt(20)-10;
		velY = 4;

		this.handler = handler;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
/*		if(y <= 0 || y >= Game.HEIGHT - 32){
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 16){
			velX *= -1;
		}*/
		
		if(y >= Game.HEIGHT){
			handler.removeObject(this);
		}
		
		handler.addObject(new BasicTrail(x, y, ID.BasicTrail, 16, 16, 0.02f, Color.red, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y,16,16);
	}

}
