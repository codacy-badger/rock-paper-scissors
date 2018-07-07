package rps;

public class PlayerException extends Exception {
    private static final long serialVersionUID = 201307031214L;

    private final String _message;

    public PlayerException(String message) {
        _message = message;
    }

    public String getMessage() { return _message; }
}
