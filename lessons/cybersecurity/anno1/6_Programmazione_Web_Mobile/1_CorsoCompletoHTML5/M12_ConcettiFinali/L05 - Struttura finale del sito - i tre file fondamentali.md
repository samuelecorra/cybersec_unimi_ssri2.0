# **Lezione 5: Struttura finale del sito: i tre file fondamentali (robots.txt, sitemap.xml, .htaccess)**

Con il nostro brutto ma onestissimo sito finalmente online, è il momento di parlare della **struttura finale reale** di un sito web.  
Finora abbiamo lavorato con:

- le pagine HTML
    
- la cartella degli asset
    
- la versione minificata
    

…ma manca ancora **un pezzo gigantesco del puzzle**:  
i tre file che tutti i siti veri devono avere, e che chiudono ufficialmente il cerchio del corso.

Questi tre file sono:

1. **`.htaccess`**
    
2. **`sitemap.xml`**
    
3. **`robots.txt`**
    

Li vediamo uno alla volta, in modo ultra-chiaro, così capisci esattamente _a cosa servono_ senza entrare in profondità da professionisti (arriverà nei corsi di hosting avanzato).

---

## **1. `.htaccess` — Il guardiano del server**

Il file `.htaccess` è un file **potentissimo**.  
A differenza dell’HTML, questo non è un file per il browser: è un file che parla direttamente al **server**.

Con `.htaccess` possiamo:

- impostare i **redirect** (es. /home → /index.html)
    
- attivare il **caching** delle immagini, dei CSS, del JS
    
- attivare la **compressione GZIP**
    
- proteggere cartelle
    
- creare URL puliti e “belli”
    
- gestire CORS, sicurezza, header vari
    

È letteralmente la **console grafica del server Apache in forma di file**.

Un esempio reale:

```htaccess
<IfModule mod_expires.c>
  ExpiresActive On
  ExpiresByType image/jpeg "access plus 1 year"
  ExpiresByType image/png  "access plus 1 year"
  ExpiresByType text/css   "access plus 1 month"
  ExpiresByType text/html  "access plus 1 day"
</IfModule>
```

Questo dice al browser:

- “le immagini tienile in cache **per un anno**”
    
- “i CSS per **un mese**”
    
- “l’HTML solo per **un giorno**”
    

→ Risultato:  
il sito diventa **molto più veloce** perché il browser non riscarica sempre tutto.

GZIP invece comprime ulteriormente i file prima di inviarli, tipo ZIP/RAR, così:

- si scaricano più velocemente
    
- il sito percepito diventa più reattivo
    
- Lighthouse ti segna un sacco di punti in più
    

Nel corso HTML non possiamo entrare in tutto questo, perché `.htaccess` è materia da hosting/server, non da markup.  
Ma ora sai **che esiste** e **perché è fondamentale**.

---

## **2. `sitemap.xml` — La mappa del sito per Google**

La sitemap è un file XML che contiene **l’elenco completo di tutte le pagine del tuo sito**, più un’informazione chiave:

- la **data dell’ultima modifica** (`<lastmod>`)
    

Il suo scopo è semplicissimo:

> Aiutare Google e tutti gli altri motori di ricerca a capire  
> _quali pagine esistono_ e _quali pagine sono state modificate_  
> senza dover riscrutare tutto il sito ogni volta.

Ecco un estratto reale:

```xml
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
  <url>
    <loc>https://miosito.it/</loc>
    <lastmod>2024-03-21</lastmod>
  </url>
  <url>
    <loc>https://miosito.it/blog.html</loc>
    <lastmod>2024-03-21</lastmod>
  </url>
</urlset>
```

Perché serve?

- Un crawler (“robot” dei motori di ricerca) arriva sul tuo sito.
    
- Controlla soltanto la sitemap.
    
- Se vede una pagina nuova → la indicizza.
    
- Se vede una pagina modificata → la aggiorna.
    
- Tutto il resto lo ignora.
    

Risparmio di tempo per Google → miglior ranking → scansione più efficiente.

È come l’appello della mattina:  
il prof vede subito **chi è presente** e **chi è nuovo** senza rifare tutto da zero.

---

## **3. `robots.txt` — Le regole per i crawler**

Se la sitemap è la mappa, `robots.txt` è il **portinaio**.

Sta sempre nella root:

```
/robots.txt
```

Serve per dire ai vari bot/crawler:

- chi può entrare
    
- chi non può entrare
    
- quali cartelle possono vedere
    
- quali cartelle devono ignorare
    
- dove si trova la sitemap
    

Un esempio reale:

```
User-agent: *
Allow: /

Sitemap: https://miosito.it/sitemap.xml
```

Significa:

> “Tutti i bot possono entrare e vedere tutto.  
> Ecco la sitemap.”

Un altro esempio:

```
User-agent: Googlebot
Disallow: /bozze/
Disallow: /privato/

User-agent: *
Allow: /

Sitemap: https://miosito.it/sitemap.xml
```

Qui stai dicendo:

- a Google: “non guardare _bozze_ e _privato_”
    
- a tutti gli altri bot: “potete entrare pure ovunque”
    

Questa distinzione è utilissima quando hai:

- cartelle con materiale non pubblicabile
    
- pagine di test
    
- sezioni di admin
    
- roba che vuoi oscurare ai motori
    

Non garantisce la privacy (un bot maleducato può fregarsene), ma i motori seri come Google lo rispettano al 100%.

---

# **Conclusione del modulo**

Hai appena visto gli ultimi tre elementi che completano un sito web “vero”:

- `.htaccess` → lato server (caching, compressione, redirect)
    
- `sitemap.xml` → lato SEO/crawler (mappa del sito)
    
- `robots.txt` → lato regole (chi entra / chi non entra)
    

Questi tre file li approfondiamo nei **prossimi tre video**, uno dedicato a ciascuno, così concludiamo il corso con tutto ciò che serve per:

- creare un sito
    
- organizzarlo
    
- minificarlo
    
- pubblicarlo
    
- farlo leggere correttamente ai motori di ricerca
    
- ottimizzarlo per velocità e indicizzazione
    

