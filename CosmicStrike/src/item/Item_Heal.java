package item;

import javax.swing.*;

import game.*;

public class Item_Heal extends ItemBase {

	public Item_Heal() {
		super();
		
		this.innerIcon = new ImageIcon("images/item_heal.png").getImage();
		
		this.costList.add(20);
		this.costList.add(30);
		this.costList.add(40);
		this.costList.add(50);
		this.costList.add(60);
		this.costList.add(70);
		this.costList.add(80);
		this.costList.add(90);
		this.costList.add(100);
		this.costList.add(110);
		this.costList.add(120);
		this.costList.add(130);
		this.costList.add(140);
		this.costList.add(150);
	}
	
	@Override
	public void applyEffect() {
		GameManager.currentHp += 20;
		if (GameManager.currentHp > GameManager.maxHp) 
			GameManager.currentHp = GameManager.maxHp;
	}
}
