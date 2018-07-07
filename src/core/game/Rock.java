package rps;

public class Rock extends BetOption {
    Rock() {
        super("Rock");
    }

    @Override
    public BetOption[] hasAnEdge() {
        return new BetOption[] {new Scissors()};
    }

    @Override
    public final boolean equals(Object o) {
        return o instanceof Rock && getName().equals(((Rock) o).getName());
    }
}