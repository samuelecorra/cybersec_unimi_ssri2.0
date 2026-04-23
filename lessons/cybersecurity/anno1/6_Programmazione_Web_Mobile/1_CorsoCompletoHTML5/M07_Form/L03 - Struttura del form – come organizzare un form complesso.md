## **Lezione 3: Struttura del form – come organizzare un form complesso**

---

### **1. Cosa intendiamo per “struttura” di un form**

Qui per **struttura** non intendiamo “la teoria dei form”, ma proprio:

> Come organizzo **visivamente e logicamente** un form che può essere anche molto lungo?

Pensa a:

- registrazione in banca
    
- iscrizione a servizi governativi
    
- richieste di bonus, bandi, documenti
    

Non sono due campi “username + password”, ma:

- dati anagrafici
    
- residenza
    
- documenti
    
- dati finanziari
    
- contatti di emergenza
    
- ecc.
    

HTML ci mette a disposizione alcuni **strumenti strutturali**, che:

- aiutano **l’utente** a capire come è organizzato il form
    
- aiutano **te come sviluppatore** a tenere il codice leggibile e mantenibile
    

Partiamo dalla prima “super accoppiata”.

---

### **2. `fieldset` e `legend`: la coppia storica per sezioni visive**

Il primo strumento integrato in HTML è:

```html
<fieldset> ... </fieldset>
```

È un contenitore **pensato proprio per i form**.

Se metti dentro alcuni campi:

```html
<form>
  <fieldset>
    <label for="nome">Nome</label>
    <input id="nome" name="nome" type="text" />

    <label for="cognome">Cognome</label>
    <input id="cognome" name="cognome" type="text" />
  </fieldset>
</form>
```

Il browser:

- disegna **automaticamente** un bordo attorno al `fieldset`
    
- aggiunge margini e padding (senza che tu usi CSS)
    

Poi entra in gioco la label della sezione: `legend`.

```html
<fieldset>
  <legend>Dati anagrafici</legend>

  <label for="nome">Nome</label>
  <input id="nome" name="nome" type="text" />

  <label for="cognome">Cognome</label>
  <input id="cognome" name="cognome" type="text" />
</fieldset>
```

Risultato:

- un **bordo** che racchiude i campi
    
- un'etichetta (“Dati anagrafici”) che viene “incastonata” sul bordo
    

Vantaggi:

- zero CSS per ottenere un minimo di struttura visiva
    
- buona semantica (il browser e i lettori di schermo capiscono che è un gruppo di campi correlati)
    

Limiti:

- lo stile del bordo e del titolo lo decide il browser (e spesso è bruttino)
    
- non sempre vuoi **quel bordo** o **quel titolo** in quel modo
    

Quindi:  
`fieldset + legend` sono perfetti per:

- gruppi chiari e coerenti (es. “Dati anagrafici”, “Dati di contatto”, “Dati di fatturazione”)
    
- form relativamente semplici
    

Se vuoi più controllo estetico, di solito si passa ad altre soluzioni + CSS.

---

### **3. Sezioni logiche: usare `section` + titoli (`h3`, `h4`, …)**

Se un form è davvero grande, una delle strategie più sane è dividerlo in **sezioni logiche**.

Esempio:

```html
<form>

  <section>
    <h3>Sezione anagrafica</h3>
    <!-- campi anagrafici -->
  </section>

  <section>
    <h3>Sezione dati principali</h3>
    <!-- campi principali -->
  </section>

  <section>
    <h3>Sezione dati finanziari</h3>
    <!-- campi finanziari -->
  </section>

</form>
```

Qui:

- `section` dice: “questo blocco è una sezione logica autonoma”
    
- `h3` (o `h2`, `h4`, in base alla gerarchia della pagina) fa da **titolo** della sezione
    

Sono molto utili per form tipo:

- registrazione completa utente
    
- apertura conto corrente
    
- domanda di finanziamento
    
- iscrizioni complesse
    

Spesso nel web moderno queste sezioni vengono spezzate in **form a step** (wizard):

- Step 1 – Dati anagrafici
    
- Step 2 – Dati di residenza
    
- Step 3 – Dati di pagamento
    

Questa però è **logica di web design + JavaScript**.  
Nel nostro contesto HTML puro, ci basta l’idea:

> Se il form è lungo, ragiona in **sezioni** con `section + titolo`.

---

### **4. Raggruppare i singoli campi: `li` oppure `div`**

Dentro ogni sezione, resta un problema pratico:

> Come raggruppo visivamente ogni “coppia” label + input  
> in modo ordinato e facile da stilare dopo?

Due strade comuni.

#### 4.1. Strada A – Liste (`ul` / `ol` + `li`)

Possiamo usare una lista:

```html
<section>
  <h3>Dati anagrafici</h3>

  <ul>
    <li>
      <label for="nome">Nome</label>
      <input id="nome" name="nome" type="text" />
    </li>

    <li>
      <label for="cognome">Cognome</label>
      <input id="cognome" name="cognome" type="text" />
    </li>
  </ul>
</section>
```

Caratteristiche:

- ogni `li` contiene una **coppia logica** (label + input)
    
- verticalmente i campi si allineano da soli
    
- otterrai i classici **puntini** della lista, che poi rimuoverai con il CSS (`list-style: none`)
    

È una soluzione sensata se:

- pensi al form come a un elenco di **voci**
    
- ti piace avere una struttura “uno sotto l’altra” pulita
    

#### 4.2. Strada B – `div` come contenitori generici

L’alternativa (molto usata) è:

```html
<section>
  <h3>Dati anagrafici</h3>

  <div>
    <label for="nome">Nome</label>
    <input id="nome" name="nome" type="text" />
  </div>

  <div>
    <label for="cognome">Cognome</label>
    <input id="cognome" name="cognome" type="text" />
  </div>
</section>
```

Qui i `div` sono:

- elementi **block**: occupano tutta la riga
    
- perfetti per avere ogni “riga di form” separata
    

Il `div` non ha **semantica**:

- `section` indica una sezione
    
- `form` indica un form
    
- `h3` indica un titolo
    
- `div` è un contenitore generico: “serve a tutto e a niente”
    

Ma proprio per questo è flessibile:

- puoi dargli una classe (`class="form-row"`, `class="field-wrapper"`, ecc.)
    
- puoi usarlo come base per il layout (flex, grid, ecc.)
    

---

### **5. `section` vs niente `section`: cosa cambia davvero**

Prendiamo due versioni:

```html
<!-- Versione A con section -->
<section>
  <h3>Dati anagrafici</h3>
  <div>
    <label for="nome">Nome</label>
    <input id="nome" name="nome" type="text" />
  </div>
</section>
```

```html
<!-- Versione B senza section -->
<h3>Dati anagrafici</h3>
<div>
  <label for="nome">Nome</label>
  <input id="nome" name="nome" type="text" />
</div>
```

Dal punto di vista **visivo**, se non applichi CSS specifico,  
non cambia praticamente niente.

La differenza è:

- **semantica e organizzativa**:
    
    - con `section` hai un contenitore logico in più
        
    - il codice è più leggibile: si vede subito il blocco “Dati anagrafici”
        
    - è più facile applicare stili “a blocchi” in futuro (padding, sfondi, bordi, ecc.)
        

Quindi:

- se hai **sezioni concettualmente distinte**, usa `section`
    
- se il form è semplice e corto, puoi anche farne a meno
    

---

### **6. Come scegliere cosa usare davvero**

Riassumiamo le combinazioni possibili:

- `fieldset + legend`
    
    - pro: bordo automatico + titolo di sezione
        
    - contro: stile base imposto dal browser, meno flessibile
        
- `section + h3`
    
    - pro: semantica chiara, ottimo per form grandi, leggibile nel codice
        
    - contro: richiede CSS per la resa grafica
        
- `ul/ol + li`
    
    - pro: ogni `li` è una riga di form, chiaro e ordinato
        
    - contro: puntini da togliere via CSS, semantica “lista” che a volte non è fondamentale
        
- `div`
    
    - pro: super flessibili, perfetti come “righe” (`form-row`, ecc.)
        
    - contro: zero significato semantico di per sé
        

La combinazione che l’insegnante dice di preferire è:

> **`section` + `div`**

Perché:

- `section` dà struttura logica a blocchi di campi
    
- `div` raggruppa le singole righe del form (label + input, label + select, ecc.)
    
- il codice risulta **molto leggibile** e pronto per il CSS
    

Esempio finale “tipo”:

```html
<form>

  <section>
    <h3>Dati principali</h3>

    <div>
      <label for="nome">Nome</label>
      <input id="nome" name="nome" type="text" />
    </div>

    <div>
      <label for="cognome">Cognome</label>
      <input id="cognome" name="cognome" type="text" />
    </div>
  </section>

  <section>
    <h3>Dati finanziari</h3>

    <div>
      <label for="iban">IBAN</label>
      <input id="iban" name="iban" type="text" />
    </div>
  </section>

  <button type="submit">Invia</button>

</form>
```

---

### **7. Struttura: cosa serve a chi**

Idea chiave della lezione:

- **Label, titoli, testi, legenda**
    
    - aiutano l’**utente** a capire cosa sta compilando
        
    - aiutano la **leggibilità** per chi legge la pagina
        
- **Section, fieldset, div, li**
    
    - spesso servono più a **te come sviluppatore**:
        
        - per organizzare il codice
            
        - per preparare un layout gestibile in CSS
            
        - per mantenere il form in futuro
            

Quindi, quando progetti un form:

1. pensa all’**utente**:
    
    - campi chiari
        
    - label precise
        
    - sezioni comprensibili (“Dati anagrafici”, “Dati di contatto”, …)
        
2. pensa al **codice**:
    
    - sezioni (`section` / `fieldset`) dove ha senso
        
    - righe di campo ben raggruppate (`div` o `li`)
        
    - niente spaghetti di input buttati uno sotto l’altro
        

---

### **8. Riepilogo**

- Abbiamo visto come **strutturare** un form, non solo inserire campi.
    
- `fieldset + legend` offrono un modo rapido per creare gruppi con bordo e titolo.
    
- `section + titoli` sono ottimi per form grandi e complessi.
    
- `ul/ol + li` e `div` permettono di allineare i campi in modo ordinato.
    
- `div` e `section` non cambiano la resa grafica da soli, ma migliorano:
    
    - semantica
        
    - organizzazione del codice
        
    - possibilità di styling futuro
        

---

Nella prossima lezione scenderemo ancora di più nel dettaglio:  
studieremo **la struttura interna di un form control**, cioè come è composto un singolo campo (input) e tutti gli attributi importanti che può avere.