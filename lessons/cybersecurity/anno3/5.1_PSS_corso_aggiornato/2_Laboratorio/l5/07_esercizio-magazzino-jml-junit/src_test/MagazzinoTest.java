import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test set per la classe Magazzino.
 *
 * I test sono organizzati in tre blocchi:
 *
 *   1. Stato post-costruttore: verifica la postcondizione JML del
 *      costruttore (tutte le quantita e tutti i prezzi a 0).
 *
 *   2. Copertura delle decisioni di inserisciProdotto: come negli
 *      esercizi di l5 (vedi EsempioPerCoverage_CoperturaDecisioni),
 *      ogni test indica nei commenti le decisioni che copre IN PIU'
 *      rispetto ai precedenti. Le decisioni rilevanti del metodo sono:
 *
 *        D1: "prodotto >= 0 && prodotto < MAX_PRODOTTI"
 *        D2: "quantita[prodotto] + q <= MAX_QUANTITA"
 *        D3: "quantita[prodotto] == q"  (vero sse prima era 0)
 *
 *      Per la copertura delle decisioni serve almeno una valutazione
 *      a true e una a false di ognuna delle tre.
 *
 *   3. Verifica delle postcondizioni di inserisciProdotto:
 *        - se torna false lo stato non e' cambiato;
 *        - se torna true la quantita e' aumentata di q;
 *        - se il prodotto era gia' presente il prezzo non cambia
 *          (postcondizione richiesta dalla consegna).
 */
public class MagazzinoTest {

	// ============================================================
	// 1) Postcondizione del costruttore
	// ============================================================

	// Verifica che tutte le 200 quantita e tutti i 200 prezzi siano 0,
	// cioe' la traduzione in JUnit della postcondizione JML del costruttore.
	@Test
	public void costruttore_postcondizione_tuttoAZero() {
		Magazzino m = new Magazzino();
		for (int i = 0; i < Magazzino.MAX_PRODOTTI; i++) {
			assertEquals("quantita iniziale non nulla a indice " + i,
					0, m.getQuantita(i));
			assertEquals("prezzo iniziale non nullo a indice " + i,
					0, m.getPrezzo(i));
		}
	}

	// ============================================================
	// 2) Copertura delle decisioni di inserisciProdotto
	// ============================================================

	// copre:
	//  - D1 "prodotto >= 0 && prodotto < MAX_PRODOTTI" a true
	//  - D2 "quantita[prodotto] + q <= MAX_QUANTITA"   a true
	//  - D3 "quantita[prodotto] == q"                  a true
	//    (prima registrazione di un prodotto -> viene scritto anche il prezzo)
	@Test
	public void inserisciProdotto_coperturaDecisioni1_primoInserimentoOk() {
		Magazzino m = new Magazzino();
		boolean esito = m.inserisciProdotto(10, 50, 25);
		assertTrue("primo inserimento valido deve avere esito true", esito);
		assertEquals(50, m.getQuantita(10));
		assertEquals(25, m.getPrezzo(10));
	}

	// copre:
	//  - D3 "quantita[prodotto] == q" a false
	//    (il prodotto era gia' presente, il prezzo NON deve essere
	//     sovrascritto dal nuovo argomento p)
	@Test
	public void inserisciProdotto_coperturaDecisioni2_secondoInserimentoNonCambiaPrezzo() {
		Magazzino m = new Magazzino();
		assertTrue(m.inserisciProdotto(10, 50, 25));  // prima registrazione
		boolean esito = m.inserisciProdotto(10, 30, 999); // gia' presente
		assertTrue("inserimento aggiuntivo entro 100 deve avere esito true", esito);
		assertEquals("la quantita si somma", 80, m.getQuantita(10));
		assertEquals("il prezzo NON deve essere modificato", 25, m.getPrezzo(10));
	}

	// copre:
	//  - D2 "quantita[prodotto] + q <= MAX_QUANTITA" a false
	//    (l'aggiunta sforerebbe il limite di 100 unita: rifiutata)
	@Test
	public void inserisciProdotto_coperturaDecisioni3_sforaQuantitaMassima() {
		Magazzino m = new Magazzino();
		assertTrue(m.inserisciProdotto(10, 50, 25)); // ora quantita = 50
		boolean esito = m.inserisciProdotto(10, 51, 100); // 50 + 51 > 100
		assertFalse("inserimento che sfora MAX_QUANTITA deve avere esito false", esito);
		assertEquals("la quantita non cambia", 50, m.getQuantita(10));
		assertEquals("il prezzo non cambia",   25, m.getPrezzo(10));
	}

	// copre:
	//  - D1a "prodotto >= 0" a false  (sotto il limite inferiore)
	@Test
	public void inserisciProdotto_coperturaDecisioni4_indiceNegativo() {
		Magazzino m = new Magazzino();
		boolean esito = m.inserisciProdotto(-1, 10, 5);
		assertFalse("indice negativo deve essere rifiutato", esito);
	}

	// copre:
	//  - D1b "prodotto < MAX_PRODOTTI" a false  (sopra il limite superiore)
	@Test
	public void inserisciProdotto_coperturaDecisioni5_indiceFuoriRangeAlto() {
		Magazzino m = new Magazzino();
		boolean esito = m.inserisciProdotto(Magazzino.MAX_PRODOTTI, 10, 5);
		assertFalse("indice >= MAX_PRODOTTI deve essere rifiutato", esito);
	}

	// ============================================================
	// 3) Verifica delle postcondizioni di inserisciProdotto
	// ============================================================

	// Postcondizione: se il metodo torna false lo stato non e' modificato.
	// Testata su tutti i possibili motivi di fallimento (D1 false e D2 false).
	@Test
	public void inserisciProdotto_postcondizione_falseLasciaStatoInvariato() {
		Magazzino m = new Magazzino();
		assertTrue(m.inserisciProdotto(5, 40, 10));
		// snapshot dello stato prima dei tentativi falliti
		int qPrima = m.getQuantita(5);
		int pPrima = m.getPrezzo(5);

		// 1) sfora MAX_QUANTITA
		assertFalse(m.inserisciProdotto(5, 61, 99));
		assertEquals(qPrima, m.getQuantita(5));
		assertEquals(pPrima, m.getPrezzo(5));

		// 2) indice negativo
		assertFalse(m.inserisciProdotto(-5, 1, 1));
		assertEquals(qPrima, m.getQuantita(5));
		assertEquals(pPrima, m.getPrezzo(5));

		// 3) indice troppo alto
		assertFalse(m.inserisciProdotto(500, 1, 1));
		assertEquals(qPrima, m.getQuantita(5));
		assertEquals(pPrima, m.getPrezzo(5));
	}

	// Postcondizione: se torna true, quantita[prodotto] = old + q
	// e tutte le altre celle restano invariate.
	@Test
	public void inserisciProdotto_postcondizione_aggiornaSoloLaCellaInteressata() {
		Magazzino m = new Magazzino();
		assertTrue(m.inserisciProdotto(7, 10, 3));
		assertTrue(m.inserisciProdotto(7, 20, 999)); // gia' presente

		assertEquals(30, m.getQuantita(7));
		assertEquals( 3, m.getPrezzo(7));

		// Tutti gli altri prodotti devono essere ancora a 0/0.
		for (int i = 0; i < Magazzino.MAX_PRODOTTI; i++) {
			if (i == 7) continue;
			assertEquals("altra cella modificata a indice " + i,
					0, m.getQuantita(i));
			assertEquals("altro prezzo modificato a indice " + i,
					0, m.getPrezzo(i));
		}
	}

	// Postcondizione richiesta dalla consegna:
	// dopo un inserimento aggiuntivo su un prodotto gia' presente,
	// il prezzo registrato e' uguale a quello memorizzato in precedenza.
	@Test
	public void inserisciProdotto_postcondizione_prezzoUgualeAlPrecedente() {
		Magazzino m = new Magazzino();
		assertTrue(m.inserisciProdotto(42, 1, 7));
		int prezzoPrecedente = m.getPrezzo(42);
		assertTrue(m.inserisciProdotto(42, 50, 12345));
		assertEquals("il prezzo deve restare quello della prima registrazione",
				prezzoPrecedente, m.getPrezzo(42));
	}

	// ============================================================
	// 4) Casi al contorno (boundary)
	// ============================================================

	// Boundary: prodotto = 0 e prodotto = MAX_PRODOTTI - 1 sono validi.
	@Test
	public void inserisciProdotto_boundary_indiciEstremiValidi() {
		Magazzino m = new Magazzino();
		assertTrue(m.inserisciProdotto(0, 1, 1));
		assertTrue(m.inserisciProdotto(Magazzino.MAX_PRODOTTI - 1, 1, 1));
		assertEquals(1, m.getQuantita(0));
		assertEquals(1, m.getQuantita(Magazzino.MAX_PRODOTTI - 1));
	}

	// Boundary: arrivare esattamente a MAX_QUANTITA e' consentito,
	// superarlo anche di 1 e' rifiutato.
	@Test
	public void inserisciProdotto_boundary_quantitaMassima() {
		Magazzino m = new Magazzino();
		assertTrue("100 unita' in una volta sola e' ammesso",
				m.inserisciProdotto(0, Magazzino.MAX_QUANTITA, 5));
		assertEquals(Magazzino.MAX_QUANTITA, m.getQuantita(0));

		Magazzino m2 = new Magazzino();
		assertFalse("101 unita' eccede il limite",
				m2.inserisciProdotto(0, Magazzino.MAX_QUANTITA + 1, 5));
		assertEquals(0, m2.getQuantita(0));
		assertEquals(0, m2.getPrezzo(0));
	}
}
