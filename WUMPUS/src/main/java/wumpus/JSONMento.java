package wumpus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;


public class JSONMento {
    static void saveToJSON(String jatekosneve, int lepesekszama, String[] palyaAdatok) throws JSONException {
        JSONObject jsonPlayerData = new JSONObject();
        jsonPlayerData.put("Játékos neve:", jatekosneve);
        jsonPlayerData.put("Lépések száma: ", lepesekszama);

        // Pálya adatok hozzáadása a JSON objektumhoz
        JSONArray palyaArray = new JSONArray();
        for (String sor : palyaAdatok) {
            palyaArray.put(sor);
        }
        jsonPlayerData.put("A győzelem pillanata:", palyaArray);

        try (FileWriter file = new FileWriter("jatekosAdatok.json")) {
            file.write(jsonPlayerData.toString(4)); // A szép formázásért: toString(4)
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}