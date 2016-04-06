package edu.westga.cs6242.wordjumble.model;

import android.content.Context;
import android.content.res.AssetManager;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Miko on 3/23/2016.
 * Updated by Robert on 3/25/2016. Testing and updated code where needed.
 * Updated by Robert on 4/4/2016. Added read file (wasn't working).
 * Updated by Robert on 4/5/2016. Corrected code for read file.
 * Updated by Miko on 4/6/16. Added hints and testing.
 */
public class WordJumble {
    private static final int DEFAULTED = 5; //Default Length
    private ArrayList<String> words;
    private ArrayList<String> hints;
    private int length;
    private Random random;
    private String original, originalHint;
    private Context context;
    private String wordFile;

    /**
     * Populate ArrayList of words at construction
     * Sets default size to 5
     */
    public WordJumble(Context context) {
        this.context = context;
        this.wordFile = "words.txt";
        getWords();
        setWordLength(DEFAULTED);
        this.random = new Random();
    }

    /********** Setters and Getters **********/

    /**
     * @return Length of Words
     */
    public int getWordLength() {
        return this.length;
    }

    /**
     * Sets the word length (initial is 5)
     */
    public void setWordLength(int length) {
        //Set base length to first in ArrayList,
        // if no words with length, base length remains.
        int check = this.words.get(0).length();

        //Checks Lengths
        for (String word : this.words) {
            if (word.length() == length) {
                check = length;
                break;

            } else {
                check = DEFAULTED;
            }
        }
        this.length = check;
    }

    /**
     * Gets a user hint
     * @return hint for user
     */
    public String getHint() {
        return originalHint;
    }

    /**
     * Gets the correct answer
     * Only used for testing
     *
     * @return correct answer
     */
    public String getAnswer() {
        return original;
    }

    /*********** Methods **********/
    /**
     * scrambles the original word one letter at a time then returns the scrambled word
     *
     * @return scrambled word
     */
    public String scramble() {
        //Sets word
        this.getRandomWord();

        //Scramble word
        ArrayList<Character> letters = new ArrayList<>();
        String scrambled = "";
        for (int count = 0; count < original.length(); count++) {
            letters.add(original.charAt(count));
        }

        for (int count = 0; count < original.length(); count++) {
            int index = random.nextInt(letters.size());
            scrambled += letters.get(index);
            letters.remove(index);
        }

        //Returns Scrambled
        return scrambled;
    }

    /**
     * Checks if the user entered the correct word
     *
     * @param word user's input
     * @return true if match
     */
    public boolean compare(String word) {
        //Converts word to all lowercase
        String lcWord = word.toLowerCase();

        //Checks if equal
        return original.equals(lcWord);
    }

    /*********** Private Methods **********/

    /**
     * Gets words from file and stores as ArrayList,
     * if file is blank, uses backup (internal hard coding).
     */
    private void getWords() {
        words = new ArrayList<>();
        hints = new ArrayList<>();

        //Checks for null testing context
        if (this.context == null) {
            this.getWordsBackUp();
            return;
        }

        //Checks for file,
        // if no file (or blank), use backup
        try {
            AssetManager assetManager = this.context.getAssets();

            //Looks up file
            Scanner input = new Scanner(assetManager.open(this.wordFile));
            while(input.hasNext()) {
                String line = input.nextLine();
                // Split the string at comma. So, first is word and second is hint
                String[] data = line.split(",");
                words.add(data[0]);
                hints.add(data[1]);
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

        //Checks size, if 0 uses backup
        if (words.size() == 0) {
            this.getWordsBackUp();
        }
    }

    /**
     * Creates a backup word, hint list if the file doesn't load
     */
    private void getWordsBackUp() {
        //Create ArrayList to load

        //Load ArrayList with words [5 each of 5 or 6 letters)
        words.add("apple");
        hints.add("fruit");
        words.add("banana");
        hints.add("fruit");
        words.add("carrot");
        hints.add("fruit");
        words.add("grape");
        hints.add("fruit");
        words.add("lemon");
        hints.add("fruit");
        words.add("mango");
        hints.add("fruit");
        words.add("orange");
        hints.add("fruit");
        words.add("peach");
        hints.add("fruit");
        words.add("raisin");
        hints.add("fruit");
        words.add("tomato");
        hints.add("fruit");
    }
    /**
     * Gets a random word from the array list that is the correct length,
     * sets as the original word to scramble
     *
     * @return Word of correct length
     */
    private String getRandomWord() {
        //Checks that the ArrayList is populated
        if (words.size() == 0) return null;

        //Set initial word
        int size = this.words.size();
        int index = random.nextInt(size);
        original = words.get(index);
        originalHint = hints.get(index);

        //Check for length
        while (original.length() != this.getWordLength()) {
            index = random.nextInt(size);
            original = words.get(index);
            originalHint = hints.get(index);
        }

        //Return word of correct length
        return original;
    }
}