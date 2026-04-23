## **Lezione 5: I primi input type – `text`, `password`, `email`, `hidden`**

---

### **1. Panoramica: perché esistono tanti input type diversi**

Finora abbiamo usato quasi sempre:

- `type="text"` → campo di testo generico
    
- `type="password"` → campo di testo nascosto
    

Con HTML5 sono arrivati **molti nuovi tipi** di input, tra cui:

- `type="email"`
    
- `type="number"`
    
- `type="date"`
    
- ecc.
    

In questa lezione guardiamo bene **i primi quattro**:

- `text`
    
- `password`
    
- `email`
    
- `hidden`
    

I primi due li conosci già, ma li fissiamo bene.  
Gli altri due sono nuovi (e `hidden` è quello più “strano”, perché non si vede proprio).

---

### **2. `type="text"` – il campo di testo standard a singola riga**

Questo è il campo più semplice e più usato:

```html
<input type="text" name="nome">
```

Caratteristiche:

- **una sola riga**  
    Se provi a premere INVIO dentro il campo, **non va a capo**.  
    Per andare a capo servirebbe una `textarea`, non un `text`.
    
- usato per:
    
    - nome
        
    - cognome
        
    - codice fiscale
        
    - codice cliente
        
    - città
        
    - qualunque stringa “breve”
        

Non fa validazioni particolari:

- accetta **qualsiasi testo**
    
- non controlla se è un’email, un numero, ecc.  
    (questo lo farai tu con JavaScript o lato server.)
    

È il “coltellino svizzero” di base: se non specifichi il tipo, di default è `text`.

---

### **3. `type="password"` – stesso campo, ma nascosto**

```html
<input type="password" name="password">
```

Comportamento:

- mentre scrivi, **i caratteri vengono oscurati** (pallini, asterischi, ecc.)
    
- il valore **c’è**, ma non si vede a schermo
    

A livello di dati:

- per il browser e per il server è comunque **una stringa normale**
    
- il fatto che sia nascosta è solo una questione **visiva**
    

Uso tipico:

- password
    
- PIN (in certe interfacce)
    
- codici che non devono essere visibili a chi ti guarda lo schermo
    

La logica è:

> `text` + “oscuramento grafico” = `password`.

---

### **4. `type="email"` – campo testo, ma specializzato per email**

Passiamo a un tipo di campo introdotto con HTML5:

```html
<input type="email" name="email">
```

Sembra simile a `text`, ma in realtà aggiunge due superpoteri:

#### 4.1. Validazione base automatica

Se usiamo:

```html
<input type="email" name="email" required>
```

Succede questo:

- se il campo è **vuoto**:
    
    - e c’è `required`, il browser blocca l’invio (“Compila questo campo”)
        
    - se `required` non c’è, lo lascia vuoto e invia (nessun controllo)
        
- se il campo **non è vuoto**, HTML controlla la forma base:
    
    - deve contenere **almeno una chiocciola** `@`
        
    - deve contenere **qualcosa dopo** la chiocciola  
        (così `a@b` viene già considerato “formalmente valido”)
        

Se scrivi una cosa a caso (tipo `ciao`) e provi a inviare, il browser ti avvisa:

> “Inserisci un indirizzo email”  
> o messaggi simili, a seconda del browser/lingua.

Non è una validazione perfetta (non controlla davvero se la mail esiste),  
ma evita tanti errori di forma assurdi.

#### 4.2. Tastiera ottimizzata sui telefoni

Questo è il motivo **pratico** per cui conviene usare i tipi specifici:

- su **desktop**, vedi sempre e comunque una casella di testo
    
- su **mobile**, il sistema operativo si accorge che il campo è `type="email"` e:
    
    - ti mostra una tastiera con:
        
        - tasto `@`
            
        - tasto `.com`
            
        - spesso `.it` o altri TLD suggeriti
            

Se invece usassi:

```html
<input type="text" name="email">
```

- potresti fare TU la validazione con JavaScript
    
- ma su mobile l’utente si troverebbe la **tastiera standard**, più scomoda
    

Quindi:

> Anche se potresti far tutto con `text`, usare `type="email"`  
> migliora subito l’**esperienza utente**, soprattutto da telefono.

E questa è la chiave mentale da tenere sempre in testa:

- per te, scrivere `text` o `email` cambia quasi zero
    
- per l’utente, può cambiare moltissimo la **comodità d’uso**
    

---

### **5. UX mindset: perché fare lo sforzo di usare il tipo giusto**

Potresti dire:

> “Vabbè, metto sempre `text` e poi valido con JS. Fine.”

Tecnicamente funziona.  
Ma pensa alla realtà:

- se il tuo sito è **scomodo**:
    
    - tastiere sbagliate
        
    - validazioni strane
        
    - errori poco chiari
        
- e esiste un competitor che fa la stessa cosa ma **più usabile**…
    

…molto probabilmente l’utente:

- si innervosisce
    
- abbandona
    
- passa all’altro servizio
    

L’unico caso in cui “te lo puoi permettere” è:

> Sei l’unico al mondo a offrire quel servizio.

In tutti gli altri casi, curare questi dettagli è parte della progettazione seria.

Cambiare:

```html
<input type="text">
```

in:

```html
<input type="email">
```

è uno sforzo **zero**, ma regala:

- una prima validazione automatica
    
- una tastiera mobile più comoda
    

Vale decisamente la pena.

---

### **6. `type="hidden"` – il campo che non si vede, ma viaggia con i dati**

Ed eccoci al campo più “misterioso”:

```html
<input type="hidden" name="id_utente" value="12345">
```

Comportamento:

- **non compare** nella pagina:  
    niente casella, niente label, niente spazio grafico
    
- l’utente:
    
    - **non lo vede**
        
    - non può cliccarlo
        
    - non può modificarlo
        
- **viene comunque inviato** al server insieme a tutti gli altri campi del form
    

Il browser lo tratta come un qualsiasi `input` dal punto di vista dei dati,  
ma non lo rende visibile in UI.

È quindi perfetto per dati che:

- non devono essere modificabili dall’utente
    
- non devono essere neanche visibili
    
- ma devono accompagnare il form nella richiesta HTTP
    

#### 6.1. Esempio 1 – Timestamp (data/ora della compilazione)

Immagina di voler salvare anche “quando” è stato compilato il form.

Potresti usare JavaScript per mettere un valore “timestamp” (numero che rappresenta una certa data/orario) in un `hidden`:

```html
<input
  type="hidden"
  name="timestamp_compilazione"
  value="1732550400">
```

Il server, ricevendo il form, può dire:

> “Ok, questo form è stato compilato in questo istante (rappresentato dal timestamp).”

Nota:  
il server spesso può calcolare da solo la data/ora di arrivo della richiesta,  
quindi non sempre serve il timestamp dal client.  
Ma è un esempio classico di informazione “non visibile” che viaggia col form.

#### 6.2. Esempio 2 – ID utente “nascosto”

Altro scenario molto comune:

- l’utente è loggato
    
- sta modificando i propri dati (nome, cognome, IBAN, ecc.)
    

Tu vuoi sapere **per quale utente** arrivano quei dati.  
Allora nel form aggiungi:

```html
<input
  type="hidden"
  name="id_utente"
  value="USER_938475923">
```

Così, quando il form viene inviato, il server riceve:

- nome, cognome, email…
    
- e **anche** `id_utente="USER_938475923"`
    

E può fare:

> “Ok, aggiorno i dati dell’utente con ID `USER_938475923`.”

L’utente non deve mai:

- vedere l’ID interno
    
- modificarlo
    

Per questo `hidden` è perfetto.

---

### **7. Riassunto della lezione**

Abbiamo visto i primi quattro tipi di input:

1. **`type="text"`**
    
    - campo di testo a una sola riga
        
    - qualsiasi stringa, nessun controllo automatico speciale
        
2. **`type="password"`**
    
    - identico a `text` come dati
        
    - ma i caratteri sono **oscurati** a schermo
        
3. **`type="email"`**
    
    - campo di testo specializzato per email
        
    - validazione base: presenza di `@` e qualcosa dopo
        
    - su mobile, tastiera ottimizzata (tasto `@`, `.com`, ecc.)
        
    - migliora in modo importante l’esperienza utente
        
4. **`type="hidden"`**
    
    - campo **invisibile** all’utente
        
    - non cliccabile, non modificabile
        
    - ma il suo `name` + `value` vengono comunque inviati al server
        
    - usatissimo per:
        
        - timestamp
            
        - ID utente
            
        - valori tecnici di contesto
            

---

Nella prossima lezione continueremo la **carrellata dei tipi di input**,  
vedendo altri `type` HTML5 che ti aiuteranno a rendere i form sempre più chiari, precisi e **amichevoli** da usare, soprattutto su mobile.