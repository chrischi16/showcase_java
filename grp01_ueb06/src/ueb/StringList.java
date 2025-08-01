package ueb;

import java.util.Arrays;
import java.util.Objects;

/**
 * Liste mit Strings als Nutzlast. Die Liste kann Duplikate enthalten. Möchte der Nutzer dieser Liste keine
 * doppelten Werte enthalten haben, muss er selbst dafür sorgen, dass gleiche Werte nicht mehrfach eingefügt werden.
 * Es muss nicht geprüft werden, ob als Nutzlast `null` eingefügt wird. Wenn durch eine Nutzlast `null` an
 * irgendeiner Stelle eine NullPointerException entsteht, ist das akzeptabel.
 *
 * @author ##### & Christoph
 */
public class StringList {

    /**
     * Werte der Liste
     */
    private String[] values;

    /**
     * Konstruktor für eine leere Liste
     */
    public StringList() {
        this.values = new String[0];
    }

    /**
     * Konstruktor, der eine Liste mit Strings erzeugt. Nutzt dafür die Methode prepend().
     * Primär zum Testen genutzt.
     *
     * @param values alle Werte, die der Liste zugefügt werden sollen
     */
    StringList(String... values) {
        this();
        for (int i = values.length - 1; i >= 0; i--) {
            this.prepend(values[i]);
        }
    }

    /**
     * Prüft, ob die Liste leer ist.
     *
     * @return true, wenn die Liste leer ist
     */
    public boolean isEmpty() {
        return (this.size() <= 0);
    }

    /**
     * Fügt das gegebene Element an den Start der Liste. Die Liste darf Duplikate enthalten.<br>
     * `null` kann zugefügt werden.
     *
     * @param elem zuzufügendes Element
     */
    public void prepend(String elem) {

        String[] modifiziertesStringArray = new String[this.values.length+1];
        System.arraycopy(this.values, 0, modifiziertesStringArray, 1, this.values.length);
        modifiziertesStringArray[0] = elem;
        this.values = modifiziertesStringArray;
    }

    /**
     * Die Länge der Liste.
     *
     * @return die Länge der Liste in integer.
     */
    public int size() {
        return values.length;
    }

    /**
     * Prüft, ob der Wert in der Liste vorkommt.
     *
     * @param payload der zu suchende Wert
     * @return true, wenn der Wert in der Liste ist
     */
    public boolean contains(String payload) {
        //TODO - DONE Strings über equals vergleichen, nicht Referenzen
        //FIXME - DONE hier ist die einzige Ausnahme für ein return aus einer Schleife, die sollte auch angewandt werden,
        // um die Schleife nicht unnötig lang laufen zu lassen
        for (String value : this.values) {
            if (Objects.equals(value, payload)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Fügt den Wert der Liste am Ende hinzu. Die Liste ist nicht sortiert und Duplikate dürfen vorkommen
     * (append(...) darf mehrmals mit dem gleichen Wert aufgerufen werden)
     */
    public void append(String payload) {
        String[] modifiziertesArray = new String[this.values.length + 1];
        System.arraycopy(this.values, 0, modifiziertesArray, 0, this.values.length);
        modifiziertesArray[modifiziertesArray.length - 1] = payload;
        this.values = modifiziertesArray;
    }

    /**
     * Gibt den Wert zurück, der am Anfang der Liste steht. Ist die Liste leer, wird null zurückgegeben
     *
     * @return Wert am Anfang der Liste
     */
    public String getPayloadAtHead() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.values[0];
            // values[0,1,2,..., n]
            // 0 müsste der Anfang sein
        }
    }

    /**
     * Gibt den Wert an der Stelle mit dem Index i zurück. Falls der Index ungültig ist, wird null zurückgegeben
     *
     * @param i Index des zu suchenden Wertes
     * @return Wert der an Stelle des Indexes steht
     */
    public String getAt(int i) {
        if (i < 0 || i >= this.values.length) {
            return null;
        } else
            return this.values[i];

        // -1 weil out of bounds
    }

    /**
     * Sucht den Wert in der Liste, gibt den Index zurück. Wenn der Wert in der Liste nicht vorkommt, wird null zurück-
     * gegeben. Da die Liste Duplikate enthalten darf. soll das erste Vorkommen (der minimale Index) zurückgegeben
     * werden. Die Elemente werden anhand ihres Inhaltes verglichen.
     *
     * @param payload Wert nach dem in der Liste gesucht werden soll
     * @return der Index des zu suchenden Wertes
     */
    public Integer getIndexOf(String payload) {
        //TODO - DONE bei Strings nicht Referenzen vergleichen
        //TODO - DONE soll bei 2 gleichen Strings den ersten Gefundenen zurückgeben
        //FIXME - DONE die Schleife soll abbrechen, wenn der korrekte Index gefunden ist, ohne ein return aus der Schleife zu nutzen
        if (this.contains(payload)) {
            int payloadIdx = -1;

            for (int i = 0; i < this.values.length && payloadIdx == -1; i++) {
                if (Objects.equals(this.values[i], payload)) {
                    payloadIdx = i;
                }
            }
            return payloadIdx;
        } else
            return null;
    }

    /**
     * Entfernt das erste Vorkommen des Wertes aus der Liste. Kommt der Wert in der Liste nicht vor,
     * bleibt sie unverändert.
     *
     * @param payload das zu löschende Element
     */
    public void remove(String payload) {

        Integer payloadIdx = this.getIndexOf(payload);
        if (payloadIdx == null)
            return;

        String[] modifiziertesArray = new String[this.values.length - 1];
        for (int i = 0; i < values.length - 1; i++) {
            //  überspringt den payload und kopiert nur alles vor und nach payload
            modifiziertesArray[i] = this.values[(i < payloadIdx) ? i : i + 1];
        }
        this.values = modifiziertesArray;
    }

    /**
     * Erstellt eine Kopie der aktuellen Liste. Hier darf der Test-Konstruktor verwendet werden.
     *
     * @return ein Duplikat der aktuellen Liste
     */
    public StringList copy() {
        return new StringList(this.values);
    }

    /**
     * Erzeugt eine Darstellung der Liste als ein einziger String. Nutzt StringBuilder.
     *
     * @return alle Werte der Liste, jeweils durch Komma getrennt, ohne ein Komma am Ende
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.values.length - 1; ++i) {
            sb.append(this.values[i]).append(", ");
        }
        if (!this.isEmpty()) {
            sb.append(this.values[this.values.length - 1]);
        }
        return sb.toString();
    }

    /**
     * Prüft, ob das gegebene Objekt eine Liste ist, die dieser gleicht. Zwei Listen gelten als gleich, wenn sie
     * die gleichen Werte in der gleichen Reihenfolge enthalten.
     *
     * @param obj Objekt, welches verglichen werden soll
     * @return true, wenn beides Listen mit gleichem Inhalt sind
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StringList) {
            StringList other = (StringList) obj;
            return Arrays.equals(this.values, other.values);
        } else {
            return false;
        }
    }
}
