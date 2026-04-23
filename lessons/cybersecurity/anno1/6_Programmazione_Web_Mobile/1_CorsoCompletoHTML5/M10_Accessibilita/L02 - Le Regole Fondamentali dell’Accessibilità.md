## **Lezione 2: Le Regole Fondamentali dell’Accessibilità**

### **1. Introduzione: non sono “regole nuove”, è tutto ciò che abbiamo già imparato**

In questa lezione rivediamo le regole fondamentali per rendere una pagina accessibile.  
La verità è che non c’è quasi nulla di nuovo: tutto ciò che segue l’hai già imparato _mentre costruivamo il corso HTML_.  
Qui le riordiniamo in un unico blocco, così da avere un quadro chiaro e unico.

Molto spesso l’accessibilità non è un insieme di trucchi tecnici:  
è **buon senso**, unito a **semantica pulita** e **testo chiaro**.

---

### **2. Semantica corretta: la regola numero zero**

Se hai un bottone, **usa `<button>`**.  
Se hai una sezione principale, usa `<main>`.  
Se hai un contenuto laterale, usa `<aside>`.  
Se hai un footer, usa `<footer>`.

È la base assoluta dell’accessibilità.

Perché è fondamentale?

- gli screen reader riconoscono gli elementi per ciò che _sono_
    
- la navigazione da tastiera funziona da sola
    
- gli utenti capiscono la struttura della pagina anche senza vederla
    
- non devi reinventare niente
    

Un `<div onclick="...">Clicca qui</div>` è un errore grave.  
Un `<button>` fa tutto automaticamente: focus, tab, stato attivo, ruolo semantico corretto, interazione.

---

### **3. Linguaggio chiaro: anche questo è accessibilità**

Il contenuto testuale **fa parte dell’HTML**.  
La scelta delle parole è una responsabilità dello sviluppatore tanto quanto la scelta dei tag.

Regole rapide:

- evita abbreviazioni, o spiegale la prima volta
    
- non scrivere frasi ambigue
    
- usa strutture semplici e dirette
    
- evita lo slang
    
- evita giochi grafici nel linguaggio (ad esempio “5/7 ottobre” può essere ambiguo; meglio: “dal 5 al 7 ottobre”)
    

Un sito accessibile deve essere **comprensibile**, non solo “tecnicamente corretto”.

---

### **4. Navigabilità da tastiera: se la semantica è corretta, sei già a posto**

Un sito accessibile deve poter essere usato **senza mouse**.

La regola è semplice:

- se usi gli elementi corretti
    
- se non abusi dei `<div>`
    
- se mantieni i link come `<a>` e i bottoni come `<button>`
    
- se le form hanno le loro `<label>`
    

… allora la pagina è automaticamente navigabile via `Tab`.

Non devi “fare” nulla di aggiuntivo: basta non rompere la semantica.

---

### **5. Tabelle: struttura chiara per dati chiari**

Le tabelle sono un punto delicato per gli screen reader.  
Devono capire:

- chi è l’intestazione
    
- cosa descrive
    
- come si collegano celle e header
    

Struttura corretta:

```html
<table>
  <caption>Vendite mensili (esempio)</caption>
  <thead>
    <tr>
      <th scope="col">Mese</th>
      <th scope="col">Totale</th>
    </tr>
  </thead>

  <tbody>
    <tr>
      <th scope="row">Gennaio</th>
      <td>1200</td>
    </tr>
  </tbody>
</table>
```

Tre regole fondamentali:

1. usa `<caption>` se la tabella contiene dati
    
2. usa `<th>` per gli header (colonne o righe)
    
3. usa gli attributi `scope="col"` o `scope="row"` per aiutare gli screen reader
    

Se la tabella è complessa, puoi usare anche gli attributi `id` e `headers` per legare celle e header in modo esplicito.

---

### **6. Form: le label sono obbligatorie**

Regola assoluta:

```html
<label for="nome">Nome</label>
<input id="nome" type="text" placeholder="Inserisci il tuo nome" />
```

La label serve:

- per lo screen reader
    
- per l’accessibilità da tastiera
    
- per l’esperienza utente (clicco sulla label → attivo l’input)
    

Un placeholder **non sostituisce** la label.  
Serve solo come aiuto aggiuntivo.

---

### **7. Testo alternativo per le immagini**

Il famoso `alt`.

Regola d’oro:

- se l’immagine **porta un contenuto informativo**, descrivila.
    
- se è **puramente estetica**, lascia `alt=""`.
    

Esempio:

```html
<img src="grafico.png" alt="Andamento delle vendite da gennaio a marzo" />
```

Esempio estetico:

```html
<img src="decorazione.svg" alt="" />
```

Gli screen reader saltano automaticamente le immagini con alt vuoto:  
non infastidiamo l’utente con inutili descrizioni.

---

### **8. Figure + figcaption per descrizioni visive**

Se un’immagine richiede una descrizione visiva più lunga o più contestuale:

```html
<figure>
  <img src="foto_citta.jpg" alt="Panorama di Milano al tramonto" />
  <figcaption>Milano vista dal Duomo durante l’ora d’oro.</figcaption>
</figure>
```

`figcaption` fa parte della stessa unità semantica dell’immagine.

---

### **9. Link: niente “clicca qui”**

Lo screen reader deve poter capire **dove porta il link**.  
Il testo cliccabile deve essere informativo:

❌ “Clicca qui”  
❌ “Link”  
❌ “Scopri di più” (senza contesto)

✔ “Scarica il PDF della guida”  
✔ “Scopri di più sul progetto”  
✔ “Vai alla documentazione di JavaScript”

Se vuoi essere ancora più preciso, usa l’attributo `rel` quando serve.  
È parte dell’accessibilità e parte del SEO.

---

### **10. Peso delle immagini: importante, ma più per le performance**

Qui il focus è leggermente diverso.  
Il peso delle immagini non è accessibilità _in senso stretto_, ma migliora:

- performance
    
- tempo di caricamento
    
- punteggio SEO
    
- usabilità generale
    

Quindi sì, rientra comunque nel buon senso.

---

### **11. Il vero segreto: usare con intelligenza ciò che già conosci**

Se:

- usi i tag corretti
    
- scrivi testi chiari
    
- rispetti la struttura del layout
    
- dai sempre un significato semantico agli elementi
    
- compili bene i form
    
- dai un alt corretto alle immagini
    
- scegli link comprensibili
    

… sei già avanti rispetto al 90% dei siti che si trovano online.

Non servono “magie” per l’accessibilità:  
serve solo **non tradire le regole dell’HTML**, cioè lo scopo per cui è stato creato.

---

### **12. Conclusione e prossimo passo**

Questa è la base.  
Tra due video iniziamo a parlare di **ARIA**, la suite di attributi progettata per completare l’accessibilità quando HTML, da solo, non basta più.

Prima, però, installiamo uno screen reader:  
imparerai a leggere la pagina “con un altro paio di occhi”.