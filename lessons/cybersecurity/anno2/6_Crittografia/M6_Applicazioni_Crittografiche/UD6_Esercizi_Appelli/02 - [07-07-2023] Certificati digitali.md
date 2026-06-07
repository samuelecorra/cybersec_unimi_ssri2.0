# Certificati digitali — Esercizio d'esame

> 🗂️ **Fonte:** appello del **7 luglio 2023**, Domanda 3 — Modulo M6 (Applicazioni Crittografiche).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del 7 luglio 2023](../../M7_Appelli_Svolti/UD1_Anno_2023/L4%20-%20Appello%207%20luglio%202023.md). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

## Domanda 3 — Certificati digitali (25 punti)

### Traccia originale

> **(25 punti) Certificati digitali.**
>
> a. (10) Illustrare la funzione di un certificato digitale e il ruolo di una autorità di certificazione.
>
> b. (15) Esempio di come Alice e Bob possano usare un certificato rilasciato da una stessa CA.

### Prerequisiti teorici

- [`../../M6_Applicazioni_Crittografiche/UD2/L2 - Certificati – Creazione, scambio e revoca.md`](../../M6_Applicazioni_Crittografiche/UD2/L2%20-%20Certificati%20–%20Creazione,%20scambio%20e%20revoca.md) — certificati e revoca
- [`../../M6_Applicazioni_Crittografiche/UD2/L4 - Autorità di certificazione.md`](../../M6_Applicazioni_Crittografiche/UD2/L4%20-%20Autorità%20di%20certificazione.md) — ruolo della CA
- [`../../M6_Applicazioni_Crittografiche/UD2/L3 - Standard X.509.md`](../../M6_Applicazioni_Crittografiche/UD2/L3%20-%20Standard%20X.509.md) — formato X.509

### Soluzione dettagliata

#### Parte a — Funzione del certificato e ruolo della CA (10 punti)

Un **certificato digitale** lega in modo verificabile un'**identità** (es. "Alice", un dominio) a una **chiave pubblica**. Risolve il problema fondamentale della crittografia a chiave pubblica: come fidarsi che una chiave pubblica appartenga davvero a chi dice di esserne il proprietario (evitando attacchi man-in-the-middle in cui un avversario spaccia la propria chiave per quella di Alice).

Un certificato (formato **X.509**) contiene tipicamente: identità del soggetto, sua chiave pubblica, identità dell'emittente, periodo di validità, numero di serie, algoritmi, e soprattutto la **firma digitale della CA** su tutti questi campi.

L'**Autorità di Certificazione (CA)** è una terza parte fidata che: (1) **verifica l'identità** del richiedente; (2) **emette** il certificato firmandolo con la propria chiave privata; (3) **pubblica e revoca** i certificati (CRL / OCSP). Chiunque conosca la chiave pubblica della CA (preinstallata come *root* fidata) può verificarne la firma e quindi fidarsi del legame identità↔chiave.

#### Parte b — Esempio: Alice e Bob con la stessa CA (15 punti)

Premessa: Alice e Bob possiedono già, in modo affidabile, la **chiave pubblica della CA** $PU_{CA}$ (è una root fidata).

1. **Registrazione.** Alice genera $(PU_A, PR_A)$ e prova la propria identità alla CA. La CA crea il certificato $\text{Cert}_A = \{\text{"Alice"}, PU_A, \text{validità}, \ldots\}$ e lo firma: $\text{Sig}_{CA} = \text{Sign}_{PR_{CA}}(\text{Cert}_A)$. Analogamente per Bob ($\text{Cert}_B$).
2. **Scambio.** Quando Alice vuole comunicare con Bob, gli invia $\text{Cert}_A$ (e viceversa); i certificati non sono segreti.
3. **Verifica.** Bob verifica la firma della CA su $\text{Cert}_A$ usando $PU_{CA}$: se valida (e il certificato non è scaduto né revocato), Bob è certo che $PU_A$ appartiene davvero ad Alice. Alice fa lo stesso con $\text{Cert}_B$.
4. **Uso.** Ora possono comunicare in sicurezza: ad esempio Bob cifra una chiave di sessione con $PU_A$ (solo Alice la decifra con $PR_A$), oppure si autenticano firmando un nonce. Un man-in-the-middle non può sostituire le chiavi perché non possiede $PR_{CA}$ per firmare un certificato falso.

> 💡 Avere la **stessa CA** semplifica: basta un'unica chiave radice fidata per validare entrambi. Con CA diverse serve una **catena di certificati** fino a una root comune.
