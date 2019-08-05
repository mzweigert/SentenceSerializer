package com.mzweigert.sentence_serializer.service.serializer.xml;

import com.mzweigert.sentence_serializer.domain.Sentence;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "sentence")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdaptedSentence {

	@XmlElement(name = "word")
	private Collection<String> words;

	public AdaptedSentence(Sentence sentence) {
		this.words = sentence.getWords();
	}

	public AdaptedSentence() {
	}

	public Collection<String> getWords() {
		return words;
	}

	public void setWords(Collection<String> words) {
		this.words = words;
	}
}
