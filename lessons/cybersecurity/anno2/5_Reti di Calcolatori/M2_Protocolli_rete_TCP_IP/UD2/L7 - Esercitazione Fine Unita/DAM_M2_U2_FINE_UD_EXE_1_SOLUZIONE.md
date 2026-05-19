# **Esercizio 1 — Scelta di classe e schema d'indirizzamento**

## **Testo**

Una rete IP composta da una sola rete locale contiene:

- 6 server Windows 2003;
- 100 computer con Windows XP;
- 80 computer Windows 2000;
- 80 computer Linux.

**a)** Quali classi di indirizzi IP possono essere usate per questa rete?
**b)** Quale dei seguenti net_id va usato per supportare la classe di indirizzi scelta?
- `196.200.3.0`
- `10.0.0.0`
- `200.20.1.0`
- `150.20.0.0`

**c)** Usando i net_id scelti nella domanda b), si progetti uno schema d'indirizzamento IP per la rete della domanda a) che renda facile distinguere i diversi tipi di computer.

---

## **Soluzione**

### **a) Classi utilizzabili**

Il totale dei dispositivi connessi è:

$$
6 + 100 + 80 + 80 = 266 \text{ host}
$$

Una rete di **classe C** offre al massimo $2^8 - 2 = 254$ host utilizzabili per rete, quindi **non è sufficiente**.

Si possono quindi usare **indirizzi di classe A o di classe B**, che dispongono rispettivamente di $\approx 16$ milioni e $\approx 65\,534$ host per rete (vedi maschere di default in [L1 – Internet Protocol](../L1%20-%20Internet%20Protocol%20(IP).md)).

### **b) Scelta del net_id**

Analizzando il primo byte di ciascun net_id:

| Net_id | Primo byte | Classe | Valido per A/B? |
|---|---|---|---|
| `196.200.3.0` | 196 | C | NO (è classe C) |
| `10.0.0.0` | 10  | A privata | **SÌ** (classe A privata, range `10.0.0.0/8`) |
| `200.20.1.0` | 200 | C | NO (è classe C) |
| `150.20.0.0` | 150 | B | **SÌ** (classe B; coincide con un indirizzo pubblico registrato, ma è formalmente di classe B) |

Vanno bene quindi **`10.0.0.0`** (classe A privata) oppure **`150.20.0.0`** (classe B).

### **c) Schema d'indirizzamento**

Avendo scelto il net_id **`10.0.0.0`** (classe A privata, host_ID di 24 bit, cioè 3 byte), si può sfruttare il **secondo byte** per identificare la categoria di computer, lasciando gli altri due byte per il singolo host. Esempio:

| Categoria | Range di indirizzi |
|---|---|
| Server Windows 2003     | `10.10.z.w`  → es. `10.10.0.1` – `10.10.0.6`     |
| Workstation Windows XP  | `10.100.z.w` → es. `10.100.0.1` – `10.100.0.100` |
| Workstation Windows 2000| `10.150.z.w` → es. `10.150.0.1` – `10.150.0.80`  |
| Computer Linux          | `10.200.z.w` → es. `10.200.0.1` – `10.200.0.80`  |

Se si fosse scelto invece il net_id di **classe B** `150.20.0.0` (host_ID di 16 bit, cioè 2 byte), si sarebbe potuto usare il **terzo ottetto** per distinguere le categorie:

| Categoria | Range di indirizzi |
|---|---|
| Server Windows 2003     | `150.20.10.w`  |
| Workstation Windows XP  | `150.20.100.w` |
| Workstation Windows 2000| `150.20.150.w` |
| Computer Linux          | `150.20.200.w` |

> **Buona pratica**: lasciare ampi intervalli vuoti tra le serie di indirizzi, in modo da poter aggiungere altri computer dello stesso tipo senza dover riprogettare lo schema.

---

**Riferimenti**: [L1 – Internet Protocol (IP)](../L1%20-%20Internet%20Protocol%20(IP).md), [L2 – Configurazione IP](../L2%20-%20Introduzione%20alla%20configurazione%20IP.md) (sezione indirizzi privati).
