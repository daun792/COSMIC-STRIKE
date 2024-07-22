package game;

import javax.swing.*;
import java.util.Random;

public class Window extends JFrame {
	
	Random random = new Random();
   
    private int currentX;
    private int currentY;
    private int currentWidth;
    private int currentHeight;
    
    private int speed = 1;
    public static int power = 10;
    private int tempPower = 10;
     
    public void init() {
    	power = tempPower = 10;
    }
    
    public void windowProcess() {
    	int direction = random.nextInt(4);

        GetValues();

        switch (direction) {
            case 0: 
            	Main.sceneManager.setLocation(currentX, currentY + speed);
            	Main.sceneManager.resizePanel(currentWidth, currentHeight - speed);
                break;
            case 1: 
            	Main.sceneManager.resizePanel(currentWidth, currentHeight - speed);
                break;
            case 2: 
            	Main.sceneManager.setLocation(currentX + speed, currentY);
            	Main.sceneManager.resizePanel(currentWidth - speed, currentHeight);
                break;
            case 3:
            	Main.sceneManager.resizePanel(currentWidth - speed, currentHeight);
                break;
        }
    }
    
    private void GetValues() {
    	currentX = Main.sceneManager.getX();
        currentY = Main.sceneManager.getY();
        currentWidth = Main.sceneManager.getWidth();
        currentHeight = Main.sceneManager.getHeight();
    }
    
    public void BulletHitWindow(int type) {
    	
    	GetValues();
        
    	tempPower = power / (GameManager.weapon.level + 1);
    	
    	switch(type) {
    		case 0:
    			if (currentY - tempPower < 0) return;
    			Main.sceneManager.setLocation(currentX, currentY - tempPower);
    			Main.sceneManager.resizePanel(currentWidth, currentHeight + tempPower);
                break;
    		case 1:
    			if (currentY + currentHeight + tempPower > Main.MONITOR_HEIGHT) return;
    			Main.sceneManager.resizePanel(currentWidth, currentHeight + tempPower);
    			break;
    		case 2:
    			if (currentX - tempPower < 0) return;
    			Main.sceneManager.setLocation(currentX - tempPower, currentY);
    			Main.sceneManager.resizePanel(currentWidth + tempPower, currentHeight);
                break;
    		case 3:
    			if (currentX + currentWidth + tempPower > Main.MONITOR_WIDTH) return;
    			Main.sceneManager.resizePanel(currentWidth + tempPower, currentHeight);
    			break;
    	}
    }
}