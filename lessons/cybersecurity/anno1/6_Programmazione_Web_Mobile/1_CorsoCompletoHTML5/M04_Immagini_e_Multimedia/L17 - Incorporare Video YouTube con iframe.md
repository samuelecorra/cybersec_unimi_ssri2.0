## **Lezione: Incorporare Video YouTube con `<iframe>`**

### **1. Introduzione**

Dopo aver visto come funzionano gli iframe, è ora di applicare tutto a uno degli usi più comuni in assoluto: **incorporare video di YouTube**.

Inserire video da YouTube nel proprio sito è utilissimo perché:

- evita di caricare file video pesanti sul server
    
- garantisce streaming ottimizzato
    
- permette all’utente di usare i controlli di YouTube
    
- è compatibile con qualsiasi dispositivo
    
- richiede soltanto un iframe con un URL speciale
    

Esistono però **alcune opzioni avanzate** che YouTube permette di configurare, ed è fondamentale conoscerle.

---

### **2. Prendere il codice di incorporamento da una playlist**

Se apri un video che fa parte di una playlist:

1. Clicchi su **Condividi**
    
2. Selezioni **Incorpora**
    
3. YouTube genera automaticamente un iframe per **l’intera playlist**
    

Esempio generato:

```html
<iframe
    width="560"
    height="315"
    src="https://www.youtube.com/embed/ID_VIDEO?list=ID_PLAYLIST"
    title="YouTube video player"
    frameborder="0"
    allow="accelerometer; autoplay; encrypted-media; picture-in-picture; gyroscope"
    allowfullscreen>
</iframe>
```

Questo incorpora **tutta la playlist**, non solo il video.  
Se fai partire il primo video, alla fine partirà automaticamente il secondo, poi il terzo, e così via.

Per alcuni blog o siti di corsi è utile mostrare la playlist intera; per altri è meglio un singolo video.

---

### **3. Incorporare un singolo video**

Per incorporare soltanto _quel_ video, apri la pagina del video separatamente.

1. Entra nel singolo video (non dalla playlist).
    
2. Clicca su **Condividi** → **Incorpora**.
    
3. Copia l’iframe generato.
    

Esempio:

```html
<iframe
    width="560"
    height="315"
    src="https://www.youtube.com/embed/ID_VIDEO"
    title="YouTube video player"
    frameborder="0"
    allow="accelerometer; autoplay; encrypted-media; picture-in-picture; gyroscope"
    allowfullscreen>
</iframe>
```

Questa volta, il video terminerà senza passare a quello successivo.

---

### **4. Iniziare la riproduzione da un punto preciso**

YouTube permette di incorporare un video partendo da un minuto o secondo specifico.

Ci sono **due modi**:

#### **1) Usare il pannello “Incorpora”**

- Vai al minuto desiderato del video
    
- Apri _Condividi → Incorpora_
    
- Noterai che YouTube aggiunge automaticamente il parametro `start=`  
    (in secondi, non in minuti)
    

Esempio:

```
https://www.youtube.com/embed/ID_VIDEO?start=45
```

#### **2) Aggiungerlo manualmente**

Puoi scriverlo tu direttamente nell’URL:

```
https://www.youtube.com/embed/ID_VIDEO?start=120
```

Partirà da 2 minuti esatti.

---

### **5. Attivare l’autoplay**

L’autoplay non è incluso di default, perciò il video non partirà da solo.  
Per farlo partire automaticamente, aggiungi:

```
&autoplay=1
```

Esempio completo:

```html
<iframe
    width="560"
    height="315"
    src="https://www.youtube.com/embed/ID_VIDEO?start=45&autoplay=1"
    title="YouTube video player"
    frameborder="0"
    allow="accelerometer; autoplay; encrypted-media; picture-in-picture; gyroscope"
    allowfullscreen>
</iframe>
```

Il video:

- parte da 45 secondi
    
- parte automaticamente
    
- rimane comunque con i controlli di YouTube
    

---

### **6. Copiare l’incorporamento dal tasto destro**

Sui video di YouTube c’è anche la voce:

**"Copia codice per l’incorporamento"**

Ma attenzione:  
se il video fa parte di una playlist, _quel_ comando copierà comunque l’iframe con la playlist inclusa.

Esempio:

- Tasto destro → copia
    
- Ti ritrovi: `list=PLxxxxxxxxxxx`
    
- Significa che stai incorporando la playlist
    

Quindi:

- **Se vuoi la playlist → usa pure questo metodo**
    
- **Se vuoi solo il video → usa Condividi → Incorpora dal singolo video**
    

---

### **7. Impostazioni aggiuntive utili**

Il codice generato da YouTube include:

- `allowfullscreen` → permette la modalità schermo intero
    
- `allow="accelerometer; autoplay; ..."` → abilita funzioni del player
    
- `frameborder="0"` → elimina il bordo (storico ma ancora usato)
    
- `loading="lazy"` (a volte presente) → migliora le performance
    

Puoi tranquillamente lasciare tutto: YouTube li imposta correttamente.

---

### **8. Distinguere playlist e video singolo**

Ricapitoliamo per non sbagliare:

#### **Se copi l’iframe da una playlist**

Otterrai:

```
src="https://www.youtube.com/embed/ID_VIDEO?list=ID_PLAYLIST"
```

→ È un iframe che contiene tutta la playlist.

#### **Se copi da un singolo video**

Otterrai:

```
src="https://www.youtube.com/embed/ID_VIDEO"
```

→ Solo il video, senza playlist.

Questa è la differenza più importante quando si incorporano video su un sito professionale.

---

### **9. Esempio finale completo**

```html
<iframe
    width="560"
    height="315"
    src="https://www.youtube.com/embed/ID_VIDEO?start=75&autoplay=1"
    title="Video YouTube incorporato"
    frameborder="0"
    allow="accelerometer; autoplay; encrypted-media; picture-in-picture; gyroscope"
    allowfullscreen>
</iframe>
```

Questo iframe:

- incorpora un singolo video YouTube
    
- parte dal minuto 1:15
    
- si avvia automaticamente
    
- può andare a schermo intero
    
- supporta funzioni modernissime come picture-in-picture
    

---

### **10. Conclusione**

Ora sai:

- come incorporare playlist e video singoli
    
- come avviare da un punto preciso
    
- come attivare l’autoplay
    
- come evitare errori quando copi l’embed da contesti diversi
    
- quali attributi YouTube aggiunge e perché
    
- quali parametri puoi modificare manualmente nell’URL dell’iframe
    

YouTube è uno dei contenuti più incorporati del web e padroneggiarlo è indispensabile.