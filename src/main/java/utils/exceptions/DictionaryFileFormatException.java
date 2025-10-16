package utils.exceptions;

public class DictionaryFileFormatException extends DictionaryFileException {

    public DictionaryFileFormatException(String filePath, Throwable cause) {
        super("Invalid YAML format in file " + filePath, filePath, cause);
    }

}
