package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuitcaseManager {
    private static final double[] SUITVALUE = new double[]{0.01, 1, 5, 10, 25, 50, 75, 100, 200, 300, 400, 500, 750, 1000, 5000, 10000, 25000, 50000, 75000, 100000, 200000, 300000, 400000, 500000, 750000, 1000000}; //kofferinhalte
    List<Suitcase> suitcases = new ArrayList<>();                       //koffernummer

    public SuitcaseManager(boolean debug) {
        Init(debug);                                                    //koffer wert zuordnen und sortieren
    }

    private void Init(boolean debug) {                                  //ordnet jeden koffer einen geldbetrag zu
        int count = SUITVALUE.length;
        for (int i = 0; i < count; i++) {                               //iteriert alle werte
            int scNumber = 0;
            if (debug) {                                                //im debugmodus - koffer der reihe nach
                scNumber = i + 1;
            } else {
                while ((scNumber < 1) || (GetValue(scNumber) != 0)) {   //solange die zufällig generierte nummer kleiner 1 ist oder die generierte nummer schon vorhanden ist
                    int min = 1;
                    int max = count;
                    int range = max - min + 1;
                    scNumber = (int) (Math.random() * range) + min;
                }
            }
            Suitcase newSuitcase = new Suitcase(SUITVALUE[i], scNumber);//erzeugt neuen koffer mit generierter nummer und inhalt
            suitcases.add(newSuitcase);                                 //hängt koffer in die liste dazu
        }
        Collections.sort(suitcases);                                    //sortieren
    }

    public double GetValue(int scNumber) {                              //liefert inhalt des koffers mit koffernummer zurück
        double retVal = 0;
        for (int i = 0; i < suitcases.size(); i++) {
            if (suitcases.get(i).number == scNumber) {
                retVal = suitcases.get(i).value;
            }
        }
        return retVal;
    }

    public void Print() {                                               //zeichnet koffer in konsole
        for (int i = 0; i < suitcases.size(); i++) {
            System.out.println(suitcases.get(i).number + " " + suitcases.get(i).value);
        }
    }

    public int GetSelectedSuitcase() {                                  //gibt die nummer des koffers vom tisch zurück
        int retVal = -1;
        for (int i = 0; i < suitcases.size(); i++) {
            if (suitcases.get(i).selected) {
                retVal = i;
            }
        }
        return retVal;
    }

    public int LastSuitcase() {                                         //sucht den letzten koffer der noch zu ist und liefert seine nummer zurück
        int retVal = -1;                                                //gibt es noch mehrere koffer wird -1 zurückgeliefert
        int count = 0;
        for (int i = 0; i < suitcases.size(); i++) {
            if ((!suitcases.get(i).open) && (!suitcases.get(i).selected)) {
                retVal = i;
                count++;
            }
        }
        if (count > 1) {
            retVal = -1;
        }
        return retVal;
    }

}
