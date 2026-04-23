## **Lezione 31: La keyword `this` in JavaScript**

---

### **1. Che cos’è davvero `this`?**

La parola chiave **`this`** è uno dei concetti più fondamentali e ricorrenti in tutta la programmazione ad oggetti, non solo in JavaScript. La userai:

- negli **oggetti**
    
- nelle **funzioni**
    
- negli **eventi**
    
- nelle **classi**
    
- nei **metodi**
    
- nei **costrutti più avanzati**
    

Per ora restiamo nel contesto che già conosci: **gli oggetti letterali**.

La definizione più importante da portarti a casa:

> **`this` significa “questo oggetto qui, quello in cui mi trovo adesso”.**

Non è teoria metafisica: è letterale.

---

### **2. `this` fa riferimento all’oggetto corrente**

Consideriamo questo oggetto:

```js
const persona = {
  nome: "Luca",
  cognome: "Rossi",
  eta: 25,

  presentati() {
    console.log(`Ciao, sono ${this.nome} ${this.cognome} e ho ${this.eta} anni`);
  }
};
```

Quando fai:

```js
persona.presentati();
```

accade questo:

- **`this` === `persona`**
    
- quindi `this.nome` → `"Luca"`
    
- `this.cognome` → `"Rossi"`
    
- `this.eta` → `25`
    

Output:

```
Ciao, sono Luca Rossi e ho 25 anni
```

Niente magia: il metodo _sta lavorando dentro persona_, quindi `this` punta a persona.

---

### **3. `this` cambia in base all'oggetto in cui viene usato**

Se creo un altro oggetto con lo stesso metodo:

```js
const persona2 = {
  nome: "Marco",
  cognome: "Bianchi",
  presentati() {
    console.log(`Ciao, sono ${this.nome}`);
  }
};
```

E chiamo:

```js
persona.presentati();   // Ciao, sono Luca Rossi
persona2.presentati();  // Ciao, sono Marco
```

`this` NON è assoluto.  
**`this` è relativo all’oggetto che lo invoca.**

Proprio come:

- _“il mio fegato”_
    
- non è _“il tuo fegato”_
    
- ma entrambi potete dire _“il mio fegato”_ e riferirvi a entità diverse
    

Così:

- `persona.presentati()` → `this` è `persona`
    
- `persona2.presentati()` → `this` è `persona2`
    

---

### **4. Perché non possiamo scrivere semplicemente `nome`?**

Perché dentro un oggetto **le proprietà devono essere referenziate tramite il “contenitore” che le ospita**.

Questo non funziona:

```js
presentati() {
  console.log(nome);  // ❌ nome non definito
}
```

Perché “nome” scritto da solo non significa niente.

Puoi:

- accedere tramite l’oggetto **esterno**  
    `persona.nome`
    
- oppure tramite **il contesto interno**  
    `this.nome`
    

Usare `persona.nome` _dentro_ persona funziona, ma è sbagliato in ottica di OOP:

- rende il codice rigido
    
- rompe il concetto di _“questo oggetto”_
    
- impedisce il riutilizzo dei metodi in altri oggetti
    

`this`, invece:

> **rende il metodo indipendente dal nome dell’oggetto che lo contiene.**

Ora puoi copiare quel metodo dentro altri oggetti senza cambiarlo.

---

### **5. `this` per modificare le proprietà**

Puoi anche **cambiare** i valori usando `this`:

```js
cambiaNome() {
  this.nome = "QWERTY";
}
```

---

### **6. Il punto cruciale: `this` = il contesto in cui stai lavorando**

Ogni volta che sei **dentro**:

```js
const persona = {
  nome: "Luca",
  cognome: "Rossi",
  metodo() {
    console.log(this); // stampa tutto l’oggetto persona
  }
};
```

`this` rappresenta **l’intero oggetto**, come se tu avessi scritto:

```js
console.log(persona);
```

Va visto così:

- **`this`** = _“questa persona”_
    
- **`this.nome`** = il nome di _questa persona_
    
- **`persona.nome`** = il nome dell’oggetto chiamato `persona`
    

Sono simili solo quando sei _dentro_ persona, ma il loro significato logico è molto diverso.

---

### **7. Perché `this` è importantissimo**

Perché JavaScript ti permette di:

- creare oggetti
    
- costruire oggetti complessi
    
- definire classi
    
- scrivere metodi riutilizzabili
    

E _tutto questo_ funziona solo perché esiste `this`.

È il fondamento dell’OOP.

---

### **8. Una metafora semplice e perfetta**

Se dico:

- **“il mio ginocchio destro”** → sto parlando di _me_
    
- **“il suo ginocchio destro”** → parlo di _lui_
    
- **“il ginocchio destro di Luca Rossi”** → parlo _di quella persona in particolare_
    

Nel codice:

- `this.ginocchioDestro` → il mio ginocchio (cioè quello dell’oggetto corrente)
    
- `persona.ginocchioDestro` → il ginocchio di Luca
    
- `persona2.ginocchioDestro` → il ginocchio di Marco
    

È esattamente così.

---

### **9. La sintesi da ricordare per sempre**

**`this` = oggetto corrente.  
Quello in cui il metodo è stato chiamato.**

Non il file, non il blocco, non il programma: **proprio l’oggetto**.

È un concetto naturale nella vita reale, ma serve fare lo switch mentale per portarlo nel codice.
