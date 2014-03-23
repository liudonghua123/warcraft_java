package warcraft;

public enum WarriorProfession {
	DRAGON("dragon"), NINJA("ninja"),ICEMAN("iceman"),LION("lion"),WOLF("wolf");
	
	private String name;
	
	WarriorProfession(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
