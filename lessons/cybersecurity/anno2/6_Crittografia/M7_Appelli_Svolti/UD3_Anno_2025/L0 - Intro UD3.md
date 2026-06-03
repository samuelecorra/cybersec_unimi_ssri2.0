# UD3 — Appelli d'Esame Anno 2025: Introduzione

> 📌 Questa unità didattica raccoglie le soluzioni complete e commentate di tutti gli appelli d'esame di Crittografia (prof. S. Cimato, Università degli Studi di Milano) sostenuti nell'anno accademico 2024/2025.

---

## Scopo dell'unità

Gli appelli qui documentati servono a tre scopi distinti:

1. **Allenamento al formato reale**: ogni file riproduce fedelmente le domande originali e mostra una soluzione passo-passo con la stessa struttura che ci si aspetta nella risposta d'esame.
2. **Riconoscimento dei pattern**: il professore ripropone ogni anno gli stessi nuclei concettuali (Secret Sharing numerico, funzioni hash, cifrari simmetrici/asimmetrici, cifrari classici). Identificarli in anticipo permette di preparare le risposte tipo.
3. **Calibrazione del tempo**: con 4 domande in ~90 minuti, ogni domanda ha un peso preciso in punti che indica quanto tempo investire. Le soluzioni qui presentate mostrano il livello di dettaglio atteso.

---

## Tabella degli appelli 2025

| File | Data | Argomenti principali | Punteggi (D1/D2/D3/D4) | Difficoltà stimata |
|------|------|----------------------|------------------------|--------------------|
| [L1 - Appello 20 gennaio 2025.md](L1%20-%20Appello%2020%20gennaio%202025.md) | 20/01/2025 | Cifratura simmetrica (XOR+addizione), DES decifratura, Hash H(x)=5x+12 mod 20, El-Gamal | 30/20/20/30 | ★★★☆☆ |
| [L2 - Appello 10 febbraio 2025.md](L2%20-%20Appello%2010%20febbraio%202025.md) | 10/02/2025 | Vigenère + IC/MIC, Cifrario XOR+hash, Funzione identità hash, Secret Sharing (2,3) Z₁₁ | 25/35/15/25 | ★★★★☆ |
| [L3 - Appello 4 luglio 2025.md](L3%20-%20Appello%204%20luglio%202025.md) | 04/07/2025 | Cifrario Hill (cifratura+decifratura), Blowfish, Hash DES one-wayness, Secret Sharing (n,n)+(k,n)+(2,3) Z₁₉ | 25/15/30/30 | ★★★☆☆ |
| [L4 - Appello 15 luglio 2025.md](L4%20-%20Appello%2015%20luglio%202025.md) | 15/07/2025 | Cifrari a sostituzione binaria, Hash H(x)=2x+8 mod 10, RSA + Double-RSA, Secret Sharing (3,3)+(2,3) Z₇ | 30/20/20/30 | ★★★☆☆ |
| [L5 - Appello 5 settembre 2025.md](L5%20-%20Appello%205%20settembre%202025.md) | 05/09/2025 | Cifrario affine (recupero chiavi), Double DES + meet-in-the-middle, MAC personalizzato AES+hash, Secret Sharing (4,4)+(2,3) Z₁₁ | 25/20/30/25 | ★★★★☆ |
| [L6 - Appello 19 settembre 2025.md](L6%20-%20Appello%2019%20settembre%202025.md) | 19/09/2025 | Classificazione attacchi + resistenza per cifrario, DES doppio meet-in-the-middle + AES-X KPT, Firma DSS, Secret Sharing (2,3) Z₁₁ | 25/40/15/20 | ★★★★★ |

---

## Pattern ricorrenti nel 2025

### Argomenti presenti in tutti o quasi tutti gli appelli

| Argomento | Frequenza | Appelli |
|-----------|-----------|---------|
| **Secret Sharing di Shamir** | 6/6 | L1, L2, L3, L4, L5, L6 |
| **Funzioni Hash (proprietà o attacchi)** | 5/6 | L1, L2, L3, L4, L5 |
| **Cifratura simmetrica (DES/AES/varianti)** | 5/6 | L1, L2, L3, L5, L6 |
| **Cifrari classici** | 4/6 | L2, L3, L4, L5 |
| **Crittografia asimmetrica** | 3/6 | L1, L4, L6 |

### Osservazioni critiche per la preparazione

> ⚠️ **Secret Sharing è costante**: in tutti e 6 gli appelli del 2025 è presente almeno una domanda di ricostruzione numerica del segreto con interpolazione di Lagrange in $\mathbb{Z}_p$. Va padroneggiato senza incertezze, sia lo schema $(k,n)$ sia lo schema $(n,n)$.

> ⚠️ **Le funzioni hash "giocattolo"**: il professore propone spesso funzioni hash lineari (es. $H(x) = 5x + 12 \bmod 20$ o $H(x) = 2x + 8 \bmod 10$) per far dimostrare la mancanza di second preimage resistance. Il trucco è sempre lo stesso: si inverte la funzione e si trova una preimmagine diversa.

> 💡 **La domanda teorica sul DES**: la proprietà di simmetria decifratura/cifratura del DES con chiavi invertite è comparsa in due forme diverse (dimostrazione formale in L1, attacco meet-in-the-middle in L5 e L6). Vale la pena studiare entrambe le presentazioni.

> 💡 **El-Gamal e curve ellittiche**: compaiono meno frequentemente ma con calcoli numerici espliciti. Se compaiono in una domanda da 30 punti (come in L1), è fondamentale saper eseguire le esponenziazioni modulari a mano.

> 📌 **Struttura dell'esame**: 4 domande, totale 100 punti. La distribuzione dei punteggi varia (mai uniforme 25+25+25+25), il che riflette la difficoltà relativa delle domande. Pianificare il tempo proporzionalmente ai punti.

---

## Come usare queste soluzioni per studiare

1. **Prima lettura**: leggi solo le tracce originali di un appello e prova a rispondere da solo su carta.
2. **Confronto**: apri la soluzione e verifica sia la correttezza dei calcoli sia il livello di dettaglio espositivo.
3. **Pattern recognition**: dopo aver fatto 2-3 appelli, identifica quali domande si ripetono e costruisci una "risposta tipo" per ciascun argomento frequente.
4. **Approfondimento**: ogni soluzione linka alle lezioni teoriche dei moduli M1-M6 dove trovare la trattazione estesa del concetto.
