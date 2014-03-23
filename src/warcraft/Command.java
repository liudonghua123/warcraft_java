package warcraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Command {
	private int totalHealthPoint;
	private int currentRemainingHealthPoint;
	private List<WarriorProfession> createWarriorSequence;
	private List<Warrior> createdWarrior;
	private WarriorProfession currentWarriorProfession;

	private WarriorsHelper warriorsHelper;
	private Map<WarriorProfession, Warrior> warriorData;
	private CommandType commandType;

	public Command(int totalHealthPoint, WarriorsHelper warriorsHelper, 
			CommandType commandType) {
		this.totalHealthPoint = totalHealthPoint;
		this.warriorsHelper = warriorsHelper;
		this.commandType = commandType;
		this.createWarriorSequence = warriorsHelper.getWarriorSequence(commandType);
		this.createdWarrior = new ArrayList<>();
		this.warriorData = warriorsHelper.getWarriors();
		this.currentRemainingHealthPoint = this.totalHealthPoint;
	}

	public boolean canCreateWarrior() {
		return currentRemainingHealthPoint >= warriorsHelper
				.getMinHealthValue();
	}

	public void createWarrior() {
		if (currentWarriorProfession == null) {
			currentWarriorProfession = createWarriorSequence.get(0);
		}
		int currentWarriorProfessionIndex = createWarriorSequence
				.indexOf(currentWarriorProfession);
		List<WarriorProfession> iteratorWarriorSequence = new ArrayList<>();
		iteratorWarriorSequence
				.addAll(createWarriorSequence.subList(
						currentWarriorProfessionIndex,
						createWarriorSequence.size()));
		if (currentWarriorProfessionIndex > 0) {
			iteratorWarriorSequence.addAll(createWarriorSequence.subList(0,
					currentWarriorProfessionIndex));
		}
		for (WarriorProfession profession : iteratorWarriorSequence) {
			Warrior warrior = warriorData.get(profession);
			if (warrior.getHealthPoint() <= currentRemainingHealthPoint) {
				currentWarriorProfession = getNextProfession(profession);
				createWarrior(warrior);
				break;
			}
		}
	}

	private WarriorProfession getNextProfession(WarriorProfession profession) {
		int currentWarriorProfessionIndex = createWarriorSequence
				.indexOf(currentWarriorProfession);
		return createWarriorSequence.get((currentWarriorProfessionIndex + 1) % createWarriorSequence.size());
	}

	private void createWarrior(Warrior warrior) {
		Warrior needCreateWarrior = null;
		try {
			needCreateWarrior = (Warrior) warrior.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		this.createdWarrior.add(needCreateWarrior);
		currentRemainingHealthPoint -= needCreateWarrior.getHealthPoint();
	}

	public int getWarriorIndex(Warrior providedWarrior) {
		List<Warrior> specifyProfessionWarrior = new ArrayList<>();
		for(Warrior warrior : createdWarrior) {
			if(warrior.getWarriorProfession().equals(providedWarrior.getWarriorProfession())) {
				specifyProfessionWarrior.add(warrior);
			}
		}
		return specifyProfessionWarrior.indexOf(providedWarrior);
	}

	public List<Warrior> getCreatedWarrior() {
		return createdWarrior;
	}

	public CommandType getCommandType() {
		return commandType;
	}

}
