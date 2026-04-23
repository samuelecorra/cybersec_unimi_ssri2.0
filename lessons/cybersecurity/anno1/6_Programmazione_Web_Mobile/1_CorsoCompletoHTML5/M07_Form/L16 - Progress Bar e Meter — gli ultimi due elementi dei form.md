## **Lezione 16: Progress Bar e Meter — gli ultimi due elementi dei form**

Con questa lezione chiudiamo l’intera rassegna degli elementi HTML legati ai form. Esaminiamo due tag particolari: **`<progress>`** e **`<meter>`**. A prima vista sembrano simili, ma hanno **obiettivi completamente diversi**.

---

## **1. `<progress>` — barra di avanzamento**

### **Definizione**

`<progress>` rappresenta un avanzamento **che parte sempre da 0** e cresce verso un valore massimo.

Esempi d’uso classici:

- download / upload file
    
- caricamenti
    
- avanzamento di una procedura
    
- lettura/scorrimento di una pagina (progress bar in alto)
    

### **Sintassi**

```html
<progress max="100" value="25">25%</progress>
```

### **Comportamento**

- **`max`** è il traguardo finale
    
- **`value`** è il punto raggiunto
    
- la barra cresce **sempre** dal punto 0 fino al massimo
    

### **Nota importante**

Nel caso in cui il browser non supporti `<progress>`, il testo interno (`25%`) viene mostrato come fallback. I browser moderni lo supportano tutti.

### **Esempio pratico**

```html
<progress max="100" value="25"></progress>
```

Risultato: barra al 25%.

---

## **2. `<meter>` — valori entro un intervallo con soglie**

### **Definizione**

`<meter>` rappresenta un valore che si trova **all’interno di un intervallo** e permette di definire:

- **min** (limite inferiore)
    
- **max** (limite superiore)
    
- **low** (inizio “zona di attenzione bassa”)
    
- **high** (inizio “zona di attenzione alta”)
    
- **optimum** (valore ideale, opzionale)
    

A differenza della progress bar:

- il valore **non parte da 0**, ma può partire da qualsiasi punto (`min`)
    
- il colore può cambiare in base a dove cade il `value`
    
- è perfetto per dati che hanno threshold (temperature, carichi, indicatori)
    

### **Sintassi**

```html
<meter min="0" max="100" value="25"></meter>
```

### **Aggiunta delle soglie**

```html
<meter
  min="0"
  max="100"
  low="25"
  high="70"
  value="24">
</meter>
```

Effetti:

- da `25` in giù = **fuori range**, di solito giallo
    
- da `26` a `69` = **range buono**, di solito verde
    
- da `70` in su = **range critico**, di solito giallo o arancione
    

### **Esempio pratico completo**

```html
<meter
  min="0"
  max="100"
  low="25"
  high="70"
  optimum="50"
  value="30">
</meter>
```

`optimum="50"` indica il valore ideale (opzionale: lo usi solo se ti serve).

---

## **3. Differenza sintetica tra Progress e Meter**

|Elemento|Quando usarlo|Come funziona|
|---|---|---|
|**`<progress>`**|Avanzamento da 0 a un massimo (download, upload, caricamenti)|Parte **sempre da 0** e va verso `max`|
|**`<meter>`**|Misurare un valore entro range con soglie (temperatura, punteggi, intensità)|Ha `min`, `max`, `low`, `high`, `optimum` e cambia colore|

---

## **4. Esempio visivo per capire la differenza**

### **Progress bar (avanzamento)**

```
0%  ██████▒▒▒▒▒▒▒▒ 100%
```

### **Meter (valutazione entro soglie)**

```
min  [verde verde verde][giallo][rosso] max
         value=30
```

---

## **5. Conclusione**

Con questi due ultimi elementi:

- sai costruire **qualsiasi tipo di input**
    
- sai gestire upload, selezioni, autocompletamenti, valori numerici, date, slider, button, checkbox, radio, ecc.
    
- hai completato **la panoramica totale degli elementi legati ai form in HTML5**
    

Ora passiamo agli **esercizi**, che ti permetteranno di mettere in pratica ogni singolo tag visto finora.