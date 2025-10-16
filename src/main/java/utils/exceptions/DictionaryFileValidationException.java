package utils.exceptions;

public class DictionaryFileValidationException extends DictionaryFileException {

    public DictionaryFileValidationException(String filePath) {
        super("No valid data in file " + filePath, filePath);
    }
}
