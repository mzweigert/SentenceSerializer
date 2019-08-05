package com.mzweigert.sentence_serializer.service.serializer.csv;

import com.mzweigert.sentence_serializer.domain.Sentence;
import com.mzweigert.sentence_serializer.service.serializer.FileSerializationService;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CsvFileSerializationService implements FileSerializationService {

    @Override
    public void serialize(File file, Collection<Sentence> sentences) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(file.getPath()));
             CSVWriter csvWriter = new CSVWriter(writer,
                     CSVWriter.DEFAULT_SEPARATOR,
                     CSVWriter.NO_QUOTE_CHARACTER,
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                     CSVWriter.DEFAULT_LINE_END)) {

            List<String[]> rows = prepareRows(sentences);
            csvWriter.writeAll(rows);
            System.out.println("File : " + file.getName() + " created in directory: " + file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String[]> prepareRows(Collection<Sentence> sentences) {
        int i = 1, savedWordsSize = 0;
        LinkedList<String[]> rows = new LinkedList<>();
        for (Sentence sentence : sentences) {
            String[] startRow = {"Sentence " + i};

            Collection<String> words = sentence.getWords();
            savedWordsSize = Math.max(words.size(), savedWordsSize);
            String[] row = Stream.concat(Arrays.stream(startRow), words.stream())
                    .toArray(String[]::new);
            rows.add(row);
            i++;
        }
        String[] header = new String[savedWordsSize + 1];
        header[0] = "";
        IntStream.range(1, savedWordsSize + 1)
                .forEach(index -> header[index] = "Word " + index);
        rows.addFirst(header);
        return rows;
    }
}
