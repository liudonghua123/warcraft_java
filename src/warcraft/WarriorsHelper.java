package warcraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class WarriorsHelper {
	
	private Map<WarriorProfession, Warrior> warriors;
	
	public WarriorsHelper() {
		 warriors = new HashMap<>();
	}

	/**
	 * 初始化各类武士信息
	 * @return
	 */
	public Map<WarriorProfession, Warrior> initWarriorData(Scanner scanner) {
		// dragon 、ninja、iceman、lion、wolf
		scanner.nextLine();
		String line = scanner.nextLine();
		String[] healthPoint = line.split(" ");
		int[] healthPointArray = new int[5];
		try {
			healthPointArray[0] = Integer.parseInt(healthPoint[0]);
			healthPointArray[1] = Integer.parseInt(healthPoint[1]);
			healthPointArray[2] = Integer.parseInt(healthPoint[2]);
			healthPointArray[3] = Integer.parseInt(healthPoint[3]);
			healthPointArray[4] = Integer.parseInt(healthPoint[4]);
		} catch (NumberFormatException e) {
			healthPointArray[0] = 3;
			healthPointArray[1] = 4;
			healthPointArray[2] = 5;
			healthPointArray[3] = 6;
			healthPointArray[4] = 7;
		} finally {
			warriors.put(WarriorProfession.DRAGON, new Warrior(
					healthPointArray[0], WarriorProfession.DRAGON));
			warriors.put(WarriorProfession.NINJA, new Warrior(
					healthPointArray[1], WarriorProfession.NINJA));
			warriors.put(WarriorProfession.ICEMAN, new Warrior(
					healthPointArray[2], WarriorProfession.ICEMAN));
			warriors.put(WarriorProfession.LION, new Warrior(
					healthPointArray[3], WarriorProfession.LION));
			warriors.put(WarriorProfession.WOLF, new Warrior(
					healthPointArray[4], WarriorProfession.WOLF));
		}
		return warriors;
	}
	
	/**
	 * 获取制造武士顺序列表
	 * @param type
	 * @return
	 */
	public List<WarriorProfession> getWarriorSequence(CommandType type) {
		List<WarriorProfession> warriorSequence = new ArrayList<>();
		switch(type) {
		// 红方司令部按照iceman、lion、wolf、ninja、dragon的顺序
		case RED:
			warriorSequence.add(WarriorProfession.ICEMAN);
			warriorSequence.add(WarriorProfession.LION);
			warriorSequence.add(WarriorProfession.WOLF);
			warriorSequence.add(WarriorProfession.NINJA);
			warriorSequence.add(WarriorProfession.DRAGON);
			break;
			// 蓝方司令部按照lion、dragon、ninja、iceman、wolf的顺序
		case BLUE:
			warriorSequence.add(WarriorProfession.LION);
			warriorSequence.add(WarriorProfession.DRAGON);
			warriorSequence.add(WarriorProfession.NINJA);
			warriorSequence.add(WarriorProfession.ICEMAN);
			warriorSequence.add(WarriorProfession.WOLF);
			break;
			
		}
		return warriorSequence;
	}
	
	public int getMinHealthValue() {
		WarriorProfession minHealthWarriorProfession = getMinHealthWarriorProfession();
		return warriors.get(minHealthWarriorProfession).getHealthPoint();
	}
	
	public WarriorProfession getMinHealthWarriorProfession() {
		Iterator<Entry<WarriorProfession, Warrior>> iterator = warriors.entrySet().iterator();
		int minHealth = -1;
		WarriorProfession minHealthWarriorProfession = WarriorProfession.DRAGON;
		while (iterator.hasNext()) { 
		    Entry<WarriorProfession, Warrior> entry = iterator.next(); 
		    WarriorProfession currentWarriorProfession = entry.getKey(); 
		    Warrior currentWarrior = entry.getValue(); 
		    if(currentWarrior.getHealthPoint() < minHealth) {
		    	minHealth = currentWarrior.getHealthPoint();
		    	minHealthWarriorProfession = currentWarriorProfession;
		    }
		}
		return minHealthWarriorProfession;
	}

	public Map<WarriorProfession, Warrior> getWarriors() {
		return warriors;
	}
}
