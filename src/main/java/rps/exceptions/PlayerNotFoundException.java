package rps;

public class PlayerNotFoundException extends Exception {
    private static final long serialVersionUID = 201807051252L;

    private final String _message;

    public PlayerNotFoundException(String message) {
        _message = message;
    }

    public String getMessage() { return _message; }
}
