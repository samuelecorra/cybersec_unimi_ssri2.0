## **Lezione 7: Accessibilità da Mobile**

### **1. Introduzione: tutto ciò che serve… lo conosci già**

Questa è l’ultima parte del capitolo sull’accessibilità, e riguarda il **mobile**.  
È una sezione breve perché:

- molte cose le hai già viste
    
- molte altre appartengono al mondo del **CSS**, che affronteremo più avanti
    
- quello che ci interessa ora è **capire le regole**, non implementarle
    

L’obiettivo è creare una mappa mentale chiara:

> quando progettate un sito, ricordatevi che l’utente mobile **non è un utente desktop in piccolo**.  
> È un utente con esigenze completamente diverse.

Procediamo.

---

## **2. Responsive design: la regola madre**

Per rendere un sito accessibile su mobile la prima cosa, quella senza la quale niente funziona, è:

- **responsive design**
    
- **meta viewport**
    

Ti ricordi:

```html
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
```

Se il sito non è responsive, tutto ciò che vediamo sul mobile è una versione “miniaturizzata” del desktop, e questo distrugge:

- leggibilità
    
- cliccabilità
    
- esperienza utente
    
- accessibilità
    

**Senza responsive, niente accessibilità.**

---

## **3. Immagini responsive**

L’hai già visto nelle lezioni dedicate alle immagini:

- utilizzare versioni diverse (`srcset`)
    
- immagini più leggere su mobile
    
- evitare elementi che “escono” dallo schermo
    

È un aspetto che tornerà potentissimo quando faremo CSS.

---

## **4. Lo zoom deve essere abilitato (NON bloccare `user-scalable`)**

Regola d’oro:

> non impedire mai allo user di zoomare.

Se trovi qualcosa del genere:

```html
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
```

→ è SBAGLIATO per l’accessibilità.

Una persona con difficoltà visive _ha bisogno di poter zoomare con due dita_.  
Bloccare lo zoom significa impedirle di leggere.

Quindi, lo zoom DEVE essere:

```html
user-scalable=yes
```

o, meglio ancora:

```html
user-scalable=1
```

O semplicemente **omesso**, perché di default lo zoom è consentito.

---

## **5. Il menù mobile deve sparire dalla vista, non rimpicciolirsi**

Il menù desktop non può essere semplicemente “ristretto”.

Su mobile deve diventare:

- hamburger menu
    
- full screen menu
    
- slide-in menu
    
- bottom-sheet menu
    
- qualsiasi cosa, purché cliccabile
    

Cosa NON fare:

- non lasciare i link uno accanto all’altro
    
- non permettere overlap tra icone e testi
    
- non mettere elementi troppo vicini da risultare impossibili da toccare
    

Di nuovo, è roba da CSS, ma **il principio è accessibile già ora**:

> su mobile, il menù deve essere semplice, grande e isolato.

---

## **6. Input da mobile: scrivere è scomodo → usa alternative**

Scrivere testi lunghi da mobile è sempre scomodo:

- tastiera piccola
    
- errori frequenti
    
- lentezza
    
- spazi ristretti
    

Quindi, se puoi, **evita di far scrivere**.

Preferisci:

- `radio`
    
- `checkbox`
    
- `select`
    
- bottoni a scelta multipla
    
- slider (in futuro)
    
- date picker
    
- input “smart”
    

Esempio: invece di:

```html
<input type="text" placeholder="Sesso">
```

→ meglio:

```html
<select>
  <option>Maschio</option>
  <option>Femmina</option>
  <option>Altro</option>
</select>
```

In più:

- le checkbox richiedono un singolo tap
    
- le radio permettono scelte rapide
    
- le select convertono in un’interfaccia mobile dedicata
    

### **Non forzare soluzioni mobile-only se peggiorano la UX desktop**

Alcune form devono rimanere testuali sul desktop ma selettive su mobile.  
Per questo servirà CSS + JS.  
Ma ora sappi già la regola:

> su mobile, riduci la necessità di digitare.

---

## **7. Riepilogo delle regole di accessibilità su mobile**

### **✔ 1. Responsive design → obbligatorio**

Meta viewport, layout flessibile, immagini adattive.

### **✔ 2. Zoom sempre abilitato**

Mai `user-scalable=no`.

### **✔ 3. Menù ripensato, non rimpicciolito**

Navigazione semplificata, pulsanti grandi, contesto chiaro.

### **✔ 4. Input ottimizzati per mobile**

Se possibile: select, radio, checkbox, pickers.

### **✔ 5. Form brevi, niente testo superfluo**

Scrivere da mobile è scomodo → riduci al minimo.

---

## **8. Conclusione del capitolo sull’accessibilità**

Hai visto tutto ciò che serve per progettare un sito accessibile:

- semantica
    
- ARIA
    
- multimediale
    
- mobile
    
- screen reader
    
- ruoli/stati/proprietà
    
- responsive design
    

Se fai tuo tutto questo, sei già anni luce avanti rispetto al 90% dei developer che “buttano giù il sito e basta”.

---

## **9. Prossimo capitolo**

Nel prossimo modulo parliamo di:

> **Attributi extra di HTML: quelli utilissimi, di cui non abbiamo ancora parlato, che sono ovunque ma che vanno usati con criterio.**