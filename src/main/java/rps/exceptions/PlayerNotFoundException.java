package rps;

public class PlayerNotFoundException extends RpsException {
    private static final long serialVersionUID = 201807051252L;

    public PlayerNotFoundException(String message) {
        super(message);
    }
}
