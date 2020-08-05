import contracts.ChooseDifficulty;
import contracts.Difficulties;

import java.util.Scanner;

public class ChooseDifficultyImpl implements ChooseDifficulty {

    private static final String WRONG_INPUT = "You can only use the numbers 0, 1 and 2! Try again: ";
    private Scanner input;

    public ChooseDifficultyImpl(Scanner input) {
        this.input = input;
    }

    @Override
    public Difficulties setDifficulty() {
        System.out.printf("Choose difficulty level:%nPress 0 for Beginner%nPress 1 for Intermediate%nPress 2 for Advanced%n");
        Difficulties difficulty;
        while (true) {
            try {
                int difficultyInput = Integer.parseInt(input.nextLine());
                if (difficultyInput == 0) {
                    difficulty = DifficultiesEnum.BEGINNER;
                    break;
                } else if (difficultyInput == 1) {
                    difficulty = DifficultiesEnum.INTERMEDIATE;
                    break;
                } else if (difficultyInput == 2) {
                    difficulty = DifficultiesEnum.ADVANCED;
                    break;
                } else {
                    System.out.println(WRONG_INPUT);
                }
            } catch (NumberFormatException ex) {
                System.out.println(WRONG_INPUT);
            }
        }
        return difficulty;
    }
}
