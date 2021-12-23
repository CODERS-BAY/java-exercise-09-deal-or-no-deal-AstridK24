package application;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean debug = false;                             //debugmodus aus-ein
        enum gameState {running, deal, end}                //spielstatus

        System.out.println("Deal or no Deal?");
        String name = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Willkommen bei Deal or no Deal");
        System.out.print("Gib deinen Namen ein: ");
        name = scanner.nextLine();                          //name von scannereingabe speichern
        System.out.println("Hallo " + name);

        SuitcaseManager manager = new SuitcaseManager(debug);    //neuen koffermanager anlegen
        Playground playground = new Playground(manager);    //neues spielbrett anlegen
        Bank bank = new Bank(playground);                   //neue bank anlegen

        playground.selectFirst();                           //erste runde - spielbeschreibung
        int counter = 6;                                    //kofferanzahl-zähler

        gameState curState = gameState.running;             //aktueller spielzustand

        do {                                                //los gehts!!!
            if (playground.LastSuitcase() > -1) {           //nur mehr ein koffer übrig?
                playground.SwitchSuitcases();               //letzten koffer wechsel?
                curState = gameState.end;                   //aus und vorbei!
            } else {
                playground.openSuitcase(counter);           //öffnet koffer
                if (playground.MakeDeal()) {                //angebot von der bank
                    curState = gameState.deal;              //angebot angenommen - ende
                }
            }
            if (counter > 1) {                              //ändert anzahl der zu öffnenden koffer
                counter--;
            }

        } while (curState == gameState.running);            //spielt solange spiel nicht beendet wird

        if (curState == gameState.end) {                    //kein deal und ende
            playground.PrintPrice();                        //preisanzeige
        }
    }
}

/*spielfeld inhalte anzeigen
26 kofferzahlen random
26 inhalte array
koffer entfernen
9 runden
kofferinhalt berechnen für deal
boolean - tauschen

* */
