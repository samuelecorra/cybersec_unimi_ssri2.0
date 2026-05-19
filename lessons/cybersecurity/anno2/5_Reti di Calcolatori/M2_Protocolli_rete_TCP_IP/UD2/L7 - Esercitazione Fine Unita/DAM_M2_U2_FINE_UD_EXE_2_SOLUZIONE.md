# **Esercizio 2 — Net_id di classe C in blocchi CIDR (supernetting)**

## **Testo**

Scrivere i net_id sia in binario che con la notazione puntata (in numero significativo a titolo di esempio) e commentare le maschere di sottorete nei casi seguenti:

**a)** Elencare i network id di classe C compresi nel blocco CIDR **`200.56.168.0/21`**.
**b)** Elencare i network id di classe C compresi nel blocco CIDR **`195.24/13`**.

---

## **Soluzione**

Ricordiamo che un net_id di **classe C** ha prefisso di **24 bit**. Quando un blocco CIDR ha un prefisso **più corto di /24**, contiene al suo interno $2^{24-p}$ network id di classe C (dove $p$ è la lunghezza del prefisso CIDR). Questa tecnica, in cui si aggregano più reti classful in un'unica rete più grande, si chiama **supernetting**.

### **a) Blocco `200.56.168.0/21`**

Espressione binaria del prefisso (i bit fissi sono i primi 21):

```
200.56.168.0/21  →  11001000 . 00111000 . 10101 000 . 00000000
                    └─── 21 bit fissi ───┘└─ host ─┘
```

La maschera `/21` è **3 bit più corta** della maschera standard di classe C (`/24`). Significa che variano i 3 bit meno significativi del terzo byte, generando **$2^3 = 8$ network id di classe C** distinti:

| # | Binario (terzo byte evidenziato) | Net_id |
|---|---|---|
| 0 | `11001000.00111000.10101`**`000`**`.xxxxxxxx` | **200.56.168.0/24** |
| 1 | `11001000.00111000.10101`**`001`**`.xxxxxxxx` | **200.56.169.0/24** |
| 2 | `11001000.00111000.10101`**`010`**`.xxxxxxxx` | **200.56.170.0/24** |
| 3 | `11001000.00111000.10101`**`011`**`.xxxxxxxx` | **200.56.171.0/24** |
| 4 | `11001000.00111000.10101`**`100`**`.xxxxxxxx` | **200.56.172.0/24** |
| 5 | `11001000.00111000.10101`**`101`**`.xxxxxxxx` | **200.56.173.0/24** |
| 6 | `11001000.00111000.10101`**`110`**`.xxxxxxxx` | **200.56.174.0/24** |
| 7 | `11001000.00111000.10101`**`111`**`.xxxxxxxx` | **200.56.175.0/24** |

### **b) Blocco `195.24.0.0/13`**

Espressione binaria del prefisso (i bit fissi sono i primi 13):

```
195.24.0.0/13  →  11000011 . 00011 000 . 00000000 . 00000000
                  └─ 13 bit fissi ─┘└─────── host ──────────┘
```

La maschera `/13` è **11 bit più corta** di `/24`, quindi nel blocco rientrano:

$$
2^{11} = 2048 \text{ network id di classe C}
$$

Alcuni di questi (i primi e gli ultimi):

| # | Binario | Net_id |
|---|---|---|
| 0     | `11000011.00011000.00000000.xxxxxxxx` | **195.24.0.0/24** |
| 1     | `11000011.00011000.00000001.xxxxxxxx` | **195.24.1.0/24** |
| 2     | `11000011.00011000.00000010.xxxxxxxx` | **195.24.2.0/24** |
| …     | …                                     | …                  |
| 2045  | `11000011.00011111.11111101.xxxxxxxx` | **195.31.253.0/24** |
| 2046  | `11000011.00011111.11111110.xxxxxxxx` | **195.31.254.0/24** |
| 2047  | `11000011.00011111.11111111.xxxxxxxx` | **195.31.255.0/24** |

Il blocco copre quindi tutti i net_id di classe C da `195.24.0.0` a `195.31.255.0`.

### **Commento sulle maschere**

In entrambi i casi la maschera CIDR (rispettivamente `/21` e `/13`) è **più corta** di quella classful di classe C (`/24`). Questo significa che il blocco CIDR **aggrega** più reti di classe C in un'unica rete logica (supernet), tecnica utile per:

- ridurre la dimensione delle tabelle di instradamento dei router (route aggregation);
- assegnare a un'organizzazione una quantità di indirizzi più adatta alle sue esigenze, evitando lo spreco tipico dell'indirizzamento classful.

---

**Riferimenti**: [L3 – Complementi di IP](../L3%20-%20Complementi%20di%20IP.md) (notazione CIDR, classless).
