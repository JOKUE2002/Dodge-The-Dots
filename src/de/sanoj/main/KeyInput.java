package de.sanoj.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import de.sanoj.main.Game.STATE;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	private Game game;
	
	public KeyInput(Handler handler, Game game){
		this.handler = handler;
		this.game = game;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i<handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Player){
				
				if(key == KeyEvent.VK_W){
					tempObject.setVELY(-HUD.getSpeed());
//					keyDown[0] = true;
				}
				if(key == KeyEvent.VK_A){
					tempObject.setVELX(-HUD.getSpeed());
//					keyDown[1] = true;
				}
				if(key == KeyEvent.VK_S){
					tempObject.setVELY(HUD.getSpeed());
//					keyDown[2] = true;
				}
				if(key == KeyEvent.VK_D){
					tempObject.setVELX(HUD.getSpeed());
//					keyDown[3] = true;
				}
				
			}
			game.window.requestFocus();
		}
		
		if(key == KeyEvent.VK_P && game.getGameState() == STATE.Game){
			Game.paused = !Game.paused;
		}
		
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
		if(key == KeyEvent.VK_SPACE){
			if(game.getGameState() == STATE.Game){
				game.setGameState(STATE.Shop);
			}else if(game.getGameState() == STATE.Shop){
				game.setGameState(STATE.Game);
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i<handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getID() == ID.Player){
				
				if(key == KeyEvent.VK_W){
					tempObject.setVELY(0);
//					keyDown[0] = false;
				}
				if(key == KeyEvent.VK_A){
					tempObject.setVELX(0);
//					keyDown[1] = false;
				}
				if(key == KeyEvent.VK_S){
					tempObject.setVELY(0);
//					keyDown[2] = false;
				}
				if(key == KeyEvent.VK_D){
					tempObject.setVELX(0);
//					keyDown[3] = false;
				}
				
//				if(!keyDown[0] && !keyDown[2]){
//					tempObject.setVELY(0);
//				}
//				if(!keyDown[1] && !keyDown[3]){
//					tempObject.setVELX(0);
//				}
				
			}
		}
	}
}
