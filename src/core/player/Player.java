package rps;

public abstract class Player {

    private String _name;
    private int _rounds;

    Player(String name) {
        _name = name;
        _rounds = 1;
    }

    public String getName() {
        return _name;
    }

    public int getRounds() {
        return _rounds;
    }

    public synchronized void addRound() {
        if (_rounds < Integer.MAX_VALUE) {
            _rounds++;
        }
    }

    @Override
    public String toString() {
        return getName() + " | round: " + getRounds();
    }
}