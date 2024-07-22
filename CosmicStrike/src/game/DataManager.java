package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataManager {
	
	public String name;
	public int score;
	
	public ArrayList<ArrayList<Object>> scoreList = new ArrayList<>();
	
	public void init() {
		name = null;
		score = -1;
	}
	
	public void setScore() {
		if (name == null || score == -1) return;
		ArrayList<Object> tempList = new ArrayList<>();
		tempList.add(name);
		tempList.add(score);
		scoreList.add(tempList);
		
		sortScoreDescending();
	}
	
	private void sortScoreDescending() {
        Collections.sort(scoreList, new Comparator<ArrayList<Object>>() {
            @Override
            public int compare(ArrayList<Object> o1, ArrayList<Object> o2) {
                int score1 = (int) o1.get(1);
                int score2 = (int) o2.get(1);
                return Integer.compare(score2, score1);
            }
        });
    }
}
