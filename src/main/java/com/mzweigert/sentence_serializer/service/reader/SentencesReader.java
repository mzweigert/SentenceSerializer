package com.mzweigert.sentence_serializer.service.reader;

import com.mzweigert.sentence_serializer.domain.Sentence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

public class SentencesReader {

	private File file;

	public SentencesReader(File file) {
		this.file = file;
	}

	public Collection<Sentence> read() throws LengthyInputException {
		String line;
		SentenceCreator sentenceCreator = new SentenceCreator();

		try (FileReader fileReader = new FileReader(file);
			 BufferedReader br = new BufferedReader(fileReader)) {
			while ((line = br.readLine()) != null) {
				sentenceCreator.processNext(line);
			}
			return sentenceCreator.getSentences();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			throw new LengthyInputException("Input is too lengthy.");
		}
		return Collections.emptyList();
	}

}
