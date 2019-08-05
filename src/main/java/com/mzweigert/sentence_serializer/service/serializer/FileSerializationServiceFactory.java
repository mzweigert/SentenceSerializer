package com.mzweigert.sentence_serializer.service.serializer;

import com.mzweigert.sentence_serializer.service.serializer.csv.CsvFileSerializationService;
import com.mzweigert.sentence_serializer.service.serializer.xml.XmlFileSerializationService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FileSerializationServiceFactory {

	public static FileSerializationService getInstance(SerializationType type) {
		switch (type) {
			case XML:
				return new XmlFileSerializationService();
			case CSV:
				return new CsvFileSerializationService();
		}
		throw new NotImplementedException();
	}
}
