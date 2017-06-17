import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XmlParser {

	public static void testDom() throws SAXException, IOException, ParserConfigurationException{
		Document document= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("resource/test.xml"));

		Node node = document.getDocumentElement();
		Node childNode = node.getFirstChild();
		NodeList nodeList = document.getElementsByTagName("word");
		Element element = (Element) ((Element)nodeList.item(0)).getElementsByTagName("surface").item(0);

		System.out.println(element.getTextContent());
	}
}
