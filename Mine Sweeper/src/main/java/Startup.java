import contracts.BoardGenerator;
import contracts.ChooseDifficulty;
import contracts.Gameplay;
import contracts.MineGenerator;

import java.util.Scanner;

public class Startup {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ChooseDifficulty chooseDifficulty = new ChooseDifficultyImpl(in);
        BoardGenerator boardGenerator = new BoardGeneratorImpl(chooseDifficulty);
        MineGenerator mineGenerator = new MineGeneratorImpl();
        Gameplay gameplay = new GameplayImpl(in, chooseDifficulty, boardGenerator, mineGenerator);
        boardGenerator.showBoard();
    }
}
