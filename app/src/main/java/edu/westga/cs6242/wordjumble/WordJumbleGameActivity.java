package edu.westga.cs6242.wordjumble;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.westga.cs6242.wordjumble.model.WordJumble;

public class WordJumbleGameActivity extends AppCompatActivity {
    private WordJumble wordJumble;
    private EditText etSize;
    private TextView tvJumbled;
    private EditText etGuess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_jumble_game);

        //Activates Display
        wordJumble = new WordJumble();
        etSize = (EditText) findViewById(R.id.etSize);
        tvJumbled = (TextView) findViewById(R.id.tvJumbled);
        etGuess = (EditText) findViewById(R.id.etGuess);

        //Load initial content
        this.newContent();
    }

    /**
     * User's guess
     *
     * @param view current view
     */
    public void click_Guess(View view) {
        String guess = "";

        //Checks that guess is not blank, then get user input
        if (tvJumbled.getText().length() == 0) {
            makeToast("Your guess is blank!");
        } else {
            guess = etGuess.getText().toString().toLowerCase();
        }

        if (wordJumble.compare(guess)) {
            makeToast("Correct!");
        } else {
            makeToast("Wrong!");
        }
    }

    /**
     * Changes the size, if valid
     *
     * @param view current view
     */
    public void click_Change(View view) {
        int oldSize = wordJumble.getWordLength();
        int newSize = oldSize;

        //Checks that size is not blank, then get user input

        if (etSize.getText().length() == 0) {
            makeToast("Size is blank!");
        } else {
            newSize = Integer.parseInt(etSize.getText().toString());
        }
        //Check if there is a real change, then sets the new length
        if (newSize == oldSize) {
            makeToast("Size is already " + oldSize + "!");
        } else {
            wordJumble.setWordLength(newSize);
            newSize = wordJumble.getWordLength();
        }

        //Checks if any words match the new length, then gets new content
        if (newSize == oldSize) {
            int size = Integer.parseInt(etSize.getText().toString());
            makeToast("There are no " + size + " letter words!");
        } else {
            makeToast("Size changed!");
            this.newContent();
        }

        //Restore size to valid data, no change if already valid
        etSize.setText(String.valueOf(wordJumble.getWordLength()));
    }

    /**
     * Clear old word and Resets with new word.
     *
     * @param view current view
     */
    public void click_NewWord(View view) {
        tvJumbled.clearFocus();
        this.newContent();
    }

    /**
     * Closes the application and returns to the start game page
     *
     * @param view current view
     */
    public void click_Quit(View view) {
        this.onBackPressed();
    }
    /*
     * method to alert user of game termination and ask if desired
     * if yes game finishes and returns to splash page
     * if no request is cancelled and game continues
     */
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WordJumbleGameActivity.this);
        builder.setMessage("Do you want to quit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    //********************Private Helper Methods*************************************

    private void newContent() {
        etSize.setText(String.valueOf(wordJumble.getWordLength()));
        tvJumbled.setText(wordJumble.scramble());
        etGuess.setText("");
    }
    /**
     * Making Toast
     */
    private void makeToast(String message) {
        //get custom_toast.xml layout
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_layout_id));

        //set message
        TextView text = (TextView) layout.findViewById(R.id.tvToast);
        text.setText(message);

        //create toast
        Toast toast = new Toast(getApplicationContext()); //Creates toast for this activity
        toast.setDuration(Toast.LENGTH_LONG); //Sets the length of time to display
        toast.setGravity(Gravity.CENTER, 0, 0); //Sets display location
        toast.setView(layout);
        toast.show();
        //Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }

}
