import contracts.MineGenerator;

import java.util.Random;

public class MineGeneratorImpl implements MineGenerator {
    public static final String MINE = "      *";

    public MineGeneratorImpl() {
    }

    public String[][] placeMines(String[][] board, int quantity) {
        Random random = new Random();
        int count = 0;
        int maxRowsCols = board.length;
        int randomRow;
        int randomCol;
        while (count != quantity) {
            randomRow = random.nextInt(maxRowsCols - 1)  + 1;
            randomCol = random.nextInt(maxRowsCols - 1) + 1;
            if(board[randomRow][randomCol].equals(BoardGeneratorImpl.UNEXPLORED)) {
                board[randomRow][randomCol] = MINE;
                count++;
            }
        }
//        for (String[] strings : board) {
//            for (int j = 0; j < board.length; j++) {
//                System.out.print(strings[j]);
//            }
//            System.out.println();
//        }
        return board;
    }
}
