package wumpus;

import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class XMLMento {
    static void saveToXML(String jatekosneve, int lepesekszama, String[] palyaAdatok) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Gyökérelem létrehozása
            Element rootElement = doc.createElement("JatekosAdatok");
            doc.appendChild(rootElement);

            // Játékos neve és lépésszáma
            Element jatekosNeve = doc.createElement("JatekosNeve");
            jatekosNeve.appendChild(doc.createTextNode(jatekosneve));
            rootElement.appendChild(jatekosNeve);

            Element lepesekSzama = doc.createElement("LepesekSzama");
            lepesekSzama.appendChild(doc.createTextNode(String.valueOf(lepesekszama)));
            rootElement.appendChild(lepesekSzama);

            // Pálya adatok hozzáadása az XML-hez
            Element palya = doc.createElement("Palya");
            for (String sor : palyaAdatok) {
                Element palyaSor = doc.createElement("Sor");
                palyaSor.appendChild(doc.createTextNode(sor));
                palya.appendChild(palyaSor);
            }
            rootElement.appendChild(palya);

            // XML fájlba mentés
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter("jatekosAdatok.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}

