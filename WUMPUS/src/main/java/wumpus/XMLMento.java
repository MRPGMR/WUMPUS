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
            FileWriter fileWriter = new FileWriter("jatekosAdatok.xml");
            fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            fileWriter.write("<JatekosAdatok>\n");
            fileWriter.write("    <JatekosNeve>" + jatekosneve + "</JatekosNeve>\n");
            fileWriter.write("    <LepesekSzama>" + lepesekszama + "</LepesekSzama>\n");
            fileWriter.write("    <Palya>\n");
            for (String sor : palyaAdatok) {
                fileWriter.write("        <Sor>" + sor + "</Sor>\n");
            }
            fileWriter.write("    </Palya>\n");
            fileWriter.write("</JatekosAdatok>");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

