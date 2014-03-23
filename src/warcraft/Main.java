package warcraft;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int totalCase = 1, currentCase = 1, totalHealthPoint = 20;;
		Scanner scanner = new Scanner(System.in);
		try {
			totalCase = scanner.nextInt();
		} catch (Exception e) {
			totalCase = 1;
		}
		while (currentCase <= totalCase) {
			try {
				totalHealthPoint = scanner.nextInt();
			} catch (Exception e) {
				totalHealthPoint = 20;
			}
			WarriorsHelper warriorsHelper = new WarriorsHelper();
			warriorsHelper.initWarriorData(scanner);
			Command redCommand = new Command(totalHealthPoint, warriorsHelper,
					CommandType.RED);
			Command blueCommand = new Command(totalHealthPoint, warriorsHelper,
					CommandType.BLUE);

			System.out.printf("Case:%d\n", currentCase);
			makeWarriors(redCommand, blueCommand);
			printResult(redCommand, blueCommand);

			++currentCase;
		}
		scanner.close();
	}

	private static void makeWarriors(Command redCommand, Command blueCommand) {
		boolean redCommandCanCreate, blueCommandCanCreate;
		while(true) {
			redCommandCanCreate = redCommand.canCreateWarrior();
			blueCommandCanCreate = blueCommand.canCreateWarrior();
			
			if(!redCommandCanCreate && !blueCommandCanCreate) {
				break;
			}
			
			if(redCommandCanCreate) {
				redCommand.createWarrior();
			}
			if(blueCommandCanCreate) {
				blueCommand.createWarrior();
			}
		}
	}

	private static void printResult(Command redCommand, Command blueCommand) {
		int maxCreatedWarriorsSize = Math.max(redCommand.getCreatedWarrior()
				.size(), blueCommand.getCreatedWarrior().size());
		boolean printedRedStops = false, printedBlueStops = false;
		for (int i = 0; i < maxCreatedWarriorsSize + 1; i++) {
			if (i < redCommand.getCreatedWarrior().size()) {
				Warrior warrior = redCommand.getCreatedWarrior().get(i);
				System.out
						.printf("%03d %s %s %d born with strength %d,%d %s in %s headquarter\n",
								i, redCommand.getCommandType(),
								warrior.getWarriorProfession(), i + 1,
								warrior.getHealthPoint(),
								redCommand.getWarriorIndex(warrior) + 1,
								warrior.getWarriorProfession(),
								redCommand.getCommandType());
			} else if (!printedRedStops) {
				System.out.printf(
						"%03d %s headquarter stops making warriors\n",
						redCommand.getCreatedWarrior().size(),
						redCommand.getCommandType());
				printedRedStops = true;
			}

			if (i < blueCommand.getCreatedWarrior().size()) {
				Warrior warrior = blueCommand.getCreatedWarrior().get(i);
				System.out
						.printf("%03d %s %s %d born with strength %d,%d %s in %s headquarter\n",
								i, blueCommand.getCommandType(),
								warrior.getWarriorProfession(), i + 1,
								warrior.getHealthPoint(),
								blueCommand.getWarriorIndex(warrior) + 1,
								warrior.getWarriorProfession(),
								blueCommand.getCommandType());

			} else if (!printedBlueStops) {
				System.out.printf(
						"%03d %s headquarter stops making warriors\n",
						blueCommand.getCreatedWarrior().size(),
						blueCommand.getCommandType());
				printedBlueStops = true;
			}
		}
	}

}
