package com.mzweigert.sentence_serializer;

import com.mzweigert.sentence_serializer.service.serializer.FileSerializationService;
import com.mzweigert.sentence_serializer.service.serializer.FileSerializationServiceFactory;
import com.mzweigert.sentence_serializer.service.serializer.SerializationType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class RunnerTest {

	@Mock
	private FileSerializationService xmlSerializationService = FileSerializationServiceFactory.getInstance(SerializationType.XML);

	@Mock
	private FileSerializationService csvSerializationService = FileSerializationServiceFactory.getInstance(SerializationType.CSV);


	@Test
	public void givenInvalidArgs_whenRun_thenNotRunningSerialization() {
		//GIVEN
		String[] args = new String[0];

		//WHEN
		new Runner(args, xmlSerializationService, csvSerializationService).run();

		//THEN
		verifyZeroInteractions(xmlSerializationService);
		verifyZeroInteractions(csvSerializationService);
	}


	@Test
	public void givenCorrectArgs_whenRun_thenNotRunningCrawl() throws Exception {
		//GIVEN
		String pathname = "small.in";
		File file = new File(pathname);
		file.createNewFile();
		String[] args = new String[]{"small.in"};

		//WHEN
		new Runner(args, xmlSerializationService, csvSerializationService).run();

		//THEN
		TestUtils.delete(file);
		TestUtils.delete(new File("output_test"));
		verify(csvSerializationService).serialize(any(), any());
		verify(xmlSerializationService).serialize(any(), any());
	}

}