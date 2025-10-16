package screens.impl;

import engine.GameStateController;
import entity.WordEntry;
import entity.Difficulty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import screens.Screen;
import screens.ScreenManager;
import utils.GameText;
import utils.InputValidator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class GameScreen extends Screen {
    public static final Logger log = LogManager.getLogger(GameScreen.class);
    public final String[] frames = GameText.Game.FRAMES;
    private final String topic;
    private final String word;
    private final String hint;
    private final Difficulty difficulty;
    private boolean isWinner;
    private final char[] currentState;
    private int revealedLetters;
    private int attempts;
    private final Set<Character> usedLetters;

    public GameScreen(ScreenManager manager, Scanner scanner, GameStateController controller, String topic, WordEntry wordEntry, Difficulty difficulty) {
        super(manager, scanner, controller);
        this.topic = topic;
        this.word = wordEntry.word().toUpperCase();
        this.hint = wordEntry.hint();
        this.difficulty = difficulty;
        this.isWinner = false;

        this.currentState = new char[word.length()];
        Arrays.fill(currentState, '_');
        this.revealedLetters = 0;
        this.attempts = frames.length - 1;
        this.usedLetters = new HashSet<>();
    }

    private boolean checkLetter(char guess) {
        boolean isPresent = false;
        if (word.indexOf(guess) != -1) {
            log.info("Right guess");
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    currentState[i] = guess;
                    revealedLetters++;
                    isPresent = true;
                }
            }
        }
        return isPresent;
    }

    private boolean checkWin(char guess) {
        if (!checkLetter(guess)) {
            log.info("Wrong guess");
            attempts -= 6 / difficulty.getMaxAttempts();
        } else if (revealedLetters == word.length()) {
            isWinner = true;
            return true;
        }
        return false;
    }

    private void displayHUD(int attempts) {
        System.out.println(frames[frames.length - attempts - 1] +
            GameText.Game.TOPIC + topic + "\n" +
            GameText.Game.DIFFICULTY + difficulty.getMode() + "\n" +
            GameText.Game.HIDDEN_WORD + new String(currentState) + "\n" +
            GameText.Game.USED_LETTERS+ usedLetters.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(" ")) + "\n" +
                (attempts - 6 / difficulty.getMaxAttempts() <= 0 ? GameText.Game.HINT + hint + "\n" : "") +
            GameText.Game.CHOOSE_LETTER);
    }

    private String getLastFrame() {
        return frames[frames.length - 1];
    }

    @Override
    public void display() {
        log.info("Word {} is selected", word);
        while (attempts > 0) {
            displayHUD(attempts);
            String input = scanner.nextLine().trim();
            if (input.length() != 1) {
                System.out.println(GameText.Errors.WORD_LENGTH_ERROR + input.length());
                continue;
            }

            char guess = input.toUpperCase().charAt(0);
            log.info("Player guesses letter {}", guess);
            if (!(InputValidator.isRussianLetter(guess))) {
                System.out.println(GameText.Errors.NOT_RUSSIAN_LETTER_ERROR);
                continue;
            }

            if (usedLetters.contains(guess)) {
                System.out.println(GameText.Errors.USED_LETTER_ERROR + guess + "'!");
                continue;
            }

            usedLetters.add(guess);
            if (checkWin(guess)) {
                break;
            }
        }
        if (isWinner) {
            log.info("Player has won");
            System.out.println(GameText.Game.FOR_WINNER + word);
            manager.setCurrentScreen(new StartEndScreen(manager, scanner, controller, ""));
        } else {
            log.info("Player has lost. His word state is {}", new String(currentState));
            System.out.println(getLastFrame() + GameText.Game.FOR_LOSER + word);
            manager.setCurrentScreen(new StartEndScreen(manager, scanner, controller,  ""));
        }
    }
}
