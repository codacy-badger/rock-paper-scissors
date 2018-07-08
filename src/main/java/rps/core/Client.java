package rps;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.PrintStream;

class Client {
    private Socket openConnection() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(Utils.HOSTNAME, Utils.PORT), Utils.TIMEOUT);
        return socket;
    }

    private void makeServerRequest(PrintStream out, String playerName, String bet) {
        out.println(playerName + "-" + bet);
    }

    private void printServerResponse(BufferedReader in) throws IOException {
        int value = 0;
        while((value = in.read()) != -1) {
            char c = (char) value;
            System.out.print(c);
        }
    }

    private String printMenu() {
        BetOption[] bets = new AvailableBetOption().getAll();
        StringBuffer sb = new StringBuffer();
        sb.append("Choose a number:");
        for (int i = 0; i < bets.length; i++) {
            sb.append(" (" + i + ")" + bets[i]);
        }
        sb.append("\n> ");
        return sb.toString();
    }

    public void start() throws ServerException {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your name to play Rock Paper Scissors!\n> ");
            String playerName = input.readLine();

            while (true) {
                System.out.print(printMenu());
                String bet = input.readLine();
                if (bet.equals("q")) {
                    break;
                }

                Socket socket = openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream out = new PrintStream(new DataOutputStream(socket.getOutputStream()));

                makeServerRequest(out, playerName, bet);
                printServerResponse(in);

                in.close();
                out.close();
                socket.close();
            }
            input.close();
        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
    }
}