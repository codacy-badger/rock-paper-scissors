package rps;

public class DuplicatePlayerException extends RpsException {
    private static final long serialVersionUID = 201807051252L;

    public DuplicatePlayerException(String message) {
        super(message);
    }
}
