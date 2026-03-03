# **Lezione 2: Instradamento intradominio**

---

### **1. Introduzione**

L’**instradamento intradominio** riguarda lo scambio di informazioni di rete **all’interno di un singolo dominio amministrativo** (es. una LAN estesa o una rete aziendale).  
In questo contesto si utilizzano protocolli detti **IGP – Interior Gateway Protocols**, i quali gestiscono automaticamente la costruzione e l’aggiornamento delle tabelle di routing.

I principali protocolli IGP sono:

- **RIP** (_Routing Information Protocol_)
    
- **IS-IS** (_Intermediate System to Intermediate System_)
    
- **OSPF** (_Open Shortest Path First_)
    

---

### **2. Il Routing Information Protocol (RIP)**

RIP è uno dei più antichi e semplici protocolli di instradamento dinamico.  
Si basa sull’**algoritmo Distance Vector**, in cui ciascun router conosce solo:

- il **proprio hop successivo** per ogni destinazione,
    
- e il **costo (numero di salti)** per raggiungerla.
    

Ogni router mantiene una tabella che indica, per ogni rete conosciuta:

- **l’indirizzo di destinazione**,
    
- **il prossimo hop**,
    
- **la distanza (metrica)**.

![](imgs/Pasted%20image%2020260225153622.png)

---

### **3. Algoritmo Distance Vector**

L’algoritmo funziona secondo tre principi fondamentali:

1. **Inizializzazione:**  
    Ogni nodo conosce solo sé stesso.  
    La distanza verso sé stesso è `0`, mentre verso tutte le altre destinazioni è `∞`.
    
2. **Aggiornamento periodico:**  
    Ogni **30 secondi**, o quando si verificano cambiamenti, ogni nodo **invia la propria tabella di routing ai vicini**.
    
3. **Regola di aggiornamento:**  
    Quando un nodo `j` riceve da un vicino `i` la distanza `d_ip` verso una destinazione `P`, calcola la nuova distanza:
    
    $$  
    d_{jp} \geq d_{ji} + d_{ip}  
    $$
    
    Se la nuova distanza è minore di quella registrata, `j` **aggiorna la sua tabella** e inoltra i pacchetti per `P` attraverso `i`.

> In sintesi: ogni nodo “impara” la topologia osservando i propri vicini e aggiornando progressivamente le rotte.

---

### **4. Esempio di aggiornamento**

Supponiamo che un nuovo nodo `I` si aggiunga alla rete.  
Inizialmente non conosce alcuna destinazione (`∞` per tutte).  
Quando si collega al nodo `A`, scambia con esso la propria tabella e riceve informazioni sui percorsi.

- `A` comunica a `I` che può raggiungere `B`, `C`, `D` a distanza 1.

![](imgs/Pasted%20image%2020260225153645.png)

- `I` aggiorna la propria tabella e imposta `A` come hop successivo per tali reti.

![](imgs/Pasted%20image%2020260225153705.png)

- Successivamente, `I` parla anche con `D`, apprendendo ulteriori cammini più efficienti.

![](imgs/Pasted%20image%2020260225153753.png)

> In pochi cicli, tutte le tabelle convergono verso percorsi coerenti e ottimali.

---

### **5. Il problema del “Count to Infinity”**

Un noto limite del Distance Vector è il **problema del conteggio all’infinito (count-to-infinity)**.  
Accade quando una rete diventa **irraggiungibile** (es. un link cade), ma i router **continuano a scambiarsi informazioni obsolete**, incrementando progressivamente la distanza verso la destinazione.

#### **Esempio**

- Inizialmente, il nodo `A` è raggiungibile da `B`, `C`, `D`, `E`.
    
- Se il collegamento tra `A` e `B` si interrompe, `B` cerca un nuovo percorso per `A`.
    
- `C` comunica a `B` che “A è raggiungibile tramite me, distanza 2”, ma non sa che quel percorso passa ancora da `B`.
    
- `B` accetta il nuovo percorso errato → e così, a ogni ciclo, la distanza verso `A` aumenta di 1 (`3, 4, 5…`) fino a raggiungere un valore infinito.

![](imgs/Pasted%20image%2020260225153833.png)

> Il problema genera **un gran numero di aggiornamenti inutili** e rallenta la convergenza della rete.

---

### **6. Soluzioni parziali al Count-to-Infinity**

Per mitigare questo problema, RIP adotta tecniche di prevenzione dei cicli:

#### **a) Split Horizon**

Un router **non comunica a un vicino** informazioni che ha appreso **proprio da quel vicino**.

> In pratica, si evita di restituire indietro la rotta da cui la si è ricevuta.

#### **b) Split Horizon con Poison Reverse**

Se `A` comunica a `B` di avere un percorso minimo verso `E`, allora `B` comunica ad `A` una **distanza infinita** verso `E`.

> Così si interrompe la propagazione di informazioni errate tra i due nodi.

#### **c) Limitazione del diametro della rete**

Si impone un **valore massimo di hop** (es. 15 in RIP).  
Oltre tale soglia, la rete viene considerata **irraggiungibile**.

---

### **7. Soluzioni più efficaci**

Le tecniche precedenti funzionano solo in parte:

- rallentano il problema ma **non lo eliminano completamente**;
    
- nei cicli più lunghi la **convergenza resta lenta**.
    

> L’unica vera soluzione è **abbandonare l’approccio Distance Vector** e utilizzare un algoritmo **Link State**, come quello implementato da **OSPF (Open Shortest Path First)**.

---

### **8. Conclusione**

L’instradamento intradominio basato su **Distance Vector** è:

- **semplice da implementare**,
    
- adatto a **reti di piccole dimensioni**,
    
- ma **inefficiente** e **lento a convergere** in reti estese o complesse.
    

> Da qui nasce l’evoluzione verso protocolli più moderni e robusti, come **OSPF**, che analizzeremo nelle prossime lezioni.