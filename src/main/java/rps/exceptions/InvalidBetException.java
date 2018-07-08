package rps;

public class InvalidBetException extends RpsException {
    private static final long serialVersionUID = 201807051252L;

    public InvalidBetException(String message) {
        super(message);
    }
}
