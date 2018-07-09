package rps;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;

class Server {
    private boolean _running = true;
    private ServerSocket _server;
    private PlayerStorage _players = new PlayerStorage();
    private BetOption[] _availableBets = new AvailableBetOption().getAll();

    private class ClientRequest {
        private Player registerNewPlayer(String playerName) {
            _players.addPlayer(new RegisteredPlayer(playerName));
            return new GuestPlayer(playerName);
        }

        private Player setupPlayer(PrintStream out, String playerName) {
            try {
                RegisteredPlayer player = _players.getPlayerByName(playerName);
                String greetings = player.greets(playerName);
                out.println(greetings);
                return player;
            } catch (PlayerNotFoundException e) {
                return registerNewPlayer(playerName);
            }
        }

        private BetOption parsePlayerBet(String bet) throws InvalidBetException {
            for (int i = 0; i < _availableBets.length; i++) {
                if (bet.equals(Integer.toString(i))) {
                    return _availableBets[i];
                }
            }
            throw new InvalidBetException(bet);
        }

        private BetOption serverBet() {
            int index = ThreadLocalRandom.current().nextInt(0, _availableBets.length);
            return _availableBets[index];
        }

        private void makeBet(PrintStream out, Player player, String playerBet) {
            try {
                BetOption clientBet = parsePlayerBet(playerBet);
                BetOption serverBet = serverBet();
                String winnerText = new Bet(player, clientBet, serverBet).winner();
                player.addRound();
                out.print(winnerText + "\n" + player + "\n");
            } catch (InvalidBetException e) {
                out.println("Invalid bet: " + e.getMessage());
            }
        }

        private void process(Socket socket) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintStream out = new PrintStream(socket.getOutputStream())) {

                String playerNameAndBet = in.readLine();
                String[] split = playerNameAndBet.split("-");
                String playerName = split[0];
                String playerBet = split[1];
                System.out.println("Player: " + playerName + ", Bet: " + playerBet);

                Player player = setupPlayer(out, playerName);
                makeBet(out, player, playerBet);

                socket.close();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public void stop() {
        _running = false;
    }

    public void start() throws RpsException {
        ExecutorService executor = Executors.newFixedThreadPool(Utils.NUM_THREADS);
        try {
            _server = new ServerSocket(Utils.PORT);
            System.out.println("Waiting for connections...");
            while (_running) {
                Socket socket = _server.accept();
                executor.submit(() -> {
                    new ClientRequest().process(socket);
                });
            }
        } catch (IOException e) {
            throw new RpsException(e.getMessage());
        } finally {
            try {
                _server.close();
                executor.shutdownNow();
            } catch (IOException ee) {
                throw new RpsException(ee.getMessage());
            } catch (NullPointerException e) {
                throw new RpsException("Port " + Utils.PORT + " is already being used.");
            }
        }
    }
}