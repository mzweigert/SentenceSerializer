package com.mzweigert.sentence_serializer.service.reader;

import com.mzweigert.sentence_serializer.domain.Sentence;
import org.junit.Test;
import java.io.*;
import java.net.URL;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class SentencesReaderTest {

    private File readFile(String name) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = Objects.requireNonNull(classLoader.getResource(name));
        return new File(url.getFile());
    }

    @Test
    public void givenFile_whenReadSentences_thenSuccessReadSortedSentences() throws Exception {
        //GIVEN
        File file = readFile("small.in");

        //WHEN
        Collection<Sentence> sentences = new SentencesReader(file).read();

        //THEN
        assertThat(sentences).isNotEmpty();
        sentences.forEach(sentence -> {
            List<String> strings = new ArrayList<>(sentence.getWords());
            assertThat(strings).isSortedAccordingTo(String.CASE_INSENSITIVE_ORDER) ;
        });
    }


}