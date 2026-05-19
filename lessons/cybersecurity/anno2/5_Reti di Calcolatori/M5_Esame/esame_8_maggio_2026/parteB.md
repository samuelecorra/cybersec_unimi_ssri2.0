# Esame di Reti di Calcolatori

**8-5-2026- Prof. Ernesto. Damiani**

## COMPITO B

_Potete consultare libri e appunti. Scrivete nome e numero di matricola su tutti i fogli che consegnate, altrimenti non saranno presi in considerazione._

---

**Esercizio 1 (12 punti)** Una compagnia aerea low cost vuole gestire il wi-fi di bordo. Non sono previsti pagamenti: durante il volo, alla rete dell'aeromobile possono accedere solo viaggiatori in possesso di un token d'autenticazione rilasciato dal programma fedeltà della compagnia. Collegandosi alla rete, i client ricevono un indirizzo IP privato tramite DHCP; la loro configurazione comprende l'indirizzo IP – sempre privato – del sistema proxy. Scrivete lo pseudo-codice del proxy basato su socket TCP che (i) quando un client inizia la connessione controlla se il suo indirizzo è presente in un file CONNECTED. Se non risulta presente, il proxy raccoglie username e password dell'utente, si collega al server remoto del programma fedeltà per ricevere un token (ii) controlla il token ricevuto (ipotizzate di usare una chiamata di libreria) e se il controllo è ok salva l'indirizzo del client nel file CONNECTED. Un processo separato del proxy (di cui non dovete occuparvi) eseguirà poi le connessioni verso Internet per conto dei client CONNECTED. ATTENZIONE: È vostra responsabilità leggere dal socket l'indirizzo privato dei client.

---

**Esercizio 2 (8 punti)**

Considerate la seguente richiesta http:

```
GET /sesar/index.html HTTP/1.1
Host: di.unimi.it
User agent: Mozilla/5.0 (Macintosh; U; PPC Mac OS X; en)
Accept: ext/xml, application/xml, application/xhtml+xml, text/html;q=0.9,
text/plain;q=0.8, image/png,*,*;q=0.5
Accept: it
Keep-Alive: 200
Connection: keep-alive
```

Rispondete alle seguenti domande:

1. Qual è l'URL richiesto?
2. Cosa significano i parametri q=0.8 e q=0.5?
3. Il browser richiede una connessione persistente o non persistente?
4. Qual è il tipo (e la versione) di browser utilizzato dal client nella richiesta HTTP?

---

**Esercizio 3 (6 punti)** Un browser deve scaricare una pagina Web che contiene 4 immagini, due delle quali sono memorizzate sullo stesso server e due su un server diverso. MOSTRATE esplicitamente le richieste HTTP generate dal browser e le risposte dei server. Considerate sia l'utilizzo di HTTP 1.0 che 1.1.

---

**Esercizio 4 (4 punti)** Siete incaricati di impostare il firewall per la rete di bordo dell'Esercizio 1. Scrivete la regola in base alla quale solo l'indirizzo IP del proxy può avere connessioni TCP con l'esterno, mentre UDP è disabilitato. Specificate la regola da utilizzare.
