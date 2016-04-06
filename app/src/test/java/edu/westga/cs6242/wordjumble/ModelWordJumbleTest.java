package edu.westga.cs6242.wordjumble;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import edu.westga.cs6242.wordjumble.model.WordJumble;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 * This test the model.WordJumble class.
 * - Holds Lists of length 5 and 6 words.
 * - Randomly selects word.
 * - Jumble random word.
 * - Verifies that the user has correctly un-jumbled.
 * Created by Robert on 3/25/2016.
 * Updated by Robert on 4/6/2016. Added answer test.
 */
public class ModelWordJumbleTest {
    private static final int RANGE = 0;
    private WordJumble wordJumble;
    private Context context = null;

    @Before
    public void setUp() throws Exception {
        this.wordJumble = new WordJumble(this.context);
    }

    /**
     * Test the creation of WordJumble,
     * confirms initial length of 5
     * @throws Exception
     */
    @Test
    public void testWordJumbleCreation() throws Exception {
        assertEquals(5, this.wordJumble.getWordLength(), RANGE);
        assertEquals(5, this.wordJumble.scramble().length(), RANGE);
    }

    /**
     * Test the scrambling of a 5 letter word
     * @throws Exception
     */
    @Test
    public void test5LetterWord() throws Exception {
        assertEquals(5, this.wordJumble.scramble().length(), RANGE);
    }

    /**
     * WordLength should be max,
     * no 100 letter word
     * @throws Exception
     */
    @Test
    public void testSetTo100Letters() throws Exception {
        this.wordJumble.setWordLength(100);
        assertEquals(5, this.wordJumble.getWordLength(), RANGE);
        assertEquals(5, this.wordJumble.scramble().length(), RANGE);
    }

    /**
     * WordLength should change to 6
     * @throws Exception
     */
    @Test
    public void testSetTo6Letters() throws Exception {
        this.wordJumble.setWordLength(6);
        assertEquals(6, this.wordJumble.getWordLength(), RANGE);
        assertEquals(6, this.wordJumble.scramble().length(), RANGE);
    }

    /**
     * Test the scrambling of a 6 letter word
     * @throws Exception
     */
    @Test
    public void test6LetterWord() throws Exception {
        this.wordJumble.setWordLength(6);
        assertEquals(6, this.wordJumble.scramble().length(), RANGE);
    }

    /**
     * Test if there is a correct answer
     * @throws Exception
     */
    @Test
    public void testCorrectAnswer() throws Exception {
        this.wordJumble.scramble();
        assertTrue(this.wordJumble.compare(this.wordJumble.getAnswer()));
    }
}