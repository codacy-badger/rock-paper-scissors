package rps;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestScissors {

    @Test
    public void testEqualScissors() {
        Assert.assertEquals(new Scissors(), new Scissors());
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(new Scissors().hashCode(), new Scissors().hashCode());
    }

    @Test
    public void testHasEdgePaper() {
        BetOption[] bets = new Scissors().hasAnEdge();
        Assert.assertTrue(bets.length == 1);
        Assert.assertEquals(bets[0], new Paper());
    }
}