## **Lezione 1 – Transform: tutte le trasformazioni base del CSS**

Prima di entrare nelle vere animazioni CSS, dobbiamo padroneggiare due concetti fondamentali:

- **Transform** → _la trasformazione_ (cosa cambia)
    
- **Transition** → _il modo in cui cambia_ (come ci si sposta da A a B)
    

Un’animazione non è altro che **una trasformazione + una transizione nello stesso blocco di tempo**.

E qui parte la prima metà del lavoro: _le trasformazioni_.

---

## **1. Prepariamo un div di test**

Creiamo un elemento semplice su cui fare tutte le prove:

```css
div {
  height: 200px;
  width: 300px;
  border: 2px solid red;
  background-color: salmon;
}
```

Visivamente: un rettangolo rosa con bordo rosso.

---

## **2. La proprietà transform**

`transform:` permette di applicare **trasformazioni geometriche** all’elemento:

- spostarlo
    
- ruotarlo
    
- ingrandirlo o rimpicciolirlo
    
- deformarlo
    
- combinare più trasformazioni insieme
    

### **Sintassi generale:**

```css
transform: operazione1(argomenti) operazione2(argomenti) ... ;
```

---

## **3. Translate – spostamento**

Sposta un elemento dalla sua posizione originaria.

```css
transform: translate(50px, 100px);
```

Significa:

- 50px verso destra
    
- 100px verso il basso
    

È simile al concetto di _relative position_, ma è una **vera trasformazione grafica**, non modifica il flusso del layout.

---

## **4. Rotate – rotazione**

Ruota l’elemento attorno al suo centro.

```css
transform: rotate(30deg);
```

Puoi usare qualunque angolo (positivo = senso orario).

---

## **5. Scale – ingrandimento/riduzione**

### **Scala uniforme**

```css
transform: scale(2);
```

Ingrandisce tutto al doppio.

### **Scala differente su X e Y**

```css
transform: scale(2, 1);
```

- raddoppia la larghezza
    
- lascia invariata l’altezza
    

### **Versioni specializzate**

```css
transform: scaleX(3);
transform: scaleY(1.5);
```

---

## **6. Skew – inclinazione**

Inclina l’elemento, come se diventasse un parallelogramma.

```css
transform: skew(20deg);
```

Oppure separatamente:

```css
transform: skewX(20deg);
transform: skewY(10deg);
```

---

## **7. Matrix – NON usarlo (per ora)**

È una versione compatta che contiene tutti i valori di trasformazione in un’unica funzione:

```css
transform: matrix(a, b, c, d, e, f);
```

Tecnicamente potentissima, **praticamente inutile** per la stragrande maggioranza dei casi.  
Evitala fino a quando non lavorerai con grafica complessa o canvas SVG.

---

## **8. Combinare più trasformazioni**

Errore classico:

```css
transform: scale(2);
transform: rotate(30deg);
```

Il secondo **sovrascrive** il primo.

### **Modo corretto:**

```css
transform: scale(2) rotate(30deg) translate(100px, 100px);
```

CSS esegue le trasformazioni **in ordine da sinistra a destra**.  
L’ordine _modifica_ il risultato, perché ruotare dopo un ingrandimento ≠ ingrandire dopo una rotazione.

---

## **9. Novità recentissima: trasformazioni separate**

Da pochissimo sono disponibili proprietà distinte:

```css
rotate: 40deg;
scale: 2;
translate: 200px 50px;
```

Producono **lo stesso identico effetto** di:

```css
transform: rotate(40deg) scale(2) translate(200px, 50px);
```

Ma sono più leggibili e modulari.

**Importante:** `transform:` rimane lo standard ed è essenziale conoscerlo perché:

- la documentazione e i progetti reali lo usano ancora
    
- i tool grafici esportano trasformazioni sotto forma di `transform:` unico
    
- supporto browser totale garantito solo per `transform`
    

---

## **10. Trasformazioni e coordinate: una nota finale**

Le trasformazioni si applicano **visivamente**, ma non spostano realmente l’elemento nel “flow” del layout.  
Sono perfette per:

- animazioni
    
- hover effect
    
- interfacce dinamiche
    
- micro-interazioni
    
- pulsanti, card e modali animate
    

---

## **Conclusione della lezione**

Hai visto tutte le trasformazioni fondamentali di CSS:

- translate
    
- rotate
    
- scale
    
- skew
    
- combinazioni multiple
    
- e la nuova sintassi modulare (rotate/scale/translate separati)
    

Ora possiamo finalmente passare alla seconda metà del discorso:

👉 **Transition**, cioè _come_ passa da uno stato all’altro.