## **Lezione Unica – Hacking CSS: capire cosa puoi fare _e cosa non puoi fare_ con HTML/CSS**

_(sezione dimostrativa di sicurezza, non pratica di attacco)_

Questa lezione serve per farti capire **quanto potere hai già** con solo HTML + CSS  
e, soprattutto, **quanto è fragile un sito che non prende le giuste contromisure**.

Non è una lezione per hackerare siti, ma per **capire la sicurezza web**.

---

## **1. Il punto chiave: il browser scarica TUTTO**

Quando apri una pagina web, accadono due cose fondamentali:

1. **Scarichi tutti i file localmente**: HTML, CSS, JS, immagini… tutto finisce nel tuo browser.
    
2. Il browser **esegue** ciò che gli viene dato.
    

Questo significa che:

- quello che vedi è _tuo_ → puoi modificarlo liberamente;
    
- lo puoi alterare in modo temporaneo, senza davvero toccare il server.
    

**Non stai hackerando il sito, stai hackerando la _tua copia_ della pagina.**

---

## **2. Perché puoi modificare tutto: F12**

È semplicissimo:

1. Premi **F12** (DevTools).
    
2. Apri il pannello “Elements”.
    
3. Qualunque cosa selezioni puoi:
    
    - eliminarla,
        
    - cambiarla,
        
    - modificarne colori, testo, struttura…
        

Esempio:

- cambi “Rifiuta e abbonati” in **“Segui Edoardo Midali”** → funziona.
    
- cambi lo sfondo → funziona.
    

Perché?  
**Perché la pagina è già in mano tua.**

---

## **3. Bypass elementi fastidiosi: banner, pop-up, cookie wall**

### Esempio: Corriere o Repubblica

Hai un banner che copre tutto:

- lo selezioni,
    
- premi `Canc`,
    
- sparisce.
    

Ma non puoi scrollare?  
Spesso il `body` ha una classe tipo:

```css
.no-scroll {
  position: fixed;
  overflow: hidden;
}
```

Tu vai sul tag `<body>` → togli `"no-scroll"` → scroll sbloccato.

È tutto **lato tuo**, non lato server.

---

## **4. Cos’è che _non_ puoi fare?**

Tutto ciò che richiede interazione col **server**.

Esempio:

- aumentare lo stipendio,
    
- pagare 0€ un articolo,
    
- prendere un prodotto gratis,
    
- modificare dati riservati,
    
- accedere ad aree non autorizzate.
    

Calvin Klein (l’esempio del corso):  
Cambiare a schermo il prezzo da 99€ a 0€ è facilissimo.  
Ma quando premi “Aggiungi al carrello”, il server usa il suo valore:

✔ riceve **il vero prezzo** dal database  
✖ NON riceve il prezzo manipolato dal tuo CSS

Perciò:

**Puoi ingannare te stesso, non i server.**

---

## **5. Bypass dei paywall: come funziona realmente**

Alcuni giornali fanno così:

1. Ti inviano la pagina intera, contenuto incluso.
    
2. Una volta caricata, **JavaScript**:
    
    - oscura il contenuto,
        
    - mostra il paywall,
        
    - blocca lo scroll.
        

Ma siccome il contenuto **c’è ed è già stato scaricato**, puoi vederlo:

- eliminando il div che oscura,
    
- togliendo la classe che blocca lo scroll,
    
- guardando il _sorgente pagina_.
    

E infatti:

```
Ctrl + U → Visualizza sorgente
Ctrl + F → cerca il titolo
↓
Tutto il contenuto dell’articolo è lì
```

Non è “hacking”: è **mancanza di sicurezza da parte loro**.

---

## **6. Dove i giornali più seri fanno meglio**

Alcuni paywall più avanzati:

- **non inviano proprio il contenuto** se non hai i cookie corretti,
    
- caricano il testo **via API**, solo dopo verifica sul server.
    

In quel caso:

- non puoi bypassare nulla col CSS,
    
- e nemmeno guardare il sorgente.
    

---

## **7. La Repubblica: un esempio di paywall debole**

Come mostrato nel corso:

- hanno un banner fatto con **Yubenda**,
    
- due elementi HTML da tirare via,
    
- e l’articolo rimane accessibile.
    

Questo significa:

- il paywall è _fiacco_,
    
- la sicurezza è solo visiva,
    
- niente server-side.
    

---

## **8. Perché è importante per te come sviluppatore**

Se crei un **e-commerce** o un **blog premium**, devi ricordarti questo:

> Non usare MAI valori presi dal DOM come valori “veri”.

Esempio grave:

```html
<div class="price">€29.99</div>
```

e poi:

```js
// ERRORE GIGANTESCO
const prezzo = document.querySelector('.price').innerText;
```

Basta 1 persona che fa:

- F12
    
- cambia `.price` in `0.00`
    

e se tu usi quel valore… hai regalato un prodotto.

**Tutto ciò che conta deve essere deciso dal server.**

---

## **9. Messaggio finale della lezione**

Questa sezione serve a farti capire 3 cose fondamentali:

### **1. Tu puoi sempre modificare ciò che è già nel tuo browser.**

CSS e HTML ti permettono di alterare tutto ciò che hai scaricato.

### **2. Quindi i siti non sono sicuri se non proteggono i dati lato server.**

Il client è solo un “visualizzatore” → non va mai fidato.

### **3. Molti siti famosi usano ancora paywall improvvisati.**

Sicurezza _visiva_, non reale.

---

## **E da qui si parte con:**

### **Animazioni CSS**

### **Transform**

### **Transition**

### **Keyframes**

### **E tutto ciò che serve per il design avanzato.**
