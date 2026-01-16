package fabio.exceptions;

public class GiocoNonTrovatoException extends Exception {
    public GiocoNonTrovatoException(String id) {
        super("Il gioco con ID " + id + " non è stato trovato nella collezione!");
    }
}
