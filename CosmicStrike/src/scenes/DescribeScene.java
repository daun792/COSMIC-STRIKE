package scenes;

import java.awt.*;
import javax.swing.*;

public class DescribeScene extends JPanel {

	private Image background;
	
	public DescribeScene(){
		setLayout(null);
		setBackground(Color.black);
	
		background = new ImageIcon("images/describe.png").getImage();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 25, 40, 350, 320, this);
    }
}
