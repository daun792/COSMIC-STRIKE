package item;

import javax.swing.*;

import game.*;

public class Item_Wealth extends ItemBase {

	public Item_Wealth() {
		super();
		
		this.innerIcon = new ImageIcon("images/item_wealth.png").getImage();
		
		this.costList.add(25);
		this.costList.add(100);
		this.costList.add(225);
		this.costList.add(400);
		this.costList.add(625);
		this.costList.add(900);
		this.costList.add(1225);
	}
	
	@Override
	public void applyEffect() {
		GameManager.wealth += 1;
	}
}