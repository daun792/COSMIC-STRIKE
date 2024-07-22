package item;

import javax.swing.*;

import game.*;

public class Item_WallPunch extends ItemBase {

	public Item_WallPunch() {
		super();
		
		this.innerIcon = new ImageIcon("images/item_wallPunch.png").getImage();
		
		this.costList.add(20);
		this.costList.add(46);
		this.costList.add(75);
		this.costList.add(106);
		this.costList.add(138);
		this.costList.add(172);
		this.costList.add(207);
	}
	
	@Override
	public void applyEffect() {
		Window.power += 1;
	}
}