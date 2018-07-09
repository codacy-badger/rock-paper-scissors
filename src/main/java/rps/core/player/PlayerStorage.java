package rps;

import java.util.HashMap;
import java.util.Map;

public class PlayerStorage {

    private Map<String, RegisteredPlayer> _players = new HashMap<String, RegisteredPlayer>();

    public RegisteredPlayer getPlayerByName(String name) throws PlayerNotFoundException {
        if (_players.containsKey(name)) {
            return _players.get(name);
        }
        throw new PlayerNotFoundException(name);
    }

    public int size() {
        return _players.size();
    }

    public void addPlayer(RegisteredPlayer player) {
        String name = player.getName();
        if (!_players.containsKey(name)) {
            _players.put(name, player);
        }
    }

    public void removePlayer(Player player) {
        throw new UnsupportedOperationException("Cannot remove player " + player);
    }

}