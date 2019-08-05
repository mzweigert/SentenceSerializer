package com.mzweigert.sentence_serializer.service.reader;

import com.mzweigert.sentence_serializer.domain.Sentence;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SentenceCreatorTest {

	@Test
	public void givenSentenceWithoutEndChar_whenProcessNext_thenReadNoSentence() {
		//GIVEN
		String sentence = "This is sample sentence without end char";
		SentenceCreator sentenceCreator = new SentenceCreator();

		//WHEN
		sentenceCreator.processNext(sentence);

		//THEN
		assertThat(sentenceCreator.getSentences()).isEmpty();
	}

	@Test
	public void givenSentencesWithVariousEndChar_whenProcessNext_thenReadThreeSentences() {
		//GIVEN
		String sentence = "Sentence one. This is sentence two? No, it is sentence three!";
		SentenceCreator sentenceCreator = new SentenceCreator();

		//WHEN
		sentenceCreator.processNext(sentence);

		//THEN
		checkCreatedSentences(sentenceCreator, 3);
	}

	@Test
	public void givenSentencesWithManyEndChar_whenProcessNext_thenReadThreeSentences() {
		//GIVEN
		String sentence = "Sentence one.. This is sentence two?? No, it is sentence three!!!";
		SentenceCreator sentenceCreator = new SentenceCreator();

		//WHEN
		sentenceCreator.processNext(sentence);

		//THEN
		checkCreatedSentences(sentenceCreator, 3);
	}


	@Test
	public void givenSentenceWithTitlesAbbreviation_whenProcessNext_thenReadOnlyOneSentence() {
		//GIVEN
		String sentence = "This is sample sentence with Mr. abbreviation.";
		SentenceCreator sentenceCreator = new SentenceCreator();

		//WHEN
		sentenceCreator.processNext(sentence);

		//THEN
		checkCreatedSentences(sentenceCreator, 1);
	}

	@Test
	public void givenSentenceWithTimeAbbreviation_whenProcessNext_thenReadOnlyOneSentence() {
		//GIVEN
		String sentence = "I was born at 12 Jun. in 1999.";
		SentenceCreator sentenceCreator = new SentenceCreator();

		//WHEN
		sentenceCreator.processNext(sentence);

		//THEN
		checkCreatedSentences(sentenceCreator, 1);
	}

	@Test
	public void givenTwoSentenceWithTimeAbbreviation_whenProcessNext_thenReadTwoSentences() {
		//GIVEN
		String sentence = "We can meet at mon. Don't forget.";
		SentenceCreator sentenceCreator = new SentenceCreator();

		//WHEN
		sentenceCreator.processNext(sentence);

		//THEN
		checkCreatedSentences(sentenceCreator, 2);
	}

	@Test
	public void givenSeparatedSentenceInArray_whenProcessNext_thenReadTwoSentences() {
		//GIVEN
		String[] sentence = new String[]{
				"We can", "meet at mon.", "Don't", "forget."
		};
		SentenceCreator sentenceCreator = new SentenceCreator();

		//WHEN
		Arrays.stream(sentence).forEach(sentenceCreator::processNext);

		//THEN
		checkCreatedSentences(sentenceCreator, 2);
	}

	@Test
	public void givenSeparatedSentenceInArrayWithManyAbbreviations_whenProcessNext_thenReadTwoSentences() {
		//GIVEN
		String[] sentence = new String[]{
				"Hi", "Mr. Smith.", "We can", "meet at mon.", "I'll be", "there at 5", "p.m.",
				"I'll ", "be dressed ", "in whit jacket."
		};
		SentenceCreator sentenceCreator = new SentenceCreator();

		//WHEN
		Arrays.stream(sentence).forEach(sentenceCreator::processNext);

		//THEN
		checkCreatedSentences(sentenceCreator, 4);
	}

	private void checkCreatedSentences(SentenceCreator sentenceCreator, int i) {
		List<Sentence> sentences = sentenceCreator.getSentences();
		assertThat(sentences).isNotEmpty();
		assertThat(sentences).hasSize(i);
		sentences.forEach(savedSentence -> {
			List<String> strings = new ArrayList<>(savedSentence.getWords());
			assertThat(strings).isSortedAccordingTo(String.CASE_INSENSITIVE_ORDER);
		});
	}
}