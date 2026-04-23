## **Lezione – I Preprocessori: quali sono, cosa fanno e che differenze hanno**

### **1. Dove siamo arrivati**

Nella lezione precedente hai capito:

- cosa sono i preprocessori
    
- perché sono nati
    
- quali problemi risolvevano
    
- perché oggi sono meno “obbligatori” rispetto al passato
    

Ora ci serve una **mappa mentale chiara**:

> **Quali preprocessori esistono?  
> Come si chiamano?  
> Che differenze ci sono tra loro?**

La risposta breve:

➤ **Ce ne sono tanti minori, ma i 3 realmente famosi e usati nella storia del web moderno sono:**

1. **Sass / SCSS**
    
2. **Less**
    
3. **Stylus**
    

Questi sono i nomi che sentirai ovunque.  
E infatti… li trovi nei siti che sembrano usciti dal 2010, perché è lì che hanno dominato davvero.

---

## **2. I tre grandi preprocessori**

### **2.1. Less**

- Sito vecchio stile 2010 → già ti fa capire l’epoca
    
- Nome basato sul gioco di parole: **“LESS” = “LESS CSS”**
    
- Nato per aggiungere poche funzionalità a CSS
    
- Sintassi più “vicina” al CSS rispetto a Sass versione classica
    
- Usatissimo nei primi anni 2010 (es. Bootstrap 2 e 3)
    

Esempio di variabile:

```less
@colorePrimario: #3498db;

button {
  background: @colorePrimario;
}
```

---

### **2.2. Stylus**

- Sintassi molto permissiva
    
- Puoi scrivere con o senza parentesi graffe, senza due punti, senza punto e virgola
    
- Molto “artistico”, quasi Python–like
    
- Popolare in progetti Node.js (es. Express, Meteor)
    

Esempio Stylus (senza graffe e senza due punti):

```stylus
colorePrimario = #3498db

button
  background colorePrimario
```

È il preprocessore più “minimalista” e più libero sintatticamente.

---

### **2.3. Sass (nelle sue due forme)**

Sass è _IL_ preprocessore.  
Quello più diffuso e più “standard” da imparare (se mai ne impari uno).

Ma Sass **non è un solo linguaggio**, ed è qui che spesso ci si confonde.

Esistono **due sintassi**:

1. **Sass (estensione `.sass`)**  
    – sintassi antica, senza parentesi, senza punto e virgola  
    – stile “indentato”, simile a Stylus
    
2. **SCSS (estensione `.scss`)**  
    – sintassi moderna  
    – sembra CSS normale, ma con tutte le feature dei preprocessori
    

Esempio Sass “vecchia sintassi” (`.sass`):

```sass
$colore: #3498db
button
  background: $colore
```

Esempio SCSS moderno (`.scss`):

```scss
$colore: #3498db;

button {
  background: $colore;
}
```

Oggi:

> **la sintassi usata da TUTTO il mondo è SCSS**  
> mentre `.sass` è ormai storico/obsoleto.

---

## **3. Ma allora Sass e SCSS sono la stessa cosa?**

Sì e no.

- **Sass** è il preprocessore
    
- **SCSS** è _una delle due sintassi di Sass_
    

Quindi:

- il preprocessore si chiama **Sass**
    
- i file possono essere `.sass` oppure `.scss`
    

Il mondo reale usa al 99%:

```
.scss
```

Perché:

- è più leggibile
    
- sembra CSS normale
    
- richiede meno “shock sintattico”
    
- è compatibile con esempi moderni e guide attuali
    

---

## **4. Le sintassi a confronto**

### **Less**

```less
@spazio: 8px;

.card {
  margin: @spazio;
}
```

### **Stylus**

```stylus
spazio = 8px

.card
  margin spazio
```

### **SCSS**

```scss
$spazio: 8px;

.card {
  margin: $spazio;
}
```

### **Sass (vecchio stile indentato)**

```sass
$spazio: 8px
.card
  margin: $spazio
```

L’idea è sempre la stessa:  
**più potenza → più comodità → prima del preprocessore → poi CSS pulito.**

---

## **5. Devo impararli davvero?**

Risposta onesta e aggiornata al 2025:

- **NO** se sei all’inizio
    
- **NO** se il tuo progetto non lo richiede
    
- **NO** se usi framework moderni, dove i preprocessori non sono più fondamentali
    
- **NO** se usi CSS moderno + variabili + nesting + Tailwind + componenti
    

Ma:

- **SÌ** se entri in un progetto aziendale vecchio
    
- **SÌ** se collabori su progetti Angular/React datati che usano `.scss`
    
- **SÌ** se vuoi avere la “cultura generale” da sviluppatore serio
    
- **SÌ** se vuoi capire gli strumenti negli stack legacy
    

Quindi nel tuo percorso:

1. **prima CSS moderno puro**
    
2. **poi JavaScript**
    
3. **poi i framework veri (React/Vue/Next/Svelte)**
    
4. **solo se necessario → Sass**
    

Il resto (Less, Stylus) è _quasi morto_ a livello industriale.

---

## **6. Perché non li installiamo ora nel corso**

Perché:

- serve Node.js
    
- serve il terminale
    
- serve NPM
    
- servono concetti di build e bundler
    
- serve capire l’ecosistema JavaScript
    
- rischieresti di confonderti, non avendo ancora JavaScript
    

E infatti:

> nel corso di JavaScript vedrai un’introduzione a Node + NPM  
> e lì potrai capire **davvero** come si installano e si compilano.

Per ora ti serve solo la **mappa mentale** per non confonderti tra le sigle.

---

## **7. Ricapitolone finale**

Ecco la mappa completa, chiara e definitiva:

```
CSS
│
├── Framework di Design
│   ├── Bootstrap
│   ├── Tailwind
│   ├── Foundation
│   └── Bulma
│
└── Preprocessori
    ├── Less
    ├── Stylus
    └── Sass
         ├── .sass  (vecchio stile indentato)
         └── .scss  (stile moderno → quello usato oggi)
```

I preprocessori:

- ti permettono di scrivere “super-CSS”
    
- aggiungono variabili, nesting, mixin, operatori, modularità
    
- poi trasformano tutto in **CSS normale**
    
- sono strumenti **per noi**, non per il browser
    

CSS moderno sta lentamente **assorbendo** tutte le funzioni storicamente introdotte dai preprocessori… ed è per questo che oggi sono meno indispensabili di 10 anni fa.

---

## **Prossima lezione**

Chiudiamo la sezione con l’argomento più importante:

➡ **Sass vs SCSS – perché esistono due sintassi, come riconoscerle, come distinguerle, e quale devi usare (spoiler: SCSS).**