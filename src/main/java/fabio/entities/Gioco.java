package fabio.entities;

public abstract class Gioco {
    private String id;
    private String titolo;
    private int annoPubblicazione;
    private double prezzo;

    public Gioco(String id, String titolo, int annoPubblicazione, double prezzo) {
        this.id = id;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        if (prezzo <= 0) {
            throw new IllegalArgumentException("Il prezzo deve essere maggiore di 0!");
        }
        this.prezzo = prezzo;
    }

    public String getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo <= 0) {
            throw new IllegalArgumentException("Il prezzo deve essere maggiore di 0!");
        }
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "Gioco{" +
                "id='" + id + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", prezzo=" + prezzo +
                '}';
    }
}