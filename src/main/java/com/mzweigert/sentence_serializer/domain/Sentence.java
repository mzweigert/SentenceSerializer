package com.mzweigert.sentence_serializer.domain;

import java.util.TreeSet;

public class Sentence {

    private TreeSet<String> words;

    public Sentence(TreeSet<String> words) {
        this.words = words;
    }

    public TreeSet<String> getWords() {
        return words;
    }
}
