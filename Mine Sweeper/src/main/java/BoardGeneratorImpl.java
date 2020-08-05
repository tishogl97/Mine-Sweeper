import contracts.BoardGenerator;
import contracts.ChooseDifficulty;
import contracts.Difficulties;
import contracts.MineGenerator;

public class BoardGeneratorImpl implements BoardGenerator {

    public static final String UNEXPLORED = "      -";
    private ChooseDifficulty chooseDifficulty;

    public BoardGeneratorImpl(ChooseDifficulty chooseDifficulty) {
        this.chooseDifficulty = chooseDifficulty;
    }

    public String[][] showBoard(Difficulties difficulty) {
        String[][] board = new String[difficulty.getBoardSize(difficulty)][difficulty.getBoardSize(difficulty)];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if(row == 0 && col == 0) {
                    board[row][col] = "  ";
                } else if (row == 0) {
                    board[row][col] = String.format("  |  %d", col);
                } else if (col == 0) {
                    board[row][col] = String.valueOf(row);
                } else {
                    board[row][col] = UNEXPLORED;
                }
            }
        }
        System.out.println("Current state of the board: ");
        for (String[] strings : board) {
            for (int col = 0; col < board[0].length; col++) {
                System.out.print(strings[col]);
            }
            System.out.println();
        }
        MineGenerator mineGenerator = new MineGeneratorImpl();
        mineGenerator.placeMines(board, 10);
        return board;
    }

    public String[][] gameplayBoard(String[][] gameplayBoard) {


    }
//    private String[][] copyGrid(String[][] board) {
//        int rows = board.length;
//        int cols = board[0].length;
//        String[][] boardCopy = new String[rows][cols];
//        for (int row = 0; row < rows; row++) {
//            System.arraycopy(board[row], 0, boardCopy[row], 0, cols);
//        }
//        return boardCopy;
//    }

}
