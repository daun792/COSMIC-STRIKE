package game;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import enemy.*;
import player.*;
import scenes.*;

public class GameManager extends Thread {
    private int delay = 20;
    private long pretime;
    private int cnt;
    
    private int score;
    public static int money;
    public static int wealth;
    
    public static int currentHp = 0;
    public static int maxHp = 0;

    private boolean up, down, left, right;
    public static boolean isRun = true;
    
    private ArrayList<Coin> coinList = new ArrayList<Coin>(); 
    private Coin coin;
    
    public static Rectangle2D playerBounds;
    
    public static Player player = new Player();
    public static Window window = new Window();
    public static EnemyManager enemyManager = new EnemyManager();
    public static Weapon weapon = new Weapon();
    
    private Audio getCoin = new Audio("audio/getCoinSFX.wav", false);
    
    private Rectangle2D coinBounds;
    
    
    @Override
    public void run() {

        reset();
        while (true) {
            while (isRun) {
                pretime = System.currentTimeMillis();
                if (System.currentTimeMillis() - pretime < delay) {
                    try {
                    	
                    	if (currentHp <= 0 || (Main.SCREEN_WIDTH < 35 && Main.SCREEN_HEIGHT < 35)) {
                    		SceneManager.gameScene.gameOver();
                    		Main.dataManager.score = score;
                    	}
                    	
                    	if (cnt % 1000 == 0)
                    		System.gc();

                        Thread.sleep(delay - System.currentTimeMillis() + pretime);
                        
                        keyProcess();
                        window.windowProcess();
                        player.playerMoveProcess();
                        enemyManager.appearProcess();
                        enemyManager.moveProcess();
                        weapon.moveProcess();
                        coinProcess();
                        
                        cnt++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void reset() {
    	cnt = 0;
    	
    	score = 0;
        money = 0;
        wealth = 1;
        maxHp = currentHp = 10;
        
        up = down = left = right = false;
        
        isRun = true;
        
        player.init();

        coinList.clear();
        
        Bullet.speed = 25;
        Player.speed = 10;
        
        Main.dataManager.score = -1;
        Main.itemManager.init();
        
        window.init();
        enemyManager.init();
        weapon.init();
        
        coinBounds = null;
    }
    
    public void enemyDie(int _x, int _y, int _coinCount) {
    	for (int i = 0; i <_coinCount * wealth; i++) {
    		coinList.add(new Coin(_x, _y));
    	}
    	
    	score += 10;
    }

    private void keyProcess() {
        if (up) player.moveProcess(0);
        if (down) player.moveProcess(1);
        if (left) player.moveProcess(2);
        if (right) player.moveProcess(3);
    }
    
    private void coinProcess() {
    	for (int i = 0; i < coinList.size(); i++) {
    		coin = coinList.get(i);
    		coin.move();
    		
    		coinBounds = new Rectangle2D.Double(coin.x - 5, coin.y - 5, coin.width + 5, coin.height + 5);
    		if (coinBounds.intersects(playerBounds)) {
    			getCoin.start();
                money++;
                coinList.remove(coin);
            }
    	}
    }
    
    public void gameDraw(Graphics g) {
    	player.draw(g);
        weapon.draw(g);
        enemyManager.draw(g);
        coinDraw(g);
        infoDraw(g);
    }

    public void coinDraw(Graphics g) {
    	for (int i = 0; i < coinList.size(); i++) {
    		coin = coinList.get(i);
    		g.drawImage(coin.image, coin.x, coin.y, null);
    	}
    }
    
    public void infoDraw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("SCORE : " + score, 5, 15);
        g.drawString("COIN : " + money, 5, 25);
        g.drawString(currentHp + " / " + maxHp, Main.SCREEN_WIDTH - 45, 15);
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}