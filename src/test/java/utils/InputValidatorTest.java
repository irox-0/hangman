package utils;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
public class InputValidatorTest {
    @Test
    public void isRussianLetterTest() {
        assertTrue(InputValidator.isRussianLetter('Й'));
    }

    @Test
    public void isNotRussianLetterTest() {
        assertFalse(InputValidator.isRussianLetter('L'));
    }

    @Test
    public void correctIntInput() {
        Scanner scanner = new Scanner("1\n");
        int output = InputValidator.intInput(scanner, 1, 2);
        assertEquals(1, output);
    }

    @Test
    public void incorrectIntInput() {
        Scanner scanner = new Scanner("666\n");
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        assertThrows(Exception.class, () ->InputValidator.intInput(scanner, 1, 10));
        assertTrue(testOut.toString().contains(GameText.Errors.INCORRECT_INPUT_ERROR));
    }
}
