## **Lezione 10: Proprietà `filter` in CSS – Guida Completa e Chiara**

La proprietà **`filter`** permette di applicare effetti grafici ai tuoi elementi, molto simili a quelli che useresti in **Photoshop**, **Premiere**, **After Effects**, ecc.  
Questi effetti si possono applicare **a tutto**: immagini, testo, div, card, componenti interi.

Per farti vedere come funziona useremo un’immagine, ma tieni a mente che puoi filtrare qualsiasi elemento.

---

## **1. Sintassi base**

```css
filter: blur(10px);
```

Ogni filtro è una _funzione_.  
Puoi mettere un solo filtro…  
oppure **più filtri insieme**, separati da uno spazio:

```css
filter: blur(5px) brightness(80%) grayscale(100%);
```

L’ordine conta: i filtri vengono applicati da sinistra a destra.

---

## **2. I filtri principali**

### **2.1 `blur()`**

Sfoca l’elemento.  
**Accetta solo pixel.**

```css
filter: blur(10px);
```

> ❗ Il blur non accetta percentuali.

---

### **2.2 `brightness()`**

Aumenta o diminuisce la luminosità.

- `100%` = normale
    
- `< 100%` = più scuro
    
- `> 100%` = più luminoso
    

```css
filter: brightness(50%);
```

---

### **2.3 `contrast()`**

Aumenta o diminuisce il contrasto.

```css
filter: contrast(150%);
```

---

### **2.4 `grayscale()`**

Converte in bianco e nero.

```css
filter: grayscale(100%);
```

---

### **2.5 `sepia()`**

Tono seppia "vintage".

```css
filter: sepia(90%);
```

---

### **2.6 `invert()`**

Inverte i colori.

```css
filter: invert(100%);
```

---

### **2.7 `opacity()`**

Trasparenza.

```css
filter: opacity(40%);
```

(uguale alla proprietà standalone `opacity: 40%`, ma filtrata)

---

### **2.8 `saturate()`**

Aumenta la saturazione dei colori.

```css
filter: saturate(200%);
```

---

### **2.9 `hue-rotate()`**

Ruota la ruota dei colori.

Accetta solo **gradi** o **turn**:

```css
filter: hue-rotate(110deg);
```

---

## **3. Filtri multipli**

Puoi concatenarli tranquillamente:

```css
filter: blur(3px) brightness(120%) saturate(150%) hue-rotate(45deg);
```

---

## **4. `drop-shadow()` – il filtro più particolare**

### ❗ Da non confondere con `box-shadow`

`box-shadow` applica un’ombra **al box dell'elemento**,  
anche se l’elemento è trasparente.

`drop-shadow`, invece, applica un’ombra al **contenuto grafico reale**,  
quindi alla forma visibile dell’elemento.

### Esempio:

```css
filter: drop-shadow(2px 2px 5px rgba(0,0,0,0.5));
```

- `offset-x`
    
- `offset-y`
    
- `blur`
    
- `color`
    

### Differenza visiva rapida

#### **`box-shadow`**

- ombra rettangolare
    
- sempre basata sul box dell’elemento
    
- anche se l'immagine ha zone trasparenti, l’ombra è un rettangolo
    

#### **`drop-shadow`**

- ombra _sulla forma reale_ dell’immagine
    
- segue la trasparenza PNG/SVG
    
- molto utile per icone, elementi arrotondati, badge, ecc.
    

---

## **5. Tips importanti**

### ✔ Alcuni filtri accettano percentuali

(es: saturation, grayscale, brightness, contrast)

### ✔ Altri accettano solo pixel

(es: blur)

### ✔ `hue-rotate` vuole gradi

### ✔ I filtri possono rallentare la pagina

Soprattutto se usati su elementi enormi o dentro animazioni pesanti.

---

## **6. Esempio finale completo**

```css
img {
  filter:
    blur(2px)
    brightness(90%)
    saturate(120%)
    hue-rotate(20deg)
    drop-shadow(2px 2px 8px rgba(0,0,0,0.4));
}
```

---
