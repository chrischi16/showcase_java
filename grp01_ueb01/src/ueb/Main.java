package ueb;

/**
 * Es soll ein Java-Programm erstellt werden, das für mehrere Zahlen angibt, ob sie ...
 * <p>
 * eine fröhliche Zahl eine Hashad-Zahl eine Münchhhausen-Zahl oder eine Mersenne-Zahl
 * <p>
 * ist.
 *
 * @author ###### & Christoph
 */
public class Main {
    //TODO - DONE JavaDoc hinzufügen

    /**
     * Main Methode des Programms, die von einem start Wert zu einem End wert auf geforderte eigenschaften einer
     * Zahl testen, wie Froehlich, Harshad, Munchhausen und Mersenne
     * @param args die dem Program beim start übergeben werden / übergeben werden können - nicht genutzt,
     *             standart vorgabe jeder psvm Methode
     */

    public static void main(String[] args) {
        // start wert von der aus getestet wird
        final int start = -5;
        // bis zu letzte zahl die getestet wird
        final int end   = 20;

        final int startWidth = calcDisplayWidth(start);
        final int endWidth   = calcDisplayWidth(end);
        scan
        System.out.printf("\nBetrachtet werden Zahlen von " + start + " bis " + end + ":\n\n");

        final int width = getMax(startWidth, endWidth);
        for (int i = start; i <= end; i++) {
            //TODO - DONE Ausgabe sollte nur für Zahlen erfolgen, die eine der Eigenschaften erfüllen
            //FIXME: jede Check-Methode nur einmal aufrufen, Ergebnisse in Variablen zwischenspeichern - DONE
            boolean isFroehlich = checkIsFroehlich(i);
            boolean isHarshad = checkIsHarshad(i);
            boolean isMersenne = checkIsMersenne(i);
            boolean isMunchhausen = checkIsMunchhausen(i);

            if (isFroehlich || isHarshad || isMunchhausen || isMersenne) {
                System.out.printf("%" + width + "d ist eine " +
                                          ((isFroehlich) ? "fröhlich" : "        ") + " " +
                                          ((isHarshad) ? "Harshad" : "       ") + " " +
                                          ((isMunchhausen) ? "Münchhausen" : "           ") + " " +
                                          ((isMersenne) ? "Mersenne" : "        ") +
                                          " Zahl" + " (0b" + toBinaryString(i) + ")" + " \n", i);
            }
        }

    }

    /**
     * //TODO - DONE Kommentar anpassen Zählt die Ziffern einer Zahl mithilfe von Modulo-Operationen
     * Ermittelt die Quersumme einer Zahl
     * @param inputZahl die Zahl dessen Quersumme ermittelt werden soll
     * @return Quersumme von inputZahl
     */

    private static int calcDigitSum(int inputZahl) {
        int quersumme = 0;

        // setzen der Zahl ins positive wenn negativ
        if (inputZahl < 0) {
            inputZahl *= -1;
        }
        // Mod-Operation für jede Ziffer zur ermittlung der quersumme
        while (inputZahl > 0) {
            int ziffer = inputZahl % 10;
            quersumme += ziffer;
            inputZahl /= 10;
        }
        return quersumme;
    }

    /**
     * Prüft, ob die Zahl eine Harshad ist. (Eine Harshad-Zahl ist eine natürliche Zahl, die durch ihre Quersumme - also
     * die Summe ihrer Ziffern - teilbar ist.)
     *
     * @param inputZahl zu prüfende Zahl;
     * @return Gibt aus ob, die Zahl eine Harshad Zahl ist oder nicht
     */

    private static boolean checkIsHarshad(int inputZahl) {
        //TODO - DONE calcDigitSum verwenden, in dieser Methode sollte es keine Schleife mehr geben und weniger Variablen
        //TODO - DONE Harshad-Zahlen sind nur natürliche Zahlen
        return inputZahl > 0 && (inputZahl % calcDigitSum(inputZahl) == 0);
    }

    /**
     * Prüft, ob eine Zahl fröhlich ist. (Eine fröhliche Zahl ist eine natürliche Zahl, die als Ausgangswert für eine
     * bestimmte Iterationsvorschrift nach endlich vielen Iterationsschritten zu dem Zahlenwert 1 führt. Die
     * Iterationsvorschrift sei hier das Aufsummieren der Quadrate der Ziffern.)
     *
     * @param inputZahl zu untersuchende Zahl
     * @return boolean ist fröhlich, oder nicht?
     */

    private static boolean checkIsFroehlich(int inputZahl) {
        int ergebnis = inputZahl;
        while (ergebnis > 0 && (ergebnis != 1 && ergebnis != 4)) {
            int sum = 0;
            while (ergebnis > 0) {
                int ziffer = ergebnis % 10;
                ergebnis /= 10;
                sum += ziffer * ziffer;
            }
            ergebnis = sum;
        }

        return ergebnis == 1;
    }

    /**
     * Prüft, ob eine Zahl eine Münchhausen-Zahl ist. (Eine Münchhausen-Zahl ist eine natürliche Zahl, bei der die Summe
     * ihrer einzelnen mit sich selbst potenzierten Ziffern wieder diese Zahl ergibt.)
     *
     * @param inputZahl - die zu prüfende Zahl
     * @return ergebnis (true oder false) der Berechnungen zur Feststellung der Münchhausen-Zahl
     */

    private static boolean checkIsMunchhausen(int inputZahl) {
        //TODO - DONE while-Schleife verwendet (wie in calcDigitSum nicht vorweg die Anzahl der Ziffern berechnen) und
        // keine Verzweigungen in der Schleife (kein if)
        //TODO - DONE Münchhausen-Zahlen sind nur natürliche Zahlen

        int cacheZahl     = inputZahl;
        int summe         = 0;

        while (inputZahl >= 0 && cacheZahl > 0) {
            summe += potBySelf(cacheZahl % 10);
            cacheZahl /= 10;
        }

        return summe == inputZahl;
    }


    /**
     * Prüft, ob eine Zahl eine Mersenne-Zahl ist (Mersenne-Zahl ist eine Zahl die Binär nur aus 1'n dargestellt wird)
     *
     * @param inputZahl - die zuprüfende Zahl
     * @return ergebnis (true oder false) der Berechnungen zur Feststellung der Mersenne-Zahl
     */
    private static boolean checkIsMersenne(int inputZahl) {
        //TODO - DONE es muss mit Bismaskierung und Bitverschiebung gearbeitet werden, Methoden aus String und Integer sind nicht erlaubt
        //TODO - DONE kein return in einer Schleife
        boolean isMersenne = true;

        if (inputZahl == 0) isMersenne = false;
        while (inputZahl != 0) {
            int rightMostBit = inputZahl & 0b1;
            inputZahl >>>= 1;
            if (rightMostBit == 0) isMersenne = false;
        }
        return isMersenne;
    }


    /**
     * Sucht nach der größten Zahl
     *
     * @param eineZahl   start Zahl
     * @param andereZahl end Zahl
     * @return gibt größe der größten Zahl aus
     */

    private static int getMax(int eineZahl, int andereZahl) {
        return eineZahl > andereZahl ? eineZahl : andereZahl;
    }

    /**
     * Berechnet Darstellungsbreite einer Zahl
     *
     * @param number ist die zu untersuchende Zahl
     * @return Darstellungsbreite der übergebenen Zahl
     */
    private static int calcDisplayWidth(int number) {
        int len = 1;

        if (number < 0) {
            len++;
            number *= -1;
        }

        while (number > 9) {
            len++;
            number /= 10;
        }
        return len;
    }

    /**
     * Potenziert den Eingabewert mit sich selbst, wenn der Wert 0 ist, dann wird nicht potenziert
     *
     * @param inputZahl - der zu potenzierende Wert
     * @return Ergebnis der Potenzierung
     */

    private static int potBySelf(int inputZahl) {
        int konstanteZahl = inputZahl;

        for (int i = 1; i < konstanteZahl; i++) {
            inputZahl *= konstanteZahl;
        }
        return inputZahl;
    }

    /**
     * Wandelt eine Zahl in Binärdarstellung um
     *
     * @param inputZahl - die zu binärisierende Zahl
     * @return die Zahl in Binärdarstellung
     */

    private static String toBinaryString(int inputZahl) {
        String resultString = "";

        if (inputZahl == 0) resultString = "0";

        while (inputZahl != 0) {
            int rightMostBit = inputZahl & 0b1;
            inputZahl >>>= 1;
            resultString =  rightMostBit + resultString;
        }


        return resultString;
    }
}