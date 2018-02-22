package es.joanmiralles.kata;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BowlingGameTest {
    private BowlingGame bowlingGame;

    @Test
    public void game_should_have_10_frames() {
        initializeBowlingGameWith("--------------------");
        assertThat(bowlingGame.getNumberOfFrames(), is(10));
    }

    @Test
    public void game_should_have_an_score() {
        initializeBowlingGameWith("11111111111111111111");
        assertTrue(bowlingGame.getScore() >= 0);
    }

    @Test
    public void given_initial_punctuation_should_first_frame_score_is_6() {
        initializeBowlingGameWith("51-1-1-1-1-1-1-1-1-1");
        GameFrame firstGameFrame = bowlingGame.getFrames().get(0);
        assertThat(firstGameFrame.getScore(), is(6));
    }

    private void initializeBowlingGameWith(String gameRolling) {
        bowlingGame = new BowlingGame(gameRolling);
    }

    @Test
    public void given_initial_punctuation_when_spare_should_first_frame_score_is_15() {
        initializeBowlingGameWith("5/X1-1-1-1-1-1-1-1-");
        GameFrame firstGameFrame = bowlingGame.getFrames().get(0);
        assertThat(firstGameFrame.getScore(), is(20));
    }

    @Test
    public void given_initial_game_when_strike_then_first_frame_score_should_be_20() {
        initializeBowlingGameWith("X5/11-1-1-1-1-1-1-1");
        GameFrame firstGameFrame = bowlingGame.getFrames().get(0);
        assertThat(firstGameFrame.getScore(), is(20));
    }

    @Test
    public void when_all_strikes_then_score_is_300() {
        initializeBowlingGameWith("XXXXXXXXXXXX");
        assertThat(bowlingGame.getScore(), is(300));
    }

    @Test
    public void when_all_pairs_of_5_and_spare_then_score_is_150() {
        initializeBowlingGameWith("5/5/5/5/5/5/5/5/5/5/5");
        assertThat(bowlingGame.getScore(), is(150));
    }

    @Test
    public void when_all_pairs_of_9_and_missing_then_score_is_90() {
        initializeBowlingGameWith("9-9-9-9-9-9-9-9-9-9-");
        assertThat(bowlingGame.getScore(), is(90));
    }
}