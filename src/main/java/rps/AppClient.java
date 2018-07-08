package rps;

public class AppClient {
    public static void main(String[] args) {
        try {
            new Client().start();
        } catch (RpsException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            System.out.println("----- Client(Off) -----");
        }
    }
}