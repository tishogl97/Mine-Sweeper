package contracts;

public interface BoardGenerator {

    String[][] showBoard(Difficulties difficulty);

    void gameplayBoard(String[][] gameplayBoard);

    String[][] copyBoard(String[][] board);
}
