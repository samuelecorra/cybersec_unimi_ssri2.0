# RSA completo — Esercizio d'esame

> 🗂️ **Fonte:** appello del **20 settembre 2024**, Domanda 4 — Modulo M3 (Cifrature Asimmetriche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 20 settembre 2024](../../M7_Appelli_Svolti/UD2_Anno_2024/L7%20-%20Appello%2020%20settembre%202024.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 4 — RSA completo: generazione chiavi, cifratura, decifratura, firma (20 punti)

### Traccia originale

> **(20 punti) Cifratura e Firma RSA.** Alice vuole generare una coppia di chiavi RSA. Vengono selezionati i due primi $p = 5$ e $q = 7$.
>
> a. Calcolare gli ulteriori parametri necessari.
>
> b. Nel selezionare la chiave privata, Alice ha dei dubbi sulla scelta di $d = 3$. Discutere la scelta.
>
> c. Alice decide di scegliere $d = 11$. Qual è la chiave pubblica: $e_1 = 13$ o $e_2 = 11$?
>
> d. Se Bob vuole mandare il messaggio $M = 33$ ad Alice, quale chiave deve usare e qual è il messaggio spedito ad Alice?
>
> e. Una volta ricevuto il messaggio cifrato, mostrare le operazioni che Alice compie per la decifratura.
>
> f. Se Alice vuole spedire a Bob un messaggio firmato $M' = 6$, cosa spedisce Alice? Come fa Bob a verificare che il messaggio è stato spedito da Alice?

### Prerequisiti teorici

- [`../../M3_Cifrature_Asimmetriche/UD2/L1 - Algoritmo RSA.md`](../../M3_Cifrature_Asimmetriche/UD2/L1%20-%20Algoritmo%20RSA.md) — RSA completo
- [`../../M5_Firme_Digitali/UD2/L1 - Schema RSA.md`](../../M5_Firme_Digitali/UD2/L1%20-%20Schema%20RSA.md) — firma RSA

### Soluzione dettagliata

#### Parte a — Calcolo dei parametri RSA (punteggio incluso nel totale)

**Dati:** $p = 5$, $q = 7$.

**Calcolo del modulo:**

$$n = p \cdot q = 5 \cdot 7 = 35$$

**Calcolo della funzione di Eulero:**

$$\varphi(n) = (p-1)(q-1) = (5-1)(7-1) = 4 \cdot 6 = 24$$

**Riepilogo dei parametri RSA per Alice:**
- Modulo: $n = 35$
- $\varphi(35) = 24$
- Chiave pubblica: $(n, e)$ — da determinare
- Chiave privata: $(n, d)$ — da determinare

#### Parte b — Discussione della scelta $d = 3$ (punteggio incluso nel totale)

Per usare $d = 3$ come chiave privata, deve valere $ed \equiv 1 \pmod{24}$.

Con $d = 3$: dobbiamo trovare $e$ tale che $3e \equiv 1 \pmod{24}$, ovvero l'inverso di 3 modulo 24.

$\operatorname{mcd}(3, 24) = 3 \neq 1$: il 3 **non ha inverso modulo 24** (poiché 3 divide 24).

Quindi **$d = 3$ non è una scelta valida** per la chiave privata RSA con $\varphi(n) = 24$. Non esiste una chiave pubblica $e$ corrispondente.

**Motivo formale:** la condizione per un valido esponente privato è $\operatorname{mcd}(d, \varphi(n)) = 1$. Poiché $\operatorname{mcd}(3, 24) = 3 \neq 1$, la scelta è invalida.

> ⚠️ In generale, $d$ deve essere scelto coprimo con $\varphi(n)$. Un errore comune è scegliere $d$ senza verificare questa condizione. Per $\varphi(24) = $ i valori coprimi con 24 sono: 1, 5, 7, 11, 13, 17, 19, 23.

#### Parte c — Chiave pubblica corrispondente a $d = 11$ (punteggio incluso nel totale)

Con $d = 11$, cerchiamo $e$ tale che $11e \equiv 1 \pmod{24}$.

Verifica di $e_1 = 13$: $11 \cdot 13 = 143 = 5 \cdot 24 + 23 \bmod 24 = 23 \neq 1$. **Non valido.**

Verifica di $e_2 = 11$: $11 \cdot 11 = 121 = 5 \cdot 24 + 1 \bmod 24 = 1$ ✓. **Valido.**

**La chiave pubblica è $e = 11$** (e coincide con la chiave privata: $e = d = 11$).

> 📌 Notare che in questo caso la chiave pubblica e quella privata coincidono ($e = d = 11$). Questo è possibile perché $11^2 = 121 \equiv 1 \pmod{24}$: 11 è la propria inversa moltiplicativa modulo 24. Non è un errore, ma è crittograficamente indesiderabile avere $e = d$ (riduce la sicurezza del sistema poiché chiunque conosce $e$ conosce anche $d$). In pratica si sceglie sempre $e \neq d$.

**Chiave pubblica di Alice:** $(n, e) = (35, 11)$.
**Chiave privata di Alice:** $(n, d) = (35, 11)$.

#### Parte d — Cifratura da parte di Bob (punteggio incluso nel totale)

Bob vuole spedire ad Alice il messaggio $M = 33$.

**Bob deve usare la chiave pubblica di Alice** $(n, e) = (35, 11)$ per cifrare.

**Cifratura:** $C = M^e \bmod n = 33^{11} \bmod 35$.

Usiamo l'esponenziazione rapida. $11 = 1011_2$:

$$33^1 = 33$$
$$33^2 = 1089 \bmod 35$$
$$1089 = 31 \cdot 35 + 4 \Rightarrow 1089 \bmod 35 = 4$$
$$33^4 = 4^2 = 16$$
$$33^8 = 16^2 = 256 \bmod 35$$
$$256 = 7 \cdot 35 + 11 \Rightarrow 256 \bmod 35 = 11$$

$11 = 8 + 2 + 1$:
$$33^{11} = 33^8 \cdot 33^2 \cdot 33^1 \bmod 35 = 11 \cdot 4 \cdot 33 \bmod 35$$

$$11 \cdot 4 = 44 \bmod 35 = 9$$
$$9 \cdot 33 = 297 \bmod 35$$
$$297 = 8 \cdot 35 + 17 \Rightarrow 297 \bmod 35 = 17$$

**Bob spedisce ad Alice:** $C = 17$.

#### Parte e — Decifratura da parte di Alice (punteggio incluso nel totale)

Alice riceve $C = 17$ e usa la sua chiave privata $d = 11$.

**Decifratura:** $M = C^d \bmod n = 17^{11} \bmod 35$.

$11 = 8 + 2 + 1 = 1011_2$:

$$17^1 = 17$$
$$17^2 = 289 \bmod 35 = 289 - 8\cdot35 = 289 - 280 = 9$$
$$17^4 = 9^2 = 81 \bmod 35 = 81 - 2\cdot35 = 11$$
$$17^8 = 11^2 = 121 \bmod 35 = 121 - 3\cdot35 = 16$$

$$17^{11} = 17^8 \cdot 17^2 \cdot 17^1 \bmod 35 = 16 \cdot 9 \cdot 17 \bmod 35$$

$$16 \cdot 9 = 144 \bmod 35 = 144 - 4\cdot35 = 4$$
$$4 \cdot 17 = 68 \bmod 35 = 68 - 35 = 33$$

**Messaggio decifrato:** $M = 33$ ✓

#### Parte f — Firma RSA di Alice su $M' = 6$ (punteggio incluso nel totale)

**Firma:** Alice vuole firmare $M' = 6$ e spedirlo a Bob.

Per firmare, Alice usa la propria **chiave privata** $(n, d) = (35, 11)$:

$$S = (M')^d \bmod n = 6^{11} \bmod 35$$

Calcolo di $6^{11} \bmod 35$:

$$6^1 = 6$$
$$6^2 = 36 \bmod 35 = 1$$
$$6^4 = 1^2 = 1$$
$$6^8 = 1^2 = 1$$

$$6^{11} = 6^8 \cdot 6^2 \cdot 6^1 = 1 \cdot 1 \cdot 6 = 6$$

Quindi la firma è $S = 6$.

> 💡 In questo caso la firma coincide con il messaggio ($S = M' = 6$) perché $6^2 \equiv 1 \pmod{35}$, quindi $6^{11} = 6^{10} \cdot 6 = (6^2)^5 \cdot 6 = 1^5 \cdot 6 = 6$. Questo non è un errore ma una coincidenza numerica dei parametri scelti.

**Alice spedisce a Bob la coppia** $(M', S) = (6, 6)$ (messaggio + firma), oppure solo la firma $S = 6$ (con il messaggio implicito o trasmesso separatamente).

**Verifica da parte di Bob:**

Bob riceve $(M', S) = (6, 6)$ e vuole verificare che la firma sia di Alice.

Bob usa la **chiave pubblica di Alice** $(n, e) = (35, 11)$:

$$V = S^e \bmod n = 6^{11} \bmod 35 = 6$$

Bob confronta $V$ con $M'$: $V = 6 = M'$ ✓

La firma è valida: il messaggio proviene da Alice (solo Alice conosce $d = 11$ che le ha permesso di calcolare $S = (M')^d$).

**Schema generale della firma RSA:**

- **Firma:** il firmatario usa la chiave **privata** per firmare: $S = (M')^d \bmod n$.
- **Verifica:** il verificatore usa la chiave **pubblica** del firmatario per verificare: $V = S^e \bmod n$; la firma è valida se $V = M'$.
- **Correttezza:** $S^e = ((M')^d)^e = (M')^{de} \equiv M' \pmod{n}$ per la correttezza di RSA.

> ⚠️ Nella firma digitale RSA, i ruoli delle chiavi sono **invertiti** rispetto alla cifratura: la chiave privata viene usata per firmare (tutti possono verificare con la chiave pubblica, solo il firmatario può generare la firma).
