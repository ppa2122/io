package pl.kognitywistyka.io;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Created by pwilkin on 03.03.2022.
 */
public class LibXMLIOTest {

    @BeforeAll
    public static void testWriteXML() {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element gs = document.createElement("gameStats");
            document.appendChild(gs);
            Element crossWins = document.createElement("crossWins");
            crossWins.setAttribute("firstWin", new Date().toString());
            crossWins.setTextContent("15");
            Element circleWins = document.createElement("circleWins");
            circleWins.setTextContent("10");
            Element ties = document.createElement("ties");
            ties.setTextContent("45");
            gs.appendChild(crossWins);
            gs.appendChild(circleWins);
            gs.appendChild(ties);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("test2.xml"));
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException | TransformerException e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void testReadXML() {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            try (FileInputStream input = new FileInputStream(new File("test2.xml"))) {
                Document doc = documentBuilder.parse(input);
                XPath xPath = XPathFactory.newInstance().newXPath();
                Node node = (Node) xPath.compile("//gameStats/ties").evaluate(doc, XPathConstants.NODE);
                Assertions.assertEquals("45", node.getTextContent());
            } catch (IOException | SAXException | XPathExpressionException e) {
                Assertions.fail(e);
            }
        } catch (ParserConfigurationException e) {
            Assertions.fail(e);
        }

    }

}
