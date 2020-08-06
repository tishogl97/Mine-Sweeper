import contracts.MoveGenerator;

import java.util.ArrayList;
import java.util.Stack;

public class MoveGeneratorImpl implements MoveGenerator {
    private static final String OPEN_CELL = "Open";

    public MoveGeneratorImpl() {
    }

    public boolean makeMove(String[][] gameplayBoard, String[][] visualBoard, int cellRow, int cellCol) {
        String cell = gameplayBoard[cellRow][cellCol].replaceAll(" ", "");
        if (cell.equals("*")) {
            System.out.println("GAME OVER!");
            return false;
        } else if (cell.equals("0")) {
            openCellsAround(gameplayBoard, visualBoard, cellRow, cellCol);
            return true;
        } else {
            visualBoard[cellRow][cellCol] = gameplayBoard[cellRow][cellCol];
            gameplayBoard[cellRow][cellCol] = OPEN_CELL;
            return true;
        }
    }

    private void openCellsAround(String[][] gameplayBoard, String[][] visualBoard, int cellRow, int cellCol) {
        Stack<Integer> zeroCells = new Stack<>();
        String checkCell = "";
        zeroCells.push(cellRow);
        zeroCells.push(cellCol);

        while (!zeroCells.isEmpty()) {
            cellCol = zeroCells.pop();
            cellRow = zeroCells.pop();
            checkTopLeft(gameplayBoard, visualBoard, zeroCells, cellRow, cellCol);
            checkTop(gameplayBoard, visualBoard, zeroCells, cellRow, cellCol);
            checkTopRight(gameplayBoard, visualBoard, zeroCells, cellRow, cellCol);
            checkLeft(gameplayBoard, visualBoard, zeroCells, cellRow, cellCol);
            checkRight(gameplayBoard, visualBoard, zeroCells, cellRow, cellCol);
            checkBotLeft(gameplayBoard, visualBoard, zeroCells, cellRow, cellCol);
            checkBot(gameplayBoard, visualBoard, zeroCells, cellRow, cellCol);
            checkBotRight(gameplayBoard, visualBoard, zeroCells, cellRow, cellCol);
        }
    }

    private void checkTopLeft(String[][] gameplayBoard, String[][] visualBoard, Stack<Integer> zeroCells, int cellRow, int cellCol) {
        if (cellRow - 1 > 0 && cellCol - 1 > 0) {
            String topLeft = gameplayBoard[cellRow - 1][cellCol - 1].replaceAll(" ", "");
            if (topLeft.equals("0")) {
                visualBoard[cellRow - 1][cellCol - 1] = gameplayBoard[cellRow - 1][cellCol - 1];
                gameplayBoard[cellRow - 1][cellCol - 1] = OPEN_CELL;
                zeroCells.push(cellRow - 1);
                zeroCells.push(cellCol - 1);
            } else if(!gameplayBoard[cellRow - 1][cellCol - 1].equals(OPEN_CELL)){
                visualBoard[cellRow - 1][cellCol - 1] = gameplayBoard[cellRow - 1][cellCol - 1];
                gameplayBoard[cellRow - 1][cellCol - 1] = OPEN_CELL;
            }
        }
    }

    private void checkTop(String[][] gameplayBoard, String[][] visualBoard, Stack<Integer> zeroCells, int cellRow, int cellCol) {
        if (cellRow - 1 > 0) {
            String top = gameplayBoard[cellRow - 1][cellCol].replaceAll(" ", "");
            if (top.equals("0")) {
                visualBoard[cellRow - 1][cellCol] = gameplayBoard[cellRow - 1][cellCol];
                gameplayBoard[cellRow - 1][cellCol] = OPEN_CELL;
                zeroCells.push(cellRow - 1);
                zeroCells.push(cellCol);
            } else if(!gameplayBoard[cellRow - 1][cellCol].equals(OPEN_CELL)){
                visualBoard[cellRow - 1][cellCol] = gameplayBoard[cellRow - 1][cellCol];
                gameplayBoard[cellRow - 1][cellCol] = OPEN_CELL;
            }
        }
    }

    private void checkTopRight(String[][] gameplayBoard, String[][] visualBoard, Stack<Integer> zeroCells, int cellRow, int cellCol) {
        if (cellRow - 1 > 0 && cellCol + 1 < gameplayBoard.length) {
            String topRight = gameplayBoard[cellRow - 1][cellCol + 1].replaceAll(" ", "");
            if (topRight.equals("0")) {
                visualBoard[cellRow - 1][cellCol + 1] = gameplayBoard[cellRow - 1][cellCol + 1];
                gameplayBoard[cellRow - 1][cellCol + 1] = OPEN_CELL;
                zeroCells.push(cellRow - 1);
                zeroCells.push(cellCol + 1);
            } else if(!gameplayBoard[cellRow - 1][cellCol + 1].equals(OPEN_CELL)) {
                visualBoard[cellRow - 1][cellCol + 1] = gameplayBoard[cellRow - 1][cellCol + 1];
                gameplayBoard[cellRow - 1][cellCol + 1] = OPEN_CELL;
            }
        }
    }

    private void checkLeft(String[][] gameplayBoard, String[][] visualBoard, Stack<Integer> zeroCells, int cellRow, int cellCol) {
        if (cellCol - 1 > 0) {
            String left = gameplayBoard[cellRow][cellCol - 1].replaceAll(" ", "");
            if (left.equals("0")) {
                visualBoard[cellRow][cellCol - 1] = gameplayBoard[cellRow][cellCol - 1];
                gameplayBoard[cellRow][cellCol - 1] = OPEN_CELL;
                zeroCells.push(cellRow);
                zeroCells.push(cellCol - 1);
            } else if(!gameplayBoard[cellRow][cellCol - 1].equals(OPEN_CELL)){
                visualBoard[cellRow][cellCol - 1] = gameplayBoard[cellRow][cellCol - 1];
                gameplayBoard[cellRow][cellCol - 1] = OPEN_CELL;
            }
        }
    }

    private void checkRight(String[][] gameplayBoard, String[][] visualBoard, Stack<Integer> zeroCells, int cellRow, int cellCol) {
        if (cellCol + 1 < gameplayBoard.length) {
            String right = gameplayBoard[cellRow][cellCol + 1].replaceAll(" ", "");
            if (right.equals("0")) {
                visualBoard[cellRow][cellCol + 1] = gameplayBoard[cellRow][cellCol + 1];
                gameplayBoard[cellRow][cellCol + 1] = OPEN_CELL;
                zeroCells.push(cellRow);
                zeroCells.push(cellCol);
            } else if(!gameplayBoard[cellRow][cellCol + 1].equals(OPEN_CELL)){
                visualBoard[cellRow][cellCol + 1] = gameplayBoard[cellRow][cellCol + 1];
                gameplayBoard[cellRow][cellCol + 1] = OPEN_CELL;
            }
        }
    }

    private void checkBotLeft(String[][] gameplayBoard, String[][] visualBoard, Stack<Integer> zeroCells, int cellRow, int cellCol) {
        if (cellRow  + 1 < gameplayBoard.length && cellCol - 1 > 0) {
            String botLeft = gameplayBoard[cellRow + 1][cellCol - 1].replaceAll(" ", "");
            if (botLeft.equals("0")) {
                visualBoard[cellRow + 1][cellCol - 1] = gameplayBoard[cellRow + 1][cellCol - 1];
                gameplayBoard[cellRow + 1][cellCol - 1] = OPEN_CELL;
                zeroCells.push(cellRow + 1);
                zeroCells.push(cellCol - 1);
            } else if(!gameplayBoard[cellRow + 1][cellCol - 1].equals(OPEN_CELL)){
                visualBoard[cellRow + 1][cellCol - 1] = gameplayBoard[cellRow + 1][cellCol - 1];
                gameplayBoard[cellRow + 1][cellCol - 1] = OPEN_CELL;
            }
        }
    }

    private void checkBot(String[][] gameplayBoard, String[][] visualBoard, Stack<Integer> zeroCells, int cellRow, int cellCol) {
        if (cellRow + 1 < gameplayBoard.length) {
            String bot = gameplayBoard[cellRow + 1][cellCol].replaceAll(" ", "");
            if (bot.equals("0")) {
                visualBoard[cellRow + 1][cellCol] = gameplayBoard[cellRow + 1][cellCol];
                gameplayBoard[cellRow + 1][cellCol] = OPEN_CELL;
                zeroCells.push(cellRow + 1);
                zeroCells.push(cellCol);
            } else if(!gameplayBoard[cellRow + 1][cellCol].equals(OPEN_CELL)) {
                visualBoard[cellRow + 1][cellCol] = gameplayBoard[cellRow + 1][cellCol];
                gameplayBoard[cellRow + 1][cellCol] = OPEN_CELL;
            }
        }
    }

    private void checkBotRight(String[][] gameplayBoard, String[][] visualBoard, Stack<Integer> zeroCells, int cellRow, int cellCol) {
        if (cellRow + 1 < gameplayBoard.length && cellCol + 1 < gameplayBoard.length) {
            String botRight = gameplayBoard[cellRow + 1][cellCol + 1].replaceAll(" ", "");
            if (botRight.equals("0")) {
                visualBoard[cellRow + 1][cellCol + 1] = gameplayBoard[cellRow + 1][cellCol + 1];
                gameplayBoard[cellRow + 1][cellCol + 1] = OPEN_CELL;
                zeroCells.push(cellRow + 1);
                zeroCells.push(cellCol + 1);
            } else if(!gameplayBoard[cellRow + 1][cellCol + 1].equals(OPEN_CELL)){
                visualBoard[cellRow + 1][cellCol + 1] = gameplayBoard[cellRow + 1][cellCol + 1];
                gameplayBoard[cellRow + 1][cellCol + 1] = OPEN_CELL;
            }
        }
    }
}
