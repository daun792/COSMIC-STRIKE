package item;

import javax.swing.*;

import game.*;

public class Item_MaxHealth extends ItemBase {

	public Item_MaxHealth() {
		super();
		
		this.innerIcon = new ImageIcon("images/item_maxHealth.png").getImage();
		
		this.costList.add(10);
		this.costList.add(25);
		this.costList.add(42);
		this.costList.add(69);
		this.costList.add(97);
		this.costList.add(106);
		this.costList.add(189);
	}
	
	@Override
	public void applyEffect() {
		GameManager.maxHp += 5;
	}
}
