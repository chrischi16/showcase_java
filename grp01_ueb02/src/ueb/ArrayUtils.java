package ueb;


/**
 * Gruppiert diverse nützliche Methoden für Arrays zusammen. Einige Methoden bearbeiten Paare (= zweidimensionale Arrays, 
 * in denen die zweite Dimension immer die Länge 2 hat). Wird in einer Methodenbeschreibung angegeben, dass Paare 
 * als Parameter erwartet werden, darf davon ausgegangen werden, dass die korrekte Datenstruktur übergeben wird. 
 * Keine dieser Methoden darf die Inhalte der als Parameter übergebenen Arrays verändern!
 *
 * @author #####
 */
public class ArrayUtils {

    /**
	 * Summiert alle Werte des Arrays.
     *
     * @param arr Array, dessen Werte aufsummiert werden sollen. 
     * @return Summe aller Werte des Arrays
     */
    public static int sumUp(int[] arr) {
        int summe = 0;

        for (int j : arr) {
            summe += j;
        }
        return summe;
    }

    /**
	 * Entfernt am gegebenen Index den Wert aus einem Array.
     *
     * @param idx index, an dem der Wert entfernt werden soll. Ist der Index invalide, wird eine 
	 *        IllegalArgumentException ausgelöst
     * @param arr Array, aus dem ein Wert entfernt werden soll
     * @return neues Array, das den Wert am gegebenen Index nicht mehr enthält 
     */
    public static int[] removeValueAt(int idx, int[] arr) {
        // exception Filterung
        String exception = null;

//        // Exception for leeres exam Array (arr)
//        if (arr == null || arr.length == 0){
//            exception = "Array can't be null or of length 0";
//        }

        // exception für inkorrekten idx
        if (exception == null && (idx <0 || idx >= arr.length)){
            exception = "Fehler: Ihre Eingabe liegt außerhalb des Arrays";
        }


        // wenn eine Exception gefunden wurde, wird sie ausgeworfen
        if (exception != null){
            throw new IllegalArgumentException(exception);
        }

        int[] newArray = new int[arr.length-1]; // Klonen des Originalarrays für non-destruktive Verarbeitung


        // Eingegebenen Index löschen > auf 0 setzen > kein Wert
//        if (arr.length == 1)
//            newArray = new int[]{};
//        else {
            for (int i = 0; i <  newArray.length; i++) {
                 newArray[i] = arr[(i < idx) ? i : i +1];
            }
//        }
        return  newArray;
    }

    /**
	 * Erzeugt eine tiefe Kopie eines zweidimensionalen Arrays.
     *
     * @param arr das gegebene Array
     * @return tiefe Kopie des gegebenen Arrays
     */
    public static int[][] deepCopyTwoDim(int[][] arr) {
        // Exception for leeres exam Array (arr)
        if (arr.length == 0) return new int[][]{};

        int[][] arr2 = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr2.length; i++){
            arr2[i] = arr[i].clone();
        }
        return arr2;
    }

    /**
	 * Bestimmt den Index des Paares mit dem größten Wert.
     *
	 * @param pairs mehrere Paare in einem Array; am ersten Index steht der Schlüssel, 
	 *              am zweiten der Wert des Paares.
	 *              Da wir nur am größten Wert interessiert sind, ist der jeweilige Schlüssel 
	 *              nicht von Interesse.
     * @return Index des Paares, das den größten Wert enthält, oder -1, wenn das Array leer ist
     */
    public static int getIdxOfMaxValue(int[][] pairs) {

        int idx = -1;

//        // Leere Arrays sollten keine Summe haben
//        if ( pairs.length == 0){
//            return -1;
//        } else {
//            for (int i = 0; i < pairs.length; i++){
//                if (pairs[i].length == 0) return -1;
//            }
//        }

        // ermitle den Index mit dem höchsten array[1] Wert
        int maxVal = 0;
        for (int i = 0; i < pairs.length; i++){
            if (maxVal < pairs[i][1]) {
                idx = i;
                maxVal = pairs[idx][1];
            }
        }

        return idx;
    }



    /**
	 * Sortiert Schlüssel-Wert-Paare nach dem Wert, indem jeweils der größte Wert mit seinem Schlüssel in ein neues
     * Array geschrieben wird. Die Methode getIdsOfMaxValue() soll hier verwendet werden. <br>
     * Wenn benötigt, kann hier Integer.MIN_VALUE eingesetzt werden.
     *
     * @param pairs mehrere Paare in einem Array (eine Spalte = ein Paar); am ersten Index steht der Schlüssel,
	 *              am zweiten der Wert 
     * @return sortiertes Array von Paaren
     */
    public static int[][] sortPairs(int[][] pairs) {
//        // Exception Filterung
//        if (pairs == null || pairs.length == 0){
//           return pairs;
//        } else {
//            for (int i = 0; i < pairs.length; i++){
//                if (pairs[i] == null || pairs[i].length != 2){
//                    return pairs;
//                }
//            }
//        }

        int[][] result = new int[pairs.length][pairs[0].length];
        int[][] currPairArray = deepCopyTwoDim(pairs);
        for (int i = 0; i < pairs.length; i++){
            int indexMax = currPairArray.length != 0 ? getIdxOfMaxValue(currPairArray) : 0;
//            currPairArray[indexMax][1] = Integer.MIN_VALUE;
            int[][] reducedArray = new int[currPairArray.length-1][pairs[i].length];
            for (int k = 0; k < reducedArray.length; k++){
                reducedArray[k] = currPairArray[(k < indexMax) ? k : k+1];
            }
            result[i] = currPairArray[indexMax];
            currPairArray = reducedArray;
        }

        return result;
    }

}
