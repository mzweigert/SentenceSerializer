package com.mzweigert.sentence_serializer.service.serializer.csv;

import com.mzweigert.sentence_serializer.TestUtils;
import com.mzweigert.sentence_serializer.domain.Sentence;
import com.mzweigert.sentence_serializer.service.serializer.FileSerializationService;
import com.mzweigert.sentence_serializer.service.serializer.FileSerializationServiceFactory;
import com.mzweigert.sentence_serializer.service.serializer.SerializationType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;

public class CsvFileSerializationServiceTest {


	private FileSerializationService service = FileSerializationServiceFactory.getInstance(SerializationType.CSV);

	private String directory = "./output_test/";
	private String fileName = "file";
	private String filePath = directory + fileName + ".xml";

	@Before
	public void setUp() {
		new File(directory).mkdir();
	}

	@After
	public void after() throws IOException {
		TestUtils.delete(new File(directory));
	}

	@Test
	public void givenFile_whenSerialize_ThenSuccessCreatingCsvFile() {
		//GIVEN
		File file = new File(filePath);
		Collection<Sentence> sentences =  TestUtils.createSentences();

		//WHEN
		service.serialize(file, sentences);

		//THEN
		try (FileReader fileReader = new FileReader(filePath);
			 BufferedReader reader = new BufferedReader(fileReader)) {
			String line = reader.readLine();
			assertThat(line).isNotEmpty();
			assertThat(line).contains(",Word 1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}