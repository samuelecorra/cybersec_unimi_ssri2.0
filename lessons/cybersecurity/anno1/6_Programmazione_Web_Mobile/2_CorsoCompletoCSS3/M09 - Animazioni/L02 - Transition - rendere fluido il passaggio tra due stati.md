## **Lezione 2 – Transition: rendere fluido il passaggio tra due stati**

Le _transition_ servono a definire **come** un elemento passa da uno stato A a uno stato B.  
Lo stato A può essere quello normale, lo stato B può essere l’hover, il click, il focus, un cambio di classe ecc.

Una _transition_ è l’effetto di “scivolamento” tra un valore e l’altro:  
senza transition → _salto immediato, brusco_  
con transition → _movimento fluido nel tempo_

---

## **1. Il problema senza transition**

Se voglio che, al passaggio del mouse, il div ruoti di 20°, scrivo:

```css
div:hover {
  rotate: 20deg;
}
```

Funziona, ma il cambio è **brutale**, immediato, anti-estetico.

La transition serve proprio a dare **tempo** al cambiamento.

---

## **2. Dove si mette la transition?**

La transition va sempre messa **sull’elemento base**, NON dentro `:hover`.

Sbagliato:

```css
div:hover {
  transition: 2s;
  rotate: 20deg;
}
```

Corretto:

```css
div {
  transition: 2s;
}

div:hover {
  rotate: 20deg;
}
```

Il div deve sapere **prima** come gestire la transizione, così quando entra in hover sa già cosa fare.

---

## **3. Le quattro regole fondamentali di transition**

CSS mette a disposizione **quattro sotto-proprietà**:

1. `transition-duration`
    
2. `transition-delay`
    
3. `transition-property`
    
4. `transition-timing-function`
    

E poi la **shortcut**: `transition`.

Ora le vediamo una per una.

---

## **4. transition-duration**

Durata della transizione.

```css
transition-duration: 2s;
```

Significa: la rotazione, il colore o qualunque valore cambi, impiegherà **2 secondi**.

E vale sia all’entrata (hover) sia all’uscita (mouse fuori).

---

## **5. transition-delay**

Tempo di attesa prima che la transizione inizi.

```css
transition-delay: 2s;
```

Effetto:

- passo sopra → attende 2 secondi → poi inizia a ruotare
    
- tolgo il mouse → attende 2 secondi → poi torna allo stato iniziale
    

---

## **6. transition-property**

Serve a specificare **quale proprietà** deve animarsi.

Esempi:

```css
transition-property: width;
transition-property: background-color;
transition-property: rotate;
```

Quella più usata e più sicura:

```css
transition-property: all;
```

Significa: “qualunque cosa cambi, animala”.

---

## **7. transition-timing-function**

È la “curva di velocità” della transizione nel tempo.  
Immaginala come la forma del grafico: inizio lento, fine veloce, ecc.

Valori importanti:

- `ease`  
    lenta all’inizio → più veloce → lenta alla fine (default)
    
- `linear`  
    velocità costante in tutto il tragitto
    
- `ease-in`  
    lenta all’inizio, poi accelera
    
- `ease-out`  
    veloce all’inizio, rallenta alla fine
    
- `ease-in-out`  
    lenta all’inizio e alla fine
    
- `cubic-bezier(x, y, z, w)`  
    curva completamente personalizzata (per animazioni avanzate)
    

Esempio:

```css
transition-timing-function: ease-out;
```

---

## **8. Comporre una transition completa con tutte le regole**

```css
div {
  transition-property: all;
  transition-duration: 2s;
  transition-delay: 1s;
  transition-timing-function: linear;
}
```

---

## **9. La shorthand: transition**

Tutte le proprietà possono essere unite in un’unica riga:

```css
transition: all 5s linear 2s;
```

Ordine obbligatorio:

1. proprietà (all o nome della proprietà)
    
2. durata
    
3. timing function
    
4. delay
    

Esempio pratico:

```css
div {
  transition: all 5s linear 2s;
}

div:hover {
  rotate: 20deg;
}
```

Effetto:

- aspetta 2 secondi
    
- ruota in 5 secondi
    
- ruota con velocità costante
    
- esce dall’hover: stesso comportamento al contrario
    

---

## **10. Riassunto concettuale**

Una _transition_ risponde alla domanda:  
**“come deve cambiare il valore?”**

Una _transform_ risponde alla domanda:  
**“quale valore deve cambiare?”**

L’animazione sarà la combinazione di queste due cose.

---

## **Conclusione**

Hai visto:

- perché serve la transition
    
- le quattro regole fondamentali
    
- come si mette e in che ordine
    
- come funziona la shorthand
    
- come cambia l’effetto in base alla curva di tempo
    

Ora manca solo il pezzo finale: **le animazioni vere**, cioè quelle che non dipendono da hover, ma funzionano da sole con keyframe, loop, delay, infinite, ecc.