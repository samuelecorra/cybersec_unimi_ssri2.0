## **Lezione 13: L’attributo `lang` sul documento e sugli elementi**

---

### **1. Introduzione**

Abbiamo già incontrato l’attributo `lang` all’inizio del documento HTML:

```html
<html lang="it">
```

In quel contesto, `lang` comunica **la lingua principale del documento**.  
Serve a due soggetti fondamentali:

1. **Motori di ricerca** → capiscono che la pagina è in italiano e la propongono preferenzialmente a utenti che parlano italiano o si trovano in Italia.
    
2. **Screen reader** → impostano la voce corretta (voce italiana per testo italiano), evitando la lettura “italiano con accento inglese” che sarebbe incomprensibile.
    

Questa è la base dell’accessibilità linguistica dell’intero documento.

---

### **2. Ma `lang` può essere usato anche su singoli elementi**

Non è limitato al tag `<html>`.  
Possiamo applicarlo **a qualsiasi elemento** che contiene testo: paragrafi, citazioni, span, ecc.

Perché farlo?  
Per due motivi:

1. **Accessibilità vocale** → lo screen reader dovrà passare temporaneamente, per alcune parole, dalla voce italiana a quella inglese, spagnola, francese… per leggere correttamente la frase riportata.
    
2. **Indicazioni ai motori di ricerca** → parti del testo in altre lingue vengono interpretate come tali, migliorando la comprensione semantica generale della pagina.
    

---

### **3. Esempio pratico**

Supponiamo che tu stia scrivendo un paragrafo in italiano, ma dentro quel paragrafo riporti una frase inglese che ti ha detto un amico.

Senza `lang`:

```html
<p>
  Ieri un mio amico inglese mi ha detto: hi how are you.
</p>
```

Lo screen reader italiano leggerà:

> “ài au ar iu”

in modo quasi incomprensibile e certamente non naturale.

Con `lang`:

```html
<p>
  Ieri un mio amico inglese mi ha detto:
  <span lang="en">Hi, how are you</span>
  e io non ho capito cosa volesse dire.
</p>
```

Ora succede questo:

- La parte italiana viene letta con voce italiana.
    
- Il frammento `<span lang="en">...</span>` viene letto con voce inglese corretta.
    
- Lo screen reader torna automaticamente all’italiano subito dopo.
    

Per l’utente che utilizza uno screen reader, la differenza è **enorme**.

---

### **4. Specificare varianti della stessa lingua**

Il `lang` supporta anche **codici più precisi**, ad esempio:

- `en-US` → inglese americano
    
- `en-GB` → inglese britannico
    
- `es-ES` → spagnolo europeo
    
- `es-MX` → spagnolo messicano
    
- `fr-FR` → francese di Francia
    

Esempio:

```html
<span lang="en-GB">Good morning, mate!</span>
<span lang="en-US">Good morning, buddy!</span>
```

Per lo screen reader, queste sono **due lingue diverse**, con pronunce diverse.

---

### **5. Esempio con più lingue nello stesso paragrafo**

```html
<p>
  Ieri ho sentito frasi in tre lingue diverse:
  <span lang="en">Hi, how are you?</span>,
  <span lang="es">Hola, ¿cómo estás?</span>,
  <span lang="fr">Bonjour, comment ça va?</span>.
</p>
```

Effetto:

- ogni frase viene letta con la voce giusta,
    
- i motori di ricerca sanno che non è un testo multilingua confuso, ma parti in lingue distinte.
    

---

### **6. Perché non basta lasciare “tanto è breve, si capisce”**

Potresti pensare:  
“Ma è solo una parola, perché complicarsi la vita?”

Motivi concreti:

#### **6.1 Accessibilità**

Uno screen reader non “indovina” le lingue.  
Se la pagina è in italiano, tutto viene letto con voce italiana finché non trova istruzioni diverse.

Senza `lang`, una semplice frase inglese diventa:

- incomprensibile,
    
- disorientante,
    
- una barriera reale alla lettura.
    

#### **6.2 SEO e interpretazione semantica**

I motori di ricerca analizzano anche il contesto linguistico.  
Specifica corretta → migliore comprensione → migliore categorizzazione.

---

### **7. L’uso obbligatorio del `lang` principale**

È fondamentale ricordare che:

```html
<html lang="it">
```

**non è opzionale**.

- Senza, gli strumenti di validazione segnalano errore.
    
- Lo screen reader deve “indovinare” la lingua.
    
- I motori di ricerca devono interpretare da soli la lingua della pagina (sbagliano spesso).
    

Per questo Visual Studio Code, con le estensioni di accessibilità e HTML Lint attive, **continua a segnalare errore** se il `lang` principale manca.

Va sempre dichiarato.

---

### **8. Quando usare `lang` sugli elementi**

Usalo quando:

- citi una frase in un’altra lingua,
    
- riporti una parola straniera pronunciata letteralmente,
    
- mostri una traduzione a confronto,
    
- scrivi un testo multilingue (brevi passaggi linguistici, non un intero sito multilingua),
    
- stai spiegando vocaboli presi da altre lingue.
    

Non usarlo per:

- nomi propri (Google, Nike, Amazon),
    
- termini inglesi diventati “italiani” nell’uso comune (mouse, browser, server),
    
- parole straniere talmente integrate che non hanno pronuncia differente per senso (es. “pizza” in inglese… che rimane pizza).
    

---

### **9. Conclusione**

L’attributo `lang`:

- è fondamentale per l’**accessibilità**,
    
- migliora la comprensione dei contenuti,
    
- indica chiaramente al browser e ai bot quale lingua usare,
    
- può essere applicato all’intera pagina o a singole parole/frasi.
    

Grazie a esso gli screen reader cambiano voce automaticamente, rendendo la tua pagina più inclusiva, chiara e professionale.