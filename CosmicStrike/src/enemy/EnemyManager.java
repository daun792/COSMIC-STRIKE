package enemy;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import game.*;
import scenes.*;

public class EnemyManager {
	
	private ArrayList<EnemyBase> enemyList = new ArrayList<EnemyBase>();
	private EnemyBase enemy;
	
	private int cnt = 1;
	
	private Audio enemyHit = new Audio("audio/enemyHitSFX.wav", false);
	private Audio enemyDie = new Audio("audio/enemyDieSFX.wav", false);
	
	private boolean normal, dex, eint, boss;
	private boolean firstBoss;
	private Rectangle2D enemyBounds1;
	private Rectangle2D enemyBounds2;
	
	public void init() {
		for (int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
			enemy.remove();
		}
		enemyList.clear();
		cnt = 1;
		normal = true;
		dex = eint = boss = firstBoss = false;
		enemyBounds1 = null;
		enemyBounds2 = null;
	}
	
	public boolean checkBulletHitEnemy(Rectangle2D _bulletBounds, int _power) {
		for (int i = 0; i < enemyList.size(); i++) {
            enemy = enemyList.get(i);
            enemyBounds1 = new Rectangle2D.Double(enemy.x, enemy.y, enemy.width, enemy.height);
            if (enemyBounds1.intersects(_bulletBounds)) {
            	enemyHit.start();
                enemy.hp -= _power;
                return true;
            }
        }
		
		return false;
	}
	
	public void appearProcess() {
		
		checkEnemyAppear();
		
		createNormal();
		createDex();
		createInt();
		createBoss();
		
		cnt++;
	}
	
	private void checkEnemyAppear() {
		if (cnt / 300 >= 1) dex = true;
		if (cnt / 500 >= 1) eint = true;
		if (cnt / 800 >= 1) boss = true;
	}
	
	private void createNormal() {
		if (normal == false) return;
		if (cnt % 50 == 0) 
			enemyList.add(new Enemy_Normal());
	}
	
	private void createDex() {
		if (dex == false) return;
		if (cnt % 300 == 0) 
			enemyList.add(new Enemy_Dex());
	}
	
	private void createInt() {
		if (eint == false) return;
		if (cnt % 500 == 0)
			enemyList.add(new Enemy_Int());
	}
	
	public void createBoss() {
		if (boss == false) return;
		if (cnt % 800 == 0 && firstBoss == false) {
			enemyList.add(new Enemy_Boss());
			firstBoss = true;
		}
		if (cnt % 3000 == 0 && firstBoss == true)
			enemyList.add(new Enemy_Boss());
	}
	
	public void moveProcess() {
		for (int i = 0; i < enemyList.size(); i++) {
            enemy = enemyList.get(i);
            enemy.move();
            
            enemyBounds2 = new Rectangle2D.Double(enemy.x, enemy.y, enemy.width, enemy.height);
            
            if (enemy.hp <= 0) {
            	enemyDie.start();
            	SceneManager.gameScene.enemyDie(enemy.x, enemy.y, enemy.coinCount);
            	enemy.remove();
                enemyList.remove(enemy);
            }
           
            if (enemyBounds2.intersects(GameManager.playerBounds)) {
            	GameManager.player.playerAttacked();
            	enemy.remove();
                enemyList.remove(enemy);
            }
        }
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < enemyList.size(); i++) {
            enemy = enemyList.get(i);
            enemy.draw(g);
        }
	}
}
