package engine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import screens.ScreenManager;
import screens.impl.StartEndScreen;
import utils.GameText;
import java.util.Arrays;
import java.util.Scanner;

public class GameEngine implements GameStateController {
    private static final Logger log = LogManager.getLogger(GameEngine.class);
    private final Scanner scanner;
    private final ScreenManager screenManager;
    private boolean isRunning;

    private GameEngine(Scanner scanner, ScreenManager screenManager) {
        this.scanner = scanner;
        this.screenManager = screenManager;
    }

    public static GameEngine init() {
        log.info("GameEngine is initializing...");
        return new GameEngine(new Scanner(System.in), new ScreenManager());
    }

    @Override
    public void startGame() {
        log.info("Game has started");
        isRunning = true;
        screenManager.setCurrentScreen(new StartEndScreen(screenManager, scanner, this, GameText.StartEnd.WELCOME));
        run();
        scanner.close();
    }

    private void run() {
        while (isRunning)  {
            screenManager.displayCurrentScreen();
        }
    }

    @Override
    public void stopGame() {
        log.info("Game has ended");
        this.isRunning = false;
    }

    public static void testMode(String hidden, String guessed) {
        log.info("Test mode has started");
        if (hidden.length() != guessed.length()) {
            log.info("Lengths of two words are not equal");
            return;
        }
        if (hidden.equals(guessed)) {
            log.info("Hidden and guessed words {} and {} are equals", hidden, guessed);
            System.out.println(hidden + ";POS");
            return;
        }
        char[] matched = new char[hidden.length()];
        Arrays.fill(matched, '*');
        for (int i = 0; i < hidden.length(); i++) {
            if (hidden.charAt(i) == guessed.charAt(i))
                matched[i] = hidden.charAt(i);
        }
        String intersection = new String(matched);
        System.out.println(intersection + ";NEG");
        log.info("Result of intersection: {}", intersection);
        log.info("Test mode has ended");
    }
}
