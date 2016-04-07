package edu.westga.cs6242.wordjumble;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Miko on 3/31/2016.
 * Updated by Miko on 4/1/2016. Added testing.
 * Updated by Miko on 4/6/2016. Added testing.
 * Updated by Robert on 4/6/2016. Changed to match correcting in Model.
 */
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
        assertEquals("5", size.getText().toString());
        assertEquals(5, jumble.getText().toString().length());

        // change button
        this.buttonTapChangeLetterCount();
        assertEquals("5", size.getText().toString());
        assertEquals(5, jumble.getText().toString().length());

        // submit button
        this.buttonTapSubmit();
        assertEquals("5", size.getText().toString());
        assertEquals(5, jumble.getText().toString().length());

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

    public void testIfPlayButtonPopulates5LetterText() {
        WordJumbleGameActivity activity = getActivity();
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
        this.buttonTapPlay();
        int jumbled = jumble.length();
        assertEquals(5, jumbled);

    }

    public void testIfPlayButtonPopulates6LetterText() {
        WordJumbleGameActivity activity = getActivity();
        final EditText size = (EditText) activity.findViewById(R.id.etSize);
        final TextView jumble = (TextView) activity.findViewById(R.id.tvJumbled);
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                size.requestFocus();
                //To increase reliability due to ide delay
                size.setText("6");
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("6");

        this.buttonTapChangeLetterCount();
        this.buttonTapPlay();
        int jumbled = jumble.length();
        assertEquals(6, jumbled);

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
        assertEquals(5, jumbleText.length());

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