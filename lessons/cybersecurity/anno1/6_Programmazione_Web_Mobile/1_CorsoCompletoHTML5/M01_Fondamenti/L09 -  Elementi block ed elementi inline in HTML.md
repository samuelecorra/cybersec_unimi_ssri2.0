# **Lezione 9: Elementi block ed elementi inline in HTML**

## **1. Perché distinguere tra block e inline**

Ora che sappiamo cos’è un elemento HTML (tag di apertura, contenuto, tag di chiusura) dobbiamo fare una distinzione fondamentale:

- **elementi block** (a blocco)
    
- **elementi inline** (in linea)
    

Questa distinzione determina **come gli elementi occupano lo spazio sulla pagina** e come si dispongono rispetto agli altri.  
È un concetto già importante in puro HTML e diventerà decisivo quando useremo il CSS per il layout.

---

## **2. Intuizione linguistica: “block” vs “inline”**

- **Block** ≈ “blocco”  
    Un elemento block si comporta come un **blocco intero**: prende tutta la riga disponibile in larghezza.
    
- **Inline** ≈ “in linea”  
    Un elemento inline si comporta come una parola in una frase: si mette **in fila** con il testo intorno, senza mandare a capo la riga.
    

Questa è l’idea di base. Vediamola in pratica.

---

## **3. Esempio di elemento block**

Consideriamo un `<h1>`:

```html
<h1 style="background-color: aqua;">Ciao</h1>
<p>Testo dopo l’H1</p>
<div style="background-color: aqua;">Altro contenuto</div>
```

Cosa succede nel browser?

- L’**`h1`** occupa **tutta la larghezza** della riga (lo si vede bene con lo sfondo acqua).
    
- Il **paragrafo `<p>`** viene mostrato **nella riga successiva**.
    
- Il **`div`** viene mostrato ancora **in una nuova riga**, ed anche lui prende tutta la sua riga.
    

Questi elementi sono tipici **elementi block**:

- `h1`, `h2`, … `h6` (titoli)
    
- `p` (paragrafi)
    
- `div`
    
- molti altri (li incontreremo più avanti)
    

Caratteristica chiave:

> Un elemento block **occupa l’intera riga**: nessun altro elemento può stare “accanto” nella stessa riga, a meno che non sia annidato _dentro_ di lui.

---

## **4. Esempio di elemento inline**

Consideriamo un paragrafo con del testo e un link:

```html
<p>
  Questo è un paragrafo e 
  <a href="https://google.com">clicca qui</a> 
  è un link.
</p>
```

Nel codice il link può anche andare a capo, ma nel browser il risultato è:

> Questo è un paragrafo e **clicca qui** è un link.

Il testo e il link sono **sulla stessa riga**, quando lo spazio lo consente.  
L’elemento `<a>` non “rompe” la riga: si comporta come una parola in più nella frase.

Altro esempio con `<span>`:

```html
<p>
  Questo è un testo con 
  <span style="background-color: red;">una parte evidenziata</span> 
  in mezzo.
</p>
```

Anche qui, lo `span` rimane **in linea** con il resto del testo.

Elementi inline tipici:

- `a` (link)
    
- `span`
    
- elementi di formattazione del testo (grassetto, corsivo, ecc., che vedremo più avanti)
    

Caratteristica chiave:

> Un elemento inline **non occupa l’intera riga**, ma solo lo spazio necessario al suo contenuto, restando in fila con il testo circostante.

---

## **5. Codice vs visualizzazione: non confondere le due cose**

Nel file HTML possiamo andare a capo per **leggibilità**:

```html
<p>
  Questo è un paragrafo con
  <a href="https://google.com">
    clicca qui
  </a>
  al suo interno.
</p>
```

Nel browser, se c’è spazio, vedremo comunque:

> Questo è un paragrafo con **clicca qui** al suo interno.

Gli **a capo nel codice** servono solo a noi.  
Il browser decide di andare a capo solo quando:

- incontra un elemento **block** successivo;
    
- oppure non c’è più spazio orizzontale (va a capo automaticamente come in un normale testo).
    

Per questo:

- `h1`, `p`, `div` si vedono uno sotto l’altro;
    
- `span`, `a` restano in linea, anche se nel codice li scriviamo su righe separate.
    

---

## **6. Elementi block con elementi inline all’interno**

Possiamo tranquillamente avere elementi inline _dentro_ elementi block.

Esempio:

```html
<h1 style="background-color: aqua;">
  Ciao
  <span style="background-color: red;">mondo</span>
</h1>
```

Nel browser:

- l’`h1` occupa tutta la riga (elemento block),
    
- dentro di lui il testo “Ciao” e lo `span` “mondo” sono **sulla stessa riga**, perché `span` è inline.
    

Attenzione: se scrivessimo uno `span` **fuori** dall’`h1`:

```html
<h1>Ciao</h1>
<span>mondo</span>
```

Risultato:

- `h1` occupa la prima riga,
    
- `span` inizierà **nella riga successiva**, perché l’`h1` si è già preso tutta la riga.
    

---

## **7. Perché questa distinzione è importante**

1. **Struttura logica della pagina**
    
    - I titoli, i paragrafi, le sezioni, i blocchi di contenuto sono quasi sempre elementi block.
        
    - Parti di testo enfatizzate, link, abbreviazioni, piccole aggiunte sono spesso inline.
        
2. **Accessibilità e lettura del codice**
    
    - Una buona distinzione tra blocchi e contenuto inline rende la pagina più chiara anche agli screen reader e ai motori di ricerca.
        
3. **CSS e layout (fondamentale)**
    
    - Nel mondo del CSS, la differenza block/inline è alla base del modo in cui gestiamo:
        
        - larghezze,
            
        - margini,
            
        - allineamenti,
            
        - posizionamento degli elementi.
            
    - Capire ora la differenza ci renderà molto più semplice il lavoro quando inizieremo a creare layout complessi.
        

---

## **8. Riepilogo**

- **Elementi block**
    
    - Occutano l’intera riga.
        
    - Tipicamente usati per strutturare la pagina (titoli, paragrafi, contenitori).
        
    - Esempi: `h1`–`h6`, `p`, `div`, molti altri.
        
- **Elementi inline**
    
    - Occupano solo lo spazio del loro contenuto.
        
    - Vivono in mezzo al testo, sulla stessa linea.
        
    - Esempi: `span`, `a`, elementi di formattazione del testo.
        
- Un elemento block può contenere elementi inline al suo interno.
    
- Gli a capo nel file HTML non significano automaticamente a capo nel browser: contano solo il tipo di elemento (block vs inline) e lo spazio disponibile.
    

Non è necessario imparare a memoria la lista di tutti gli elementi block e inline: li incontreremo gradualmente e sarà il loro comportamento, in combinazione con il CSS, a fissarli nella memoria.

---

Nella prossima lezione andremo ad analizzare **la sezione `<head>` nel dettaglio**: vedremo che tipo di informazioni contiene, perché sono importanti per SEO, accessibilità e funzionamento generale della pagina.