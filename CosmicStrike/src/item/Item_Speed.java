package item;

import javax.swing.*;

import player.*;

public class Item_Speed extends ItemBase {

	public Item_Speed() {
		super();
		
		this.innerIcon = new ImageIcon("images/item_speed.png").getImage();
		
		this.costList.add(10);
		this.costList.add(23);
		this.costList.add(37);
		this.costList.add(53);
		this.costList.add(69);
		this.costList.add(86);
		this.costList.add(103);
		this.costList.add(121);
		this.costList.add(140);
		this.costList.add(158);
		this.costList.add(178);
	}
	
	@Override
	public void applyEffect() {
		Player.speed += 1;
	}
}
