package screens;

import engine.GameEngine;
import engine.GameStateController;
import entity.WordEntry;
import entity.Difficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screens.impl.GameScreen;
import utils.GameText;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameScreenTest {
    private ByteArrayOutputStream testOut;
    private GameScreen screen;
    private final ScreenManager manager = new ScreenManager();
    private final GameStateController engine = GameEngine.init();


    @BeforeEach
    public void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private String getNormalizedOutput() {
        return testOut.toString().replace("\r\n", "\n");
    }

    @Test
    public void impossibleModeNegativeTest() {
        String expectedOutput = GameText.Game.FRAMES[6] + GameText.Game.FOR_LOSER;
        Scanner scanner = new Scanner("а\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.IMPOSSIBLE);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void impossibleModePositiveTest() {
        String expectedOutput = GameText.Game.FOR_WINNER;
        Scanner scanner = new Scanner("т\nе\nс\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.IMPOSSIBLE);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void hardModeNegativeTest() {
        String expectedOutput = GameText.Game.FRAMES[6] + GameText.Game.FOR_LOSER;
        Scanner scanner = new Scanner("а\nи\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.HARD);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void hardModePositiveTest() {
        String expectedOutput = GameText.Game.FOR_WINNER;
        Scanner scanner = new Scanner("Т\nе\nс\n");
        screen = new GameScreen(manager, scanner, engine,"тест", new WordEntry("тест", "это тест"), Difficulty.HARD);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }


    @Test
    public void mediumModeNegativeTest() {
        String expectedOutput = GameText.Game.FRAMES[6] + GameText.Game.FOR_LOSER;
        Scanner scanner = new Scanner("а\nи\nо\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.MEDIUM);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void mediumModePositiveTest() {
        String expectedOutput = GameText.Game.FOR_WINNER;
        Scanner scanner = new Scanner("т\nе\nС\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.MEDIUM);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }


    @Test
    public void easyModeNegativeTest() {
        String expectedOutput = GameText.Game.FRAMES[6] + GameText.Game.FOR_LOSER;
        Scanner scanner = new Scanner("а\nи\nо\nй\nв\nм\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.EASY);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void easyModePositiveTest() {
        String expectedOutput = GameText.Game.FOR_WINNER;
        Scanner scanner = new Scanner("Т\nЕ\nС\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.EASY);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void easyModeHintTest() {
        String expectedOutput = GameText.Game.HINT;
        Scanner scanner = new Scanner("м\nи\nй\nк\nн\nъ\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.EASY);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void mediumModeHintTest() {
        String expectedOutput = GameText.Game.HINT;
        Scanner scanner = new Scanner("м\nи\nй\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.MEDIUM);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void impossibleModeHintTest() {
        String expectedOutput = GameText.Game.HINT;
        Scanner scanner = new Scanner("м\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.IMPOSSIBLE);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void hardModeHintTest() {
        String expectedOutput = GameText.Game.HINT;
        Scanner scanner = new Scanner("м\nи\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.HARD);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void noHintTest() {
        String expectedOutput = GameText.Game.HINT;
        Scanner scanner = new Scanner("т\nи\nе\nр\nс\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.EASY);
        screen.display();
        assertFalse(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void wordContainsRightLetterTest() {
        String[] expectedOutput = new String[]{"____", "Т__Т", "ТЕ_Т", "ТЕСТ"};
        Scanner scanner = new Scanner("т\nе\nс\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.IMPOSSIBLE);
        screen.display();
        for (String expectedWordState : expectedOutput) {
            assertTrue(getNormalizedOutput().contains(expectedWordState));
        }
    }

    @Test
    public void incorrectLetterLengthInputTest() {
        String expectedOutput = GameText.Errors.WORD_LENGTH_ERROR;
        Scanner scanner = new Scanner("ыва\n");
        screen = new GameScreen(manager, scanner, engine, "тест", new WordEntry("тест", "это тест"), Difficulty.IMPOSSIBLE);
        assertThrows(Exception.class, () -> screen.display());
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void incorrectLetterLanguageInputTest() {
        String expectedOutput = GameText.Errors.NOT_RUSSIAN_LETTER_ERROR;
        Scanner scanner = new Scanner("l\n");
        screen = new GameScreen(manager, scanner, engine,"тест", new WordEntry("тест", "это тест"), Difficulty.IMPOSSIBLE);
        assertThrows(Exception.class, () -> screen.display());
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void usedInputLetterTest() {
        String expectedOutput = GameText.Errors.USED_LETTER_ERROR;
        Scanner scanner = new Scanner("ф\nф\n");
        screen = new GameScreen(manager, scanner, engine,"тест", new WordEntry("тест", "это тест"), Difficulty.EASY);
        assertThrows(Exception.class, () -> screen.display());
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }
}
