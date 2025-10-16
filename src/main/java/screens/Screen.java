package screens;

import engine.GameStateController;
import java.util.Scanner;

public abstract class Screen {
    protected final ScreenManager manager;
    protected final Scanner scanner;
    protected final GameStateController controller;

    public Screen(ScreenManager manager, Scanner scanner, GameStateController controller) {
        this.manager = manager;
        this.scanner = scanner;
        this.controller = controller;
    }

    public abstract void display();
}
