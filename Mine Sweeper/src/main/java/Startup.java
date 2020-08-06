import contracts.*;

import java.util.Scanner;

public class Startup {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ChooseDifficulty chooseDifficulty = new ChooseDifficultyImpl(in);
        BoardGenerator boardGenerator = new BoardGeneratorImpl(chooseDifficulty);
        MineGenerator mineGenerator = new MineGeneratorImpl();
        MoveGenerator moveGenerator = new MoveGeneratorImpl();
        Gameplay gameplay = new GameplayImpl(in, chooseDifficulty, boardGenerator, mineGenerator, moveGenerator);
        gameplay.startPlaying();
    }
}
