package player;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import game.*;

public class Weapon {
	
	public int level = 0;
	public boolean isPiercing = false;
	
	public ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	private ArrayList<BrokenBullet> brokenBulletList = new ArrayList<BrokenBullet>();
	
	private BrokenBullet brokenBullet;
	private Bullet bullet;
	
	public void init() {
		level = 0;
		isPiercing = false;
		
		bulletList.clear();
		brokenBulletList.clear();
	}
	
	public void shootBullet(int _x, int _y) {
		double angle = Math.atan2(_y - GameManager.player.y, _x - GameManager.player.x);
		
		switch(level) {
			case 0:
				shotOne(angle);
				break;
			case 1:
				shotTwo(angle);
				break;
			case 2:
				shotThree(angle);
				break;
			case 3:
				shotFour(angle);
				break;
			case 4:
				shotFive(angle);
				break;
		}
	}
	
	void shotOne(double _angle) {
		bulletList.add(new Bullet(_angle));
	}
	
	void shotTwo(double _angle) {
		double[] num = {-0.5, 0.5}; 
		for (int i = 0; i < 2; i++) {
			double temp = _angle + (num[i] * Math.PI / 20);
			bulletList.add(new Bullet(temp));
		}
	}
	
	void shotThree(double _angle) {
		int[] num = {-1, 0, 1}; 
		for (int i = 0; i < 3; i++) {
			double temp = _angle + (num[i] * Math.PI / 20);
			bulletList.add(new Bullet(temp));
		}
	}

	void shotFour(double _angle) {
		double[] num = {-1.5, -0.5, 0.5, 1.5}; 
		for (int i = 0; i < 4; i++) {
			double temp = _angle + (num[i] * Math.PI / 20);
			bulletList.add(new Bullet(temp));
		}
	}
	
	void shotFive(double _angle) {
		int[] num = {-2, -1, 0, 1, 2}; 
		for (int i = 0; i < 5; i++) {
			double temp = _angle + (num[i] * Math.PI / 20);
			bulletList.add(new Bullet(temp));
		}
	}
	
	public void moveProcess() {
		 for (int i = 0; i < bulletList.size(); i++) {
	        	bullet = bulletList.get(i);
	        	bullet.fire();
	        	
	        	Rectangle2D bulletBounds = new Rectangle2D.Double(bullet.x, bullet.y, bullet.width, bullet.height);
	        	
	        	if (GameManager.enemyManager.checkBulletHitEnemy(bulletBounds, bullet.power)) {
	        		if (isPiercing == true) continue;
	        		removeBullet(bullet);
	        	}
	        		
	        	if (bullet.checkBulletHitWindow())
	        		removeBullet(bullet);
		 }
		 
		 for (int i = 0; i < brokenBulletList.size(); i++) {
	        	brokenBullet = brokenBulletList.get(i);
	        	brokenBullet.move();
	        	
	        	if (brokenBullet.cnt > 5)
	        		brokenBulletList.remove(brokenBullet);
		 }
	}
	
	private void removeBullet(Bullet bullet) {
    	for (int i = 0; i < 5; i++)
    		brokenBulletList.add(new BrokenBullet(bullet.x, bullet.y));
    	
    	bulletList.remove(bullet);
    }
		 
	public void draw(Graphics g) {
		for (int i = 0; i < bulletList.size(); i++) {
            bullet = bulletList.get(i);
            bullet.draw(g);
        }
        
        for (int i = 0; i < brokenBulletList.size(); i++) {
        	brokenBullet = brokenBulletList.get(i);
        	brokenBullet.draw(g);
        }
	}
}
