package rps;

public class AvailableBetOption {
    public BetOption[] getAll() {
        return new BetOption[] {new Rock(), new Scissors(), new Paper()};
    }
}
