package rps;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRock {

    @Test
    public void testEqualRock() {
        Assert.assertEquals(new Rock(), new Rock());
    }

    @Test
    public void testHasEdgeScissors() {
        BetOption[] bets = new Rock().hasAnEdge();
        Assert.assertTrue(bets.length == 1);
        Assert.assertEquals(bets[0], new Scissors());
    }
}