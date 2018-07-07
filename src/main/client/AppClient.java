package rps;

public class AppClient {
    public static void main(String[] args) {
        try {
            new Client().run();
        } catch (ServerException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            System.out.println("------ Client(Off) -----");
        }
    }
}