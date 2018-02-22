package es.joanmiralles.kata;

class GameFrame {

    private int score = 0;
    private int remainingTries = 2;

    public int getScore() {
        return score;
    }

    public int getRemainingTries() {
        return remainingTries;
    }

    public void addScore(int addedScore) {
        this.score += addedScore;
        updateTries(addedScore);
    }

    private void updateTries(int addedScore) {
        if (addedScore == 10) {
            this.remainingTries = 0;
        } else {
            this.remainingTries--;
        }
    }
}
