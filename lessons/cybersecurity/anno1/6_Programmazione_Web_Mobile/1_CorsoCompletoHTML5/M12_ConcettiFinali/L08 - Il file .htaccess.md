# **Lezione 8: Il file `.htaccess`**

Eccoci tornati per l’ultimo file della nostra triade d’oro: il **file `.htaccess`**.

È un file potentissimo.  
Serve per:

- gestire redirect permanenti e temporanei
    
- controllare la cache di immagini, CSS e JavaScript
    
- bloccare hotlinking e accessi indesiderati
    
- impostare pagine di errore personalizzate
    
- attivare compressione GZip / Brotli
    
- proteggere cartelle con password
    
- gestire IP consentiti o esclusi
    

…e molto altro.

È un file fondamentale per qualsiasi sito hostato su server Apache (Altervista lo usa, quindi ci riguarda al 100%).

La buona notizia?  
**Non lo scriviamo a mano.**  
Usiamo un generatore.

---

## **1. Usare un generatore di `.htaccess`**

Apri Google e cerca:

```
htaccess generator
```

Vanno bene tutti, ma nel video viene usato:

- **htaccessredirect.net**
    
- **bwssystems.com / generate-htaccess**
    

Apriamone uno: qualsiasi basta, perché tutti generano codice standard.

Sulla sinistra trovi una serie di sezioni: redirect, caching, hotlink prevention, protezioni, error pages…  
Ogni categoria ha la sua descrizione, molto chiara.

---

# **A) Redirect (301 e 302)**

### **Perché servono**

Immagina di aver rinominato una pagina:

```
/blogg.html
→ diventa
/blog.html
```

Ma Google ha ancora in memoria la pagina vecchia.  
Se qualcuno clicca su un vecchio link, trova un 404.

Con un redirect 301 dici:

> “Se qualcuno cerca /blogg.html, mandalo automaticamente su /blog.html”.

Questo evita errori, mantiene la SEO e la qualità del sito.

Esempio di regola:

```
Redirect 301 /blogg.html /blog.html
```

Noi non lo useremo ora, ma devi sapere cosa significa.

---

# **B) Cache dei file statici**

Questa è la parte più importante per noi.  
Serve per dire al browser:

> “Non riscaricare ogni volta le immagini, tienile in memoria per un po’”.

Il generatore crea automaticamente qualcosa tipo:

```
<FilesMatch "\.(jpg|jpeg|png|webp|gif|css|js)$">
  Header set Cache-Control "max-age=2592000, public"
</FilesMatch>
```

Significa:  
Tutte le immagini, CSS e JS → tienile in cache per **30 giorni**.

Il browser le salva localmente.  
Risultato: il sito diventa molto più veloce.

Nel generatore:

- selezioni JPG, PNG, WEBP
    
- puoi aggiungere CSS, JS
    
- setti “1 month”
    
- metti “public”
    
- generi il codice
    

---

# **C) Prevenire l’hotlinking**

L’hotlinking succede quando un altro sito punta direttamente alle tue immagini:

```
<img src="https://ilmiosito.it/assets/immagini/profilo.png">
```

Così il _tuo_ server paga la banda per _i loro_ visitatori.

L’htaccess può bloccarli:

```
RewriteEngine on
RewriteCond %{HTTP_REFERER} !^$
RewriteCond %{HTTP_REFERER} !^https://ilmiosito\.altervista\.org/ [NC]
RewriteRule \.(jpg|jpeg|png|gif|webp)$ https://ilmiosito.altervista.org/assets/images/stronzo.png [R,NC]
```

(La parte dell’immagine “stronzo.png” è volutamente comica nel video, giusto per far capire la meccanica del meccanismo: _se mi rubi le immagini ti mostro un meme di risposta_.)

Non serve ora, ma il concetto è chiaro.

---

# **D) Pagine di errore personalizzate**

Il 404 di default di Altervista è bruttissimo.

Vogliamo una pagina personalizzata.

Creiamo:

```
404.html
```

Contenuto semplice:

```html
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>404</title>
</head>
<body>
  <h1>Errore 404</h1>
  <p>Mangiati un panino e torna alla <a href="/">home page</a>.</p>
</body>
</html>
```

La carichiamo via FileZilla.

Ora nel generatore `.htaccess`:

```
ErrorDocument 404 /404.html
```

Scarichiamo il file e lo mettiamo nella root.

Se ora cerchiamo una pagina inesistente, tipo:

```
/blogggg.html
```

→ vediamo la nostra pagina di errore personalizzata.

Perfetto.

---

# **E) Upload finale del `.htaccess`**

Una volta generato:

1. crea un file chiamato **`.htaccess`** (attenzione al punto!)
    
2. incolla tutto il codice generato
    
3. salva
    
4. trascina in FileZilla nella root del sito (`public_html`)
    

Testiamo:

- prova un URL sbagliato → appare la pagina 404
    
- apri Lighthouse → vedrai che ora hai cache attiva, compressione, redirect corretti
    

Funziona tutto.

---

# **F) Verifica finale**

Riepilogando:

- **robots.txt** → dice ai bot cosa possono vedere
    
- **sitemap.xml** → mappa tutte le pagine del sito
    
- **.htaccess** → velocità, redirect, protezioni, errori personalizzati
    

È la base assoluta di qualsiasi sito anche minimamente professionale.

Ora il nostro sito:

- è online
    
- è leggibile dai bot
    
- è indicizzabile
    
- è veloce
    
- è sicuro
    
- ha una gestione corretta degli errori
    
- ha tutte le ottimizzazioni base di un sito moderno
    

“Brutto” graficamente… ma strutturalmente perfetto.

---

# **Prossima lezione**

Come anticipato nel video:  
si passa alle **performance di immagini, video e media**, e poi al concetto di **CDN**, che chiude ufficialmente il corso HTML.