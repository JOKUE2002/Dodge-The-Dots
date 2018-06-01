package de.sanoj.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy_Body extends GameObject{

	private Handler handler;
	private int timer = 100, timer2 = 80;
	private Random r = new Random();	

	public BossEnemy_Body(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		velX = 0;
		velY = 2;

		this.handler = handler;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
/*		if(y <= 0 || y >= Game.HEIGHT - 32){
			velY *= -1;
		}*/
		if(x <= 0 || x >= Game.WIDTH - 96){
			velX *= -1;
		}
		
		if(timer <= 0){
			velY = 0;
		}else{
			timer--;
		}
		
		if(timer <= 0){
			timer2--;
		}
		
		if(timer2 <= 0){
			
			if(velX < 0){
				velX -= 0.01f;
			}else if(velX >0){
				velX += 0.01f;
			}
			
			velX = Game.clamp(velX, -5, 5);
			
			if(velX == 0){
				velX = 2;
			}
			int spawn = r.nextInt(10);
			if(spawn == 0){
				handler.addObject(new BossEnemy_Bullet(x + 48, y + 48, ID.BasicEnemy, handler));
			}
		}
		
		handler.addObject(new BasicTrail(x, y, ID.BasicTrail, 64, 64, 0.02f, Color.red, handler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 64, 64);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y,64,64);
	}

}
