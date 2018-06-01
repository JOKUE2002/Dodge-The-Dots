package de.sanoj.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static float HEALTH = 100;
	public static float MAXHEALTH = 100;
	private static float speed = 0.75f;
	private int score = 500;
	private int level = 1;
	public static boolean BOSS = false;
	public static boolean IMMUNE = false;
	public static int IMMUNETIMER = 10;

	private float greenValue = 255;
	
	public void reset(){
		speed = 0.75f;
		score = 500;
		level = 1;
		BOSS = false;
		IMMUNE = false;
		IMMUNETIMER = 10;
		HEALTH = 100;
		MAXHEALTH = 100;
	}

	public void tick() {

		HEALTH = Game.clamp(HEALTH, 0, MAXHEALTH);
		greenValue = HEALTH * 2;
		greenValue = Game.clamp(greenValue, 0, 255);
		score++;
		if (IMMUNETIMER <= 0) {
			IMMUNE = false;
			IMMUNETIMER = 10;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, (int) MAXHEALTH * 2, 32);

		g.setColor(new Color(75, (int) greenValue, 0));
		g.fillRect(15, 15, (int) HEALTH * 2, 32);

		g.setColor(Color.white);
		g.drawRect(15, 15, (int) MAXHEALTH * 2, 32);

		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
		g.drawString("Space for Shop!", 15, 94);
		String tmp = "Shopoffers: ";
		if(Shop.B1available){
			tmp += Shop.B1 + "; ";
		}else{
			tmp += "SOLD OUT " + "; ";
		}
		if(Shop.B2available){
			tmp += Shop.B2 + "; ";
		}else{
			tmp += "SOLD OUT " + "; ";
		}
		tmp += Shop.B3;
//		"Shopoffers: " + Shop.B1 + "; " + Shop.B2 + "; " + Shop.B3
		g.drawString(tmp, 15, 10);
	}

	/**
	 * SETTERS AND GETTERS
	 */

	public void setScore(int score) {
		this.score = score;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getScore() {
		return this.score;
	}

	public int getLevel() {
		return this.level;
	}

	public static boolean getBoss() {
		return BOSS;
	}

	public void setBoss(boolean boss) {
		this.BOSS = boss;
	}

	public static float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
