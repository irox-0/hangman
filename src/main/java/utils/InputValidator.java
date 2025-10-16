package utils;

import java.util.Scanner;

public class InputValidator {

    private static boolean isNotInteger(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isRussianLetter(char c) {
        return c >= 'А' && c <= 'Я' || c == 'Ё';
    }


    public static int intInput(Scanner scanner, int min, int max) {
        String input = scanner.nextLine().trim();
        while(isNotInteger(input) || Integer.parseInt(input) < min || Integer.parseInt(input) > max) {
            System.out.println(GameText.Errors.INCORRECT_INPUT_ERROR);
            input = scanner.nextLine().trim();
        }
        return Integer.parseInt(input);
    }

}
