/*
 * Copyright (c) 2019. BEST S.A. and/or its affiliates. All rights reserved.
 */
package com.mzweigert.sentence_serializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class TestUtils {

	public static void delete(File f) throws IOException {
		if (f.isDirectory()) {
			for (File c : Objects.requireNonNull(f.listFiles()))
				delete(c);
		}
		if (!f.delete())
			throw new FileNotFoundException("Failed to delete file: " + f);
	}

}
