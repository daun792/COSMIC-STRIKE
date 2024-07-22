package scenes;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

import game.*;

public class SceneManager extends JFrame {
	
    private Audio backgroundMusic = new Audio("audio/mainBGM.wav", true);
    
    public static GameScene gameScene;

    public SceneManager() {
        setTitle("COSMIC STIRKE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(1090, 450);
        
        TitleScene titleScene = new TitleScene();
        setContentPane(titleScene);
        titleScene.setFocusOn();
        
        setUndecorated(true);
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
       
        setVisible(true);
        setLayout(null);
        
        init();
    }

    private void init() {
        backgroundMusic.start();
        
        Main.dataManager.init();
    }

    public void gameStart() {
    	switchToDescribePanel();

        Timer loadingTimer = new Timer();
        TimerTask loadingTask = new TimerTask() {
            @Override
            public void run() {
                switchToGamePanel();
            }
        };
        loadingTimer.schedule(loadingTask, 3000);
    }
    
    public void resizePanel(int _width, int _height) {
        Main.SCREEN_WIDTH = _width;
        Main.SCREEN_HEIGHT = _height;
        setSize(_width, _height);
    }

    public void switchToDescribePanel() {
    	backgroundMusic.stop();
    	DescribeScene describeScene = new DescribeScene();
    	setContentPane(describeScene);
    	revalidate();
    }
    
    public void switchToGamePanel() {
    	gameScene = new GameScene();	
        setContentPane(gameScene);
        revalidate();
        gameScene.setFocusOn();
    }
    
    public void switchToScorePanel() {
    	resizePanel(400, 400);
    	setLocation(1090, 450);
    	ScoreScene scoreScene = new ScoreScene();
    	setContentPane(scoreScene);
    	revalidate();
    	scoreScene.setFocusOn();
    }
    
    public void switchToRestart() {
    	setContentPane(gameScene);
        revalidate();
        gameScene.restartGame();
        gameScene.setFocusOn();
    }
}