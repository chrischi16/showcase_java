package ueb;

import java.util.Scanner;

/**
 * Kommunikation mit dem Nutzer, um die Ergebnisse verschiedener Prüfungen zu untersuchen.
 *
 * @author #####
 */
public class Main {

    /** maximale Länge eines Namens ist für die Ausgabe in Spalten zu verwenden */
    static final int LEN_NAME = 15;

    /**
     * Berechnet für eine Prüfung den Mittelwert und die Noten und gibt diese Information auf System.out aus.
     *
     * @param exam Prüfung, für die Ausgaben erfolgen sollen
     */
    public static void calcAndPrintExamResults(int[][] exam) {
        int mean = Analysis.calcMean(exam);
        System.out.printf("Der Mittelwert der Noten ist: %d%n", mean);
        int[][] examResult = Analysis.calcAllGrades(exam);
        for (int i = 1; i < examResult.length; i++) {
            System.out.printf("%d) %"+ -LEN_NAME +"s"+": %d\n", i, Data.getStudentName(examResult[i][0]), examResult[i][1]);
        }
    }

    /**
     * Lässt den Benutzer eine Prüfung auswählen. Es wird so lange nach einer PrüfungsID gefragt, bis eine gültige
     * eingegeben wurde.
     *
     * @return die ID der ausgewählten Prüfung
     */
    public static int selectExam() {
        Scanner sc = new Scanner(System.in);
        int nutzerInput = -1;

        while (!Data.isValidExamResult(nutzerInput)) {
            System.out.print("Die Ergebnisse welcher Prüfung möchten Sie sehen? ");
            nutzerInput = sc.nextInt();
        }

        return nutzerInput;
    }

    /**
     * Fragt den Nutzer, ob das Prüfungsergebnis geschönt werden soll.
     *
     * @return true, wenn der Nutzer das Ergebnis geschönt haben möchte
     */
    public static boolean checkImproveMean() {
        System.out.print("Sollen die Noten durch Entfernen einer Aufgabenbewertung angehoben werden? (0 für Nein, jede andere Zahl für Ja): ");
        Scanner scanner = new Scanner(System.in);
        int delete = scanner.nextInt();
        return (delete != 0);
    }

    /**
     * Fragt den Nutzer nach einer Prüfung, gibt deren Ergebnisse aus und fragt, ob die Ergebnisse aufgebessert werden sollen.
     * Es werden Mittelwert und Noten der Prüfung ausgegeben.
     *
     * @param args ungenutzt
     */
    public static void main(String[] args) {
        int selectedExam = selectExam();
        calcAndPrintExamResults(Data.getExamResult(selectedExam));
        if (checkImproveMean()) {
            int[][] improvedExam = Analysis.improveMean(Data.getExamResult(selectedExam));
            calcAndPrintExamResults(improvedExam);
        }
    }
}
