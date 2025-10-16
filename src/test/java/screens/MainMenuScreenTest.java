package screens;

import engine.GameEngine;
import engine.GameStateController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screens.impl.MainMenuScreen;
import utils.GameText;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainMenuScreenTest {
    private ByteArrayOutputStream testOut;
    private MainMenuScreen screen;
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
    public void chooseDictionaryMessageTest() {
        String expectedOutput = GameText.MainMenu.CHOOSE_DICTIONARY + "\n" ;
        Scanner scanner = new Scanner("1\n1\n1\n1");
        screen = new MainMenuScreen(manager, scanner, engine);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void chooseTopicMessageTest() {
        String expectedOutput = GameText.MainMenu.CHOOSE_TOPIC + "\n";
        Scanner scanner = new Scanner("1\n1\n1\n");
        screen = new MainMenuScreen(manager, scanner, engine);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void chooseDifficultyMessageTest() {
        String expectedOutput = GameText.MainMenu.CHOOSE_DIFFICULTY + "\n";
        Scanner scanner = new Scanner("1\n1\n1\n");
        screen = new MainMenuScreen(manager, scanner, engine);
        screen.display();
        assertTrue(getNormalizedOutput().contains(expectedOutput));
    }

    @Test
    public void incorrectDictionaryNumberTest() {
        Scanner scanner = new Scanner("1\n1\n9\n");
        screen = new MainMenuScreen(manager, scanner, engine);
        assertThrows(Exception.class, () -> screen.display());
        assertTrue(getNormalizedOutput().contains(GameText.Errors.INCORRECT_INPUT_ERROR));
    }
}
