package main.com.ovi.Writers;

import main.com.ovi.Models.Athlete;
import main.com.ovi.Models.Exceptions.WriterException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLAthleteWriter implements AthleteWriter {
    private Document document;
    private Transformer transformer;
    StreamResult streamResult;
    DOMSource domSource;

    public XMLAthleteWriter(String filename, Boolean prettyPrint) throws WriterException {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            document = documentBuilder.newDocument();
            streamResult = new StreamResult(new File(filename));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();

            if (prettyPrint) {
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            }

            domSource = new DOMSource(document);
        } catch (Exception e) {
            throw new WriterException("Failed to create athlete output file.", e);
        }
    }

    public void write(List<Athlete> athletes) throws WriterException {
        try {
            Element rootEl = document.createElement("athletes");

            for (Athlete a : athletes) {
                rootEl.appendChild(athleteToXmlNode(a));
            }

            document.appendChild(rootEl);
            transformer.transform(domSource, streamResult);
        } catch (Exception e) {
            throw new WriterException("Failed to write athletes to output file.", e);
        }
    }

    private Element athleteToXmlNode(Athlete athlete) {
        Element athleteEl = document.createElement("athlete");

        Element fn = document.createElement("name");
        fn.appendChild(document.createTextNode(athlete.getName()));
        athleteEl.appendChild(fn);

        Element perfEl = document.createElement("performance");
        athleteEl.appendChild(perfEl);
        List<Double> performance = athlete.getPerformance();

        for (Double p : performance) {
            Element valueEl = document.createElement("value");
            valueEl.appendChild(document.createTextNode(p.toString()));
            perfEl.appendChild(valueEl);
        }

        Element markEl = document.createElement("mark");
        markEl.appendChild(document.createTextNode(String.valueOf(athlete.getMark())));
        athleteEl.appendChild(markEl);

        Element rankEl = document.createElement("rank");
        rankEl.appendChild(document.createTextNode(String.valueOf(athlete.getRank())));
        athleteEl.appendChild(rankEl);

        return athleteEl;
    }
}
