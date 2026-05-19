# **Esercizio 5 — Subnetting di `192.168.1.0` ed espansione VLSM**

L'esercizio è composto da due parti correlate (5A e 5B).

---

## **Esercizio 5A**

### **Testo**

Una piccola azienda ha ricevuto in assegnazione il pool di indirizzi privati **`192.168.1.0`** e deve creare **cinque sottoreti**, ciascuna delle quali supporta fino a **20 host**. Date la subnet mask e le subnet id corrispondenti, perché **`192.168.1.224`** non è un subnet id valido in questo contesto?

### **Soluzione**

#### **Scelta della maschera**

`192.168.1.0` è un indirizzo privato di classe C (`/24` di default). Servono 5 sottoreti di almeno 20 host:

- **Host**: $2^h - 2 \geq 20 \;\Rightarrow\; h = 5$ bit di host.
- **Subnet**: $2^s - 2 \geq 5 \;\Rightarrow\; s = 3$ bit di subnet.

Con $24 + 3 = 27$ bit di prefisso si ottiene la maschera:

$$
\boxed{\text{Subnet mask} = /27 = 255.255.255.224}
$$

Ogni subnet può ospitare $2^5 - 2 = 30$ host (più che sufficienti per i 20 richiesti).

#### **Lista dei subnet_id (3 bit di subnet)**

I 3 bit di subnet possono assumere 8 valori; escludendo `000` e `111` rimangono **6 subnet utili**:

| # | Binario (3 bit subnet evidenziati) | Subnet id |
|---|---|---|
| – | `192.168.1.`**`000`**`00000` | `192.168.1.0/27` (escluso, tutti 0) |
| 1 | `192.168.1.`**`001`**`00000` | **`192.168.1.32/27`** |
| 2 | `192.168.1.`**`010`**`00000` | **`192.168.1.64/27`** |
| 3 | `192.168.1.`**`011`**`00000` | **`192.168.1.96/27`** |
| 4 | `192.168.1.`**`100`**`00000` | **`192.168.1.128/27`** |
| 5 | `192.168.1.`**`101`**`00000` | **`192.168.1.160/27`** |
| 6 | `192.168.1.`**`110`**`00000` | **`192.168.1.192/27`** |
| – | `192.168.1.`**`111`**`00000` | `192.168.1.224/27` (**escluso, tutti 1**) |

#### **Perché `192.168.1.224` non è un subnet id valido**

Perché i 3 bit di subnet_id sono **tutti a 1** (`111`). Secondo la convenzione classica del subnetting **FLSM**, le subnet con i bit di subnet_id tutti a 0 o tutti a 1 sono **da escludere**:

- la subnet "tutti zero" coincide con la rete principale, generando ambiguità di routing;
- la subnet "tutti uno" può collidere con l'indirizzo di broadcast della rete principale.

Per questo, su 8 combinazioni possibili dei 3 bit, ne risultano effettivamente utilizzabili solo $2^3 - 2 = 6$, e `192.168.1.224/27` (con bit di subnet = `111`) è **sconsigliato**.

> Con i moderni router e l'indirizzamento classless (CIDR, RFC 1878) è in realtà possibile usare anche le subnet "tutti 0" e "tutti 1", ma la convenzione storica — adottata in questo esercizio — le esclude.

---

## **Esercizio 5B**

### **Testo**

Riferendosi alle 5 sottoreti dell'Esercizio 5A: le 2 reti di New York, le 2 di Londra e il collegamento router-router New York–Londra, come illustrato in figura, sono indirizzate con i subnet_id `192.168.1.32`, `192.168.1.64`, `192.168.1.96`, `192.168.1.128` e `192.168.1.160`.

Alla inter-rete vengono ora aggiunte le sottoreti di **Ontario** e **Tokyo**, collegate a New York tramite due nuovi collegamenti router-router. Spiegare come è possibile assegnare l'indirizzamento IP delle due nuove sottoreti **senza eseguire renumbering** delle reti esistenti.

### **Soluzione**

La maschera usata in 5A è **`/27`** e dei 6 subnet_id disponibili ne sono già occupati 5; resta **una sola sottorete libera**: **`192.168.1.192/27`**.

Per supportare le **due nuove sottoreti** (i due collegamenti router-router verso Ontario e Tokyo) **senza toccare i subnet_id già assegnati**, si applica una tecnica **VLSM** (*Variable Length Subnet Mask*): si **subnetta ulteriormente** la sottorete libera `192.168.1.192/27` con una maschera **più lunga** che però coincide con `/27` nei primi 27 bit, in modo da non sovrapporsi alle altre.

#### **Scelta della nuova maschera**

I collegamenti router-router (punto-punto) richiedono **2 host_id** ciascuno (i due router agli estremi). Quindi servono $h$ bit di host tali che $2^h - 2 \geq 2$, cioè **$h = 2$ bit di host** e **30 bit di prefisso**:

$$
\boxed{\text{Maschera per i link router-router} = /30 = 255.255.255.252}
$$

Da `/27` a `/30` aggiungiamo 3 bit di subnet → $2^3 = 8$ sotto-sottoreti, di cui **6 utili** (escludendo "tutti 0" e "tutti 1" all'interno del nuovo blocco).

#### **Sotto-sottoreti di `192.168.1.192/27` con maschera `/30`**

I 3 nuovi bit di subnet sono i bit 28-29-30 dell'ultimo ottetto. Si parte da `192.168.1.192` (`1100 0000` nell'ultimo byte) e il delta è $2^2 = 4$:

| # | Binario ultimo byte | Subnet id /30 | Host utili | Note |
|---|---|---|---|---|
| – | `110 000 00` | `192.168.1.192/30` | `.193 – .194` | sotto-subnet "tutti 0" (esclusa da convenzione) |
| 1 | `110 001 00` | **`192.168.1.196/30`** | `.197 – .198` | utilizzabile (es. New York ↔ Ontario) |
| 2 | `110 010 00` | **`192.168.1.200/30`** | `.201 – .202` | utilizzabile (es. New York ↔ Tokyo) |
| 3 | `110 011 00` | **`192.168.1.204/30`** | `.205 – .206` | utilizzabile |
| 4 | `110 100 00` | **`192.168.1.208/30`** | `.209 – .210` | utilizzabile |
| 5 | `110 101 00` | **`192.168.1.212/30`** | `.213 – .214` | utilizzabile |
| 6 | `110 110 00` | **`192.168.1.216/30`** | `.217 – .218` | utilizzabile |
| – | `110 111 00` | `192.168.1.220/30` | `.221 – .222` | sotto-subnet "tutti 1" (esclusa) |

Ognuna di queste sotto-sottoreti `/30` ospita esattamente **2 host_id**, esattamente ciò che serve per un collegamento router-router.

#### **Risultato**

L'inter-rete viene quindi indirizzata così:

| Rete | Subnet_id | Maschera |
|---|---|---|
| LAN New York #1     | `192.168.1.32`  | /27 |
| LAN New York #2     | `192.168.1.64`  | /27 |
| LAN Londra #1       | `192.168.1.96`  | /27 |
| LAN Londra #2       | `192.168.1.128` | /27 |
| Link NY ↔ Londra    | `192.168.1.160` | /27 |
| Link NY ↔ Ontario   | `192.168.1.196` | **/30** (nuovo) |
| Link NY ↔ Tokyo     | `192.168.1.200` | **/30** (nuovo) |

In questo modo:

- le **5 sottoreti già configurate non vengono toccate** (nessun renumbering);
- l'unica sottorete `/27` ancora libera viene **ulteriormente suddivisa** in 6 sotto-sottoreti `/30`, di cui 2 servono per Ontario e Tokyo e 4 restano disponibili per future espansioni;
- la rete continua a funzionare correttamente perché ciascun router può gestire prefissi di lunghezza diversa, grazie al **routing classless**.

---

**Riferimenti**: [L3 – Complementi di IP](../L3%20-%20Complementi%20di%20IP.md) (subnetting, CIDR), [L4 – Progetto di inter-reti](../L4%20-%20Progetto%20di%20inter-reti.md) (FLSM vs VLSM).
