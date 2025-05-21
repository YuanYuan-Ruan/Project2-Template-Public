
public enum Rank {
    ACE(5, 11, 1),
    KING(3, 4, 13),
    QUEEN(2, 3, 12),
    JACK(1, 2, 11),
    TEN(4, 10, 10), NINE(0, 0, 9);

    private int rankCardValue = 1;
    private int scoreValue = 0;
    private int shortHandValue = 0;
    public static final int NINE_TRUMP = 10;

    Rank(int rankCardValue, int scoreValue, int shortHandValue) {
        this.rankCardValue = rankCardValue;
        this.scoreValue = scoreValue;
        this.shortHandValue = shortHandValue;
    }

    public int getRankCardValue() {
        return rankCardValue;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public int getShortHandValue() {
        return shortHandValue;
    }

    public String getCardLog() {
        return String.format("%d", shortHandValue);
    }
}