# **Lezione 1: Crittografia e Steganografia**

---

### **1. L’evoluzione della crittografia**

Dall’antichità fino a pochi decenni fa, la **crittografia** era utilizzata quasi esclusivamente per **comunicazioni segrete** in ambito:

- **militare**, per trasmettere ordini senza che il nemico li comprendesse,
    
- **diplomatico**, per proteggere documenti sensibili e trattative politiche.
    

Oggi, invece, la crittografia ha assunto un significato più ampio:  
è diventata lo **studio di tecniche e applicazioni** per la **protezione dell’informazione**, basate sull’esistenza di **problemi matematici difficili da risolvere** (ad esempio la fattorizzazione di numeri grandi o il logaritmo discreto).

---

### **2. Comunicazioni segrete: due approcci**

Nel corso della storia, le **comunicazioni segrete** si sono realizzate attraverso due discipline principali:

1. **Crittografia** → nasconde il **contenuto** del messaggio.
    
2. **Steganografia** → nasconde **l’esistenza stessa** del messaggio.
    

In altre parole:

- la **crittografia** rende il messaggio incomprensibile a chi non possiede la chiave,
    
- la **steganografia** fa sì che **nessuno si accorga nemmeno che esista un messaggio nascosto**.
    

---

### **3. Etimologia di “Steganografia”**

La parola **steganografia** deriva dal greco:  
**stegano** (στεγανό) = coperto  
**graphía** (γραφία) = scrittura

Significa letteralmente **“scrittura coperta”**.  
L’obiettivo è **occultare il messaggio** all’interno di un contenitore apparentemente innocuo (testo, immagine, oggetto fisico, ecc.).

---

### **4. Episodi storici di steganografia**

#### **Erodoto – La tavoletta di cera**

Nel V secolo a.C., **Erodoto** racconta che **Demerato**, in esilio, avvisò gli Spartani di un imminente attacco di **Serse**, re dei Persiani.  
Per far arrivare l’avvertimento, scrisse il messaggio su una **tavoletta di legno** e lo **coprì con uno strato di cera**, così che apparisse vuota.  
Fu **Gorgo**, moglie di Leonida, a intuire il trucco e scoprire il messaggio nascosto.

#### **Erodoto – Il messaggio tatuato**

Sempre secondo Erodoto, **Istieo** inviò un ordine segreto a **Aristagora di Mileto** tatuando un messaggio sulla **testa rasata di uno schiavo**.  
Quando i capelli ricrebbero, lo schiavo fu mandato come corriere; una volta arrivato, gli venne rasata di nuovo la testa per leggere il messaggio.

---

### **5. Tecniche di steganografia antica e moderna**

#### **Tecniche antiche**

- **Disposizione delle lettere** in un messaggio apparentemente innocente (es. ogni terza parola forma un messaggio segreto).
    
- **Contrassegno dei caratteri** tramite segni invisibili o minimi, come un **ripasso a matita** o **piccoli fori** sulle lettere.
    
- **Inchiostro invisibile**, che compare solo dopo essere stato riscaldato o trattato con sostanze chimiche.
    

#### **Tecniche moderne**

- **Steganografia digitale**: nasconde informazioni **nei bit** di un file multimediale (immagine, audio, video).  
    Un esempio è il **watermarking**, cioè l’inserimento invisibile di una **firma digitale** all’interno di un contenuto per proteggerne la proprietà.
    

---

### **6. Esempio di messaggio steganografico**

Ecco un brano tratto dal romanzo _The Silent World of Nicholas Quinn_ di **Colin Dexter**, riportato **nella stessa impaginazione della slide originale**.  
👉 Il messaggio segreto si ottiene **leggendo l’ultima parola di ogni riga**.

```
Dear George,
Greetings to all at Oxford. Many thanks for your
letter and for the Summer examination package.
All Entry Forms and Fees Forms should be ready
for final despatch to the Syndicate by Friday
20th or at the very latest, I'm told, by the 21st.
Admin has improved here, though there's room
for improvement still; just give us all two or three
more years and we'll really show you! Please
don't let these wretched 16+ proposals destroy
your basic O and A pattern. Certainly this 
sort of change, if implemented immediately, 
would bring chaos.
Sincerely yours,
```

#### **Messaggio nascosto (ultima parola di ogni riga):**

> **"your package ready Friday 21st, room three. Please destroy this immediately"**

Si tratta di un messaggio operativo: _il pacco è pronto per venerdì 21, stanza tre; distruggere subito la lettera dopo la lettura._  
È un esempio perfetto di **steganografia linguistica**, in cui il segreto è nascosto **nella struttura**, non nel significato apparente.

---

### **7. Valutazione della steganografia**

#### **Vantaggi**

- Le parti comunicanti possono **nascondere l’esistenza stessa** dello scambio.  
    Nessuno sospetterebbe che dietro un testo o un’immagine comune si nasconda un messaggio segreto.
    

#### **Svantaggi**

- Se il mezzo di trasmissione viene **analizzato attentamente**, il messaggio nascosto può essere scoperto.
    

Esempi di analisi che rivelano un messaggio nascosto:

- raschiare una tavoletta di cera,
    
- radere il capo a un corriere,
    
- osservare una lettera **in controluce**,
    
- analizzare i bit o la compressione anomala di un file digitale.
    

👉 **La steganografia fallisce completamente quando il suo meccanismo è scoperto.**  
Una volta noto il metodo di occultamento, la segretezza è perduta.

---

### **8. Il Cifrario di Polibio**

Uno dei primi esempi di **cifratura sistematica** è il **Cifrario di Polibio**, basato su una matrice numerica 5×5.

|       | 1   | 2   | 3   | 4   | 5   |
| ----- | --- | --- | --- | --- | --- |
| **1** | A   | B   | C   | D   | E   |
| **2** | F   | G   | H   | I/J | K   |
| **3** | L   | M   | N   | O   | P   |
| **4** | Q   | R   | S   | T   | U   |
| **5** | V   | W   | X   | Y   | Z   |


> Esempio:  
> Testo in chiaro: **CASA**  
> Testo cifrato: **(1,3)** **(1,1)** **(4,3)** **(1,1)**

Questo metodo introduce il concetto di **sostituzione sistematica** e di **rappresentazione numerica** del testo.

---

### **9. Il Cifrario di Cesare**

Il più celebre cifrario monoalfabetico è il **Cifrario di Cesare**, utilizzato da **Gaio Giulio Cesare** nel I secolo a.C. per comunicazioni militari.

Ogni lettera viene sostituita con quella posta **tre posizioni più avanti** nell’alfabeto.

| **Chiaro**  | A   | B   | C   | D   | E   | F   | G   | H   | I   | J   | K   | L   | M   | N   | O   | P   | Q   | R   | S   | T   | U   | V   | W   | X   | Y   | Z   |
| ----------- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| **Cifrato** | D   | E   | F   | G   | H   | I   | J   | K   | L   | M   | N   | O   | P   | Q   | R   | S   | T   | U   | V   | W   | X   | Y   | Z   | A   | B   | C   |

> Esempio pratico:  
> `OMNIA GALLIA EST DIVISA IN PARTES TRES`  
> → `RPQLD JDOOLD HVW GLYLVD LQ SDUWHV WUHV`

Questa sostituzione fissa è detta **rotazione** (o _shift_) dell’alfabeto di 3 posizioni.

---

### **10. Crittografia vs Steganografia: differenze**

|**Aspetto**|**Crittografia**|**Steganografia**|
|---|---|---|
|**Obiettivo**|Rendere il messaggio incomprensibile|Nascondere l’esistenza del messaggio|
|**Principio base**|Trasformazione matematica (cifratura)|Occultamento nel mezzo|
|**Esempio**|Cifrario di Cesare|Tavoletta di cera, lettera di Dexter|
|**Vulnerabilità**|Decifrazione o furto della chiave|Scoperta del meccanismo di occultamento|

---

### **11. In sintesi**

Fin dall’antichità, l’uomo ha cercato di **proteggere le proprie comunicazioni**:

- con la **steganografia**, nascondendo il messaggio,
    
- con la **crittografia**, rendendolo indecifrabile.
    

Le tecniche moderne di sicurezza informatica discendono da queste due idee complementari, oggi fondate su **modelli matematici rigorosi** e **complessità computazionale**, piuttosto che su semplici espedienti materiali.