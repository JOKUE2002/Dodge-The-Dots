package de.sanoj.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import de.sanoj.main.Game.STATE;

public class Shop extends MouseAdapter implements KeyListener {

	private Handler handler;
	private HUD hud;
	Game game;
	public static int B1 = 400;
	public static int B2 = 200;
	public static int B3 = 200;

	public static boolean B1available = true;
	public static boolean B2available = true;
	public static boolean B3available = true;
	
	public void reset(){
		B1 = 400;
		B2 = 200;
		B3 = 200;
		B1available = true;
		B2available = true;
		B3available = true;
		shoppingCountdown1 = 5;
		shoppingCountdown2 = 4;
	}

	public Shop(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}

	public void tick() {
		if (shoppingCountdown1 <= 0) {
			B1available = false;
		}
		if (shoppingCountdown2 <= 0) {
			B2available = false;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("ARIAL", 0, 48));
		g.drawString("SHOP", Game.WIDTH / 2 - 100, 50);

		g.setFont(new Font("ARIAL", 0, 12));

		// BOX 1
		g.drawRect(100, 100, 100, 80);
		g.drawString("Upgrade + Refill", 105, 120);
		g.drawString("Health", 105, 140);
		g.drawString("Costs: " + B1, 110, 160);

		// BOX 2
		g.drawRect(250, 100, 100, 80);
		g.drawString("Upgrade Speed", 260, 120);
		g.drawString("Costs: " + B2, 260, 140);

		if (!B1available) {
			g.setColor(Color.RED);
			g.drawString("SOLD OUT", 105, 130);
			g.setColor(Color.WHITE);
		}

		if (!B2available) {
			g.setColor(Color.RED);
			g.drawString("SOLD OUT", 260, 130);
			g.setColor(Color.WHITE);
		}

		// BOX 3
		g.drawRect(400, 100, 100, 80);
		g.drawString("Refill Health", 410, 120);
		g.drawString("Costs: " + B3, 410, 140);

		// money
		g.drawString("SCORE: " + hud.getScore(), Game.WIDTH / 2 - 50, 300);
		g.drawString("Press Space to get back", Game.WIDTH / 2 - 50, 330);
	}

	int shoppingCountdown1 = 5;
	int shoppingCountdown2 = 4;

	public void mouseClicked(MouseEvent e) {
		if (Game.gameState == STATE.Shop) {
			int x = e.getX();
			int y = e.getY();

			if (y >= 100 && y <= 180) {
				// BOX 1
				if (x >= 100 && x <= 200) {
					if (hud.getScore() >= B1) {
						shoppingCountdown1 = (int) Game.clamp(shoppingCountdown1 - 1, 0, 5);
						tick();
						if (B1available) {
							hud.setScore(hud.getScore() - B1);
							HUD.MAXHEALTH += 50;
							HUD.HEALTH = HUD.MAXHEALTH;
							B1 *= 2f;
							HUD.IMMUNETIMER = 10;
							HUD.IMMUNE = true;
						}
					}
				}
				// BOX 2
				if (x >= 250 && x <= 350) {
					if (hud.getScore() >= B2) {
						shoppingCountdown2 = (int) Game.clamp(shoppingCountdown2 - 1, 0, 4);
						if (shoppingCountdown2 <= 0) {
							B2available = false;
						}
						if (B2available) {
							hud.setScore(hud.getScore() - B2);
							hud.setSpeed(hud.getSpeed() * 2f);
							B2 *= 2f;
							HUD.IMMUNETIMER = 10;
							HUD.IMMUNE = true;
						}
					}
				}
				// BOX 3
				if (x >= 400 && x <= 500) {
					if (hud.getScore() >= B3) {
						hud.setScore(hud.getScore() - B3);
						HUD.HEALTH = HUD.MAXHEALTH;
						B3 *= 2f;
						HUD.IMMUNETIMER = 10;
						HUD.IMMUNE = true;
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

		if (e.getKeyChar() == '1') {
			if (hud.getScore() >= B1) {
				shoppingCountdown1 = (int) Game.clamp(shoppingCountdown1 - 1, 0, 5);
				tick();
				if (B1available) {
					hud.setScore(hud.getScore() - B1);
					HUD.MAXHEALTH += 50;
					HUD.HEALTH = HUD.MAXHEALTH;
					B1 *= 2f;
					HUD.IMMUNETIMER = 10;
					HUD.IMMUNE = true;
				}
			}
		}
		if (e.getKeyChar() == '2') {
			if (hud.getScore() >= B2) {
				shoppingCountdown2 = (int) Game.clamp(shoppingCountdown2 - 1, 0, 4);
				if (shoppingCountdown2 <= 0) {
					B2available = false;
				}
				if (B2available) {
					hud.setScore(hud.getScore() - B2);
					hud.setSpeed(HUD.getSpeed() * 2f);
					B2 *= 2f;
					HUD.IMMUNETIMER = 10;
					HUD.IMMUNE = true;
				}
			}
		}
		if (e.getKeyChar() == '3') {
			if (hud.getScore() >= B3) {
				hud.setScore(hud.getScore() - B3);
				HUD.HEALTH = HUD.MAXHEALTH;
				B3 *= 2f;
				HUD.IMMUNETIMER = 10;
				HUD.IMMUNE = true;
			}
		}
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}
