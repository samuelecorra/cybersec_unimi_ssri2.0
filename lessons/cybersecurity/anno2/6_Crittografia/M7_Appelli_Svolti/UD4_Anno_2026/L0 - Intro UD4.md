# UD4 — Appelli d'Esame Anno 2026: Introduzione

> 📌 Questa unità didattica raccoglie le soluzioni complete e commentate di tutti gli appelli d'esame di Crittografia (prof. S. Cimato, Università degli Studi di Milano) sostenuti nell'anno accademico 2025/2026.

---

## Scopo dell'unità

Gli appelli qui documentati servono a tre scopi distinti:

1. **Allenamento al formato reale**: ogni file riproduce fedelmente le domande originali e mostra una soluzione passo-passo con la stessa struttura che ci si aspetta nella risposta d'esame.
2. **Riconoscimento dei pattern**: il professore ripropone ogni anno gli stessi nuclei concettuali (Secret Sharing numerico, funzioni hash, cifrari simmetrici/asimmetrici, cifrari classici). Identificarli in anticipo permette di preparare le risposte tipo.
3. **Calibrazione del tempo**: con 4 domande in ~90 minuti, ogni domanda ha un peso preciso in punti che indica quanto tempo investire. Le soluzioni qui presentate mostrano il livello di dettaglio atteso.

---

## Tabella degli appelli 2026

| File | Data | Argomenti principali | Punteggi (D1/D2/D3/D4) | Difficoltà stimata |
|------|------|----------------------|------------------------|--------------------|
| [L1 - Appello 8 gennaio 2026.md](L1%20-%20Appello%208%20gennaio%202026.md) | 08/01/2026 | Vigenère IC e MIC, Cifrario a blocchi XOR+hash, Firma DSS, Secret Sharing (2,3) Z₁₁ | 25/35/15/25 | ★★★★☆ |
| [L2 - Appello 24 febbraio 2026.md](L2%20-%20Appello%2024%20febbraio%202026.md) | 24/02/2026 | Sostituzione binaria con XOR, Funzioni hash one-wayness AES, El Gamal numerico, Secret Sharing (2,3) Z₁₁ | 30/30/20/20 | ★★★☆☆ |
| [L3 - Appello 21 marzo 2026.md](L3%20-%20Appello%2021%20marzo%202026.md) | 21/03/2026 | Sostituzione a blocchi (cifrario ottale), Feistel 2 round con complemento, Hash one-wayness DES, Secret Sharing (2,3) Z₁₁ segreto s=7 | 20/35/30/15 | ★★★★☆ |

---

## Pattern ricorrenti nel 2026

### Argomenti presenti in tutti o quasi tutti gli appelli

| Argomento | Frequenza | Appelli |
|-----------|-----------|---------|
| **Secret Sharing di Shamir** | 3/3 | L1, L2, L3 |
| **Funzioni Hash (proprietà o attacchi)** | 3/3 | L1, L2, L3 |
| **Cifratura simmetrica (varianti/Feistel)** | 3/3 | L1, L2, L3 |
| **Cifrari classici** | 2/3 | L1, L2 |
| **Crittografia asimmetrica** | 1/3 | L2 |

### Osservazioni critiche per la preparazione

> ⚠️ **Secret Sharing è ancora costante**: in tutti e 3 gli appelli 2026 è presente la ricostruzione numerica in $\mathbb{Z}_{11}$ con interpolazione di Lagrange. In L1: $(1,3),(2,4)$ → $s=2$; in L2: $(1,5),(2,7)$ → $s=3$; in L3: schema $(2,3)$ con $s=7$ dato, chiede di costruire un esempio.

> ⚠️ **L'attacco a compleanno e le implicazioni sulle funzioni hash** è presente in L2 e L3: vale la pena avere una risposta tipo pronta. La domanda viene abbinata ogni volta all'analisi di one-wayness di $H(x) = \text{AES}_k(x)$ o $H(x) = \text{DES}_k(x)$, distinguendo il caso $k$ nota dal caso $k$ non nota.

> 💡 **Vigenère e indici IC/MIC**: il professore continua a proporre il calcolo numerico. In L1 la parola è "massaggiatore" (IC) e le coppie "tetto"/"gessetto" (MIC). Tenersi pronti a contare le occorrenze e applicare la formula direttamente.

> 💡 **Feistel con funzione non standard**: in L3 la funzione round è $f(x,K)=K \oplus \bar{x}$. Il pattern di Feistel rimane identico; cambia solo la funzione $f$. La dimostrazione di correttezza della decifratura segue sempre lo stesso schema.

> 📌 **Struttura dell'esame**: 4 domande, totale 100 punti. La distribuzione dei punteggi varia significativamente: D2 è sempre la più pesante (35 punti in L1 e L3), seguita da D1 (25-30).

---

## Come usare queste soluzioni per studiare

1. **Prima lettura**: leggi solo le tracce originali di un appello e prova a rispondere da solo su carta.
2. **Confronto**: apri la soluzione e verifica sia la correttezza dei calcoli sia il livello di dettaglio espositivo.
3. **Pattern recognition**: dopo aver fatto 2-3 appelli, identifica quali domande si ripetono e costruisci una "risposta tipo" per ciascun argomento frequente.
4. **Approfondimento**: ogni soluzione linka alle lezioni teoriche dei moduli M1-M6 dove trovare la trattazione estesa del concetto.
