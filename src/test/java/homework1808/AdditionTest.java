package homework1808;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AdditionTest {
    private final Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        Assert.assertEquals(calculator.add(5.0, 3.0), 8.0);
    }
}