package edu.westga.cs6242.wordjumble;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class WordJumbleGameActivityTests extends ActivityInstrumentationTestCase2<WordJumbleGameActivity> {
    public WordJumbleGameActivityTests() {
        super(WordJumbleGameActivity.class);
    }

    public void testActivityExists() {
        WordJumbleGameActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testUserInputMissing() {
        WordJumbleGameActivity activity = getActivity();
        final EditText guess = (EditText) activity.findViewById(R.id.etGuess);
        final EditText size = (EditText) activity.findViewById(R.id.etSize);
        final TextView jumble = (TextView) activity.findViewById(R.id.tvJumbled);

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
        getInstrumentation().sendStringSync("lmone");

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                jumble.requestFocus();
            }
        });
        this.buttonTapPlay();
        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("");

        TextView guessWord =
                (TextView) activity.findViewById(R.id.etGuess);

        String guessText = guessWord.getText().toString();
        assertEquals("", guessText);
        this.buttonTapSubmit();

    }
    /*
    public void testIfWordJumbleEmpty() {
        WordJumbleGameActivity activity = getActivity();
        final EditText guess = (EditText) activity.findViewById(R.id.etGuess);
        final EditText size = (EditText) activity.findViewById(R.id.etSize);
        final TextView jumble = (TextView) activity.findViewById(R.id.tvJumbled);

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

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                jumble.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("");

        TextView jWord =
                (TextView) activity.findViewById(R.id.tvJumbled);

        String jumbleText = jWord.getText().toString();
        assertEquals("", jumbleText);
        this.buttonTapSubmit();
    }*/
    public void testChangeButtonIfEmptyDefaultsTo5() {
        WordJumbleGameActivity activity = getActivity();
        final EditText size = (EditText) activity.findViewById(R.id.etSize);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                size.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("0");

        this.buttonTapChangeLetterCount();
        EditText sizeWord =
                (EditText) activity.findViewById(R.id.etSize);

        String letters = sizeWord.getText().toString();
        assertEquals("5", letters);


    }
    public void testChangeButtonInvalidNumberDefaultsTo5() {
        WordJumbleGameActivity activity = getActivity();
        final EditText size = (EditText) activity.findViewById(R.id.etSize);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                size.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("20");

        this.buttonTapChangeLetterCount();
        EditText sizeWord =
                (EditText) activity.findViewById(R.id.etSize);

        String letters = sizeWord.getText().toString();
        assertEquals("5", letters);

    }
    //********************* Private Helpers ************************

    private void buttonTapSubmit() {
        WordJumbleGameActivity activity = getActivity();

        Button submitButton =
                (Button) activity.findViewById(R.id.btnGuess);
        TouchUtils.clickView(this, submitButton);
    }
    private void buttonTapChangeLetterCount() {
        WordJumbleGameActivity activity = getActivity();

        Button changeButton =
                (Button) activity.findViewById(R.id.btnChange);
        TouchUtils.clickView(this, changeButton);
    }
    private void buttonTapPlay() {
        WordJumbleGameActivity activity = getActivity();

        Button playButton =
                (Button) activity.findViewById(R.id.btnNewWord);
        TouchUtils.clickView(this, playButton);
    }

}