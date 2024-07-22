package enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import game.*;

public class Enemy_Int extends EnemyBase {

	int cnt = 0;
	
	private boolean flag = true;
	
	private ArrayList<EnemyBullet> bulletList = new ArrayList<EnemyBullet>();
	private EnemyBullet bullet;
	
	private Audio attack = new Audio("audio/enemy_intAttackSFX.wav", false);

	public Enemy_Int() {
		super();

		this.image = new ImageIcon("images/enemy_int.png").getImage();
		
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		
		this.hp = 20;
		this.speed = 1;
		this.coinCount = 4;
		
		setXY();
	}
	
	private void setXY() {
    	x -= width / 2;
    	y -= height / 2;
    }
	
	@Override
	public void move() {

		if (cnt % 100 == 0) {
			attack.start();
			bullet = new EnemyBullet(x, y);
			bulletList.add(bullet);
		}
			
		switch (direction) {
			case 0:
				y = -(height / 2);
				
				if (flag)
					x -= speed;
				else
					x += speed;				
				if (x <= 0) 
					flag = false;
				else if (x >= Main.SCREEN_WIDTH - width)
					flag = true;
				
				break;
			case 1:
				y = Main.SCREEN_HEIGHT - (height / 2);
				
				if (flag)
					x += speed;
				else
					x -= speed;				
				if (x <= 0) 
					flag = true;
				else if (x >= Main.SCREEN_WIDTH - width)
					flag = false;
			
				break;
			case 2:
				x = -(width / 2);
				
				if (flag)
					y += speed;
				else
					y -= speed;			
				if (y <= 0) 
					flag = true;
				else if (y >= Main.SCREEN_HEIGHT - height)
					flag = false;
				
				break;
			case 3:
				x = Main.SCREEN_WIDTH - (width / 2);
				
				if (flag)
					y -= speed;
				else
					y += speed;			
				if (y <= 0) 
					flag = false;
				else if (y >= Main.SCREEN_HEIGHT - height)
					flag = true;
				
				break;
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
    }
	
	public void checkBulletHitPlayer(EnemyBullet _bullet) {
		Rectangle2D bulletBounds = new Rectangle2D.Double(_bullet.x, _bullet.y, _bullet.width, _bullet.height);
		if (bulletBounds.intersects(GameManager.playerBounds)) {
			GameManager.player.playerAttacked();
			bulletList.remove(_bullet);
        }
	}
}