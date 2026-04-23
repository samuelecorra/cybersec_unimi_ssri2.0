## **Lezione 15 – La Paginazione (Pagination)**

### **1. Che cos’è la Paginazione**

La **paginazione** è un componente di navigazione che permette di **suddividere molti risultati** in più pagine, ciascuna con un numero limitato di elementi.

È un pattern fondamentale ovunque ci siano:

- liste lunghe
    
- risultati di ricerca
    
- articoli di blog
    
- cataloghi di e-commerce
    
- forum
    

E serve a evitare:

- caricamenti giganteschi,
    
- scroll infiniti,
    
- disorientamento dell’utente.
    

---

## **2. Perché esiste la paginazione**

Immagina di avere **100 risultati** ma poter mostrare solo 10 elementi per pagina.  
Che fai?

Li **spezzi** in:

- pagina 1 → risultati 1–10
    
- pagina 2 → risultati 11–20
    
- pagina 3 → risultati 21–30  
    …
    
- pagina 10 → risultati 91–100
    

E in fondo alla pagina mostri questo tipo di navigazione:

**1 — 2 — 3 — … — 10**  
con _Prev_ e _Next_ a lato.

Questo è esattamente ciò che avviene in ogni sito moderno.

---

## **3. Dove la vedi tutti i giorni**

### ✔ **Google (prima dell’infinite scroll)**

Le SERP di Google storicamente avevano:

**Gooooooogle** con tutte le pagine numerate.

Oggi Google usa spesso l’**infinite scroll**, che è un modo alternativo:

- inizi a scrollare
    
- lui carica automaticamente altri risultati
    
- continui a scrollare finché non ce la fai più
    

Però l’infinite scroll ha problemi reali:

- perdi il “punto esatto” in cui eri
    
- tornare in cima è scomodo
    
- non puoi linkare la “pagina 3”
    
- la memoria del browser può gonfiarsi
    

Per questo motivo **la paginazione non è sparita** ed è ancora la scelta migliore quando:

- i risultati sono **strutturati**
    
- serve **ordinare o filtrare**
    
- serve riferirsi a una pagina specifica (3, 7, 12…)
    

---

## **4. Esempio classico: un blog**

Se hai un blog con:

- 23 articoli pubblicati
    

e vuoi mostrarne 8 per pagina, avrai:

- pagina 1 → articoli 1–8
    
- pagina 2 → articoli 9–16
    
- pagina 3 → articoli 17–23
    

E in fondo comparirà automaticamente una paginazione:

**1 — 2 — 3 →**

È il modo più chiaro e pulito per organizzare contenuti editoriali.

---

## **5. Paginazione nei framework CSS**

### **Tailwind / Flowbite**

Offrono componenti già pronti:

- numeri delle pagine
    
- frecce “Previous / Next”
    
- versioni mini o full-width
    
- varianti con ellissi “…”
    

### **Bootstrap**

Ha le classiche pagine numerate con uno stile pulito, tipico dei backoffice:

```html
<ul class="pagination">
  <li class="page-item"><a class="page-link" href="#">Previous</a></li>
  <li class="page-item"><a class="page-link" href="#">1</a></li>
  <li class="page-item"><a class="page-link" href="#">2</a></li>
  <li class="page-item"><a class="page-link" href="#">Next</a></li>
</ul>
```

---

## **6. Perché nel tuo corso non la implementiamo**

La paginazione, per funzionare davvero, richiede:

- un **database**
    
- una **query** che calcoli quanti risultati totali esistono
    
- un **backend** che decida quali elementi tornare alla pagina X
    
- logica dinamica per costruire gli URL
    

Fatta a mano è:

- un incubo da mantenere
    
- un’antipattern se il sito ha contenuti dinamici
    
- completamente inutile se poi i contenuti cambiano
    

Per questo la imparerai **solo** quando avremo:

- JavaScript avanzato
    
- framework moderni (React, Next.js, SvelteKit)
    
- oppure un backend completo (Node, PHP, Python, ecc.)
    

Lì diventerà naturale.

---

## **7. Differenza rapida: Infinite Scroll vs Pagination**

### **Infinite scroll**

- Perfetto per feed social (Instagram, TikTok)
    
- Facilissimo da usare su mobile
    
- Ma: ti perdi, non puoi “andare alla pagina 5”, la UX è confusa su dataset grandi.
    

### **Paginazione**

- Perfetta per blog, SERP, marketplace, cataloghi
    
- Ti permette di **orientarti**
    
- Ogni pagina è un’entità precisa
    
- SEO friendly
    
- UX più chiara
    

---

## **8. Quando la userai nel tuo sito**

Sul tuo sito (blog dell’Academy) la paginazione:

- esiste già
    
- si attiverà **automaticamente** quando supererai il numero massimo di articoli per pagina
    

Per ora non la vedi semplicemente perché:

> non hai abbastanza contenuti per costringere la UI a dividere le pagine.

Appena raggiungerai il limite, comparirà la **pagina 2**, poi la **3**, e così via.

---

## **9. Conclusione**

La paginazione è uno dei componenti fondamentali del design moderno, molto più robusta dell’infinite scroll per strutture grandi o professionali.  
Non si implementa in HTML puro, ma si capisce come concetto perché governa la navigazione nei grandi dataset.

---

## **Prossima lezione**

**I macro-blocchi di un sito web** — come si struttura davvero una pagina: header, hero, content, CTA, footer, e come funzionano insieme.