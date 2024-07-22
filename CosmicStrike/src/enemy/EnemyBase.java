package enemy;

import java.awt.*;
import java.util.Random;

import game.*;

public abstract class EnemyBase {
	
	public Image image;
    
    public int x, y;
    
    public int width;
    public int height;
    
    public int hp;
    
    int speed;
    
    public int coinCount;
    
    Random random = new Random();
    int direction;
    
    public EnemyBase() {
    	direction = random.nextInt(4);

        switch (direction) {
            case 0: 
                x = (int)(Math.random() * Main.SCREEN_WIDTH);
                y = 0;
                break;
            case 1: 
                x = (int)(Math.random() * Main.SCREEN_WIDTH);
                y = Main.SCREEN_HEIGHT;
                break;
            case 2:
                x = 0;
                y = (int)(Math.random() * Main.SCREEN_HEIGHT);
                break;
            case 3: 
                x = Main.SCREEN_WIDTH;
                y = (int)(Math.random() * Main.SCREEN_HEIGHT);
                break;
        }
    }

    public abstract void move();
    
    public void draw(Graphics g) {
    	g.drawImage(image, x, y , null);
    }
    
    public void remove() {
    	
	}
}

