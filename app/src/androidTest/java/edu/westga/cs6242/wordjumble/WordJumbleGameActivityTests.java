package edu.westga.cs6242.wordjumble;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Miko on 3/30/2016.
 */
public class WordJumbleGameActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {
    public WordJumbleGameActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }
    public void testUserInputMissing() {
        this.buttonTapStartGame();
        MainActivity activity = getActivity();
        final EditText guess = (EditText) activity.findViewById(R.id.etGuess);
        final EditText size = (EditText) activity.findViewById(R.id.etSize);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                size.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("5");

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                guess.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("");

        TextView scrambledWord =
                (TextView) activity.findViewById(R.id.tvJumbled);

        String scrambledText = scrambledWord.getText().toString();
        assertEquals("", scrambledText);
        this.buttonTapSubmit();
        //how do I test the jumbled word textView is

    }
    //********************* Private Helpers ************************

    private void buttonTapStartGame() {
        MainActivity activity = getActivity();

        Button startGameButton =
                (Button) activity.findViewById(R.id.btnStart);
        TouchUtils.clickView(this, startGameButton);
    }
    private void buttonTapSubmit() {
        MainActivity activity = getActivity();

        Button submitButton =
                (Button) activity.findViewById(R.id.btnGuess);
        TouchUtils.clickView(this, submitButton);
    }
    private void buttonTapChangeLetterCount() {
        MainActivity activity = getActivity();

        Button changeButton =
                (Button) activity.findViewById(R.id.btnChange);
        TouchUtils.clickView(this, changeButton);
    }
    private void buttonTapPlayAgain() {
        MainActivity activity = getActivity();

        Button playButton =
                (Button) activity.findViewById(R.id.btnNewWord);
        TouchUtils.clickView(this, playButton);
    }
    private void buttonTapQuitGame() {
        MainActivity activity = getActivity();

        Button quitButton =
                (Button) activity.findViewById(R.id.btnQuit);
        TouchUtils.clickView(this, quitButton);
    }
}
