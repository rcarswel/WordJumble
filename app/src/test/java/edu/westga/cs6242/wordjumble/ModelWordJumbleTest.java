package edu.westga.cs6242.wordjumble;

import org.junit.Test;

import java.util.ArrayList;

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
 */
public class ModelWordJumbleTest {
    private static final int RANGE = 0;
    private WordJumble wordJumble = new WordJumble();

    /**
     * Test the creation of WordJumble,
     * confirms initial length of 5
     *
     * @throws Exception
     */
    @Test
    public void testWordJumbleCreation() throws Exception {
        assertEquals(5, wordJumble.getWordLength(), RANGE);
    }

    /**
     * Test the scrambling of a 5 letter word
     *
     * @throws Exception
     */
    @Test
    public void test5LetterWord() throws Exception {
        String word = wordJumble.scramble();
        assertEquals(5, word.length(), RANGE);
    }

    /**
     * Test if there is a correct answer
     *
     * @throws Exception
     */
    @Test
    public void testCorrect5LetterAnswer() throws Exception {
        wordJumble.scramble();
        ArrayList<String> wordsToTry = wordJumble.getWords();
        boolean result = false;
        for (String tries : wordsToTry) {
            if (wordJumble.compare(tries)) {
                result = true;
            }
        }
        assertTrue(result);
    }

    /**
     * WordLength should remain 5,
     * no 100 letter word
     *
     * @throws Exception
     */
    @Test
    public void testSetTo100Letters() throws Exception {
        wordJumble.setWordLength(100);
        assertEquals(5, wordJumble.getWordLength(), RANGE);
    }

    /**
     * WordLength should change to 6
     *
     * @throws Exception
     */
    @Test
    public void testSetTo6Letters() throws Exception {
        wordJumble.setWordLength(6);
        assertEquals(6, wordJumble.getWordLength(), RANGE);
    }

    /**
     * Test the scrambling of a 6 letter word
     *
     * @throws Exception
     */
    @Test
    public void test6LetterWord() throws Exception {
        wordJumble.setWordLength(6);
        String word = wordJumble.scramble();
        assertEquals(6, word.length(), RANGE);
    }

    /**
     * Test if there is a correct answer
     *
     * @throws Exception
     */
    @Test
    public void testCorrect6LetterAnswer() throws Exception {
        wordJumble.setWordLength(6);
        wordJumble.scramble();
        ArrayList<String> wordsToTry = wordJumble.getWords();
        boolean result = false;
        for (String tries : wordsToTry) {
            if (wordJumble.compare(tries)) {
                result = true;
            }
        }
        assertTrue(result);
    }
}