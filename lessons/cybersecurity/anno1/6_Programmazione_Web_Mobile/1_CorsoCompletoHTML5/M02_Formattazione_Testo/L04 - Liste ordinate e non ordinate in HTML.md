## **Lezione 4: Liste ordinate e non ordinate in HTML**

---

## **1. Perché le liste sono fondamentali**

Le liste non sono un “optional carino”: sono **una struttura centrale** del contenuto sul web.

Pensa a questi casi reali:

- Articoli tipo “Le 10 migliori scarpe invernali” → elenco di modelli.
    
- Ricette di cucina → lista degli **ingredienti** + lista dei **passaggi**.
    
- Guide pratiche → “Passaggi per fare un reso Amazon”, “Come spedire una raccomandata”.
    
- Presentazioni PowerPoint → quasi sempre **bullet point**, mai blocchi di testo enormi.
    

Quindi le liste sono importanti:

- per chi **vede** la pagina (leggibilità, scorribilità, zero “mallopponi”);
    
- per i **motori di ricerca** (SEO);
    
- per i **lettori di schermo** (accessibilità): una lista letta come lista è molto più chiara di un testo unico.
    

HTML ci dà tre tipi di liste (in questa lezione vediamo le prime due):

- **`<ul>`** → liste non ordinate (bullet, pallini).
    
- **`<ol>`** → liste ordinate (numeri, lettere, numeri romani).
    
- **`<dl>`** → liste descrittive (le faremo nella prossima lezione).
    

---

## **2. Le liste non ordinate: `<ul>` + `<li>`**

### **2.1. La struttura base**

Una lista non ordinata si crea con:

- `<ul>` → _unordered list_ (lista non ordinata),
    
- `<li>` → _list item_ (elemento della lista).
    

La struttura minima è:

```html
<ul>
  <li>Primo elemento</li>
  <li>Secondo elemento</li>
  <li>Terzo elemento</li>
</ul>
```

Risultato:

- una lista con **pallini** (bullet) davanti a ogni elemento;
    
- nella forma base, nessuna numerazione, nessuna lettera.
    

Esempio con nomi:

```html
<ul>
  <li>Luca</li>
  <li>Anna</li>
  <li>Marco</li>
  <li>Leonardo</li>
</ul>
```

Ogni volta che vuoi un nuovo punto nella lista, aggiungi un nuovo `<li>`.

> Nota: il tag `<ul>` da solo non basta.  
> Senza `<li>`, la lista non ha elementi e non ha senso.

---

## **3. Le liste ordinate: `<ol>` + `<li>`**

Quando ci interessa mantenere un **ordine preciso** (1°, 2°, 3°… oppure A, B, C…):

- usiamo `<ol>` → _ordered list_.
    

Struttura base:

```html
<ol>
  <li>Luca</li>
  <li>Anna</li>
  <li>Marco</li>
</ol>
```

Per default, il browser mostra:

1. Luca
    
2. Anna
    
3. Marco
    

Ma con `<ol>` abbiamo anche degli **attributi potenti** che ci permettono di controllare numerazione e stile.

---

## **4. Attributi importanti di `<ol>`**

### **4.1. `start`: da che numero (o lettera) cominciare**

Se vuoi che la lista **non inizi da 1** ma, per esempio, da 100:

```html
<ol start="100">
  <li>Elemento uno</li>
  <li>Elemento due</li>
  <li>Elemento tre</li>
</ol>
```

Risultato:

100. Elemento uno
    
101. Elemento due
    
102. Elemento tre
    

Stessa cosa con lettere/romani: `start` si riferisce alla **posizione**, non al simbolo:

```html
<ol type="A" start="3">
  <li>Elemento uno</li>
  <li>Elemento due</li>
</ol>
```

Risultato:

C. Elemento uno  
D. Elemento due

(perché A=1, B=2, C=3, D=4…)

---

### **4.2. `reversed`: invertire l’ordine (countdown)**

L’attributo `reversed` fa contare la lista **al contrario**.

Esempio:

```html
<ol reversed>
  <li>Elemento uno</li>
  <li>Elemento due</li>
  <li>Elemento tre</li>
</ol>
```

Risultato:

3. Elemento uno
    
4. Elemento due
    
5. Elemento tre
    

Puoi combinarlo con `start`:

```html
<ol start="5" reversed>
  <li>Elemento uno</li>
  <li>Elemento due</li>
</ol>
```

Risultato:

5. Elemento uno
    
6. Elemento due
    

---

### **4.3. `type`: stile della numerazione**

L’attributo `type` decide **come** numerare la lista:

- `type="1"` → numeri (1, 2, 3…) **(default)**
    
- `type="A"` → lettere maiuscole (A, B, C…)
    
- `type="a"` → lettere minuscole (a, b, c…)
    
- `type="I"` → numeri romani maiuscoli (I, II, III…)
    
- `type="i"` → numeri romani minuscoli (i, ii, iii…)
    

Esempi:

```html
<ol type="A">
  <li>Primo</li>
  <li>Secondo</li>
  <li>Terzo</li>
</ol>
```

```html
<ol type="i">
  <li>Introduzione</li>
  <li>Sviluppo</li>
  <li>Conclusione</li>
</ol>
```

Puoi combinare `type` con `start` e `reversed`:

```html
<ol type="I" start="4" reversed>
  <li>Quarto elemento</li>
  <li>Quinto elemento</li>
</ol>
```

---

## **5. Attributo `value` su `<li>`: saltare la numerazione**

Oltre agli attributi di `<ol>`, anche i singoli `<li>` possono avere un attributo:

- `value` → forza un numero specifico per quella voce.
    

Esempio:

```html
<ol>
  <li>Elemento 1</li>
  <li>Elemento 2</li>
  <li value="9">Elemento 9</li>
  <li>Elemento successivo</li>
</ol>
```

Risultato:

1. Elemento 1
    
2. Elemento 2
    
3. Elemento 9
    
4. Elemento successivo
    

Cosa succede:

- i primi due `<li>` seguono la numerazione normale;
    
- il terzo `<li>` forza il numero a 9;
    
- i successivi continuano da lì (10, 11, …).
    

È come dire: “Da qui in poi, considera questo come 9º”.

È utile quando hai:

- salti logici,
    
- punti numerati che devono seguire uno schema preciso (es. numerazione di domande d’esame, esercizi di un libro, ecc.).
    

---

## **6. Liste annidate (sottoliste)**

Spessissimo nelle pagine vere hai **liste dentro altre liste**.

Esempio: elenco di famiglie, ognuna con i propri membri.

```html
<ul>
  <li>
    Famiglia Rossi
    <ol>
      <li>Luca</li>
      <li>Anna</li>
      <li>Massimo</li>
    </ol>
  </li>

  <li>
    Famiglia Verdi
    <ol>
      <li>Sara</li>
      <li>Giulia</li>
    </ol>
  </li>
</ul>
```

Osserva:

- abbiamo una `<ul>` esterna (lista di famiglie),
    
- dentro **ogni `<li>`** abbiamo un’altra lista (`<ol>`),
    
- puoi annidare **quante volte vuoi**: HTML non ti limita (usa il buon senso).
    

Puoi anche mischiare:

- `<ul>` fuori, `<ol>` dentro,
    
- `<ol>` fuori, `<ul>` dentro.
    

L’importante è che:

- le sottoliste stiano **dentro un `<li>`** della lista superiore.
    

---

## **7. Riepilogo concettuale**

- Le liste sono **centrali** per:
    
    - leggibilità,
        
    - SEO,
        
    - accessibilità,
        
    - user experience (soprattutto da mobile).
        
- Tipi di liste viste oggi:
    
    - **`<ul>`** → liste non ordinate (pallini),
        
    - **`<ol>`** → liste ordinate (numeri/lettere/romani).
        
- Elemento comune:
    
    - **`<li>`** → singolo elemento di lista (_list item_).
        
- Attributi principali:
    
    - su `<ol>`:
        
        - `start` → da dove inizia,
            
        - `reversed` → countdown,
            
        - `type` → stile numerazione (1, A, a, I, i).
            
    - su `<li>`:
        
        - `value` → forza un numero specifico.
            
- Possiamo creare:
    
    - **liste annidate**, combinando `<ul>` e `<ol>` dentro i `<li>`.
        

Nella prossima lezione passeremo al terzo tipo di lista:  
le **liste descrittive** (`<dl>`, `<dt>`, `<dd>`), utilissime per definizioni, glossari, FAQ e coppie termine/descrizione.