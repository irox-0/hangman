package screens;

import engine.GameEngine;
import engine.GameStateController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import screens.impl.StartEndScreen;
import utils.GameText;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class StartEndScreenTest {
    private ByteArrayOutputStream testOut;
    private StartEndScreen screen;
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
    public void welcomeMessageTest() {
        String expectedOutput = GameText.StartEnd.WELCOME + "\n" + GameText.StartEnd.START + "\n";
        Scanner scanner = new Scanner("1\n");
        screen = new StartEndScreen(manager, scanner, engine, GameText.StartEnd.WELCOME);
        screen.display();
        assertEquals(expectedOutput, getNormalizedOutput());
    }

    @Test
    public void incorrectInputTest() {
        Scanner scanner = new Scanner("6\n");
        screen = new StartEndScreen(manager, scanner, engine, GameText.StartEnd.WELCOME);
        assertThrows(Exception.class, () -> screen.display());
        assertTrue(getNormalizedOutput().contains(GameText.Errors.INCORRECT_INPUT_ERROR));
    }
}
