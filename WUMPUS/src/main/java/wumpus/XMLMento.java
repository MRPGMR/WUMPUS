package wumpus;

import java.io.FileWriter;
import java.io.IOException;

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

