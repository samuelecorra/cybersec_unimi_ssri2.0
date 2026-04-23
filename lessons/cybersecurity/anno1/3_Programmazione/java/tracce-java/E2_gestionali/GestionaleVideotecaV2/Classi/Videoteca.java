package E2_gestionali.GestionaleVideotecaV2.Classi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Videoteca {

    // ATTRIBUTI
    private final List<Film> catalogoFilm;      // tutte le copie fisiche disponibili
    private final List<Cliente> clienti;        // tutti i clienti registrati
    private final Map<String, Integer> copiePerTitolo; // titolo -> numero copie in catalogo
    private int numeroClientiRegistrati;
    private int numeroFilmRegistrati;

    // COSTRUTTORE
    public Videoteca() {
        this.catalogoFilm = new ArrayList<>();
        this.clienti = new ArrayList<>();
        this.copiePerTitolo = new HashMap<>();
        this.numeroClientiRegistrati = 0;
        this.numeroFilmRegistrati = 0;
    }

    // ---------- FUNZIONI DI UTILITÀ INTERNE ---------- //

    // Cerca una copia fisica in catalogo tramite id
    private Film trovaFilmInCatalogoPerId(int idFilm) {
        for (Film f : catalogoFilm) {
            if (f.getId() == idFilm) {
                return f;
            }
        }
        return null;
    }

    // Cerca un cliente registrato tramite id
    private Cliente trovaClientePerId(int idCliente) {
        for (Cliente c : clienti) {
            if (c.getId() == idCliente) {
                return c;
            }
        }
        return null;
    }

    // ---------- GESTIONE CATALOGO ---------- //

    public void aggiungiAlCatalogo(Film film) {
        Film eGiaInCatalogo = trovaFilmInCatalogoPerId(film.getId());
        if (eGiaInCatalogo == null) {
            catalogoFilm.add(film);
            copiePerTitolo.merge(film.getTitolo(), 1, Integer::sum);
            numeroFilmRegistrati++;
        } else {
            System.out.println("Errore: copia con id " + film.getId() + " già esistente in catalogo.");
        }
    }

    public void rimuoviDalCatalogo(Film film) {
        Film eGiaInCatalogo = trovaFilmInCatalogoPerId(film.getId());
        if (eGiaInCatalogo != null) {
            catalogoFilm.remove(eGiaInCatalogo);
            copiePerTitolo.computeIfPresent(eGiaInCatalogo.getTitolo(), (t, count) -> {
                int nuovoCount = count - 1;
                return (nuovoCount <= 0) ? null : nuovoCount;
            });
            numeroFilmRegistrati--;
        }
    }

    // ---------- GESTIONE CLIENTI ---------- //

    public void registraCliente(Cliente cliente) {
        Cliente eGiaRegistrato = trovaClientePerId(cliente.getId());
        if (eGiaRegistrato == null) {
            clienti.add(cliente);
            numeroClientiRegistrati++;
        }
    }

    public void rimuoviCliente(Cliente cliente) {
        Cliente eGiaRegistrato = trovaClientePerId(cliente.getId());
        if (eGiaRegistrato != null) {
            clienti.remove(eGiaRegistrato);
            numeroClientiRegistrati--;
        }
    }

    // ---------- NOLEGGIO E RESTITUZIONE (NUOVE FIRME) ---------- //

    /**
     * Noleggia una copia fisica identificata da idFilm ad un cliente identificato da idCliente.
     * Ritorna true se l'operazione va a buon fine, false altrimenti.
     */
    public boolean noleggiaFilm(int idFilm, int idCliente) {

        Film eInCatalogo = trovaFilmInCatalogoPerId(idFilm);
        if (eInCatalogo == null) {
            System.out.println("Noleggio fallito: nessuna copia disponibile con id " + idFilm);
            return false;
        }

        Cliente eRegistrato = trovaClientePerId(idCliente);
        if (eRegistrato == null) {
            System.out.println("Noleggio fallito: nessun cliente registrato con id " + idCliente);
            return false;
        }

        // La copia esce dal catalogo
        catalogoFilm.remove(eInCatalogo);

        // Aggiorna contatore per il titolo
        String titolo = eInCatalogo.getTitolo();
        copiePerTitolo.computeIfPresent(titolo, (t, count) -> {
            int nuovoCount = count - 1;
            return (nuovoCount <= 0) ? null : nuovoCount;
        });

        // La copia entra nei film noleggiati dal cliente
        eRegistrato.aggiungiAiNoleggiati(eInCatalogo);

        System.out.println("Noleggio effettuato.");
        return true;
    }

    /*
     * Restituisce una copia fisica identificata da idFilm da parte di un cliente identificato da idCliente.
     * Ritorna true se l'operazione va a buon fine, false altrimenti.
     */
    public boolean restituisciFilm(int idFilm, int idCliente) {

        Cliente eRegistrato = trovaClientePerId(idCliente);
        if (eRegistrato == null) {
            System.out.println("Restituzione fallita: nessun cliente registrato con id " + idCliente);
            return false;
        }

        Film eNoleggiato = eRegistrato.restituisciFilm(idFilm);
        if (eNoleggiato == null) {
            System.out.println("Restituzione fallita: il cliente non ha una copia con id " + idFilm);
            return false;
        }

        catalogoFilm.add(eNoleggiato);
        copiePerTitolo.merge(eNoleggiato.getTitolo(), 1, Integer::sum);

        System.out.println("Restituzione effettuata.");
        return true;
    }

    // ---------- toString ---------- //

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Videoteca attuale ===\n");
        sb.append("Riepilogo:\n");
        sb.append("  • Clienti registrati: ").append(numeroClientiRegistrati).append('\n');
        sb.append("  • Copie totali registrate: ").append(numeroFilmRegistrati).append('\n');
        sb.append("  • Copie attualmente disponibili: ").append(catalogoFilm.size()).append('\n');
        sb.append("  • Titoli disponibili: ").append(copiePerTitolo.size()).append('\n');
        sb.append('\n');
        appendCatalogoDisponibile(sb);
        sb.append('\n');
        appendClientiRegistrati(sb);
        return sb.toString();
    }

    private void appendCatalogoDisponibile(StringBuilder sb) {
        sb.append("Catalogo disponibile:\n");
        if (catalogoFilm.isEmpty()) {
            sb.append("  Nessuna copia fisica disponibile al momento.\n");
            return;
        }

        Map<String, List<Film>> filmPerTitolo = new HashMap<>();
        for (Film film : catalogoFilm) {
            filmPerTitolo.computeIfAbsent(film.getTitolo(), t -> new ArrayList<>()).add(film);
        }

        List<String> titoli = new ArrayList<>(filmPerTitolo.keySet());
        titoli.sort(String.CASE_INSENSITIVE_ORDER);

        for (String titolo : titoli) {
            List<Film> copie = filmPerTitolo.get(titolo);
            sb.append("  - ").append(titolo)
                    .append(" (copie disponibili: ").append(copie.size()).append(")\n");

            copie.sort(Comparator.comparingInt(Film::getId));
            for (Film film : copie) {
                sb.append("      • copia #").append(film.getId())
                        .append(" - genere: ").append(film.getGenere())
                        .append('\n');
            }
        }
    }

    private void appendClientiRegistrati(StringBuilder sb) {
        sb.append("Clienti registrati:\n");
        if (clienti.isEmpty()) {
            sb.append("  Nessun cliente registrato.\n");
            return;
        }

        List<Cliente> clientiOrdinati = new ArrayList<>(clienti);
        clientiOrdinati.sort(Comparator
                .comparing(Cliente::getNome, String.CASE_INSENSITIVE_ORDER)
                .thenComparingInt(Cliente::getId));

        for (Cliente cliente : clientiOrdinati) {
            sb.append("  - [ID ").append(cliente.getId()).append("] ")
                    .append(cliente.getNome())
                    .append(" (noleggi attivi: ").append(cliente.getNumeroFilmNoleggiati()).append(")\n");

            List<Film> filmNoleggiati = cliente.getFilmNoleggiati();
            if (filmNoleggiati.isEmpty()) {
                sb.append("      Nessun film noleggiato.\n");
                continue;
            }

            List<Film> copieNoleggiate = new ArrayList<>(filmNoleggiati);
            copieNoleggiate.sort(Comparator
                    .comparing(Film::getTitolo, String.CASE_INSENSITIVE_ORDER)
                    .thenComparingInt(Film::getId));

            for (Film film : copieNoleggiate) {
                sb.append("      • ").append(film.getTitolo())
                        .append(" (copia #").append(film.getId())
                        .append(", genere: ").append(film.getGenere())
                        .append(")\n");
            }
        }
    }
}
