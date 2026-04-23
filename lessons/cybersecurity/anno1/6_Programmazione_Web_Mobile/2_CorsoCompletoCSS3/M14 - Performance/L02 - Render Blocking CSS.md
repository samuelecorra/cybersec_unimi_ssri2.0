## **Lezione 2: Render Blocking CSS**

---

## **1. Introduzione**

In questa lezione approfondiamo un concetto fondamentale per le performance web: il **Render Blocking CSS**.  
Dopo aver capito il _Critical Rendering Path_, ora vediamo come il CSS possa rallentare — o addirittura bloccare — la visualizzazione della pagina, causando ritardi sia per l’utente sia per i bot dei motori di ricerca.

Capire il comportamento “bloccante” dei fogli di stile è essenziale per qualsiasi sviluppatore front-end che voglia ottimizzare seriamente la propria pagina.

---

## **2. Cos’è il Render Blocking CSS**

### **2.1 Significato intuitivo**

Il termine _render blocking_ indica ogni risorsa che **impedisce al browser di completare il Critical Rendering Path**, ritardando la comparsa del primo contenuto visibile (First Contentful Paint).

Il CSS viene considerato _render-blocking_ perché **il browser non può renderizzare nulla finché non ha scaricato ed elaborato completamente i fogli di stile**.

In altre parole:

> **Finché il CSS non arriva, la pagina non può essere disegnata.**

È come ordinare un menu al McDonald’s e restare fermi al bancone finché non arrivano anche le patatine: non si procede senza tutto ciò che serve.

---

## **3. Perché il CSS blocca la renderizzazione**

### **3.1 Il comportamento del browser**

Quando visitiamo un sito:

1. Il browser scarica l’**HTML**.
    
2. Scorrendo il codice, trova uno o più `<link rel="stylesheet">`.
    
3. A quel punto **sospende l’elaborazione del resto dell’HTML**.
    
4. Effettua una richiesta separata per scaricare il file CSS.
    
5. Solo quando _tutto_ il CSS è stato scaricato e parsato:
    
    - costruisce il **CSSOM**,
        
    - lo unisce al **DOM**,
        
    - crea il **Render Tree**,
        
    - esegue il **layout**,
        
    - effettua il **paint**.
        

Questo significa che, finché il CSS non è pronto, il browser **non può andare avanti** nel percorso di renderizzazione.

È una scelta logica: senza stile, l’impaginazione non avrebbe senso.  
Il browser non può mostrare una pagina bianca e poi ricalcolare tutto dopo: sarebbe ancora più costoso e lento.

---

## **4. Visualizziamo il blocco**

### **4.1 Sequenza reale del browser**

Il percorso è:

1. Request HTML
    
2. Download HTML
    
3. Parsing HTML
    
4. Scoperta del file CSS
    
5. Request CSS
    
6. Download CSS
    
7. Costruzione del CSSOM
    
8. Creazione del Render Tree
    
9. Layout
    
10. Paint
    

Il “blocco” avviene tra i passaggi 4 e 8.

Finché il CSS non arriva, **il DOM non può procedere verso la fase di render**.

### **4.2 Schema concettuale**

```
[HTML Request] → [HTML Parsing] → trova <link> → STOP
                       ↑                    ↓
                    DOM building ← CSSOM building (aspetta file CSS)
```

Il rendering riparte solo quando entrambi sono pronti.

---

## **5. Perché il Render Blocking CSS rallenta la pagina**

### **5.1 Effetti reali sulle performance**

Il CSS blocca:

- la costruzione del Render Tree,
    
- il layout,
    
- il paint,
    
- quindi _ritarda la comparsa dei primi pixel utili_.
    

Questo ha conseguenze dirette su:

- **L'utente** → percepisce lentezza e “vuoti”
    
- **I crawler** → penalizzano il sito nei test prestazionali
    
- **Lighthouse e PageSpeed** → segnalano problemi come:
    
    - “Elimina risorse che bloccano la visualizzazione”
        
    - “Render-blocking resources”
        
    - “Reduce unused CSS”
        
    - “Defer non-critical CSS”
        

---

## **6. Esempi da Lighthouse**

### **6.1 Risorse bloccanti**

Aprendo Lighthouse (DevTools → _Lighthouse → Prestazioni_) troverai sempre la sezione:

**Render-blocking resources**

Qui compariranno tipicamente:

- File `.css` (quasi sempre)
    
- File `.js` non ottimizzati
    

Per esempio:

- `/style.css`
    
- `/main.css`
    
- `/font-awesome.css`
    
- Script non differiti o non asincroni
    

Questa sezione mostra anche **il ritardo introdotto** da ciascun file in millisecondi.

---

## **7. Una dimostrazione pratica**

### **7.1 Caricamento di CSS esterno**

Quando in HTML troviamo:

```html
<link rel="stylesheet" href="style.css">
```

il browser:

- sospende il parsing dell’HTML,
    
- richiede `style.css`,
    
- aspetta tutta la risposta,
    
- solo allora prosegue.
    

Se il CSS è grande (Framework, reset globali, componenti inutilizzati, temi complessi), il blocco è ancora più lungo.

### **7.2 E se fosse enorme?**

Immagina un CSS da 500KB non minificato (caso reale con Bootstrap + componenti personalizzati + librerie varie):

→ il browser aspetta  
→ Lighthouse segnala ritardi di 300–700ms  
→ FCP peggiora  
→ intera UX degradata

---

## **8. Una soluzione “grezza”: spostare il CSS nel body**

Lo dico subito: **NON è la soluzione ottimale**, ma serve per capire.

Se inseriamo il CSS alla fine del body:

```html
<body>
  ...
  <link rel="stylesheet" href="style.css">
</body>
```

il browser:

- mostra la pagina strutturalmente _prima_,
    
- poi applica gli stili dopo.
    

Risultato:  
un flash non stilizzato (FOUC, _Flash of Unstyled Content_).

Non è elegante, non è professionale, ma tecnicamente _elimina_ il render blocking, perché il browser non è più costretto ad aspettare il CSS prima di mostrare qualcosa.

### **8.1 Perché è utile parlarne adesso?**

Perché introduce il concetto chiave della prossima lezione:

## **Critical CSS**

che consiste nel distinguere:

- **CSS essenziale** per la parte visibile dello schermo → caricato subito
    
- **CSS non essenziale** (parte bassa della pagina, componenti non visibili) → caricato dopo
    

Questo è il metodo _corretto_ per eliminare il blocco senza causare FOUC.

---

## **9. Ricapitolazione**

Il CSS è _render-blocking_ perché:

1. il browser **non può renderizzare nulla senza CSS**,
    
2. quindi sospende tutto finché il CSS non è stato scaricato e processato.
    

Questo porta a ritardi nella visualizzazione della pagina e penalizza le performance percepite e misurate.

---

## **10. Conclusione**

Ora hai compreso:

- perché il CSS blocca la renderizzazione,
    
- dove avviene il blocco,
    
- come Lighthouse lo segnala,
    
- perché è fondamentale intervenire,
    
- e quale sarà il prossimo step ottimizzativo.
    

La prossima lezione è decisiva:

## **Lezione 3 – Critical CSS: cos’è, perché serve e come si costruisce**

Ti ci porto subito.