## **Lezione 1: Stile dei Link e Pseudo-Classi**

### **1. Introduzione**

Apriamo un nuovo modulo dedicato agli **stili degli elementi interattivi**, partendo da uno dei più fondamentali: **i link**.  
Per capire davvero come funzionano dobbiamo introdurre e usare in modo sistematico le **pseudo-classi**, che rappresentano **gli stati** di un elemento (in questo caso `<a>`).

Finora le abbiamo solo sfiorate, ma con i link diventano finalmente chiare e operative.

In questa lezione vedremo:

- come si creano vari tipi di link
    
- in che modo CSS distingue un link visitato, non visitato, attivo, in hover, in focus
    
- come rimuovere e gestire l’underline
    
- perché tutto questo è fondamentale in termini di UX e accessibilità
    

---

### **2. Creazione dei link di esempio**

Costruiamo tre casi diversi per mostrare tutti i comportamenti:

```html
<a href="#">ciao</a>
<a>ciao due</a>
<a href="https://facebook.com">ciao tre</a>
```

- Il **primo** ha `href="#"`: è un link valido ma rimanda alla stessa pagina.
    
- Il **secondo** non ha `href`: tecnicamente non è un link.
    
- Il **terzo** è un link reale verso un sito esterno.
    

Questo ci permette di discriminare i comportamenti di `:link` e `:visited`.

---

### **3. Pseudo-classe `:link`**

### **3.1 Cos’è**

La pseudo-classe `:link` seleziona tutti i link **non ancora visitati**, cioè:

- quelli con un `href` funzionante
    
- che l’utente non ha mai cliccato prima
    

Esempio:

```css
a:link {
  color: blue;
}
```

Effetti:

- i due link con `href` diventano blu
    
- l’elemento `<a>` senza `href` **non** viene colorato, perché non ha uno “stato link”
    

---

### **4. Pseudo-classe `:visited`**

### **4.1 Come funziona**

`:visited` si attiva quando l’utente ha **già visitato** quel link almeno una volta.

```css
a:visited {
  color: red;
}
```

Funzionamento:

- se clicchi “ciao tre”, vai su Facebook, torni indietro
    
- ora “ciao tre” è rosso
    
- esattamente come succede nei risultati di ricerca di Google
    

Serve a indicare ciò che hai già visitato per aiutare la navigazione, soprattutto in pagine piene di link (Wikipedia, YouTube, Google, blog).

---

### **5. Pseudo-classe `:hover`**

### **5.1 A cosa serve**

`:hover` rappresenta lo stato in cui il cursore **passa sopra** l’elemento.

Si può applicare praticamente a qualsiasi elemento, non solo ai link.

```css
a:hover {
  background: purple;
}
```

Risultato:

- mentre il puntatore passa sopra il link, il background diventa viola
    

---

### **6. Pseudo-classe `:active`**

### **6.1 Quando si attiva**

`:active` funziona **nel momento esatto in cui il link viene premuto**, cioè dal click fino al rilascio.

```css
a:active {
  color: green;
}
```

Spiegazione:

- quando clicchi e tieni premuto → il link diventa verde
    
- quando rilasci e il browser naviga → lo stato scompare
    

---

### **7. Pseudo-classi `:focus` e `:focus-visible`**

### **7.1 Differenza fondamentale**

- `:focus` = l’elemento è attivato (clic, interazione diretta)
    
- `:focus-visible` = l’elemento è raggiunto tramite **tastiera** (TAB)
    

Esempio:

```css
a:focus {
  color: lightcoral;
}

a:focus-visible {
  color: orange;
}
```

#### **Tabbing**

Premendo TAB:

- il browser sposta il focus tra i vari link
    
- `:focus-visible` si attiva e colora l’elemento (qui: arancione)
    

#### **Click**

Cliccando direttamente:

- si attiva `:focus`
    
- assume lo stile “premuto” (qui: lightcoral)
    

Questa distinzione è cruciale per l’accessibilità dei moduli e dei link navigabili via tastiera, e vedrai la sua potenza quando lavoreremo sui form.

---

### **8. Rimozione dell’underline**

Di default, i link sono **sottolineati**.  
Molti designer la rimuovono, ma lato accessibilità **non è sempre una buona idea**, perché l’utente deve capire subito cosa è cliccabile.

Per rimuoverla:

```css
a {
  text-decoration: none;
}
```

Ricorda comunque:

- sui siti di qualità, i link hanno SEMPRE almeno un indicatore: underline, colore diverso, hover ben visibile, o tutti insieme.
    

---

### **9. Riassunto sulle pseudo-classi dei link**

|Pseudo-classe|Stato rappresentato|Note|
|---|---|---|
|`:link`|link non ancora visitato|funziona solo se c'è `href`|
|`:visited`|link già visitato|utile per navigazioni lunghe|
|`:hover`|mouse sopra|usabile su qualsiasi elemento|
|`:active`|click in corso|dura una frazione di secondo|
|`:focus`|elemento attivato|stato dopo il click|
|`:focus-visible`|elemento raggiunto via TAB|essenziale per accessibilità|

---

### **10. Conclusione**

Abbiamo visto:

- come funzionano i link in HTML
    
- come si applicano gli stili tramite pseudo-classi
    
- come distinguere gli stati link → visited → hover → active → focus
    
- come rimuovere l’underline
    
- come gestire correttamente l’interazione per migliorare UX e accessibilità
    

Nella prossima lezione ci concentreremo su un aspetto cruciale:  
**l’accessibilità dei link esterni**, con un accorgimento fondamentale che molti sviluppatori dimenticano.