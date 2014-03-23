package warcraft;

public enum CommandType {
	RED("red"),BLUE("blue");
	
	private String name;
	
	CommandType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
