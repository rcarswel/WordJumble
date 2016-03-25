package edu.westga.cs6242.wordjumble.model;

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

    /**
     * Populate ArrayList of words at construction
     * Sets default size to 5
     */
    public WordJumble() {
        this.words = getWords();
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
            }
        }
        this.length = check;
    }

    /**
     * @return All words Array list
     */
    public ArrayList<String> getWords() {
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