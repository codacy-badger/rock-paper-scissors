package rps;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPlayerStorage {

    @Test
    public void testAddPlayer() {
        PlayerStorage storage = new PlayerStorage();
        storage.addPlayer(new RegisteredPlayer("joao"));
        Assert.assertTrue(storage.size() == 1);
        storage.addPlayer(new RegisteredPlayer("elvas"));
        Assert.assertTrue(storage.size() == 2);
    }

    @Test
    public void testDuplicatePlayer() {
        PlayerStorage storage = new PlayerStorage();
        storage.addPlayer(new RegisteredPlayer("rafael"));
        Assert.assertTrue(storage.size() == 1);
        storage.addPlayer(new RegisteredPlayer("rafael"));
        Assert.assertTrue(storage.size() == 1);
    }

    @Test(expectedExceptions = PlayerNotFoundException.class)
    public void testUnknownPlayer() throws PlayerNotFoundException {
        new PlayerStorage().getPlayerByName("oh noes");
        Assert.fail();
    }

    @Test
    public void testGetPlayer() throws PlayerNotFoundException {
        RegisteredPlayer player = new RegisteredPlayer("sporting");
        PlayerStorage storage = new PlayerStorage();
        storage.addPlayer(player);
        Assert.assertTrue(storage.size() == 1);
        Player storedPlayer = storage.getPlayerByName("sporting");
        Assert.assertEquals(storedPlayer, player);
    }

}