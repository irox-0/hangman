package utils;

import utils.exceptions.DictionaryFileException;
import utils.exceptions.DictionaryFileFormatException;
import utils.exceptions.DictionaryFileNotFoundException;
import utils.exceptions.DictionaryFileValidationException;
import org.junit.jupiter.api.Test;
import utils.DictionaryLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class DictionaryLoaderTest {

    @Test
    public void fileFormatTest() {
        assertThrows(DictionaryFileFormatException.class, () -> DictionaryLoader.load("test_incorrect_format_dictionary.yml"));
    }
//    приколы с тестами для класса File
//    @Test
//    public void externalFileAddTest() {
//        assertThrows(DictionaryFileFormatException.class, () -> DictionaryLoader.load("app-java/config/dictionary.yml"));
//    }
//    Этот тест проходит, но по другой причине :):):)
//    @Test
//    public void fileNotFoundTest() {
//        assertThrows(DictionaryFileNotFoundException.class, () -> DictionaryLoader.load("app-java/config/sahur.yml"));
//
//    }

    @Test
    public void validationTest() {
        assertThrows(DictionaryFileValidationException.class, () -> DictionaryLoader.load("test_empty_dictionary.yml"));
    }

    @Test
    public void dataTest() {
        Map<String, List<String>> testData = new HashMap<>();
        assertDoesNotThrow(() -> DictionaryLoader.load("test_dictionary.yml"));
    }

    @Test
    public void test() {
        assertDoesNotThrow(() ->
            DictionaryLoader.load("dictionary.yml")
        );

    }

}
