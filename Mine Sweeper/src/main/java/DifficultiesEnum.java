import contracts.Difficulties;

public enum DifficultiesEnum implements Difficulties {

    BEGINNER(10, 10),
    INTERMEDIATE(17, 40),
    ADVANCED(25, 99);

    private int boardSize;
    private int mineNumber;

     DifficultiesEnum(int boardSize, int mineNumber) {
        this.boardSize = boardSize;
        this.mineNumber = mineNumber;
    }

    public int getBoardSize(Difficulties difficulty) {
        return this.boardSize;
    }
    public int getMineNumber(Difficulties difficulty) {
        return this.mineNumber;
    }
}
