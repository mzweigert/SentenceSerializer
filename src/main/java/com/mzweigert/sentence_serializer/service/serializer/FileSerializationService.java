package com.mzweigert.sentence_serializer.service.serializer;

import com.mzweigert.sentence_serializer.domain.Sentence;

import java.io.File;
import java.util.Collection;

public interface FileSerializationService {

    /**
     * Method serialize given sentences to file (depends on implementation)
     */
    void serialize(File file, Collection<Sentence> links);

}
