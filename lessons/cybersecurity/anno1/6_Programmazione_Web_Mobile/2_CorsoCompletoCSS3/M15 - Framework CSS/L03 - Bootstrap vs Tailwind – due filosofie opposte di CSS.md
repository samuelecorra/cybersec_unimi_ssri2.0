## **Lezione 3: Bootstrap vs Tailwind – due filosofie opposte di CSS**

---

## **1. Perché Bootstrap e Tailwind sono “due mondi”**

Negli ultimi anni il mondo dei framework CSS estetici si è diviso in due grandi “famiglie”:

- **Famiglia Bootstrap** → include anche _Pico, Bulma, Foundation, ecc._
    
    - Tutti ragionano **per componenti**.
        
- **Famiglia Tailwind** → al momento praticamente **da solo** nella sua categoria.
    
    - Ragiona **per utility class**, cioè per singole regole CSS.
        

La differenza non è banale:

> Non cambia “solo” la sintassi:  
> cambia **il modo mentale** con cui pensi al CSS.

---

## **2. La filosofia di Bootstrap (e simili): componenti ad alta utilità**

### **2.1 Componenti = blocchi di interfaccia**

Bootstrap (come Pico, Bulma, Foundation, ecc.) è costruito attorno al concetto di **componente**.

Esempio: l’**accordion** di Bootstrap.

Nella documentazione vedi un codice simile (semplificato):

```html
<div class="accordion">
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button">
        Titolo
      </button>
    </h2>
    <div class="accordion-collapse">
      <div class="accordion-body">
        Contenuto...
      </div>
    </div>
  </div>
</div>
```

Qui succedono diverse cose:

- `accordion` → è **il blocco principale** (il componente).
    
- `accordion-item` → è **ogni singolo elemento** dell’accordion.
    
- `accordion-header` → è **la testata** dell’item (parte cliccabile).
    
- `accordion-collapse` → è **la parte che collassa** (che si apre/chiude).
    
- `accordion-body` → è **il contenuto interno**.
    

Tutte queste classi sono:

- **pensate per stare insieme**,
    
- fanno parte dello _stesso componente_,
    
- non hanno senso se le mescoli con altri componenti (es. usarle su `list-group`, ecc.).
    

### **2.2 È esattamente come abbiamo già fatto noi a mano**

Questo modo di ragionare non è diverso da come hai costruito le tue **card** a mano:

- `card` → blocco principale,
    
- `card-image`, `card-header`, `card-body`, `card-footer`,
    
- magari `card--disabled`, `card--notice`, ecc.
    

Bootstrap non fa altro che:

> Fornirti una **libreria di componenti già pronti**, con una struttura mentale identica a quella che useresti tu:

- “Ho una card → ha header, body, footer, immagine, pulsante…”
    
- “Ho un accordion → ha header + contenuto collassabile…”
    

### **2.3 High utility: una classe = tante regole CSS**

In questo mondo si parla spesso di **high utility**:

- una classe come `.accordion` o `.alert` **porta con sé un pacchetto enorme di stile**:
    
    - border,
        
    - padding,
        
    - background,
        
    - colori,
        
    - radius,
        
    - transizioni,
        
    - tipografia…
        

Se apri il CSS di Bootstrap e cerchi `.accordion`, troverai decine di regole associate.

Quindi:

> **Una classe = un componente ricco**, con tante regole CSS applicate insieme.

È comodo perché:

- scrivi poche classi,
    
- ottieni subito un componente “pieno” e bello.
    

---

## **3. La filosofia di Tailwind: utility class a bassa granularità**

### **3.1 Una card in Tailwind “non sa” di essere una card**

Ora entriamo in Tailwind.

Immagina una card tipo “profilo”:

- un’immagine (avatar),
    
- nome,
    
- ruolo,
    
- bio.
    

Il codice Tailwind per l’immagine potrebbe essere qualcosa del genere:

```html
<img
  class="w-24 h-24 md:w-48 md:h-48 rounded-full mx-auto"
  src="..."
  alt="Profile"
/>
```

Se guardi solo le classi:

- `w-24`, `h-24`, `md:w-48`, `md:h-48`
    
- `rounded-full`
    
- `mx-auto`
    

Ti accorgi che **non c’è nessuna classe tipo** `profile-picture` o `avatar`:

- nessuna classe semantica che dica “questa è la foto profilo”,
    
- nessuna classe che dica “questa è una card”.
    

Lo stesso per il testo:

```html
<p class="text-lg font-medium">
  ...
</p>
```

Non c’è `bio`, `author-description`, ecc.  
Solo regole generiche.

### **3.2 Low utility: una classe = una regola CSS**

E qui sta la rivoluzione:

In Tailwind, **ogni classe corrisponde praticamente a una singola regola CSS**.

Esempi:

- `bg-slate-100` → `background-color: #...` (colore “slate”, shade 100)
    
- `rounded-xl` → `border-radius: ...` (raggio grande)
    
- `p-8` → `padding: 2rem` (per dire: scala fissa)
    
- `w-24` → `width: ...`
    
- `text-sky-500` → `color: ...`
    
- `dark:text-sky-400` → `color: ...` ma solo in dark mode
    
- `md:flex` → `display: flex` quando lo schermo è almeno “md”
    

Ogni classe:

- non descrive un **componente**,
    
- ma descrive una **regola CSS secca** (proprietà + valore).
    

L’interfaccia la “costruisci” combinando decine di queste micro–classi.

### **3.3 Prefix e responsive**

Tailwind usa prefissi e pattern molto chiari:

- `md:` → applichi la regola da “medium screen” in su.  
    Esempio: `md:flex`, `md:w-48`…
    
- `dark:` → applichi la regola quando il tema è dark.  
    Esempio: `dark:text-sky-400`.
    

Concettualmente:

> Tailwind ti dice: “Io ti do **tutti i mattoncini CSS possibili** sotto forma di classi.  
> Tu, nel tuo HTML, li combini e costruisci i componenti come vuoi, senza quasi aprire un file `.css`.”

---

## **4. Bootstrap vs Tailwind: approcci a confronto**

### **4.1 Bootstrap (e simili) – Component-based, high utility**

- Ragiona per **componenti**:
    
    - `.accordion`, `.accordion-item`, `.accordion-header`, `.accordion-body`…
        
    - `.card`, `.card-header`, `.card-body`, `.card-footer`…
        
    - `.alert`, `.alert-success`, `.alert-warning`…
        
- Ogni classe è collegata a uno specifico **pezzo di componente**.
    
- Una classe porta con sé più regole CSS (alta “utilità” per singola classe).
    

Vantaggi:

- apprendimento naturale se hai già studiato CSS classico;
    
- ottimo per principianti e per andare velocissimi con componenti standard;
    
- markup più leggibile semanticamente (vedi “accordion”, “card”, “navbar”, ecc.).
    

---

### **4.2 Tailwind – Utility-first, low utility**

- Non ragiona per componenti predefiniti (o comunque non è il focus).
    
- Ragiona per **utility class**, cioè per **regole CSS atomiche**:
    
    - `flex`, `grid`, `items-center`, `justify-between`,
        
    - `bg-...`, `text-...`, `border-...`,
        
    - `p-4`, `m-2`, `gap-6`,
        
    - `md:flex`, `lg:w-1/3`, `dark:text-sky-400`, ecc.
        
- Il “componente” lo costruisci tu combinando utility nel markup.
    

Vantaggi:

- enorme controllo fine sul design,
    
- CSS effettivo **molto piccolo** in produzione (grazie al “purge” delle classi inutilizzate),
    
- niente più file CSS pieni di classi custom mezze duplicate:
    
    - quasi tutto è nel markup,
        
    - il CSS “a mano” si riduce tantissimo.
        

---

## **5. Quale approccio è più adatto all’inizio?**

L’autore del corso dice chiaramente (e ha molto senso):

- **Tailwind** è stato rivoluzionario, è in fortissima crescita, e lui lo adora → ma lo ha imparato **dopo 10–11 anni di esperienza**.
    
- Per un principiante assoluto, Tailwind può risultare **non immediato**:
    
    - vedi solo classi “strane” (`bg-slate-100`, `md:w-24`, `rounded-xl`…),
        
    - non hai nomi tipo `card`, `navbar`, `profile-picture` che ti guidano.
        

Per questo consiglia:

> **Parti con Bootstrap (o framework simili)**,  
> poi, quando hai solide basi di CSS e componenti, passa a Tailwind se ti ispira.

Non sei **obbligato** a usare entrambi.  
L’importante è capire:

- che sono **due filosofie diverse**,
    
- che il browser alla fine riceve sempre **HTML + CSS**,
    
- che il framework serve **a te**, non alla macchina.
    

---

## **6. Ricapitolazione in una frase**

- **Bootstrap & co.**  
    → _Una classe = un componente (o un pezzo di componente) con tante regole CSS sotto._
    
- **Tailwind**  
    → _Una classe = una singola regola CSS con un valore specifico. I componenti li costruisci combinandole._
    

Questa è la distinzione fondamentale.

---

## **7. Prossimi step**

Nei prossimi due approfondimenti pratici:

1. **Bootstrap in pratica**  
    – come si usa, come sono strutturati i componenti, come si costruisce una pagina vera.
    
2. **Tailwind in pratica**  
    – come si leggono le utility class, come si costruisce una card, come funziona la parte responsive/dark mode.
    

Quando vuoi, passiamo alla parte operativa.