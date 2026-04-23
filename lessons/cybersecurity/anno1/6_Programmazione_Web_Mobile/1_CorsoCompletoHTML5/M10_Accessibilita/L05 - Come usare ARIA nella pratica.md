## **Lezione 5: Come usare ARIA nella pratica (prima applicazione reale)**

### **1. Introduzione: la verità su ARIA pratica**

Arrivati a questo punto abbiamo capito cos’è ARIA, da dove arriva e perché esiste.  
Ora facciamo ciò che serve davvero: **vedere come si usa in HTML**, concretamente, senza studiare le 250+ proprietà della documentazione.

Ti mostro:

- **i ruoli che potrebbero servirti**
    
- **le proprietà essenziali**
    
- **gli stati fondamentali**
    
- **i casi d’uso che puoi usare da subito**
    
- **come evitare gli errori più comuni**
    

Non toccheremo tutto, perché:

1. molte proprietà ARIA **richiedono JavaScript**, e non le conoscete ancora
    
2. sarebbe ridondante caricare cento concetti tutti insieme
    
3. se scrivete _buon HTML_, ARIA vi serve pochissimo
    

Ti mostro **solo ciò che è davvero utile adesso**, così hai una base solida e concreta.

---

## **2. I ruoli (role="…")**

I ruoli trasformano un elemento qualunque (div, span, ecc.) in **qualcosa di semantico agli occhi dello screen reader**.

Esempio:

```html
<div role="navigation"></div>
```

Per una persona normodotata, resta un div.  
Per lo screen reader, diventa una zona di navigazione.

Ma — cosa fondamentale —

### **→ se usi già i tag giusti, non devi mettere il role.**

Perché `<nav>` è già navigation.  
`<header>` è già banner.  
`<main>` è già main.  
`<button>` è già button.

Il role serve in due scenari reali:

1. **stai lavorando su un sito vecchio** pieno di `<div>`
    
2. **hai bisogno di un wrapper extra** per motivi di stile/CSS e quel wrapper rompe la semantica
    

Esempio concreto che può capitare:

```html
<div class="box" role="button">Avanti</div>
```

Con JavaScript intercetti il click.  
Con ARIA fai sì che uno screen reader lo interpreti come un bottone.

Ma: **meglio evitare**.  
Se puoi usare `<button>`, usa `<button>`.

---

## **3. ARIA live: contenuti aggiornati automaticamente**

Questo è un caso utilissimo nei siti “che cambiano da soli”:

- punteggi delle partite
    
- aggiornamenti di borsa
    
- notifiche “live”
    
- dati che arrivano dal server
    

HTML da solo non ha un sistema di “notifica” per gli screen reader.  
ARIA sì.

Esempio:

```html
<section aria-live="polite">
  Punteggio: 1 - 0
</section>
```

I tre valori possibili:

### **1) aria-live="off"**

Non notificare niente (default).

### **2) aria-live="polite"**

Notifica _solo_ quando l’utente non è impegnato.

Esempio:  
sta ascoltando il contenuto → non interrompere.  
Ha finito → annuncia l’update.

### **3) aria-live="assertive"**

Interrompi TUTTO e annuncia subito.  
Usalo solo se l’informazione è veramente critica.

---

### **Atomic: leggere solo l’update o tutto il blocco**

`aria-atomic="true"` significa:

> “quando aggiorni qualcosa dentro questo blocco, leggi anche il contesto”.

Esempio pratico:

```html
<section aria-live="assertive" aria-atomic="true">
  <h2>Risultato</h2>
  <p>3 - 2</p>
</section>
```

Lo screen reader non dirà solo “3 - 2”.  
Ma:

> “Risultato: 3 - 2”.

Molto più chiaro per chi non vede.

---

## **4. Aria-busy: indica che una parte della pagina è “in caricamento”**

Esempio classico:

```html
<section aria-busy="true">
  Caricamento in corso…
</section>
```

Finché aria-busy è true, lo screen reader **non legge il contenuto interno**, perché sta cambiando.

Quando lo script mette:

```html
<section aria-busy="false">
```

→ allora può leggere il contenuto definitivo.

Serve nei casi di interfacce dinamiche, con contenuto che cambia velocemente.

---

## **5. tabindex: rendere tababile ciò che non lo è**

Il tab index è fondamentale anche **senza JS**.

### Elementi tababili di default:

- link `<a>`
    
- bottoni `<button>`
    
- input `<input>`
    
- textarea
    
- select
    

### Elementi **non** tababili di default:

- div
    
- span
    
- li
    
- h1, h2, h3…
    
- immagini
    
- qualunque cosa non interattiva
    

Se vuoi far arrivare il focus su un elemento non tababile, fai così:

```html
<div tabindex="0">Elemento raggiungibile col tab</div>
```

Risultato:

- div diventa tababile
    
- riceve il focus
    
- può essere gestito dalla tastiera
    

Combinato con:

```html
role="button"
```

il div diventa per lo screen reader un vero bottone.

### Esempio completo:

```html
<div role="button" tabindex="0">
  Cliccami
</div>
```

Attenzione:  
questo è un “hack” accettato solo quando **non puoi usare un vero bottone**.  
Non una scorciatoia.

---

## **6. ARIA label: dare un nome a un elemento che non ce l’ha**

Caso tipico: bottone con l’icona e basta.

```html
<button>
  <svg>…</svg>
</button>
```

Lo screen reader cosa dice?

> “Button… Nessun nome”.

Soluzione:

```html
<button aria-label="Chiudi finestra">
  <svg>…</svg>
</button>
```

Semplice ed efficacissimo.

### Perché è fondamentale?

Perché l’utente vedente vede la X.  
Chi non vede non vede niente.

---

## **7. aria-hidden: nascondere elementi solo allo screen reader**

Caso tipico: immagine puramente decorativa.

```html
<img src="copertina.jpg" aria-hidden="true" />
```

Risultato:

- visibile per chi vede
    
- invisibile per screen reader
    
- non viene annunciata
    
- non crea rumore inutile
    

Funziona anche con elementi di layout:

```html
<div class="ombreggiatura" aria-hidden="true"></div>
```

---

## **8. Label e ARIA: quando usare cosa**

### Caso ideale: label + input

```html
<label for="email">Email</label>
<input id="email" type="email">
```

Questo è **perfetto**.  
Non serve aria-label.

### Quando usare aria-label?

- quando lo stile non permette una label visiva
    
- quando il bottone è fatto solo da un’icona
    
- quando il contenuto testuale non può essere inserito
    

### Quando NON usarlo?

- quando potresti semplicemente usare la label corretta
    
- quando esiste un modo semantico per far capire cosa è l’elemento
    

---

## **9. Il vero messaggio della lezione**

Se scrivi HTML:

- semantico
    
- pulito
    
- ordinato
    
- usando i tag corretti
    
- usando label e alt con criterio
    

… non avrai quasi bisogno di ARIA.

ARIA serve per:

- casi “speciali”
    
- interfacce dinamiche
    
- elementi complessi
    
- componenti interattivi avanzati
    
- workaround per codice vecchio
    

Per ora, ti basta conoscere:

- `role`
    
- `aria-label`
    
- `aria-hidden`
    
- `aria-live`
    
- `aria-atomic`
    
- `aria-busy`
    
- `tabindex`
    

E con questo, stai già lavorando **a livello professionale**.

---

## **10. Prossimo video**

Nel prossimo video vediamo l’**accessibilità dei contenuti multimediali**:

- immagini
    
- audio
    
- video
    
- trascrizioni
    
- sottotitoli
    
- fallback