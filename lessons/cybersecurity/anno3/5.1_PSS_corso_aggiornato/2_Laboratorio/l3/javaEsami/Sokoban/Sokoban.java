public class Sokoban {

	// (userR, userC) sono le coordinate (riga, colonna) del giocatore sulla scacchiera 5x5.
	// Anche qui annotati spec_public perche' compaiono nelle specifiche pubbliche di move
	// (in particolare nella postcondizione che usa \old(userR) e \old(userC)).
	private /*@ spec_public @*/ int userR, userC;

	// La scacchiera e' una matrice 5x5 di booleani: board[i][j] vale true se e solo se
	// la cella (i, j) contiene LA cassa (in questa versione semplificata ce n'è una sola).
	// Anche board e' spec_public perche' lo referenziamo nelle specifiche pubbliche.
	private /*@ spec_public @*/ boolean[][] board;

	// Postcondizione del costruttore (richiesta dalla consegna):
	// - tutte le celle della scacchiera tranne la cella (1, 1) devono valere false.
	//
	// La traduzione in JML usa un \forall su tutti gli indici (i, j) della scacchiera 5x5,
	// escludendo la coppia (1, 1): se almeno una delle due coordinate e' diversa da 1, allora
	// la cella deve essere false (cioe' !board[i][j] e' true).
	//
	// Aggiungiamo - per coerenza con il method body, che inizializza esplicitamente board[1][1] a true
	// e mette il giocatore in (0, 0) - anche gli ensures "positivi" su board[1][1] e sullo stato
	// iniziale di userR/userC. La consegna chiede solo il primo punto, ma fissare l'intero stato
	// iniziale e' utile a chi volesse appoggiarsi a queste informazioni in altre specifiche.

	// Nel range del \forall escludiamo direttamente la cella (1, 1) con la negazione
	// della congiunzione: !(i == 1 && j == 1). E' la forma piu' aderente al testo della
	// consegna ("tutte le celle TRANNE la (1, 1)"), ed e' equivalente per De Morgan a
	// (i != 1 || j != 1).
	/*@ ensures (\forall int i, j; i >= 0 && i < 5 && j >= 0 && j < 5 && !(i == 1 && j == 1);
	  @           !board[i][j]);
	  @ ensures board[1][1];
	  @ ensures userR == 0 && userC == 0;
	  @*/
	public Sokoban() {
		board = new boolean[5][5];
		board[1][1] = true;
		userR = 0;
		userC = 0;
	}

	// Precondizione del metodo move (richiesta dalla consegna):
	// - dir deve essere compreso tra 1 e 4. Le quattro direzioni cardinali sono codificate cosi':
	//     1 = su, 2 = destra, 3 = giu', 4 = sinistra.
	//   Senza questa pre, un dir = 5 (o qualunque altro valore) "salterebbe" tutti i rami if/else if
	//   e il metodo restituirebbe sempre false, senza nessun effetto: meglio bloccarlo a monte.
	//
	// Postcondizioni del metodo move (richieste dalla consegna):
	//
	// 1) Nella scacchiera deve continuare ad esserci ESATTAMENTE una cella che vale true: la
	//    cassa rimane sempre una sola e non si duplica ne' sparisce a causa della mossa.
	//    Lo esprimiamo elegantemente con \num_of, che conta le coppie (i, j) per cui board[i][j]
	//    e' true: il risultato deve essere 1.
	//
	// 2) Il giocatore puo' muoversi solo in una direzione cardinale alla volta: di conseguenza
	//    fra prima e dopo la chiamata almeno una delle due coordinate deve essere rimasta invariata
	//    (se la mossa non viene eseguita affatto, restano invariate entrambe; se viene eseguita,
	//    cambia solo userR oppure solo userC, mai tutte e due insieme).
	//    \old(...) ci da' il valore della variabile al momento dell'ingresso nel metodo, cosi'
	//    possiamo confrontarlo con il valore finale.
	/*@ requires dir >= 1 && dir <= 4;
	  @
	  @ ensures (\num_of int i, j; i >= 0 && i < 5 && j >= 0 && j < 5; board[i][j]) == 1;
	  @ ensures userR == \old(userR) || userC == \old(userC);
	  @*/
	// Schema generale dei quattro rami if/else if (e' identico, cambia solo la direzione).
	// Per ciascuna direzione il metodo fa al massimo TRE controlli prima di muovere qualcosa:
	//   (a) il giocatore non e' gia' "schiacciato" contro il muro in quella direzione;
	//   (b) nella cella adiacente verso cui vuole entrare c'e' la cassa?
	//        - NO -> il giocatore si sposta liberamente, senza push;
	//        - SI -> bisogna anche spingerla, quindi serve un ulteriore controllo:
	//   (c) la cella DUE passi avanti (futura posizione della cassa) esiste ancora sulla scacchiera.
	// Se uno qualsiasi di questi tre controlli salta, si cade in fondo al metodo e si ritorna false.
	//
	// IMPORTANTE per leggere le due righe del "push": userR/userC vengono aggiornati PRIMA delle
	// scritture su board, quindi nelle istruzioni successive board[userR][...] si riferisce alla
	// NUOVA posizione del giocatore - che coincide con la VECCHIA cella della cassa.
	public boolean move(int dir) {
		if(dir == 1) { // dir == 1: il giocatore vuole salire (muoversi verso l'alto).
			if(userR != 0) { // (a) procediamo solo se non e' gia' schiacciato contro la prima riga; altrimenti uscirebbe dalla scacchiera.
				if(board[userR - 1][userC]) { // (b) c'e' la cassa nella cella immediatamente sopra di lui?
					if(userR > 1) { // (c) la cassa e' spingibile solo se la riga userR-2 esiste, cioe' userR-2 >= 0  <=>  userR > 1.
						userR = userR - 1; // Il giocatore sale effettivamente di una riga, andando a occupare la vecchia cella della cassa.
						board[userR][userC] = false; // Svuotiamo la vecchia cella della cassa (dove e' appena arrivato il giocatore).
						board[userR - 1][userC] = true; // Posiamo la cassa una riga piu' su rispetto alla nuova posizione del giocatore.
						return true; // Mossa con push eseguita: il giocatore si e' mosso (insieme alla cassa).
					}
					// Se siamo qui userR == 1: la cassa e' gia' in riga 0 e spingerla la farebbe uscire. Cadiamo al return false finale, lasciando lo stato invariato.
				}
				else { // Sopra non c'e' nessuna cassa: salita libera.
					userR = userR - 1; // Il giocatore sale di una riga, senza dover spingere nulla.
					return true; // Mossa senza push eseguita.
				}
			}
			// Se userR == 0 siamo gia' contro il muro superiore: nessun ramo ha restituito true e finiamo al return false in fondo.
		}
		else if(dir == 2) { // dir == 2: il giocatore vuole andare a destra.
			if(userC != 4) { // (a) procediamo solo se non e' gia' schiacciato contro l'ultima colonna.
				if(board[userR][userC + 1]) { // (b) c'e' la cassa nella cella immediatamente alla sua destra?
					if(userC < 3) { // (c) la cassa e' spingibile solo se la colonna userC+2 esiste, cioe' userC+2 <= 4  <=>  userC < 3.
						userC = userC + 1; // Il giocatore avanza effettivamente di una colonna verso destra.
						board[userR][userC] = false; // Svuotiamo la vecchia cella della cassa (ora occupata dal giocatore).
						board[userR][userC + 1] = true; // Posiamo la cassa nella colonna successiva alla nuova posizione del giocatore.
						return true; // Mossa con push eseguita.
					}
					// userC == 3: la cassa e' gia' in colonna 4 e non puo' essere spinta oltre il muro destro.
				}
				else { // A destra nessuna cassa: avanzata libera.
					userC = userC + 1; // Il giocatore avanza di una colonna.
					return true; // Mossa senza push eseguita.
				}
			}
			// userC == 4: muro destro, niente da fare.
		}
		else if(dir == 3) { // dir == 3: il giocatore vuole scendere.
			if(userR != 4) { // (a) procediamo solo se non e' gia' schiacciato contro l'ultima riga.
				if(board[userR + 1][userC]) { // (b) c'e' la cassa nella cella immediatamente sotto di lui?
					if(userR < 3) { // (c) la cassa e' spingibile solo se la riga userR+2 esiste, cioe' userR+2 <= 4  <=>  userR < 3.
						userR = userR + 1; // Il giocatore scende effettivamente di una riga.
						board[userR][userC] = false; // Svuotiamo la vecchia cella della cassa.
						board[userR + 1][userC] = true; // Posiamo la cassa una riga piu' in basso rispetto al giocatore.
						return true; // Mossa con push eseguita.
					}
					// userR == 3: la cassa e' gia' in riga 4 e non puo' essere spinta oltre il muro inferiore.
				}
				else { // Sotto non c'e' nessuna cassa: discesa libera.
					userR = userR + 1; // Il giocatore scende di una riga.
					return true; // Mossa senza push eseguita.
				}
			}
			// userR == 4: muro inferiore.
		}
		else if(dir == 4) { // dir == 4: il giocatore vuole andare a sinistra.
			if(userC != 0) { // (a) procediamo solo se non e' gia' schiacciato contro la prima colonna.
				if(board[userR][userC - 1]) { // (b) c'e' la cassa nella cella immediatamente alla sua sinistra?
					if(userC > 1) { // (c) la cassa e' spingibile solo se la colonna userC-2 esiste, cioe' userC-2 >= 0  <=>  userC > 1.
						userC = userC - 1; // Il giocatore arretra effettivamente di una colonna verso sinistra.
						board[userR][userC] = false; // Svuotiamo la vecchia cella della cassa.
						board[userR][userC - 1] = true; // Posiamo la cassa una colonna piu' a sinistra rispetto al giocatore.
						return true; // Mossa con push eseguita.
					}
					// userC == 1: la cassa e' gia' in colonna 0 e non puo' essere spinta oltre il muro sinistro.
				}
				else { // A sinistra nessuna cassa: arretramento libero.
					userC = userC - 1; // Il giocatore arretra di una colonna.
					return true; // Mossa senza push eseguita.
				}
			}
			// userC == 0: muro sinistro.
		}
		return false; // Confluenza di tutti i casi in cui la mossa NON e' stata eseguita: indici al muro o cassa non spingibile.
	}

	public static void main(String[] args) {
		Sokoban s = new Sokoban();

		// Esempi di chiamate che VIOLANO la precondizione su dir (lasciate commentate):
		//s.move(5);  // dir = 5 esce dal range [1, 4]
		//s.move(0);  // anche dir = 0 viola la precondizione
		//s.move(-1); // idem, valori negativi non sono ammessi

		// Sequenza di mosse legali, scelta per mostrare la dinamica del push contro il muro.
		// Stato iniziale (P = giocatore, X = cassa):
		//   P . . . .
		//   . X . . .
		//   . . . . .
		//   . . . . .
		//   . . . . .
		s.move(3); // giu': il giocatore scende a (1, 0), la cassa non e' nel suo cammino, resta in (1, 1).
		           //   . . . . .
		           //   P X . . .
		           //   . . . . .
		s.move(2); // destra: la cassa in (1, 1) viene SPINTA in (1, 2), il giocatore va in (1, 1).
		           //   . . . . .
		           //   . P X . .
		           //   . . . . .
		s.move(2); // destra: la cassa viene spinta da (1, 2) a (1, 3), il giocatore va in (1, 2).
		s.move(2); // destra: la cassa viene spinta da (1, 3) a (1, 4), il giocatore va in (1, 3).
		           //   . . . . .
		           //   . . . P X
		           //   . . . . .
		// La prossima mossa NON viola la precondizione (dir e' valido) ma deve restituire false:
		// il giocatore e' in (1, 3) con la cassa in (1, 4) (ultima colonna). Il body controlla
		// userC < 3 per poter spingere, ma userC vale 3, quindi la spinta non si puo' fare e
		// si esce dal blocco senza alcuna modifica. Le postcondizioni JML restano comunque
		// soddisfatte: la cassa e' ancora una sola e nessuna coordinata del giocatore e' cambiata.
		boolean spostato = s.move(2);
		System.out.println("La spinta contro il muro ha mosso il giocatore? " + spostato);
	}
}
