package homework1808;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SubtractionTest {
    private final Calculator calculator = new Calculator();

    @Test
    public void testSubtract() {
        Assert.assertEquals(calculator.subtract(10.0, 4.0), 6.0);
    }
}