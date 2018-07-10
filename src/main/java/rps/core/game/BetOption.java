package rps;

import java.util.Objects;

public abstract class BetOption {
    private String _name;

    BetOption (String name) {
        _name = name;
    }

    public abstract BetOption[] hasAnEdge();

    public String getName() {
        return _name;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(_name);
    }

    @Override
    public String toString() {
        return _name;
    }
}