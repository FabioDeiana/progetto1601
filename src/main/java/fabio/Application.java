package fabio;

import fabio.collezione.Collezione;
import fabio.entities.Genere;
import fabio.entities.Gioco;
import fabio.entities.GiocoDaTavolo;
import fabio.entities.Videogioco;
import fabio.exceptions.GiocoGiaEsistenteException;
import fabio.exceptions.GiocoNonTrovatoException;

import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Collezione collezione = new Collezione();

        // Giochi di esempio
        try {
            collezione.aggiungiGioco(new Videogioco("VG001", "The Last of Us", 2013, 59.99, "PS5", 20, Genere.AVVENTURA));
            collezione.aggiungiGioco(new Videogioco("VG002", "FIFA 24", 2023, 69.99, "PC", 100, Genere.SPORT));
            collezione.aggiungiGioco(new Videogioco("VG003", "Elden Ring", 2022, 49.99, "Xbox", 80, Genere.RPG));
            collezione.aggiungiGioco(new GiocoDaTavolo("GT001", "Monopoly", 1935, 29.99, 6, 90));
            collezione.aggiungiGioco(new GiocoDaTavolo("GT002", "Scacchi", 1475, 19.99, 2, 60));
            collezione.aggiungiGioco(new GiocoDaTavolo("GT003", "Catan", 1995, 39.99, 4, 120));
        } catch (GiocoGiaEsistenteException e) {
            System.out.println(e.getMessage());
        }

        boolean continua = true;

        while (continua) {
            System.out.println("\n=== MENU COLLEZIONE GIOCHI ===");
            System.out.println("1. Aggiungi gioco");
            System.out.println("2. Cerca per ID");
            System.out.println("3. Cerca per prezzo");
            System.out.println("4. Cerca per numero giocatori");
            System.out.println("5. Rimuovi gioco");
            System.out.println("6. Aggiorna gioco");
            System.out.println("7. Statistiche");
            System.out.println("8. Mostra tutti");
            System.out.println("0. Esci");
            System.out.println("==============================");
            System.out.print("Scelta: ");

            try {
                int scelta = Integer.parseInt(scanner.nextLine());

                switch (scelta) {
                    case 1:
                        // Aggiungi gioco
                        System.out.println("\nTipo gioco:");
                        System.out.println("1. Videogioco");
                        System.out.println("2. Gioco da tavolo");
                        System.out.print("Scelta: ");
                        int tipo = Integer.parseInt(scanner.nextLine());

                        System.out.print("ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Titolo: ");
                        String titolo = scanner.nextLine();
                        System.out.print("Anno: ");
                        int anno = Integer.parseInt(scanner.nextLine());
                        System.out.print("Prezzo: ");
                        double prezzo = Double.parseDouble(scanner.nextLine());

                        if (tipo == 1) {
                            System.out.print("Piattaforma: ");
                            String piattaforma = scanner.nextLine();
                            System.out.print("Durata (ore): ");
                            int durata = Integer.parseInt(scanner.nextLine());
                            System.out.println("Genere (1=AZIONE, 2=AVVENTURA, 3=SPORT, 4=RPG, 5=STRATEGIA): ");
                            int genereScelta = Integer.parseInt(scanner.nextLine());

                            Genere genere = Genere.AZIONE;
                            if (genereScelta == 2) genere = Genere.AVVENTURA;
                            else if (genereScelta == 3) genere = Genere.SPORT;
                            else if (genereScelta == 4) genere = Genere.RPG;
                            else if (genereScelta == 5) genere = Genere.STRATEGIA;

                            try {
                                Videogioco v = new Videogioco(id, titolo, anno, prezzo, piattaforma, durata, genere);
                                collezione.aggiungiGioco(v);
                            } catch (GiocoGiaEsistenteException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.print("Numero giocatori: ");
                            int numGiocatori = Integer.parseInt(scanner.nextLine());
                            System.out.print("Durata partita (minuti): ");
                            int durataPartita = Integer.parseInt(scanner.nextLine());

                            try {
                                GiocoDaTavolo g = new GiocoDaTavolo(id, titolo, anno, prezzo, numGiocatori, durataPartita);
                                collezione.aggiungiGioco(g);
                            } catch (GiocoGiaEsistenteException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;

                    case 2:
                        // Cerca per ID
                        System.out.print("\nID gioco: ");
                        String idCerca = scanner.nextLine();
                        try {
                            Gioco gioco = collezione.ricercaPerId(idCerca);
                            System.out.println("\nTrovato:");
                            System.out.println(gioco);
                        } catch (GiocoNonTrovatoException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 3:
                        // Cerca per prezzo
                        System.out.print("\nPrezzo massimo: ");
                        double prezzoMax = Double.parseDouble(scanner.nextLine());
                        List<Gioco> giochi = collezione.ricercaPerPrezzo(prezzoMax);

                        if (giochi.isEmpty()) {
                            System.out.println("Nessun gioco trovato");
                        } else {
                            System.out.println("\nGiochi trovati:");
                            for (Gioco g : giochi) {
                                System.out.println(g);
                            }
                        }
                        break;

                    case 4:
                        // Cerca per numero giocatori
                        System.out.print("\nNumero giocatori: ");
                        int numGiocatori = Integer.parseInt(scanner.nextLine());
                        List<GiocoDaTavolo> giochiTavolo = collezione.ricercaPerNumeroGiocatori(numGiocatori);

                        if (giochiTavolo.isEmpty()) {
                            System.out.println("Nessun gioco trovato");
                        } else {
                            System.out.println("\nGiochi trovati:");
                            for (GiocoDaTavolo g : giochiTavolo) {
                                System.out.println(g);
                            }
                        }
                        break;

                    case 5:
                        // Rimuovi
                        System.out.print("\nID gioco da rimuovere: ");
                        String idRimuovi = scanner.nextLine();
                        try {
                            collezione.rimuoviGioco(idRimuovi);
                        } catch (GiocoNonTrovatoException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 6:
                        // Aggiorna
                        System.out.print("\nID gioco da aggiornare: ");
                        String idAggiorna = scanner.nextLine();

                        try {
                            Gioco giocoEsistente = collezione.ricercaPerId(idAggiorna);
                            System.out.println("Gioco attuale: " + giocoEsistente);

                            System.out.print("Nuovo titolo: ");
                            String nuovoTitolo = scanner.nextLine();
                            System.out.print("Nuovo anno: ");
                            int nuovoAnno = Integer.parseInt(scanner.nextLine());
                            System.out.print("Nuovo prezzo: ");
                            double nuovoPrezzo = Double.parseDouble(scanner.nextLine());

                            if (giocoEsistente instanceof Videogioco) {
                                Videogioco v = (Videogioco) giocoEsistente;
                                Videogioco nuovo = new Videogioco(idAggiorna, nuovoTitolo, nuovoAnno, nuovoPrezzo, v.getPiattaforma(), v.getDurataGioco(), v.getGenere());
                                collezione.aggiornaGioco(idAggiorna, nuovo);
                            } else if (giocoEsistente instanceof GiocoDaTavolo) {
                                GiocoDaTavolo g = (GiocoDaTavolo) giocoEsistente;
                                GiocoDaTavolo nuovo = new GiocoDaTavolo(idAggiorna, nuovoTitolo, nuovoAnno, nuovoPrezzo, g.getNumeroGiocatori(), g.getDurataMediaPartita());
                                collezione.aggiornaGioco(idAggiorna, nuovo);
                            }
                        } catch (GiocoNonTrovatoException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 7:
                        collezione.stampaStatistiche();
                        break;

                    case 8:
                        collezione.stampaTuttiGiochi();
                        break;

                    case 0:
                        continua = false;
                        System.out.println("Ciao!");
                        break;

                    default:
                        System.out.println("Scelta non valida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore! Inserisci un numero.");
            }
        }

        scanner.close();
    }
}