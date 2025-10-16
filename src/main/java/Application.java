import engine.GameEngine;


public class Application {

    public static void main(String[] args) {
        if (args.length == 2) {
            GameEngine.testMode(args[0], args[1]);
            return;
        }
        GameEngine engine = GameEngine.init();
        engine.startGame();
    }
}
