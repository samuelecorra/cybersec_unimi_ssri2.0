Il primo modulo introduce le **basi fisiche e logiche della comunicazione digitale**, ossia il modo in cui l’informazione viene trasformata in segnali elettrici o ottici e trasmessa lungo un canale.  
Si parte dai **principi fondamentali della trasmissione digitale** — campionamento, codifica e modulazione — per arrivare ai concetti di **banda, rumore, velocità di trasmissione e sincronizzazione**.

Il modulo analizza inoltre **le principali tecnologie di comunicazione locale**, come Ethernet e Wi-Fi, e **la rete telefonica digitale**, mostrando come il passaggio dall’analogico al digitale abbia reso possibili le moderne reti dati.  
L’obiettivo è fornire una visione chiara di **come un segnale informatico diventi comunicazione reale**, e di quali scelte ingegneristiche ne determinino l’affidabilità e l’efficienza.


---

# **Lezione 1: Segnali**


![](dispensa_segnali.pdf)

### **1. Cosa si intende per “segnale”**

Nel linguaggio comune, un segnale può essere qualsiasi cosa comunichi un’informazione: il fumo per indicare l’elezione del Papa, una stretta di mano, un cenno del capo.  
In ambito tecnico, però, **un segnale è un evento che varia nel tempo** e che può essere usato per **trasmettere informazione** tra due o più sistemi.

Un segnale può essere:

- **elettrico**, come la tensione che varia in un circuito;
    
- **elettromagnetico**, come le onde radio;
    
- **luminoso**, come un fascio laser che trasporta dati in fibra ottica;
    
- **acustico**, come le onde sonore.
    

In generale, ogni grandezza fisica che cambia nel tempo e può rappresentare un’informazione è un **segnale**.

---

### **2. Le forme d’onda**

Poiché un segnale cambia nel tempo, la rappresentazione più naturale è un **grafico** che mostri come varia la sua **ampiezza** (cioè il valore istantaneo della grandezza fisica) rispetto al tempo.  
Nel piano cartesiano:

- sull’asse delle **x** si rappresenta il **tempo** $t$;
    
- sull’asse delle **y** si rappresenta l’**ampiezza istantanea** del segnale.
    

La forma di questo grafico è detta **forma d’onda**.

Gli strumenti che permettono di visualizzare la forma d’onda di un segnale sono gli **oscilloscopi**, fondamentali in elettronica e telecomunicazioni.

---

### **3. Segnali periodici**

Un segnale si dice **periodico** se la sua forma d’onda si ripete identica nel tempo.  
L’intervallo di tempo minimo dopo il quale il segnale si ripete è detto **periodo** $T$.  
La **frequenza fondamentale** $f$ è il numero di cicli che si ripetono ogni secondo, ed è misurata in **hertz (Hz)**.

$$  
f = \frac{1}{T} \quad \text{e} \quad T = \frac{1}{f}  
$$

![](imgs/Pasted%20image%2020251128061633.png)

Esempi comuni di forme d’onda periodiche sono:

- **onda quadra**, composta da alternanze nette tra due valori;
    
- **onda triangolare**, in cui il segnale cresce e decresce linearmente;
    
- **onda a dente di sega**, che cresce lentamente e poi cala bruscamente (tipica nei segnali video analogici).

![](imgs/Pasted%20image%2020251128061216.png)

---

### **4. Lunghezza d’onda**

Nel caso di segnali **elettromagnetici**, come le onde radio, è utile parlare di **lunghezza d’onda** $λ$ (lambda), cioè la distanza tra due creste consecutive dell’onda.

![](imgs/Pasted%20image%2020251128061408.png)

Essa è legata alla frequenza dalla relazione:

$$  
λ = \frac{v}{f}  
$$

dove:

- $λ$ è la lunghezza d’onda (in metri),
    
- $v$ è la velocità di propagazione (per le onde radio, circa $3 \times 10^8$ m/s),
    
- $f$ è la frequenza (in hertz).
    

Ad esempio, un’onda radio di **900 MHz** ha:

$$  
λ = \frac{3 \times 10^8}{9 \times 10^8} = 0.33\ \text{m}  
$$

---

### **5. Notazione tecnica**

Nel mondo scientifico si usano **prefissi standard** per esprimere numeri molto grandi o molto piccoli.  
Ad esempio:

- $1.260.000\ \text{Hz} = 1.26\ \text{MHz}$,
    
- $0.005\ \text{s} = 5\ \text{ms}$.
    

Questa notazione deriva dalle **potenze di 10**:  
$$  
1.26 \times 10^6\ \text{Hz} = 1.26\ \text{MHz}  
$$

---

### **6. Onde sinusoidali**

Un’**onda sinusoidale** è il tipo più semplice di segnale periodico.  

![](imgs/Pasted%20image%2020251128061748.png)

È descritta da una funzione del tipo:

$$  
s(t) = A \sin(2πft + φ)  
$$

dove:

- $A$ = ampiezza massima (valore di picco),
    
- $f$ = frequenza,
    
- $φ$ (phi) = fase (spostamento orizzontale della curva).
    

Le onde sinusoidali sono **la base di tutti i segnali complessi**, poiché qualsiasi forma d’onda può essere costruita come somma di sinusoidi a frequenze diverse.

---

### **7. Sintesi e analisi di Fourier**

All’inizio dell’Ottocento, **Joseph Fourier** dimostrò che **ogni segnale periodico può essere rappresentato come somma di onde sinusoidali** (le cosiddette **armoniche**).

Ad esempio, un’onda quadra può essere ottenuta sommando un numero infinito di sinusoidi con frequenze dispari:

![](imgs/Pasted%20image%2020251128062016.png)

che, matematicamente, si traduce in:

$$  
s(t) = \sin(ωt) + \frac{1}{3}\sin(3ωt) + \frac{1}{5}\sin(5ωt) + \dots  
$$

Ogni termine contribuisce ad “arrotondare” o “squadrare” la forma del segnale.  
Questo principio è la **base dell’analisi spettrale** usata in tutte le telecomunicazioni.

---

### **8. Lo spettro di un segnale**

Un segnale può essere rappresentato in due modi:

- **nel dominio del tempo**: ampiezza in funzione del tempo $s(t)$;
    
- **nel dominio della frequenza**: ampiezza in funzione della frequenza $A(f)$.
    

Lo **spettro** di un segnale mostra **quali frequenze lo compongono** e **quanto è intensa ciascuna**.  
Un segnale sinusoidale puro ha uno spettro con una sola linea; un segnale complesso ha molte componenti.

![](imgs/Pasted%20image%2020251128062245.png)

---

### **9. Larghezza di banda**

La **larghezza di banda (bandwidth)** indica **l’intervallo di frequenze** occupato da un segnale o supportato da un canale di trasmissione.

![](imgs/Pasted%20image%2020251128062520.png)

Esempi tipici:

|Segnale o Canale|Larghezza di banda|
|---|---|
|Telefonata|4 kHz|
|Radio AM|10 kHz|
|Radio FM|200 kHz|
|Canale TV|6 MHz|

Un canale di 28 kHz può trasportare al massimo **7 telefonate** da 4 kHz ciascuna.  
La banda è quindi una misura diretta della **capacità del canale** di trasportare informazione.

---

### **10. Filtri**

Spesso è utile limitare la banda di un segnale con dei **filtri**:

- **LPF (Low-Pass Filter):** lascia passare solo le basse frequenze;
    
- **HPF (High-Pass Filter):** lascia passare solo le alte frequenze;
    
- **BPF (Band-Pass Filter):** lascia passare solo una certa banda;
    
- **BSF (Band-Stop Filter):** elimina una certa banda.
    

![](imgs/Pasted%20image%2020251128062604.png)

I filtri sono fondamentali per selezionare, isolare o proteggere componenti specifiche di un segnale.

---

### **11. Spettrogrammi**

Uno **spettrogramma** mostra come lo spettro di un segnale cambia nel tempo.  
È una rappresentazione tridimensionale (tempo, frequenza e ampiezza) usata per analizzare suoni e comunicazioni.

![](imgs/Pasted%20image%2020251128062805.png)

Durante la Seconda Guerra Mondiale, lo spettrografo sonoro fu usato per **identificare voci e suoni sottomarini**.  
Oggi è usato in molti ambiti, dalle telecomunicazioni alla biologia acustica (analisi del canto delle balene o del linguaggio umano).

---

### **12. Riassunto concettuale**

- Un **segnale** è una grandezza fisica che varia nel tempo e trasporta informazione.
    
- La **forma d’onda** descrive come cambia nel tempo.
    
- Le **onde sinusoidali** sono i “mattoni” di tutte le altre forme d’onda.
    
- La **trasformata di Fourier** permette di analizzare i segnali in frequenza.
    
- La **larghezza di banda** definisce quante informazioni possono essere trasmesse.
    
- I **filtri** e gli **spettrogrammi** sono strumenti chiave per manipolare e visualizzare i segnali.