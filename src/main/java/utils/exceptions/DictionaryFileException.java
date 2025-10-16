package utils.exceptions;

public class DictionaryFileException extends Exception {
    private final String filePath;

    public DictionaryFileException(String message, String filePath, Throwable cause) {
        super(message, cause);
        this.filePath = filePath;
    }

    protected DictionaryFileException(String message, String filePath) {
        super(message);
        this.filePath = filePath;
    }

}
