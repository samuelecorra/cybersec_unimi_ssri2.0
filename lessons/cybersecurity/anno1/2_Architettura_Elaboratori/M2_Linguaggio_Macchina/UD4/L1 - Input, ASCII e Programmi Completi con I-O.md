## ***Lezione 1: Programmazione interattiva, input e ASCII***

---

> 📌 Questa lezione rielabora integralmente le pagine 76–80 di `M2doc.pdf`.

### **1. I servizi usati dal programma**

L’esempio interattivo del PDF usa tre trap:

| Istruzione | Effetto |
| --- | --- |
| `TRAP x23` | attende un carattere e ne restituisce il codice in `R0` |
| `TRAP x21` | stampa il carattere il cui codice è in `R0` |
| `TRAP x25` | termina il programma |

Ogni `TRAP` salva in `R7` il punto di ritorno e trasferisce il controllo alla routine indicata dalla relativa voce nella vector table. La console LC-2 rappresenta tastiera e schermo del sistema simulato.

> ⚠️ In questa versione della LC-2 l’input usato dal PDF è `TRAP x23`, non `x20`. È inoltre input di **un carattere**, non una lettura automatica di interi decimali.

### **2. Relazione fra maiuscole e minuscole ASCII**

Nei codici ASCII le lettere latine maiuscole e minuscole corrispondenti differiscono di 32:

$$
\operatorname{ASCII}(\text{minuscola})=
\operatorname{ASCII}(\text{maiuscola})+32.
$$

Per esempio:

$$
\text{`Y'}=89=x0059,
\qquad \text{`y'}=121=x0079,
\qquad 121-89=32.
$$

Questa relazione consente una trasformazione con una sola somma, a condizione che l’input sia già una lettera maiuscola. Il programma non controlla l’intervallo `A–Z`: applicare 32 a un altro carattere produce semplicemente un altro codice.

### **3. Programma completo**

```asm
        .orig x3000

        TRAP  x23          ; legge un carattere in R0
        LD    R1, MaToMin  ; R1 = 32
        ADD   R0, R0, R1   ; maiuscola -> minuscola
        TRAP  x21          ; stampa il carattere in R0
        TRAP  x25          ; termina

MaToMin .fill #32
        .end
```

`MaToMin` è una cella dati. Si usa `LD` perché 32 non è rappresentabile nel campo con segno `imm5` di `ADD`, limitato a $[-16,15]$.

### **4. Traccia con input Y**

| Passo | Stato rilevante |
| --- | --- |
| `TRAP x23` | attende il tasto; poi `R0=x0059` |
| `LD R1,MaToMin` | `R1=x0020` |
| `ADD R0,R0,R1` | `R0=x0079`, cioè `y` |
| `TRAP x21` | la console visualizza `y` |
| `TRAP x25` | l’esecuzione si arresta |

Nel simulatore la trap di input può eseguire internamente un gran numero di istruzioni in un ciclo di attesa finché non arriva un tasto. Questo comportamento non indica un blocco anomalo: è **busy waiting** della routine di sistema. Usare Step Into per ogni istruzione interna sarebbe inutilmente lento; Step Over consente di osservare direttamente il ritorno al programma.

La schermata rende intuitivo il ciclo di attesa con una lettura ripetuta del registro di tastiera e un salto all’indietro finché non compare un dato. Quando l’utente digita `Y`, la console dell’esempio acquisisce subito il carattere, senza richiedere `Invio`; la routine termina e il controllo torna al programma con `R0=x0059` e `R7=x3001`.

### **5. Caratteri e numeri**

Un carattere digitato è una parola che contiene un codice ASCII. Il carattere `5`, per esempio, non restituisce automaticamente il numero 5. La conversione di stringhe decimali, i segni e i numeri a più cifre richiedono routine dedicate che non sono sviluppate in queste pagine.

> 💡 L’esempio mostra la differenza tra valore e rappresentazione: il numero 89, il codice `x0059` e il carattere `Y` possono essere la stessa configurazione di bit interpretata in modi diversi.

> ✅ Un programma interattivo LC-2 combina servizi di sistema, dati in memoria e istruzioni elementari; persino una conversione di carattere rende visibile il lavoro nascosto dalle librerie di alto livello.
