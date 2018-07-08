package rps;

public class RpsException extends Exception {
	private static final long serialVersionUID = 201807051252L;

	private final String _message;

	public RpsException(String message) {
		_message = message;
	}

	public String getMessage() { return _message; }
}