package com.mzweigert.sentence_serializer.service.reader;

import com.mzweigert.sentence_serializer.domain.Sentence;

import java.io.*;
import java.util.*;

public class SentencesReader {

    private File file;
    private List<Sentence> sentences;
    private Sentence currentSentence;
    private WordCheckType lastWordCheckType;
    private AbbreviationsRecognizer recognizer;

    public SentencesReader(File file) {
        this.file = file;
        this.recognizer = new AbbreviationsRecognizer();
        this.sentences = new LinkedList<>();
        this.lastWordCheckType = WordCheckType.NOT_CHECK;
        this.currentSentence = new Sentence(new TreeSet<>(String.CASE_INSENSITIVE_ORDER));
    }

    public Collection<Sentence> read() throws LengthyInputException {
        String line;

        try(FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader)) {
            while ((line = br.readLine()) != null) {
                processLine(line);
            }
            return sentences;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            throw new LengthyInputException("Input is too lengthy.");
        }
        return Collections.emptyList();
    }

    private void processLine(String line) {
        String[] words = line.split("[\\s*%&(),\":-]+");
        for (String word : words) {
            if(word.equals("")) {
                continue;
            }

            if (isEndOfSentenceAfterAbbreviation(lastWordCheckType, word)) {
                initNewSentence();
            }

            checkWord(word);
        }
    }

    private boolean isEndOfSentenceAfterAbbreviation(WordCheckType wordCheckType, String word) {
        return wordCheckType == WordCheckType.AFTER_TIME_ABBREVIATION && Character.isUpperCase(word.charAt(0));
    }

    private void checkWord(String word) {
        if (isNotEndingWord(word) || recognizer.isPrefixTitleAbbreviation(word)) {
            lastWordCheckType = WordCheckType.NOT_CHECK;
            currentSentence.addWord(word);
        } else if (recognizer.isTimesAbbreviation(word)) {
            lastWordCheckType = WordCheckType.AFTER_TIME_ABBREVIATION;
            currentSentence.addWord(word);
        } else {
            lastWordCheckType = WordCheckType.END_OF_SENTENCE;
            word = word.substring(0, word.length() - 1);
            if(!word.equals("")) {
                currentSentence.addWord(word);
            }
            initNewSentence();
        }
    }

    private boolean isNotEndingWord(String word) {
        char endChar = word.charAt(word.length() - 1);
        return endChar != '.' && endChar != '?' && endChar != '!';
    }

    private void initNewSentence() {
        sentences.add(currentSentence);
        currentSentence = new Sentence(new TreeSet<>(String.CASE_INSENSITIVE_ORDER));
    }

}
