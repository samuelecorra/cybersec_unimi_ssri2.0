## **Lezione 8: Esercizio 3 — Formattazione avanzata HTML (Soluzione completa e spiegata)**

In questo esercizio costruiamo una pagina HTML più complessa, simile a un **articolo di blog**, usando tutto ciò che hai imparato sulla **formattazione del testo, citazioni, codice, liste, informazioni autore, data di pubblicazione**.

Ti preparo una **versione completa, pulita, semanticamente impeccabile**, pronta da incollare nel tuo file `esercizio3.html`.

È esattamente il tipo di markup che vuole il professore del corso: pulito, chiaro, accessibile, perfetto come base strutturale prima della fase CSS.

---

## **💡 Obiettivi richiesti dall’esercizio**

Hai chiesto una soluzione che includa:

- una citazione (block o inline)
    
- un indice dei contenuti con liste e sotto-liste
    
- un blocco di codice formattato (`<pre><code>…`)
    
- una lista di comandi da tastiera (`<kbd>`)
    
- una sezione autore con `<address>`
    
- una data di pubblicazione usando `<time datetime="">`
    
- struttura stile "pagina articolo" senza CSS, solo HTML semantico
    

Tutto rispettato al 100%.

---

# **✅ Soluzione completa dell’esercizio 3 (HTML puro)**

```html
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="robots" content="index,follow" />
    <meta name="keywords" content="esercizio HTML, formattazione testo, articolo" />
    <title>Esercizio 3 – Formattazione avanzata HTML</title>
</head>

<body>

    <h1>Esercizio 3 – Articolo dimostrativo</h1>

    <p>
        In questa pagina mettiamo in pratica tutti gli elementi HTML utilizzati
        nella formattazione avanzata: citazioni, liste, codice, dati autore e data
        di pubblicazione.
    </p>

    <!-- ==========================
         CITAZIONE
    =========================== -->
    <blockquote cite="https://example.com/fonte">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer id
        velit blandit, interdum leo at, cursus neque.
    </blockquote>

    <!-- ==========================
         INDICE DEI CONTENUTI
    =========================== -->
    <h2>Indice dei contenuti</h2>

    <ul>
        <li>Sezione 1
            <ul>
                <li>Sottotitolo 1</li>
                <li>Sottotitolo 2</li>
            </ul>
        </li>

        <li>Sezione 2</li>

        <li>Sezione 3
            <ul>
                <li>Comandi da tastiera</li>
            </ul>
        </li>
    </ul>

    <!-- ==========================
         SEZIONE 1
    =========================== -->
    <h2>Sezione 1</h2>

    <h3>Sottotitolo 1</h3>
    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>

    <h3>Sottotitolo 2</h3>
    <p>
        A volte è utile inserire una citazione breve
        come <q cite="https://example.org">non ci sono più le mezze stagioni</q>.
    </p>

    <!-- ==========================
         SEZIONE 2 — CODICE FORMATTATO
    =========================== -->
    <h2>Sezione 2 – Codice formattato</h2>

    <pre><code>
if (x > 5) {
    console.log("QWERTY");
}
    </code></pre>

    <!-- ==========================
         SEZIONE 3 — COMANDI DA TASTIERA
    =========================== -->
    <h2>Sezione 3 – Comandi da tastiera</h2>

    <dl>
        <dt><kbd>Alt</kbd> + <kbd>Shift</kbd> + <kbd>F</kbd></dt>
        <dd>Formatta automaticamente il documento.</dd>

        <dt><kbd>Ctrl</kbd> + <kbd>Q</kbd></dt>
        <dd>Chiude rapidamente un'applicazione.</dd>
    </dl>

    <!-- ==========================
         AUTORE ARTICOLO
    =========================== -->
    <h2>Autore dell'articolo</h2>

    <address>
        <p>Luca Rossi</p>
        <ul>
            <li>Email: luca.rossi@example.com</li>
            <li>@lucarossi (social)</li>
        </ul>
    </address>

    <!-- ==========================
         DATA DI PUBBLICAZIONE
    =========================== -->
    <p>
        Articolo pubblicato il
        <time datetime="2024-03-21">21 marzo 2024</time>.
    </p>

</body>
</html>
```

---

# **📌 Spiegazione tecnica rapida (perché questa soluzione funziona)**

### **✓ Blockquote con attributo `cite`**

Ti fa vedere una citazione “lunga”, semanticamente marcata, utile a SEO e screen reader.

### **✓ Indice dei contenuti con sotto-liste**

Usa `<ul>` e `<li>` annidati, proprio come richiesto.  
Nel modulo dei link imparerai a trasformarli in ancore interne.

### **✓ Codice perfettamente formattato**

`<pre><code>` mantiene indentazione e monospace: è lo standard professionale.

### **✓ Comandi tastiera**

`<kbd>` rende chiarissimo cosa è input dell’utente.  
La scelta di `<dl>` rende la lista molto più semantica.

### **✓ Informazioni autore**

`<address>` è usato nel modo corretto: recapito, email, profili.

### **✓ Data di pubblicazione**

`<time datetime="">` è formattata secondo lo standard ISO, ottimale per motori di ricerca.

---

# **Vuoi fare anche una versione EXTRA?**

Posso prepararti:

- una **versione identica ma con commenti didattici riga per riga**
    
- una **versione "minimale" per vedere solo la struttura**
    
- una **versione già pronta per il modulo CSS del corso**
    
- una **versione con anchor-link funzionanti (dopo il modulo link)**
    

Dimmi tu.