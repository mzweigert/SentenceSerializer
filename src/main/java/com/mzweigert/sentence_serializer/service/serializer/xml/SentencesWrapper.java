package com.mzweigert.sentence_serializer.service.serializer.xml;

import com.mzweigert.sentence_serializer.domain.Sentence;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.stream.Collectors;

@XmlRootElement(name = "text")
@XmlAccessorType(XmlAccessType.FIELD)
public class SentencesWrapper {

	@XmlAnyElement(lax = true)
	private Collection<AdaptedSentence> sentences;

	public SentencesWrapper() {
	}

	public SentencesWrapper(Collection<Sentence> sentences) {
		this.sentences = sentences.stream()
				.map(AdaptedSentence::new)
				.collect(Collectors.toList());
	}

	public Collection<AdaptedSentence> getSentences() {
		return sentences;
	}

	public void setSentences(Collection<AdaptedSentence> sentences) {
		this.sentences = sentences;
	}
}


