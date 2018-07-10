package rps;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPaper {

    @Test
    public void testEqualPaper() {
        Assert.assertEquals(new Paper(), new Paper());
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(new Paper().hashCode(), new Paper().hashCode());
    }

    @Test
    public void testHasEdgeRock() {
        BetOption[] bets = new Paper().hasAnEdge();
        Assert.assertTrue(bets.length == 1);
        Assert.assertEquals(bets[0], new Rock());
    }
}