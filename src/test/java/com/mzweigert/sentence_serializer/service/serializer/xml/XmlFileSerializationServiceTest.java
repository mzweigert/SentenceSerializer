package com.mzweigert.sentence_serializer.service.serializer.xml;

import com.mzweigert.sentence_serializer.domain.Sentence;
import com.mzweigert.sentence_serializer.service.serializer.FileSerializationService;
import com.mzweigert.sentence_serializer.service.serializer.FileSerializationServiceFactory;
import com.mzweigert.sentence_serializer.service.serializer.SerializationType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class XmlFileSerializationServiceTest {

	private FileSerializationService service = FileSerializationServiceFactory.getInstance(SerializationType.XML);

	private String directory = "./output_test/";
	private String fileName = "file";
	private String filePath = directory + fileName + ".xml";

	@Before
	public void setUp() {
		new File(directory).mkdir();
	}

	@After
	public void after() throws IOException {
		delete(new File(directory));
	}

	private void delete(File f) throws IOException {
		if (f.isDirectory()) {
			for (File c : Objects.requireNonNull(f.listFiles()))
				delete(c);
		}
		if (!f.delete())
			throw new FileNotFoundException("Failed to delete file: " + f);
	}

	@Test
	public void givenFile_whenSerialize_ThenSuccessCreatingXmlFile() {
		//GIVEN
		File file = new File(filePath);
		Collection<Sentence> sentences = createSentences();

		//WHEN
		service.serialize(file, sentences);

		//THEN
		try (FileReader fileReader = new FileReader(filePath);
			 BufferedReader reader = new BufferedReader(fileReader)) {
			String line = reader.readLine();
			assertThat(line).isNotEmpty();
			assertThat(line).contains("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Collection<Sentence> createSentences() {
		List<Sentence> sentences = new LinkedList<>();
		sentences.add(createSentence("This", "is", "sample", "sentence", "1"));
		sentences.add(createSentence("This", "is", "sample", "sentence", "2"));
		sentences.add(createSentence("This", "is", "sample", "sentence", "3"));
		return sentences;
	}

	private Sentence createSentence(String... words) {
		Sentence sentence = new Sentence();
		Arrays.stream(words).forEach(sentence::addWord);
		return sentence;
	}
}