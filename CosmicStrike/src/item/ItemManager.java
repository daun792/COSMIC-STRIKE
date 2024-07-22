package item;

import java.util.Random;
import java.util.ArrayList;

public class ItemManager {
	private Random random = new Random();
	
	public ItemBase[] items = new ItemBase[3];
	public ArrayList<ItemBase> itemList = new ArrayList<ItemBase>(); 
	
	public ItemManager() {
		init();	
	}
	
	public void init() {
		itemList.clear();
		
		itemList.add(new Item_FireRate());
		itemList.add(new Item_Heal());
		itemList.add(new Item_Speed());
		itemList.add(new Item_WallPunch());
		itemList.add(new Item_Wealth());
		itemList.add(new Item_MaxHealth());
		itemList.add(new Item_MultiShot());
		itemList.add(new Item_Piercing());
		
		for (int i = 0; i < items.length; i++) 
			getRandomItem(i);
	}
	
	public void getRandomItem(int _index) {
		int randomIndex = random.nextInt(itemList.size());
		
        items[_index] = itemList.get(randomIndex);
	}
}