# **Creazione del file `robots.txt`**

Arriviamo finalmente alla creazione del nostro file **robots.txt**.

Siamo ancora nel contesto dei concetti finali del modulo: è tutto orientato a prendere il nostro sitarello HTML e metterlo **online come si deve**, con una struttura da sito vero.  
E uno dei file essenziali è proprio _robots.txt_.

In teoria potremmo leggerci tutta la documentazione ufficiale di Google (che è ottima, se un giorno vorrai approfondire seriamente SEO tecnica).  
Ma per ora non ci serve assolutamente passare per quello.  
Noi vogliamo semplicemente:

- creare un file valido
    
- aggiungere le regole minime
    
- capire _a cosa serve_, _come funziona_
    
- metterlo sul sito online
    

E per farlo, la cosa più sensata è usare un **generatore**.

---

## **Usiamo un generatore di robots.txt (molto più pratico)**

Vai su Google e cerca:

```
robots txt generator
```

Prendi il **secondo risultato**, quello con l’interfaccia più pulita — è quello che uso anch’io e che funziona meglio per iniziare.

Il concetto è semplice:  
invece di scrivere a mano tutte le regole, compiliamo un modulo e lui genera il robots.txt per noi.

---

## **Capire lo scenario: cosa deve fare il nostro robots.txt**

Il nostro sito, se ci pensi un attimo, **non ha nulla di segreto**:

- quattro pagine HTML
    
- la cartella degli asset
    
- qualche immagine
    
- zero sezioni private
    
- nessun login
    
- nessun backend
    

Quindi possiamo tranquillamente dire:

> “Bot, potete vedere tutto”.

L’opzione che ci serve è **Allow all**.

Tradotto:  
tutti gli user-agent possono accedere a tutte le cartelle del sito.

Perché lo facciamo?

- Non abbiamo niente da nascondere
    
- Non esistono cartelle private
    
- Non c’è alcun rischio
    
- Le nostre pages devono essere scansionate liberamente
    

Volendo, potremmo anche disabilitare la scansione dei file CSS e JS.  
Molti siti lo fanno per “pulizia”… ma per noi non cambia niente.

Quindi: **Allow all** e via.

---

## **Cos’è “user-agent” e perché c’è l’asterisco**

Gli user-agent sono i **crawler**:  
Googlebot, Bingbot, DuckDuckBot, YandexBot, ecc.

Quando metti:

```
User-agent: *
```

l’asterisco (“wildcard”) significa letteralmente:

> “questa regola vale per tutti”.

È come dire “tutti i bot, nessuno escluso”.

---

## **Generiamo il file**

1. Nel generatore seleziona **Allow all**
    
2. Spunta “I understand” (è solo per responsabilità)
    
3. Clicca **Create**
    

Otterrai qualcosa del genere:

```
User-agent: *
Allow: /
```

Questo è esattamente ciò che vogliamo:  
tutti i bot possono visitare tutto, a partire dalla root `/`.

Volendo puoi anche crearne uno più complesso:

- “Disallow all” (utile per siti in costruzione)
    
- Regole personalizzate per Googlebot, Bingbot, ecc.
    
- Accesso limitato a cartelle specifiche
    
- Aggiunta della sitemap
    

Ma per ora non ci serve.

---

## **Dove mettiamo il file?**

Il robots.txt _deve_ essere nella **root del sito**, cioè:

```
/robots.txt
```

Nel nostro caso, significa caricarlo nella stessa cartella dove vive `index.html` lato hosting.  
Quindi:

1. Generi robots.txt
    
2. Lo trascini in FileZilla dentro `public_html`
    
3. Ricarichi la pagina di AlterVista
    
4. Provi a visitare:
    

```
https://tuosito.altervista.org/robots.txt
```

E se tutto è andato bene, lo vedi subito.

---

## **Perché il robots.txt è così importante?**

Perché tutti i crawler — Google, Bing, Yahoo, ecc. — **leggono SEMPRE prima questo file**.

È letteralmente la prima cosa che visitano quando arrivano sul tuo sito.

Tu stai dicendo loro:

- “Cosa puoi vedere”
    
- “Cosa non puoi vedere”
    
- “Dove si trova la sitemap”
    
- “Quali cartelle devono restare private”
    

Non dà protezione assoluta (un bot “maleducato” può ignorarlo),  
ma i motori di ricerca seri lo rispettano al 100%.

---

## **Situazioni reali in cui userai robots.txt**

1. **Sito in costruzione → Disallow all**
    
2. **Cartella /admin da nascondere → Disallow /admin**
    
3. **Blog con sitemap nuova → Aggiungi sitemap**
    
4. **Limitare Googlebot Images a una sola cartella → Disallow /immaginiprivate**
    
5. **Bloccare PDF non indicizzabili**
    

Per ora non entriamo nei dettagli SEO.  
L’importante è capire la logica.

---

## **Ricapitolazione (versione super breve)**

- `robots.txt` serve per dire ai bot “cosa possono fare sul tuo sito”.
    
- Va SEMPRE posizionato nella root.
    
- Per siti semplici → **Allow all** è perfetto.
    
- Generarlo con uno strumento online è la scelta più intelligente.
    
- I bot lo leggono prima ancora della sitemap.
    

---

Perfetto: **robots.txt completato e caricato online**.

Ora siamo pronti per la lezione successiva:  
**creazione della sitemap.xml**, che è la seconda “colonna portante” dell’indicizzazione.