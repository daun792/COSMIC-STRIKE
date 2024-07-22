package scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import game.*;

public class GameScene extends JPanel {
	
    private GameManager gameManager = new GameManager();

    private JLabel gameOverLabel;
    
    private Audio gameBGM = new Audio("audio/gameBGM.wav", true);
    private Audio playerAttack = new Audio("audio/playerAttackSFX.wav", false);
    private Audio openStore = new Audio("audio/openStoreSFX.wav", false);
    
    public GameScene() {
        setLayout(null);
        setBackground(Color.black);

        gameBGM.start();
        gameManager.start();
        
        addKeyListener(new KeyListener());
        addMouseListener(new MouseListener());
    }
    
    public void setFocusOn() {
        setFocusable(true);
        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameManager.gameDraw(g);
    }
    
    class KeyListener extends KeyAdapter {
    	
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                	gameManager.setUp(true);
                    break;
                case KeyEvent.VK_S:
                	gameManager.setDown(true);
                    break;
                case KeyEvent.VK_A:
                	gameManager.setLeft(true);
                    break;
                case KeyEvent.VK_D:
                	gameManager.setRight(true);
                    break;
                case KeyEvent.VK_SPACE:
                	openStore.start();
                	GameManager.isRun = false;
                	gameManager.setUp(false);
                	gameManager.setDown(false);
                	gameManager.setLeft(false);
                	gameManager.setRight(false);
                	new StoreScene(Main.sceneManager);
                	break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
            }
        }

        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                	gameManager.setUp(false);
                    break;
                case KeyEvent.VK_S:
                	gameManager.setDown(false);
                    break;
                case KeyEvent.VK_A:
                	gameManager.setLeft(false);
                    break;
                case KeyEvent.VK_D:
                	gameManager.setRight(false);
                    break;
            }
        }
    }
    
    class MouseListener extends MouseAdapter {
    	
		public void mouseReleased(MouseEvent e) {
			int mouseX = e.getX();
	        int mouseY = e.getY();
	        
	        playerAttack.start();
	        GameManager.weapon.shootBullet(mouseX, mouseY);
		}
	}
    
    class GameOverLabel extends JLabel {
    	
    	public GameOverLabel() {
    		setText("Game Over");
    		setForeground(Color.RED);
            Font labelFont = getFont();
            setFont(new Font(labelFont.getName(), Font.PLAIN, 30));
            setBounds((Main.SCREEN_WIDTH - 150) / 2, (Main.SCREEN_HEIGHT - 30) / 2, 200, 30);
    	}
    }
    
    public void gameOver() {
    	gameBGM.stop();
    	
    	GameManager.isRun = false;
    	
    	if (Main.SCREEN_WIDTH < 400 && Main.SCREEN_HEIGHT < 400)
    		Main.sceneManager.resizePanel(400, 400);
    	else if (Main.SCREEN_WIDTH < 400)
    		Main.sceneManager.resizePanel(400, Main.SCREEN_HEIGHT);
    	else if (Main.SCREEN_HEIGHT < 400)
    		Main.sceneManager.resizePanel(Main.SCREEN_WIDTH, 400);
    	
    	gameOverLabel = new GameOverLabel();
        add(gameOverLabel);
        
        repaint();
    	
    	Timer loadingTimer = new Timer();
        TimerTask loadingTask = new TimerTask() {
            @Override
            public void run() {
            	GameManager.enemyManager.init();
            	Main.dataManager.setScore();
                Main.sceneManager.switchToScorePanel();
            }
        };
        loadingTimer.schedule(loadingTask, 2000);
    }
    
    public void restartGame() {
    	gameBGM.start();
    	
    	remove(gameOverLabel);
    	
    	gameManager.reset();
    }
    
    public void enemyDie(int _x, int _y, int _coinCount) {
    	gameManager.enemyDie(_x, _y, _coinCount);
    }
}
