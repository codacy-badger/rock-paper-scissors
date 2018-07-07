package rps;

import java.util.HashMap;
import java.util.Map;

public class PlayerStorage {

    private Map<String, Player> _players = new HashMap<String, Player>();

    public Player getPlayerByName(String name) throws PlayerNotFoundException {
        if (_players.containsKey(name)) {
            return _players.get(name);
        }
        throw new PlayerNotFoundException(name);
    }

    public void addPlayer(Player player) throws PlayerException {
        String name = player.getName();
        if (!_players.containsKey(name)) {
            _players.put(name, player);
            return;
        }
        throw new PlayerException(name);
    }

    public void removePlayer(Player player) {
        throw new UnsupportedOperationException("Cannot remove player " + player);
    }

}