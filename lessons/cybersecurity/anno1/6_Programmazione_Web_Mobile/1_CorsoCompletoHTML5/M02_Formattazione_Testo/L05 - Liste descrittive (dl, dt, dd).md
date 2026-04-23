## **Lezione 5: Liste descrittive (`<dl>`, `<dt>`, `<dd>`)**

---

## **1. Cosa sono le liste descrittive**

Le liste descrittive sono il **terzo e ultimo tipo di lista in HTML**, dopo:

- le liste non ordinate (`<ul>`),
    
- le liste ordinate (`<ol>`).
    

Si chiamano descrittive perché ogni elemento della lista non è un semplice “punto”, ma una **coppia**:

- un **termine** da definire,
    
- una **descrizione** del termine.
    

Sono quindi perfette per:

- glossari, vocabolari, definizioni;
    
- FAQ (domanda → risposta);
    
- specifiche tecniche;
    
- elenchi di proprietà e valori;
    
- elenchi di terminologia durante una lezione (come la nostra).
    

---

## **2. La struttura base della lista descrittiva**

Gli elementi coinvolti sono tre:

- **`<dl>`** → _description list_ → contiene la lista.
    
- **`<dt>`** → _description term_ → il termine.
    
- **`<dd>`** → _description definition_ → la descrizione del termine.
    

### **Esempio base**

```html
<dl>
  <dt>HTML</dt>
  <dd>Un linguaggio di markup usato per strutturare il contenuto delle pagine web.</dd>

  <dt>CSS</dt>
  <dd>Il linguaggio che permette di stilizzare gli elementi HTML.</dd>
</dl>
```

Risultato concettuale:

- “HTML” è il termine
    
- “Un linguaggio di markup…” è la descrizione
    
- “CSS” è un altro termine
    
- “Il linguaggio che permette…” è la descrizione
    

---

## **3. Perché usare `<dl>` invece di `<ul>` con `<strong>`**

Potresti ottenere qualcosa di simile anche così:

```html
<ul>
  <li><strong>HTML:</strong> linguaggio di markup...</li>
</ul>
```

MA:

- questo è **solo forma**, non semantica;
    
- per un lettore di schermo, questa è una comune lista, dove il termine è “solo” in grassetto;
    
- per un motore di ricerca è meno chiaro cosa sia termine e cosa sia descrizione.
    

Con `<dl>`, invece, il browser e gli screen reader **capiscono davvero**:

- quale parola è il termine,
    
- quale è la spiegazione.
    

Le description list nascono esattamente per questo scopo.

---

## **4. Un esempio completo**

```html
<dl>
  <dt>Termine</dt>
  <dd>Questa è la descrizione del termine. Può essere una frase o un paragrafo.</dd>

  <dt>Sistema Operativo</dt>
  <dd>È il software che permette al computer di funzionare e gestire programmi e risorse.</dd>
</dl>
```

Il browser automaticamente dà una formattazione di default:

- il `<dt>` appare spesso **in grassetto**
    
- il `<dd>` ha un **indent** (rientro)
    

Questo comportamento non è obbligatorio: è solo lo stile predefinito del browser e potrà essere personalizzato con il CSS.

---

## **5. Aggiungere margine o stile (solo a scopo dimostrativo)**

Durante la lezione hai visto un esempio come questo:

```html
<dd style="margin-left: 40px;">
  Questa descrizione è visivamente spostata più a destra.
</dd>
```

Questo serve solo per imitare lo stile che il browser applica automaticamente.  
Più avanti, nel modulo di CSS, stilizzerai l’intera lista in modo pulito.

---

## **6. Più termini per una sola descrizione**

HTML ti permette questo pattern:

```html
<dl>
  <dt>Termine</dt>
  <dt>Sinonimo 1</dt>
  <dt>Sinonimo 2</dt>
  <dd>Descrizione che vale per tutti e tre i termini.</dd>
</dl>
```

È molto utile per:

- sinonimi,
    
- termini equivalenti,
    
- varianti linguistiche,
    
- concetti multipli con la stessa definizione.
    

---

## **7. Un termine con più descrizioni**

Può succedere (specie in glossari tecnici):

```html
<dl>
  <dt>Buffer</dt>
  <dd>Nell’informatica, un’area di memoria temporanea.</dd>
  <dd>Nel contesto audio, una porzione di dati in streaming.</dd>
  <dd>In biologia, una soluzione che stabilizza il pH.</dd>
</dl>
```

Ogni `<dd>` è una descrizione valida del termine.

---

## **8. Più termini e più descrizioni (caso raro)**

È possibile anche questo:

```html
<dl>
  <dt>Termine A</dt>
  <dt>Termine B</dt>
  <dd>Prima spiegazione</dd>
  <dd>Seconda spiegazione</dd>
</dl>
```

HTML lo permette, ma è una struttura che ha senso solo in contesti molto specifici.

---

## **9. Riepilogo della sintassi**

- **`<dl>`** → contiene la lista
    
- **`<dt>`** → rappresenta un _termine_
    
- **`<dd>`** → rappresenta una _descrizione_
    

Tutto inizia per **D**:

- **D**escription **L**ist
    
- **D**escription **T**erm
    
- **D**escription **D**efinition
    

Questa mnemotecnica te la ricorderai per sempre.

---

## **10. Confronto visivo**

### Lista “fatta a mano” (non semantica)

```html
<ul>
  <li><strong>CPU:</strong> Unità di elaborazione principale.</li>
</ul>
```

### Lista descrittiva (semantica, accessibile)

```html
<dl>
  <dt>CPU</dt>
  <dd>Unità di elaborazione principale.</dd>
</dl>
```

Per un lettore di schermo la differenza è enorme.

---

## **11. Conclusione**

Le liste descrittive:

- sono facili da usare,
    
- hanno una semantica fortissima,
    
- sono essenziali per glossari, definizioni e FAQ,
    
- rendono il contenuto più accessibile,
    
- saranno bellissime da stilizzare quando avremo il CSS.
    

Nel prossimo episodio passeremo alle **citazioni**, dove vedremo i tag:

- `<blockquote>`
    
- `<q>`
    
- `<cite>`
    

…e come vanno usati in modo corretto.