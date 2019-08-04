package com.mzweigert.sentence_serializer.service.serializer;

import com.mzweigert.sentence_serializer.service.serializer.xml.XmlFileSerializationService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FileSerializationServiceFactory {

	public static FileSerializationService getInstance(SerializationType type) {
		switch (type) {
			case XML:
				return new XmlFileSerializationService();
			case CSV:
				throw new NotImplementedException();
		}
		throw new NotImplementedException();
	}
}
