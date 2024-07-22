package enemy;

import javax.swing.*;
import java.awt.*;

import game.*;

public class EnemyBullet {
	private Image image = new ImageIcon("images/enemy_bullet.png").getImage();
    
    public int x, y;
    
    private int speedX, speedY;
    
    public int width = image.getWidth(null);
    public int height = image.getHeight(null);
    
    private int speed = 5;

    public EnemyBullet(int _x, int _y) {
    	
    	double angle = Math.atan2(GameManager.player.y - _y, GameManager.player.x - _x);

	    speedX = (int) (speed * Math.cos(angle));
	    speedY = (int) (speed * Math.sin(angle));
	    
	    x = _x + 27;
	    y = _y + 27;
    }
    
    public EnemyBullet(int _x, int _y, double _angle) {
    	speedX = (int) (speed * Math.cos(_angle));
	    speedY = (int) (speed * Math.sin(_angle));
	    
	    x = _x + 56;
	    y = _y + 56;
    }

    public void fire() {
        this.x += speedX;
        this.y += speedY;
    }
    
    public void draw(Graphics g) {
    	g.drawImage(image, x, y, null);
    }
}
