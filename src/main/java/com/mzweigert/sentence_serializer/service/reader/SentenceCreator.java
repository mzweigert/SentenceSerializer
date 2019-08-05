package com.mzweigert.sentence_serializer.service.reader;

import com.mzweigert.sentence_serializer.domain.Sentence;

import java.util.LinkedList;
import java.util.List;

class SentenceCreator {

	private AbbreviationsRecognizer recognizer;
	private List<Sentence> sentences;
	private Sentence currentSentence;
	private WordCheckType lastWordCheckType;

	SentenceCreator() {
		this.recognizer = new AbbreviationsRecognizer();
		this.lastWordCheckType = WordCheckType.NOT_CHECK;
		this.sentences = new LinkedList<>();
		this.currentSentence = new Sentence();
	}

	void processNext(String line) {
		String[] words = line.split("[\\s*%&(),\":-]+");
		for (String word : words) {
			if (word.equals("")) {
				continue;
			}

			if (isEndOfSentenceAfterAbbreviation(word)) {
				initNewSentence();
			}

			checkWord(word);
		}
	}

	private boolean isEndOfSentenceAfterAbbreviation(String word) {
		return lastWordCheckType == WordCheckType.AFTER_TIME_ABBREVIATION && Character.isUpperCase(word.charAt(0));
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
			word = word.replaceAll("[.!?]", "");
			if (!word.equals("")) {
				currentSentence.addWord(word);
			}
			initNewSentence();
		}
	}

	private void initNewSentence() {
		sentences.add(currentSentence);
		currentSentence = new Sentence();
	}

	private boolean isNotEndingWord(String word) {
		char endChar = word.charAt(word.length() - 1);
		return endChar != '.' && endChar != '?' && endChar != '!';
	}

	public List<Sentence> getSentences() {
		return sentences;
	}
}