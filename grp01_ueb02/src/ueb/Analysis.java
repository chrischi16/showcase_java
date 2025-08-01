package ueb;


/**
 * Klasse für Methoden zum Auswerten eines Prüfungsergebnisses. Alle Methoden, die eine Prüfung als Parameter erhalten, 
 * dürfen diesen nicht verändern und keine Referenzen auf die Originalergebnisse zurückgeben. Es darf stets davon
 * ausgegangen werden, dass die übergebene Prüfung valide Daten enthält. Es ist also nicht notwendig, auf beispielsweise
 * invalide StudentenIDs zu prüfen.
 *
 * @author #####
 */
public class Analysis {

    /**
     * Berechnet die Note (= Prozentwert, zu dem die Prüfung korrekt gelöst wurde) aus den erhaltenen Punkten und den
     * maximal verfügbaren Punkten für diese Aufgaben. Es ist nicht erlaubt, Methoden aus der Klasse Math zu nutzen!
     *
     * @param points    Punkte, die erreicht wurden
     * @param maxPoints maximal zu erreichende Punkte
     * @return Note als ein Wert zwischen 0 und 100 (inklusive). Der Wert wird kaufmännisch gerundet, d.h. ist die erste
     * Nachkommastelle >= 5 wird auf-, andernfalls abgerundet (z.B. 88,8 = 89 und 71,1 = 71).
     */
    public static int calcGrade(int points, int maxPoints) {
//        if (maxPoints == 0 || maxPoints < points){
//            throw new IllegalArgumentException("maxPoints cannot be 0 or smaller then points");
//        }


        float calcGrade;
        calcGrade = ((float) points / maxPoints) * 100; // o
<<<<<<< .mine
        int calcGradeInt = (int) calcGrade;
        float decimal = calcGrade - calcGradeInt;

        if (decimal >= 0.5) {
            float differenz = 1 - decimal;
            calcGrade += differenz;
            return (int) calcGrade;
        }
        else {
        calcGrade -= decimal;
        return (int) calcGrade;
        }
||||||| .r46
        int calcGradeInt = (int) calcGrade;
        float decimal = calcGrade - calcGradeInt;

        if (calcGrade >= 0.5) {
            float differenz = 1 - decimal;
            calcGrade += differenz;
            return (int) calcGrade;
        }
        else return calcGradeInt;
=======
        return (int) (calcGrade + 0.5f);
//        int calcGradeInt = (int) calcGrade;
//        float decimal = calcGrade - calcGradeInt;
//
//        if (calcGrade >= 0.5) {
//            float differenz = 1 - decimal;
//            calcGrade += differenz;
//            return (int) calcGrade;
//        }
//        else return calcGradeInt;
>>>>>>> .r49
    }

    /**
	 * Berechnet die Noten aller Teilnehmer einer Prüfung und sortiert sie (Teilnehmer 
	 * mit der besten Note zuerst). Haben zwei Teilnehmer die gleiche Note, wird der 
	 * Teilnehmer, dessen Ergebnisse in den Original-Prüfungsdaten als Erstes stehen,
	 * auch hier als Erstes auftauchen.
     *
     * @param exam die Ergebnisse einer Prüfung: die zu erreichenden Maximalpunkte und für jeden Studenten die
     *             erreichten Punkte pro Aufgabe
     * @return die nach Noten absteigend sortierten Ergebnisse für alle Studenten in einem Array 
	 *         von Paaren {student id, Note}. Die Länge wird um eins kleiner sein als die des gegebenen Prüfungsarrays, 
	 *         da die Maximalpunkte nicht länger von Interesse sind.
     */
    public static int[][] calcAllGrades(int[][] exam) {

        // checks ob exam correkt, also nicht leer ist
//        checkForException(exam);


        int[][] examClone = ArrayUtils.deepCopyTwoDim(exam);
        int[] average = new int[exam.length-1];

        for (int i = 1; i < examClone.length; i++){
            for (int k = 1; k < examClone[0].length; k++){
                average[i-1] += examClone[i][k];
            }
        }

        for (int i = 0; i < average.length; i++){
            average[i] /= calcGrade(average[i], examClone[i][1]);
        }

        int[][] tmp = new int[examClone.length-1][2];

        for (int ec = 1; ec < examClone.length; ec++){
            tmp[ec-1] = new int[]{examClone[ec][0], average[ec-1]};
        }

        tmp = ArrayUtils.sortPairs(tmp);
        int[][] result = new int[examClone.length][2];
        result[0] = new int[]{examClone[0][0], calcAverage(examClone[0])};
        System.arraycopy(tmp, 0, result, 1, tmp.length);
        return result;
    }


    /**
     * Berechnet den Durchschnitt eines Eindimensionalen Array's, ignoriert den ersten index (0)
     * @param arr das übergebene Array dessen Durchschnitt errechnet werden soll
     * @return den Durchschnitt des Arrays das übergeben wurde
     */
    private static int calcAverage(int[] arr){

        int result = 0;
        for (int i = 1; i < arr.length; i++){
            result += arr[i];
        }
        return result / arr.length;
    }


    /**
     * Prüft ob die Prüfung die übergeben wurde nutzbar ist, oder eine exception werfen muss
     * @param exam die übergebene zu prüfende Prüfung
     * @throws IllegalArgumentException wenn inkorrekt, wirft exception
     */
    private static void checkForException(int[][] exam){
        if (exam == null || exam.length == 0){
            throw new IllegalArgumentException("exam cannot be null or have a length of 0");
        } else {
            for (int i = 0; i < exam.length; i++){
                if (exam[i] == null || exam[i].length == 0){
                    throw new IllegalArgumentException("sub array of exam cannot be null or have a length of 0");
                }
            }
        }
    }


    /**
	 * Berechnet den Mittelwert der Noten für die gegebene Prüfung. Der erhaltene Mittelwert wird immer abgerundet. 
	 * calcAllGrades() kann hier verwendet werden.
     *
     * @param exam Ergebnisse einer Prüfung
     * @return Mittelwert aller Noten dieser Prüfung. Hat kein Student teilgenommen, kann kein Mittelwert berechnet
     * werden und es wird -1 zurückgegeben. Der Mittelwert ist immer ein abgerundeter Wert.
     */
    public static int calcMean(int[][] exam) {
<<<<<<< .mine
        boolean noStudent = false;
        int counterStudents = 0;
        
        for (int i = 1; i < exam[0].length; i++) {
            if (exam[i][0] >= 0) {
                counterStudents++;
||||||| .r46
        // checks ob exam correkt, also nicht leer ist
        checkForException(exam);

        // Berechnet alle Durchschnitte per prüfungen
        int[] temp = new int[exam[0].length-1];
        for (int i = 0; i < exam.length; i++){
            for (int k = 0; k < exam[i].length-1; k++){
                temp[k] += exam[i][k+1];
=======
        // checks ob exam correkt, also nicht leer ist
//        checkForException(exam);

        // Berechnet alle Durchschnitte per prüfungen
        int[] temp = new int[exam[0].length-1];
        for (int i = 0; i < exam.length; i++){
            for (int k = 0; k < exam[i].length-1; k++){
                temp[k] += exam[i][k+1];
>>>>>>> .r49
            }
            
        }

        if (counterStudents == 0) {
            return -1;
        }
        else {
            for (int i = 1; i < exam[0].length; i++) {
                
            }
        }


    }

    /**
	 * Entfernt die Ergebnisse einer Aufgabe aus den Prüfungsergebnissen. Bestand eine Prüfung vormals aus drei 
     * Aufgaben, werden die Ergebnisse aus nur zwei Aufgaben zurückgegeben, sofern die Parameter valide sind. Ist ein
     * Parameter nicht valide, wird eine IllegalArgumentException ausgelöst. Besteht die Prüfung nur aus einer Aufgabe,
     * macht es keinen Sinn, diese zu entfernen und der Parameter wird als invalide angesehen.
     *
     * @param exam    die Prüfung, deren modifizierte Ergebnisse zurückgegeben werden sollen. Die Prüfung selbst darf
     *                nicht verändert werden.
     * @param idxPart der Teil der Prüfung, der entfernt werden soll. Der kleinste idxPart ist 1, da in 0 die id steht
     * @return die neue, modifizierte Prüfung, die einen Prüfungsteil (Aufgabe) nicht mehr enthält 
     */
    public static int[][] eliminateExamPart(int[][] exam, int idxPart) {
        // checks ob exam correkt, also nicht leer ist
//        checkForException(exam);

        if (idxPart < 0 || idxPart >= exam[0].length){
            throw new IllegalArgumentException("idxPart cannot be smaller then 0 or bigger then exam[0].length");
        }

        int[][] result = new int[exam.length][exam[0].length-1];

        for (int i = 0; i < result.length; i++){
            for (int k = 0; k < result[i].length; k++){
                result[i][k] = exam[i][(k < idxPart)? k : k+1];
            }
        }

        return result;

    }

    /**
	 * Verbessert den Durchschnitt einer Prüfung durch Entfernen einer Aufgabe. Ergibt das Entfernen irgendeiner Aufgabe 
	 * keinerlei Verbesserung des Mittelwertes, wird eine unveränderte Prüfung zurückgegeben.
     *
     * @param exam die Prüfung, deren Mittelwert verbessert werden soll
     * @return modifizierte neue Prüfung
     */
    public static int[][] improveMean(int[][] exam) {
        // checks ob exam correkt, also nicht leer ist
//        checkForException(exam);

        int[][] examClone = ArrayUtils.deepCopyTwoDim(exam);

        // initialisiert das durchschnitts Array pro prüfung
        int[][][] averagesPerExam = new int[examClone[0].length-1][examClone.length][examClone[0].length-1];

        for (int pruefung = 1; pruefung < examClone[0].length; pruefung++){
            int[][] tempExam = new int[examClone.length][examClone[0].length-1];
            for (int i = 0; i < examClone.length; i++){
                tempExam[i][0] = examClone[i][0];
                for (int p = 1; p < examClone[0].length-1; p++){
                    tempExam[i][p] = examClone[i][(p < pruefung) ? p : p+1];
                }
            }
            averagesPerExam[pruefung-1] = tempExam;
        }


        int best = 0;
        for (int i = 0; i < averagesPerExam.length; i++){
            if (calcMean(averagesPerExam[i]) > calcMean(averagesPerExam[best])) best = i;
        }
        int[][] result = averagesPerExam[best];

        return (calcMean(result) > calcMean(exam)) ? result : exam;

    }


}
