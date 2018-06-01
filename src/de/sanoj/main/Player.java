package de.sanoj.main;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Player extends GameObject{

	Handler handler;
	private BufferedImage playerImage;
	Game game;
	float speed = 0.75f;

	public Player(float x, float y, ID id,Handler handler, Game game) {
		super(x, y, id);

		this.game = game;
		velX = 0;
		velY = 0;

		this.handler = handler;
		SpriteSheet spriteSheet = new SpriteSheet(Game.spriteSheet);
		playerImage = spriteSheet.grabImage(1,1,32,32);
	}

	@Override
	public void tick() {
		//move();
		x += velX;
		y += velY;

		//		System.out.println(velX);
		//		System.out.println(velY);

		if(HUD.getBoss()){
			if(y <= 200){
				y = 200;
			}
		}

		x = Game.clamp(x,0,Game.WIDTH - 32);
		y = Game.clamp(y, 0, Game.HEIGHT - 52);

		//		handler.addObject(new BasicTrail(x, y, ID.BasicTrail, 32, 32, 0.09f, Color.white, handler));

		collision();
		if(HUD.IMMUNE){
			HUD.IMMUNETIMER -= 1;
		}
	}


	private void move() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		p = new Point((p.x - game.window.frame.getLocation().x), (p.y - game.window.frame.getLocation().y));

		float x1 = p.x - 20;
		float y1 = p.y - 32;

		//        System.out.println(x1);
		//        System.out.println(y1);

		float diffX = x - x1 - 8;
		float diffY = y - y1 - 8;

		float dist = (float) Math.sqrt( (x-x1) * (x-x1) + (y-y1) * (y-y1) );
		float usedDist = (float) (dist * HUD.getSpeed());
		velX = ((-1/usedDist) * diffX );
		velY = ((-1/usedDist) * diffY );
	}

	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempGO = handler.object.get(i);

			if(tempGO.getID() == ID.BasicEnemy || tempGO.getID() == ID.FastEnemy || tempGO.getID() == ID.SmartEnemy || tempGO.getID() == ID.BossEnemy || tempGO.getID() == ID.HardEnemy){
				if(getBounds().intersects(tempGO.getBounds())){
					if(!HUD.IMMUNE){
						HUD.HEALTH -= 2;
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {

		//		Graphics2D g2d = (Graphics2D) g;

		//		g.setColor(Color.red);
		//		g2d.draw(getBounds());

		//		g.setColor(Color.white);
		//		g.fillRect((int)x,(int)y,32,32);
		g.drawImage(playerImage, (int)x, (int)y, null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y,32,32);
	}

}
