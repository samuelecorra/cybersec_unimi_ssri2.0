## ***Lezione 1: Compiti e organizzazione dell'ALU***

---

> 📌 Questa lezione rielabora integralmente le pagine 27–30 di `M4doc.pdf`: ruolo dell'ALU nel datapath, operazioni logiche e aritmetiche, comandi, flag e organizzazione interna. La pagina 30 del PDF è priva di contenuto didattico.

### **1. Il blocco che chiude il datapath**

L'**Arithmetic Logic Unit** riceve gli operandi dai bus di uscita del datapath, esegue l'operazione scelta dalla Control Unit e restituisce risultato e indicatori di stato. È il blocco che rende possibile il passaggio:

$$
\text{registri sorgente}\longrightarrow\text{elaborazione}\longrightarrow\text{registro destinazione}.
$$

Senza ALU, il datapath potrebbe soltanto conservare e instradare bit. Attraverso l'ALU, le stesse configurazioni diventano oggetto di trasformazioni logiche, aritmetiche e confronti.

### **2. Interfaccia dell'ALU**

Una ALU generica presenta:

- due ingressi dati `A` e `B`;
- un'uscita risultato `R`;
- un ingresso di comando `C`;
- un gruppo di uscite di stato `F`.

La larghezza di `A`, `B` e `R` coincide normalmente con quella dei registri general purpose della CPU. In una macchina a 16 bit, quindi, i tre percorsi principali sono larghi 16 bit.

Se `C` contiene $c$ bit, può distinguere al massimo:

$$
2^c
$$

configurazioni e quindi, in linea di principio, altrettante operazioni. Non tutte devono necessariamente essere utilizzate.

Le uscite `F`, o **flag**, descrivono proprietà del risultato che serviranno alle istruzioni successive. YM-0 conserva soltanto `Z`; altre architetture espongono più condition code. Il PDF richiama, come esempio, i flag `N`, `Z` e `P` della macchina LC-2:

- `N`: risultato negativo;
- `Z`: risultato nullo;
- `P`: risultato positivo.

### **3. Operazioni logiche**

Le operazioni logiche vengono applicate bit per bit. Per due parole da $n$ bit:

$$
(A\operatorname{AND}B)_i=A_iB_i,
$$

$$
(A\operatorname{OR}B)_i=A_i+B_i,
$$

$$
(\operatorname{NOT}A)_i=\overline{A_i}.
$$

Ogni posizione è indipendente dalle altre: l'AND sul bit 5 non dipende dal bit 4. Questa regolarità rende possibile costruire l'operatore replicando la stessa porta per tutti i bit.

Esempio su 4 bit:

| Operazione | Calcolo | Risultato |
| --- | --- | --- |
| AND | `1010 AND 1100` | `1000` |
| OR | `1010 OR 1100` | `1110` |
| NOT | `NOT 1010` | `0101` |

### **4. Operazioni aritmetiche**

L'ALU può includere:

- somma e sottrazione;
- incremento e decremento;
- confronti;
- in unità più sofisticate, moltiplicazione e divisione.

Il confronto non richiede necessariamente un circuito del tutto separato. Per verificare se $A=B$, per esempio, si può calcolare $A-B$ e osservare il flag `Z`. Analogamente, il segno e l'overflow possono contribuire ai confronti ordinati, secondo la rappresentazione numerica adottata.

> ⚠️ L'ALU manipola configurazioni di bit. È il comando e il modo in cui il programma interpreta gli operandi a stabilire se tali bit rappresentino interi senza segno, interi in complemento a 2, maschere logiche o altro.

### **5. Organizzazione interna a circuiti paralleli**

Il PDF propone un'organizzazione concettuale con quattro circuiti che ricevono tutti gli stessi ingressi `A` e `B`:

1. AND bit a bit;
2. OR bit a bit;
3. somma in complemento a 2;
4. prodotto di operandi con segno.

Le quattro uscite raggiungono un multiplexer $4\rightarrow1$. Il comando `C` seleziona quale risultato viene inviato su `R`:

| `C` | Risultato selezionato, in un possibile progetto |
| --- | --- |
| `00` | $A\land B$ |
| `01` | $A\lor B$ |
| `10` | $A+B$ |
| `11` | $A\times B$ |

La corrispondenza è una scelta progettuale: il principio fondamentale è che i circuiti producono i valori candidati e il multiplexer ne rende visibile uno solo.

Un secondo circuito di selezione può fare lo stesso per i flag associati all'operazione scelta. Non sarebbe corretto, per esempio, conservare il flag zero prodotto dal sommatore mentre l'uscita visibile proviene dall'AND.

### **6. Natura combinatoria e tempo di propagazione**

Dal punto di vista del datapath, l'ALU si comporta come un circuito combinatorio:

$$
(R,F)=f(A,B,C).
$$

Fissati operandi e comando, dopo il tempo di propagazione si stabilizzano risultato e flag. L'ALU non deve conservare autonomamente il risultato: è il registro destinazione a campionarlo al fronte di clock.

Dire “combinatoria” non implica però che ogni sua possibile realizzazione interna sia priva di stato. Un moltiplicatore può essere iterativo e usare registri interni per più cicli. In quel caso il blocco deve esporre un protocollo di avvio e completamento, e il control path attende la fine. Per il resto della CPU, il risultato diventa utilizzabile soltanto dopo la latenza dichiarata dall'unità.

### **7. Larghezza del risultato**

Somma e prodotto pongono problemi diversi.

- La somma di due parole da $n$ bit può richiedere $n+1$ bit se si vuole conservare il riporto senza segno.
- Il prodotto di due parole da $n$ bit può richiedere fino a $2n$ bit.

Se `R` ha la stessa larghezza dei registri general purpose, il circuito o l'ISA devono stabilire come trattare i bit eccedenti: scartarli, conservarli in un secondo registro o segnalarli tramite flag. Lo schema concettuale del PDF mostra il prodotto come una delle funzioni interne, mentre le lezioni successive studiano esplicitamente reti con uscita completa a $2n$ bit.

### **8. ALU, datapath e Control Unit**

I tre elementi hanno responsabilità diverse:

| Elemento | Responsabilità |
| --- | --- |
| Datapath | rende disponibili percorsi e registri |
| ALU | calcola una funzione sugli operandi |
| Control Unit | sceglie sorgenti, operazione, destinazione e istante di campionamento |

Per eseguire $R0\leftarrow R0+MDR$, la CU deve:

1. abilitare `R0` e `MDR` su bus diversi;
2. selezionare la somma nell'ALU;
3. attendere la stabilizzazione del risultato;
4. abilitare l'ingresso di `R0` e gli eventuali registri di flag.

Un errore in uno solo di questi segnali rende inutile anche un'ALU aritmeticamente corretta.

> ✅ L'ALU è un insieme selezionabile di circuiti logico-aritmetici. Operandi e risultato hanno la larghezza naturale della CPU, il comando sceglie una delle funzioni disponibili e i flag rendono osservabili proprietà utili del risultato.
