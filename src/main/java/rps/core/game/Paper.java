package rps;

import java.util.Objects;

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
        return o instanceof Paper && Objects.equals(getName(), ((Paper) o).getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}