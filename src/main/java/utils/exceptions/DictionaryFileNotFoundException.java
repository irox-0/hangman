package utils.exceptions;

public class DictionaryFileNotFoundException extends DictionaryFileException {

    public DictionaryFileNotFoundException(String filePath) {
        super("Dictionary file was not found: " + filePath, filePath);
    }
}
