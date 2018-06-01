package de.sanoj.main;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import de.sanoj.main.Game.STATE;

public class KeyInputNew {
	
	Handler handler;
	Game game;

	public KeyInputNew(Game game, Handler handler) {
		this.handler = handler;
		this.game = game;
		
		game.btn.getInputMap().put(KeyStroke.getKeyStroke("W"),
				"pressedW");
		game.btn.getInputMap().put(KeyStroke.getKeyStroke("W"),
				"releasedW");
		game.btn.getActionMap().put("pressedW", pressedW);
		game.btn.getActionMap().put("releasedW", releasedW);
		
		game.btn.getInputMap().put(KeyStroke.getKeyStroke("S"),
				"pressedS");
		game.btn.getInputMap().put(KeyStroke.getKeyStroke("S"),
				"releasedS");
		game.btn.getActionMap().put("pressedS", pressedS);
		game.btn.getActionMap().put("releasedS", releasedS);
		
		game.btn.getInputMap().put(KeyStroke.getKeyStroke("A"),
				"pressedA");
		game.btn.getInputMap().put(KeyStroke.getKeyStroke("A"),
				"releasedA");
		game.btn.getActionMap().put("pressedA", pressedA);
		game.btn.getActionMap().put("releasedA", releasedA);
		
		game.btn.getInputMap().put(KeyStroke.getKeyStroke("D"),
				"pressedD");
		game.btn.getInputMap().put(KeyStroke.getKeyStroke("D"),
				"releasedD");
		game.btn.getActionMap().put("pressedD", pressedD);
		game.btn.getActionMap().put("releasedD", releasedD);
	}
	
	/**
	 * W
	 */

	Action pressedW = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i<handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Player){
					tempObject.setVELY(-5);
				}
			}
		}
	};

	Action releasedW = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i<handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Player){
					tempObject.setVELY(0);
				}
			}
		}
	};
	
	/**
	 * S
	 */
	Action pressedS = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i<handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Player){
					tempObject.setVELY(5);
				}
			}
		}
	};

	Action releasedS = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i<handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Player){
					tempObject.setVELY(0);
				}
			}
		}
	};
	
	/**
	 * A
	 */
	Action pressedA = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i<handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Player){
					tempObject.setVELX(-5);
				}
			}
		}
	};

	Action releasedA = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i<handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Player){
					tempObject.setVELY(0);
				}
			}
		}
	};
	
	/**
	 * D
	 */
	Action pressedD = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i<handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Player){
					tempObject.setVELY(-5);
				}
			}
		}
	};

	Action releasedD = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i<handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getID() == ID.Player){
					tempObject.setVELY(0);
				}
			}
		}
	};
}
