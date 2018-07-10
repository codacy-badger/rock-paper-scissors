package rps;

import java.util.Objects;

public class Rock extends BetOption {
    Rock() {
        super("Rock");
    }

    @Override
    public BetOption[] hasAnEdge() {
        return new BetOption[] {new Scissors()};
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Rock && Objects.equals(getName(), ((Rock) o).getName());
    }
}