## **Lezione 3: Critical CSS**

---

## **1. Introduzione**

In questa lezione rimaniamo nell’ambito delle **performance** lato front-end e introduciamo un concetto chiave: il **Critical CSS**.

Abbiamo già visto:

- il **Critical Rendering Path** (tutti gli step per passare da “pagina richiesta” a “primi pixel visibili”),
    
- il **Render Blocking CSS** (il CSS che _blocca_ questo percorso).
    

Ora facciamo un passo in più: impariamo a distinguere **quale** CSS è davvero fondamentale subito e **quale può aspettare qualche millisecondo**, senza che l’utente se ne accorga.

---

## **2. Above the Fold e Below the Fold**

### **2.1 Il concetto di “fold”**

Prendiamo una pagina web qualsiasi.

Quando la pagina si carica, tu **non vedi tutto il documento**, ma solo la porzione che entra nel tuo schermo. Possiamo immaginare una linea orizzontale che separa:

- la parte **visibile senza scroll**
    
- la parte **che compare solo dopo lo scroll**
    

Quella linea concettuale è il **fold** (dall’inglese “to fold” = piegare), come se:

- la pagina fosse un **lenzuolo** o un **tovagliolo**,
    
- e tu vedessi solo la parte “piegata” sopra, mentre il resto è nascosto sotto.
    

### **2.2 Above the fold vs below the fold**

- **Above the fold** → la parte superiore visibile **subito**, senza scroll
    
- **Below the fold** → tutto ciò che si trova **sotto** e che vedi solo scrollando
    

Il punto critico è questo:

> Quando la pagina si carica, all’utente **interessa solo** che la parte _above the fold_ sia pronta, bella e leggibile.

La parte _below the fold_ deve arrivare, certo, ma può essere caricata **leggermente dopo**. Nel frattempo l’utente sta ancora guardando la parte alta e non ha neppure iniziato a scrollare.

---

## **3. Parallelismo con il lazy loading delle immagini**

### **3.1 Lazy loading e above the fold**

In HTML abbiamo già visto il concetto di **lazy loading** delle immagini:

- non ha senso caricare _subito_ tutte le immagini che stanno molto in basso,
    
- possiamo dire al browser: “carica queste immagini **quando l’utente si avvicina** scorrendo”.
    

Questo ottimizza:

- banda,
    
- tempo di caricamento,
    
- percezione di velocità.
    

### **3.2 Stessa idea applicata al CSS**

Possiamo applicare lo stesso principio al CSS:

- lo **stile necessario** per l’area _above the fold_ è **critico**,
    
- lo stile per la parte _below the fold_ è **non critico** e può essere caricato subito dopo, in modo meno prioritario.
    

Il browser è molto più veloce di noi:  
mentre noi guardiamo la parte alta e ci orientiamo, lui ha già il tempo di caricare il resto.

---

## **4. Definizione di Critical CSS**

### **4.1 CSS critico = CSS di facciata**

Possiamo ora definire in modo chiaro:

> **Critical CSS** = il CSS minimo indispensabile per stilare **la parte di pagina che l’utente vede immediatamente (above the fold)**.

È il CSS che:

- deve essere disponibile **istantaneamente**,
    
- deve permettere al browser di mostrare una **prima vista completa, leggibile e gradevole**,
    
- deve evitare che la pagina sembri rotta, non stilata o “spoglia”.
    

Metafora:

- **Critical CSS** = la facciata del ristorante: vetrina, insegna, ingresso pulito.
    
- **Non-critical CSS** = il retro del ristorante, l’uscita della cucina, i bidoni dell’immondizia: fondamentali per il funzionamento interno, ma l’utente non li vede inizialmente.
    

Se la facciata è brutta o non stilata, molti utenti se ne vanno subito.  
Se il retro non è ancora perfetto nei primi millisecondi, a loro non importa.

---

## **5. Il problema pratico: file CSS esterni**

### **5.1 Comodità per noi, costo per la macchina**

Per comodità nostra, come sviluppatori, teniamo spesso gli stili in un file esterno:

```html
<link rel="stylesheet" href="style.css">
```

Vantaggi per noi:

- stesso file condiviso tra più pagine,
    
- manutenzione centralizzata,
    
- organizzazione mentale migliore (file per navbar, footer, componenti, ecc.).
    

Svantaggio per la macchina:

- il browser deve fare una **richiesta aggiuntiva** (`HTTP request`) per ottenere `style.css`,
    
- finché non riceve tutto e non lo ha parsato, il CSS resta **render-blocking**.
    

Qui entra il classico compromesso dell’informatica:

> Ciò che è comodo per l’essere umano (organizzare in tanti file separati)  
> spesso comporta un lavoro aggiuntivo per la macchina (più richieste, più attesa).

L’ottimizzazione consiste nel trovare un equilibrio:

- noi vogliamo **ordine**,
    
- la macchina vuole **dati subito e con meno richieste possibili**.
    

---

## **6. Come funziona il Critical CSS nella pratica**

### **6.1 Idea di base**

La strategia del Critical CSS è:

1. **Mantenere** il file CSS esterno (o i vari file) per tutta la pagina → comodo per noi.
    
2. **Estrarre** da quei file solo le regole strettamente necessarie per la parte _above the fold_.
    
3. Mettere queste regole **direttamente nell’`<head>`**, dentro un tag `<style>` inline (non inline sugli elementi, ma inline nel documento).
    

In pseudocodice:

```html
<head>
  <style>
    /* Critical CSS: solo ciò che serve per l’area above the fold */
    body { margin: 0; font-family: system-ui, sans-serif; }
    header { ... }
    .hero { ... }
    .hero-title { ... }
    .btn-primary { ... }
  </style>

  <!-- CSS non critico, caricato subito dopo -->
  <link rel="stylesheet" href="style.css">
</head>
```

### **6.2 Perché questo non è render-blocking nello stesso modo**

- Il CSS dentro `<style>` è **già incluso** nella risposta HTML.
    
- Non richiede una chiamata separata → non aggiunge una “attesa extra” di rete.
    
- Il browser può **stilare immediatamente** la parte iniziale della pagina.
    

Il file `style.css` esterno esiste ancora, ma:

- può contenere tutto il resto (stili per sezioni più in basso, componenti secondari, ecc.),
    
- può essere caricato appena dopo,
    
- nel frattempo l’utente vede già una pagina sensata e “bella” nella parte alta.
    

---

## **7. Collegare Critical CSS e Render Blocking**

### **7.1 Come riduciamo l’effetto bloccante**

Quando tutto il CSS è in un file esterno enorme:

- il browser è **costretto** ad aspettare che arrivi **tutto** prima di poter rendere correttamente la pagina,
    
- quindi il CSS è fortemente **render-blocking**.
    

Con il Critical CSS:

- la parte critica per il **First Contentful Paint** è **già inclusa nell’HTML**,
    
- il file CSS esterno diventa meno “critico” per la parte above the fold,
    
- il tempo percepito di caricamento migliora molto:
    
    - l’utente vede qualcosa di completo _above the fold_ quasi subito,
        
    - il resto della pagina viene “rifinito” mentre lui ancora non scrolla o sta iniziando a farlo.
        

---

## **8. Ancora sulla metafora del fast food**

Riprendiamo la metafora del fast food:

- **HTML + Critical CSS nella `<head>`** = vassoio completo con:
    
    - panino,
        
    - patatine,
        
    - bevanda.  
        Hai tutto quello che ti serve per iniziare a mangiare.
        
- **CSS non critico nel file esterno** = il gelato:
    
    - lo puoi prendere **dopo**,
        
    - ti stampano lo scontrino,
        
    - vai alla cassa quando vuoi e lo ritiri.
        

In termini di pagina web:

- **Critical CSS** = ciò che serve per avere un sopra-la-piega perfetto (header, hero, navbar, primo blocco di testo).
    
- **Non-critical CSS** = tutto ciò che riguarda sezioni più in basso e componenti non immediatamente visibili.
    

L’utente è felice se:

- in pochi millisecondi vede il “vassoio” completo della parte iniziale del sito;
    
- non gli importa se, mentre lui legge, in background si stanno ancora caricando stili per la sezione “Testimonianze” in fondo alla pagina che forse non vedrà mai.
    

---

## **9. Benefici concreti del Critical CSS**

Implementare il Critical CSS porta:

- **miglioramento del First Contentful Paint (FCP)** → la prima cosa visibile compare prima;
    
- **sensazione di velocità** → l’utente percepisce il sito come “reattivo”;
    
- **migliori punteggi in Lighthouse / PageSpeed** → meno risorse di blocco visualizzazione;
    
- **migliore SEO tecnico** → Google premia siti veloci e ottimizzati.
    

Tutto questo senza rinunciare alla nostra organizzazione per file:

- noi continuiamo a usare `style.css` (e altri file),
    
- ma un piccolo sottoinsieme di stili viene “promosso” a **critical** e messo direttamente nell’HTML.
    

---

## **10. Ricapitolazione**

- Esiste una distinzione fondamentale tra:
    
    - **Above the fold** (parte visibile subito),
        
    - **Below the fold** (parte che compare solo dopo lo scroll).
        
- Il **Critical CSS** è il CSS **minimo indispensabile** per stilare in modo completo e decente l’area _above the fold_.
    
- Il CSS esterno è comodo per noi, ma implica richieste extra → quindi _render blocking_.
    
- La soluzione è:
    
    - mettere il Critical CSS **direttamente nella `<head>`** dentro `<style>`,
        
    - spostare il resto nel file esterno,
        
    - ridurre al minimo l’impatto bloccante per il primo rendering.
        

---

## **11. Conclusione**

Ora hai una chiara visione di:

- cosa significa **Critical CSS**,
    
- perché è collegato ad **above/below the fold**,
    
- come si integra con il problema del **render blocking**,
    
- e perché è uno strumento chiave per ottimizzare l’esperienza utente.
    

Nel **prossimo video/lezione** si affronta un altro punto importante riguardo al CSS e alle performance. Quando vuoi, possiamo formalizzarlo nello stesso stile.