package rps;

public abstract class Player {

    private String _name;
    private int _rounds;
    private int _winCount;

    Player(String name) {
        _name = name;
        _rounds = 1;
        _winCount = 0;
    }

    public String getName() {
        return _name;
    }

    public int getRounds() {
        return _rounds;
    }

    public int getWins() {
        return _winCount;
    }

    public synchronized void addRound() {
        if (_rounds < Integer.MAX_VALUE) {
            _rounds++;
        }
    }

    public synchronized void addWin() {
        if (_winCount < Integer.MAX_VALUE) {
            _winCount++;
        }
    }

    @Override
    public String toString() {
        return getName() + " | Win: " + getWins() + " | round: " + getRounds();
    }
}