## **Lezione 3 – Animation: il cuore delle animazioni in CSS**

Le _animation_ rappresentano il livello superiore rispetto alle _transition_.  
Le transition servono per effetti brevi e reattivi (hover, click, focus).  
Le animation invece permettono:

- movimenti continui
    
- cicli infiniti
    
- cambi di colore automatici
    
- loop di rotazioni
    
- animazioni indipendenti dall’utente
    
- controlli avanzati su direzione, tempo, ritardi, riavvii, ecc.
    

Sono molto più potenti, ma richiedono più regole da imparare.

---

## **1. Differenza concettuale tra transition e animation**

Transition → uno stato passa ad un altro _solo quando succede qualcosa_ (hover, click, ecc.)  
Animation → parte da sola, può ripetersi, può essere infinita, può tornare indietro.

Se vuoi un effetto che si ripete all’infinito = serve _animation_.  
Se vuoi un effetto che avviene solo una volta, al passaggio del mouse = basta _transition_.

---

## **2. Costruzione di una animazione: i keyframe**

Tutto parte da `@keyframes`, che definisce _cosa_ succede in ogni fase dell’animazione.

Esempio base:

```css
@keyframes cambia-sfondo {
  from { background-color: salmon; }
  to   { background-color: hotpink; }
}
```

`from` e `to` sono semplicemente l’inizio e la fine.

Finché non applichi questa animazione al div, non succede nulla.

---

## **3. Applicare l’animazione**

Serve:

- nome dell’animazione
    
- durata
    

```css
div {
  animation-name: cambia-sfondo;
  animation-duration: 5s;
}
```

Non hai bisogno di un trigger:  
la pagina si carica → parte automaticamente.

---

## **4. Keyframe complessi: le percentuali**

Oltre a from/to, puoi usare step intermedi:

```css
@keyframes cambia-sfondo {
  0%   { background-color: salmon; }
  25%  { background-color: red; }
  50%  { background-color: yellow; }
  75%  { background-color: green; }
  100% { background-color: blue; }
}
```

Più percentuali = animazione più ricca.

Puoi animare _qualunque proprietà animabile_:  
colori, posizioni, rotate, scale, translate, ecc.

---

## **5. Animare non solo i colori**

Esempio con trasformazioni:

```css
@keyframes muovi {
  0%   { translate: 0 0; }
  25%  { translate: 200px 0; }
  50%  { translate: 200px -200px; }
  75%  { translate: 0 -200px; }
  100% { translate: 0 0; rotate: 40deg; }
}
```

Puoi modificare tutte le proprietà che vuoi in ogni punto.

---

## **6. animation-delay**

Quanto tempo deve aspettare prima di partire:

```css
animation-delay: 2s;
```

La pagina carica → aspetta 2s → inizia a muoversi.

---

## **7. animation-iteration-count**

Quante volte l’animazione deve ripetersi:

- numero (1, 2, 10…)
    
- infinito
    

```css
animation-iteration-count: 3;
animation-iteration-count: infinite;
```

Infinite significa: non si ferma mai.

---

## **8. animation-direction**

Controlla _la direzione_ dell’animazione.

Quattro valori:

### Normal

Procede da 0% → 100%.  
È il comportamento standard.

### Reverse

Parte dal 100% → torna allo 0%.

### Alternate

Esegue un ciclo avanti (0 → 100), poi uno indietro (100 → 0), poi avanti, ecc.

Esempio perfetto per animazioni visive simmetriche.

### Alternate-reverse

Parte al contrario, poi torna in avanti, ecc.

---

## **9. animation-timing-function**

Identica alla transition-timing-function:

- ease
    
- ease-in
    
- ease-out
    
- ease-in-out
    
- linear
    
- cubic-bezier(…)
    

Definisce **come** si distribuisce la velocità durante l’animazione.

---

## **10. animation-fill-mode**

Controlla cosa succede **prima** e **dopo** l’animazione.

Valori fondamentali:

### None

Non applica lo stile dell’animazione né prima né dopo.  
La proprietà torna a quella originale.

### Forwards

Alla fine dell’animazione, l’elemento **rimane** nello stato 100%.  
Non torna allo stile originale.

### Backwards

Applica subito lo stile dello 0% _prima_ che inizi l’animazione, ignorando lo stile reale del div.

### Both

Applica sia lo stile iniziale che quello finale.  
Prima assume lo stile dello 0%, dopo mantiene quello del 100%.

È molto comune con animazioni complesse.

---

## **11. Un esempio completo**

```css
@keyframes cambia-sfondo {
  0% { background-color: salmon; }
  25% { background-color: red; }
  50% { background-color: yellow; }
  75% { background-color: green; }
  100% { background-color: hotpink; }
}

div {
  animation-name: cambia-sfondo;
  animation-duration: 5s;
  animation-delay: 2s;
  animation-iteration-count: infinite;
  animation-direction: alternate;
}
```

Effetto:

- aspetta 2 secondi
    
- cambia colore per 5 secondi
    
- torna indietro
    
- ripete per sempre
    
- movimenti avanti-indietro fluidi
    

---

## **12. La shorthand: animation**

Come per transition, puoi combinare tutto in un’unica riga.

Ordine:

1. animation-name
    
2. animation-duration
    
3. animation-timing-function
    
4. animation-delay
    
5. iteration-count
    
6. direction
    
7. fill-mode
    
8. play-state (opzionale)
    

Esempio:

```css
div {
  animation: cambia-sfondo 5s ease 2s infinite alternate;
}
```

Stessa animazione di prima in una sola riga.

---

## **13. Perché usare animazioni CSS al posto di JavaScript?**

- CSS è più leggero del JS
    
- meno costi di performance
    
- zero rischio di bloccare il main thread
    
- più semplice da mantenere
    
- più fluido, più ottimizzato dal browser
    
- JS va usato solo quando serve una logica o un controllo avanzato
    

Le animazioni CSS garantiscono fluidità e prestazioni massime.

---

## **Conclusione**

In questa lezione hai imparato:

- cos’è un’animazione
    
- come costruire i keyframe
    
- come applicare un’animazione al div
    
- tutti i parametri fondamentali (delay, iteration, direction, fill-mode…)
    
- come funziona la shorthand
    
- perché le animazioni CSS sono da preferire rispetto al JS
    

Adesso hai tutte le basi per l’esercizio 11 che farà da recap completo.