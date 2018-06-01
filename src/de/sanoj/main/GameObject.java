package de.sanoj.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x,y;
	protected ID id;
	protected float velX, velY;
	
	public GameObject(float x, float y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract Rectangle getBounds();
	public abstract void render(Graphics g);
	
	/**
	 * SETTERS AND GETTERS
	 */
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public void setID(ID id){
		this.id = id;
	}
	
	public void setVELX(float vx){
		this.velX = vx;
	}
	
	public void setVELY(float vy){
		this.velY = vy;
	}
	
	public float getVELY(){
		return this.velY;
	}
	
	public float getVELX(){
		return this.velX;
	}
	
	public ID getID(){
		return this.id;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	
	
}
