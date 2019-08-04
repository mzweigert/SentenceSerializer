package com.mzweigert.sentence_serializer.domain;

import java.util.Collection;

public class Sentence {

    private Collection<String> words;

    public Sentence(Collection<String> words) {
        this.words = words;
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
