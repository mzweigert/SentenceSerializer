package com.mzweigert.sentence_serializer.service.reader;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class AbbreviationsRecognizer {

	private Set<String> prefixTitles, times;

	public AbbreviationsRecognizer() {
		this.prefixTitles = initPrefixTitles();
		this.times = initTimes();
	}

	private Set<String> initPrefixTitles() {
		return createSet(
				"mr.", "ms.", "mrs.", "dr.", "prof.", "eng.", "st."
		);
	}

	private Set<String> initTimes() {
		return createSet(
				"sec.", "min.", "hr.", "h.", "wk.", "mo.", "yr.", "cent.", "a.m.", "p.m.", "b.c.",
				"tu.", "tue.", "tues.", "th.", "thu.", "thur.", "thurs.", "sat.", "mon.", "wed.", "fri.",
				"jan.", "feb.", "mar.", "apr.", "may.", "jun.", "jul.", "aug."
		);
	}

	public boolean isPrefixTitleAbbreviation(String word) {
		return isSetContainsWord(word, prefixTitles);
	}

	public boolean isTimesAbbreviation(String word) {
		return isSetContainsWord(word, times);
	}

	private boolean isSetContainsWord(String word, Set<String> set) {
		if (word == null) {
			return false;
		}
		return set.contains(word.toLowerCase());
	}

	private Set<String> createSet(String... abbreviations) {
		return Arrays.stream(abbreviations)
				.collect(Collectors.toSet());
	}
}
