package player;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import game.*;

public class BrokenBullet {
	private Image image = new ImageIcon("images/bullet_broken.png").getImage();
    
    int x, y;
    
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    
    int speedX, speedY;
    
    Random random = new Random();
    
    public int cnt = 0;
    
    public BrokenBullet(int x, int y) {

    	this.x = x;
        this.y = y;
        
        speedX = random.nextInt(15) - 2;
        speedY = random.nextInt(15) - 2;
    }

    public void move() {
    	if (cnt > 5) return;
    	
        x += speedX;
        y += speedY;
        
        if (y < 0 || y + height > Main.SCREEN_HEIGHT) y *= -1;
        if (x < 0 || x + width > Main.SCREEN_WIDTH) x *= -1;
        
        cnt++;
    }
    
    public void draw(Graphics g) {
    	g.drawImage(image, x, y, null);
    }
}
