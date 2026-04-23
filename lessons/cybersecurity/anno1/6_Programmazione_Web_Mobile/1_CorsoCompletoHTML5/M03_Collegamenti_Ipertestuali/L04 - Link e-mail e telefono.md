## **Lezione 4: Link e-mail (`mailto:`) e telefono (`tel:`)**

In questa lezione vediamo due tipi speciali di link:

- i link **telefonici**: `tel:`
    
- i link **e-mail**: `mailto:`
    

Li userai praticamente su qualunque sito che abbia una sezione **“Contatti”**, **footer**, **about** ecc.

---

### **1. Link telefonici: `tel:`**

L’idea è semplice:

> Quando l’utente clicca sul link, il dispositivo prova ad **avviare una chiamata** a quel numero.

La sintassi base è:

```html
<a href="tel:+390123456789">+39 012 345 6789</a>
```

- `tel:` dice al browser: “questo è un **numero di telefono**”.
    
- `+39 012 345 6789` è solo il **testo visibile** del link.
    
- Nel valore di `href` è meglio usare il numero **pulito**, senza spazi, con prefisso internazionale:  
    `tel:+390123456789`.
    

#### **1.1. Comportamento su diversi dispositivi**

- **Su smartphone**:  
    cliccando il link, si apre direttamente la schermata della chiamata:
    
    - il numero viene pre-inserito nel tastierino;
        
    - puoi confermare la chiamata o salvarlo in rubrica.
        
- **Su PC / laptop**:
    
    - il browser chiederà con **quale app** aprire il link (`Skype`, `Teams`, `FaceTime`, `WhatsApp Desktop`…);
        
    - se non hai app collegate, può anche non succedere nulla, oppure mostrarti una finestra “Apri con…”.
        

Esempio pratico:

```html
<p>Per assistenza telefonica chiama:</p>
<a href="tel:+393331112222">+39 333 111 22 22</a>
```

---

### **2. Link e-mail: `mailto:` – la base**

L’obiettivo qui è:

> Aprire il **client di posta predefinito** con già compilato:
> 
> - destinatario
>     
> - (eventualmente) oggetto
>     
> - (eventualmente) corpo del messaggio
>     
> - (eventualmente) copie conoscenza (CC)
>     

La forma più semplice:

```html
<a href="mailto:info@esempio.it">Scrivici una e-mail</a>
```

- `mailto:` dice: “questo link serve per inviare una e-mail”.
    
- `info@esempio.it` è l’indirizzo del destinatario.
    
- “Scrivici una e-mail” è il testo cliccabile.
    

Cliccando, sul PC si aprirà il client (Outlook, Mail di Windows, Apple Mail, ecc.).  
Sul telefono, si aprirà l’app di posta (Gmail, Mail, ecc.) con il campo **A:** già compilato.

---

### **3. Più destinatari: TO e CC**

#### **3.1. Più destinatari nel campo “A:”**

Puoi indicare **più destinatari** nel campo principale, separandoli con virgola:

```html
<a href="mailto:info@esempio.it,contatti@esempio.it">
  Scrivi al nostro team
</a>
```

Questi due indirizzi andranno entrambi nel campo **A:**.

---

#### **3.2. Destinatari in copia conoscenza: `cc=`**

Per aggiungere una **copia conoscenza** (CC), si usano i **parametri** dopo il punto di domanda `?`.

Struttura generale:

```text
mailto:indirizzo_principale?cc=indirizzo_in_cc
```

Esempio:

```html
<a href="mailto:info@esempio.it?cc=admin@esempio.it">
  Scrivici (con admin in copia)
</a>
```

- `info@esempio.it` → campo **A:**
    
- `admin@esempio.it` → campo **CC:**
    

Se vuoi più indirizzi in CC:

```html
<a href="mailto:info@esempio.it?cc=admin@esempio.it,owner@esempio.it">
  Scrivici (admin e owner in copia)
</a>
```

Oppure puoi anche ripetere il parametro `cc`:

```html
<a href="mailto:info@esempio.it?cc=admin@esempio.it&cc=owner@esempio.it">
  Scrivici (admin e owner in copia)
</a>
```

Entrambe le forme sono accettate dalla maggior parte dei client.

> Regola mentale:
> 
> - Primo parametro dopo `mailto:...` → preceduto da `?`
>     
> - Parametri successivi → separati da `&`
>     

---

### **4. Impostare l’oggetto della mail: `subject=`**

Per indicare l’**oggetto** si usa il parametro `subject`.

Struttura:

```text
mailto:indirizzo?subject=TESTO_OGGETTO
```

Esempio base:

```html
<a href="mailto:info@esempio.it?subject=Richiesta%20informazioni">
  Richiedi informazioni
</a>
```

Attenzione:

- Gli **spazi** nell’URL devono essere scritti come `%20` (URL encoding).
    
- Anche caratteri speciali (accenti, &, ?, =) vanno codificati.
    

#### **4.1. Come ottenere facilmente il testo codificato**

In pratica:

1. Scrivi l’oggetto in italiano normale:
    
    - `Informazioni sui vostri prodotti`
        
2. Lo passi in un tool di **URL encoding** (online) e ottieni qualcosa tipo:
    
    - `Informazioni%20sui%20vostri%20prodotti`
        
3. Incolli quel risultato dopo `subject=`.
    

Esempio completo:

```html
<a href="mailto:info@esempio.it?subject=Informazioni%20sui%20vostri%20prodotti">
  Chiedi informazioni sui prodotti
</a>
```

---

### **5. Impostare il corpo della mail: `body=`**

Puoi pre-compilare anche il **corpo** del messaggio con il parametro `body`.

Struttura di base:

```text
mailto:indirizzo?body=TESTO_CORPO
```

Anche qui, tutti gli spazi vanno codificati con `%20`, eventuali “a capo” con `%0A` (ma non è obbligatorio per l’esercizio).

Esempio semplice:

```html
<a href="mailto:info@esempio.it?body=Buongiorno%2C%20vorrei%20maggiori%20informazioni.">
  Invia una richiesta veloce
</a>
```

---

### **6. Combinare TO + CC + SUBJECT + BODY**

Qui viene il bello: i parametri sono combinabili.

Struttura generale:

```text
mailto:destinatario_principale
  ?cc=indirizzo_in_copia1,indirizzo_in_copia2
  &subject=OGGETTO_URL_ENCODED
  &body=CORPO_URL_ENCODED
```

Mettiamola in pratica con un esempio “vero”.

#### **6.1. Esempio completo**

Vogliamo:

- inviare a: `info@esempio.it`
    
- CC: `admin@esempio.it`
    
- Oggetto: `Informazioni sui vostri prodotti`
    
- Corpo:
    
    > Buongiorno,  
    > le scrivo perché sto apprezzando molto il suo corso HTML.
    

1. Oggetto testuale:
    
    ```text
    Informazioni sui vostri prodotti
    ```
    
    → URL encoded ≈ `Informazioni%20sui%20vostri%20prodotti`
    
2. Corpo testuale (una riga, per semplicità):
    
    ```text
    Buongiorno, le scrivo perché sto apprezzando molto il suo corso HTML.
    ```
    
    → URL encoded (approssimato senza stare a perfezionare tutti gli accenti):
    
    `Buongiorno,%20le%20scrivo%20perche%20sto%20apprezzando%20molto%20il%20suo%20corso%20HTML.`
    
3. Mettiamo tutto insieme:
    

```html
<a href="mailto:info@esempio.it
?cc=admin@esempio.it
&subject=Informazioni%20sui%20vostri%20prodotti
&body=Buongiorno,%20le%20scrivo%20perche%20sto%20apprezzando%20molto%20il%20suo%20corso%20HTML.">
  Contattami per una consulenza
</a>
```

Ovviamente nella pagina lo scrivi **tutto su una riga**, il ritorno a capo qui è solo per leggibilità.

Il browser:

- aprirà il client di posta;
    
- metterà `info@esempio.it` nel campo **A:**
    
- metterà `admin@esempio.it` nel campo **CC:**
    
- compilerà **Oggetto** e **Corpo** con i testi che hai codificato.
    

---

### **7. Schema riassuntivo: sintassi di `mailto:`**

Per fissare tutto, uno schemino generico:

```text
mailto:INDIRIZZO/i
  ?cc=INDIRIZZO_IN_CC1,INDIRIZZO_IN_CC2
  &subject=OGGETTO_URL_ENCODED
  &body=CORPO_URL_ENCODED
```

In HTML:

```html
<a href="mailto:indirizzo@dominio.it
?cc=altro@dominio.it
&subject=Oggetto%20della%20mail
&body=Testo%20precompilato%20della%20mail">
  Testo del link
</a>
```

Regole chiave:

- prima dei parametri → `?`
    
- tra parametri → `&`
    
- spazi e caratteri speciali → **URL encoded** (`%20` per lo spazio)
    
- puoi omettere qualsiasi parte che non ti serve (es. solo `subject`, solo `body`, solo `cc`, ecc.)
    

---

### **8. Mini–confronto `tel:` vs `mailto:`**

|Tipo link|Schema base|Effetto principale|
|---|---|---|
|`tel:`|`href="tel:+390123456789"`|Avvia chiamata (o app chiamate)|
|`mailto:`|`href="mailto:info@esempio.it"`|Apre client mail con destinatario precompilato|

Entrambi sono normalissimi link `<a>`, ma con uno **schema** (`tel:`, `mailto:`) diverso dal solito `https:`.

---

Se vuoi, nel prossimo passaggio possiamo costruire insieme:

- la sezione **“Contatti”** della tua pagina di esercizio con:
    
    - link e-mail completo (`mailto:` con subject e body),
        
    - link telefono (`tel:`),
        
    - magari un `address` con indirizzo fisico e una piccola lista di social.