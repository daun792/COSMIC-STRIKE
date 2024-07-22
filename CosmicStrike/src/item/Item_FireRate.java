package item;

import javax.swing.*;

import player.*;

public class Item_FireRate extends ItemBase {

	public Item_FireRate() {
		super();
		
		this.innerIcon = new ImageIcon("images/item_fireRate.png").getImage();
		
		this.costList.add(28);
		this.costList.add(98);
		this.costList.add(202);
		this.costList.add(340);
		this.costList.add(507);
		this.costList.add(704);
		this.costList.add(1182);
	}
	
	@Override
	public void applyEffect() {
		Bullet.speed += 1;
	}
}