package rps;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPlayerStorage {

    @Test(expectedExceptions = PlayerNotFoundException.class)
    public void testUnknownPlayer() throws PlayerNotFoundException {
        new PlayerStorage().getPlayerByName("oh noes");
        Assert.fail("This method was not supposed to be executed");
    }

}