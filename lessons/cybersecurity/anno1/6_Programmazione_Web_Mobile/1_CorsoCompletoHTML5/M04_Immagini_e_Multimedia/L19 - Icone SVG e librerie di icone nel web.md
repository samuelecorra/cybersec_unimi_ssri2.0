## **Lezione 19: Icone SVG e librerie di icone nel web**

### **1. Perché le icone meritano una lezione a parte**

Le icone non sono solo “altre SVG”.  
Sono un pezzo a sé del **linguaggio visivo del web**:

- icona Twitter / X
    
- icona GitHub, Figma
    
- icona lente per la ricerca
    
- icona “freccia” per aprire un menu o indicare una versione
    
- icona “razzo” per indicare la versione Pro / Premium
    
- tutte le micro-icone che vedi nei bottoni, nei menu, nelle card…
    

Le icone sono ormai un linguaggio universale:

- sul web
    
- nella segnaletica (cartelli stradali)
    
- sui telefoni (icone delle app, impostazioni, ecc.)
    

Sul sito:

- possono **sostituire o rafforzare il testo** (es. cestino = elimina)
    
- possono **rendere più leggibile** una lista (testo + iconcina a sinistra)
    
- possono **aggiungere colore** e ritmo visivo (non solo testo grigio)
    

Quindi, anche se tecnicamente molte sono semplici SVG, **a livello di design** le icone sono una categoria a parte e vanno gestite con una strategia dedicata.

---

### **2. Dove troviamo icone sul web**

Esempi tipici:

- in header e navbar (icone login, profilo, home, ricerca)
    
- nei bottoni (“Scarica”, “Avanti”, “Indietro”, “Play”, “Condividi”)
    
- negli elenchi di features (“Veloce”, “Sicuro”, “Scalabile”, ecc.)
    
- nei pricing table (spunte, X, razzi, stelline, lock, ecc.)
    

Spesso potresti lasciare solo il testo.  
Ma aggiungendo l’icona:

- si capisce prima il significato
    
- hai una “scusa” per inserire colore e stile
    
- la pagina risulta molto più “viva”
    

---

### **3. Strumenti principali per le icone**

Vediamo tre approcci/librerie citati:

1. **Heroicons** → icone come SVG puri
    
2. **Font Awesome** → icone come “font” (via `<i>` e classi)
    
3. **Google Fonts Icons (Material Icons)** → icone come font o come elementi con classi specifiche
    

Ognuno risolve lo stesso problema ma con una filosofia diversa.

---

### **4. Heroicons: icone SVG “pure”**

Vai su **Heroicons**:

- trovi icone in diversi stili (outline, solid, mini, ecc.)
    
- scegli un’icona (es. una cartella, una casa, un’icona generica)
    
- hai due pulsanti di copia:
    
    - **SVG**
        
    - **JSX** (per React, che al momento non ci interessa)
        

Noi usiamo **SVG**.

Esempio tipico:

```html
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none">
    <!-- path, linee, ecc. -->
</svg>
```

Cosa facciamo:

- **copiamo** lo snippet SVG
    
- **incolliamo** direttamente nell’HTML
    
- salviamo
    
- otteniamo subito l’icona nella pagina
    

Caratteristiche:

- è un normale SVG, come quelli visti nella lezione precedente
    
- per ora può sembrare grande o piccola → la sistemeremo con il CSS
    
- non serve importare librerie esterne (nessun link, nessun kit)
    
- è molto semplice e diretto
    

---

### **5. Font Awesome: icone come “font” (via `<i>` + classi)**

Secondo strumento: **Font Awesome**.

È una libreria enorme di icone, organizzate come un “font di icone”:

1. Normalmente ti iscrivi (`Start for free`), lasci l’email e scarichi un **kit**.
    
2. Il kit ti spiega come:
    
    - includere uno script/link nel `<head>`
        
    - usare le icone nel `<body>` tramite tag `<i>` con classi specifiche.
        

Per evitare la parte di configurazione completa, nel video viene usata una **CDN**:

- una URL ospitata online che punta ai file di Font Awesome
    
- la inserisci nel `<head>` con `<link>` o `<script>`
    
- e sei subito operativo
    

> Nota: la CDN è semplicemente un server esterno che ti fornisce i file già pronti. Approfondirai il concetto più avanti.

#### **Uso pratico di Font Awesome**

Dopo aver incluso la CDN/kit nel `<head>`, nel body scrivi:

```html
<i class="fa-solid fa-house"></i>
```

- `<i>` prima voleva dire _italic_, testo in corsivo
    
- qui viene riutilizzato come contenitore di icone
    
- le classi (`fa-solid`, `fa-house`, ecc.) dicono al CSS:  
    “In questo punto, disegna l’icona ‘house’ dello stile solid”.
    

Risultato: la `i` non mostra testo, ma l’icona.

**Pro:**

- libreria enorme
    
- migliaia di icone pronte
    
- molte categorie, anche pro/premium
    

**Contro:**

- serve importare il kit / CDN
    
- alcune icone sono a pagamento
    
- carichi un font di icone, quindi un asset in più a livello di performance
    

---

### **6. Google Fonts + Material Icons**

Terzo approccio: **Google Fonts Icons** (Material Icons).

Dal sito di Google Fonts puoi:

- scaricare font
    
- usare **icone** in stile Material Design
    

Quando selezioni un’icona:

1. Google ti offre diversi modi per importarla:
    
    - link `<link>` nel `<head>`
        
    - import via CSS
        
    - altri meccanismi
        
2. Nel nostro caso usiamo la versione **web** con il classico `<link>`.
    

Esempio di link nel `<head>`:

```html
<link rel="stylesheet" href="https://fonts.googleapis.com/...">
```

#### **Come si usa l’icona**

Material Icons spesso usa un **tag `<span>`** con classi e testo interno:

```html
<span class="material-icons">
    home
</span>
```

- le **classi** definiscono lo stile (filled, rounded, outlined, ecc.)
    
- il **testo interno** (`home`, `search`, ecc.) identifica l’icona
    

Cambiando lo stile (per esempio `material-icons-rounded`) cambi il look dell’icona.

Quindi:

- Font Awesome → `<i class="fa fa-house">`
    
- Material Icons → `<span class="material-icons">home</span>`
    

Ogni libreria ha il suo modo di “mappare” il codice HTML sull’icona.

---

### **7. Confronto tra i tre approcci**

#### **Heroicons (SVG puri)**

- Copi direttamente lo SVG nel tuo HTML
    
- Nessuna libreria da importare
    
- Molto pulito e moderno
    
- Codice SVG più verboso (più righe), ma **nessun asset esterno**
    

#### **Font Awesome**

- Richiede il kit / CDN
    
- Usa `<i>` con classi
    
- Tantissime icone, anche versioni pro
    
- Ottimo per progetti grandi, ma è un ecosistema a parte
    

#### **Material Icons (Google)**

- Richiede link nel `<head>` (Google Fonts)
    
- Usa `<span>` con classi + nome icona nel testo
    
- Stile coerente con Material Design
    
- Facile da cambiare stile (filled, rounded, outlined…)
    

---

### **8. Bilanciamento: codice vs asset esterni**

Dal punto di vista delle performance:

- **SVG inline (Heroicons)**:
    
    - più codice nell’HTML
        
    - nessun asset esterno da scaricare
        
- **Icon font (Font Awesome, Material Icons)**:
    
    - poco codice nel body (`<i>`, `<span>`)
        
    - ma un font / CSS esterno da scaricare (e tenere aggiornato)
        

È sempre un gioco di equilibrio:

- meno codice HTML ↔ più librerie esterne
    
- più codice HTML ↔ meno dipendenze esterne
    

La scelta dipende dal progetto:

- sito grande, con tantissime icone → può avere senso una grossa libreria
    
- sito più piccolo, o design molto personalizzato → SVG mirati possono bastare
    

---

### **9. Quale usare e quando**

- **Heroicons**  
    Ottimo per progetti moderni, soprattutto se usi framework più avanti (es. Tailwind).  
    Copia-incolla lo SVG e sei a posto.
    
- **Font Awesome**  
    Enorme catalogo, ideale se hai bisogno di _tutte_ le icone possibili.  
    Parte della raccolta è Pro (a pagamento).  
    Prezzi pensati per professionisti/team, non per chi è all’inizio.
    
- **Material Icons**  
    Perfetto se ami lo stile Google / Material Design.  
    Integrazione comoda tramite Google Fonts.
    

---

Queste sono le basi per lavorare in modo consapevole con le **icone nel web**.

Ora hai il quadro completo:

- icone come SVG puri
    
- icone come font (via classi)
    
- pro e contro di ogni approccio
    
- relazione tra design, performance e sviluppo
    

Puoi passare al video di chiusura del modulo quando vuoi.