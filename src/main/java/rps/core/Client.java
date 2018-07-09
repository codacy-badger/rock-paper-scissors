package rps;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;

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
        while ((value = in.read()) != -1) {
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
        sb.append("\n");
        return sb.toString();
    }

    private String welcomePlayer(String[] args) throws IOException {
        InputStream inputStream = args.length > 0 ? new ByteArrayInputStream((args[0]+"\n").getBytes()) : System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
        System.out.println("Enter your name to play Rock Paper Scissors!");
        String playerName = input.readLine();
        return playerName;
    }

    private String makeBet(String[] args) throws IOException {
        InputStream inputStream = args.length > 1 ? new ByteArrayInputStream((args[1]+"\n").getBytes()) : System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
        String bet = input.readLine();
        return bet;
    }

    private int getNumberGames(String[] args) {
        try {
            return args.length > 2 ? Integer.parseInt(args[2]) : Utils.DEFAULT_NUM_GAMES;
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    public void start(String[] args) throws RpsException {
        try {
            int numGames = getNumberGames(args);
            String playerName = welcomePlayer(args);
            for (int i = 0; i < numGames; i++) {
                System.out.print(printMenu());
                String bet = makeBet(args);
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
        } catch (IOException e) {
            throw new RpsException(e.getMessage());
        }
    }
}