# **Lezione 2: DevTools e Responsive Design: come testarli correttamente**

Restiamo un attimo sul sito usato come esempio, perché ora che conosciamo il concetto di _responsive design_ possiamo finalmente capire **come usare i DevTools in modo professionale per testare ogni versione del layout**.

Anche se i DevTools li abbiamo già visti molto tempo fa, allora non avevamo ancora tutti i concetti che ci servono davvero per usarli bene lato responsive. Oggi li colleghiamo correttamente.

---

## **1. Il “finto responsive” trascinando la finestra**

Il metodo più grezzo e meno comodo è semplicemente:

- trascinare la finestra più stretta
    
- poi riaprirla
    
- poi trascinarla di nuovo
    
- e ogni volta ridimensionare il pannello del codice, ecc.
    

Funziona, ma è scomodo, impreciso, e non simula i veri dispositivi.

Per questo passiamo alla modalità _vera_.

---

## **2. Il vero responsive test: F12 + simulazione dispositivi**

Premi **F12** e apri gli strumenti per sviluppatori.

Ora hai due possibilità:

### **A) Tenere DevTools separati dallo schermo**

Molto comodo se vuoi avere _tutto il sito in grande_ e DevTools in finestra laterale.

### **B) Tenere DevTools ancorati allo schermo**

Es. sidebar a destra.

In entrambi i casi, l'opzione fondamentale è quella che trovi accanto al picker degli elementi:

👉 L’icona dei **vari dispositivi / responsive mode**.

Cliccala.

Ora stai davvero _simulando un device_, non solo stringendo la finestra.

---

## **3. Vantaggi enormi del Device Mode**

Entrando nella modalità dispositivi, ottieni funzionalità che il semplice “stringi finestra” non ti dà:

### **✔ Modalità portrait / landscape**

Puoi ruotare il dispositivo con un click (telefono verticale/orizzontale).

### **✔ Righelli**

Puoi attivare i righelli per vedere correttamente dimensioni e proporzioni.

### **✔ Screenshot perfetti**

Puoi generare screenshot:

- della viewport
    
- della pagina intera
    

### **✔ Aggiungere device personalizzati**

Puoi aggiungere nuovi dispositivi con risoluzioni specifiche.

### **✔ Zoom (100%, 75%, ecc.)**

Utile se vuoi vedere “più sito” dentro la stessa area di lavoro.

### **✔ Simulazione realistica della dimensione fisica**

Non stai più vedendo “400px di finestra”, ma “un iPhone X da 375px”.

### **✔ Selettore rapido delle media query**

In alto trovi tutte le categorie:

- **mobile piccolo**
    
- **mobile medio**
    
- **mobile grande**
    
- **tablet**
    
- **laptop**
    
- **desktop grande**
    
- …
    

Queste barre colorate ti mostrano esattamente i breakpoints del sito.

> _Le media query le vediamo nel prossimo video: ora basta sapere che qui vengono visualizzate automaticamente._

---

## **4. Adattabile vs Dispositivo fisso**

Nel menu dei dispositivi trovi due modalità:

### **Adattabile**

Puoi cambiare liberamente la larghezza.  
È perfetto per controllare _tutte_ le dimensioni possibili, anche quelle non standard.

### **Dispositivo fisso (iPhone, iPad, Pixel, Galaxy...)**

Simula il vero comportamento di quello specifico device.

Ad esempio:

- iPhone X
    
- iPhone SE (molto piccolo)
    
- iPad Air
    
- iPad Mini
    
- Pixel
    
- Surface Pro
    
- Galaxy Fold (che fa schifo e distorce tutto 🤣)
    

Puoi anche aggiungerne altri da **Modifica**.

---

## **5. Attenzione: uscire dal Device Mode non significa “responsive”**

Se chiudi il Device Mode tornando alla vista normale:

- il sito NON viene considerato un dispositivo mobile
    
- quindi non applica le media query mobile
    
- il layout potrebbe sembrare “rotto”, ma è normale
    

Il vero test responsive si fa **solo** dentro Device Mode.

---

## **6. Ricapitolando**

Il Device Mode serve per:

- testare la tua UI in mobile, tablet, laptop e desktop
    
- testare rotazioni portrait/landscape
    
- controllare i breakpoints definiti nelle media query
    
- capire se un elemento si comporta male in certe larghezze specifiche
    
- verificare bug su dispositivi reali senza possederli fisicamente
    

È uno strumento indispensabile se fai web design moderno.

---

## **E adesso?**

Nel prossimo video entriamo nel cuore della questione:

# **Le Media Query – il vero motore del responsive design**
