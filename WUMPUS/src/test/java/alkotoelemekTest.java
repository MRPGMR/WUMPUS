import org.junit.jupiter.api.Test;
import wumpus.alkotoelemek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class alkotoelemekTest {
    @Test
    public void testPalyaInicializalas() {
        // Teszteljük, hogy a pálya inicializálása helyesen történik-e
        alkotoelemek.palyaMeret = 6; // Például beállítunk egy pálya méretet
        alkotoelemek.palyaInicializalas();

        // Ellenőrizzük a pálya méretét és a kezdő pozíciókat
        assertEquals(8, alkotoelemek.vilag.length); // A pálya mérete a falakkal együtt
        assertEquals('H', alkotoelemek.vilag[alkotoelemek.HosX][alkotoelemek.HosY]); // Kezdő pozíció megfelelő-e
    }
    @Test
    public void testBalraFordul() {
        // Teszteljük, hogy a balraFordul metódus helyesen változtatja-e az irányt
        alkotoelemek.irany = 'N'; // Kezdetben az észak felé nézünk

        alkotoelemek.balraFordul(); // Balra fordulás

        // Ellenőrizzük, hogy az irány megfelelően változott-e
        assertEquals('W', alkotoelemek.irany); // Az északról balra fordulva nyugat felé nézünk
    }

    @Test
    public void testJobbraFordul() {
        // Teszteljük, hogy a jobbraFordul metódus helyesen változtatja-e az irányt
        alkotoelemek.irany = 'E'; // Kezdetben kelet felé nézünk

        alkotoelemek.jobbraFordul(); // Jobbra fordulás

        // Ellenőrizzük, hogy az irány megfelelően változott-e
        assertEquals('S', alkotoelemek.irany); // Keletről jobbra fordulva dél felé nézünk
    }
    @Test
    public void testAranyElhelyezese() {
        alkotoelemek.palyaMeret = 5; // Például beállítunk egy pálya méretet
        alkotoelemek.palyaInicializalas();

        char[][] vilag = alkotoelemek.vilag; // Pálya állapotának lekérése

        boolean aranyElhelyezve = false;

        // Ellenőrizzük, hogy van-e 'G' az arany jelölés a pályán
        for (int i = 0; i < vilag.length; i++) {
            for (int j = 0; j < vilag[i].length; j++) {
                if (vilag[i][j] == 'G') {
                    aranyElhelyezve = true;
                    break;
                }
            }
        }

        assertTrue(aranyElhelyezve); // Ellenőrizzük, hogy az arany megfelelően el lett-e helyezve a pályán
    }

    @Test
    public void testFalakMegjelenese() {
        alkotoelemek.palyaMeret = 5; // Például beállítunk egy pálya méretet
        alkotoelemek.palyaInicializalas();

        char[][] vilag = alkotoelemek.vilag; // Pálya állapotának lekérése

        // Ellenőrizzük, hogy a falak (W) megjelennek-e a pályán a megfelelő helyeken
        for (int i = 0; i < vilag.length; i++) {
            for (int j = 0; j < vilag[i].length; j++) {
                if (i == 0 || i == vilag.length - 1 || j == 0 || j == vilag[i].length - 1) {
                    assertEquals('W', vilag[i][j]); // Falat (W) kell találnunk a pálya szélein
                }
            }
        }
    }
    @Test
    public void testWumpusGeneralas() {
        alkotoelemek.palyaMeret = 5; // Például beállítunk egy pálya méretet
        alkotoelemek.WumpusokSzama = 3; // Például beállítunk három Wumpust
        alkotoelemek.palyaInicializalas();

        char[][] vilag = alkotoelemek.vilag; // Pálya állapotának lekérése

        int wumpusCount = 0;

        // Ellenőrizzük, hogy a megfelelő számú Wumpus (U) van-e a pályán
        for (int i = 0; i < vilag.length; i++) {
            for (int j = 0; j < vilag[i].length; j++) {
                if (vilag[i][j] == 'U') {
                    wumpusCount++;
                }
            }
        }

        assertEquals(3, wumpusCount); // Ellenőrizzük, hogy a megadott számú Wumpus van-e a pályán
    }
    @Test
    public void testVeremekGeneralasa() {
        alkotoelemek.palyaMeret = 5; // Például beállítunk egy pálya méretet
        alkotoelemek.palyaInicializalas();

        char[][] vilag = alkotoelemek.vilag; // Pálya állapotának lekérése

        int veremekCount = 0;

        // Ellenőrizzük, hogy mennyi a veremek (P) száma a pályán
        for (int i = 0; i < vilag.length; i++) {
            for (int j = 0; j < vilag[i].length; j++) {
                if (vilag[i][j] == 'P') {
                    veremekCount++;
                }
            }
        }

        // Az elvárt veremek száma: a pályaméretet kivéve a pálya területén
        int expectedVeremek = (alkotoelemek.palyaMeret - 2) * (alkotoelemek.palyaMeret - 2);

        assertEquals(expectedVeremek, veremekCount); // Ellenőrizzük, hogy a megfelelő számú verem van-e a pályán
    }
}

