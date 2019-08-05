/*
 * Copyright (c) 2019. BEST S.A. and/or its affiliates. All rights reserved.
 */
package com.mzweigert.sentence_serializer;

import com.mzweigert.sentence_serializer.domain.Sentence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TestUtils {

	public static void delete(File f) throws IOException {
		if (f.isDirectory()) {
			for (File c : Objects.requireNonNull(f.listFiles()))
				delete(c);
		}
		if (!f.delete())
			throw new FileNotFoundException("Failed to delete file: " + f);
	}

	public static Collection<Sentence> createSentences() {
		List<Sentence> sentences = new LinkedList<>();
		sentences.add(createSentence("This", "is", "sample", "sentence", "1"));
		sentences.add(createSentence("This", "is", "sample", "sentence", "2"));
		sentences.add(createSentence("This", "is", "sample", "sentence", "3"));
		return sentences;
	}

	private static Sentence createSentence(String... words) {
		Sentence sentence = new Sentence();
		Arrays.stream(words).forEach(sentence::addWord);
		return sentence;
	}
}
