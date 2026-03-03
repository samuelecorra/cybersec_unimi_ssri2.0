# **Lezione 4: MCC e MCDC**

### 1. Introduzione ai criteri avanzati di copertura delle condizioni

Dopo aver analizzato i criteri di copertura delle istruzioni, dei branch, delle decisioni, delle condizioni e delle decisioni/condizioni, vengono introdotti criteri più avanzati che permettono di testare in modo ancora più approfondito le condizioni atomiche presenti nelle decisioni.

In particolare vengono studiati:

1. **Multiple Condition Coverage (MCC)**;
    
2. **Modified Condition/Decision Coverage (MCDC)**.
    

Questi criteri nascono dall’esigenza di migliorare la capacità di individuare difetti nelle espressioni booleane complesse, che rappresentano spesso punti critici nei sistemi software, soprattutto nei sistemi di sicurezza e nei sistemi critici.

---

### 2. Multiple Condition Coverage (MCC)

Il criterio di **Multiple Condition Coverage** richiede che vengano testate tutte le possibili combinazioni dei valori di verità delle condizioni atomiche presenti in una decisione.

Se una decisione contiene $n$ condizioni atomiche, il numero totale di combinazioni possibili è:

$$  
2^n  
$$

poiché ogni condizione può assumere valore vero o falso.

Un test set soddisfa il criterio MCC se verifica ogni possibile combinazione dei valori di verità delle condizioni atomiche in ogni decisione.

Per rappresentare queste combinazioni si utilizzano generalmente tabelle di verità. Tuttavia, il numero effettivo di casi può ridursi grazie alla valutazione a corto circuito, che evita la valutazione di alcune condizioni quando il risultato della decisione è già determinato.

---

### 3. Limiti pratici del Multiple Condition Coverage

Il principale limite del criterio MCC è la crescita esponenziale del numero di test necessari al crescere del numero di condizioni.

Se il numero di condizioni è elevato, il criterio diventa rapidamente impraticabile dal punto di vista economico e temporale. Per questo motivo, pur essendo teoricamente molto forte, MCC non viene spesso utilizzato nella pratica su sistemi complessi.

---

### 4. Modified Condition/Decision Coverage (MCDC)

Per superare i limiti dell’MCC è stato introdotto il criterio di **Modified Condition/Decision Coverage (MCDC)**, che mantiene una forte capacità di individuazione dei difetti ma richiede un numero di test che cresce linearmente con il numero delle condizioni.

Il criterio MCDC ha avuto grande successo industriale ed è stato adottato come standard in contesti critici, ad esempio nel software aeronautico richiesto dalla Federal Aviation Administration (FAA).

Il principio fondamentale dell’MCDC è che:

> ogni condizione atomica deve essere dimostrata capace di influenzare in modo indipendente il risultato finale della decisione.

Formalmente, per ogni condizione atomica $C$ devono esistere due casi di test tali che:

- tutte le altre condizioni rimangono invariate;
    
- la condizione $C$ cambia valore;
    
- il valore finale della decisione cambia.
    

---

### 5. Esempi di MCDC con operatori logici

Si consideri la decisione:

$$  
Dec = A \land B  
$$

Per verificare l’influenza indipendente di $A$ si mantiente $B$ vero e si varia $A$:

- $A=true, B=true$ → decisione vera;
    
- $A=false, B=true$ → decisione falsa.
    

Per verificare l’influenza indipendente di $B$ si mantiene $A$ vero e si varia $B$:

- $A=true, B=true$ → decisione vera;
    
- $A=true, B=false$ → decisione falsa.
    

In totale sono sufficienti tre casi di test:

$$  
{(A,B), (\neg A,B), (A,\neg B)}  
$$

Analogamente, per una decisione:

$$  
Dec = A \lor B  
$$

sono sufficienti tre casi di test:

$$  
{(\neg A,\neg B), (A,\neg B), (\neg A,B)}  
$$

Questo dimostra che il numero di test cresce linearmente con il numero delle condizioni.

---

### 6. Esempio con decisione complessa

Considerando una decisione reale come:

```
if Reset = on and 
   (Pressure = TooLow or Pressure = Normal) then ...
```

per soddisfare il criterio MCDC è necessario costruire casi di test in cui ogni condizione atomica (Reset, Pressure TooLow, Pressure Normal) venga variata indipendentemente mantenendo costanti le altre, verificando che il risultato finale della decisione cambi.

Questo richiede una progettazione attenta dei casi di test per isolare l’effetto di ciascuna condizione.

---

### 7. Altri criteri per espressioni booleane

Oltre a MCC e MCDC esistono altri criteri proposti in letteratura, tra cui:

- **Full Predicate Coverage**;
    
- **Missing Condition Coverage**.
    

Quest’ultimo criterio è particolarmente interessante perché permette di individuare se una condizione atomica è stata accidentalmente omessa in una decisione.

Ad esempio, considerando:

```
if (x > 3 && switchOn)
```

scegliendo valori in cui `switchOn` è vero e `x <= 3` si può verificare se la condizione `x > 3` è realmente presente nella decisione. Se invece `switchOn` fosse falso, non sarebbe possibile rilevare l’omissione.

---

### 8. Importanza dei criteri avanzati nella sicurezza

I criteri MCC e MCDC risultano particolarmente importanti nei sistemi critici e nei sistemi di sicurezza perché consentono di analizzare in profondità le condizioni logiche che controllano il comportamento del sistema.

Errori nelle condizioni booleane possono infatti generare vulnerabilità gravi, specialmente quando riguardano controlli di sicurezza, autorizzazioni o condizioni di emergenza.

---

### Sintesi della lezione

In questa lezione sono stati introdotti criteri avanzati di copertura delle condizioni nelle decisioni. Il Multiple Condition Coverage richiede la verifica di tutte le combinazioni possibili delle condizioni atomiche, ma risulta spesso impraticabile a causa della crescita esponenziale del numero di test.

Il Modified Condition/Decision Coverage rappresenta un compromesso efficace, richiedendo che ogni condizione dimostri la propria influenza indipendente sul risultato finale della decisione. Questo criterio è ampiamente utilizzato nei sistemi critici ed è considerato uno standard industriale.

Sono stati infine menzionati altri criteri per il testing delle espressioni booleane e discussa l’importanza di questi approcci nel contesto della sicurezza del software.