package rps;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.PrintStream;

class Client {
    private final int TIMEOUT = 5000;
    private final int PORT = 58038;
    private final String HOSTNAME = "localhost";

    private Socket openConnection() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(HOSTNAME, PORT), TIMEOUT);
        return socket;
    }

    private void makeServerResquest(PrintStream out, String playerName, String bet) {
        out.println(playerName + "-" + bet);
    }

    private void printServerResponse(BufferedReader in) throws IOException {
        int value = 0;
        while((value = in.read()) != -1) {
            char c = (char) value;
            System.out.print(c);
        }
    }

    public void run() throws ServerException {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your name to play Rock Paper Scissors!\n> ");
            String playerName = input.readLine();
            System.out.print("Choose a number: (1)Rock (2)Scissors (3)Paper\n> ");
            String bet = input.readLine();

            Socket socket = openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(new DataOutputStream(socket.getOutputStream()));

            makeServerResquest(out, playerName, bet);
            printServerResponse(in);

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
    }
}