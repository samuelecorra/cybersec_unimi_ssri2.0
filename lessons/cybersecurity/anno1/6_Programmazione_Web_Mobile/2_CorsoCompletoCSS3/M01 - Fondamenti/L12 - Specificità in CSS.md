
## **Lezione 12 – Specificità in CSS**

### **1. Introduzione alla specificità**

La specificità è l’ultimo dei grandi concetti fondamentali di CSS, dopo la cascata e l’ereditarietà.  
Quando avrai un po’ più di pratica sulle spalle, è utile tornare su questo argomento leggendo articoli tecnici: su _developer.mozilla_, sul _W3C_, oppure sul sito del corso stesso.  
I motivi sono due:

1. La specificità ha molte regole, basate su **punteggi**.
    
2. È un concetto che diventa naturale con l’esperienza, più che con la teoria.
    

Il senso della specificità è dare un “peso” diverso ai vari selettori CSS, così che il browser sappia **quale regola applicare** quando più regole competono per lo stesso elemento.

La specificità convive con:

- **la cascata**, che decide quale regola vince tra quelle scritte in ordine diverso
    
- **l’ereditarietà**, che permette agli elementi di prendere valori dai genitori
    

Ma introduce anche una logica nuova: **il punteggio**.

---

### **2. Una parentesi fondamentale: `!important`**

Prima di entrare nella specificità vera e propria, bisogna chiarire il funzionamento di `!important`.

Se hai un elemento con questa regola:

```
h1 {
    color: red;
}
```

e per vari motivi non puoi modificare quella regola (magari perché arriva da una libreria esterna o da un’estensione), puoi forzare una sovrascrittura così:

```
h1 {
    color: yellow !important;
}
```

In questo caso il colore diventa immediatamente giallo, anche se altre regole sarebbero più “forti”.

`!important` è un comando che dice:

> “Non mi interessa la cascata, non mi interessa la specificità, non mi interessa nulla: questa regola vince.”

Il problema è che, quando si è principianti, lo si usa ovunque. E il risultato è un CSS ingovernabile.

`!important` **va usato solo come ultima spiaggia**, quando non esiste alcun modo pratico per sovrascrivere una regola che arriva dall’esterno.

Per tutto il resto, bisogna lavorare “bene”, cioè strutturando regole e selettori in modo ordinato.

---

### **3. I punteggi della specificità**

La specificità assegna valori numerici ai vari tipi di selettori.  
Questi valori determinano quale regola “pesa di più”.

La scala dei punteggi (semplificata e utile per il 99% dei casi) è la seguente:

#### **Elementi → vale 1**

Esempi:  
`h1`, `p`, `body`, `ul`, `strong`

Questo valore **1** vale anche per gli _pseudo-elementi_ (che vedremo più avanti).

---

#### **Classi → vale 10**

Esempi:  
`.titolo`, `.grassetto`, `.evidenziato`

Il valore **10** vale anche per:

- attributi: `[type="text"]`
    
- pseudo-classi: `:hover`, `:focus`, `:nth-child()`
    

---

#### **ID → vale 100**

Esempi:  
`#hero`, `#titoloPrincipale`

Un ID è univoco all’interno del documento (come un codice fiscale).  
Per questo pesa molto più di elementi e classi.

---

#### **Stile inline → vale 1000**

Esempio:

```
<h1 style="color: pink">
```

Lo stile inline è potentissimo: sovrascrive quasi tutto, tranne `!important`.

---

### **4. Rinfresco rapido: elemento, classe, ID**

#### **Elemento → il tag HTML**

Esempio: `h1`, `p`, `button`  
Pesa **1 punto**.

#### **Classe → nome condivisibile tra più elementi**

È come un nome proprio: si può ripetere.  
Pesa **10 punti**.

#### **ID → identificatore unico**

È come un codice fiscale: non dovrebbe esisterne un secondo.  
Pesa **100 punti**.

---

### **5. La specificità in azione**

Partiamo da un esempio semplice:

```
h1 {
    color: red;
}
```

Poi scriviamo:

```
h1.titolone {
    color: green;
}
```

Ora l’`h1` diventa verde.  
Perché?

- `h1` → 1 punto
    
- `h1.titolone` → 1 (elemento) + 10 (classe) = **11 punti**
    

La regola con punteggio più alto vince.

Se aggiungessimo più classi:

```
h1.titolone.evidenziato {
    color: blue;
}
```

il punteggio diventerebbe:

- 1 (h1)
    
- 10 (titolone)
    
- 10 (evidenziato)  
    = **21 punti**
    

E vincerebbe anche contro selettori da 11 punti.

---

### **6. Superare gli ID… in teoria**

Se un ID vale 100, per superarlo dovresti scrivere:

```
.elemento.classe1.classe2.classe3... (10 classi)
```

10 classi × 10 punti = 100 + 1 dell’elemento = 101

E batteresti un ID.

Ma ovviamente **nessuno fa queste cose nella vita reale**.  
Non è questo lo scopo della specificità.  
Lo scopo è capire perché un elemento non sta usando lo stile che ti aspetti.

---

### **7. Stile inline: 1000 punti**

Lo stile inline vince su tutto:

```
<h1 style="color: pink">
```

pesa **1000**.

Quindi:

- elementi → 1
    
- classi → 10
    
- ID → 100
    
- inline → 1000
    
- `!important` → il boss finale
    

---

### **8. Il senso pratico della specificità**

Lo scopo non è fare guerre di punti.  
Lo scopo è:

- capire quale regola sta vincendo
    
- sapere perché un valore viene sovrascritto
    
- evitare di impazzire quando un colore non cambia come ti aspetti
    
- sapere che un selettore più “specifico” vale di più
    

Tutto ciò diventa naturale dopo un po’ di esperienza.

Se un elemento è rosso e lo volevi verde, devi chiederti:

- viene sovrascritto dalla cascata?
    
- eredita da un genitore?
    
- oppure una regola più specifica lo sta battendo?
    

La specificità è lo strumento che ti fa rispondere a questa domanda.

---

### **9. Ricapitolazione finale**

Per chiudere:

- **cascata** → vince la regola scritta più in basso
    
- **ereditarietà** → gli elementi possono prendere valori dai genitori
    
- **specificità** → i selettori hanno un “peso”, e quelli più specifici vincono gli altri
    
- **!important** → arma finale, da usare solo quando non hai alternative
    

La combinazione di questi tre concetti è ciò che dà vita al comportamento reale del CSS.

---

### **10. Prossima lezione**

Nel prossimo video il prof mostra come usare la console del browser in modo avanzato per capire esattamente _chi_ sta applicando _cosa_ a ogni elemento.

È uno strumento che diventerà indispensabile mentre il CSS diventa sempre più complesso.

---

Se vuoi, passo subito alla prossima lezione.