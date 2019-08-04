package com.mzweigert.sentence_serializer;

import com.mzweigert.sentence_serializer.service.serializer.FileSerializationService;
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
	private FileSerializationService serializerService;

	@Test
	public void givenInvalidArgs_whenRun_thenNotRunningSerialization() {
		//GIVEN
		String[] args = new String[0];

		//WHEN
		new Runner(args, serializerService).run();

		//THEN
		verifyZeroInteractions(serializerService);
	}


	@Test
	public void givenCorrectArgs_whenRun_thenNotRunningCrawl() throws Exception {
		//GIVEN
		String pathname = "small.in";
		File file = new File(pathname);
		file.createNewFile();
		String[] args = new String[]{"small.in"};

		//WHEN
		new Runner(args, serializerService).run();

		//THEN
		TestUtils.delete(file);
		TestUtils.delete(new File("output_test"));
		verify(serializerService).serialize(any(), any());
	}

}