package com.mzweigert.sentence_serializer.domain;

import java.util.Collection;
import java.util.TreeSet;

public class Sentence {

    private Collection<String> words;

    public Sentence() {
        this.words = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
    }

    public boolean addWord(String word) {
        return words.add(word);
    }

    @Override
    public String toString() {
        return words.toString();
    }

    public Collection<String> getWords() {
        return words;
    }
}
