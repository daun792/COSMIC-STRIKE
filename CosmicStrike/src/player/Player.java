package player;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import game.*;

public class Player {
	
	private Image player = new ImageIcon("images/player.png").getImage();
	
	public int x, y;
	
    public int width = player.getWidth(null);
    public int height = player.getHeight(null);
    
    public static int speed = 10;
    
    private ArrayList<Blood> bloodList = new ArrayList<Blood>();
    private Blood blood;
    
    private Audio playerHit = new Audio("audio/playerHitSFX.aiff", false);
    
    public Player() {
    	x = (Main.SCREEN_WIDTH - width) / 2;
    	y = (Main.SCREEN_HEIGHT - height) / 2;
    }
    
    public void init() {
    	bloodList.clear();
    	x = (Main.SCREEN_WIDTH - width) / 2;
    	y = (Main.SCREEN_HEIGHT - height) / 2;
    }
    
    public void moveProcess(int type) {
    	
    	switch (type) {
        	case 0: 
        		y -= speed;
        		break;
        	case 1:
        		y += speed;
        		break;
        	case 2:
        		x -= speed;
        		break;
        	case 3:
        		x += speed;
        		break;
    	}
    	
    	if (y < 0) y = 0;
    	else if (y + height > Main.SCREEN_HEIGHT) y = Main.SCREEN_HEIGHT - height;
        if (x < 0) x = 0;
        else if (x + width > Main.SCREEN_WIDTH) x = Main.SCREEN_WIDTH - width;
    }
    
    public void playerMoveProcess() {
    	GameManager.playerBounds = new Rectangle2D.Double(x, y, width, height);
    	
    	for (int i = 0; i < bloodList.size(); i++) {
        	blood = bloodList.get(i);
        	blood.move();
        	
        	if (blood.cnt > 5)
        		bloodList.remove(blood);
        }
    }
    
    
    public void draw(Graphics g) {
    	g.drawImage(player, x, y, null);
    	
    	for (int i = 0; i < bloodList.size(); i++) {
        	blood = bloodList.get(i);
        	blood.draw(g);
        }
    }
    
    public void playerAttacked() {
    	playerHit.start();
    	GameManager.currentHp--;
    	for (int i = 0; i < 5; i++) 
    		bloodList.add(new Blood());
    }
    
    public boolean checkLaserHitPlayer(Line2D _laser) {
        if (_laser.intersects(GameManager.playerBounds)) {
            playerAttacked();
            return true;
        }
        else return false;
    }
}
