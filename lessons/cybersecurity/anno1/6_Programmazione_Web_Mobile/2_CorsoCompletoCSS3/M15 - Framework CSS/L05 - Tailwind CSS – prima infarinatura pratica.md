## **Lezione 5: Tailwind CSS – prima infarinatura pratica**

---

### **1. Obiettivo: vedere Tailwind “in azione”**

In questa lezione non vogliamo imparare _tutto_ Tailwind (sarebbe un corso a parte), ma:

- capire **come si usa al volo** in un file HTML normale,
    
- vedere **come le utility class sostituiscono il CSS**,
    
- costruire un piccolo esempio concreto,
    
- intuire **pro e contro** pratici dell’approccio Tailwind.
    

L’idea è: non “so usare Tailwind perfettamente”, ma:

> “So che cos’è, dove si incastra nel mondo CSS, e riesco a leggere un pezzo di codice Tailwind senza andare in panico.”

---

### **2. Come usare Tailwind senza build, Node, NPM & co.**

Sul sito di Tailwind, nella sezione **Get Started**, la prima cosa che vedi sono comandi tipo:

- `npm install tailwindcss`
    
- `npx tailwindcss init`
    

Questa è l’installazione **“seria”**, pensata per progetti con build, framework JavaScript, ecc.

Per il nostro obiettivo didattico (HTML + CSS, niente Node), usiamo la strada più semplice:

> **Play CDN di Tailwind** – una `<script>` che carichi direttamente in pagina.

#### **2.1 HTML di partenza**

Immaginiamo un file `index.html` minimale:

```html
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8" />
    <title>Prova Tailwind</title>

    <!-- Tailwind Play CDN -->
    <script src="https://cdn.tailwindcss.com"></script>

    <!-- Configurazione base di Tailwind lato client -->
    <script>
      tailwind.config = {
        theme: {
          extend: {},
        },
      };
    </script>
  </head>

  <body>
    <!-- Qui faremo gli esempi -->
  </body>
</html>
```

Cosa succede:

- lo `<script src="https://cdn.tailwindcss.com">` carica Tailwind **dal CDN**, come abbiamo fatto con Bootstrap;
    
- lo `<script> tailwind.config = { … } </script>` è una mini configurazione lato client:
    
    - per ora lasciamo `extend: {}` vuoto,
        
    - in progetti seri questa config vive in un file `tailwind.config.js` ma qui ci basta “giocare”.
        

**Niente file CSS esterni**: per adesso non scriviamo più `style.css`.

---

### **3. Primo esempio: un quadrato 80×80 rosso**

Obiettivo: creare un quadrato di 80×80 pixel con sfondo rosso.

Con CSS “classico”, avresti fatto:

```css
div {
  width: 80px;
  height: 80px;
  background-color: red;
}
```

e in HTML:

```html
<div></div>
```

Con Tailwind, **non tocchi il CSS**:  
scrivi tutto con le classi direttamente nell’HTML:

```html
<body>
  <div class="w-20 h-20 bg-red-400"></div>
</body>
```

Vediamo cosa significa:

- `w-20` → larghezza secondo la **scala di spacing** di Tailwind.  
    Per default:
    
    - `1` unità = `0.25rem` ≈ 4 px
        
    - `20` unità = `20 × 4px` = **80 px**
        
- `h-20` → stessa cosa sull’altezza → **80 px**.
    
- `bg-red-400` → colore di sfondo:
    
    - `red` è il nome della palette,
        
    - `400` è la **shade** (più bassa → colore più chiaro, più alta → colore più scuro).
        

Risultato: un quadrato 80×80, rosso, creato **senza scrivere CSS**, solo con utility class.

Se vuoi un rosso più acceso/scuro, puoi provare:

- `bg-red-500`
    
- `bg-red-600`
    
- ecc.
    

---

### **4. Colori e palette di Tailwind**

Tailwind definisce una palette di colori molto strutturata:

- nomi come `red`, `blue`, `emerald`, `sky`, `slate`, `zinc`, ecc.
    
- per ogni nome, hai varie **shade**: `50`, `100`, `200`, …, `900`, e talvolta `950`.
    

Esempio:

```html
<body class="bg-zinc-950">
  <p class="text-white">
    Ciao, sono in modalità sfondo quasi nero.
  </p>
</body>
```

- `bg-zinc-950` → sfondo quasi nero (zinc “ultra scuro”).
    
- `text-white` → testo bianco.
    

In pratica:

> Ogni combinazione `bg-COLORE-SHADE` o `text-COLORE-SHADE` è una regola CSS pronta, richiamabile tramite una classe.

---

### **5. Esempio più interessante: box fissato in basso a destra**

Ora costruiamo qualcosa di un filo più articolato.

Vogliamo:

- un box con testo dentro,
    
- un po’ di padding interno,
    
- bordi arrotondati,
    
- posizionato in **basso a destra** dello schermo,
    
- su sfondo scuro.
    

Codice:

```html
<body class="bg-zinc-950">
  <div
    class="absolute bottom-0 right-0 p-5 rounded-xl bg-red-400 text-white"
  >
    Sono un box in basso a destra
  </div>
</body>
```

Spieghiamo le classi:

- `absolute` → `position: absolute;`
    
- `bottom-0` → `bottom: 0;`
    
- `right-0` → `right: 0;`
    
- `p-5` → padding secondo la scala spacing:
    
    - `5` → `5 × 4px = 20px` (circa) di padding interno.
        
- `rounded-xl` → bordi arrotondati “extra large”.
    
- `bg-red-400` → sfondo rosso.
    
- `text-white` → colore del testo bianco.
    

Tutte queste sono **utility class**:

> Ogni classe = una singola regola CSS (o poco più) con un valore preciso.

---

### **6. Tailwind come sostituto del file CSS**

Quello che stiamo facendo, in pratica, è:

- prendere **ciò che avremmo scritto in `style.css`**,
    
- tradurlo in una sequenza di **classi** dentro l’attributo `class` dell’elemento.
    

Esempio “traduzione mentale”:

- `width: 80px;` → `w-20`
    
- `height: 80px;` → `h-20`
    
- `background-color: red;` → `bg-red-400`
    
- `padding: 20px;` → `p-5`
    
- `border-radius: 12px;` (per dire) → `rounded-xl`
    
- `position: absolute; bottom: 0; right: 0;` → `absolute bottom-0 right-0`
    

È una mappa **regola CSS → utility class**.

---

### **7. Il lato oscuro: HTML che esplode di classi**

Tailwind è comodo, ma porta con sé **un problema evidente**:

- ogni elemento HTML ha una **lista molto lunga di classi**;
    
- più il componente grafico è complesso, più la lista di classi si allunga.
    

Effetti collaterali:

1. **Più caratteri nell’HTML**
    
    - Il file HTML “pesa” di più (più testo da scaricare).
        
    - Non è una tragedia, ma è comunque un costo.
        
2. **Markup meno leggibile**
    
    - Un `<div>` con 10–15 classi è meno immediato da leggere/gestire.
        
3. **Ripetizione delle stesse utility**
    
    - Se hai 10 card tutte simili, rischi di copincollare la stessa sequenza di classi 10 volte.
        

Mentre con CSS classico o Bootstrap:

- definivi una `.card` nel CSS e poi usavi `<div class="card">` ovunque;
    
- la “ricetta” grafica era centralizzata in un file.
    

Con Tailwind **puro**:

- la “ricetta” grafica è scritta **dentro il markup**,
    
- ripetuta per ogni istanza del componente, a meno che non crei un qualche strato di astrazione (componenti, partials, ecc.).
    

Questo ci porta al **secondo problema** (che l’autore del corso cita come cliffhanger):

> Come gestiamo il **riuso** dei componenti (card, alert, box, ecc.)  
> se la “logica grafica” è incollata dentro class list lunghe?

La risposta sta nel mondo:

- dei **componenti** (React, Vue, Blade, ecc.),
    
- della **configurazione avanzata di Tailwind**,
    
- e di tool che “compressano” questo markup in qualcosa di più gestibile.
    

Ma questa è, appunto, **la puntata successiva**.

---

### **8. Ricapitolazione**

In questa lezione hai visto:

1. Come usare **Tailwind via Play CDN** senza build tool:
    
    - `<script src="https://cdn.tailwindcss.com"></script>`
        
    - mini config con `tailwind.config = { … }`.
        
2. Come creare uno stile **senza scrivere CSS**:
    
    - un quadrato 80×80 con `class="w-20 h-20 bg-red-400"`.
        
    - uno sfondo scuro con `body class="bg-zinc-950"`.
        
    - un box fisso in basso a destra con `absolute bottom-0 right-0 ...`.
        
3. Il cuore dell’approccio Tailwind:
    
    - **una classe = una regola CSS** (utility-first).
        
4. Un primo sguardo ai **contro**:
    
    - HTML più lungo,
        
    - tanto codice ripetuto nelle classi,
        
    - mancanza (a prima vista) di un concetto di “componente” pronto come in Bootstrap.
        

Nella prossima “puntata” l’idea è proprio quella di prendere questo problema e domandarci:

> “Ok, ma se voglio una vera _card riutilizzabile_ con Tailwind, come faccio a non impazzire?”

Ed è lì che si entra nell’ecosistema avanzato di Tailwind e nel legame con i framework JavaScript e i componenti.