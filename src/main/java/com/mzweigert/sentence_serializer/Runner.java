package com.mzweigert.sentence_serializer;

import com.mzweigert.sentence_serializer.domain.Sentence;
import com.mzweigert.sentence_serializer.service.reader.LengthyInputException;
import com.mzweigert.sentence_serializer.service.reader.SentencesReader;
import com.mzweigert.sentence_serializer.service.serializer.FileSerializationService;
import com.mzweigert.sentence_serializer.service.serializer.FileSerializationServiceFactory;
import com.mzweigert.sentence_serializer.service.serializer.SerializationType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

public class Runner {

	private final String[] args;
	private FileSerializationService xmlSerializationService;
	private FileSerializationService csvSerializationService;
	private String outputDir;

	public Runner(String[] args, FileSerializationService xmlSerializerService, FileSerializationService csvSerializerService) {
		this.args = args;
		this.xmlSerializationService = xmlSerializerService;
		this.csvSerializationService = csvSerializerService;
		Properties properties = new Properties();
		try {
			properties.load(getClass()
					.getClassLoader()
					.getResourceAsStream("config.properties")
			);
			this.outputDir = properties.getProperty("output_dir").trim();
		} catch (IOException e) {
			System.out.println("Output dir property not found!");
			this.outputDir = "output/";
		}
	}

	public static void main(String[] args) {
		FileSerializationService xmlSerializerService = FileSerializationServiceFactory.getInstance(SerializationType.XML);
		FileSerializationService csvSerializerService = FileSerializationServiceFactory.getInstance(SerializationType.CSV);
		new Runner(args, xmlSerializerService, csvSerializerService).run();
	}

	void run() {
		File file = null;
		if (args.length > 0) {
			file = new File(args[0]);
		} else {
			System.out.println("Arg with file not given!");
			return;
		}

		if (!file.exists()) {
			System.out.println("No file found with name " + file.getName());
		} else {
			serializeSentences(file);
		}

	}

	private void serializeSentences(File file) {

		Optional<String> directory = createOrFindDirectory();
		if (!directory.isPresent()) {
			System.out.println("Cannot create output directory...");
		}
		try {
			Collection<Sentence> sentences = new SentencesReader(file).read();
			File xmlFile = new File(directory.get() + "sentences.xml");
			xmlSerializationService.serialize(xmlFile, sentences);

			File csvFile = new File(directory.get() + "sentences.csv");
			csvSerializationService.serialize(csvFile, sentences);
		} catch (LengthyInputException e) {
			System.out.println("Lengthy input");
		}
	}

	private Optional<String> createOrFindDirectory() {
		String path = "./" + outputDir + "/";
		boolean success = true;
		if (!Files.isDirectory(Paths.get(path))) {
			success = new File(path).mkdirs();
		}
		return Optional.ofNullable(success ? path : null);
	}
}
