package item;

import javax.swing.*;

import game.*;

public class Item_Piercing extends ItemBase {

	public Item_Piercing() {
		super();
		
		this.innerIcon = new ImageIcon("images/item_piercing.png").getImage();
		
		this.costList.add(50);
	}
	
	@Override
	public void applyEffect() {
		GameManager.weapon.isPiercing = true;
	}
}
