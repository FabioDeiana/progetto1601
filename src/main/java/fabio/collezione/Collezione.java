package fabio.collezione;

import fabio.entities.Gioco;
import fabio.entities.GiocoDaTavolo;
import fabio.entities.Videogioco;
import fabio.exceptions.GiocoGiaEsistenteException;
import fabio.exceptions.GiocoNonTrovatoException;

import java.util.ArrayList;
import java.util.List;

public class Collezione {
    private List<Gioco> giochi;

    public Collezione() {
        this.giochi = new ArrayList<>();
    }

    // Aggiunta elemento
    public void aggiungiGioco(Gioco gioco) throws GiocoGiaEsistenteException {
        // Controllo se ID già esiste
        for (Gioco g : giochi) {
            if (g.getId().equals(gioco.getId())) {
                throw new GiocoGiaEsistenteException(gioco.getId());
            }
        }
        giochi.add(gioco);
        System.out.println("Gioco aggiunto!");
    }

    // Ricerca per ID
    public Gioco ricercaPerId(String id) throws GiocoNonTrovatoException {
        for (Gioco gioco : giochi) {
            if (gioco.getId().equals(id)) {
                return gioco;
            }
        }
        throw new GiocoNonTrovatoException(id);
    }

    // Ricerca per prezzo
    public List<Gioco> ricercaPerPrezzo(double prezzoMassimo) {
        List<Gioco> risultati = new ArrayList<>();
        for (Gioco gioco : giochi) {
            if (gioco.getPrezzo() < prezzoMassimo) {
                risultati.add(gioco);
            }
        }
        return risultati;
    }

    // Ricerca per numero giocatori
    public List<GiocoDaTavolo> ricercaPerNumeroGiocatori(int numeroGiocatori) {
        List<GiocoDaTavolo> risultati = new ArrayList<>();
        for (Gioco gioco : giochi) {
            if (gioco instanceof GiocoDaTavolo) {
                GiocoDaTavolo giocoTavolo = (GiocoDaTavolo) gioco;
                if (giocoTavolo.getNumeroGiocatori() == numeroGiocatori) {
                    risultati.add(giocoTavolo);
                }
            }
        }
        return risultati;
    }

    // Rimozione elemento
    public void rimuoviGioco(String id) throws GiocoNonTrovatoException {
        Gioco giocoDaRimuovere = ricercaPerId(id);
        giochi.remove(giocoDaRimuovere);
        System.out.println("Gioco rimosso!");
    }

    // Aggiornamento elemento
    public void aggiornaGioco(String id, Gioco giocoAggiornato) throws GiocoNonTrovatoException {
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
        System.out.println("Gioco aggiornato!");
    }

    // Statistiche
    public void stampaStatistiche() {
        System.out.println("\n--- STATISTICHE COLLEZIONE ---");

        int numVideogiochi = 0;
        int numGiochiTavolo = 0;
        double prezzoMassimo = 0;
        String titoloMassimo = "";
        double somma = 0;

        for (Gioco gioco : giochi) {
            if (gioco instanceof Videogioco) {
                numVideogiochi++;
            } else if (gioco instanceof GiocoDaTavolo) {
                numGiochiTavolo++;
            }

            if (gioco.getPrezzo() > prezzoMassimo) {
                prezzoMassimo = gioco.getPrezzo();
                titoloMassimo = gioco.getTitolo();
            }

            somma += gioco.getPrezzo();
        }

        System.out.println("Videogiochi: " + numVideogiochi);
        System.out.println("Giochi da tavolo: " + numGiochiTavolo);
        System.out.println("Gioco più costoso: " + titoloMassimo + " - €" + prezzoMassimo);

        if (giochi.size() > 0) {
            double media = somma / giochi.size();
            System.out.println("Prezzo medio: €" + media);
        }

        System.out.println("------------------------------\n");
    }

    // Stampa tutti
    public void stampaTuttiGiochi() {
        if (giochi.isEmpty()) {
            System.out.println("Collezione vuota!");
        } else {
            System.out.println("\n--- LISTA GIOCHI ---");
            for (Gioco gioco : giochi) {
                System.out.println(gioco);
            }
            System.out.println("--------------------\n");
        }
    }
}