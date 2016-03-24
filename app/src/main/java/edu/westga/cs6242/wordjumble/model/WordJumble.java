package edu.westga.cs6242.wordjumble.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Miko on 3/23/2016.
 */
public class WordJumble {
    /**
     * note to myself
     * TODO - check for errors as well and allow for capitals
     */
    private ArrayList<String> words;
    private String original;

    //list of words to use as hard coded
    public WordJumble() {
        words = new ArrayList<>();
        words.add("apple");
        words.add("mango");
        words.add("peach");
    }
    //get a word from the array randomly and set as the original word to scramble
    public String getRandomWord() {

        if(words.size() == 0) return null;

        int index = (int)(Math.random() * (words.size()));

        original = words.get(index);
        words.remove(index);

        return original;
    }
    //scrambles the original word one letter at a time then returns the scrambled word
    public String scramble() {
        ArrayList<Character> letters = new ArrayList<>();
        String scrambled = "";
        int i;
        for(i=0; i< original.length(); ++i) {
            letters.add(original.charAt(i));
        }

        for(i=0; i < original.length(); ++i) {
            int index = (int)(Math.random() * (letters.size()));
            scrambled += letters.get(index);
            letters.remove(index);
        }
        return scrambled;
    }
    //does a comparison of the user's entered word and the original word
    public boolean compare(String input) {
        return original.equals(input);
    }

}

