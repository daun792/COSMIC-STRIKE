package enemy;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Timer;
import java.util.TimerTask;

import game.*;

public class EnemyLaser {
    private Point enemyPosition; 
    private Point endPosition;
    
    private Enemy_Boss boss;
    
    private int blinkCount = 0;
    public boolean visible = true;
    private Timer timer;
    int delay = 2000; 
    int period = 250; 
    private boolean drawThickLaser = false;
    private int thickLaserDuration = 1000; 
    private Timer thickLaserTimer;
    private int thickLaserDelay = 0;
    
	public EnemyLaser(int _x, int _y, Enemy_Boss _boss) {
		boss = _boss;
		
		if (_x >= Main.SCREEN_WIDTH)
			endPosition = new Point(0, (int)(Math.random() * Main.SCREEN_HEIGHT));
		else if (_x <= 0)
			endPosition = new Point(Main.SCREEN_WIDTH, (int)(Math.random() * Main.SCREEN_HEIGHT));
		else {
			if (_y >= Main.SCREEN_HEIGHT)
				endPosition = new Point((int)(Math.random() * Main.SCREEN_WIDTH), 0);
			else if (_y <= 0)
				endPosition = new Point((int)(Math.random() * Main.SCREEN_WIDTH), Main.SCREEN_HEIGHT);
			else
				endPosition = new Point((int)(Math.random() * Main.SCREEN_WIDTH), Main.SCREEN_HEIGHT);
		}
		
		enemyPosition = new Point(_x, _y);
		
		timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (delay > 0) {
                    delay -= 250;
                } else {
                    if (blinkCount < 5) { 
                        visible = !visible;
                        blinkCount++;
                    } else {
                    	visible = true;
                    	drawThickLaser = true;
                        timer.cancel();
                    }
                }
            }
        }, 0, period);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if (visible) {
			if (!drawThickLaser) {
                g2d.setColor(new Color(0xff509a));
                g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{5, 5}, 0.0f));

                Line2D line = new Line2D.Double(endPosition, enemyPosition);
                g2d.draw(line);
            } else {
                g2d.setColor(new Color(0x45a5ff));
                g2d.setStroke(new BasicStroke(30)); 
                Line2D thickLine = new Line2D.Double(endPosition, enemyPosition);
                g2d.draw(thickLine);
                
                if (GameManager.player.checkLaserHitPlayer(thickLine)) {
                	visible = false;
                	boss.removeLaser();
                }
                
                thickLaserDelay += 20;
                if (thickLaserDelay >= thickLaserDuration) {
                	visible = false;
                	boss.removeLaser();
                }
            }
        }
    }
	
	public void startThickLaser() {
        thickLaserTimer = new Timer();
        thickLaserTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                drawThickLaser = true;
            }
        }, 0, 20); // 20ms 간격으로 두꺼운 레이저를 그리도록 설정
    }
}
