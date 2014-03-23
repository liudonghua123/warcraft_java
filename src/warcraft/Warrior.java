package warcraft;

public class Warrior implements Cloneable {
	private int number;
	private int healthPoint;
	private int attackPoint;
	private WarriorProfession warriorProfession;

	public Warrior(int healthPoint, WarriorProfession warriorProfession) {
		this(0, healthPoint, 0, warriorProfession);
	}

	public Warrior(int number, int healthPoint, int attackPoint,
			WarriorProfession warriorProfession) {
		this.number = number;
		this.healthPoint = healthPoint;
		this.attackPoint = attackPoint;
		this.warriorProfession = warriorProfession;
	}

	public int getNumber() {
		return number;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	public int getAttackPoint() {
		return attackPoint;
	}

	public WarriorProfession getWarriorProfession() {
		return warriorProfession;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
