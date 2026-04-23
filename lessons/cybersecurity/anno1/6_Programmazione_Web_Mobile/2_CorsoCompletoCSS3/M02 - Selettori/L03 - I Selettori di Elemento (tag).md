## **Lezione 3 – I Selettori di Elemento (tag)**

### **1. Che cosa sono i selettori di elemento**

Arrivando dal corso HTML, questo concetto è immediato:  
gli **elementi** sono semplicemente i **tag HTML**.

Un selettore di elemento è quindi un selettore che prende un elemento **in base al suo tag**.

Esempi:

```
p      → seleziona tutti i paragrafi <p>
h1     → seleziona tutti gli <h1>
strong → seleziona tutti gli <strong>
div    → seleziona tutti i <div>
```

È il selettore più semplice in assoluto: basta scrivere il nome del tag.

Se nel CSS scrivi:

```css
p {
    color: red;
}
```

la regola verrà applicata a **tutti** i paragrafi `<p>` del documento.

---

### **2. Il comportamento naturale: prende tutti gli elementi di quel tipo**

Finché usi un selettore come:

```
p {
    color: red;
}
```

CSS selezionerà **tutti i `<p>` della pagina**.

Questo perché i selettori di elemento non distinguono tra un elemento e un altro:  
prendono l’intero “gruppo”.

Esempio:

```html
<p>Paragrafo 1</p>
<p>Paragrafo 2</p>
<p>Paragrafo 3</p>
```

Se hai tre `<p>`, tutti e tre diventano rossi.

Questo introduce già una prima necessità:  
cosa facciamo quando **vogliamo prendere solo uno di quei paragrafi**, o solo alcuni di essi?

Per ora non possiamo.  
Il selettore di elemento è troppo generico.

---

### **3. La tentazione del novizio: usare `style=""` in HTML**

Da principianti, la soluzione più spontanea è:

```html
<p style="color: blue">Paragrafo blu</p>
```

Funziona, certo.  
Ma è il modo **peggiore** per stilizzare, per vari motivi:

#### – Usi più caratteri

Un attributo `style="color: blue"` sono circa 20 caratteri.  
Se lo ripeti 3, 4, 10 volte, stai aumentando il peso del file.

Su una pagina piccola non fa paura,  
ma su centinaia di elementi e migliaia di pagine può diventare un disastro.

#### – Non è scalabile

Se domani vuoi cambiare da “blue” a “green”, devi:

- cercare ogni elemento
    
- cambiare il valore a mano
    
- rischiare di dimenticarne qualcuno
    
- rischiare errori di battitura
    

Il codice diventa fragile, pieno di ripetizioni, difficile da mantenere.

---

### **4. Verso un approccio migliore: isolare lo stile**

La programmazione (e anche il CSS) segue sempre una filosofia fondamentale:

> Quando qualcosa si ripete, **estraila in un punto unico**.

Esempio:

Se quattro paragrafi devono essere blu, invece di scrivere quattro volte `style="color: blue"` nell’HTML, è meglio creare un selettore che li identifichi tutti insieme:

```
p.particolare {
    color: blue;
}
```

E poi nell’HTML:

```html
<p class="particolare">...</p>
<p class="particolare">...</p>
<p class="particolare">...</p>
<p class="particolare">...</p>
```

Così:

- lo stile è definito **in un solo posto**
    
- non hai duplicazione
    
- se vuoi cambiare colore, lo cambi **una volta sola**
    
- non rischi errori
    
- la pagina pesa meno
    

Il prof lascia volutamente il nome “particolare” senza dire che è una classe, perché la introduciamo solo nella prossima lezione.  
Per ora, il punto chiave è:  
il selettore di elemento **non basta** per situazioni più specifiche.

---

### **5. Limiti del selettore di elemento**

Ricapitoliamo i problemi:

1. Seleziona **tutti** gli elementi di quel tag
    
2. Non permette distinzione tra “uno sì, uno no”
    
3. Porta a usare `style=""` quando serve specificità (cosa da evitare)
    
4. Non è scalabile su progetti veri
    
5. Non permette riutilizzo dello stile
    
6. Moltiplica gli errori se lo stile viene ripetuto a mano
    

Il selettore di elemento è utile, ma è un **primo passo**.

Per selettività più fine, servono strumenti migliori:

- classi
    
- ID
    
- attributi
    
- combinazioni di selettori
    

E infatti la prossima lezione introdurrà proprio il passo successivo.

---

### **6. Conclusione della lezione**

In questa lezione hai visto:

- che cosa sono i selettori di elemento
    
- perché afferrano **tutti** gli elementi del tag corrispondente
    
- perché non sono sufficienti da soli
    
- perché il vecchio approccio con `style=""` va abbandonato
    
- perché nella programmazione si evita la ripetizione e si centralizzano gli stili
    

E, soprattutto, che i selettori di elemento sono solo **il primo gradino** dei selettori CSS.

Nella prossima lezione entreremo nel selettore successivo:

### **i selettori di ID**, la prima forma di selezione “più precisa” rispetto ai semplici tag.