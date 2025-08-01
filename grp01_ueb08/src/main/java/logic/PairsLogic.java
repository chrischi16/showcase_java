package logic;

/**
 * Logik des Spiels "Memory". In diesem Spiel spielen zwei Spieler gegeneinander
 * auf einem 4x3 Spielfeld. Die Spieler wählen abwechselnd zwei Karten aus, von
 * denen sie die Symbole sehen wollen. Stimmen die Symbole überein, hat der
 * jeweilige Spieler ein Paar gefunden. Ziel des Spiels ist es mehr Paare als der
 * Gegenspieler zu finden.
 *
 * @author #####, co
 */
public class PairsLogic {

    /**
     * Namen der Spieler in einem Array. Länge muss 2 sein.
     */
    private String[] players;

    /**
     * Index des aktuellen Spielers. Muss entweder 0 oder 1 sein. Startet immer mit 0.
     * (Streng genommen ist die Initialisierung nicht notwendig, wir machen es aber
     * trotzdem).
     */
    private int idxCurrPlayer = 0;

    /**
     * Verbindung zur GUI.
     */
    private final GUIConnector gui;

    /**
     * Aufzählungsdatentyp für die Symbole der Karten. Mehr Symbole als notwendig wären
     * für ein Spielfeld der Größe 4x3, um das Spiel etwas interessanter zu gestalten.
     */
    public enum Symbol {
        // TODO - DONE: Die Logik darf keine Informationen darüber entahlten wie die Symbole tatsächlich darsgestellt werden
        //       sollen. Hier keine weiteren Attribute haben und to.String nicht überschreiben.
        BEE, HONEY, BEAR, PIG,
        CAKE, OWL, GHOST, BOOK, BAT,
        POO, DOG, BUG;

    }

    /**
     * Das 2-dimensionale Spielfeld mit Karten.
     */
    private Symbol[][] cards;

    /**
     * Aufzählung um zu vermerken, wer ein Paar gefunden (gelöst) hat. Entweder war es
     * der erste Spieler, der zweite oder das Paar ist noch nicht ggefunden worden.
     * Der Ordinalwert des Enumwertes für einen Spieler korrespondiert mit dem Index
     * des Spielers im Spielerarray.
     */
    enum Solved {
        FST, SND, NOT
    }

    /**
     * Um sich zu merken, an welchen Positionen bereits Paare gefunden worden sind.
     * Wichtig, sobald alle Paare gefunden worden sind.
     */
    private Solved[][] cardsSolved;

    /**
     * Die Positionen der Karten, die gerade aufgedeckt worden sind. Dieses Array soll
     * immer die Länge zwei haben (da wir die Positionen für zwei Karten speichern wollen)!
     * Sind die beiden enthaltenen Positionen null, dann hat das Spiel gerdae anfangen oder
     * ein Spieler hat eben ein Paar gefunden. Ist nur die erste Position im Array belegt,
     * hat der aktuelle Spieler bisher nur seine erste Karte ausgewählt und es muss darauf
     * gewartet werden, dass er/sie eine zweite Karte auswählt. Sind zwei Positionen
     * gespeichert, dann hatte der vorherige Spieler zwei Karten ausgewählt, die kein Paar
     * waren.
     */
    private final Position[] posOpenCards = new Position[2];

    /**
     * Konstruktor für ein Memoryspiel. Initialisiert das Feld.
     *
     * @param p1     Name des ersten Spielers
     * @param p2     Name des zweiten Spielers
     * @param width  Breite des Spielfeldes
     * @param height Höhe des Spielfeldes
     * @param gui    Verbindung zur GUI
     */
    public PairsLogic(String p1, String p2, int width, int height, GUIConnector gui) {
        if (width < 1 || height < 1 || width * height % 2 != 0)
            throw new AssertionError("Ein " + width + "*" + height + " Spielfeld kann nicht sinnvoll erzeugt werden.");
        this.players = new String[]{p1, p2};
        this.cards = createRandomBoard(width, height);
        this.cardsSolved = noSolvedCards(width, height);
        this.gui = gui;
        setUp();
        this.gui.setCurrentPlayer(p1);
    }

    // TODO - DONE: Alle folgenden JavaDoc-Kommentare vervollständigen.

    /**
     * Gibt ein Solved Array mit werten von Solved.NOT zurück, das einer gewünschten größe Entspricht
     *
     * @param width  die Breite des Spielfeldes
     * @param height die Höhe des Spielfeldes
     * @return Ein Solved.NOT array
     */
    private Solved[][] noSolvedCards(int width, int height) {
        Solved[][] newSolved = new Solved[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newSolved[i][j] = Solved.NOT;
            }
        }
        return newSolved;
    }

    /**
     * package-private Konstruktor zum Testen der ein Array mit den Spielernamen, ein Array mit den Kartensymbolen,
     * ein Array mit der Information, welche Karte bereits "gelöst" wurde und eine Verbindung zur GUI
     * (s.u. betreffs Testen) erhält
     *
     * @param players     - Die aktiven Spieler
     * @param symbols     - Die für das Spielfeld genutzte Symbolik
     * @param cardsSolved - Das Array zum merken, der aufgedeckten Karten
     * @param gui         - die Verbindung zur GUI
     */
    PairsLogic(String[] players, Symbol[][] symbols, Solved[][] cardsSolved, GUIConnector gui) {
        this.players = players;
        this.cards = symbols;
        this.cardsSolved = cardsSolved;
        this.gui = gui;
        setUp();
    }

    /**
     * erstellt ein neues 2D-Array und füllt es mit zufälligen Paaren von Symbolen
     * an zufälligen Stellen und gibt dieses zurück.
     *
     * @param width  Breite des Spielfeldes
     * @param height Höhe des Spielfeldes
     * @return ein neues Array mit gemischten Karten
     */
    private Symbol[][] createRandomBoard(int width, int height) {
        Symbol[][] newCards = new Symbol[width][height];
        int count = 0;
        Symbol sym = getRandomCard();
        // Spielfeld durchgehen
        for (int i = 0; i < newCards.length; i++) {
            for (int j = 0; j < newCards[newCards.length - 1].length; j++) {
                if (count < 2) {
                    newCards[i][j] = sym;
                    count++;
                } else {
                    count = 1;
                    while (isSaturated(newCards, sym)) {
                        sym = getRandomCard();
                    }
                    newCards[i][j] = sym;
                }
            }
        }
        return shuffleBoard(newCards);
    }

    /**
     * mischt das übergebene 2D-Array durch, sodass die Symbole zufällig verteilt sind.
     * Darf die Reihenfolge der Symbole im übergebene Array verändern
     *
     * @param board das betroffene Spielfeld
     * @return das Array mit gemischten Karten
     */
    private Symbol[][] shuffleBoard(Symbol[][] board) {
        // Board durchgehen

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[board.length - 1].length; j++) {
                int r = (int) ((Math.random()) * (board.length * board[0].length));

                int x = 0, y = 0;
                while (r > 0) {
                    if (r >= board[0].length) {
                        x++;
                        r -= board[0].length;
                    } else {
                        y = r;
                        r = 0;
                    }
                }
                Symbol sym = board[i][j];
                board[i][j] = board[x][y];
                board[x][y] = sym;
            }
        }
        return board;
    }

    /**
     * Prüft obj das Symbol schon gleich oder mehr als 2 Mal vorkommt im Array
     *
     * @param board 2 Dimensionales Array in dem geprüft wird
     * @param sym   Symbol das Geprüft wird
     * @return true, wenn mehr oder gleich 2 exemplare vorhanden sind
     */
    private boolean isSaturated(Symbol[][] board, Symbol sym) {
        int count = 0;
        for (Symbol[] symbols : board) {
            for (Symbol s : symbols) {
                if (sym.equals(s)) count++;
            }
        }
        return count >= 2;
    }


    /**
     * Holt sich eine zufällige Karte für Memory und gibt diese zurück
     *
     * @return eine zufällige Karte mit zufälligem Symbol
     */

    private Symbol getRandomCard() {
        Symbol[] symbols = Symbol.values();
        // Random Nummer ermitteln und Übersetzen auf Enum um Karte zu erhalten
        int randomNumber = (int) (Math.random() * symbols.length); // +1 damit auch 12 erreicht werden kann
        return symbols[randomNumber];
    }

    /**
     * liefert true, wenn alle Paare aufgedeckt wurden
     *
     * @return ob, alle Karten aufgedeckt wurden
     */
    protected boolean allSolved() {
        // Annahme zuerst Boolean ist True
        boolean allCardsUncovered = true;
        for (int i = 0; i < this.cardsSolved.length; i++) {
            for (int j = 0; j < this.cardsSolved[this.cardsSolved.length - 1].length; j++) {
                if (cardsSolved[i][j] == Solved.NOT) {
                    allCardsUncovered = false; // Durchchecken >>> Wenn irgendwas nicht solved dann direkt falsch!
                }
            }
        }
        return allCardsUncovered;
    }

    /**
     * liefert den Namen des Gewinners. Bei "Unentschieden" wird null zurückgegeben.
     * Brechen Sie das Programm mit einem AssertionError ab, falls noch nicht alle Karten aufgedeckt wurden.
     *
     * @return den Namen des Gewinners
     * @throws AssertionError wenn aufgerufen aber nicht alle Karten aufgedeckt
     */
    protected String getWinnerName() {
        // early exit
        if (!allSolved()) {
            throw new AssertionError("Fehler - Es wurden noch nicht alle Karten aufgedeckt.");
        }
        // Memory wurde gelöst
        else {
            return checkWinner(this.cardsSolved);
        }
    }

    /**
     * Zählt alle aufgedeckten Paare und überprüft + gibt, aus wer der Gewinner ist
     *
     * @param cardsSolved die aktuell aufgedeckten Karten
     * @return der Gewinner
     */
    private String checkWinner(Solved[][] cardsSolved) {
        int pairsPlayer1 = 0, pairsPlayer2 = 0;
        for (int i = 0; i < this.cardsSolved.length; i++) {
            for (int j = 0; j < this.cardsSolved[this.cardsSolved.length - 1].length; j++) {
                if (this.cardsSolved[i][j] == Solved.FST) {
                    pairsPlayer1++;
                } else if (this.cardsSolved[i][j] == Solved.SND) {
                    pairsPlayer2++;
                }
            }
        }
        // Jede Karte des jeweiligen Spieles wird gezählt.
        // Durch 2 Teilen um die Anzahl an Paaren zu erhalten.
        pairsPlayer1 /= 2;
        pairsPlayer2 /= 2;

        if (pairsPlayer1 > pairsPlayer2) {
            return getPlayerName(0);
        } else if (pairsPlayer2 > pairsPlayer1) {
            return getPlayerName(1);
        } else return null;
    }

    /**
     * Gibt den Spieler namen zurück
     *
     * @param i der Index des Spielerarrays
     * @return der Index übersetzt in den zugehörigen Namen
     */
    private String getPlayerName(int i) {
        return this.players[i];
    }

    /**
     * liefert den Namen des aktuellen Spielers. <b>Außerhalb dieser Klasse</b> sollte diese Methode ausschließlich zum
     * Testen verwendet werden.
     *
     * @return der Name des aktuellen Spielers
     */
    String getNameCurrentPlayer() {
        return this.players[idxCurrPlayer];
    }

    /**
     * liefert eine Stringrepräsentation des Spielfeldes.
     * Jeder Eintrag wird in der Breite 5 ausgegeben, die entstehenden Spalten jeweils durch ein Leerzeichen
     * getrennt, Zeilen durch einen Zeilenumbruch (\n). Nutzen Sie hier gerne String.format(). Eine Beispielausgabe
     * können Sie den gegebenen Tests entnehmen.
     * <p>
     * <b>Diese Methode soll ausschließlich zum Testen/Debuggen genutzt werden.</b>
     *
     * @return Alle Karten als String
     */
    String cardsToString() {
        StringBuilder outputString = new StringBuilder();
        for (int j = 0; j < this.cards[0].length; j++) {
            for (int i = 0; i < this.cards.length; i++) {
                // -5s, heißt links formatiert und das eingegebene Objekt/String/Textdings ist 5 breit.
                outputString.append(String.format("%-5s", this.cards[i][j].name())).append(" ");
            }
            outputString.append("\n");
        }
        return outputString.toString();
    }

    /**
     * Zuständig für die Verarbeitung der Katenauswahl
     *
     * @param pos die Position für die ausgewählte Karte
     * @return Der Zustand der angesprochenen Karte
     */
    private void processCardSelection(Position pos) {
        // Start/ Zugstart

        if (cardsSolved[pos.x()][pos.y()] != Solved.NOT){
            return;
        }

        if (posOpenCards[0] != null && posOpenCards[1] != null){
            toggleCards(false, posOpenCards);
            clearPosOpenCards();
        }
        // TODO: - DONE Ab hier nur noch ein return nutzt else if oder etwas vergleichbares.
        if (this.posOpenCards[0] == null && this.posOpenCards[1] == null) {
            // posOpenCard 1 auf voll
            this.posOpenCards[0] = pos;
            toggleCards(true, posOpenCards[0]);
        }
        // 1. Karte aufgedeckt
        else if (this.posOpenCards[0] != null && this.posOpenCards[1] == null && !this.posOpenCards[0].equals(pos)) {
            this.posOpenCards[1] = pos;
            toggleCards(true, posOpenCards[1]);
        }



        // 2. aufgedeckt
        if (this.posOpenCards[0] != null && this.posOpenCards[1] != null) {
            togglePlayers();
            if (setPair(idxCurrPlayer, this.posOpenCards[0], this.posOpenCards[1])){
                clearPosOpenCards();
            }
        }
    }

    /**
     * Setzt die Gespeicherten Pos der geöffneten Karten auf null
     */
    private void clearPosOpenCards(){
        this.posOpenCards[0] = null;
        this.posOpenCards[1] = null;
    }


    /**
     *
     * @param show zeigen = true, hide = false
     * @param pos position(en) die gedreht werden sollen
     */
    private void toggleCards(boolean show, Position... pos){
        for (Position p : pos){
            if (p != null){
                if (show){
                    gui.showCard(p, cards[p.x()][p.y()]);
                } else {
                    gui.hideCard(p);
                }
            }
        }
    }

    /**
     * Wechselt den Text der Karten zu ihren Aufgedeckten oder Verdeckten Typen, je nach zustand während des Setups.
     * Resetted das Spielfeld wenn mit keinen vorgebenen SolvedCards die PairsLogic initialisiert wurde.
     */
    private void setUp() {
        boolean[][] solved = new boolean[cardsSolved.length][cardsSolved[0].length];
        for (int i = 0; i < solved.length; i++) {
            for (int j = 0; j < solved[0].length; j++) {
                solved[i][j] = cardsSolved[i][j] != Solved.NOT;
            }
        }

        for (int i = 0; i < solved.length; i++) {
            for (int j = 0; j < solved[0].length; j++) {
                toggleCards(solved[i][j], new Position(i, j));
            }
        }

    }


    /**
     * Wechselt den Aktiven spieler
     */
    private void togglePlayers() {
        if (idxCurrPlayer == 0) {
            idxCurrPlayer = 1;
        } else {
            idxCurrPlayer = 0;
        }

        gui.setCurrentPlayer(players[idxCurrPlayer]);
    }

    /**
     * Setzt die aufgedeckten Karten (Wenn Sie ein Paar sind) auf den aktuellen Spieler und auf aufgedeckt
     *
     * @param pos1 Koordinaten für die erste Karte
     * @param pos2 Koordinaten für die zweite Karte
     * @return Ob, die angesprochenen Karten ein Paar sind oder nicht
     */
    private boolean setPair(int currentPlayer, Position pos1, Position pos2) {
        if (isPair(pos1, pos2) && currentPlayer == 0) {
            this.cardsSolved[pos1.x()][pos1.y()] = Solved.FST;
            this.cardsSolved[pos2.x()][pos2.y()] = Solved.FST;
        } else if (isPair(pos1, pos2) && currentPlayer == 1) {
            this.cardsSolved[pos1.x()][pos1.y()] = Solved.SND;
            this.cardsSolved[pos2.x()][pos2.y()] = Solved.SND;
        } else {
            return false;
        }
        return true;
    }

    /**
     * Überprüft 2 Karten auf Gleichheit
     *
     * @param pos1 Koordinaten für die erste Karte
     * @param pos2 Koordinaten für die zweite Karte
     * @return ob, die beiden Karten gleich sind (1 Paar)
     */
    private boolean isPair(Position pos1, Position pos2) {
        return this.cards[pos1.x()][pos1.y()] == this.cards[pos2.x()][pos2.y()];
    }

    /**
     * Kümmert sich um den Zug eines Spielers. Ist die ausgewählte Zelle voll,
     * passiert nichts (der Spieler kann eine andere Zelle auswählen).
     * <p>
     * War die Zelle leer und es war die erste Karte, die der Spieler ausgewählt hat, so
     * passiert nicht viel (außer, dass sich die Informationen gemerkt werden).
     * <p>
     * War die Zelle leer und der Spieler hat seine zweite Karte gewählt, so ist der Zug
     * des Spielers beendet und der nächste Spieler ist am Zug.
     * <p>
     * Wurde ein Paar gefunden, muss überprüft werden, ob jemand das Spiel gewonnen hat. Im
     * Gegensatz zu den üblichen Memoryregeln ist jemand, der ein Paar gefunden hat
     * nicht direkt wieder an der Reihe.
     *
     * @param pos Koordinate der Zelle, die der aktuelle Spieler aufdecken möchte
     */
    public void playerTurn(Position pos) {

        if (pos == null)
            throw new AssertionError("Übergebene Position darf nicht null sein.");

        processCardSelection(pos);
        if (allSolved()) {
            gui.onGameEnd(getWinnerName());
        }
    }

}
