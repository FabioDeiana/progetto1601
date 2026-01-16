package fabio.collezione;

import fabio.entities.Gioco;
import fabio.entities.GiocoDaTavolo;
import fabio.entities.Videogioco;
import fabio.exceptions.GiocoGiaEsistenteException;
import fabio.exceptions.GiocoNonTrovatoException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Collezione {
    private List<Gioco> giochi;

    public Collezione() {
        this.giochi = new ArrayList<>();
    }

    // Aggiunta di un elemento
    public void aggiungiGioco(Gioco gioco) throws GiocoGiaEsistenteException {
        // Controllo se esiste già un gioco con lo stesso ID
        boolean esiste = giochi.stream().anyMatch(g -> g.getId().equals(gioco.getId()));

        if (esiste) {
            throw new GiocoGiaEsistenteException(gioco.getId());
        }

        giochi.add(gioco);
        System.out.println("Gioco aggiunto con successo!");
    }

    // Ricerca per ID
    public Gioco ricercaPerId(String id) throws GiocoNonTrovatoException {
        Optional<Gioco> giocoTrovato = giochi.stream()
                .filter(gioco -> gioco.getId().equals(id))
                .findFirst();

        if (giocoTrovato.isPresent()) {
            return giocoTrovato.get();
        } else {
            throw new GiocoNonTrovatoException(id);
        }
    }

    // Ricerca per prezzo - ritorna lista di giochi con prezzo inferiore
    public List<Gioco> ricercaPerPrezzo(double prezzoMassimo) {
        return giochi.stream()
                .filter(gioco -> gioco.getPrezzo() < prezzoMassimo)
                .collect(Collectors.toList());
    }

    // Ricerca per numero di giocatori (solo per giochi da tavolo)
    public List<GiocoDaTavolo> ricercaPerNumeroGiocatori(int numeroGiocatori) {
        return giochi.stream()
                .filter(gioco -> gioco instanceof GiocoDaTavolo)
                .map(gioco -> (GiocoDaTavolo) gioco)
                .filter(giocoTavolo -> giocoTavolo.getNumeroGiocatori() == numeroGiocatori)
                .collect(Collectors.toList());
    }

    // Rimozione di un elemento dato un codice ID
    public void rimuoviGioco(String id) throws GiocoNonTrovatoException {
        Gioco giocoDaRimuovere = ricercaPerId(id);
        giochi.remove(giocoDaRimuovere);
        System.out.println("Gioco rimosso con successo!");
    }

    // Aggiornamento di un elemento esistente dato l'ID
    public void aggiornaGioco(String id, Gioco giocoAggiornato) throws GiocoNonTrovatoException {
        // Trovo l'indice del gioco da aggiornare
        int index = -1;
        for (int i = 0; i < giochi.size(); i++) {
            if (giochi.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new GiocoNonTrovatoException(id);
        }

        giochi.set(index, giocoAggiornato);
        System.out.println("Gioco aggiornato con successo!");
    }

    // Statistiche della collezione
    public void stampaStatistiche() {
        System.out.println("\n===== STATISTICHE COLLEZIONE =====");

        // Numero totale di videogiochi
        long numeroVideogiochi = giochi.stream()
                .filter(gioco -> gioco instanceof Videogioco)
                .count();
        System.out.println("Numero totale di videogiochi: " + numeroVideogiochi);

        // Numero totale di giochi da tavolo
        long numeroGiochiTavolo = giochi.stream()
                .filter(gioco -> gioco instanceof GiocoDaTavolo)
                .count();
        System.out.println("Numero totale di giochi da tavolo: " + numeroGiochiTavolo);

        // Gioco con il prezzo più alto
        Optional<Gioco> giocoPiuCostoso = giochi.stream()
                .max(Comparator.comparing(Gioco::getPrezzo));

        if (giocoPiuCostoso.isPresent()) {
            System.out.println("Gioco più costoso: " + giocoPiuCostoso.get().getTitolo() + " - €" + giocoPiuCostoso.get().getPrezzo());
        }

        // Media dei prezzi
        double mediaPrezzi = giochi.stream()
                .mapToDouble(Gioco::getPrezzo)
                .average()
                .orElse(0.0);
        System.out.println("Media dei prezzi: €" + String.format("%.2f", mediaPrezzi));

        System.out.println("===================================\n");
    }

    // Stampa tutti i giochi
    public void stampaTuttiGiochi() {
        if (giochi.isEmpty()) {
            System.out.println("La collezione è vuota!");
        } else {
            System.out.println("\n===== COLLEZIONE GIOCHI =====");
            giochi.forEach(gioco -> System.out.println(gioco));
            System.out.println("=============================\n");
        }
    }
}
