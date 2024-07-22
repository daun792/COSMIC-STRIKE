package enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import game.*;

public class Enemy_Boss extends EnemyBase {
	private Random random = new Random();
	
	int cnt = 0;
	
	private int monitorX;
	private int monitorY;
	
	private ArrayList<EnemyBullet> bulletList = new ArrayList<EnemyBullet>();
	private EnemyBullet bullet;
	private EnemyLaser laser;
	
	private Audio attack = new Audio("audio/enemy_intAttackSFX.wav", false);
	
	private Enemy_Frame frame;
	private int bulletCount = 36;
	
	public Enemy_Boss() {
		this.image = new ImageIcon("images/enemy_boss.png").getImage();
		
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		
		this.hp = 100;
		this.coinCount = 20;
		
		int frameX = Main.sceneManager.getX();
		int frameY = Main.sceneManager.getY();
		do {
			monitorX = (int)(Math.random() * (Main.MONITOR_WIDTH - 600));
		} while (monitorX >= frameX && monitorX <= frameX + Main.SCREEN_WIDTH);
		do {
			monitorY = (int)(Math.random() * (Main.MONITOR_HEIGHT - 300));
		} while (monitorY >= frameY && monitorY <= frameY + Main.SCREEN_HEIGHT);
		
		
		
		frame = new Enemy_Frame(monitorX, monitorY);
		frame.setFocusableWindowState(false);
		
		x = monitorX - frameX + 4;
		y = monitorY - frameY + 4;
	}
	
	@Override
	public void move() {
		int frameX = Main.sceneManager.getX();
		int frameY = Main.sceneManager.getY();
		
		x = monitorX - frameX + 4;
		y = monitorY - frameY + 4;
		
		if (cnt % 150 == 0) {
			int type = random.nextInt(2);
			switch (type) {
				case 0:
					attack.start();
					for (int i = 0; i < bulletCount; i++) {
						 double angle = 2 * Math.PI * i / bulletCount;
						bullet = new EnemyBullet(x, y, angle);
						bulletList.add(bullet);
					}
					break;
				case 1:
					laser = new EnemyLaser(x + 56, y + 56, this);
					break;
			}
		}
		
		cnt++;
	}	
	
	@Override
	public void draw(Graphics g) {
    	g.drawImage(image, x, y, null);
    	
    	for (int i = 0; i < bulletList.size(); i++) {
    		bullet = bulletList.get(i);
    		bullet.fire();
    		bullet.draw(g);
    		checkBulletHitPlayer(bullet);
    	}
    	
    	if (laser != null)
    		laser.draw(g);
    }
	
	public void checkBulletHitPlayer(EnemyBullet _bullet) {
		Rectangle2D bulletBounds = new Rectangle2D.Double(_bullet.x, _bullet.y, _bullet.width, _bullet.height);
		if (bulletBounds.intersects(GameManager.playerBounds)) {
			GameManager.player.playerAttacked();
			bulletList.remove(_bullet);
        }
	}
	
	public void remove() {
		if (frame != null) {
			frame.dispose();
			frame = null;
		}
		removeLaser();
	}
	
	public void removeLaser() {
		if (laser != null) {
			laser.visible = false;
			laser = null;
		}
			
	}
}