package fabio.entities;

public class GiocoDaTavolo extends Gioco {
    private int numeroGiocatori;
    private int durataMediaPartita;

    public GiocoDaTavolo(String id, String titolo, int annoPubblicazione, double prezzo, int numeroGiocatori, int durataMediaPartita) {
        super(id, titolo, annoPubblicazione, prezzo);
        // Controllo numero giocatori tra 2 e 10
        if (numeroGiocatori < 2 || numeroGiocatori > 10) {
            throw new IllegalArgumentException("Il numero di giocatori deve essere tra 2 e 10!");
        }
        this.numeroGiocatori = numeroGiocatori;
        this.durataMediaPartita = durataMediaPartita;
    }

    public int getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public void setNumeroGiocatori(int numeroGiocatori) {
        if (numeroGiocatori < 2 || numeroGiocatori > 10) {
            throw new IllegalArgumentException("Il numero di giocatori deve essere tra 2 e 10!");
        }
        this.numeroGiocatori = numeroGiocatori;
    }

    public int getDurataMediaPartita() {
        return durataMediaPartita;
    }

    public void setDurataMediaPartita(int durataMediaPartita) {
        this.durataMediaPartita = durataMediaPartita;
    }

    @Override
    public String toString() {
        return "GiocoDaTavolo{" +
                "id='" + getId() + '\'' +
                ", titolo='" + getTitolo() + '\'' +
                ", annoPubblicazione=" + getAnnoPubblicazione() +
                ", prezzo=" + getPrezzo() +
                ", numeroGiocatori=" + numeroGiocatori +
                ", durataMediaPartita=" + durataMediaPartita + " minuti" +
                '}';
    }
}