package screens.impl;

import entity.WordEntry;
import entity.Difficulty;
import org.apache.logging.log4j.LogManager;
import utils.DictionaryLoader;
import utils.exceptions.DictionaryFileException;
import utils.exceptions.DictionaryFileFormatException;
import utils.exceptions.DictionaryFileNotFoundException;
import utils.exceptions.DictionaryFileValidationException;
import engine.GameStateController;
import org.apache.logging.log4j.Logger;
import screens.Screen;
import screens.ScreenManager;
import utils.GameText;
import utils.InputValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MainMenuScreen extends Screen {

    private static final Logger log = LogManager.getLogger(MainMenuScreen.class);

    public MainMenuScreen(ScreenManager manager, Scanner scanner, GameStateController controller) {
        super(manager, scanner, controller);
    }

    private String selectDictionaryName(int input) {
        return input == 1 ?
            GameText.Files.ITERNAL_DICTIONARY :
             GameText.Files.EXTERNAL_DICTIONARY;
    }

   private Map<String, List<WordEntry>> selectDictionary(String filePath) {
        Map<String, List<WordEntry>> dictionary = new HashMap<>();
       log.info("Attempt to upload {}...", filePath);

       try {
           dictionary = DictionaryLoader.load(filePath);
           log.info("{} is uploaded", filePath);
       } catch (DictionaryFileNotFoundException e) {
           log.error(e.getMessage());
           System.out.println(GameText.Errors.FILE_NOT_FOUND_ERROR);
           controller.stopGame();
       } catch (DictionaryFileFormatException e) {
           log.error(e.getMessage());
           System.out.println(GameText.Errors.FORMAT_ERROR);
           controller.stopGame();
       } catch (DictionaryFileValidationException e) {
           log.error(e.getMessage());
           System.out.println(GameText.Errors.VALIDATION_ERROR);
           controller.stopGame();
       } catch (DictionaryFileException e) {
           log.error(e.getMessage());
           System.out.println(GameText.Errors.DICTIONARY_ERROR);
           controller.stopGame();
       }

       return dictionary;
   }

   private Difficulty selectDifficulty(int input) {
        return input == 0 ? Difficulty.random() : Difficulty.values()[input - 1];
   }

    @Override
    public void display() {
        System.out.println(GameText.MainMenu.CHOOSE_DICTIONARY);
        int input = InputValidator.intInput(scanner, 1, 2);
        String filePath = selectDictionaryName(input);

        Map<String, List<WordEntry>> dictionary = selectDictionary(filePath);
        if (dictionary.isEmpty()){
            return;
        }
        List<String> topics = new ArrayList<>(dictionary.keySet());
        System.out.println(GameText.MainMenu.CHOOSE_TOPIC + "\n" + GameText.MainMenu.RANDOM_OPTION);
        topics.forEach(topic -> System.out.println(topics.indexOf(topic) + 1 + ". " + topic));

        input = InputValidator.intInput(scanner, 0, topics.size());
        Random random = new Random();
        String topic = topics.get(input == 0 ? random.nextInt(topics.size()) : input - 1);
        log.info("Topic {} is selected", topic);
        List<WordEntry> words = dictionary.get(topic);
        System.out.println(GameText.MainMenu.CHOOSE_DIFFICULTY + "\n" + GameText.MainMenu.RANDOM_OPTION);
        Arrays.stream(Difficulty.values())
            .forEach(diff -> System.out.println(diff.ordinal() + 1 + ". " + diff));
        input = InputValidator.intInput(scanner, 0, 4);
        Difficulty difficulty = selectDifficulty(input);
        log.info("Difficulty {} is selected", difficulty);
        manager.setCurrentScreen(new GameScreen(manager, scanner, controller, topic, words.get(random.nextInt(words.size())), difficulty));
    }
}
