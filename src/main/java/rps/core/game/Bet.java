package rps;

public class Bet {

    private BetOption _clientBet;
    private BetOption _serverBet;
    private Player _player;

    Bet(BetOption clientBet, BetOption serverBet) {
        _player = new GuestPlayer("");
        _clientBet = clientBet;
        _serverBet = serverBet;
    }

    Bet(Player player, BetOption clientBet, BetOption serverBet) {
        _player = player;
        _clientBet = clientBet;
        _serverBet = serverBet;
    }

    public String winner() {
        if (_clientBet.equals(_serverBet)) {
            return "tie. We both chose " + _serverBet + ".";
        }
        for (BetOption betAdvantage : _clientBet.hasAnEdge()) {
            if (betAdvantage.equals(_serverBet)) {
                _player.addWin();
                return "Congratulations, you won! I chose " + _serverBet + ".";
            }
        }
        return "Sorry, you lost the bet. I won with " + _serverBet + ".";
    }
}