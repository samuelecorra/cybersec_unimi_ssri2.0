## **Lezione 3: Web Font (Google Fonts) — Come importarli e usarli nel progetto**

### **1. Introduzione ai Web Font**
I **web font** sono font caricati dal web (da un server esterno) e non dal dispositivo dell’utente.  
Fra tutti i servizi disponibili, **Google Fonts** è di fatto lo standard del settore, perché offre:

- font gratuiti sia per uso personale che commerciale,
- ottimizzazione per il web,
- caricamento veloce tramite CDN,
- centinaia di varianti e famiglie,
- interfaccia chiara per scegliere categorie, pesi e stili.

Per comodità, nel corso useremo l’espressione **“Google Font”** per riferirci ai web font in generale.

---

### **2. Esplorare Google Fonts**
Quando apri https://fonts.google.com trovi:

- **Categorie**: Serif, Sans-serif, Monospace, Display, Handwriting (equivale a Script).
- **Lingue**: utili se ti servono caratteri speciali (greco, cirillico, vietnamita…).
- **Pesi e stili**: da Thin (100) a ExtraBold (900), con o senza corsivo.
- **Anteprime personalizzabili**: puoi scrivere una frase e vedere come appare.

Ogni famiglia mostra:

- il **nome del font**,
- una frase di anteprima,
- il numero di varianti disponibili,
- il link per aprire le opzioni di importazione.

---

### **3. Selezionare gli stili**
Ogni font può avere molte varianti.  
Esempio: **Roboto** ha versioni:

- Thin, Light, Regular, Medium, Bold, Black,
- e ciascuna in versione normale + Italic.

Quando clicchi su una variante, questa viene aggiunta alla tua selezione.

> **Consiglio:** importa solo gli stili che usi davvero.  
> Ogni stile pesa qualche kilobyte e rallenta leggermente la pagina.

---

### **4. Metodo 1: Importazione tramite `<link>`**
È il metodo più comune e quello ufficialmente consigliato.

1. Vai su Google Fonts.
2. Scegli il font e gli stili.
3. Copia i tag `<link>` suggeriti, che includono:

   - il **preconnect** (ottimizza la connessione ai server Google),
   - il link al foglio di stile contenente il font.

Esempio:

```html
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400&display=swap" rel="stylesheet">
```

Di solito questi `<link>` vanno messi **nel `<head>`**, preferibilmente dopo eventuali meta importanti per non bloccare la renderizzazione.

#### **Usarlo nel CSS**

Dopo aver inserito i `<link>`, puoi attivare il font:

```html
font-family: 'Roboto', sans-serif;
```

Google ti suggerisce sempre anche la famiglia di fallback corretta.

---

### **5. Metodo 2: Import tramite `@import`**

Funziona allo stesso modo, ma si usa _dentro_ il CSS.  
È leggermente meno performante, ma accettabile per progetti semplici.

Esempio:

```html
@import url('https://fonts.googleapis.com/css2?family=Pacifico&display=swap');`
```

Poi puoi usare il font:

```html
font-family: 'Pacifico', cursive;
```

#### **Attenzione**

Se usi `@import`, deve essere la **prima riga** del file CSS, prima di qualunque regola.

---

### **6. Esempio completo**

Supponiamo di voler usare **Roboto** per tutto il sito e **Pacifico** solo per i titoli.

#### **HTML**

```html
<head>   
	<link rel="preconnect" href="https://fonts.googleapis.com">   
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>   
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400&family=Pacifico&display=swap" rel="stylesheet"> 
</head>
```

#### **CSS**

```css
body {   font-family: 'Roboto', sans-serif; }  
h1 {   font-family: 'Pacifico', cursive; }
```

---

### **7. Perché non importare font “a mano”**

Puoi farlo, esistono siti che permettono di scaricare file `.woff` o `.woff2`.  
Ma:

- devi creare la struttura delle cartelle,
    
- devi definire tu `@font-face`,
    
- devi esportare tutte le varianti,
    
- devi preoccuparti della compressione,
    
- devi verificare il supporto dei browser.
    

Google Fonts fa tutto questo per te gratuitamente.

---

### **8. Buone pratiche**

- importa **sempre** solo gli stili che ti servono,
    
- usa i fallback suggeriti (`sans-serif`, `serif`, `monospace`, ecc.),
    
- evita pacificamente font strani, difficili da leggere o troppo elaborati,
    
- ricordati che ogni stile pesa e influisce sui tempi di caricamento,
    
- per progetti professionali, usa font consolidati e diffusi.
    

---

### **9. Transizione alla prossima lezione**

Ora che sai importare i font da Google e hai imparato i due metodi principali, possiamo passare a un argomento fondamentale nel design tipografico:

### **→ Come abbinare correttamente i font nel tuo sito.**