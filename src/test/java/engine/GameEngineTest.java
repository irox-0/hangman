package engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private String getNormalizedOutput() {
        return testOut.toString().replace("\r\n", "\n");
    }

    @Test
    public void correctWordTest() {
        GameEngine.testMode("окно", "окно");
        assertEquals("окно;POS\n", getNormalizedOutput());
    }
    @Test
    public void incorrectWordTest() {
        GameEngine.testMode("волокно", "толокно");
        assertEquals("*олокно;NEG\n", getNormalizedOutput());
    }

    @Test
    public void differentLengthTest() {
        GameEngine.testMode("тралалело", "тралала");
        assertEquals("", getNormalizedOutput());
    }
}
