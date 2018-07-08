package rps;

public class GuestPlayer extends Player {

    GuestPlayer(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[GUEST] " + super.toString();
    }
}