package edu.westga.cs6242.wordjumble.model;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Miko on 3/23/2016.
 * Updated by Robert on 3/25/2016.
 */
public class WordJumble {
    private static final int DEFAULTED = 5; //Default Length
    private ArrayList<String> words;
    private int length;
    private Random random;
    private String original;
    private Context context;
    private String wordFile;

    /**
     * Populate ArrayList of words at construction
     * Sets default size to 5
     */
    public WordJumble(Context context) {
        this.context = context;
        this.wordFile = "words.txt";
        setWordLength(DEFAULTED);
        this.words = getWords();
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
            }
        }
        this.length = check;
    }

    /**
     * Gets words from file and stores as ArrayList,
     * if file is blank, uses backup (internal hard coding).
     */
    private ArrayList<String> getWords() {
        AssetManager assetManager = this.context.getAssets();
        InputStream input;
        ArrayList<String> wordList = new ArrayList<>();

        //Checks for file,
        // if no file (or blank), use backup
        try {
            //Looks up file
            input = assetManager.open(this.wordFile);
            int count = input.available();
            byte[] buffer = new byte[count];
            input.read(buffer);
            input.close();

            //Converts Bytes to String
            String fileText = new String(buffer);
            String[] fileWords = fileText.split(",");

            //Creates a ArrayList from file
            for (String word : fileWords) {
                wordList.add(word);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        //Checks size, if 0 uses backup
        if (wordList.isEmpty()) {
            wordList = this.getWordsBackUp();
        }

        return wordList;
    }

    /**
     * @return All words Array list
     */
    private ArrayList<String> getWordsBackUp() {
        //Create ArrayList to load
        ArrayList<String> wordList = new ArrayList<>();

        //Load ArrayList with words [5 each of 5 or 6 letters)
        wordList.add("apple");
        wordList.add("banana");
        wordList.add("carrot");
        wordList.add("grape");
        wordList.add("lemon");
        wordList.add("mango");
        wordList.add("orange");
        wordList.add("peach");
        wordList.add("raisin");
        wordList.add("tomato");

        //Return ArrayList
        return wordList;
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

        //Check for length
        while (original.length() != this.getWordLength()) {
            index = random.nextInt(size);
            original = words.get(index);
        }

        //Return word of correct length
        return original;
    }
}