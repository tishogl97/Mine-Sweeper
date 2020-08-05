import contracts.*;

import java.util.Scanner;

public class GameplayImpl implements Gameplay {

    private Scanner input;
    private ChooseDifficulty chooseDifficulty;
    private BoardGenerator boardGenerator;
    private MineGenerator mineGenerator;

    public GameplayImpl(Scanner input, ChooseDifficulty chooseDifficulty, BoardGenerator boardGenerator, MineGenerator mineGenerator) {
        this.input = input;
        this.chooseDifficulty = chooseDifficulty;
        this.boardGenerator = boardGenerator;
        this.mineGenerator = mineGenerator;
    }

    public void startPlaying() {
        Difficulties difficulty = chooseDifficulty.setDifficulty();
        String[][] visualBoard = boardGenerator.showBoard(difficulty);
        String[][] gameplayBoard = new String[difficulty.getBoardSize(difficulty)][difficulty.getBoardSize(difficulty)];
        System.out.println("Enter your move, (row, column):");
        int rowMove = input.nextInt();
        int colMove = input.nextInt();
        while (true) {
            if (rowMove < 1 || rowMove > difficulty.getBoardSize(difficulty)
                    || colMove < 1 || colMove > difficulty.getBoardSize(difficulty)) {
                System.out.printf("Invalid row or column! Row and Column should be between 1 and %d!%nEnter move again:%n"
                        , difficulty.getBoardSize(difficulty));
                rowMove = input.nextInt();
                colMove = input.nextInt();
            } else {
                gameplayBoard[rowMove][colMove] = "holder";
                break;
            }
        }
        mineGenerator.placeMines(gameplayBoard, difficulty)
    }
}
