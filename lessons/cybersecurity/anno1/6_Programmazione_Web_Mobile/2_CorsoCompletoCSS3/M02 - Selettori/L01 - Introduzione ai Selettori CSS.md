## **Lezione 1 – Introduzione ai Selettori CSS**

### **1. Inizio della nuova sezione: i selettori CSS**

In questa nuova sezione del corso, composta da circa una decina di video, affrontiamo uno degli argomenti più importanti e strutturali del CSS: **i selettori**.

Quando scriviamo una regola CSS, la parte che si trova **prima** delle parentesi graffe `{ }` è il selettore.  
È ciò che dice al browser **quali elementi della pagina devono essere raggiunti** da quella regola.

Esempi semplici:

```
p {
    color: red;
}
```

Qui `p` è il selettore.

Esempi più complessi:

```
#titolo > span.attivo[aria-disabled="true"] {
    color: blue;
}
```

Non importa quanto semplice o complesso diventi il selettore:  
**tutto ciò che sta prima della parentesi graffa è il selettore**, ed è ciò che il CSS usa per “trovare” gli elementi nella pagina.

---

### **2. Perché i selettori diventano complessi molto velocemente**

All’inizio si lavora con selettori base:

- `body`
    
- `p`
    
- `h1`
    
- `.classe`
    
- `#id`
    

Ma appena iniziamo a mescolare:

- più classi
    
- ID
    
- relazioni tra elementi (freccette come `>` o spazi come discendenti)
    
- attributi (`[disabled]`, `[type="text"]`)
    
- pseudo-classi (`:hover`, `:active`)
    
- pseudo-elementi (`::before`, `::after`)
    

… i selettori diventano **rapidamente complessi**.

Questo perché il CSS permette di combinare tutte queste cose insieme.  
E quando iniziamo a usare la **specificità** (che dipende direttamente dai selettori), dobbiamo sapere _esattamente_ cosa stiamo scrivendo.

---

### **3. Le categorie principali di selettori**

Per capire e ordinare questo mondo, dividiamo i selettori in grandi categorie:

#### **3.1. Selettore globale**

Lo vedremo nel prossimo video.  
È semplicissimo, dura letteralmente 30 secondi, ed è potentissimo.

#### **3.2. Selettori di elemento HTML**

Riconoscono gli elementi per tag:  
`p`, `h1`, `button`, `div`.

#### **3.3. Selettori di ID**

Riconoscono l’elemento tramite il suo ID:  
`#titolo`, `#hero`.

Gli ID sono unici (come un codice fiscale), quindi identificano uno specifico elemento.

#### **3.4. Selettori di classe**

Riconoscono tutti gli elementi che condividono una o più classi:  
`.rosso`, `.attivo`, `.grande`.

Possono anche essere combinati:  
`.rosso.attivo`.

#### **3.5. Selettori di attributo**

Permettono di selezionare elementi che possiedono un certo attributo:  
`button[disabled]`,  
`input[type="text"]`,  
`h1[aria-disabled="true"]`.

#### **3.6. Pseudo-classi**

Rappresentano **stati dinamici**, come:  
`:hover`, `:active`, `:focus`, `:checked`.

Sono fondamentali per interazioni e stili condizionali.

#### **3.7. Pseudo-elementi**

Rappresentano “parti virtuali” dell’elemento:  
`::before`, `::after`, `::first-line`.

Li vedremo in profondità più avanti.

---

### **4. Combinare i selettori: da semplici a avanzati**

Una caratteristica chiave del CSS è che **tutti questi selettori possono essere combinati**.

Esempi:

- `p.attivo`
    
- `.card:hover`
    
- `#menu > li > a`
    
- `input[type="email"]:focus`
    
- `section .contenuto strong::before`
    

È proprio questa combinazione illimitata che rende i selettori uno strumento potente… ma anche molto facile da complicare.

Se vogliamo essere **super specifici**, per sfruttare la **specificità**, possiamo scrivere selettori che puntano a elementi molto precisi.

Ma più si combinano selettori complessi, più aumenta la possibilità di confusione o conflitto nel codice.

---

### **5. Conclusione della lezione**

Per ora ci fermiamo qui: questa introduzione serve solo a dare un’idea generale di che cosa sono i selettori e perché sono fondamentali.

Nel **prossimo video**, il prof introduce il primo di questi selettori:  
**il selettore globale**.

È facilissimo da usare, ma va trattato con cautela perché ha effetti su _tutta_ la pagina.

Ci vediamo lì.