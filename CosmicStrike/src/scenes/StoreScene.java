package scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import game.*;
import item.*;

public class StoreScene extends JDialog {
	
	private JLabel coinLabel;
    private JButton restockButton;
    private JButton[] itemButtons = new JButton[3];
    private JLabel[] itemLevels = new JLabel[3];
    private JLabel[] itemCosts = new JLabel[3];
    private JLabel restockCost;
    
    ImageIcon exitButtonImage = new ImageIcon("images/store_exitButton.png");
    Image outerIcon = new ImageIcon("images/store_itemBox.png").getImage();
    ImageIcon restockButtonImage = new ImageIcon("images/store_restockButton.png");
    
    private Audio buttonClick = new Audio("audio/buttonClickSFX.wav", false);
    
	public StoreScene(Frame parent) {
		super(parent, "상점", true);
        setLocation(300, 500);
        
        setContentPane(new StorePanel());
        setUndecorated(true);
        setSize(700, 550);
       
        setVisible(true);
        setLayout(null);
	}
	
	class StorePanel extends JPanel {
		
		StorePanel() {
			setLayout(null);
			setBackground(Color.black);
			
			coinLabel = new CoinLabel(); 
	        add(coinLabel);
	        
	        JButton exitButton = new ExitButton(exitButtonImage);
	        add(exitButton);
	
	        for (int i = 0; i < itemButtons.length; i++) {
	            createItem(i);

	            add(itemButtons[i]);
	            add(itemLevels[i]);
	            add(itemCosts[i]);
	        }
	        

	        restockButton = new RestockButton(restockButtonImage);
	        add(restockButton);			
	        
	        restockCost = new RestockCost("10");
	        add(restockCost);
	        
	        setLabelColor();
		}
	}
	
	class CoinLabel extends JLabel {
		
		CoinLabel() {
			setText("COIN : " + GameManager.money);
	        setForeground(Color.decode("#9d0dc0"));
	        Font labelFont = getFont();
	        setFont(new Font(labelFont.getName(), Font.BOLD, 30));
	        setBounds(300, 50, 200, 30);
		}
	}
	
	class ExitButton extends JButton implements ActionListener {
		
		ExitButton(ImageIcon icon) {
	        super(icon);
	        
	        setBorderPainted(false);
	        setContentAreaFilled(false);
	        
	        setBounds(550, 50, 100, 40); 
	        addActionListener(this);
	    }
		
		@Override
	    public void actionPerformed(ActionEvent e) {
			buttonClick.start();
			GameManager.isRun = true;
			dispose();
	    }	    
	}
	
	class ItemButton extends JButton {
		
		private int index;
		private ItemBase item;


	    ItemButton(ImageIcon _icon, int _index, ItemBase _item) {
	        super(_icon);
	        
	        this.index = _index;
	        this.item = _item;
	        
	        setBounds(40 + _index * 220, 170, 180, 180);
	        
	        setBorderPainted(false);
	        setContentAreaFilled(false);
	        
	        addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	                if (_item.isMoneyEnough() == true) {
	                	System.out.println(item + " " + item.level);
	                	buttonClick.start();
	                	Main.itemManager.getRandomItem(_index);
		                refillItem(_index);
		                setLabelColor();
		                coinLabel.setText("COIN : " + GameManager.money);
	                }
	            }
	        });
	    }
	}
	
	class ItemLevel extends JLabel {
		
		ItemLevel(int _level, int _index) {
	        super("lv. " + _level);
	        
	        setBounds(120 + _index * 220, 140, 100, 30);
	        
	        setForeground(Color.WHITE);
	        setFont(new Font(getFont().getName(), Font.PLAIN, 20));
	    }
	}
	
	class ItemCost extends JLabel {
		
		ItemCost(int _cost, int _index) {
	        super(Integer.toString(_cost)); 
	        
	        setBounds(125 + _index * 220, 350, 100, 30);
	      
	        setFont(new Font(getFont().getName(), Font.PLAIN, 20));
	    }
	}
	
	class RestockButton extends JButton implements ActionListener {
		
		RestockButton(ImageIcon _icon) {
			super(_icon);
			
			setBorderPainted(false);
	        setContentAreaFilled(false);
			
	        setBounds(259, 430, 182, 53); 
	        addActionListener(this);
		}
		
		@Override
	    public void actionPerformed(ActionEvent e) {
			if (GameManager.money >= 10) {
				buttonClick.start();
				GameManager.money -= 10;
				for (int i = 0; i < itemButtons.length; i++) {
					Main.itemManager.getRandomItem(i);
		        	refillItem(i);
		        }
				setLabelColor();
				coinLabel.setText("COIN : " + GameManager.money);
			}
	    }
	}
	
	class RestockCost extends JLabel {
	    RestockCost(String cost) {
	        super(cost);
	        Font labelFont = getFont();
	        setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
	        setBounds(340, 480, 100, 30); 
	    }
	}
	
	Image getCombinedImage(Image outerImage, Image innerImage) {
        int width = outerImage.getWidth(null);
        int height = outerImage.getHeight(null);

        BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = combinedImage.getGraphics();
        g.drawImage(outerImage, 0, 0, null);
        g.drawImage(innerImage, (width - innerImage.getWidth(null)) / 2, (height - innerImage.getHeight(null)) / 4 * 3, null);
        g.dispose();

        return combinedImage;
    }
	
	void createItem(int _index) {
		ItemBase item = Main.itemManager.items[_index];
		
		int level = item.level;
		itemLevels[_index] = new ItemLevel(++level, _index);
		itemCosts[_index] = new ItemCost(item.costList.get(item.level), _index);
		
    	Image combinedImage = getCombinedImage(outerIcon, item.innerIcon);
        ImageIcon buttonIcon = new ImageIcon(combinedImage);
        
        itemButtons[_index] = new ItemButton(buttonIcon, _index, item);
	}
	
	void setLabelColor() {
		for (int i = 0; i < itemCosts.length; i++) {
			if (Integer.parseInt(itemCosts[i].getText()) > GameManager.money)
				itemCosts[i].setForeground(Color.red);
			else
				itemCosts[i].setForeground(Color.white);
		}
		
		if (10 > GameManager.money)
			restockCost.setForeground(Color.red);
		else
			restockCost.setForeground(Color.white);
	}
	
	void refillItem(int _index) {
		remove(itemButtons[_index]);
		remove(itemLevels[_index]);
		remove(itemCosts[_index]);
	    
		createItem(_index);
		
		add(itemButtons[_index]);
		add(itemLevels[_index]);
		add(itemCosts[_index]);
		
		revalidate();
		repaint();
	}
}
