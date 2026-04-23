## **Lezione 8: Text-Shadow**

### **1. Introduzione**

Riprendiamo da dove eravamo e rimuoviamo la direzione RTL usata in precedenza. Ora il nostro paragrafo torna normale e possiamo concentrarci su una proprietà estetica molto importante: **text-shadow**.

Questa proprietà è l’equivalente tipografico della **box-shadow**, ma applicata **solo ai caratteri**, non al box rettangolare che contiene il testo.

---

### **2. Che cos’è `text-shadow`**

`text-shadow` permette di applicare un’ombra al testo stesso, lettera per lettera.  
Funziona esattamente come `box-shadow`, ma è specifica per i glifi.

Esempio base:

```css
text-shadow: 2px 2px 4px black;
```

Questa sintassi comprende quattro valori:

1. **offset orizzontale** → spostamento sx/destra
    
2. **offset verticale** → spostamento su/giù
    
3. **blur-radius** → livello di sfocatura
    
4. **colore** → può essere `rgb`, `rgba`, `hex`, nome colore, ecc.
    

---

### **3. Usare i generatori online**

Se cerchi su Google _“text shadow CSS generator”_, trovi strumenti con anteprima in tempo reale.  
Sono utilissimi perché ti permettono di:

- vedere subito l’effetto
    
- modificare i parametri visualmente
    
- copiare il codice generato
    

All’interno dei generatori puoi anche passare a box-shadow o gradient, ma oggi ci concentriamo solo su **text-shadow**.

---

### **4. L’effetto neon**

Per mostrare la potenza della proprietà, abbiamo preso un preset “Neon” da un generatore.

Questo tipo di effetto richiede:

- un **font color** molto acceso
    
- un **background** molto scuro
    
- **più ombre sovrapposte**
    
- un uso calibrato di blur e opacità
    

Quando incolli il codice ottieni un testo molto brillante, quasi illeggibile se esagerato, ma perfetto per capire come funziona la proprietà.

### **4.1 Perché funziona così**

L’effetto neon si basa su più ombre applicate contemporaneamente:

```css
text-shadow:
  0 0 5px #fff,
  0 0 10px #fff,
  0 0 20px #0ff,
  0 0 40px #0ff;
```

Ogni riga è una singola ombra.  
La somma degli strati crea glow, profondità ed effetto luminoso.

Il colore del font e lo sfondo influenzano fortemente l’aspetto finale:

- testo chiaro su sfondo scuro → effetto migliore
    
- testo rosso + shadow rossa → glow rosso
    
- sfondo uniforme → resa più pulita
    

---

### **5. Torniamo alla versione base**

Prima di esagerare con 10 ombre sovrapposte, capiamo la struttura essenziale.

Esempio semplice:

```css
text-shadow: 4px 4px 8px rgba(0,0,0,0.5);
```

Qui abbiamo:

- **4px** → spostamento a destra
    
- **4px** → spostamento verso il basso
    
- **8px** → blur
    
- **rgba(0,0,0,0.5)** → colore dell’ombra con opacità al 50%
    

Se riduci il blur a 0:

```css
text-shadow: 4px 4px 0 black;
```

l’effetto è duro e netto, senza sfumatura.

---

### **6. Collegamento a box-shadow**

`text-shadow` funziona in maniera molto simile a `box-shadow`, quindi i concetti già visti tornano utili:

- offset orizzontale
    
- offset verticale
    
- blur
    
- colore
    
- possibilità di avere **multiple shadows** con una lista separata da virgole
    

La differenza è solo l’oggetto a cui si applicano:

- `box-shadow` → box dell’elemento
    
- `text-shadow` → glifi del font
    

---

### **7. Consiglio pratico: usare i generatori**

I generatori sono strumenti intelligenti perché:

- risparmi tempo
    
- vedi subito il risultato
    
- impari velocemente a leggere il codice
    
- puoi ottenere effetti complessi senza scrivere tutto a mano
    

L’importante è **capire cosa stai copiando**, non incollare a caso.

---

### **8. Conclusione**

`text-shadow` è una proprietà semplice ma potentissima, utilissima per creare:

- titoli più leggibili su sfondi complessi
    
- effetti neon
    
- glow colorati
    
- profondità
    
- design più moderni e dinamici
    

Nella prossima lezione parliamo della parte successiva: **text-layout**, cioè tutte le proprietà che influenzano l’impaginazione del testo.