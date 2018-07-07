package rps;

public class RegisteredPlayer extends Player implements Greetings {

    RegisteredPlayer(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[REG] " + getName() + " | round: " + getRounds();
    }
}