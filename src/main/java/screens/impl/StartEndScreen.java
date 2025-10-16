package screens.impl;

import engine.GameStateController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import screens.Screen;
import screens.ScreenManager;
import utils.GameText;
import utils.InputValidator;
import java.util.Scanner;

public class StartEndScreen extends Screen {
    private final String message;
    private static final Logger log = LogManager.getLogger(StartEndScreen.class);

    public StartEndScreen(ScreenManager manager, Scanner scanner, GameStateController controller, String message) {
        super(manager, scanner, controller);
        this.message = message;
    }

    @Override
    public void display() {
//        log.info("StartEndScreen is displaying...");
        System.out.println(message + "\n" + GameText.StartEnd.START);
        int input = InputValidator.intInput(scanner, 1, 2);
        if (input == 1) {
            manager.setCurrentScreen(new MainMenuScreen(manager, scanner, controller));
        } else {
            controller.stopGame();
        }
    }
}
