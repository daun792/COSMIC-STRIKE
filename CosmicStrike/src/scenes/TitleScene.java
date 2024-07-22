package scenes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import game.*;

public class TitleScene extends JPanel {
	
	private JTextField id;
	private JButton startBtn;
	
	private Image logoGIF = new ImageIcon("images/main_logo.gif").getImage();
	private Image logoPNG = new ImageIcon("images/main_logo.png").getImage();
	private Image background = new ImageIcon("images/main_background.gif").getImage();
	private ImageIcon startButton = new ImageIcon("images/main_startButton.png");
	
	private boolean isNew =  true;
	
	private Audio buttonClick = new Audio("audio/buttonClickSFX.wav", false);
	
	public TitleScene(){
		setLayout(null);

		id = new IdTextField();
        add(id);

        startBtn = new StartButton(startButton);
        add(startBtn);
        
        Timer timer = new Timer(2000, e -> {
            id.setVisible(true);
            startBtn.setVisible(true);
            isNew = false;
        });
        timer.setRepeats(false); // 한 번만 실행하도록 설정
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
        g.drawImage(background, 0, 0, 400, 400, this);
        
        if (isNew == true)
        	g.drawImage(logoGIF, 25, 100, 350, 60, this);
        else 
        	g.drawImage(logoPNG, 25, 100, 350, 60, this);
    }
	
	class KeyListener extends KeyAdapter {
    	
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
            }
        }
    }
    
	class IdTextField extends JTextField {
		
		private boolean hintShowing = true;
		
		IdTextField() {
			setVisible(false);
			setBounds(150, 230, 100, 30);
			setText("Enter your ID");
	        setForeground(Color.GRAY);
	        addFocusListener(new java.awt.event.FocusAdapter() {
	            public void focusLost(java.awt.event.FocusEvent evt) {
	                if (id.getText().isEmpty()) {
	                	hintShowing = true;
	                    setForeground(Color.GRAY);
	                    setText("Enter your ID");
	                }
	            }
	        });
	        addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyTyped(KeyEvent e) {
	                if (hintShowing == true) {
	                    setText("");
	                    setForeground(Color.BLACK);
	                    hintShowing = false;
	                }
	            }
	        });
		}
	}
	
	class StartButton extends JButton {
		
	    StartButton(ImageIcon startButton) {
	        super(startButton);
	        setVisible(false);
	        
	        setBorderPainted(false);
	        setContentAreaFilled(false);
	        
	        setBounds(111, 280, 178, 28);
	        
	        addActionListener(e -> {
	            buttonClick.start();
	            if (id.getText().isEmpty() || id.getText().equals("Enter your ID")) {
	                JOptionPane.showMessageDialog(null, "Please enter your ID.", "Empty ID", JOptionPane.WARNING_MESSAGE);
	            } else if (id.getText().length() > 15) {
	                JOptionPane.showMessageDialog(null, "Please enter 15 characters or less.", "Long ID", JOptionPane.WARNING_MESSAGE);
	            } else {
	                Main.dataManager.name = id.getText();
	                Main.sceneManager.gameStart();
	            }
	        });
	    }
	}
}

