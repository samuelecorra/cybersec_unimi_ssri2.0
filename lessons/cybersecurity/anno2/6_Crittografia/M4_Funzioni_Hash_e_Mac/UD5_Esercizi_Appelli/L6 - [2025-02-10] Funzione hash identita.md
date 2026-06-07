# Funzione hash identita — Esercizio d'esame

> 🗂️ **Fonte:** appello del **10 febbraio 2025**, Domanda 3 — Modulo M4 (Funzioni Hash e MAC).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 10 febbraio 2025](../../M7_Appelli_Svolti/UD3_Anno_2025/L2%20-%20Appello%2010%20febbraio%202025.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 3 — Funzione hash identità (15 punti)

### Traccia originale

> **(15 punti) Funzioni hash.**
>
> a. (15 punti) Si consideri come funzione hash, la funzione identità $I: \{0,1\}^{128} \to \{0,1\}^{128}$ e se ne discutano le proprietà.

### Prerequisiti teorici

- [`../../M4_Funzioni_Hash_e_Mac/UD1/L2 - Proprietà.md`](../../M4_Funzioni_Hash_e_Mac/UD1/L2%20-%20Proprietà.md) — le tre proprietà di sicurezza

### Soluzione dettagliata

La funzione identità $I: \{0,1\}^{128} \to \{0,1\}^{128}$ è definita da $I(x) = x$ per ogni $x \in \{0,1\}^{128}$.

**Analisi delle proprietà crittografiche:**

**1. Resistenza alla preimmagine (One-wayness):**

Data $h \in \{0,1\}^{128}$, dobbiamo trovare $x$ tale che $I(x) = h$, cioè $x = h$.

La risposta è banale: la preimmagine di $h$ è $h$ stesso. Trovare la preimmagine richiede **zero** sforzo computazionale.

**Conclusione:** $I$ NON ha resistenza alla preimmagine. È completamente invertibile (è la funzione inversa di se stessa).

**2. Resistenza debole alle collisioni (Second preimage resistance):**

Dato $x$, dobbiamo trovare $x' \neq x$ tale che $I(x') = I(x)$, cioè $x' = x$.

Ma l'unica soluzione è $x' = x$, che viene esclusa dalla condizione $x' \neq x$. Quindi non esiste nessun $x' \neq x$ con $I(x') = I(x)$.

**Conclusione:** $I$ HA resistenza debole alle collisioni (second preimage resistance), poiché $I$ è iniettiva (su input distinti produce output distinti).

**3. Resistenza forte alle collisioni (Collision resistance):**

Dobbiamo trovare una coppia $(x, x')$ con $x \neq x'$ tale che $I(x) = I(x')$, cioè $x = x'$.

Ma ciò contraddice $x \neq x'$. Quindi non esistono collisioni.

**Conclusione:** $I$ HA resistenza forte alle collisioni.

**Sintesi delle proprietà di sicurezza:**

| Proprietà | $I(x) = x$ | Motivazione |
|-----------|-----------|-------------|
| One-wayness | ✗ NO | La preimmagine di $h$ è $h$ stesso, trovabile in O(1) |
| Second preimage resistance | ✓ SÌ | $I$ è iniettiva: $I(x) = I(x') \Rightarrow x = x'$ |
| Collision resistance | ✓ SÌ | Stessa ragione: $I$ è iniettiva |

**Commenti aggiuntivi:**

Questa analisi sembra controintuitiva: $I$ non è one-way ma ha resistenza alle collisioni. Questo è possibile perché le tre proprietà non sono equivalenti e non esiste una gerarchia unidirezionale totale. In particolare:

> ⚠️ La funzione identità NON è una funzione hash crittografica nel senso pratico, per due ragioni fondamentali: (1) manca di one-wayness, quindi non protegge il preimage (es. nascondere una password), e (2) manca di compressione (l'output ha la stessa lunghezza dell'input, quindi non può essere usata come hash di messaggi di lunghezza arbitraria).

> 💡 Nella definizione formale, una funzione hash crittografica dovrebbe essere definita su $\{0,1\}^*$ (input di lunghezza arbitraria) e produrre output di lunghezza fissa $n$ con $n$ molto minore della lunghezza dell'input. La funzione $I: \{0,1\}^{128} \to \{0,1\}^{128}$ non soddisfa il requisito di compressione.
