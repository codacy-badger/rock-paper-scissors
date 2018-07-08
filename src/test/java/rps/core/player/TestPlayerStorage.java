package rps;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPlayerStorage {

    @Test
    public void testAddPlayer() throws DuplicatePlayerException {
        PlayerStorage storage = new PlayerStorage();
        storage.addPlayer(new GuestPlayer("joao"));
        Assert.assertTrue(storage.size() == 1);
        storage.addPlayer(new RegisteredPlayer("elvas"));
        Assert.assertTrue(storage.size() == 2);
    }

    @Test(expectedExceptions = DuplicatePlayerException.class)
    public void testDuplicatePlayer() throws DuplicatePlayerException {
        PlayerStorage storage = new PlayerStorage();
        storage.addPlayer(new GuestPlayer("rafael"));
        Assert.assertTrue(storage.size() == 1);
        storage.addPlayer(new RegisteredPlayer("rafael"));
        Assert.fail();
    }

    @Test(expectedExceptions = PlayerNotFoundException.class)
    public void testUnknownPlayer() throws PlayerNotFoundException {
        new PlayerStorage().getPlayerByName("oh noes");
        Assert.fail();
    }

    @Test
    public void testGetPlayer() throws PlayerNotFoundException, DuplicatePlayerException {
        Player player = new GuestPlayer("sporting");
        PlayerStorage storage = new PlayerStorage();
        storage.addPlayer(player);
        Assert.assertTrue(storage.size() == 1);
        Player storedPlayer = storage.getPlayerByName("sporting");
        Assert.assertEquals(storedPlayer, player);
    }

}