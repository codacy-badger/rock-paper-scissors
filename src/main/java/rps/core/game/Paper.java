package rps;

public class Paper extends BetOption {
    Paper() {
        super("Paper");
    }

    @Override
    public BetOption[] hasAnEdge() {
        return new BetOption[] {new Rock()};
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Paper && getName().equals(((Paper) o).getName());
    }
}