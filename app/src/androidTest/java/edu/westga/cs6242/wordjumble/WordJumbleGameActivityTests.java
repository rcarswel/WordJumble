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
    public void testInitialState() {
        WordJumbleGameActivity activity = getActivity();
        final EditText guess = (EditText) activity.findViewById(R.id.etGuess);
        final EditText size = (EditText) activity.findViewById(R.id.etSize);
        final TextView jumble = (TextView) activity.findViewById(R.id.tvJumbled);


        // tap hint button.
        this.buttonTapHint();
        assertEquals(0, size.getText().toString().length());
        assertEquals(0, jumble.getText().toString().length());

        // change button
        this.buttonTapChangeLetterCount();
        assertEquals(0, size.getText().toString().length());
        assertEquals(0, jumble.getText().toString().length());

        // submit button
        this.buttonTapSubmit();
        assertEquals(0, size.getText().toString().length());
        assertEquals(0, jumble.getText().toString().length());

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

    public void testSubmitIfWordJumbleEmpty() {
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
        this.buttonTapSubmit();
        TextView jWord =
                (TextView) activity.findViewById(R.id.tvJumbled);

        String jumbleText = jWord.getText().toString();
        assertEquals("", jumbleText);

    }
    public void testChangeButtonIf0Defaults5() {
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
        //assertEquals("5", letters);

    }
    public void testPlayButtonIfWordSizeIsEmptyDefaultTo5() {
        WordJumbleGameActivity activity = getActivity();
        final EditText size = (EditText) activity.findViewById(R.id.etSize);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                size.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("");

        this.buttonTapPlay();
        EditText sizeWord =
                (EditText) activity.findViewById(R.id.etSize);

        String letters = sizeWord.getText().toString();

        assertEquals("5", letters);

    }
    public void testSubmitButtonIfGuessIsEmpty() {
        WordJumbleGameActivity activity = getActivity();
        final EditText guess = (EditText) activity.findViewById(R.id.etGuess);
        final EditText size = (EditText) activity.findViewById(R.id.etSize);
        this.buttonTapPlay();

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

        this.buttonTapSubmit();
        EditText guessW =
                (EditText) activity.findViewById(R.id.etGuess);

        String guessWord = guessW.getText().toString();

        assertEquals("", guessWord);

    }
    //********************* Private Helpers ************************
    //buttons for testing
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
    private void buttonTapHint() {
        WordJumbleGameActivity activity = getActivity();

        Button hintButton =
                (Button) activity.findViewById(R.id.btnHint);
        TouchUtils.clickView(this, hintButton);


    }

}