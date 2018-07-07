package rps;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestBet {

    @Test
    public void testPaperBetTie() {
        BetOption firstBet = new Paper();
        BetOption secondBet = new Paper();
        String text = new Bet(firstBet, secondBet).winner();
        Assert.assertTrue(text.startsWith("tie"));
    }

    @Test
    public void testRockBetTie() {
        BetOption firstBet = new Rock();
        BetOption secondBet = new Rock();
        String text = new Bet(firstBet, secondBet).winner();
        Assert.assertTrue(text.startsWith("tie"));
    }

    @Test
    public void testScissorsBetTie() {
        BetOption firstBet = new Scissors();
        BetOption secondBet = new Scissors();
        String text = new Bet(firstBet, secondBet).winner();
        Assert.assertTrue(text.startsWith("tie"));
    }

    @Test
    public void testScissorsLossesRock() {
        BetOption firstBet = new Scissors();
        BetOption secondBet = new Rock();
        String text = new Bet(firstBet, secondBet).winner();
        Assert.assertTrue(text.startsWith("Sorry"));
    }

    @Test
    public void testRockWinsScissors() {
        BetOption firstBet = new Rock();
        BetOption secondBet = new Scissors();
        String text = new Bet(firstBet, secondBet).winner();
        Assert.assertTrue(text.startsWith("Congra"));
    }

    @Test
    public void testScissorsWinsPaper() {
        BetOption firstBet = new Scissors();
        BetOption secondBet = new Paper();
        String text = new Bet(firstBet, secondBet).winner();
        Assert.assertTrue(text.startsWith("Congra"));
    }

    @Test
    public void testPaperLossesScissors() {
        BetOption firstBet = new Paper();
        BetOption secondBet = new Scissors();
        String text = new Bet(firstBet, secondBet).winner();
        Assert.assertTrue(text.startsWith("Sorry"));
    }

    @Test
    public void testRockLossesPaper() {
        BetOption firstBet = new Rock();
        BetOption secondBet = new Paper();
        String text = new Bet(firstBet, secondBet).winner();
        Assert.assertTrue(text.startsWith("Sorry"));
    }

    @Test
    public void testPaperWinsRock() {
        BetOption firstBet = new Paper();
        BetOption secondBet = new Rock();
        String text = new Bet(firstBet, secondBet).winner();
        Assert.assertTrue(text.startsWith("Congra"));
    }
}