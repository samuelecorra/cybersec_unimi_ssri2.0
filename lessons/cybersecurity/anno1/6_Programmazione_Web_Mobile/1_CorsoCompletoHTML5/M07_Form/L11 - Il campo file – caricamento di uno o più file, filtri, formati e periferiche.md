## **Lezione 11: Il campo `file` – caricamento di uno o più file, filtri, formati e periferiche**

---

### **1. Introduzione**

Il campo file è uno degli input più importanti nel mondo reale:

- upload di foto profilo
    
- caricamento documenti (PDF, DOCX, JPG…)
    
- upload di video/audio
    
- gestione di allegati in un gestionale
    
- acquisizione da fotocamera, microfono, videocamera
    
- invio di file su mobile
    

Per questo motivo HTML dedica un input specifico:

```html
<input type="file">
```

E dietro c’è molto da capire.

---

## **2. Struttura base di un campo file**

Partiamo dalla versione più semplice:

```html
<label for="file">Foto profilo:</label>
<input type="file" id="file" name="file">
```

Comportamento:

- clicchi sul pulsante → si apre il file picker del sistema operativo
    
- puoi selezionare **un solo file**
    
- il nome del file scelto appare accanto al campo
    

È identico su desktop e mobile, cambia solo l’interfaccia grafica del picker.

---

## **3. Caricare _più_ file: attributo `multiple`**

Di default: **un solo file alla volta**.

Per consentire selezioni multiple:

```html
<input type="file" id="file" name="file" multiple>
```

Ora puoi:

- trascinare più file
    
- selezionare più elementi nella finestra di dialogo
    
- inviarli in un unico form
    

---

## **4. Limitare i tipi di file con `accept`**

Questo attributo permette di filtrare quali file l’utente può vedere/selezionare.

### **4.1. Accettare tutte le immagini**

```html
<input type="file" accept="image/*">
```

Funziona come “famiglia di file”.  
**Mostra solo file immagine**: PNG, JPG, WEBP, GIF, SVG…

### **4.2. Accettare solo un formato specifico**

```html
<input type="file" accept="image/webp">
<input type="file" accept="image/png">
<input type="file" accept="video/mp4">
```

Il picker mostrerà **solo** quei tipi.

### **4.3. Accettare più formati (in lista)**

Basta separarli con virgole:

```html
<input type="file" accept="image/webp, image/png">
```

### **4.4. Formati multipli, anche diversi**

```html
<input type="file" accept="image/*, video/*">
```

Così accetti _qualsiasi_ immagine + _qualsiasi_ video.

---

## **5. Far scattare la fotocamera (mobile) – attributo `capture`**

Questo è interessante: su smartphone possiamo chiedere **acquisizione diretta** da dispositivi interni.

Esempi:

### **5.1. Fotocamera**

```html
<input type="file" accept="image/*" capture="camera">
```

Aprirà direttamente:

- l’app fotocamera (mobile)
    
- oppure la webcam (su alcuni sistemi desktop)
    

### **5.2. Videocamera**

```html
<input type="file" accept="video/*" capture="camcorder">
```

### **5.3. Microfono (registratore vocale)**

```html
<input type="file" accept="audio/*" capture="microphone">
```

Questo permette:

- registrazioni vocali al volo
    
- invio di video fatti sul momento
    
- foto scattate dalla camera frontale/posteriore
    

Tutto senza scrivere una riga di JavaScript.

---

## **6. Cosa succede ai file dopo la selezione?**

Importantissimo:

> **HTML può leggere solo il nome dei file, non il loro contenuto.**  
> Il vero upload avviene SOLO quando il form viene inviato al server.

Quindi:

- selezionare = _preparare_ l’upload
    
- inviare = _caricare realmente i file_
    

Senza backend, il form file “mostra solo l’interfaccia”.

Con linguaggi come PHP, Node, Python, Java… vedremo poi:

- come leggere i file
    
- come salvarli
    
- come gestire upload multipli
    
- come verificare dimensione, formato e tipo
    
- come proteggerci da malware nei file caricati
    

(Sono tutte tematiche dei moduli avanzati.)

---

## **7. Riepilogo degli attributi fondamentali**

|Attributo|Funzione|
|---|---|
|`multiple`|permette selezione di più file|
|`accept="..."`|filtra tipi di file visibili/selezionabili|
|`capture="camera"`|foto diretta|
|`capture="camcorder"`|video diretto|
|`capture="microphone"`|audio diretto|

---

## **8. Il campo file + CSS**

Hai sicuramente notato che il campo file, esteticamente, è uno degli elementi più brutti di HTML.

È normale.

Per avere:

- dropzone
    
- aree drag & drop
    
- pulsanti personalizzati
    
- anteprime delle immagini
    
- box animati
    

dovremo usare **CSS** + **JavaScript**.

Lo faremo più avanti, ma la logica di base rimane sempre:

```html
<input type="file">   ← è questo il cuore dell’upload
```

Il resto è presentazione.

---

## **9. Conclusione**

Con questa lezione abbiamo imparato:

- a inserire un campo file
    
- a permettere selezioni multiple
    
- a filtrare file per tipo
    
- a integrare fotocamera, videocamera e microfono
    
- a distinguere tra “selezione” e “upload” reale
    
- a preparare form professionali per caricamenti complessi
    

---

## **10. Prossima lezione**

Ora ci spostiamo sui **bottoni (`type="button"`, `type="submit"`, `type="reset"`)**,  
perché sono molto di più di un semplice “pulsante con del testo dentro”.