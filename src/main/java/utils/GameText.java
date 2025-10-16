package utils;


public final class GameText {
    private GameText() {}

    public interface StartEnd {
        String WELCOME = "Добро пожаловать в игру Виселица!";
        String START = "Введите 1, чтобы начать новую игру, или введите 2, чтобы выйти из игры:";
    }

    public interface MainMenu {
        String CHOOSE_DICTIONARY = "Введите 1 для использования внутриигрового словаря:\n" +
            "или\n" +
            "Введите 2 для использования своего словаря (убедитесь, что файл вашего словаря соответсвует названию и структуре внутриигрового):";
        String CHOOSE_TOPIC = "Введите номер темы: ";
        String RANDOM_OPTION = "0. случайная";
        String CHOOSE_DIFFICULTY = "Выберите сложность:";
    }

    public interface Files {
        String ITERNAL_DICTIONARY = "dictionary.yml";
        String EXTERNAL_DICTIONARY = "app-java/config/dictionary.yml";
    }

    public interface DifficultyModes {
        String EASY = "новичок";
        String MEDIUM = "бывалый";
        String HARD = "эксперт";
        String IMPOSSIBLE = "одна ошибка — и ты ошибся";
        String ATTEMPTS = "кол-во попыток: ";
    }

    public interface Errors {
        String INCORRECT_INPUT_ERROR = "Некорректный вод, попробуйте еще раз:";
        String FILE_NOT_FOUND_ERROR = "Файл не найден (ваш файл должен лежать в директории config с расширением yml).";
        String FORMAT_ERROR = "Неверный формат вашего словаря.";
        String VALIDATION_ERROR = "Ваш словарь должен содержать хотя бы 1 непустую тему.";
        String DICTIONARY_ERROR = "Ошибка при считывании файла. Проверьте логи.";
        String WORD_LENGTH_ERROR = "Пожалуйста, введите только одну букву! Сейчас введено: ";
        String NOT_RUSSIAN_LETTER_ERROR = "Пожалуйста, введите русскую букву (А-Я, Ё)!";
        String USED_LETTER_ERROR = "Вы уже вводили букву '";
    }

    public interface Game {
        String TOPIC = "Тема: ";
        String DIFFICULTY = "Сложность: ";
        String HIDDEN_WORD = "Загаданное слово: ";
        String USED_LETTERS = "Использованные буквы: ";
        String CHOOSE_LETTER = "Выберите букву: ";
        String HINT = "Подсказка: ";
        String FOR_WINNER = "Вы победили! Угаданное слово: ";
        String FOR_LOSER = "Вы проиграли! Загаданное слово: ";
        String[] FRAMES = new String[] {
                "   ┌───┐    \n" +
                "   │   │    \n" +
                "   │        \n" +
                "   │        \n" +
                "   │        \n" +
                "   │        \n" +
                " ══╧══════  \n",
                "   ┌───┐    \n" +
                "   │   │    \n" +
                "   │   O    \n" +
                "   │        \n" +
                "   │        \n" +
                "   │        \n" +
                " ══╧══════  \n",
                "   ┌───┐    \n" +
                "   │   │    \n" +
                "   │   O    \n" +
                "   │   │    \n" +
                "   │        \n" +
                "   │        \n" +
                " ══╧══════  \n",
                "   ┌───┐    \n" +
                "   │   │    \n" +
                "   │   O    \n" +
                "   │  /│    \n" +
                "   │        \n" +
                "   │        \n" +
                " ══╧══════  \n",
                "   ┌───┐    \n" +
                "   │   │    \n" +
                "   │   O    \n" +
                "   │  /│\\  \n" +
                "   │        \n" +
                "   │        \n" +
                " ══╧══════  \n",
                "   ┌───┐    \n" +
                "   │   │    \n" +
                "   │   O    \n" +
                "   │  /│\\  \n" +
                "   │  /     \n" +
                "   │        \n" +
                " ══╧══════  \n",
                "   ┌───┐    \n" +
                "   │   │    \n" +
                "   │   O    \n" +
                "   │  /│\\  \n" +
                "   │  / \\  \n" +
                "   │        \n" +
                " ══╧══════  \n"
        };
    }
}
