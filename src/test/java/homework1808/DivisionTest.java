package homework1808;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DivisionTest {
    private final Calculator calculator = new Calculator();

    @Test
    public void testDivide() {
        Assert.assertEquals(calculator.divide(10.0, 2.0), 5.0);
    }
}