# **Esercizio 4 — Subnetting di `200.35.1.0/24` con 20 host per subnet**

## **Testo**

Vi è stato assegnato il blocco di indirizzi IP **`200.35.1.0/24`**.

**a)** Definire la maschera per avere **20 host** su ciascuna sottorete.
**b)** Qual è il massimo numero di host su ciascuna sottorete?
**c)** Qual è il massimo numero di subnet che è possibile definire?
**d)** Specificare le subnet di `200.35.1.0/24` in notazione decimale puntata.
**e)** Specificare l'intervallo di indirizzi di host della subnet `200.35.1.192`.
**f)** Specificare l'indirizzo di broadcast per la subnet `200.35.1.192`.

---

## **Soluzione**

`200.35.1.0` è un net_id di **classe C** (primo byte 200 ∈ [192, 223]) con maschera di default `/24` e 8 bit di host.

### **a) Maschera per ospitare 20 host**

Per supportare 20 host servono $h$ bit di host tali che $2^h - 2 \geq 20$:

$$
2^4 - 2 = 14 \;<\; 20 \;\leq\; 2^5 - 2 = 30
$$

Quindi servono **5 bit di host**, e i bit di prefisso sono $32 - 5 = 27$:

$$
\boxed{\text{Maschera} = /27 = 255.255.255.224}
$$

### **b) Numero massimo di host per subnet**

$$
2^5 - 2 = \boxed{30 \text{ host}}
$$

(esclusi network address e broadcast).

### **c) Numero massimo di subnet**

Sono stati "rubati" $27 - 24 = 3$ bit alla parte host. Le combinazioni utili (escludendo tutti 0 e tutti 1) sono:

$$
2^3 - 2 = \boxed{6 \text{ subnet}}
$$

### **d) Lista delle subnet**

`200.35.1 = 11001000.00100011.00000001`. I 3 bit di subnet_id occupano la parte alta dell'ultimo byte; il delta tra subnet successive è $2^5 = 32$ (valore del bit meno significativo della maschera nell'ultimo ottetto: `001 00000` = 32).

| # | Binario (3 bit di subnet evidenziati) | Subnet id |
|---|---|---|
| 1 | `11001000.00100011.00000001.`**`001`**`00000` | **200.35.1.32/27** |
| 2 | `11001000.00100011.00000001.`**`010`**`00000` | **200.35.1.64/27** |
| 3 | `11001000.00100011.00000001.`**`011`**`00000` | **200.35.1.96/27** |
| 4 | `11001000.00100011.00000001.`**`100`**`00000` | **200.35.1.128/27** |
| 5 | `11001000.00100011.00000001.`**`101`**`00000` | **200.35.1.160/27** |
| 6 | `11001000.00100011.00000001.`**`110`**`00000` | **200.35.1.192/27** |

Sono escluse `.0/27` (tutti zero nei bit di subnet) e `.224/27` (tutti uno).

### **e) Intervallo di host per la Subnet #6 (`200.35.1.192/27`)**

| Etichetta | Binario | Indirizzo |
|---|---|---|
| Subnet (network address) | `11001000.00100011.00000001.110 00000` | **`200.35.1.192/27`** |
| Host #1 | `11001000.00100011.00000001.110 00001` | `200.35.1.193/27` |
| Host #2 | `11001000.00100011.00000001.110 00010` | `200.35.1.194/27` |
| Host #3 | `11001000.00100011.00000001.110 00011` | `200.35.1.195/27` |
| … | … | … |
| Host #29 | `11001000.00100011.00000001.110 11101` | `200.35.1.221/27` |
| Host #30 | `11001000.00100011.00000001.110 11110` | `200.35.1.222/27` |

Intervallo utilizzabile:

$$
\boxed{200.35.1.193 \;\text{–}\; 200.35.1.222 \quad (30 \text{ host})}
$$

### **f) Indirizzo di broadcast della Subnet #6**

Tutti i bit di host a 1:

```
11001000.00100011.00000001.110 11111  →  200.35.1.223
```

$$
\boxed{\text{Broadcast} = 200.35.1.223/27}
$$

---

**Riferimenti**: [L3 – Complementi di IP](../L3%20-%20Complementi%20di%20IP.md), [L4 – Progetto di inter-reti](../L4%20-%20Progetto%20di%20inter-reti.md) (tabella classe C).
