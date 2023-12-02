import org.json.JSONException;
import org.junit.jupiter.api.Test;
import wumpus.Jatek;

import static org.junit.jupiter.api.Assertions.*;

public class JatekTest {

    @Test
    public void testPalyaMeretValidInput() {
        Jatek.palyaMeret = 10;
        assertEquals(10, Jatek.palyaMeret);
    }


    @Test
    public void testNyilKilovesWumpusHit() throws JSONException {
        Jatek.palyaMeret = 10;
        Jatek.HosX = 5;
        Jatek.HosY = 5;
        Jatek.irany = 'N';
        Jatek.vilag = new char[12][12]; // Pálya inicializálása
        Jatek.vilag[5][6] = 'U'; // Wumpus elhelyezése a hős szomszédságában

        Jatek jatek = new Jatek();
        jatek.NyilKiloves();

        assertEquals('X', Jatek.vilag[5][6]); // Ellenőrzi, hogy a wumpus helyére X került-e
    }
}
