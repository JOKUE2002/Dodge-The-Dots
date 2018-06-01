package de.sanoj.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JButton;

public class Game extends Canvas implements Runnable{

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; 
	public static final String NAME = " DODGE THE DOTS ";
	public static final String VERSION = "1.7.1";
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r;
	public HUD hud;
	public Spawner spawner;
	public Menu menu;
	public static boolean paused = false;
	public static  int diff = 0;
	// 0 = easy; 1 = hard
	public static BufferedImage spriteSheet;
	private Shop shop;
	public JButton btn;
	public Window window;
	
	public enum STATE {
		Menu,
		Help,
		Game,
		Select,
		Shop,
		End
	};
	
	public static STATE gameState = STATE.Menu;
	
	public void menu(){
		for(int i = 0; i < 10; i++){
			handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));
		}
	}
	
	public Game() {
		handler = new Handler(this);
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler,this));
		this.addMouseListener(menu);
		window = new Window(WIDTH,HEIGHT,getTitle(),this);
		BufferedImageLoader loader = new BufferedImageLoader();
		spriteSheet = loader.loadImage("/img_game.png");
		r = new Random();
		spawner = new Spawner(handler, hud);
		shop = new Shop(handler,hud,this);
		this.addMouseListener(shop);
		this.addKeyListener(shop);
		if(gameState == STATE.Game){
			handler.addObject(new Player(WIDTH / 2 - 32,HEIGHT / 2 - 32,ID.Player, handler, this));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
		}else{
			for(int i = 0; i < 10; i++){
				handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));
			}
		}
		
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception x){
			x.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
//				this.requestFocus();
//				window.requestFocus();
				delta--;
			}
			if(running){
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				System.out.println("Enities:" + handler.object.size());
				frames = 0;
			}
		}
		stop();
	}
	
	private String getTitle(){
		return NAME + " - " + VERSION;
	}
	
	private void tick(){
		
		if(gameState == STATE.Game){
			
			if(!paused){
				hud.tick();
				spawner.tick();
				handler.tick();
				if(hud.HEALTH <= 0){
					HUD.HEALTH = 100;
					handler.clearEnemys();
					gameState = STATE.End;
					menu();
				}
				
				
			}
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
			menu.tick();
			handler.tick();
		}else if(gameState == STATE.Shop){
			shop.tick();
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(paused){
			g.drawString("GAME PAUSED", 100, 100);
		}
		
		if(gameState == STATE.Game){
			hud.render(g);
			handler.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
			handler.render(g);
			menu.render(g);
		}else if(gameState == STATE.Shop){
			shop.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max){
		if(var >= max){
			return var = max;
		}else if(var <= min){
			return var = min;
		}else{
			return var;
		}
	}

	public String getDiff() {
		if(diff == 0){
			return "easy";
		}else{
			return "hard";
		}
	}

	public STATE getGameState() {
		return gameState;
	}

	public void setGameState(STATE gs) {
		gameState = gs;
	}
	
	public void reset(){
		shop.reset();
		hud.reset();
	}

}
