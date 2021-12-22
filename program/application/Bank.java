package application;

import java.util.Scanner;

public class Bank {
    Playground playground;                          //einbindung spielbrett(koffer)
    double offer;                                   //angebot

    public Bank(Playground playground) {
        this.playground = playground;
        this.offer = 0;
    }

    public boolean makeOffer() {                    //angebot erstellen
        double value = 0;
        int count = 0;

        for (int i = 0; i < playground.manager.suitcases.size(); i++) { //alle koffer ansehn
            if (!playground.manager.suitcases.get(i).open) {            //wenn koffer geschlossen
                count++;
                value = value + playground.manager.suitcases.get(i).value;
            }
        }
        value = value / count;                                          //formel für deal
        value = value * playground.round;
        value = value / 10;
        offer = value;
        System.out.println();
        System.out.println("Die Bank bietet dir " + value + " an");

        Scanner scanner = new Scanner(System.in);                       //scannereingabe ob deal
        boolean jn = false;
        char selection;
        do {                                                            //mind einmal durchlaufen
            System.out.print("Willst du den Deal machen? (J/N): ");
            String input = scanner.nextLine().toLowerCase();            //string in kleinbuchstaben umwandeln
            selection = input.charAt(0);
            if ((selection == 'j') || (selection == 'n')) {
                jn = true;
            } else {
                System.out.println("Bitte gib J oder N ein.");
            }
        } while (!jn);

        boolean deal = (selection == 'j');                              //deal

        return deal;
    }
}
