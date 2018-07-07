package rps;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPaper {

    @Test
    public void testEqualPaper() {
        Assert.assertEquals(new Paper(),new Paper());
    }
}