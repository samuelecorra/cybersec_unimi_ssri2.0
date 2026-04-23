## **Lezione 5 – `object-fit` e il ridimensionamento intelligente delle immagini**

### **1. Scenario iniziale**

Hai una `section` alta **300px** con un background color acqua marina.  
Dentro c’è un tag `<img>` che:

- è più grande della section,
    
- **straborda** a destra e/o in basso.
    

Obiettivo:  
**costringere l’immagine a rientrare nella section**, senza usarla come `background-image`.

Perché non usare `background-image`?

- SEO → un’immagine nel DOM ha valore semantico (alt, crawling).
    
- Accessibilità → screen reader possono interpretare la `<img>`.
    
- Gestione dinamica → l’immagine potrebbe arrivare da DB o API.
    

Quindi ti serve lavorare **sul tag `<img>` vero e proprio**.

---

### **2. Prima regola fondamentale: la dimensione del contenitore**

Per far sì che `object-fit` funzioni, l’immagine deve prima essere “ancorata” dimensionalmente al contenitore.

Esempio:

```css
section {
  height: 300px;
}

section img {
  height: 100%;
  width: 100%;
}
```

Ora l’immagine è **obbligata** a occupare l’intera section.

Senza questo, `object-fit` non ha riferimenti → non funziona.

---

### **3. `object-fit`: che cosa fa?**

Serve a indicare **come** un’immagine deve adattarsi allo spazio che gli assegni.

È l’equivalente CSS di:

- “riempi lo spazio”
    
- “stai dentro senza tagliare”
    
- “fai da cover come la copertina di Facebook”
    
- “non deformarti”
    

Ecco i principali valori.

---

### **4. `object-fit: contain`**

```css
object-fit: contain;
```

- L’immagine viene **interamente contenuta** nel box.
    
- Mai tagliata.
    
- Mantiene la **proporzione originale**.
    
- Possono comparire “spazi vuoti”, come quando ridimensioni un’immagine in PowerPoint senza distorcerla.
    

Perfetto per:

- loghi,
    
- foto che non devono essere tagliate,
    
- immagini scientifiche.
    

---

### **5. `object-fit: cover`**

```css
object-fit: cover;
```

- L’immagine **riempie tutto il contenitore**.
    
- Per farlo, può essere **tagliata ai bordi**.
    
- Mantiene la proporzione originale.
    

È il comportamento delle:

- copertine Facebook,
    
- header hero dei siti moderni,
    
- banner responsive.
    

> “Voglio la sezione piena, non mi interessa se i bordi si perdono”.

---

### **6. `object-fit: fill`**

```css
object-fit: fill;
```

- L’immagine **si deforma** pur di riempire il contenitore.
    
- Non mantiene il rapporto d’aspetto.
    

È raramente usato perché:

- l’immagine si allunga,
    
- si schiaccia,
    
- perde proporzioni.
    

Può servire:

- per textures,
    
- pattern,
    
- grafica non fotografica.
    

---

### **7. `object-fit: none`**

```css
object-fit: none;
```

- L’immagine mantiene la sua dimensione originale.
    
- Ignora totalmente il contenitore → può strabordare.
    

È l’opposto di qualsiasi adattamento.

---

### **8. `object-fit: scale-down`**

```css
object-fit: scale-down;
```

Comportamento ibrido:

- confronta **none** vs **contain**
    
- sceglie quello che produce l’immagine **più piccola**.
    

È come dire:

> “Se ci stai bene così, non ti tocco.  
> Se sei troppo grande, ti restringo come con contain.”

---

### **9. Senza width/height, `object-fit` non funziona**

Esempio sbagliato:

```css
img {
  object-fit: cover;
}
```

Risultato: nessun effetto.

Motivo:  
L’immagine non sa quale contenitore deve rimpiazzare → mantiene la sua dimensione nativa.

Serve **sempre**:

```css
img {
  width: 100%;
  height: 100%;
}
```

oppure una dimensione fissa o relativa del parent.

---

### **10. `object-position`: la versione “positional”**

Dopo aver deciso come adattare l’immagine, puoi anche decidere **quale parte centrare**.

Esempi:

```css
object-position: center;   /* centro esatto */
object-position: top;      /* parte alta dell’immagine */
object-position: bottom right;
object-position: 30px 40px; /* offset preciso */
```

Funziona in tutte le modalità che possono coinvolgere “taglio” (in primis `cover`).

Utile per:

- mettere al centro il volto di una persona,
    
- allineare una porzione della foto,
    
- spostare leggermente il punto focale.
    

---

### **11. Riepilogo operativo**

**Passo 1: dimensiona il contenitore**

```css
section { height: 300px; }
```

**Passo 2: forza l’immagine nel contenitore**

```css
img {
  height: 100%;
  width: 100%;
}
```

**Passo 3: scegli il comportamento**

- `contain` → tutto dentro, possibile spazio vuoto
    
- `cover` → riempi tutto, possibile taglio
    
- `fill` → riempi tutto deformando
    
- `none` → mantieni dimensione originale
    
- `scale-down` → compromesso intelligente
    

**Passo 4: regola la parte visibile**

```css
object-position: center;
```

---

### **12. Conclusione della sezione sul Posizionamento**

Hai completato:

- `display`
    
- `position` (static, relative, absolute, fixed, sticky)
    
- `z-index`
    
- `float` e `clear`
    
- `object-fit` + `object-position`
    

Sei pronto a entrare nei **layout moderni**:

- **Flexbox**
    
- **Grid**
    

Da qui in poi la potenza del CSS esplode.