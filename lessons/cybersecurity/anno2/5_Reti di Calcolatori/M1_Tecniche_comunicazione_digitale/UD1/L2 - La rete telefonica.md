# **Lezione 2: La rete telefonica**

### **1. Comunicazione analogica**

Prima della rivoluzione digitale, la comunicazione si basava su **segnali analogici**, cioè grandezze fisiche che **variano in modo continuo nel tempo**.

Un esempio classico è la voce: quando parliamo, produciamo **variazioni di pressione** dell’aria, rappresentabili come una funzione del tempo $p(t)$.  
Per trasmettere la voce a distanza, questa grandezza deve essere **trasdotta** (cioè trasformata) in un’altra grandezza più adatta alla trasmissione, come una **tensione elettrica** $v(t)$.

L’idea nasce con **Antonio Meucci**, che utilizzò l’effetto **piezoelettrico dei granuli di carbone** per trasformare le vibrazioni della voce in variazioni di corrente elettrica.  
Questa è la base della **telefonia analogica**.

![](imgs/Pasted%20image%2020260212112721.png)

![](imgs/Pasted%20image%2020251128062907.png)

---

### **2. Modulazione**

Una volta ottenuto un segnale elettrico $v(t)$, esso può:

- essere trasmesso direttamente (a bassa frequenza), oppure
- essere usato per **modulare** un’**onda portante** (carrier), cioè un segnale ad alta frequenza che serve da “mezzo di trasporto”.

![](imgs/Pasted%20image%2020260212112808.png)

![](imgs/Pasted%20image%2020251128063023.png)

La **modulazione** consiste nel **variare una proprietà della portante** (ampiezza, frequenza o fase) in base al segnale informativo.  
È il principio che permette di trasmettere più segnali contemporaneamente su frequenze diverse (come nelle radio AM/FM).

![](imgs/Pasted%20image%2020251128063037.png)

---

### **3. Il problema della telefonia analogica**

Dopo l’invenzione del telefono, sorse subito un problema di **scalabilità**:  
come collegare efficacemente milioni di utenti?

Ci sono due possibilità teoriche:

1. **Collegare direttamente ogni utente a tutti gli altri:**  
   ciò richiederebbe $n(n-1)$ connessioni per $n$ utenti — impossibile da realizzare.

   $$
   N_\text{connessioni} = n(n-1)
   $$

2. **Usare una linea condivisa:**  
   ma in questo caso, **come distinguere a chi è destinata una chiamata?**

La soluzione storica fu la **commutazione di circuito (circuit switching)**:

![](imgs/Pasted%20image%2020251128063159.png)

per ogni chiamata si crea **temporaneamente un percorso fisico dedicato** tra mittente e destinatario, che rimane riservato finché la conversazione è attiva.

---

### **4. Dalla telefonia analogica alla digitale**

Con l’avvento dell’elettronica, si comprese che i segnali analogici potevano essere convertiti in **sequenze di numeri binari**, ottenendo vantaggi enormi in qualità e affidabilità.

Il principio della **telefonia digitale** è semplice:

- la forma d’onda analogica della voce viene **campionata** a intervalli regolari (8.000 volte al secondo, cioè 8 kHz);
- ogni campione viene **quantizzato** e rappresentato con **8 bit**;
- si ottiene quindi un flusso binario continuo di dati.

Il **bit rate** di un canale telefonico digitale è:

$$
8\ \text{bit/campione} \times 8000\ \text{campioni/s} = 64\ \text{kbit/s}
$$

![](imgs/Pasted%20image%2020260212113146.png)

Questo valore (64 kbps) rappresenta la **capacità standard di un canale PCM** (Pulse Code Modulation), usato in tutto il mondo nelle reti telefoniche digitali.

---

### **5. Struttura della rete PSTN**

La **rete telefonica pubblica commutata**, o **PSTN (Public Switched Telephone Network)**, è l’infrastruttura globale che collega miliardi di apparecchi telefonici.  
È composta da diverse sezioni:

![](imgs/Pasted%20image%2020251128063418.png)

- **Apparati terminali**: i telefoni stessi, solitamente analogici;
- **Local loop**: il collegamento tra l’abitazione e la centrale telefonica (ancora analogico in molti casi);
- **Trunk**: collegamenti tra centrali (digitali, ad alta capacità);
- **Switch di commutazione di circuito**, che gestiscono l’instradamento delle chiamate;
- **Rete di controllo**, che supervisiona e gestisce la segnalazione e la disponibilità delle linee.

Tutti questi elementi lavorano insieme per **creare dinamicamente un percorso dedicato** ogni volta che viene effettuata una chiamata.

![](imgs/Pasted%20image%2020260212113618.png)

---

### **6. La gerarchia della commutazione di circuito**

Per evitare il caos di collegamenti diretti tra milioni di utenti, la rete telefonica è organizzata **gerarchicamente**:

1. Ogni area locale ha un **Local Switch**, che collega gli utenti della zona.
2. Più Local Switch fanno capo a un **Central Office (CO)**.
3. I CO sono a loro volta collegati in una rete di livello superiore, che permette di raggiungere qualsiasi utente in qualunque parte del mondo.

Questo sistema di **livelli di concentrazione** riduce drasticamente i costi e rende possibile una **scalabilità globale**.  
Durante una telefonata a lunga distanza, il segnale attraversa quindi **più switch e CO** prima di raggiungere il destinatario.

---

### **7. Come funziona una chiamata**

L’interfaccia utente è il **telefono**: i tasti che premiamo inviano codici al sistema di commutazione.  
Ogni cifra composta genera una combinazione di due toni — sistema **DTMF (Dual-Tone Multi-Frequency)** — che rappresenta numericamente il numero desiderato.

La segnalazione che gestisce lo stato della chiamata (occupato, libero, errore, ecc.) avviene tramite una rete separata, chiamata **SS7 (Signaling System 7)**.  
È una **rete “fuori banda” (out-band)**, cioè distinta dal canale della voce:  
mentre la voce viaggia su un circuito dedicato, la segnalazione viaggia in parallelo su una **rete a pacchetto (X.25)** che collega tutti gli switch.

SS7 è responsabile di:

- stabilire e terminare la chiamata,
- rilevare linee occupate,
- gestire errori e deviazioni.

---

### **8. PBX – Private Branch Exchange**

Un **PBX** è uno **switch telefonico privato**, spesso chiamato **centralino aziendale**.  
Serve a collegare internamente i telefoni di un’organizzazione, con numeri brevi (solitamente meno di 7 cifre), e a fornire accesso alla rete pubblica.

Tipologie:

- **PBX reale**: il centralino fisico si trova all’interno dell’azienda;
- **PBX virtuale**: il centralino è gestito dal fornitore del servizio (es. VoIP aziendale).

In entrambi i casi, per uscire verso l’esterno si usa solitamente il prefisso **“0”**.  
L’azienda paga la compagnia telefonica per l’uso del servizio di commutazione.

---

### **9. Dalla telefonia classica al mondo IP**

La transizione da analogico a digitale nella rete telefonica è stata il primo passo verso le **reti dati** moderne.  
I concetti di **campionamento, codifica, commutazione di circuito e segnalazione** sono le radici dei meccanismi che ritroveremo più avanti nel corso, quando parleremo di **reti IP** e **commutazione di pacchetto**.

---

### **10. Riassunto concettuale**

- Un segnale vocale è una **grandezza analogica** trasformata in **tensione elettrica**.
- La **modulazione** permette di trasmettere più segnali su frequenze diverse.
- La **commutazione di circuito** crea un percorso dedicato e temporaneo tra due utenti.
- La **telefonia digitale** usa la **PCM** per rappresentare la voce come bit (8 bit × 8 kHz = 64 kbps).
- La rete **PSTN** combina tratti analogici e digitali in una gerarchia di **switch e central office**.
- Il protocollo **SS7** gestisce la segnalazione fuori banda.
- I **PBX** permettono la comunicazione interna in aziende e organizzazioni.
