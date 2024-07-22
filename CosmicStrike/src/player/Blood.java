package player;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import game.*;

public class Blood {
	private Image image = new ImageIcon("images/blood.png").getImage();
    
    public int x, y;
    
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    
    int speedX, speedY;
    
    Random random = new Random();
    
    public int cnt = 0;
    
    public Blood() {

    	this.x = GameManager.player.x; // 코인이 생성될 시작 위치 (가운데)
        this.y = GameManager.player.y;
        
        speedX = random.nextInt(5) - 2; // X 방향 속도 (-2부터 2까지 랜덤)
        speedY = random.nextInt(5) - 2;  
    }

    public void move() {
    	if (cnt > 10) return;
    	
        x += speedX;
        y += speedY;
        
        cnt++;
    }
    
    public void draw(Graphics g) {
    	g.drawImage(image, x, y, null);
    }
}
