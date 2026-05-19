/**
 * Esercizio - Magazzino (JML + JUnit)
 *
 * Modella un magazzino con 200 prodotti diversi, ognuno identificato
 * dall'indice i compreso fra 0 e 199.
 *
 * Il costruttore inizializza due array paralleli:
 *   - quantita[i] = quantita corrente del prodotto i (in [0, 100])
 *   - prezzo[i]   = prezzo unitario del prodotto i (>= 0)
 *
 * Il metodo inserisciProdotto aggiunge unita di un prodotto:
 *   - rifiuta indici fuori da [0, 199];
 *   - rifiuta se l'incremento porterebbe la quantita oltre 100;
 *   - se prima della chiamata la quantita era 0 scrive anche il
 *     prezzo (prima registrazione del prodotto); altrimenti
 *     conserva il prezzo gia presente.
 *
 * Lo stile delle annotazioni segue quello degli esercizi del corso
 * (vedi Tombola.java, CounterAssert.java): JML come contratto
 * dichiarativo e asserzioni Java come verifica runtime equivalente.
 */
public class Magazzino {

	public static final int MAX_PRODOTTI = 200;
	public static final int MAX_QUANTITA = 100;

	private /*@ spec_public @*/ int[] quantita;
	private /*@ spec_public @*/ int[] prezzo;

	/*@ // ---------- Invarianti di classe ----------
	  @
	  @ // Strutture allocate e di dimensione fissata.
	  @ invariant quantita != null && quantita.length == MAX_PRODOTTI;
	  @ invariant prezzo   != null && prezzo.length   == MAX_PRODOTTI;
	  @
	  @ // Le quantita sono sempre in [0, MAX_QUANTITA].
	  @ invariant (\forall int i; 0 <= i && i < MAX_PRODOTTI;
	  @                            0 <= quantita[i] && quantita[i] <= MAX_QUANTITA);
	  @
	  @ // I prezzi non sono mai negativi.
	  @ // (La consegna chiede "prezzi > 0": come invariante stretto
	  @ //  sarebbe pero' incompatibile con la postcondizione del
	  @ //  costruttore (tutti i prezzi a 0). Si rilassa quindi a >= 0,
	  @ //  e si esprime la versione "stretta" come implicazione: se un
	  @ //  prodotto e' stato registrato (quantita > 0) il suo prezzo
	  @ //  e' > 0.)
	  @ invariant (\forall int i; 0 <= i && i < MAX_PRODOTTI; prezzo[i] >= 0);
	  @ invariant (\forall int i; 0 <= i && i < MAX_PRODOTTI;
	  @                            quantita[i] > 0 ==> prezzo[i] > 0);
	  @*/

	/*@ // ---------- Postcondizione del costruttore ----------
	  @ // Tutte le quantita e tutti i prezzi sono inizializzati a 0.
	  @ ensures (\forall int i; 0 <= i && i < MAX_PRODOTTI; quantita[i] == 0);
	  @ ensures (\forall int i; 0 <= i && i < MAX_PRODOTTI; prezzo[i]   == 0);
	  @*/
	public Magazzino() {
		quantita = new int[MAX_PRODOTTI];
		prezzo   = new int[MAX_PRODOTTI];
		// Java azzera gia' i nuovi int[]; il ciclo rende esplicita
		// l'inizializzazione rispetto al contratto.
		for (int i = 0; i < MAX_PRODOTTI; i++) {
			quantita[i] = 0;
			prezzo[i]   = 0;
		}

		// Verifica runtime della postcondizione del costruttore
		// (traduzione in Java puro dell'ensures JML; serve il quantificatore
		// esplicito perche' Java non ha \forall - cfr. l4.md sezione 10).
		for (int i = 0; i < MAX_PRODOTTI; i++) {
			assert quantita[i] == 0 && prezzo[i] == 0
					: "stato post-costruttore non valido: indice " + i
					+ ", quantita=" + quantita[i] + ", prezzo=" + prezzo[i];
		}
	}

	/*@ // ---------- Postcondizioni di inserisciProdotto ----------
	  @
	  @ // Se il metodo torna false lo stato del magazzino non e' cambiato.
	  @ ensures (!\result) ==>
	  @         (\forall int i; 0 <= i && i < MAX_PRODOTTI;
	  @                          quantita[i] == \old(quantita[i])
	  @                       && prezzo[i]   == \old(prezzo[i]));
	  @
	  @ // Se il metodo torna true, la quantita del prodotto e' stata
	  @ // incrementata di q e tutte le altre celle sono invariate.
	  @ ensures \result ==>
	  @         quantita[prodotto] == \old(quantita[prodotto]) + q;
	  @ ensures \result ==>
	  @         (\forall int i; 0 <= i && i < MAX_PRODOTTI && i != prodotto;
	  @                          quantita[i] == \old(quantita[i])
	  @                       && prezzo[i]   == \old(prezzo[i]));
	  @
	  @ // Postcondizione richiesta dalla consegna:
	  @ // se il prodotto era gia' presente (quantita precedente > 0)
	  @ // il prezzo registrato non viene modificato e rimane uguale
	  @ // a quello memorizzato in precedenza.
	  @ ensures (\result && \old(quantita[prodotto]) > 0) ==>
	  @         prezzo[prodotto] == \old(prezzo[prodotto]);
	  @*/
	public boolean inserisciProdotto(int prodotto, int q, int p) {
		if (prodotto >= 0 && prodotto < MAX_PRODOTTI) {                 // D1
			if (quantita[prodotto] + q <= MAX_QUANTITA) {               // D2
				// Salvataggio manuale dei "vecchi" valori per la verifica
				// runtime della postcondizione (in Java puro non esiste \old).
				int oldQuantita = quantita[prodotto];
				int oldPrezzo   = prezzo[prodotto];

				quantita[prodotto] += q;
				if (quantita[prodotto] == q) {                           // D3 (vero <=> prima era 0)
					prezzo[prodotto] = p;
				}

				// Postcondizione consegna: se era gia' presente il prezzo
				// non deve essere cambiato.
				assert oldQuantita == 0 || prezzo[prodotto] == oldPrezzo
						: "prezzo modificato per un prodotto gia' presente: "
						+ "prodotto=" + prodotto
						+ ", oldPrezzo=" + oldPrezzo
						+ ", prezzoAttuale=" + prezzo[prodotto];

				return true;
			}
		}
		return false;
	}

	// ---------- Getter di sola lettura (utili nei test) ----------

	/*@ requires 0 <= i && i < MAX_PRODOTTI;
	  @ ensures \result == quantita[i];
	  @ pure @*/
	public int getQuantita(int i) {
		return quantita[i];
	}

	/*@ requires 0 <= i && i < MAX_PRODOTTI;
	  @ ensures \result == prezzo[i];
	  @ pure @*/
	public int getPrezzo(int i) {
		return prezzo[i];
	}
}
