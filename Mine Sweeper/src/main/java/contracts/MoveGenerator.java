package contracts;

public interface MoveGenerator {

    boolean makeMove(String[][] gameplayBoard, String[][] visualBoard, int cellRow, int cellCol);
}
