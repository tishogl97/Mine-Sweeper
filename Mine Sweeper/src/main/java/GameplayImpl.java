import contracts.*;

import java.util.Scanner;

public class GameplayImpl implements Gameplay {

    private static final String WRONG_MOVE = "Invalid row or column! Row and Column should be between 1 and %d!%nEnter move again:%n";
    private static final String FIRST_MOVE = "holder";
    private static final String ENTER_MOVE = "Enter your move, (row, column):";
    private Scanner input;
    private ChooseDifficulty chooseDifficulty;
    private BoardGenerator boardGenerator;
    private MineGenerator mineGenerator;
    private MoveGenerator moveGenerator;

    public GameplayImpl(Scanner input, ChooseDifficulty chooseDifficulty, BoardGenerator boardGenerator, MineGenerator mineGenerator, MoveGenerator moveGenerator) {
        this.input = input;
        this.chooseDifficulty = chooseDifficulty;
        this.boardGenerator = boardGenerator;
        this.mineGenerator = mineGenerator;
        this.moveGenerator = moveGenerator;
    }

    public void startPlaying() {
        Difficulties difficulty = chooseDifficulty.setDifficulty();
        String[][] visualBoard = boardGenerator.showBoard(difficulty);
        String[][] gameplayBoard = boardGenerator.copyBoard(visualBoard);
        System.out.println(ENTER_MOVE);
        String[] move = input.nextLine().replaceAll(" ", "").split("");
        int rowMove = Integer.parseInt(move[0]);
        int colMove = Integer.parseInt(move[1]);
        while (true) {
            if (rowMove < 1 || rowMove > difficulty.getBoardSize(difficulty)
                    || colMove < 1 || colMove > difficulty.getBoardSize(difficulty)) {
                System.out.printf(WRONG_MOVE
                        , difficulty.getBoardSize(difficulty));
                rowMove = input.nextInt();
                colMove = input.nextInt();
            } else {
                gameplayBoard[rowMove][colMove] = FIRST_MOVE;
                break;
            }
        }
        mineGenerator.placeMines(gameplayBoard, difficulty.getMineNumber(difficulty));
        boardGenerator.gameplayBoard(gameplayBoard);
        visualBoard[rowMove][colMove] = gameplayBoard[rowMove][colMove];
        for (String[] strings : visualBoard) {
            for (int cols = 0; cols < visualBoard.length; cols++) {
                System.out.print(strings[cols]);
            }
            System.out.println();
        }
        boolean gameOver = true;
        while (gameOver) {
            System.out.println(ENTER_MOVE);
            move = input.nextLine().split(" ");
            try {
                rowMove = Integer.parseInt(move[0]);
                colMove = Integer.parseInt(move[1]);
            } catch (NumberFormatException ex ){
                System.out.println("The format is -> 1 5");
            }
            while (true) {
                if (rowMove < 1 || rowMove > difficulty.getBoardSize(difficulty)
                        || colMove < 1 || colMove > difficulty.getBoardSize(difficulty)) {
                    System.out.printf(WRONG_MOVE
                            , difficulty.getBoardSize(difficulty));
                    rowMove = input.nextInt();
                    colMove = input.nextInt();
                } else if(!gameplayBoard[rowMove][colMove].equals(MoveGeneratorImpl.OPEN_CELL)) {
                    visualBoard[rowMove][colMove] = gameplayBoard[rowMove][colMove];
                    break;
                }
            }
            gameOver = checkGameEnded(visualBoard, gameplayBoard, rowMove, colMove, difficulty);
            for (String[] strings : visualBoard) {
                for (int cols = 0; cols < visualBoard.length; cols++) {
                    System.out.print(strings[cols]);
                }
                System.out.println();
            }
        }
    }

    private boolean checkGameEnded(String[][] visualBoard, String[][] gameplayBoard, int rowMove, int colMove, Difficulties difficulty) {
        int countCells = 0;
        if(!moveGenerator.makeMove(gameplayBoard, visualBoard, rowMove, colMove)) {
            return false;
        }
        for (String[] strings : visualBoard) {
            for (int cols = 0; cols < visualBoard[0].length; cols++) {
                if (strings[cols].equals(BoardGeneratorImpl.UNEXPLORED)) {
                    countCells++;
                }
            }
        }
        if(countCells == difficulty.getMineNumber(difficulty)) {
            System.out.println("YOU WIN! THE REST ARE ONLY MINES!");
            return false;
        }
        return true;
    }
}
