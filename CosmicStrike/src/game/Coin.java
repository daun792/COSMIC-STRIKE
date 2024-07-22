package game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Coin {
	Image image = new ImageIcon("images/coin.png").getImage();
    
    int x, y;
    
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    
    int speedX, speedY;
    
    Random random = new Random();
    
    int cnt = 0;
    
    public Coin(int x, int y) {
    	this.x = x; 
        this.y = y;

        speedX = random.nextInt(5) - 2; 
        speedY = random.nextInt(5) - 2;
        
    }

    public void move() {
    	if (cnt > 20) return;
    	
        x += speedX;
        y += speedY;
        
        if (y < 0 || y + height > Main.SCREEN_HEIGHT) y *= -1;
        if (x < 0 || x + width > Main.SCREEN_WIDTH) x *= -1;
        
        cnt++;
    }
}
