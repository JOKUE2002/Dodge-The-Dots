package de.sanoj.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BasicTrail extends GameObject{

	private float alpha = 1;
	private Handler handler;
	private Color color;
	private float width,height;
	private float life;
	
	public BasicTrail(float x, float y, ID id, float width, float height, float life, Color c,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = c;
		this.width = width;
		this.height = height;
		this.life = life;
	}

	@Override
	public void tick() {
		if(alpha > life){
			alpha -= life - 0.001f;
		}else{
			handler.removeObject(this);
		}
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha);
	}

}
