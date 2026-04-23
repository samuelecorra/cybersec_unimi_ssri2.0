# **Lezione 7: La struttura basica di una pagina HTML**

## **1. Introduzione: il “boilerplate” HTML**

Quando creiamo una nuova pagina HTML, Visual Studio Code ci permette di generare rapidamente il codice iniziale digitando:

```
html:5
```

e premendo **TAB**.

Il risultato è il cosiddetto **boilerplate**: lo scheletro essenziale da cui parte ogni pagina HTML moderna.  
Questo boilerplate contiene già tutti gli elementi fondamentali richiesti dagli standard del web: dichiarazione del tipo di documento, apertura e chiusura dell’elemento `<html>`, le due sezioni principali (`<head>` e `<body>`), e alcuni meta tag importanti.

In questa lezione analizziamo con precisione **a cosa serve ogni parte**, così da comprendere davvero cosa stiamo vedendo.

---

## **2. `<!DOCTYPE html>`: dichiarazione del tipo di documento**

Il primo elemento è:

```html
<!DOCTYPE html>
```

Questa riga non è un tag HTML, ma una **dichiarazione**.  
Serve a dire al browser:

> “Questo documento è una pagina HTML scritta secondo gli standard moderni (HTML5).”

In assenza di `<!DOCTYPE html>` i browser potrebbero interpretare il contenuto in una modalità di compatibilità (“quirks mode”), causando comportamenti imprevedibili.

È quindi **obbligatorio** inserirlo sempre.

---

## **3. L’elemento `<html>` e l’attributo `lang`**

Subito sotto troviamo:

```html
<html lang="en">
```

Il tag `<html>` racchiude **tutta** la pagina: è l'elemento radice del documento.

### **3.1 L’attributo `lang`**

`lang` indica la lingua principale del contenuto.

Esempi:

- `lang="en"` → inglese
    
- `lang="it"` → italiano
    
- `lang="de"` → tedesco
    
- `lang="fr"` → francese
    

A cosa serve?

- Ai motori di ricerca (per capire la lingua del contenuto).
    
- Agli screen reader (per leggere correttamente il testo, con la pronuncia giusta).
    
- All’accessibilità in generale.
    

Se togliamo questo attributo, gli strumenti di accessibilità spesso mostrano un avviso o un errore.  
Con l’estensione **Edge Tools** in VS Code, appare infatti l’indicazione:

> “L’elemento `<html>` deve avere l’attributo `lang`”.

Per i nostri documenti useremo **sempre**:

```html
<html lang="it">
```

---

## **4. Le due sezioni principali: `<head>` e `<body>`**

La pagina HTML è divisa in due macro-aree:

### **4.1 `<head>`: la testa del documento**

Contiene **metadati** e informazioni non visibili sullo schermo del browser.

Esempi di ciò che può contenere:

- Il **titolo** della pagina (quello che compare nella tab del browser e su Google).
    
- Le impostazioni di encoding.
    
- La descrizione SEO.
    
- Link agli stili CSS.
    
- Script di tracking.
    
- Icona del sito (favicon).
    
- Dati per i social media (Open Graph).
    
- Metadati per responsività e mobile.
    

Esempio:

```html
<head>
  <meta charset="UTF-8" />
  <title>Document</title>
</head>
```

Notate che:

- Questa parte **non appare** nella pagina visibile.
    
- La vediamo solo nella tab del browser o nei risultati dei motori di ricerca.
    

### **4.2 `<body>`: il contenuto visibile**

Tutto ciò che un utente vede sulla pagina si trova dentro `<body>`.

Esempi:

- Testi
    
- Titoli
    
- Immagini
    
- Pulsanti
    
- Tabelle
    
- Video
    
- Link
    
- Menu
    
- Paragrafi e blocchi di contenuto
    

Esempio:

```html
<body>
  <h1>Ciaooooo qwerty</h1>
</body>
```

Se modifichiamo il contenuto del body e salviamo, la pagina cambia immediatamente (soprattutto con Live Server attivo).

---

## **5. Analisi dettagliata del boilerplate generato da `html:5`**

Quando scriviamo `html:5` otteniamo uno scheletro simile a questo:

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Document</title>
</head>
<body>
  
</body>
</html>
```

Vediamo cosa fa ogni parte.

### **5.1 `<meta charset="UTF-8">`**

Indica quale sistema di codifica usare.

`UTF-8` è lo standard moderno per scrivere:

- lettere accentate (`à è ò ì`)
    
- caratteri speciali
    
- simboli internazionali
    

Se mancasse, rischieremo errori nei caratteri.

---

### **5.2 `<meta http-equiv="X-UA-Compatible" content="IE=edge">`**

Serve per compatibilità con alcune vecchie versioni di Internet Explorer.

Oggi è quasi sempre irrilevante, ma viene incluso per completezza.

---

### **5.3 `<meta name="viewport">`**

È uno dei meta tag più importanti per il web moderno.

Dice al browser mobile:

> “Mostra la pagina adattandola alla larghezza dello schermo.”

Senza questo tag, i siti da smartphone apparirebbero ingigantiti e difficili da usare.

---

## **6. Capire la differenza tra ciò che sta nel `<head>` e ciò che sta nel `<body>`**

Guarda questo esempio pratico:

### Title nel head:

```html
<title>Document prova</title>
```

→ appare nella **tab del browser** e nei risultati Google.

### Contenuto nel body:

```html
<h1>Ciao mondo!</h1>
```

→ appare direttamente **sulla pagina**.

Questa distinzione è fondamentale.

---

## **7. Costruire una pagina HTML “a mano”**

Se vogliamo creare una pagina senza usare `html:5`, il minimo indispensabile è:

```html
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>Titolo della pagina</title>
</head>
<body>
  <!-- contenuto visibile -->
</body>
</html>
```

Questi sono gli elementi _minimi_ per una pagina funzionante, completa e leggibile dai browser e dagli strumenti di accessibilità.

---

## **8. Perché insistiamo sul `lang="it"`**

Non è un semplice dettaglio estetico.

Serve per:

- SEO
    
- Accessibilità
    
- Screen reader
    
- Strumenti automatici di analisi
    
- Standard del W3C
    

Se non specifichiamo la lingua:

- Google non capisce esattamente il target linguistico,
    
- gli screen reader non sanno quale pronuncia utilizzare,
    
- alcuni validatori segnalano un errore.
    

---

## **9. Conclusione**

Ora conosci:

- la struttura fondamentale di una pagina HTML;
    
- il significato di `<!DOCTYPE html>`;
    
- l’importanza dell’attributo `lang`;
    
- la divisione tra `<head>` (informazioni e metadati) e `<body>` (contenuto visibile);
    
- i meta tag essenziali;
    
- come generare e comprendere un boilerplate HTML.
    

Dalla prossima lezione entreremo finalmente nel cuore della sintassi:

➡️ **cosa sono i tag, gli elementi e gli attributi HTML**  
➡️ **come funziona la struttura annidata del codice**  
➡️ **come si costruisce contenuto semantico e accessibile**
