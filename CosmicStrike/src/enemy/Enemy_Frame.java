package enemy;

import javax.swing.*;
import java.awt.*;

public class Enemy_Frame extends JFrame {

	private Image enemyBossImage;
	
	Enemy_Frame(int x, int y) {
		setLocation(x, y);
        
		enemyBossImage = new ImageIcon("images/enemy_boss.png").getImage();

		setContentPane(new MyPanel());
		
        setUndecorated(true);
        setSize(120, 120);
       
        setFocusable(false);
        
        setVisible(true);
        setLayout(null);
	}
	
	class MyPanel extends JPanel {
		
		MyPanel() {
			setBackground(Color.black);
		}
		
		@Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(enemyBossImage, 4, 4, null);
	    }
	}
}
