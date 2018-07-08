package rps;

public class AppServer {
    public static void main(String[] args) {
        try {
            new Server().start();
        } catch (ServerException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            System.out.println("------ Server(Off) -----");
        }
    }
}