package item;

import javax.swing.*;

import game.*;

public class Item_MultiShot extends ItemBase {

	public Item_MultiShot() {
		super();
		
		this.innerIcon = new ImageIcon("images/item_multiShot.png").getImage();
		
		this.costList.add(50);
		this.costList.add(400);
		this.costList.add(1350);
		this.costList.add(3200);
		this.costList.add(6000);
		this.costList.add(10800);
	}
	
	@Override
	public void applyEffect() {
		GameManager.weapon.level += 1;
	}
}
