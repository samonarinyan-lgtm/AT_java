package homework1808;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MultiplicationTest {
    private final Calculator calculator = new Calculator();

    @Test
    public void testMultiply() {
        Assert.assertEquals(calculator.multiply(3.0, 4.0), 12.0);
    }
}