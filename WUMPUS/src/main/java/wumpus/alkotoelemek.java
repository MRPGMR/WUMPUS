package wumpus;

import org.json.JSONException;

public class alkotoelemek {
    public static int palyaMeret;

    public static char[][] vilag;
    public static int HosX;
    public static int HosY;
    public static int KezdoX;
    public static int KezdoY;
    public static int WumpusokSzama;
    public static int NyilakSzama;
    public static char irany = 'E'; // Kezdetben előre fordul
    public static boolean hasGold = false;
    static String JatekosNev;
    private static int lepesszam = 0;
    private static Adatbazis adatbazis;

    public static void palyaInicializalas() {
        // A pálya mérete a falak közötti területtel
        int PalyaMeret = Jatek.palyaMeret + 2;
        vilag = new char[PalyaMeret][PalyaMeret];

        // Pálya feltöltése falakkal és üres helyekkel
        for (int i = 0; i < PalyaMeret; i++) {
            for (int j = 0; j < PalyaMeret; j++) {
                if (i == 0 || i == PalyaMeret - 1 || j == 0 || j == PalyaMeret - 1) {
                    vilag[i][j] = 'W'; // Walls
                } else {
                    vilag[i][j] = ' ';
                }
            }
        }


        do {
            HosX = (int) (Math.random() * (Jatek.palyaMeret)) + 1;
            HosY = (int) (Math.random() * (Jatek.palyaMeret)) + 1;
        } while (vilag[HosX][HosY] == 'W');

        vilag[HosX][HosY] = 'H';
        KezdoX = HosX;
        KezdoY = HosY;

        //WUMPUSOK LÉTREHOZÁSA VÉLETLENSZERŰ POZÍCIÓBA
        for (int k = 0; k < WumpusokSzama; k++) {
            int wumpusX, wumpusY;
            do {
                wumpusX = (int) (Math.random() * (Jatek.palyaMeret)) + 1;
                wumpusY = (int) (Math.random() * (Jatek.palyaMeret)) + 1;
            } while (vilag[wumpusX][wumpusY] == 'W' || (wumpusX == HosX && wumpusY == HosY));

            vilag[wumpusX][wumpusY] = 'U';
        }
        for (int i = 1; i <= Jatek.palyaMeret; i++) {
            int pitX, pitY;
            do {
                pitX = (int) (Math.random() * (Jatek.palyaMeret)) + 1;
                pitY = (int) (Math.random() * (Jatek.palyaMeret)) + 1;
            } while (vilag[pitX][pitY] == 'W' || vilag[pitX][pitY] == 'P' || (pitX == HosX && pitY == HosY));

            vilag[pitX][pitY] = 'P';
        }

        //ARANY VÉLETLENSZERŰ ELHELYEZÉSE

        int aranyX;
        int aranyY;
        do {
            aranyX = (int) (Math.random() * (Jatek.palyaMeret)) + 1;
            aranyY = (int) (Math.random() * (Jatek.palyaMeret)) + 1;
        } while (vilag[aranyX][aranyY] == 'W' || (aranyX == HosX && aranyY == HosY));

        vilag[aranyX][aranyY] = 'G';
    }

    static void PalyaKiiras() {
        //A jelenlegi pálya kiiratása
        for (char[] chars : vilag) {
            for (int j = 0; j < vilag.length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
        System.out.println("Irány: " + irany); // Kiírja az aktuális irányt
        System.out.println("Nyilak: " + NyilakSzama); // Kiírja a nyilak számát
        System.out.println();
    }

    public static void Mozogas(char move) throws JSONException {

        vilag[HosX][HosY] = ' ';

        // Mozgás az aktuális irányba

        switch (move) {
            case 'W', 'w' -> {
                lepesszam++;
                elore();
            }
        }


        if (vilag[HosX][HosY] == 'U') {
            System.out.println("A jateknak vege, megölt a wumpus!");
            System.exit(0);

        }

        if (vilag[HosX][HosY] == 'P') {
            if (NyilakSzama == 0) {
                System.out.println("Verembe léptél,és mivel nincs nyilad,a játék végetért");
                System.exit(0);
            } else {
                System.out.println("Verembe léptél, és elejtettél egy nyilat");
                NyilakSzama--; // Elveszít egy nyilat
                vilag[HosX][HosY] = ' '; // A verem törlése


            }

        } else if (vilag[HosX][HosY] == 'G') {
            System.out.println("GRATULA, " + JatekosNev + "! Megtaláltad az aranyat");
            hasGold = true;


        }


        vilag[HosX][HosY] = 'H';


        if (hasGold && HosX == KezdoX && HosY == KezdoY) {

            System.out.println("KIAKRÁOOOLY? Gratulálok, " + JatekosNev + " nyertél");

            System.exit(0);

        }


        JSONMento.saveToJSON(JatekosNev, lepesszam, jelenlegiPalya());
        XMLMento.saveToXML(JatekosNev, lepesszam, jelenlegiPalya());

    }

    private static void elore() {
        // Mozgás az aktuális irányba
        switch (irany) {
            case 'N' -> {
                if (HosX > 1 && vilag[HosX - 1][HosY] != 'W') {
                    HosX--;
                }
            }
            case 'E' -> {
                if (HosY < palyaMeret && vilag[HosX][HosY + 1] != 'W') {
                    HosY++;
                }
            }
            case 'S' -> {
                if (HosX < palyaMeret && vilag[HosX + 1][HosY] != 'W') {
                    HosX++;
                }
            }
            case 'W' -> {
                if (HosY > 1 && vilag[HosX][HosY - 1] != 'W') {
                    HosY--;
                }
            }
        }
    }

    public static void balraFordul() {
        // Balra forgás esetén változtassa meg az irányt
        switch (irany) {
            case 'N' -> irany = 'W';
            case 'E' -> irany = 'N';
            case 'S' -> irany = 'E';
            case 'W' -> irany = 'S';
        }
        System.out.println("Irány: " + irany);
    }

    public static void jobbraFordul() {

        switch (irany) {
            case 'N' -> irany = 'E';
            case 'E' -> irany = 'S';
            case 'S' -> irany = 'W';
            case 'W' -> irany = 'N';
        }
    }

    public void NyilKiloves() {
        // Lövés esetén csökkentse a nyilak számát
        if (NyilakSzama > 0) {
            NyilakSzama--;

            System.out.println("Kilőttél egy nyilat, ennyi nyilad maradt: " + NyilakSzama);

            //Eltalálta- e a lövés
            int NyilX = HosX;
            int NyilY = HosY;

            while (true) {
                switch (irany) {
                    case 'N' -> NyilX--;
                    case 'E' -> NyilY++;
                    case 'S' -> NyilX++;
                    case 'W' -> NyilY--;
                }

                // Ha a nyíl eléri a pálya szélét, akkor eltűnik
                if (NyilX <= 0 || NyilX >= palyaMeret || NyilY <= 0 || NyilY >= palyaMeret) {
                    System.out.println("Nem talált, a nyíl, beleállt a falba!");
                    break;
                }

                // Ha a nyíl eltalál egy Wumpust, akkor a Wumpus helyén megjelenik egy 'X'
                if (vilag[NyilX][NyilY] == 'U') {
                    System.out.println("Meggyilkoltad a wumpust!");
                    vilag[NyilX][NyilY] = 'X';
                    break;
                }
            }
        }
    }

    public static String[] jelenlegiPalya() {
        String[] palya = new String[palyaMeret + 2];

        // Pálya felépítése soronként
        for (int i = 0; i < palyaMeret + 2; i++) {
            StringBuilder sor = new StringBuilder();
            for (int j = 0; j < palyaMeret + 2; j++) {
                sor.append(vilag[i][j]).append(" ");
            }
            palya[i] = sor.toString();
        }

        return palya;
    }


    public static void main(String[] args) throws JSONException {
        new Jatek();
    }
}