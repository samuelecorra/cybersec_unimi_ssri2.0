// Introduzione accademica a fetch

/* La funzione fetch rappresenta oggi lo strumento fondamentale di JavaScript
per la comunicazione asincrona con il web. Essa nasce per sostituire e 
semplificare i meccanismi precedenti (come XMLHttpRequest), offrendo 
un’interfaccia moderna, coerente con il paradigma delle Promises e 
perfettamente integrata con la sintassi async/await.

Dal punto di vista concettuale, fetch permette a un’applicazione di inviare 
richieste HTTP e ricevere risposte dal server in maniera asincrona, senza 
bloccare il flusso principale del programma. Questo significa che mentre il 
browser attende la risposta da un server remoto (che può richiedere 
millisecondi o anche diversi secondi), l’esecuzione del codice JavaScript 
può continuare liberamente: l’utente può interagire con l’interfaccia, 
compiere altre azioni, e soltanto quando la risposta sarà pronta essa verrà 
gestita.

La grande potenza di fetch risiede nella sua versatilità: non si limita a 
recuperare dati in formato JSON (lo standard per l’interoperabilità web), 
ma è in grado di gestire anche testi, immagini, file multimediali o risorse 
binarie. In pratica, rappresenta la porta d’accesso privilegiata verso il 
mondo delle API (Application Programming Interfaces), cioè quei servizi che 
espongono dati e funzionalità attraverso il web.

Un altro aspetto cruciale è la naturale integrazione di fetch con il modello 
asincrono di JavaScript: la chiamata a fetch restituisce immediatamente una 
Promise, che entrerà nello stato “risolta” soltanto quando il server avrà 
inviato una risposta. Questa architettura consente di comporre operazioni 
complesse in modo leggibile, mantenendo il controllo sia sul flusso 
principale sia sulle eventuali eccezioni o errori di rete.

Dal punto di vista applicativo, fetch non è soltanto un mezzo tecnico: 
rappresenta il ponte fra il codice lato client (il browser) e l’enorme 
patrimonio di dati distribuiti sul web. Senza di essa, un’applicazione 
rimarrebbe isolata; con essa, invece, diventa possibile interagire con 
servizi remoti, consultare database globali, integrare contenuti dinamici e 
trasformare una semplice pagina web in una vera applicazione connessa.

👉 Ed è proprio qui che entra in gioco l’esempio pratico che svilupperai: 
usare fetch per interrogare un’API pubblica — in questo caso quella dei 
Pokémon — ti permetterà di vedere come un’applicazione locale, scritta 
in JavaScript, possa collegarsi a un archivio esterno, chiedere informazioni 
e trasformare quei dati grezzi in un’esperienza interattiva per l’utente. */

//////////////////////////////////////////////////////////////////////////////

// Partiamo dalla base: quale sarà l’API da interrogare? 
// In questo caso, useremo l’API dei Pokémon, 
// che ci permette di ottenere informazioni dettagliate su ogni Pokémon, 
// comprese le loro abilità, statistiche e molto altro.

// https://pokeapi.co/

// Ora, la sintassi generica di una fetch è:

// fetch(url, {options})
//   .then(response => response.json())
//   .then(data => console.log(data))
//   .catch(error => console.error('Error:', error));

// L'oggetto delle options è facoltativo, o meglio è Javascript più
// avanzato che ci consente di personalizzare la richiesta. Ad esempio,
// possiamo specificare il metodo HTTP da utilizzare:

// 1. method: "GET" - è il default, serve a recuperare dati
// 2. method: "POST" - per inviare dati
// 3. method: "PUT" - per modificare dati
// 4. method: "DELETE" - per eliminare dati

// Ergo per quel che ci serve ora non dobbiamo specificare nulla!

// Ci accontentiamo dell'url.

// Proviamo senza async/await a semplicemente stampare in console
// la risposta ottenuta in caso di successo:
fetch('https://pokeapi.co/api/v2/pokemon/pikachu')
  .then(response => console.log(response));

// Notiamo dalla console che ci sono degli HTTP response status codes:

// classi di risposte:
// 1. Informational Responses (100-199): sono risposte temporanee che
// indicano che la richiesta è stata ricevuta e che il server sta elaborando
// i dati.

// 2. Successful Responses (200-299): indicano che la richiesta è stata
// elaborata con successo e che il server ha restituito i dati richiesti.

// 3. Redirection Responses (300-399): indicano che la richiesta è stata
// reindirizzata a un'altra URL e che il client deve seguire il
// reindirizzamento per ottenere i dati richiesti.

// 4. Client Error Responses (400-499): indicano che c'è stato un errore
// da parte del client, ad esempio una richiesta malformata o un accesso
// non autorizzato. (ECCO CHE FINALMENTE SCOPRIAMO IL FAMOSISSIMO 404)

// 5. Server Error Responses (500-599): indicano che c'è stato un errore
// da parte del server, ad esempio un problema interno o un servizio non 
// disponibile.

// Gestiamo la risposta: sì, stamparla in console ci mostra lo stato, ma
// vogliamo anche della leggibilità in più, quindi una conversione è
// doverosa. Per ora usiamo il metodo .json() per ottenere i dati in un
// formato più gestibile:

fetch('https://pokeapi.co/api/v2/pokemon/pikachu')
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error('Errore riscontrato:', error));

// Possiamo anche accedere a dati particolari chainando le proprietà
// dell'oggetto restituito:

fetch('https://pokeapi.co/api/v2/pokemon/pikachu')
  .then(response => response.json())
  .then(data => {
    console.log(data); // da qui leggo i nomi delle proprietà
    console.log(data.name); // e ci accedo
    console.log(data.abilities); // Pikachu ha due abilità: statico e parafulmine
    console.log(data.weight);
  })
  .catch(error => console.error('Errore riscontrato:', error));

// Se provassi a inserire un nome che non è un Pokémon
fetch('https://pokeapi.co/api/v2/pokemon/Principessa')
  .then(response => console.log(response))
  .then(data => { console.log(data) })
  .catch(error => console.error('Errore riscontrato:', error));

// NON CI ARRIVA ERRORE, BENSI' UNA RISPOSTA CON status 404

// Per farci generare errore consciamente:
fetch('https://pokeapi.co/api/v2/pokemon/Principessa')
  .then(response => {
    if (!response.ok) {
      throw new Error('Hai inserito un nome non di Pokémon');
    }
    return response.json();
  })
  .then(data => {
    console.log(data);
  })
  .catch(error => console.error('Errore riscontrato:', error));