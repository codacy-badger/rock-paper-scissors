package rps;

public class Bet {

    private BetOption _clientBet;
    private BetOption _serverBet;

    Bet(BetOption clientBet, BetOption serverBet) {
        _clientBet = clientBet;
        _serverBet = serverBet;
    }

    public String winner() {
        boolean tie = _clientBet.equals(_serverBet);
        if (tie) {
            return "tie. We both chose " + _serverBet + ".";
        }
        for (BetOption betAdvantage : _clientBet.hasAnEdge()) {
            if (betAdvantage.equals(_serverBet)) {
                return "Congratulations, you won! I chose " + _serverBet + ".";
            }
        }
        return "Sorry, you lost the bet. I won with " + _serverBet + ".";
    }
}