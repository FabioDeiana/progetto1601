package fabio.exceptions;

public class GiocoGiaEsistenteException extends Exception {
    public GiocoGiaEsistenteException(String id) {
        super("Il gioco con ID " + id + " è già presente nella collezione!");
    }
}
