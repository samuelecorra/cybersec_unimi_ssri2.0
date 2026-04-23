## **Lezione 2: Testo dei link – buone e cattive pratiche**

In questa lezione non cambiamo _la sintassi_ del link (è sempre `<a href="...">...</a>`), ma ci concentriamo su **che cosa scrivere dentro** il link.

L’idea chiave è:

> Il testo del link deve dire **dove stai andando** e **che cosa troverai**, non “clicca qui”.

Questo è fondamentale sia per:

- chi legge la pagina (leggibilità, usabilità, accessibilità),
    
- sia per i motori di ricerca (SEO, reputazione del sito e delle singole pagine).
    

---

## **1. Ripasso: struttura tecnica del link**

Struttura base:

```html
<a href="https://www.google.com">Testo del link</a>
```

- `href="..."` → **destinazione** (URL, pagina, frammento, ecc.).
    
- tra `<a>` e `</a>` → **testo cliccabile**.
    

Questa lezione si occupa **solo** di quella parte: `Testo del link`.

---

## **2. Cose da evitare nel testo del link**

### **2.1. Evita “clicca qui”, “premi qui”, “scopri di più qui”**

Esempi da **non** usare:

```html
<a href="https://www.google.com">Clicca qui</a>
```

```html
<a href="prodotti.html">Scopri di più qui</a>
```

Perché è sbagliato:

- è **inutile**: si vede già che è un link (colore, underline, ecc.);
    
- è **poco informativo**: non dice _cosa_ succede quando clicchi;
    
- per gli **screen reader** (che leggono una lista di link) “clicca qui”, “clicca qui”, “clicca qui” è completamente inutile;
    
- per i **motori di ricerca**, “clicca qui” non dà nessuna informazione sul contenuto della pagina di destinazione.
    

---

### **2.2. Evita di mettere l’URL “grezzo” come testo del link**

Esempio da evitare:

```html
<a href="https://www.google.com">https://www.google.com</a>
```

oppure

```html
<a href="https://www.google.com">https://www.google.com/search?q=scarpe+da+corsa</a>
```

Perché è una cattiva idea:

- è **brutto da leggere** (soprattutto con query lunghe e parametri);
    
- non aiuta a capire il **contenuto** della pagina, solo l’indirizzo;
    
- se già stai linkando `https://www.google.com`, non ha senso ripeterlo tal quale nel testo.
    

Se proprio vuoi usare il dominio, è molto meglio:

```html
<a href="https://www.google.com">Google</a>
```

o al massimo:

```html
<a href="https://www.google.com">Google.com</a>
```

---

## **3. Come scrivere un buon testo di link**

### **3.1. Deve descrivere la destinazione**

Il testo del link dovrebbe dire **cosa c’è nella pagina di arrivo**.

Esempi corretti:

```html
<a href="https://www.google.com">
  Scopri di più su Google
</a>
```

```html
<a href="download-chrome.html">
  Download di Google Chrome
</a>
```

```html
<a href="prodotti.html">
  Scopri di più sui nostri prodotti
</a>
```

In tutti questi casi, chi legge capisce:

- che sta andando su **Google**;
    
- che sta andando alla pagina di **download di Chrome**;
    
- che sta andando alla pagina dei **prodotti**.
    

---

### **3.2. Deve essere conciso, ma non criptico**

Obiettivo: **2–3 parole**, massimo una breve espressione, ma **significative**.

Esempi sani:

- `Scopri di più su Google`
    
- `Scarica l’app`
    
- `Scarpe da corsa migliori`
    
- `Pagina prodotti`
    
- `Documentazione ufficiale`
    

Esempi esagerati (da evitare):

```html
<a href="scarpe-corsa.html">
  Nel 2024 le migliori scarpe da corsa sono considerate...
</a>
```

Qui il link è tutta la frase: è **troppo lungo** e confonde.

Meglio:

```html
Nel 2024 le <a href="scarpe-corsa.html">migliori scarpe da corsa</a> sono considerate...
```

---

## **4. Esempi pratici su testo normale**

Immagina un paragrafo:

> Nel 1989 c’è stata la caduta del muro di Berlino.

### **4.1. Link generico al tema “muro di Berlino”**

Se la pagina di destinazione parla **in generale** del muro di Berlino (storia del muro, costruzione, caduta, contesto):

```html
Nel 1989 c’è stata la caduta del
<a href="muro-di-berlino.html">muro di Berlino</a>.
```

Qui il link è **“muro di Berlino”**, che è esattamente il tema della pagina.

---

### **4.2. Link specifico alla “caduta del muro di Berlino”**

Se la pagina di destinazione è **specifica** sulla caduta (evento storico, data, conseguenze):

```html
Nel 1989 c’è stata la
<a href="caduta-muro-di-berlino.html">caduta del muro di Berlino</a>.
```

Qui il link ingloba “caduta del muro di Berlino” perché quella pagina parla esattamente di **quell’evento**.

---

### **4.3. Esempi da evitare**

Questa forma è confusa:

```html
<a href="caduta-muro-di-berlino.html">
  Nel 1989 c’è stata la caduta del muro di Berlino
</a>
```

- il link è tutta la frase → troppo lungo;
    
- in un elenco di link, non si capisce rapidamente di cosa parli;
    
- la parte informativa del link è solo “caduta del muro di Berlino”.
    

---

## **5. Wikipedia come modello mentale**

Wikipedia è un ottimo laboratorio per capire **come si usano i link**:

- link su **nomi di persone**: `Enrico VIII`
    
- link su **paesi**: `Francia`, `Svizzera`, `Austria`
    
- link su **concetti ben definiti**: `Repubblica parlamentare`, `Presidente del Consiglio dei Ministri`
    

Di solito:

- il link è sul **nome del concetto** (non sull’intera frase),
    
- ogni link porta a una pagina che spiega **esattamente** quel concetto.
    

Esempio:

```html
Il sistema politico italiano è una
<a href="repubblica-parlamentare.html">repubblica parlamentare</a>.
```

Non:

```html
<a href="repubblica-parlamentare.html">
  Il sistema politico italiano è una repubblica parlamentare
</a>
```

---

## **6. Legame con la SEO (senza entrare nei tecnicismi)**

Senza entrare in formule SEO, l’idea è questa:

- Ogni volta che **un altro sito** linka una tua pagina, sta mandando un “segnale” ai motori di ricerca.
    
- Il **testo del link** (anchor text) dice:  
    “questa pagina è rilevante per **queste parole**”.
    

Esempi:

1. Se tanti siti autorevoli linkano:
    
    ```html
    <a href="https://ilmiosito.it">Il Mio Sito</a>
    ```
    
    → i motori di ricerca associano il tuo dominio al **tuo brand** (“Il Mio Sito”).
    
2. Se tanti siti autorevoli linkano:
    
    ```html
    <a href="https://ilmiosito.it/scarpe-corsa">
      migliori scarpe da corsa
    </a>
    ```
    
    → i motori di ricerca associano quella pagina alla query **“migliori scarpe da corsa”**.
    

L’idea pratica per te:

- se scrivi tu i link (sul tuo sito o quando citi altri siti),  
    cerca di usare **anchor text significativi**:
    
    - `il mio sito`
        
    - `migliori scarpe da corsa`
        
    - `guida al linguaggio C`
        
    - ecc.
        

Evita di buttare link su frasi intere o su “clicca qui”, “leggi di più”.

---

## **7. Riepilogo: checklist “Testo del link”**

Quando scrivi il testo di un link, chiediti:

1. **Dice chiaramente dove porta?**
    
    - Sì → bene.
        
    - No → rendilo più descrittivo.
        
2. **È conciso?**
    
    - 2–4 parole ben scelte sono ideali.
        
    - Evita frasi lunghe.
        
3. **Evita “clicca qui / premi qui / scopri di più qui”?**
    
    - Se sì, ottimo.
        
    - Se no, riscrivilo puntando sul contenuto.
        
4. **Evita l’URL grezzo come testo?**
    
    - Usa il nome del sito o il concetto.
        
5. **Per un lettore di sola lista link (screen reader), il testo ha senso da solo?**
    
    - “Scarpe da corsa migliori”, “Pagina prodotti”, “Caduta del muro di Berlino” → sì.
        
    - “Clicca qui”, “leggi di più”, “qui” → no.
        

Se rispetti queste regole di base, **stai già facendo un ottimo lavoro** sia per gli utenti che per i motori di ricerca.