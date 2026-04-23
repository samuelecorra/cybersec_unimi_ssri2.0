## **Lezione 12: Il paragrafo `<p>` e la scrittura sul web**

---

### **1. Introduzione all’elemento `<p>`**

Dopo aver sistemato gli headings, abbiamo iniziato a riempire i contenuti con del testo “di prova”.  
È quindi il momento di formalizzare **l’elemento base del testo in HTML**: il paragrafo.

L’elemento è:

```html
<p>...</p>
```

La `p` sta per **paragraph**, cioè “paragrafo”.

Un paragrafo rappresenta **un blocco di testo coerente**: una o più frasi che sviluppano **un’unica idea** sotto un certo titolo o sottotitolo.

> Regola mentale:
> 
> - Titolo → `<h1>`–`<h6>`
>     
> - Testo che sviluppa il titolo → `<p>`
>     

---

### **2. Perché non scrivere testo “sciolto” nel body**

Tecnicamente potresti scrivere:

```html
Testo scritto direttamente nel body senza tag...
```

Il browser lo visualizza comunque, ma è **sbagliato a livello semantico**:

- per noi umani è “testo e basta”, quindi lo leggiamo;
    
- per i **crawler** (motori di ricerca) e per gli **screen reader** è testo senza ruolo:
    
    - non è un titolo,
        
    - non è una lista,
        
    - non è un paragrafo dichiarato.
        

Usando sempre:

```html
<p>Testo del paragrafo...</p>
```

stai dicendo in modo chiaro:

> “Questo è un blocco di testo che fa parte del contenuto, non è un titolo, non è un menù, non è altro.”

Dentro un `<p>` puoi mettere:

- testo normale,
    
- elementi inline come `<span>`, `<a>`, `<strong>`, `<em>`, ecc.
    

---

### **3. Testo riempitivo: `lorem ipsum`**

Per **testare la struttura** della pagina senza avere ancora il testo definitivo, si usa il famoso **lorem ipsum**, un testo finto, pseudo-latino, usato **puramente come riempimento**.

Nei moderni editor (es. con Emmet) puoi:

- scrivere `lorem` e premere Tab → ti genera un paragrafo di prova;
    
- scrivere `lorem3` → 3 parole;
    
- scrivere `lorem50` → circa 50 parole;
    
- scrivere `lorem500` → un blocco enorme.
    

Esempio:

```html
<p>
  Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
  Nisi eaque cupiditate incidunt...
</p>
```

Questo è **solo testo finto**, serve per:

- vedere la resa grafica;
    
- studiare la leggibilità dei blocchi;
    
- verificare spaziature, interlinea ecc.
    

---

### **4. Il problema dei paragrafi “mallopponi”**

Se usi qualcosa come `lorem500`, ottieni un paragrafo gigantesco:

```html
<h2>Quali sono gli headings</h2>
<p>
  Lorem ipsum dolor sit amet, consectetur adipisicing elit...
  <!-- 500 parole di seguito -->
</p>
```

Problema:

- su desktop è già pesante,
    
- su mobile diventa **un muro compatto di testo**.
    

Risultato:

- l’utente si stanca subito,
    
- perde il segno,
    
- smette di leggere e **abbandona il sito**.
    

È identico alla sensazione da “romanzetto di 200 pagine” con **pagine piene, senza mai un rientro o uno spazio**.

---

### **5. Suddividere il testo in più paragrafi**

Soluzione:  
**spezzare** il testo in più `<p>` che rappresentano sotto-idee all’interno della stessa sezione.

Esempio brutto (tutto attaccato):

```html
<h2>Quali sono gli headings</h2>
<p>
  Lorem ipsum dolor sit amet, consectetur adipisicing elit... (200 parole)
  Lorem ipsum dolor sit amet, consectetur adipisicing elit... (altre 200)
</p>
```

Versione buona:

```html
<h2>Quali sono gli headings</h2>

<p>
  Lorem ipsum dolor sit amet, consectetur adipisicing elit...
  (prima idea / introduzione)
</p>

<p>
  Lorem ipsum dolor sit amet, consectetur adipisicing elit...
  (seconda idea / approfondimento)
</p>

<p>
  Lorem ipsum dolor sit amet, consectetur adipisicing elit...
  (terza idea / esempio)
</p>
```

Non hai cambiato il significato logico, ma:

- **visivamente** è molto più leggibile,
    
- l’utente può:
    
    - fermarsi alla fine di un paragrafo,
        
    - riprendere facilmente da dove aveva lasciato.
        

Questo è fondamentale soprattutto:

- su **mobile**,
    
- in **contesti rumorosi** (metro, tram, treno),
    
- quando l’attenzione dell’utente è frammentata.
    

---

### **6. Perché non usare `<br>` al posto di `<p>`**

Potresti essere tentato di usare `<br>` per andare a capo:

```html
<p>
  Prima frase...<br>
  Seconda frase...<br>
  Terza frase...
</p>
```

Se poi vuoi più spazio, cominci ad accumulare:

```html
Prima frase...<br>
<br>
Seconda frase...<br>
<br>
Terza frase...
```

Risultato:

- HTML pieno di `<br>` inutili,
    
- controllare e modificare il layout diventa un incubo,
    
- non stai creando **paragrafi** distinti, solo salti di riga.
    

Il `<br>` serve per **piccole interruzioni** di riga (poesia, indirizzi, casi speciali), non per strutturare la pagina.

Se hai **idee diverse**, usa paragrafi diversi:

```html
<p>Prima idea del discorso.</p>
<p>Seconda idea del discorso.</p>
<p>Conclusione.</p>
```

I sistemi di gestione contenuti (WordPress, ecc.) si basano proprio su questa logica:  
**vai a capo due volte → nuovo paragrafo, non `<br>` ovunque.**

---

### **7. Come scrivere paragrafi efficaci sul web**

Finora abbiamo visto **come** usare `<p>` dal punto di vista tecnico.  
Ora parliamo di **come riempirli**, perché la forma del contenuto è parte dell’accessibilità.

#### **7.1 Linguaggio**

- Non usare linguaggio da chat tipo:
    
    > “bella fra, zio, spaccare tutto, ecc.”
    
- Non usare neanche italiano da romanzo ottocentesco:
    
    > “ove giace la consapevolezza dell’utente moderno…”
    

Serve un **italiano colloquiale pulito**:

- chiaro,
    
- diretto,
    
- senza parolacce,
    
- comprensibile al volo anche se l’utente è stanco o distratto.
    

#### **7.2 Struttura delle frasi**

Evita:

- frasi-matrioska con:
    
    - 700 virgole,
        
    - 3 incisi,
        
    - 2 subordinate dentro altre subordinate.
        

Preferisci:

- frasi **brevi**,
    
- ognuna con un concetto principale chiaro,
    
- concatenate in modo logico.
    

Schema:

1. Frase che introduce l’idea del paragrafo.
    
2. Una o due frasi che la sviluppano.
    
3. Eventuale frase di chiusura / collegamento al paragrafo successivo.
    

Quando hai finito un’idea → **nuovo paragrafo**.

---

### **8. Paragrafi, headings, accessibilità e tempi moderni**

Headings e paragrafi sono elementi HTML **semplicissimi** come sintassi:

- `<h1> ... </h1>`
    
- `<p> ... </p>`
    

La parte difficile non è “come si scrivono”, ma **come li usi per organizzare contenuti complessi**.

Ricorda che l’accessibilità non riguarda solo:

- screen reader,
    
- disabilità visive.
    

Riguarda anche:

- persone che vedono benissimo ma:
    
    - hanno 2 minuti sul treno,
        
    - leggono da uno schermo piccolo,
        
    - sono bombardate da notifiche.
        

In più:

- i motori di ricerca leggono la pagina,
    
- se la struttura è confusa, dicono (senza avvisarti):
    
    > “Questo contenuto non è così chiaro rispetto alla concorrenza, lo metto più in basso”.
    

Non ricevi una mail di errore, semplicemente:

- non sali nelle prime posizioni,
    
- non vieni trovato.
    

Headings + paragrafi ben strutturati sono quindi:

- **accessibilità umana** (leggibilità),
    
- **accessibilità tecnica** (screen reader),
    
- **base per SEO** e copywriting.
    

---

### **9. Riepilogo operativo**

- Usa sempre `<p>` per il testo “normale”, non testo sciolto.
    
- Spezza i mallopponi in **più paragrafi** brevi.
    
- Usa `<br>` solo per andare a capo in casi particolari (non per impaginare articoli interi).
    
- Linguaggio:
    
    - italiano chiaro,
        
    - frasi brevi,
        
    - niente slang pesante,
        
    - niente prosa inutilmente complicata.
        
- Pensa al lettore su mobile:  
    ogni paragrafo deve essere “respirabile” a colpo d’occhio.
    

---

Nella prossima lezione entreremo nell’attributo `lang`, che hai già intravisto in cima al documento HTML, e vedremo **perché è fondamentale per l’accessibilità e per i motori di ricerca** indicare correttamente la lingua del contenuto.