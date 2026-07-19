## ***Lezione 2: Struttura e set di istruzioni della CPU LC-2***

---

> 📌 Questa lezione rielabora integralmente le pagine 19–23 di `M2doc.pdf`.

### **1. Origine e finalità della LC-2**

La **LC-2** è una CPU didattica proposta da Yale N. Patt e utilizzata all’Università della Virginia e nel percorso del manuale *Introduction to Computing Systems*. Non vuole imitare la complessità di un processore commerciale: riduce la macchina ai meccanismi necessari per collegare circuiti, ISA, assembly e programmi.

### **2. Profilo architetturale**

La LC-2 è una macchina RISC a 16 bit:

- ogni istruzione occupa una parola di **16 bit**;
- data bus, celle di memoria, PC e registri generali sono larghi **16 bit**;
- l’address bus a 16 bit identifica $2^{16}=65\,536$ celle;
- i GPR sono otto, da `R0` a `R7`, quindi un registro si codifica con 3 bit;
- i condition code sono `N`, `Z` e `P`;
- l’ALU realizza direttamente `ADD`, `AND` e `NOT`.

Con 4 bit di opcode esistono $2^4=16$ **codici operativi possibili**. Questo non significa che esistano soltanto sedici forme mnemoniche: uno stesso opcode può distinguere varianti attraverso altri campi, come `ADD` registro/immediato o `JSR` con e senza salvataggio del ritorno.

L’operazione OR non richiede una porta specifica nell’ISA, perché deriva da De Morgan:

$$
A\lor B=\neg(\neg A\land \neg B).
$$

In assembly si può quindi negare ciascun operando, applicare `AND` e negare il risultato.

### **3. Il problema dell’indirizzo in una parola da 16 bit**

I primi 4 bit di un’istruzione sono occupati dall’opcode. Restano al massimo 12 bit, insufficienti per includere un indirizzo arbitrario di 16 bit insieme agli altri operandi. La LC-2 risolve alcuni accessi mediante **indirizzamento nella pagina corrente**.

Un indirizzo di 16 bit viene separato correttamente in:

$$
\underbrace{\text{pagina}}_{7\ \text{bit}}\;\underbrace{\text{spostamento nella pagina}}_{9\ \text{bit}}.
$$

Ne risultano:

- $2^7=128$ pagine;
- $2^9=512$ celle per pagina;
- $128\cdot512=65\,536$ celle complessive.

> ⚠️ La suddivisione non è 4+12: confondere i 4 bit dell’opcode con i bit di pagina porterebbe erroneamente a 16 pagine da 4096 celle.

### **4. Costruzione dell’indirizzo di pagina corrente**

Le istruzioni con `pgoffset9` prendono i 7 bit più significativi del PC e li concatenano ai 9 bit dell’istruzione:

$$
EA=PC[15:9]\;\|\;pgoffset9.
$$

Il simbolo $\|$ indica **concatenazione**, non somma. L’istruzione può quindi riferirsi a qualunque cella della pagina di 512 parole che contiene il PC usato come riferimento.

Se il PC è nella pagina che inizia a `x3000`, i 9 bit finali selezionano da `x3000` a `x31FF`. Quando il PC passa in una pagina diversa, cambiano anche i 7 bit superiori e quindi la regione raggiunta.

### **5. LEA: caricare un indirizzo**

Il formato di `LEA` è:

| 15–12 | 11–9 | 8–0 |
| --- | --- | --- |
| `1110` | `DR` | `pgoffset9` |

La sua semantica è:

$$
DR\leftarrow PC[15:9]\;\|\;pgoffset9.
$$

`LEA` carica l’**indirizzo effettivo**, senza leggere il contenuto della cella. Se il PC è `x3023`, la pagina è `x3000`; per ottenere `x30F4`, l’offset di pagina è `0 1111 0100`. Con `DR=R1`:

```text
1110 001 0 1111 0100 = xE2F4
```

e al termine `R1=x30F4`.

Nel PDF e negli esempi si usa talvolta la scrittura didattica `LEA R1, x30F4`. Nel vero sorgente assembly si preferisce un’etichetta: l’assembler verifica che sia nella pagina raggiungibile e inserisce i 9 bit necessari.

> ✅ La LC-2 sacrifica l’indirizzo assoluto dentro ogni istruzione a favore di formati fissi e semplici; pagina corrente, indiretto e base+offset recuperano la flessibilità necessaria.
