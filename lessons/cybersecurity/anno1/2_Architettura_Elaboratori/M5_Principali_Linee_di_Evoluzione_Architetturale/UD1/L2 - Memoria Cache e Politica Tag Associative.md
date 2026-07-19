## ***Lezione 2: Memoria cache e politica Tag Associative***

---

> 📌 Questa lezione rielabora integralmente le pagine 9–15 di `M5doc.pdf`: mapping dei blocchi, hit e miss, esempio numerico a 16 bit, memoria dei tag e procedura completa di accesso.

### **1. I tre problemi di progetto**

La memoria di lavoro (`MdL`) e la memoria cache (`MC`) vengono entrambe divise in blocchi di uguale dimensione. Quando la parola richiesta non è in cache, viene trasferito l'intero blocco che la contiene.

Il progetto deve risolvere tre problemi:

1. **mapping**: in quali blocchi di cache può essere copiato un dato blocco di MdL;
2. **ricerca**: come stabilire rapidamente se la parola richiesta è presente;
3. **sostituzione**: quale blocco eliminare quando occorre importarne uno nuovo.

Il PDF chiama la prima soluzione **politica Tag Associative**. Nella terminologia più comune corrisponde a una cache **direct-mapped**, perché ogni blocco di memoria ha una sola posizione possibile in cache. Manteniamo il nome del PDF, chiarendo questa equivalenza.

### **2. Hit, miss e hit ratio**

Un accesso produce:

- **hit** se il blocco è già presente in cache;
- **miss** se deve essere recuperato dalla memoria di lavoro.

Il tempo di hit è vicino al tempo di accesso della cache. Un miss comprende almeno ricerca fallita, trasferimento del blocco dal livello inferiore, aggiornamento dei metadati e accesso finale alla parola.

La misura corretta dell'efficacia è:

$$
HR=\frac{N_{hit}}{N_{accessi}}
=\frac{N_{hit}}{N_{hit}+N_{miss}}.
$$

Il **miss ratio** è:

$$
MR=1-HR=\frac{N_{miss}}{N_{hit}+N_{miss}}.
$$

> ⚠️ Nel PDF compare $N_{hit}/N_{miss}$, ma quello è il rapporto hit/miss, non l'hit ratio convenzionale. La formula è stata corretta perché un hit ratio deve restare fra 0 e 1.

Un valore del 100% è un limite ideale: significherebbe che la memoria di lavoro non deve mai essere consultata dopo l'avviamento.

### **3. Esempio di riferimento**

Il PDF considera:

- una memoria di lavoro da 64 Ki parole, cioè $2^{16}=65\,536$ parole;
- blocchi da 16 parole, cioè $2^4$;
- una cache da 128 blocchi, cioè $2^7$;
- blocchi di cache ancora da 16 parole.

Il numero di blocchi in MdL è:

$$
N_{blocchi,MdL}=\frac{65\,536}{16}=4\,096=2^{12}.
$$

Poiché la cache contiene solo 128 blocchi, 32 differenti blocchi di MdL devono condividere la stessa posizione possibile:

$$
\frac{4\,096}{128}=32=2^5.
$$

### **4. Gruppi e corrispondenza univoca**

I blocchi di MdL vengono organizzati logicamente in 128 gruppi. Ogni gruppo contiene i 32 blocchi che possono essere copiati nella stessa linea di cache:

| Linea/gruppo | Blocchi di MdL candidati |
| ---: | --- |
| 0 | 0, 128, 256, $\ldots$, 3968 |
| 1 | 1, 129, 257, $\ldots$, 3969 |
| $g$ | $g$, $g+128$, $g+256$, $\ldots$, $g+31\cdot128$ |
| 127 | 127, 255, 383, $\ldots$, 4095 |

Il numero di gruppi coincide con il numero di linee della cache:

$$
N_{gruppi}=N_{linee,MC}=128.
$$

Per un blocco globale $b$ della memoria di lavoro:

$$
NG=b\bmod128,
$$

$$
NB=\left\lfloor\frac{b}{128}\right\rfloor.
$$

`NG` identifica la linea di cache; `NB` distingue quale dei 32 blocchi candidati vi risiede attualmente.

### **5. Scomposizione dell'indirizzo**

Ogni indirizzo a 16 bit viene diviso in tre campi:

| Campo | Bit | Intervallo | Funzione |
| --- | ---: | ---: | --- |
| `NB` | 5 MSB | 0–31 | numero del blocco all'interno del gruppo; funge da tag |
| `NG` | 7 bit centrali | 0–127 | numero del gruppo e indice della linea di cache |
| `NP` | 4 LSB | 0–15 | numero della parola nel blocco |

Quindi:

$$
\text{indirizzo}=NB\;\Vert\;NG\;\Vert\;NP,
$$

dove $\Vert$ indica concatenazione.

I 4 bit meno significativi derivano dalla dimensione del blocco; i successivi 7 selezionano una delle 128 linee; i 5 rimanenti devono essere memorizzati come identificatore.

### **6. La memoria dei tag**

Accanto a ogni linea di cache è presente una voce nella **tag memory**. La voce `tag[NG]` conserva il campo `NB` del blocco copiato in quella linea.

Esempio: il blocco globale 129 è:

$$
129=1\cdot128+1.
$$

Pertanto $NB=1$ e $NG=1$. Se si trova nella cache, deve essere nella linea 1 e `tag[1]` deve contenere 1.

Il tag è indispensabile perché l'indice, da solo, identifica la linea ma non quale dei 32 blocchi alternativi vi sia contenuto.

### **7. Procedura in caso di hit**

La CPU presenta $NB\Vert NG\Vert NP$ sul bus indirizzi. Il gestore della cache:

1. usa `NG` per selezionare simultaneamente la linea e la voce di tag;
2. confronta `tag[NG]` con `NB`;
3. se coincidono, dichiara un hit;
4. accede alla parola `MC[NG,NP]`.

Formalmente:

$$
tag[NG]=NB
\quad\Longrightarrow\quad
\text{dato}=MC[NG,NP].
$$

La ricerca richiede un solo confronto, perché il blocco può trovarsi in una sola posizione.

### **8. Procedura in caso di miss**

Se:

$$
tag[NG]\ne NB,
$$

la linea indicizzata contiene un altro blocco dello stesso gruppo. Il gestore:

1. legge dalla MdL l'intero blocco identificato da $(NB,NG)$;
2. lo copia nella linea `MC[NG]`, sostituendo il contenuto precedente;
3. assegna `tag[NG] ← NB`;
4. serve la parola `MC[NG,NP]` alla CPU.

Il miss è quindi più lento perché aggiunge un trasferimento di blocco e l'aggiornamento del tag.

### **9. Esempio completo**

Supponiamo che l'indirizzo sia:

$$
NB=00101_2=5,
\qquad NG=0000011_2=3,
\qquad NP=1001_2=9.
$$

Il blocco globale richiesto è:

$$
b=NB\cdot128+NG=5\cdot128+3=643.
$$

La parola è la numero 9 del blocco 643 e può risiedere soltanto nella linea 3.

- Se `tag[3]=5`, si legge direttamente `MC[3,9]`.
- Se `tag[3]≠5`, si copia il blocco 643 in `MC[3]`, si scrive 5 nel tag e poi si legge la parola 9.

### **10. Vantaggi e conflitti**

La politica è semplice:

- indice immediato;
- un solo tag da leggere e confrontare;
- nessuna scelta di posizione durante un miss;
- circuito veloce e regolare.

Il limite è la **non ottimizzazione globale**. Se il programma alterna frequentemente due blocchi con lo stesso `NG`, questi si espellono a vicenda anche quando molte altre linee sono inutilizzate. Si tratta di **conflict miss** dovuti al mapping rigido.

> ✅ La politica Tag Associative/direct-mapped divide l'indirizzo in tag, indice e offset. Il mapping univoco rende la ricerca rapidissima, ma blocchi dello stesso gruppo competono sempre per una sola linea e possono causare sostituzioni evitabili.
