package player;

import javax.swing.*;
import java.awt.*;

import game.*;

public class Bullet {
	
    private Image image = new ImageIcon("images/bullet.png").getImage();
    
    public int x, y;
    
    private int speedX, speedY;
    
    public int width = image.getWidth(null);
    public int height = image.getHeight(null);
    
    public int power = 5;
    public static int speed = 25;

    public Bullet(double _angle) {
    	
	    speedX = (int) (speed * Math.cos(_angle));
	    speedY = (int) (speed * Math.sin(_angle));
	    
	    x = GameManager.player.x + 15;
	    y = GameManager.player.y + 15;
    }

    public void fire() {
        this.x += speedX;
        this.y += speedY;
    }
    
    public boolean checkBulletHitWindow() {
    	 if (y < 0) {
    		GameManager.window.BulletHitWindow(0);
         	return true;
         }
         if (y + height > Main.SCREEN_HEIGHT) {
        	GameManager.window.BulletHitWindow(1);
         	return true;
         }
         if (x < 0) {
        	GameManager.window.BulletHitWindow(2);
         	return true;
         }
         if (x + width > Main.SCREEN_WIDTH) {
        	GameManager.window.BulletHitWindow(3);
         	return true;
         }
         
         return false;
    }
    
    public void draw(Graphics g) {
    	g.drawImage(image, x, y, null);
    }
}