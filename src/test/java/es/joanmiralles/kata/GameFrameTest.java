package es.joanmiralles.kata;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GameFrameTest {

    private GameFrame frame;

    @Before
    public void setUp() throws Exception {
        frame = new GameFrame();
    }

    @Test
    public void should_have_an_score() {
        assertTrue(frame.getScore() >= 0);
    }

    @Test
    public void should_have_two_tries() {
        assertThat(frame.getRemainingTries(), is(2));
    }

    @Test
    public void should_add_score() {
        frame.addScore(6);
        frame.addScore(2);
        assertThat(frame.getScore(), is(8));
    }

    @Test
    public void given_added_score_less_than_10_should_remove_one_try() {
        frame.addScore(7);
        assertThat(frame.getRemainingTries(), is(1));
    }

    @Test
    public void given_added_score_eq_10_should_remove_all_tries() {
        frame.addScore(10);
        assertThat(frame.getRemainingTries(), is(0));
    }
}
