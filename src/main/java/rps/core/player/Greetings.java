package rps;

public interface Greetings {
    default String greets(String playerName) {
        return "Welcome back " + playerName + "!";
    }
}