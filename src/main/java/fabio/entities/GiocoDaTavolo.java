package fabio.entities;

public class GiocoDaTavolo extends Gioco {
    private int numeroGiocatori;
    private int durataMediaPartita;

    public GiocoDaTavolo(String id, String titolo, int annoPubblicazione, double prezzo, int numeroGiocatori, int durataMediaPartita) {
        super(id, titolo, annoPubblicazione, prezzo);
        this.numeroGiocatori = numeroGiocatori;
        this.durataMediaPartita = durataMediaPartita;
    }

    public int getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public void setNumeroGiocatori(int numeroGiocatori) {
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