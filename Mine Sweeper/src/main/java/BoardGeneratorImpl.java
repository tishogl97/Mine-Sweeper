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
                    board[row][col] = String.format("%d |", row);
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
//        MineGenerator mineGenerator = new MineGeneratorImpl();
//        mineGenerator.placeMines(board, 10);
        return board;
    }

    public String[][] copyBoard(String[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        String[][] boardCopy = new String[rows][cols];
        for (int row = 0; row < rows; row++) {
            System.arraycopy(board[row], 0, boardCopy[row], 0, cols);
        }
        return boardCopy;
    }

    public void gameplayBoard(String[][] gameplayBoard) {
        String currentCell;
        int minesAround;
        for (int rows = 1; rows < gameplayBoard.length; rows++) {
            for (int cols = 1; cols < gameplayBoard[0].length; cols++) {
                if(!gameplayBoard[rows][cols].equals(MineGeneratorImpl.MINE)) {
                    gameplayBoard[rows][cols] = String.format("     %d", countMinesAround(gameplayBoard, rows, cols));
                }
            }
        }
    }
    private int countMinesAround(String[][] board, int row, int col) {
        int minesAround = 0;
        minesAround = minesAround
                + topLeft(board, row, col)
                + topCenter(board, row, col)
                + topRight(board, row, col)
                + right(board, row, col)
                + left(board, row, col)
                + bottomLeft(board, row, col)
                + bottomCenter(board, row, col)
                +bottomRight(board, row, col);

        return minesAround;
    }

    private int topLeft(String[][] board, int row, int col) {
        int topLeftRow = row - 1;
        int topLeftCol = col - 1;
        if (topLeftRow >= 0 && topLeftCol >= 0 && board[topLeftRow][topLeftCol].equals(MineGeneratorImpl.MINE)) {
            return 1;
        } else {
            return 0;
        }
    }

    private int topCenter(String[][] board, int row, int col) {
        int topCenterRow = row - 1;
        if (topCenterRow >= 0 && board[topCenterRow][col].equals(MineGeneratorImpl.MINE)) {
            return 1;
        } else {
            return 0;
        }
    }

    private int topRight(String[][] board, int row, int col) {
        int topRightRow = row - 1;
        int topRightCol = col + 1;
        int maxCols = board[0].length;
        if (topRightRow >= 0 && topRightCol < maxCols && board[topRightRow][topRightCol].equals(MineGeneratorImpl.MINE)) {
            return 1;
        } else {
            return 0;
        }
    }

    private int right(String[][] board, int row, int col) {
        int rightCol = col + 1;
        int maxCols = board[0].length;
        if (rightCol < maxCols && board[row][rightCol].equals(MineGeneratorImpl.MINE)) {
            return 1;
        } else {
            return 0;
        }
    }

    private int left(String[][] board, int row, int col) {
        int leftCol = col - 1;
        if (leftCol >= 0 && board[row][leftCol].equals(MineGeneratorImpl.MINE)) {
            return 1;
        } else {
            return 0;
        }
    }

    private int bottomLeft(String[][] board, int row, int col) {
        int bottomLeftRow = row + 1;
        int bottomLeftCol = col - 1;
        int maxRows = board.length;
        if (bottomLeftRow < maxRows && bottomLeftCol >= 0 && board[bottomLeftRow][bottomLeftCol].equals(MineGeneratorImpl.MINE)) {
            return 1;
        } else {
            return 0;
        }
    }

    private int bottomCenter(String[][] board, int row, int col) {
        int bottomCenterRow = row + 1;
        int maxRows = board.length;
        if (bottomCenterRow < maxRows && board[bottomCenterRow][col].equals(MineGeneratorImpl.MINE)) {
            return 1;
        } else {
            return 0;
        }
    }

    private int bottomRight(String[][] board, int row, int col) {
        int bottomRightRow = row + 1;
        int bottomRightCol = col + 1;
        int maxRows = board.length;
        int maxCols = board[0].length;
        if (bottomRightRow < maxRows && bottomRightCol < maxCols
                && board[bottomRightRow][bottomRightCol].equals(MineGeneratorImpl.MINE)) {
            return 1;
        } else {
            return 0;
        }
    }

}
