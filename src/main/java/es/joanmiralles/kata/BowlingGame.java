package es.joanmiralles.kata;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private int numberOfFrames;
    private List<GameFrame> frames;
    private String rolls;

    public BowlingGame(String rolls) {
        this.rolls = rolls;
        this.numberOfFrames = 10;
        this.frames = new ArrayList<>();
        calculateScore();
    }

    public int getNumberOfFrames() {
        return this.numberOfFrames;
    }

    public int getScore() {
        return frames.stream().mapToInt(GameFrame::getScore).sum();
    }

    public List<GameFrame> getFrames() {
        return frames;
    }

    private void calculateScore() {
        int rollIndex = 0;
        for (int i = 0; i < numberOfFrames; i++) {
            GameFrame gameFrame = new GameFrame();
            while (gameFrame.getRemainingTries() > 0) {
                gameFrame.addScore(rollScore(rollIndex));
                if (isSpare(rollSymbol(rollIndex))) {
                    gameFrame.addScore(nextRollScore(rollIndex));
                }
                if (isStrike(rollSymbol(rollIndex))) {
                    gameFrame.addScore(nextRollScore(rollIndex));
                    gameFrame.addScore(nextAfterNextRollScore(rollIndex));
                }
                rollIndex++;
            }
            frames.add(gameFrame);
        }
    }

    private Integer nextAfterNextRollScore(int currentRollIndex) {
        return rollScore(currentRollIndex + 2);
    }

    private Integer nextRollScore(int currentRollIndex) {
        return rollScore(currentRollIndex + 1);
    }

    private Integer rollScore(int currentRollIndex) {
        String rollSymbol = rollSymbol(currentRollIndex);
        if (isMissing(rollSymbol)) {
            return 0;
        } else if (isSpare(rollSymbol)) {
            return 10 - previousRollScore(currentRollIndex);
        } else if (isStrike(rollSymbol)) {
            return 10;
        }
        return Integer.valueOf(rollSymbol);
    }

    private Integer previousRollScore(int currentRollIndex) {
        return rollScore(currentRollIndex - 1);
    }

    private String rollSymbol(int currentRollIndex) {
        return String.valueOf(rolls.charAt(currentRollIndex));
    }

    private boolean isMissing(String rollSymbol) {
        return rollSymbol.equals("-");
    }

    private boolean isStrike(String rollSymbol) {
        return rollSymbol.equals("X");
    }

    private boolean isSpare(String rollSymbol) {
        return rollSymbol.equals("/");
    }
}

