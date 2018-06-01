package de.sanoj.main;

import java.util.Random;

public class Spawner {

	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	private Random r = new Random();
	
	public Spawner(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		scoreKeep++;
		if(scoreKeep >= 180){
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			if(Game.diff == 0){
				for(int i = 0; i < 100; i++){
					if(hud.getLevel() == 2 && i == 0){
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
					}else if(hud.getLevel() == 2 + 13*i && hud.getLevel() >= 15){
						noboss();
						hud.setBoss(false);
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
					}else if(hud.getLevel() == 3 + 13*i){
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
					}else if(hud.getLevel() == 4 + 13*i){
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
					}else if(hud.getLevel() == 5 + 13*i){
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
					}else if(hud.getLevel() == 6 + 13*i){
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
					}else if(hud.getLevel() == 7 + 13*i){
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
					}else if(hud.getLevel() == 8 + 13*i){
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
					}else if(hud.getLevel() == 11 + 13*i){	
						boss();
						hud.setBoss(true);
					}
				}
			}else if(Game.diff == 1){
				for(int i = 0; i < 100; i++){
					if(hud.getLevel() == 2 && i == 0){
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
					}else if(hud.getLevel() == 2 + 13*i && hud.getLevel() >= 15){
						noboss();
						hud.setBoss(false);
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
					}else if(hud.getLevel() == 3 + 13*i){
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
					}else if(hud.getLevel() == 4 + 13*i){
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));
					}else if(hud.getLevel() == 5 + 13*i){
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
					}else if(hud.getLevel() == 6 + 13*i){
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
					}else if(hud.getLevel() == 7 + 13*i){
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
					}else if(hud.getLevel() == 8 + 13*i){
						handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
					}else if(hud.getLevel() == 11 + 13*i){	
						boss();
						hud.setBoss(true);
					}
				}
			}
		}
	}
	
	private void boss(){
		handler.clearEnemys();
		handler.addObject(new BossEnemy_Body((Game.WIDTH / 2)-48, -120, ID.BossEnemy, handler));
	}
	
	private void noboss(){
		handler.clearEnemys();
		if(Game.diff == 0){
//			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
		}else if(Game.diff == 1){
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler));	
		}
	}
}
