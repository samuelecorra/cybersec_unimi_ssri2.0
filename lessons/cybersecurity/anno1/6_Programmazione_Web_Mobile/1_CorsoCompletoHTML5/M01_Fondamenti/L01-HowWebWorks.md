# **Lezione 1: Come funziona davvero il Web**

## 1. Perché partiamo dalla struttura del Web

Prima di scrivere una singola riga di HTML, serve capire come funziona il Web a livello di flusso. Il motivo è semplice: nei prossimi corsi (su YouTube o sul sito) si entrerà nel lato server, nei linguaggi di programmazione, nella comunicazione tra client e server. Le cose diventeranno più complesse, ma tutto si appoggerà sempre alla stessa struttura iniziale.

Questa è la mappa mentale fondamentale: se non capisco la struttura base, non capirò mai la struttura avanzata.

## 2. Il browser: il nostro punto di partenza

Tu stai guardando questa lezione da un browser: può essere su telefono, tablet o PC, non cambia nulla. Un browser è un programma che può fare richieste verso altri computer (server), ricevere risposte, leggere file e trasformarli in una pagina visibile sullo schermo.

Nel prossimo video vedremo cos’è una pagina HTML, ma per ora basta un concetto chiave: il sito che vedi esiste solo perché il browser è in grado di interpretare HTML e tutto ciò che lo accompagna. Senza il browser vedresti soltanto codice grezzo.

## 3. Cosa succede quando digiti un sito

Immagina di scrivere `sketch.io` nella barra degli indirizzi e premere Invio. La pagina appare quasi subito, ma dietro le quinte succede questo:
1. Il tuo browser invia una richiesta HTTP.
2. La richiesta viaggia in rete fino a un server.
3. Il server risponde inviandoti una pagina HTML (più altre risorse).

Nella barra vedrai “https”. Il protocollo è sempre HTTP; la `S` significa “sicuro” perché i dati sono criptati. Non entriamo ora nei dettagli: ti basta ricordare che la richiesta che facciamo è una richiesta HTTP, e che HTTPS è la sua versione protetta.

## 4. Cos’è un server (senza immaginarlo come un PC normale)

Un server è un computer sempre acceso. Ma non è il classico PC con tastiera e monitor: sono scatole di metallo piene di hardware, allineate in grandi stanze, sempre operative.

Perché usiamo un server e non il computer di casa? Ci sono due motivi fondamentali.

### 4.1. Banda Internet

Un sito molto visitato consumerebbe tutta la connessione di casa. La rete domestica non è progettata per gestire migliaia di richieste al secondo, grandi quantità di dati e picchi di traffico. I server, invece, hanno connessioni professionali pensate proprio per questo.

### 4.2. Sicurezza

Se ospitassi il sito sul tuo PC, chiunque potrebbe provare a entrare nella tua rete e nel tuo computer, che sarebbe esposto 24 ore su 24. I server sono progettati per essere più isolati, protetti e monitorati.

## 5. Dal browser al server: la richiesta HTTP

Quando digiti `sketch.io`, il browser invia una richiesta HTTP al server. Il server riceve la richiesta e deve capire che cosa stai chiedendo. Se nella URL non hai specificato altro (nessun `/qualcosa`), per convenzione ti consegna il file:

`index.html`

L’index è la pagina predefinita di qualunque sito, la porta d’ingresso. Il server trova `index.html` e te lo manda come risposta.

## 6. Il browser riceve la risposta

Il browser riceve:
- la pagina HTML,
- lo stile (CSS),
- le immagini,
- eventuali video,
- codice aggiuntivo (per esempio JavaScript),
- tutte le risorse necessarie a mostrare la pagina così come l’autore l’ha progettata.

Poi comincia a costruire il sito sullo schermo: mette il logo in alto, crea il menu, disegna i pulsanti, applica i colori, carica le immagini, gestisce le interazioni. L’HTML è solo una parte del tutto: il browser assembla una pagina usando molti file diversi.

In questo corso ci concentreremo sull’HTML, ma devi sapere che dietro le quinte c’è un pacchetto di risorse molto più ampio.

## 7. Le sottocartelle del sito

Un sito è, in pratica, una cartella su un computer (il server) con:
- file HTML,
- immagini,
- script,
- fogli di stile,
- altre sottocartelle.

Per esempio, quando vai su:

`sketch.io/education`

stai dicendo al server: “Dammi la pagina education”. Il browser invia una nuova richiesta HTTP. Il server capisce che vuoi `/education`, cerca il file corrispondente e ti manda la pagina di education, insieme a tutte le sue risorse (stili, immagini, codice, eccetera).

Ogni volta che cambi pagina, il meccanismo è lo stesso: nuova richiesta, nuova risposta.

## 8. L’idea fondamentale: il Web è uno scambio continuo di file

Il funzionamento del Web si può riassumere così:
1. Il browser chiede un file (HTML, immagine, video, eccetera).
2. Il server lo manda.
3. Il browser lo interpreta e lo trasforma in qualcosa di visibile.

È un meccanismo concettualmente semplice, ma potentissimo nei risultati.

## 9. Cosa succede ora

Ora che hai un’idea chiara dell’architettura del Web, possiamo passare al passo successivo: capire che cos’è, concretamente, una pagina HTML e come è fatta. Nel prossimo step andremo a vedere proprio la struttura interna di un file HTML.