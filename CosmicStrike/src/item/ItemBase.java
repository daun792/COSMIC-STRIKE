package item;

import java.awt.*;
import java.util.ArrayList;

import game.*;

public abstract class ItemBase {
	public Image innerIcon;
	public int level = 0;
	public ArrayList<Integer> costList = new ArrayList<Integer>();
	
	public boolean isMoneyEnough() {
		if (GameManager.money >= costList.get(level)) {
			GameManager.money -= costList.get(level);
			level++;
			applyEffect();
			if (costList.size() <= level) {
				Main.itemManager.itemList.remove(this);
				level--;
			}	
			return true;
		}
		else
			return false;
	}
	
	public abstract void applyEffect();
}
