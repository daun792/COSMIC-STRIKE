package enemy;

import javax.swing.*;

import game.*;

public class Enemy_Dex extends EnemyBase {
	
	int cnt = 0;
	
	public Enemy_Dex() {
		super();
		
		this.image = new ImageIcon("images/enemy_dex.png").getImage();
		
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		
		this.hp = 10;
		this.speed = 15;
		this.coinCount = 3;
	}
	
	@Override
	public void move() {
		cnt++;
		
		if (cnt % 20 >= 15 && cnt % 20 < 20) return;
		
		double angle = Math.atan2(GameManager.player.y - y, GameManager.player.x - x);
		 
		int direction = random.nextInt(2);
		switch (direction) {
			case 0:
				int enemyXSpeed = (int) (speed * Math.cos(angle));
		 		this.x += enemyXSpeed;
		 		break;
		 	case 1:
		 		int enemyYSpeed = (int) (speed * Math.sin(angle));
		 		this.y += enemyYSpeed;
		 		break;
		 }
	}	
}