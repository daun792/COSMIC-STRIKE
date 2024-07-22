package game;

import java.awt.*;

import item.*;
import scenes.*;

public class Main {
    public static int SCREEN_WIDTH = 400;
    public static int SCREEN_HEIGHT = 400;
    
    public static int MONITOR_WIDTH;
    public static int MONITOR_HEIGHT;

    public static SceneManager sceneManager;
    public static DataManager dataManager = new DataManager();
    public static ItemManager itemManager = new ItemManager();
    
    public static void main(String[] args) {
    	
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        MONITOR_WIDTH = screenSize.width;
        MONITOR_HEIGHT = screenSize.height;
        
    	sceneManager = new SceneManager();
    }
}