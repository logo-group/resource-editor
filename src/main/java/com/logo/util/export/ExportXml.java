package com.logo.util.export;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.logo.data.entity.ReResourceitemShort;
import com.logo.data.entity.ReTurkishtr;
import com.logo.data.repository.ReResourceitemShortRep;
import com.logo.data.repository.ReTurkishtrRep;
import com.logo.util.search.SearchParam;

public class ExportXml implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ExportXml.class.getName());

	private ReResourceitemShortRep reResourceitemShortRep;
	private ReTurkishtrRep reTurkishtrRep;
	private final transient String fileName;

	private final transient SearchParam sParam;

	private static int prevRef = 0;
	private static Element firstTaggedList = null;

	public ExportXml(SearchParam sParam, String fileName, ReResourceitemShortRep reResourceitemShortRep,
			ReTurkishtrRep reTurkishtrRep) {
		this.sParam = sParam;
		this.fileName = fileName;
		this.reResourceitemShortRep = reResourceitemShortRep;
		this.reTurkishtrRep = reTurkishtrRep;
	}

	public void export() throws IOException, TransformerException, ParserConfigurationException {
		logger.log(Level.WARNING, "Creating xml");
		prevRef = 0;
		firstTaggedList = null;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element taggedLists = doc.createElement("TaggedLists");
		doc.appendChild(taggedLists);

		Page<com.logo.data.entity.ReResourceitemShort> resourceItems = searchByresourceParamAll(0, 1000, sParam);
		ArrayDeque<ReResourceitemShort> newList1 = new ArrayDeque<>();
		newList1.addAll(resourceItems.getContent());

		AtomicInteger counter = new AtomicInteger(0);
		newList1.stream().forEach(item -> addNewItemToXML(doc, taggedLists, item, counter));

		long totalPage = resourceItems.getTotalElements() / 1000;

		for (int i = 1; i < totalPage + 1; i++) {
			resourceItems = searchByresourceParamAll(i, 1000, sParam);
			ArrayDeque<ReResourceitemShort> newList = new ArrayDeque<>();
			newList.addAll(resourceItems.getContent());
			newList.stream().forEach(item -> addNewItemToXML(doc, taggedLists, item, counter));
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(fileName));
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.transform(source, result);

		logger.log(Level.WARNING, "xml done");
	}

	private void addNewItemToXML(Document doc, Element rootElement, ReResourceitemShort item, AtomicInteger counter) {

		if (prevRef != item.reResource.getId()) {
			Element taggedList = createNewTaggedList(doc, rootElement, item);
			firstTaggedList = taggedList;
		}
		createNewItem(doc, item, firstTaggedList);

		prevRef = item.reResource.getId();
	}

	private Element createNewTaggedList(Document doc, Element rootElement, ReResourceitemShort item) {
		Element taggedList = doc.createElement("TaggedList");

		Attr resourceGroup = doc.createAttribute("ResourceGroup");
		resourceGroup.setValue(item.reResource.getResourcegroup().name());
		taggedList.setAttributeNode(resourceGroup);

		Attr resourceType = doc.createAttribute("ResourceType");
		resourceType.setValue(item.reResource.getResourcetype().name());
		taggedList.setAttributeNode(resourceType);

		Attr listID = doc.createAttribute("ListID");
		listID.setValue(Integer.toString(item.reResource.getResourcenr()));
		taggedList.setAttributeNode(listID);

		Attr listName = doc.createAttribute("ListName");
		listName.setValue(item.reResource.getDescription());
		taggedList.setAttributeNode(listName);

		Attr prefix = doc.createAttribute("Prefix");
		prefix.setValue(item.getPrefixstr());
		taggedList.setAttributeNode(prefix);
		rootElement.appendChild(taggedList);

		return taggedList;
	}

	private void createNewItem(Document doc, ReResourceitemShort item, Element taggedList) {
		ReTurkishtr reTurkishtr = reTurkishtrRep.getresourceitemrefEquals(item.getId());

		Element listItem = doc.createElement("ListItem");

		Attr tag = doc.createAttribute("Tag");
		tag.setValue(Integer.toString(item.getTagnr()));
		listItem.setAttributeNode(tag);

		Attr resItem = doc.createAttribute("Item");
		if (reTurkishtr != null)
			resItem.setValue(reTurkishtr.getResourcestr());
		listItem.setAttributeNode(resItem);

		taggedList.appendChild(listItem);

	}

	private Page<com.logo.data.entity.ReResourceitemShort> searchByresourceParamAll(int page, int size,
			SearchParam sParam) {
		Pageable pageable = new PageRequest(page, size, null);
		return reResourceitemShortRep.searchByParamAll(pageable, sParam);
	}

}
