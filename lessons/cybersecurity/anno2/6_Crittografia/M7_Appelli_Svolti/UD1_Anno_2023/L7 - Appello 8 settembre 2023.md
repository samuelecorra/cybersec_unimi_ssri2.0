# Appello dell'8 settembre 2023 — Soluzione completa

> 📌 **Informazioni appello:** 08/09/2023, docente S. Cimato, punteggio totale 100/100, 4 domande (25 + 30 + 15 + 30).

> ⚠️ **Questo appello ha testo identico a quello del [7 settembre 2023](L6%20-%20Appello%207%20settembre%202023.md)**: stesse quattro domande con gli stessi parametri (Playfair con chiave "welcome back"; modalità OFB con keystream riusato; funzioni MAC e HMAC; secret sharing $(3,3)$ e $(2,3)$ in $\mathbb{Z}_{11}$). Le sessioni del 7 e dell'8 settembre 2023 hanno proposto la medesima prova.

Per la **soluzione completa passo-passo** fai riferimento a **[L6 — Appello 7 settembre 2023](L6%20-%20Appello%207%20settembre%202023.md)**. In sintesi:

1. **Playfair (25 pt)** — matrice $5\times5$ da "welcome back"; "pilotare" → **SFCWRDQL**, "gbtzlthdsn" → **bentornati**.
2. **Cifratura simmetrica / OFB (30 pt)** — riuso del keystream (two-time-pad): $c_t \oplus c'_t = m_t \oplus m_{t+1}$; per induzione da $w_i = c_i \oplus m_i$ si deducono tutte le $w_t$ e gli $m_t$. Mai riusare IV/keystream.
3. **Funzioni MAC (15 pt)** — caratteristiche/applicazioni dei MAC + HMAC ($H((K'\oplus\text{opad})\,\|\,H((K'\oplus\text{ipad})\,\|\,m))$, resistenza al length-extension).
4. **Secret Sharing (30 pt)** — $(3,3)$ additivo $S=1$; $(2,3)$ con Lagrange $S=2$.

> 💡 Gli esercizi di questo appello sono distribuiti nelle unità "Esercizi_Appelli" per modulo a partire dalla versione del 07/09/2023 (per evitare duplicati).
