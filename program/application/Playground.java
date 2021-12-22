package application;

import java.util.Scanner;

public class Playground {                                       //spielbrett
    SuitcaseManager manager;                                    //manager einbinden
    Bank bank;                                                  //bank einbinden
    int round;                                                  //rundenzähler

    public Playground(SuitcaseManager manager) {
        this.manager = manager;
        this.bank = new Bank(this);
        this.round = 0;
    }

    public void Paint() {                                       //alle koffer zeichnen
        int size = manager.suitcases.size();
        int rowLen = size / 3;                                  //anzahl der koffer in einer reihe
        int index = 0;
        while (index < size) {
            for (int i = 0; i < rowLen; i++) {
                if (index < size) {
                    manager.suitcases.get(index).Paint();
                    index++;
                }
            }
            System.out.println();
            System.out.println();
        }
    }

    public int selectSuitcase() {                               //auswählen eines koffers und gibt koffernummer zurück
        Paint();
        int selection = -1;
        boolean tryAgain;
        do {
            tryAgain = false;
            System.out.print("Wähle eine Koffernummer aus: ");
            Scanner scanner = new Scanner(System.in);
            selection = scanner.nextInt();
            if ((selection < 1) || (selection > manager.suitcases.size())) {
                System.out.println("Wähle einen Koffer zwischen 1 und " + manager.suitcases.size());
                tryAgain = true;
            } else if (manager.suitcases.get(selection - 1).selected) {
                System.out.println("Dieser Koffer liegt bereits bei dir am Tisch!");
                tryAgain = true;
            } else if (manager.suitcases.get(selection - 1).open) {
                System.out.println("Diesen Koffer hast du bereits geöffnet!");
                tryAgain = true;
            }
        } while (tryAgain);
        return selection - 1;
    }

    public void selectFirst() {                                             //erste wahl
        System.out.println("Du wirst jetzt deinen ersten Koffer wählen.");
        System.out.println("Dieser bleibt bei dir am Tisch");
        System.out.println("Danach musst du 6 Koffer auswählen, die aus dem Spiel ausscheiden");
        System.out.println("Wenn die Koffer weg sind, bietet dir die Bank einen Deal für deinen Koffer an");
        System.out.println("Los gehts");
        System.out.println();
        int selection = selectSuitcase();
        manager.suitcases.get(selection).selected = true;
    }

    public void openSuitcase(int count) {                                   //öffnet anzahl der koffer
        round++;
        System.out.println();
        System.out.println("Runde: " + round);
        System.out.println("Wähle " + count + " Koffer aus, die das Spiel verlassen sollen:");
        System.out.println();
        for (int i = 0; i < count; i++) {
            int selection = selectSuitcase();
            manager.suitcases.get(selection).open = true;
        }
    }

    public boolean MakeDeal() {                                             //macht ein angebot
        Paint();
        boolean deal = bank.makeOffer();
        if (deal) {
            System.out.println("Gratuliere du hast " + bank.offer + " gewonnen!!!");
            System.out.println("In deinem Koffer wären " + manager.GetValue(manager.GetSelectedSuitcase() + 1) + " gewesen.");
            manager.suitcases.get(manager.GetSelectedSuitcase()).open = true;
            Paint();
        }
        return deal;
    }

    public int LastSuitcase() {
        return manager.LastSuitcase();
    }

    public boolean SwitchSuitcases() {                                      //letzten koffer wechseln oder nicht
        boolean retVal = false;
        Paint();
        System.out.println("Die letzte Runde");
        System.out.println("Du kannst deinen Koffer gegen den anderen tauschen.");
        Scanner scanner = new Scanner(System.in);
        boolean jn = false;
        char selection;
        do {
            System.out.println("Soll der der Koffer getauscht werden? (J/N):");
            String input = scanner.nextLine().toLowerCase();
            selection = input.charAt(0);
            if ((selection == 'j') || (selection == 'n')) {
                jn = true;
            } else {
                System.out.println("Bitte gib J oder N ein.");
            }
        } while (!jn);
        retVal = (selection == 'j');
        if (retVal) {
            int last = manager.LastSuitcase();
            int selected = manager.GetSelectedSuitcase();
            manager.suitcases.get(selected).selected = false;
            manager.suitcases.get(selected).open = true;
            manager.suitcases.get(last).selected = true;
            manager.suitcases.get(last).open = true;
            Paint();
        }
        return retVal;
    }

    public void PrintPrice() {
        System.out.println("Gratuliere, du hast " + manager.GetValue(manager.GetSelectedSuitcase() + 1) + " gewonnen!");
    }

}
