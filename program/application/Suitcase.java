package application;

import java.text.DecimalFormat;

public class Suitcase implements Comparable<Suitcase> {   //koffer(+sortieren)
    double value;
    int number;
    boolean open;
    boolean selected;

    public Suitcase(double value, int number) {
        this.value = value;
        this.number = number;
        open = false;
        selected = false;

    }

    public void Paint() {                                   //kofferzeichnen
        String box = " " + String.format("%1$2s", number);  //formatiert nummer auf zwei stellen
        if (open) {
            box = box + String.format("%1$11s", String.format("%.2f", value));//beschränkt den kofferwert auf 2 kommastellen und diesen auf 11 stellen: https://www.javatpoint.com/java-string-format
        } else {
            box = box + "           ";                      //kofferzeichen ohne kofferwert
        }
        box = box + " ";
        if (selected) {
            System.out.print(ConCol.YELLOW_BOLD_BRIGHT);    //koffer am tisch -gelb
        } else {
            if (open) {
                System.out.print(ConCol.RED_BOLD_BRIGHT);   //koffer offen rot
            } else {
                System.out.print(ConCol.WHITE_BOLD_BRIGHT); //unbenutzter koffer weiß
            }
        }
        System.out.print(ConCol.BLACK_BACKGROUND);
        System.out.print(box + ConCol.RESET);
        System.out.print(" ");
    }

    @Override
    public int compareTo(Suitcase o) {                      //komperator für koffer
        int retVal = 0;                                     //vergleicht koffer für sortierer
        if (o.number > this.number) {
            retVal = -1;
        }
        if (o.number < this.number) {
            retVal = 1;
        }
        return retVal;
    }
}
