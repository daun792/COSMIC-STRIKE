package scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import game.*;

public class ScoreScene extends JPanel {

	Image rankingGIF = new ImageIcon("images/score_ranking.gif").getImage();
	Image rankingPNG = new ImageIcon("images/score_ranking.png").getImage();
	Image table = new ImageIcon("images/score_table.png").getImage();
	Image press = new ImageIcon("images/score_press.gif").getImage();
	
	JLabel[] names = new JLabel[5];
	JLabel[] scores = new JLabel[5];
	
	private boolean isNew = true;
	
	private Audio scoreBGM = new Audio("audio/scoreBGM.wav", true);
	
    public ScoreScene() {
    	setLayout(null); 
    	setBackground(Color.black);
    	
    	scoreBGM.start();

    	for (int i = 0; i < 5; i++) {
    		setNameLabel(i);
            setScoreLabel(i);
            
            if (names[i] != null)
            	add(names[i]);
            if (scores[i] != null)
            	add(scores[i]);
    	}
    	
    	Timer timer = new Timer(1000, e -> {
    		isNew = false;
    		for (int i = 0; i < 5; i++) {
    			if (names[i] != null)
                	names[i].setVisible(true);
                if (scores[i] != null)
                	scores[i].setVisible(true);
    		}
        });
        timer.setRepeats(false); 
        timer.start();
    	
    	addKeyListener(new KeyListener());
    }
    
    public void setFocusOn() {
        setFocusable(true);
        requestFocus();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (isNew == true)
        	g.drawImage(rankingGIF, 25, 25, 350, 60, this);
        else {
        	g.drawImage(rankingPNG, 25, 25, 350, 60, this);
       
            g.drawImage(table, 20, 105, 360, 180, this);
            g.drawImage(press, 25, 320, 350, 60, this);
        }
    }

    class KeyListener extends KeyAdapter {
    	
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_R:
                	scoreBGM.stop();
                	Main.sceneManager.switchToRestart();
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
            }
        }
    }
    
    void setNameLabel(int _index) {
        if (Main.dataManager.scoreList.size() <= _index) return;
        Object name = Main.dataManager.scoreList.get(_index).get(0);
        if (name == null) return;
        names[_index] = new Label(name);
        names[_index].setBounds(120, 135 + _index * 30, 150, 30);
    }

    void setScoreLabel(int _index) {
        if (Main.dataManager.scoreList.size() <= _index) return;
        Object score = Main.dataManager.scoreList.get(_index).get(1);
        if (((Integer) score).intValue() == -1) return;
        scores[_index] = new Label(score);
        scores[_index].setBounds(300, 135 + _index * 30, 100, 30);
    }

    class Label extends JLabel {

        Label(Object _name) {
            super(_name.toString());
            setVisible(false);
            setForeground(Color.WHITE);
            setFont(new Font(getFont().getName(), Font.PLAIN, 20));
        }
    }
   
}
