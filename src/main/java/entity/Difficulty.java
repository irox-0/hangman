package entity;

import utils.GameText;
import java.util.Random;

public enum Difficulty {
    EASY(GameText.DifficultyModes.EASY, 6),
    MEDIUM(GameText.DifficultyModes.MEDIUM, 3),
    HARD(GameText.DifficultyModes.HARD, 2),
    IMPOSSIBLE(GameText.DifficultyModes.IMPOSSIBLE, 1);

    private final String mode;
    private final int maxAttempts;

    Difficulty(String mode, int maxAttempts) {
        this.mode = mode;
        this.maxAttempts = maxAttempts;
    }

    public String getMode() {
        return mode;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public static Difficulty random() {
        Difficulty[] values = values();
        return values[new Random().nextInt(values.length)];
    }

    @Override
    public String toString() {
        return mode + " (" + GameText.DifficultyModes.ATTEMPTS + maxAttempts + ")";
    }
}
