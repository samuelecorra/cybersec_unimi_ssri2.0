## **Lezione 10: Il tag `<head>` in HTML**

### **1. Introduzione**

Il tag `<head>` è la sezione “invisibile” dell’HTML: non appare sullo schermo come il contenuto del `<body>`, ma contiene **tutte le informazioni fondamentali che spiegano al browser, ai motori di ricerca e ai social network come interpretare e presentare la pagina**.

Possiamo immaginarlo come la **scheda tecnica** del documento HTML: contiene i metadati, le istruzioni per la visualizzazione, i collegamenti ai file esterni e le informazioni utili per SEO e social sharing.

Il `<body>` ospita tutto ciò che vedrà l’utente.  
Il `<head>` ospita tutto ciò che _serve_ al browser e ai motori di ricerca per capire cosa stanno guardando.

In questa lezione analizziamo **tutti i tag essenziali** per costruire una `<head>` moderna, pulita e pronta sia per l’uso quotidiano che per migliorare l’accessibilità, la SEO e la condivisione sui social.

---

## **2. Il tag `<meta charset="UTF-8">`**

### **2.1 Cosa significa “charset”**

`charset` indica **il set di caratteri utilizzato nella pagina**.  
`UTF-8` è, oggi, lo standard universale: supporta praticamente tutti i caratteri di tutte le lingue moderne (accenti italiani, lettere europee, simboli internazionali, alfabeti non latini...).

Senza questo tag:

- alcune lettere potrebbero non essere rese correttamente,
    
- caratteri speciali potrebbero comparire come simboli strani (“�”).
    

### **2.2 Perché usiamo sempre UTF-8**

Perché è compatibile con tutto.  
Negli anni passati si usavano charset diversi (ISO-8859-1, Windows-1252…), ma oggi **UTF-8 è la scelta corretta nel 100% dei casi**.

Il tag è questo:

```html
<meta charset="UTF-8">
```

---

## **3. Il meta tag `viewport`**

### **3.1 Cos’è il "viewport"**

Il viewport è **la porzione di schermo in cui la pagina viene visualizzata**.  
Su desktop è larga.  
Su smartphone è stretta.

Senza istruzioni specifiche, i siti nati per desktop appaiono sui telefoni **minuscoli**, costringendo l’utente a zoomare.

### **3.2 Il meta viewport risolve il problema**

Il tag è:

```html
<meta name="viewport" content="width=device-width, initial-scale=1.0">
```

Significa:

- **width=device-width** → “usa la larghezza reale del dispositivo”
    
- **initial-scale=1.0** → “non zoomare di default”
    

È la base del **responsive design** moderno.

E infatti HTML5 lo include già automaticamente nel template di base.

---

## **4. Il tag compatibilità con Internet Explorer / Edge**

Questo tag:

```html
<meta http-equiv="X-UA-Compatible" content="IE=edge">
```

Serve _solo_ per dire ai vecchissimi browser Microsoft **“usa la versione più moderna possibile”**.

Oggi non serve praticamente più, ma HTML5 lo include e puoi tranquillamente lasciarlo lì.

---

## **5. Il tag `<link rel="canonical">`**

### **5.1 A cosa serve**

Serve a indicare ai motori di ricerca **qual è l’URL ufficiale della pagina** quando possono esistere duplicati.

Esempio: un prodotto con più varianti (taglia, colore...) produce URL quasi identici.  
Il canonical dice a Google:

> “Questa è l’URL principale. Tutte le altre sono varianti.”

Esempio base:

```html
<link rel="canonical" href="https://example.com/migliori-scarpe-invernali">
```

### **5.2 Quando serve davvero**

- Siti complessi → sì
    
- Prime pagine HTML → no, ma abituarsi ad usarlo è utile
    
- Blogging / e-commerce → fondamentale
    

È un pilastro della SEO, ma non è necessario capirlo in profondità all’inizio.  
Ti basta sapere **che evita duplicazioni e stabilisce la pagina originale**.

---

## **6. Meta robots (index / noindex — follow / nofollow)**

Il tag:

```html
<meta name="robots" content="index, follow">
```

dice ai bot dei motori di ricerca **come devono comportarsi** sulla pagina.

### **6.1 index / noindex**

- **index** → la pagina può apparire su Google
    
- **noindex** → la pagina _non_ deve apparire su Google
    

Esempi di pagine **da non indicizzare**:

- pagine admin
    
- pannelli di controllo
    
- pagine visibili solo dopo login
    
- risultati temporanei
    

### **6.2 follow / nofollow**

- **follow** → i bot seguono i link presenti nella pagina
    
- **nofollow** → i bot ignorano i link della pagina
    

Un sito tipo Wikipedia è pieno di link:  
i bot li seguono e costruiscono la mappa delle relazioni tra pagine.

Se metti `nofollow`, i bot vedono i link ma **non li esplorano**.

Puoi applicarlo:

- all’intera pagina
    
- a un singolo link
    

Esempio:

```html
<a href="pagina.html" rel="nofollow">Link</a>
```

---

## **7. Favicon (icona del sito)**

È l’icona che compare:

- sulla tab del browser,
    
- nei preferiti,
    
- nelle ricerche mobile.
    

Un esempio:

```html
<link rel="icon" type="image/webp" href="icona.webp">
```

Puoi usare `.png`, `.jpg`, `.svg`, `.ico`, `.webp`.

Una pagina senza favicon appare “vuota” nella tab.

---

## **8. Title, Description, Keywords, Author**

Sono meta tag fondamentali sia per SEO che per chiarezza documentale.

### **8.1 Title**

Compare:

- nella tab del browser,
    
- nei risultati di Google.
    

Es.:

```html
<title>Migliori scarpe per l’inverno</title>
```

### **8.2 Description**

È il testo che compare sotto il titolo nei risultati di ricerca:

```html
<meta name="description" content="Confronto completo delle migliori scarpe per l'inverno.">
```

### **8.3 Author**

Utile per blog, articoli, siti editoriali:

```html
<meta name="author" content="Luca Rossi">
```

### **8.4 Keywords**

Non più fondamentali per Google, ma ancora usati da altri sistemi:

```html
<meta name="keywords" content="scarpe invernali, migliori scarpe inverno, classifica scarpe">
```

Mettili comunque: non fanno danni e arricchiscono i metadati.

---

## **9. Meta per Facebook (Open Graph)**

I social network leggono la pagina quando la condividi.  
Per decidere:

- che immagine mostrare,
    
- che titolo,
    
- che descrizione,
    
- che URL associare.
    

Questi dati vengono dai tag Open Graph (OG).

Esempio:

```html
<meta property="og:type" content="article">
<meta property="og:title" content="Migliori scarpe per l'inverno">
<meta property="og:description" content="La guida definitiva alle scarpe invernali 2025.">
<meta property="og:image" content="https://example.com/images/scarpe.webp">
<meta property="og:url" content="https://example.com/migliori-scarpe-inverno">
<meta property="og:site_name" content="Sito di Prova">
```

Questi tag determinano **l’anteprima che vedi su Facebook, WhatsApp, Telegram, Discord e LinkedIn**.

Se mancano, il social tenta di inventarsi qualcosa… spesso fallendo.

---

## **10. Meta per Twitter**

Twitter usa un formato simile ma con tag specifici:

```html
<meta name="twitter:title" content="Migliori scarpe per l'inverno">
<meta name="twitter:description" content="Guida completa alle scarpe invernali.">
<meta name="twitter:image" content="https://example.com/images/scarpe.webp">
<meta name="twitter:site" content="@sito_ufficiale">
<meta name="twitter:creator" content="@autore">
```

Sono utili per far apparire un’anteprima ottimizzata quando il link viene condiviso.

---

## **11. Collegamento ai CSS esterni**

Il `<head>` ospita anche il collegamento ai fogli di stile:

```html
<link rel="stylesheet" href="style.css">
```

Questo è il modo corretto (e pulito) di strutturare il design rispetto allo stile inline visto negli esempi precedenti.

---

## **12. Collegamento ai file JavaScript**

Per JavaScript non si usa `<link>` ma `<script>`:

```html
<script src="script.js"></script>
```

Oppure puoi scrivere JavaScript direttamente dentro il tag:

```html
<script>
  console.log("ciao");
</script>
```

Nel workflow moderno, di solito il JS viene messo **alla fine del body**, ma tecnicamente può stare anche nell’head.

---

## **13. Conclusione**

Ora hai una visione completa e professionale del tag `<head>`:

- **metadati tecnici** (charset, viewport, compatibilità)
    
- **SEO e indicizzazione** (canonical, index/follow)
    
- **social preview** (Open Graph, Twitter Cards)
    
- **favicon**
    
- **collegamenti esterni** (CSS/JS)
    
- **meta editoriali** (title, description, author, keywords)
    

Questa è la struttura che si usa nei siti moderni, ottimizzati, accessibili e compatibili con tutti i device.

Nella prossima lezione entreremo finalmente nel **body**, partendo dalle **heading** (`<h1>`, `<h2>`, …) e dalla gerarchia dei contenuti.

---

Quando vuoi, passo subito alla lezione successiva.