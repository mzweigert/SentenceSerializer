package com.mzweigert.sentence_serializer.service.serializer.xml;

import com.mzweigert.sentence_serializer.domain.Sentence;
import com.mzweigert.sentence_serializer.service.serializer.FileSerializationService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Collection;

public class XmlFileSerializationService implements FileSerializationService {

	@Override
	public void serialize(File file, Collection<Sentence> sentences) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(SentencesWrapper.class, AdaptedSentence.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			SentencesWrapper wrapper = new SentencesWrapper(sentences);

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.marshal(wrapper, file);
			System.out.println("File : " + file.getName() + " created in directory: " + file.getPath());

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
