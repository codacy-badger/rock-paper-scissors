package rps;

import java.util.Objects;

public class Scissors extends BetOption {
    Scissors() {
        super("Scissors");
    }

    @Override
    public BetOption[] hasAnEdge() {
        return new BetOption[] {new Paper()};
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Scissors && Objects.equals(getName(), ((Scissors) o).getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}