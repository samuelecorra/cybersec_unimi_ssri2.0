# **Esercizio 3 — Subnetting di `132.45.0.0/16` in 8 sottoreti**

## **Testo**

Vi è stato assegnato il blocco di indirizzi IP **`132.45.0.0/16`** e dovete stabilire **8 sottoreti**. Completate il seguente schema:

**a)** Quante cifre binarie sono necessarie per definire le 8 sottoreti?
**b)** Scrivere la maschera di sottorete per la creazione di 8 sottoreti (forma decimale).
**c)** Esprimere i subnet_id in notazione binaria e decimale puntata.
**d)** Scrivere l'intervallo di indirizzi assegnabili (con un numero significativo di esempi) nella subnet `132.45.96.0` appena trovata.
**e)** Scrivere l'indirizzo di broadcast della subnet `132.45.96.0`.

---

## **Soluzione**

`132.45.0.0` è un net_id di **classe B** (primo byte 132 ∈ [128, 191]) con maschera di default `/16`. Per creare sottoreti dobbiamo aggiungere bit al prefisso, "rubandoli" alla parte host.

### **a) Bit necessari per 8 sottoreti**

Per ottenere **8 sottoreti** occorrono $s$ bit tali che $2^s \geq 8$:

$$
2^s = 8 \;\Rightarrow\; s = 4 \text{ bit}
$$

> Servono **4 bit** (non 3): la convenzione "classica" richiede di escludere le combinazioni con tutti 0 e tutti 1, quindi $2^3 - 2 = 6$ subnet utili con 3 bit non bastano. Con 4 bit si ottengono $2^4 - 2 = 14$ subnet effettive, sufficienti a coprirne 8.

### **b) Subnet mask**

Aggiungendo 4 bit ai 16 di default si ottiene una maschera **`/20`**:

```
11111111 . 11111111 . 1111 0000 . 00000000  →  255.255.240.0
```

$$
\boxed{\text{Subnet mask} = /20 = 255.255.240.0}
$$

Per ogni subnet sono disponibili $2^{12} - 2 = 4094$ host.

### **c) Subnet_id (8 sottoreti)**

`132.45 = 10000100.00101101` in binario. I 4 bit di subnet_id variano nella parte alta del terzo byte; il delta tra subnet successive è $2^{32-20} / 2^{8} = 16$ (cioè il valore del bit meno significativo della maschera nel terzo ottetto: `0001 0000` = 16).

| #   | Binario (4 bit di subnet evidenziati)         | Subnet id           |
| --- | --------------------------------------------- | ------------------- |
| 1   | `10000100.00101101.`**`0001`**`0000.00000000` | **132.45.16.0/20**  |
| 2   | `10000100.00101101.`**`0010`**`0000.00000000` | **132.45.32.0/20**  |
| 3   | `10000100.00101101.`**`0011`**`0000.00000000` | **132.45.48.0/20**  |
| 4   | `10000100.00101101.`**`0100`**`0000.00000000` | **132.45.64.0/20**  |
| 5   | `10000100.00101101.`**`0101`**`0000.00000000` | **132.45.80.0/20**  |
| 6   | `10000100.00101101.`**`0110`**`0000.00000000` | **132.45.96.0/20**  |
| 7   | `10000100.00101101.`**`0111`**`0000.00000000` | **132.45.112.0/20** |
| 8   | `10000100.00101101.`**`1000`**`0000.00000000` | **132.45.128.0/20** |

> Si parte da `132.45.16.0` (non da `.0.0`) per escludere la subnet "tutti zero". Allo stesso modo si esclude all'estremo opposto la subnet "tutti uno" (`.240.0`).

### **d) Intervallo di host per la Subnet #6 (`132.45.96.0/20`)**

I 12 bit di host variano da `0...01` a `1...10` (esclusi network address e broadcast). In binario:

| Etichetta | Binario | Indirizzo |
|---|---|---|
| Subnet (network address) | `10000100.00101101.0110 0000.00000000` | **`132.45.96.0/20`** |
| Host #1 | `10000100.00101101.0110 0000.00000001` | `132.45.96.1/20` |
| Host #2 | `10000100.00101101.0110 0000.00000010` | `132.45.96.2/20` |
| Host #3 | `10000100.00101101.0110 0000.00000011` | `132.45.96.3/20` |
| … | … | … |
| Host #4093 | `10000100.00101101.0110 1111.11111101` | `132.45.111.253/20` |
| Host #4094 | `10000100.00101101.0110 1111.11111110` | `132.45.111.254/20` |

L'intervallo utilizzabile è quindi:

$$
\boxed{132.45.96.1 \;\text{–}\; 132.45.111.254 \quad (4094 \text{ host})}
$$

### **e) Indirizzo di broadcast della Subnet #6**

Il broadcast si ottiene mettendo a **1 tutti i bit di host**:

```
10000100.00101101.0110 1111.11111111  →  132.45.111.255/20
```

$$
\boxed{\text{Broadcast} = 132.45.111.255/20}
$$

---

**Riferimenti**: [L3 – Complementi di IP](../L3%20-%20Complementi%20di%20IP.md) (subnetting, maschere), [L4 – Progetto di inter-reti](../L4%20-%20Progetto%20di%20inter-reti.md) (FLSM, tabelle di classe B).
