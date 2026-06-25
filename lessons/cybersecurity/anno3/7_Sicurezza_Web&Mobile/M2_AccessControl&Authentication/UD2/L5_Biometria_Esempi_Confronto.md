# L5 — Esempi di Caratteristiche Biometriche e Confronto tra Tecniche di Autenticazione

---

### **1. Impronta Digitale**

L'impronta digitale è il metodo biometrico più comune e affidabile. Fa riferimento alle piccole righe (*creste dermopapillari*) che si formano su mani e piedi ancora prima della nascita.

**Proprietà soddisfatte:**
- **Universalità:** tutti gli individui le possiedono.
- **Stabilità:** le creste rimangono inalterate per tutta la vita (salvo incidenti e cicatrici).
- **Unicità:** il pattern presente sulle dita è unico per ogni individuo.

#### **1.1. Classificazione dei Pattern**

Le impronte digitali sono classificate in tre schemi principali in base alla forma predominante delle creste:

| Schema | Descrizione | Frequenza nella popolazione |
|--------|-------------|---------------------------|
| **Arco** (*arch*) | Schema piatto, raro | ~5% |
| **Spirale** (*whorl*) | Cerchi concentrici | ~35% |
| **Ciclo** (*loop*) | Strutture circolari che escono da un lato | ~60% |
| **Accidentale** | Schema ibrido, fuori categoria | raro |

#### **1.2. Digitalizzazione: Le Minuzie**

Il template digitale dell'impronta non memorizza l'immagine grezza, ma le **minuzie** — punti caratteristici dell'impronta:
- **Biforcazioni:** punti in cui una cresta si divide in due.
- **Terminazioni:** punti in cui una cresta finisce.

Le minuzie sono anch'esse uniche per individuo e sono il dato su cui si basa il confronto in fase di autenticazione.

> 💡 Si è passati dall'inchiostro al lettore ottico: l'utente appoggia il dito per qualche secondo e il sensore acquisisce l'immagine digitale da cui vengono estratte le minuzie.

**Dimensione del template:** circa **250.000 byte**.

**Vantaggi:**
- Non intrusiva e facile da usare.
- Economica — lettori di piccole dimensioni con bassi requisiti computazionali.

**Svantaggi:**
- Ferite o cicatrici possono alterare il campione.
- Alcuni individui hanno poche minuzie → difficoltà nell'enrollment e nel riconoscimento.

---

### **2. Forma della Mano**

Il lettore acquisisce una serie di **misure geometriche** della mano:
- Lunghezza e larghezza delle dita.
- Ampiezza del palmo.

**Dimensione del template:** circa **10 byte** — molto più compatto dell'impronta digitale.

**Vantaggi:**
- Più affidabile delle impronte digitali in alcuni contesti.
- Template estremamente piccolo.

**Svantaggi:**
- Richiede scanner **più grandi e costosi** rispetto ai lettori di impronte.
- Gestione problematica di **ferite frequenti alle mani** o ingessatura: la proprietà di stabilità non è sempre garantita.

---

### **3. Riconoscimento Facciale**

Il template digitale del viso si basa sulle **distanze metriche** tra punti di riferimento:
- Distanza occhi-naso.
- Distanza occhi-bocca.
- Alcune dimensioni generali del viso.

> ⚠️ Richiede un'**espressione neutra** durante l'acquisizione, per garantire la riproducibilità delle misure.

**Vantaggi:**
- Totalmente **non intrusiva**: la rilevazione può avvenire anche a distanza, senza che l'utente debba compiere azioni specifiche.

**Svantaggi:**
- L'acquisizione tramite fotocamera non è sempre accettata dagli utenti.
- I valori di FAR e FRR sono **molto fluttuanti** a seconda delle condizioni di luce, espressione, invecchiamento → difficile calibrare una soglia di tolleranza stabile.
- L'utente non sa sempre quale espressione mantenere durante lo scanning.

---

### **4. Confronto tra le Tre Caratteristiche**

| Proprietà | Impronta digitale | Forma della mano | Riconoscimento facciale |
|-----------|:-----------------:|:----------------:|:-----------------------:|
| **Universalità** | Alta | Alta | Alta |
| **Unicità** | Molto alta | Media | Media |
| **Stabilità** | Alta | Media (ferite) | Media (età, espressione) |
| **Dimensione template** | ~250.000 byte | ~10 byte | Variabile |
| **Performance** | Alta | Media | Media-bassa |
| **Accettazione utente** | Alta | Media | Media |
| **Vulnerabilità ad attacchi** | Media | Bassa | Media-alta |

> 📌 L'**impronta digitale** risulta vincente nel confronto complessivo, con valori medi più alti sulle proprietà rilevanti. È per questo che rimane il metodo biometrico più diffuso in applicazioni pratiche.

---

### **5. Applicazioni Pratiche della Biometria**

Nonostante la minore diffusione rispetto a password e token, la biometria ha già applicazioni concrete in diversi settori:

- **Settore bancario:** autenticazione dei clienti per operazioni ad alto rischio.
- **Immigrazione:** gli USA richiedono impronte digitali ai visitatori stranieri; l'Italia le raccoglie al momento del rilascio del passaporto biometrico.
- **Ambito militare:** già alle Olimpiadi di Atene 2004, gli atleti tedeschi avevano smart card con impronta digitale integrata — esempio di combinazione di due tecniche di autenticazione (token + biometria).
- **Controllo accessi aziendali:** all'aeroporto di Francoforte, dal 2000, gli impiegati usano la scansione dell'**iride** (iris scan) al posto del badge per registrare l'accesso.

---

### **6. Quale Tecnica di Autenticazione È Migliore?**

Dopo aver visto tutte e tre le tecniche (password, token, biometria), possiamo rispondere alla domanda.

**Da un punto di vista tecnico:** la tecnica più robusta è quella biometrica, perché la caratteristica non può essere persa, rubata o indovinata.

**Da un punto di vista pratico:** le **password** rimangono il meccanismo più utilizzato — e probabilmente lo resteranno nel breve futuro — per il classico trade-off costi/benefici: sono economiche, semplici da implementare e sufficienti per molti contesti applicativi.

> 📌 **Soluzione ottimale dal punto di vista tecnico:** la combinazione di:
> 1. **Autenticazione biometrica** tra l'utente e il token (l'utente sblocca il token con la propria biometria).
> 2. **Autenticazione mutua crittografica** tra il token e il sistema.
>
> Questo approccio combina i punti di forza di tutti e tre i fattori — biometria, possesso e crittografia — e mitiga i rispettivi punti deboli.

---

### **7. Riepilogo**

> ✅ Le tre caratteristiche biometriche più diffuse sono **impronta digitale** (migliori performance complessive, template da ~250 KB, basata sulle minuzie), **forma della mano** (template minuscolo ~10 B, ma scanner grandi e vulnerabile a ferite) e **riconoscimento facciale** (non invasivo ma FAR/FRR instabili). Non esiste la caratteristica biometrica perfetta: la scelta è sempre un trade-off tra costi e benefici. La soluzione tecnicamente ottimale combina autenticazione biometrica utente-token con autenticazione mutua crittografica token-sistema.
