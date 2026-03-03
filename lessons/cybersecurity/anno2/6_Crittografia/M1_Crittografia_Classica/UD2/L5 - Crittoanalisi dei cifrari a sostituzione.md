# **Lezione 5: Crittoanalisi dei cifrari a sostituzione**

---

### **1. Premessa: caratteristiche dei cifrari a sostituzione monoalfabetica**

I **cifrari a sostituzione monoalfabetica** sostituiscono ogni lettera del messaggio in chiaro con **sempre la stessa lettera** dell’alfabeto cifrato.

- Esempio: una vocale può essere mappata **sempre nello stesso simbolo**.
    
- Le **caratteristiche statistiche della lingua originale** (frequenze, coppie di lettere, parole comuni) **si conservano** nel messaggio cifrato.
    

➡️ È proprio questo il loro **punto debole**: la **frequenza delle lettere** nel testo cifrato riflette quella della lingua usata.

---

### **2. Principio della crittoanalisi classica**

La **crittoanalisi** dei cifrari a sostituzione si basa sullo **studio statistico delle frequenze** dei simboli.

Ogni lingua presenta **distribuzioni caratteristiche**:

- in italiano: E, A, I, O, N, R, T, L sono più frequenti;
    
- in inglese: E, T, A, O, I, N, S, H, R sono le più comuni.
    

Analizzando la frequenza delle lettere nel testo cifrato, si può ipotizzare **quale sostituzione** corrisponda a ciascun simbolo.

---

### **3. Esempio di analisi su un cifrario a shift**

Nel **cifrario a shift**, ogni lettera è traslata di un numero fisso di posizioni.  
Il crittoanalista può:

1. Calcolare la frequenza di ciascuna lettera del testo cifrato.
    
2. Confrontarla con la distribuzione statistica della lingua di riferimento.
    
3. Verificare per quale _shift_ i due grafici coincidono meglio.
    

Se ad esempio il picco di frequenza nel testo cifrato si trova su “J” e sappiamo che in inglese la lettera più comune è “E”, possiamo ipotizzare che:

$$  
J \rightarrow E \Rightarrow k = (J - E) \mod 26  
$$

così da dedurre la chiave di cifratura.

---

### **4. Distribuzioni di frequenza (esempio pratico)**

Nel caso del cifrario a shift, le frequenze delle lettere nel testo cifrato possono apparire ad esempio così:

![](imgs/Pasted%20image%2020260221154147.png)

Il grafico della frequenza mostra **picchi ripetuti e regolari**, tipici di un semplice scorrimento dell’alfabeto.

---

Mettiamo a paragone il grafico di un testo cifrato con quello sottostante che mostra le frequenze delle lettere in un testo in italiano:

![](imgs/Pasted%20image%2020260221154439.png)

Non è difficile andare poi a confermare che la sostituzione individuata è:

A diventa F; B diventa G; C diventa H etc...

---

### **5. Analisi di un cifrario a sostituzione**

In un cifrario a sostituzione generico (non a semplice shift), la frequenza delle lettere non è traslata, ma **rimescolata**.

Ecco un esempio di testo cifrato:

```
uzqso vuohx mopvg pozpe vsgzw szopf pesxu dbmet sxaiz
vueph zhndz shzow sfpap pdtsv pquzw ymxuz uhsxe pyepo
pdzsz ufpom bzwpf upzhm djudt mohmq
```

Ma chiaramente, questo a noi crittanalisti poco cambia, perché possiamo ricorrere comunque alla solita strategia:
L’analisi di frequenza produce risultati come questi:

![](imgs/Pasted%20image%2020260221154819.png)

Confrontando questi valori con la lingua inglese, si possono proporre **ipotesi di sostituzione** (mapping) da verificare.

---

### **6. Procedura di crittoanalisi**

Il crittoanalista segue i seguenti **passaggi sistematici**:

1. **Calcola** la distribuzione di frequenza del testo cifrato.
    
2. **Individua** le lettere più frequenti (es. E, T, A, O, N, I in inglese).
    
3. **Prova dei mapping** tra lettere cifrate e lettere in chiaro.
    
4. **Imposta sistemi di equazioni** per verificare le ipotesi.
    
5. **Decifra parzialmente** il testo e controlla se le parole risultano sensate.
    
6. Se il messaggio non ha senso, **ripete** con nuovi mapping.
    

---

### **7. Esempio di decifrazione progressiva**

Testo cifrato:

```
uzqso vuohx mopvg pozpe vsgzw szopf pesxu dbmet sxaiz
vueph zhndz shzow sfpap pdtsv pquzw ymxuz uhsxe pyepo
pdzsz ufpom bzwpf upzhm djudt mohmq
```

L'alfabeto inglese ha come lettere più frequenti la 't' e la 'e'. Allora sostituiamole. Ma poi sappiamo su base statistica che la t in lingua inglese è spesso seguita dalla h, quindi abbiamo una pista da seguire...

![](imgs/Pasted%20image%2020260221155127.png)

Dopo alcune iterazioni e correzioni, il testo inizia a rivelarsi come:

> **“It was disclosed yesterday that several informal but direct contacts have been made with political representatives of the Viet Cong in Moscow.”**

---

### **8. Crittoanalisi del cifrario affine**

Per un **cifrario affine**, il crittoanalista adotta un approccio più matematico:

1. Si calcolano le **lettere più frequenti** nel testo cifrato.
    
2. Si ipotizza a quali lettere del linguaggio possano corrispondere.
    
3. Si impostano **due equazioni** del tipo:  
    $$  
    c_1 = (k_1 p_1 + k_2) \mod 26
    $$
    $$  
    c_2 = (k_1 p_2 + k_2) \mod 26  
    $$
    
4. Si risolve il sistema per $k_1$ e $k_2$.
    
5. Si prova la decifratura: se il risultato ha senso, la chiave è corretta.
    

---

### **9. Il cifrario a blocco monouso (One-Time Pad)**

Il **One-Time Pad (OTP)** è un sistema crittografico perfetto, ideato da **Gilbert Vernam (AT&T, 1917)**.
Si basa su una **chiave casuale** lunga **quanto il messaggio**.

Prima di vedere la definizione formale nel mondo dei bit, soffermiamoci su un esempio ancora alfabetico.

Supponiamo che un crittanalista abbia in suo possesso il cyphertext qui presentato.

Se dovesse miracolosamente trovare due chiavi casuali come quelle indicate, non avrebbe dati oggettivi per poter stabilire quale delle due sia più plausibile!

|**plain**|**key**|**cyphertext**|
|---|---|---|
|attaccarelavalleall’alba|PLMOEZQK JZL RTEAVCRCBYNN|PEF OGBQBNKLM TPLZCCNBJON|

| **plain**                | **key**                 | **cyphertext**           |
| ------------------------ | ----------------------- | ------------------------ |
| abbandonarevalleall’alba | PDEOTYCONTHRTEAVCRCBYNN | PEF OGBQBNKLMTPLZCCNBJON |

---

Ora entriamo nel merito dei bit...

![](imgs/Pasted%20image%2020260221161544.png)

$$  
C_i = M_i \oplus K_i  
$$

dove:

- $M_i$ = i-esimo bit del messaggio in chiaro,
    
- $K_i$ = i-esimo bit della chiave casuale,
    
- $C_i$ = i-esimo bit del messaggio cifrato.

La chiave deve essere **completamente casuale** e **mai riutilizzata**.

---

### **10. Sicurezza del One-Time Pad**

- **Indipendentemente** dal tempo o dalle risorse disponibili, è **impossibile** decifrare il testo cifrato senza la chiave.
    
- È l’unico sistema **unconditionally secure** (sicuro in senso assoluto).
    
- La sicurezza deriva dal fatto che **messaggio e testo cifrato sono statisticamente indipendenti**:

$$  
P(M|C) = P(M)  
$$

Questo significa che **osservare il cifrato non fornisce alcuna informazione** sul messaggio in chiaro.

---

### **11. Storia e applicazioni**

Il One-Time Pad è stato impiegato in contesti reali ad altissima sicurezza:

- **Spie russe** durante la Guerra Fredda,
    
- **Linea rossa Washington–Mosca**,
    
- **Che Guevara** per comunicare con **Fidel Castro** (cifrari OTP scritti in spagnolo).
    

Scoperto nel 1969, questi messaggi erano indecifrabili perché basati su una **relazione fissa lettera-numero** e chiavi uniche per ogni trasmissione.

---

### **12. Condizioni di sicurezza perfetta**

Un sistema è **perfettamente sicuro** se:

1. La **chiave è lunga quanto il messaggio**.
    
2. La **chiave è casuale** e usata **una sola volta**.
    
3. Ogni possibile messaggio è **equiprobabile** dato il cifrato.
    

In simboli:

![](imgs/Pasted%20image%2020260221163229.png)

$$  
P(M = m | C = c) = P(M = m)  
$$

---

### **13. In sintesi**

- I **cifrari a sostituzione** conservano la struttura statistica del linguaggio → vulnerabili ad analisi di frequenza.
    
- Il **One-Time Pad** è l’unico sistema **incondizionatamente sicuro**, ma poco pratico.
    
- Nella **crittografia moderna**, la sicurezza si basa su **modelli computazionali** e **chiavi finite ma sufficientemente lunghe**.
    

💡 _Conclusione:_ la crittografia classica ci ha mostrato come dalla pura linguistica e statistica si sia passati alla **matematizzazione della sicurezza** — il passo decisivo verso la crittografia moderna.