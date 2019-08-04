package com.mzweigert.sentence_serializer.service.reader;

import com.mzweigert.sentence_serializer.domain.Sentence;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SentencesReader {

    private File file;

    public SentencesReader(File file) {
        this.file = file;
    }

    public Collection<Sentence> read() {

        return Collections.emptyList();
    }
}
