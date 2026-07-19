# **M11 Approfondimenti per l'esame - Lezione 2 - Conteggio degli schedule seriali equivalenti**

### **1. Impostazione del problema**

Si considerino $n$ transazioni, ciascuna composta da una sola operazione sul medesimo oggetto $x$. Uno schedule seriale è una permutazione delle $n$ transazioni: in assenza di vincoli ne esistono $n!$.

Per contare gli schedule equivalenti non bisogna enumerarli. Occorre tradurre l'equivalenza in vincoli d'ordine e contare le permutazioni che li rispettano.

---

### **2. View-equivalenza e conflict-equivalenza**

Due schedule sono view-equivalenti se preservano:

1. chi legge il valore iniziale;
2. da quale scrittura legge ogni lettura;
3. quale transazione esegue la scrittura finale.

Due schedule sono conflict-equivalenti se preservano l'ordine relativo di ogni coppia di operazioni in conflitto. Sullo stesso oggetto confliggono:

- lettura e scrittura;
- scrittura e lettura;
- scrittura e scrittura.

Due letture non confliggono.

---

### **3. Caso: tutte letture**

Tutte le transazioni leggono il valore iniziale, non esistono scritture finali e non esiste alcun conflitto.

$$
N_{V}=n!,\qquad N_{C}=n!.
$$

---

### **4. Caso: tutte scritture**

Per la view-equivalenza conta soltanto la scrittura finale. Se nello schedule dato l'ultima scrittura è di $T_f$, questa deve restare ultima; le altre $n-1$ transazioni possono essere permutate liberamente:

$$
N_{V}=(n-1)!.
$$

Per la conflict-equivalenza ogni coppia di scritture confligge. L'ordine relativo di tutte le coppie deve restare invariato, quindi è ammesso un solo ordine:

$$
N_{C}=1.
$$

---

### **5. Caso: una lettura in posizione $k$, tutte le altre scritture**

Sia $T_r$ l'unica transazione che legge.

Per la view-equivalenza occorre preservare:

- la scrittura da cui $T_r$ legge, oppure la lettura del valore iniziale se $k=1$;
- la scrittura finale.

I due vincoli fissano due posizioni logiche, mentre le restanti $n-2$ scritture possono essere permutate:

$$
N_V=(n-2)!.
$$

Il risultato vale anche ai bordi:

- $k=1$: il lettore resta prima di tutte le scritture e lo scrittore finale resta ultimo;
- $k=n$: lo scrittore finale deve precedere immediatamente il lettore, così il lettore osserva proprio il suo valore.

Per la conflict-equivalenza tutte le scritture confliggono fra loro, quindi il loro ordine è completamente fissato; inoltre il lettore confligge con ogni scrittura e deve restare nella stessa posizione relativa. Ne segue:

$$
N_C=1.
$$

---

### **6. Caso: una scrittura in posizione $k$, tutte le altre letture**

Sia $T_w$ l'unico scrittore.

- Le $k-1$ letture che lo precedono devono continuare a leggere il valore iniziale.
- Le $n-k$ letture che lo seguono devono continuare a leggere il valore scritto da $T_w$.

Le letture di ciascun gruppo non confliggono e possono essere permutate internamente:

$$
N_V=(k-1)!(n-k)!.
$$

Gli stessi due blocchi sono imposti dai conflitti lettura-scrittura; all'interno di ciascun blocco le letture restano permutabili. Quindi:

$$
N_C=(k-1)!(n-k)!.
$$

---

### **7. Tabella finale**

|Operazioni sullo stesso oggetto|Schedule view-equivalenti|Schedule conflict-equivalenti|
|---|---:|---:|
|Tutte letture|$n!$|$n!$|
|Tutte scritture|$(n-1)!$|$1$|
|Una lettura in posizione $k$, resto scritture|$(n-2)!$|$1$|
|Una scrittura in posizione $k$, resto letture|$(k-1)!(n-k)!$|$(k-1)!(n-k)!$|

> ⚠️ La formula $(n-2)!$ presuppone $n\geq 2$. Nei casi degeneri con una sola transazione il conteggio è direttamente 1.

---

### **8. Metodo per casi più generali**

Quando ogni transazione contiene più operazioni:

1. costruire le relazioni `legge-da` e individuare le scritture finali;
2. ricavare i vincoli d'ordine per la view-equivalenza;
3. costruire il grafo dei conflitti per la conflict-equivalenza;
4. contare gli ordinamenti topologici del sistema di precedenze;
5. verificare direttamente ciascun candidato rispetto alle letture e alle scritture finali.

> ✅ Il conteggio nasce sempre dagli ordini obbligatori: fattoriali interi per blocchi liberamente permutabili, valore 1 quando tutti gli ordini relativi sono fissati.
