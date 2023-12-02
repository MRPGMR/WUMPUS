
package wumpus;

import java.util.Scanner;

import org.json.JSONException;

public class Jatek extends alkotoelemek {


    public Jatek() throws JSONException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Üdvözöllek a Wumpus Játékban");
        System.out.println("Kérlek add meg a játékosneved");
        JatekosNev = scanner.nextLine();

        //PÁLYA BEKÉRÉSE
        do {
            System.out.println("Add meg a pálya méretét (N x N, falakon kívül, 6 és 20 között): ");
            palyaMeret = scanner.nextInt();
        } while (palyaMeret < 6 || palyaMeret > 20);

        //WUMPUSOK SZÁMA PÁLYAMÉRET ALAPJÁN
        if (palyaMeret <= 8) {
            WumpusokSzama = 1;
        } else if (palyaMeret <= 14) {
            WumpusokSzama = 2;
        } else {
            WumpusokSzama = 3;
        }

        //NYILAK SZÁMÁNAK BEÁLLÍTÁSA
        NyilakSzama = WumpusokSzama;

        palyaInicializalas();
        PalyaKiiras();
        System.out.println("Jó játékot! Sok szerencsét!");

        while (true) {
            System.out.println("Az irányok az égtájak angol kezdőbetűi,de az egyszerűség kedvéért:\n \t| N=Fel | E=JOBBRA | S=LE | W=BALRA | ");
            System.out.println("A |W| billentyű lenyomásával mozogsz,A |K| betűvel kilépsz, |Q|/|E| betűvel fordulsz, |L| betűvel lősz ");
            char mozgas = scanner.next().charAt(0);

            if (mozgas == 'K' || mozgas == 'k') {
                System.out.println("Vége a játéknak!");
                System.exit(0);
            } else if (mozgas == 'E' || mozgas == 'e') {
                // Jobbra forgás esetén változtassa meg az irányt
                jobbraFordul();
                PalyaKiiras(); // Pálya kiírása irányváltoztatás után
            } else if (mozgas == 'Q' || mozgas == 'q') {
                // Balra forgás esetén változtassa meg az irányt
                balraFordul();
                PalyaKiiras(); // Pálya kiírása irányváltoztatás után
            } else if (mozgas == 'L' || mozgas == 'l') {
                // Nyíl kilövése az aktuális irányba
                NyilKiloves();
                PalyaKiiras(); // Pálya kiírása lövés után
            } else {
                Mozogas(mozgas);
                PalyaKiiras();

            }
        }

    }


}

