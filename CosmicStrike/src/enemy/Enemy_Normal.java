package enemy;

import javax.swing.*;

import game.*;

public class Enemy_Normal extends EnemyBase {
	
	public Enemy_Normal() {
		super();
		
		this.image = new ImageIcon("images/enemy_normal.png").getImage();
		
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		
		this.hp = 10;
		this.speed = 5;
		this.coinCount = 2;
	}
	
	@Override
	public void move() {
		 double angle = Math.atan2(GameManager.player.y - y, GameManager.player.x - x);

	     int enemyXSpeed = (int) (speed * Math.cos(angle));
	     int enemyYSpeed = (int) (speed * Math.sin(angle));

	     this.x += enemyXSpeed;
	     this.y += enemyYSpeed;
	}
}
