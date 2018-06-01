package de.sanoj.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import de.sanoj.main.Game.STATE;

public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public int x,y;
	
	public void render(Graphics g){
		if(game.getGameState() == STATE.Menu){
			Font fnt = new Font("arial",1,50);
			Font fnt2 = new Font("arial",1,30);
		
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("MENU", 240, 70);
			
			g.setFont(fnt2);
			g.drawString("PLAY", 270, 195);
			g.drawString("HELP", 270, 295);
			g.drawString("QUIT", 270, 395);
			
			g.drawRect(210, 150, 200, 64);
			
			g.drawRect(210, 250, 200, 64);
			
			g.drawRect(210, 350, 200, 64);
		}else if(game.getGameState() == STATE.Help){
			Font fnt = new Font("arial",1,50);
			Font fnt2 = new Font("arial",1,30);
		
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("HELP", 240, 70);
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("BACK", 270, 395);
			g.drawString("Use the Mouse to navigate the player", 10, 105);
			g.drawString("Dodge the enemies", 10, 205);
			g.drawString("(boxes with other colors)", 100, 255);
//			
		}else if(game.getGameState() == STATE.End){
			Font fnt = new Font("arial",1,50);
			Font fnt2 = new Font("arial",1,30);
		
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 240, 70);
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("RETRY", 270, 395);
			g.drawString("You lost with a Score of: "+hud.getScore() , 10, 105);
			g.drawString("Level: "+hud.getLevel()+" - "+game.getDiff(), 10, 205);
//			g.drawString("( boxes with another color )", 100, 255);
//			
		}else if(game.getGameState() == STATE.Select){
			Font fnt = new Font("arial",1,50);
			Font fnt2 = new Font("arial",1,30);
		
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SETTINGS", 240, 70);
			g.setFont(fnt2);
			g.setFont(fnt2);
			g.drawString("EASY", 270, 195);
			g.drawString("HARD", 270, 295);
			g.drawString("BACK", 270, 395);
			g.drawRect(210, 150, 200, 64);
			
			g.drawRect(210, 250, 200, 64);
			
			g.drawRect(210, 350, 200, 64);
//			
		}
	}
	
	public void tick(){
		
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(game.getGameState() == STATE.Menu){
			
			//play
			if(mouseOver(mx, my, 210, 150, 200, 64)){
//				game.gameState = STATE.Game;
				
//				System.out.println("GAME");
				
				
//				handler.addObject(new Player(Game.WIDTH / 2 - 32,Game.HEIGHT / 2 - 32,ID.Player, handler));
//				handler.clearEnemys();
//				handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.BasicEnemy, handler));
				Game.gameState = STATE.Select;
				return;
			}
		}
		
		if(game.getGameState() == STATE.Select){
			
			//easy
			if(mouseOver(mx, my, 210, 150, 200, 64)){
				game.setGameState(STATE.Game);
				
				System.out.println("GAME");
				
				
				handler.addObject(new Player(Game.WIDTH / 2 - 32,Game.HEIGHT / 2 - 32,ID.Player, handler,game));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.BasicEnemy, handler));
				Game.diff = 0;
				game.reset();
				return;
			}
			
			//back
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.setGameState(STATE.Menu);
				return;
			}
			
			//hard
			if(mouseOver(mx, my, 210, 250, 200, 64)){
				game.setGameState(STATE.Game);
				
				System.out.println("GAME");
				
				
				handler.addObject(new Player(Game.WIDTH / 2 - 32,Game.HEIGHT / 2 - 32,ID.Player, handler,game));
				handler.clearEnemys();
				handler.addObject(new HardEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.HardEnemy, handler));
				Game.diff = 1;
				game.reset();
				return;
			}
		}
		
		if(game.getGameState() == STATE.End){
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.setGameState(STATE.Select);
				
				hud.setLevel(1);
				hud.setScore(0);
				
			}
		}
		
		//quit
		if(mouseOver(mx, my, 210, 350, 200, 64) && game.getGameState() == STATE.Menu){
			System.exit(0);
			return;
		}
		
		//help
		if(mouseOver(mx, my, 210, 250, 200, 64) && game.getGameState() == STATE.Menu){
			game.setGameState(STATE.Help);
			return;
		}
		
		//back
		if(mouseOver(mx, my, 210, 350, 200, 64) && game.getGameState() == STATE.Help){
			game.setGameState(STATE.Menu);
			return;
		}
		
		
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int w, int h){
		if(mx > x && mx < x + w){
			if(my > y && my < y + h){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

}
