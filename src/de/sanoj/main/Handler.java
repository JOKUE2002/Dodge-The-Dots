package de.sanoj.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	Game game;
	
	public Handler(Game game){
		this.game = game;
	}
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject go){
		this.object.add(go);
	}
	
	public void removeObject(GameObject go){
		this.object.remove(go);
	}
	
	public void clearEnemys(){
		for(int i = 0; i < object.size(); i++){
			GameObject temp = object.get(i);
			
			if(temp.getID() == ID.Player){
				object.clear();
				if(Game.gameState != Game.STATE.End){
					addObject(new Player(temp.x, temp.y, ID.Player, this,game));
				}
			}
		}
	}
}
