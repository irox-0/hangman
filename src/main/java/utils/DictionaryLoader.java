package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import entity.WordEntry;
import entity.Dictionary;
import utils.exceptions.DictionaryFileException;
import utils.exceptions.DictionaryFileFormatException;
import utils.exceptions.DictionaryFileNotFoundException;
import utils.exceptions.DictionaryFileValidationException;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


public class DictionaryLoader {
    private static final ObjectReader YAML_READER = new ObjectMapper(new YAMLFactory()).findAndRegisterModules().reader();

    public static Map<String, List<WordEntry>> load(String filePath) throws DictionaryFileException {
        File externalDictionary = new File(filePath);
        if (!externalDictionary.exists() && filePath.contains("app-java")) {
            throw new DictionaryFileNotFoundException(filePath);
        }
        try (InputStream inputStream = filePath.contains("app-java") ?
                new FileInputStream(filePath) :
                DictionaryLoader.class.getClassLoader().getResourceAsStream(filePath)) {

            Dictionary dictionary = YAML_READER.readValue(inputStream, Dictionary.class);
            validate(dictionary, filePath);

            return dictionary.data();
        } catch (IOException e) {
            if (e instanceof JsonProcessingException) {
                throw new DictionaryFileFormatException(filePath, e);
            }
            throw new DictionaryFileException(e.getMessage(), filePath, e);
        }
    }

    private static void validate(Dictionary dictionary, String filePath) throws DictionaryFileException {
        dictionary.data().entrySet().removeIf(entry -> entry.getValue() == null || entry.getValue().isEmpty());

        if (dictionary.data().isEmpty()) {
            throw new DictionaryFileValidationException(filePath);
        }
    }
}
