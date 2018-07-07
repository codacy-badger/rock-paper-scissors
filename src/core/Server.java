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
    private final int PORT = 58038;

    private Socket _socket;
    private ServerSocket _server;
    private PlayerStorage _players = new PlayerStorage();
    private BetOption[] _serverBetOptions = new BetOption[] {new Rock(), new Scissors(), new Paper()};

    private class ClientRequest {
        private void registerNewPlayer(String playerName) {
            try {
                Player player = new RegisteredPlayer(playerName);
                _players.addPlayer(player);
            } catch (PlayerException e) {
                System.err.println("Cannot register player: " + e.getMessage());
            }
        }

        private void validatePlayer(PrintStream out, String playerName) {
            try {
                Player player = _players.getPlayerByName(playerName);
                RegisteredPlayer registeredPlayer = (RegisteredPlayer) player;
                player.addRound();
                String greetings = registeredPlayer.greets(playerName);
                out.println(greetings + registeredPlayer);
            } catch (PlayerNotFoundException e) {
                GuestPlayer guest = new GuestPlayer(playerName);
                out.println(guest);
                registerNewPlayer(playerName);
            }
        }

        private BetOption parsePlayerBet(String bet) throws InvalidBetException {
            if (bet.equals("1")) {
                return new Rock();
            } else if (bet.equals("2")) {
                return new Scissors();
            } else if (bet.equals("3")) {
                return new Paper();
            }
            throw new InvalidBetException(bet);
        }

        private BetOption serverBet() {
            int index = ThreadLocalRandom.current().nextInt(0, _serverBetOptions.length);
            return _serverBetOptions[index];
        }

        private void makeBet(PrintStream out, String playerBet) {
            try {
                BetOption clientBet = parsePlayerBet(playerBet);
                BetOption serverBet = serverBet();
                String winnerText = new Bet(clientBet, serverBet).winner();
                out.println(winnerText);
            } catch (InvalidBetException e) {
                out.println("Invalid bet: " + e.getMessage());
            }
        }

        private void process() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
                PrintStream out = new PrintStream(_socket.getOutputStream());

                String playerNameAndBet = in.readLine();
                String[] split = playerNameAndBet.split("-");
                String playerName = split[0];
                String playerBet = split[1];
                System.out.println("Player: " + playerName + ", Bet: " + playerBet);

                validatePlayer(out, playerName);
                makeBet(out, playerBet);

                in.close();
                out.close();
                _socket.close();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public void run() throws ServerException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            _server = new ServerSocket(PORT);
            System.out.println("Waiting for connections...");
            while (true) {
                _socket = _server.accept();
                executor.submit(() -> {
                    new ClientRequest().process();
                });
            }
        } catch (IOException e) {
            try {
                _socket.close();
                _server.close();
                executor.shutdownNow();
            } catch (IOException ee) {
                throw new ServerException(ee.getMessage());
            } catch (NullPointerException ee) {
                throw new ServerException("Port is already being used.");
            }
        }
    }
}