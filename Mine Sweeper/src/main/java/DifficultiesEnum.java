import contracts.Difficulties;

public enum DifficultiesEnum implements Difficulties {

    BEGINNER(10, 0), INTERMEDIATE(17, 1), ADVANCED(25, 2);

    private int boardSize;
    private int mineNumber;

    DifficultiesEnum(int boardSize, int mineNumber) {
        this.boardSize = boardSize;
        this.mineNumber = mineNumber;
    }

    public int getBoardSize(Difficulties difficulty) {
        return this.boardSize;
    }
}
