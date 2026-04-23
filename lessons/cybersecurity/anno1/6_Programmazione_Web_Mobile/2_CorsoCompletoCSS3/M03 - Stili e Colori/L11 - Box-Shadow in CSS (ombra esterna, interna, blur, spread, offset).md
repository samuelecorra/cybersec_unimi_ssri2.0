## **Lezione 11: Box-Shadow in CSS (ombra esterna, interna, blur, spread, offset)**

### **1. Introduzione alla box-shadow**

La **box-shadow** è la proprietà che permette di aggiungere ombre attorno (o dentro) un elemento.  
È una delle proprietà visive più usate nel web moderno, soprattutto per:

- card
    
- pulsanti
    
- modali
    
- elementi in evidenza
    
- effetti “material design”
    

Come sempre, esistono generatori online molto comodi, ma qui impariamo a **capire ogni parametro**, così da poter leggere e modificare qualsiasi ombra senza problemi.

---

### **2. Sintassi base della box-shadow**

La forma completa, nell’ordine corretto, è:

```
box-shadow: offset-x offset-y blur-radius spread-radius color inset;
```

I primi due valori sono obbligatori; tutti gli altri sono opzionali.

---

### **3. Offset orizzontale (offset-x)**

Esempio:

```
10px 0px
```

Significa:

- **positivo** → ombra spostata verso destra
    
- **negativo** → ombra spostata verso sinistra
    
- **0** → ombra perfettamente centrata orizzontalmente
    

---

### **4. Offset verticale (offset-y)**

Esempio:

```
0px 10px
```

Significa:

- **positivo** → ombra spostata verso il basso
    
- **negativo** → ombra spostata verso l’alto
    
- **0** → ombra centrata verticalmente
    

---

### **5. Blur radius (sfocatura)**

È il terzo parametro:

```
box-shadow: 10px 10px 15px;
```

- Più il valore è grande, più l’ombra diventa morbida e diffusa.
    
- Deve essere **solo positivo**.
    
- A valore 0, l’ombra è netta e dura.
    

Esempio:

- 5px → ombra abbastanza definita
    
- 20px → ombra morbida e naturale
    

---

### **6. Spread radius (espansione)**

È il quarto parametro:

```
box-shadow: 10px 10px 15px 20px;
```

Indica di quanto l’ombra si “espande” attorno all’elemento.

- Spread positivo → l’ombra cresce, diventa più grande dell’elemento
    
- Spread negativo → l’ombra si restringe
    

Lo spread è ciò che determina **quanto l’ombra si estende radialmente**, come se aumentasse la sua “potenza”.

---

### **7. Colore dell’ombra**

Puoi usare qualunque tipo di colore:

```
box-shadow: 10px 10px 15px 0px rgba(0,0,0,0.4);
```

In pratica:

- usa **rgba()** quando vuoi un’ombra realistica (trasparenze)
    
- evita colori puri tipo red, se non per effetti speciali
    

---

### **8. Ombra interna: `inset`**

Aggiungendo la parola chiave **inset**, l’ombra diventa interna:

```
box-shadow: inset 5px 5px 15px 0px rgba(0,0,0,0.5);
```

L’ombra si disegna **dentro** l’elemento, verso il contenuto.

Ideale per:

- effetti “incavati”
    
- input field cliccati
    
- pulsanti “premuti”
    

---

### **9. Più ombre contemporaneamente**

Possiamo usare più ombre separandole con una virgola:

```
box-shadow:
  inset 5px 5px 10px rgba(0,0,0,0.5),
  10px 10px 20px rgba(0,0,0,0.3);
```

Questo permette di creare effetti complessi, ad esempio:

- una ombra interna + una esterna
    
- più ombre colorate
    
- glow multipli
    

---

### **10. Interazione con il border-radius**

Il **border-radius** influenza _anche_ le ombre:

```
border-radius: 20px;
```

Il bordo arrotondato si applica anche alla box-shadow, rendendo coerente la curvatura dell’ombra.

Funziona **anche se il bordo non è visibile**.

---

### **11. Usare i generatori**

Generatori come _CSS box-shadow generator_ ti permettono di vedere l’ombra in tempo reale e ottenere il codice già pronto.

Vanno usati senza alcun problema, soprattutto per ombre complesse o quando serve prototipare velocemente.

La cosa importante è **sapere leggere e correggere** il codice generato, cosa che ora puoi fare perfettamente.

---

### **12. Sintesi finale dei parametri**

- **offset-x** → sposta l’ombra a destra/sinistra
    
- **offset-y** → sposta l’ombra su/giù
    
- **blur-radius** → la rende morbida
    
- **spread-radius** → la rende più grande o più piccola
    
- **color** → consigliato uso RGBA
    
- **inset** → ombra interna
    
- **virgole** → permettono più ombre
    

---

### **Conclusione**

Hai ora l'intera teoria della box-shadow completa e chiarissima:

- come funziona
    
- come regolare i valori
    
- come creare ombre realistiche
    
- come fare ombre interne
    
- come combinarne più di una
    
- come interpretare o correggere qualsiasi box-shadow generata