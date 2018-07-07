package rps;

public class Scissors extends BetOption {
    Scissors() {
        super("Scissors");
    }

    @Override
    public BetOption[] hasAnEdge() {
        return new BetOption[] {new Paper()};
    }

    @Override
    public final boolean equals(Object o) {
        return o instanceof Scissors && getName().equals(((Scissors) o).getName());
    }
}