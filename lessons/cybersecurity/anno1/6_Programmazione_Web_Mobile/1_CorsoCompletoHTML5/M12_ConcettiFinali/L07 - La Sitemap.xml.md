
# **Lezione 7: La Sitemap.xml**

È arrivato il momento di creare il secondo dei tre file fondamentali per portare un sito online: **la sitemap.xml**.

Nel video introduttivo avevamo già visto la teoria: una sitemap è, letteralmente, **la mappa del sito**.  
Serve ai motori di ricerca per capire:

- quali pagine esistono
    
- quali sono nuove
    
- quali sono state modificate
    
- quali non devono essere scansionate due volte
    
- come è organizzato il sito
    

Ed è uno strumento _critico_ per l’indicizzazione corretta.

La parte bella?  
Non dobbiamo scriverla a mano.  
La generiamo in automatico.

---

## **Generiamo la sitemap in 10 secondi**

Vai su Google e cerca:

```
sitemap generator
```

Prendi il **primo risultato** (quello più usato e affidabile).  
Questo generatore funziona così:

1. prendi l’URL del tuo sito
    
2. incolli solo il dominio, tipo:
    

```
https://sitoesempio1.altervista.org
```

3. premi **Start**
    
4. aspetti 3–4 secondi
    

Ed è già tutto pronto.

Il tool scansiona il sito, individua tutte le pagine collegate e costruisce l’albero completo.

Nel nostro caso ha trovato **5 URL**:

- index.html
    
- about.html
    
- blog.html
    
- contatti.html
    
- articolo.html
    

Esattamente ciò che avevamo creato.

---

## **Scarichiamo e posizioniamo la Sitemap**

Nel tool clicca **Download Sitemap**.  
Ti scarica un file chiamato:

```
sitemap.xml
```

Ora:

1. lo sposti nella tua cartella del progetto
    
2. tramite FileZilla lo trascini in `public_html`
    
3. controlli che sia raggiungibile da browser:
    

```
https://sitoesempio1.altervista.org/sitemap.xml
```

Se la vedi, significa che è caricata correttamente.

---

## **Analizziamo la sitemap.xml riga per riga**

Apriamo il file: è scritto in **XML**, un linguaggio molto simile all’HTML come sintassi (tag di apertura e chiusura), ma con uno scopo completamente diverso: **trasportare dati**.

Vediamo la struttura.

---

### **1. Header XML**

```
<?xml version="1.0" encoding="UTF-8"?>
```

Come per l’HTML ha un “doctype”, qui abbiamo una dichiarazione XML.  
Indica versione e codifica dei caratteri.

---

### **2. Il tag `<urlset>`**

È una specie di contenitore globale.

```
<urlset xmlns="https://www.sitemaps.org/schemas/sitemap/0.9">
```

`xmlns` indica lo “schema” da cui prende le regole di formattazione.  
Non devi impararlo: serve solo ai parser dei motori di ricerca.

All’interno di `<urlset>` ci saranno tutti i nostri URL.

---

### **3. Ogni pagina è strutturata così**

```
<url>
  <loc>https://sitoesempio1.altervista.org/index.html</loc>
  <lastmod>2024-01-20T10:00:00+00:00</lastmod>
  <priority>1.00</priority>
</url>
```

Vediamole una per una.

#### **`<loc>` — location**

L’URL assoluto della pagina.

#### **`<lastmod>` — last modification**

Data dell’ultima modifica (utile ai crawler per capire se devono riscaricare la pagina).

#### **`<priority>`**

Un valore da **0.0 a 1.0** che indica l’importanza della pagina nel sito.

- Home → **1.0**
    
- Pagine principali → **0.8**
    
- Sottopagine → **0.6 / 0.5**
    

Non è SEO “magico”: è solo un suggerimento.

Ecco perché il nostro file mostra:

- index → 1.0
    
- about, blog, contatti → 0.80
    
- articolo (sottopagina del blog) → 0.64
    

Tutto perfettamente coerente.

---

## **Aggiorniamo robots.txt con la sitemap**

I motori fanno questo ordine:

1. leggono `robots.txt`
    
2. se trovano la sitemap → la visitano subito
    
3. poi analizzano le pagine
    

Apriamo il nostro robots.txt e aggiungiamo:

```
Sitemap: https://sitoesempio1.altervista.org/sitemap.xml
```

Come si fa?

1. Torna sul generatore di robots.txt
    
2. Vai su **Customize**
    
3. Aggiungi la sitemap
    
4. Rigenera
    
5. Trascina il nuovo file in FileZilla
    

Adesso il file conterrà:

```
User-agent: *
Allow: /
Sitemap: https://sitoesempio1.altervista.org/sitemap.xml
```

Perfetto.

---

## **Il passo successivo (non indispensabile, ma consigliato)**

Se volessi indicizzare seriamente questo sito, dovresti registrarlo su:

- **Google Search Console**
    
- **Bing Webmaster Tools**
    

e inviare manualmente la sitemap, così Google la vede subito.  
Ma per un sito di prova non serve: i crawler arriveranno da soli.

---

## **Ricapitolazione**

- La sitemap serve ai motori per scansionare il sito in modo intelligente.
    
- La generiamo in automatico con un tool.
    
- La carichiamo nella root del sito.
    
- La colleghiamo nel robots.txt.
    
- Ora il nostro sito è perfettamente mappato e pronto per l’indicizzazione.
    

---

**Perfetto, anche la sitemap è fatta.**  
Ci manca solo l’ultimo file: **.htaccess**, fondamentale per redirect, cache, compressione e tante altre cose.