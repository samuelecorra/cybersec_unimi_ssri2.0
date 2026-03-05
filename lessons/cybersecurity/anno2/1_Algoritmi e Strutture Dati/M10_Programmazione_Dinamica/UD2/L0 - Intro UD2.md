# **Lezione 0 - Intro Unit? Didattica 2 - – String matching approssimato**


Lo **string matching approssimato** chiede di trovare nel testo $T$ tutte le posizioni in cui il pattern $P$ compare **con al più $k$ errori** (inserimenti, cancellazioni o sostituzioni). È fondamentale in **bioinformatica** (allineamento DNA/RNA), **correttori ortografici**, **ricerca fuzzy** su log/testi e **motori di ricerca** tolleranti al rumore.

### **Idea chiave (PD)**

Si usa **programmazione dinamica** per calcolare la **distanza di edit (Levenshtein)** tra prefissi di $P$ e $T$.  
La tabella $D[i,j]$ memorizza il **costo minimo** per trasformare il prefisso $P[1..i]$ nel prefisso $T[1..j]$ tramite:

- **sostituzione** (o match se i caratteri coincidono),
    
- **inserimento** (carattere in $T$),
    
- **cancellazione** (carattere in $P$).
    

Confrontando i valori dell’ultima riga $D[m,j]$ (o l’intera tabella, a seconda della variante), individuiamo tutte le posizioni del testo dove l’**edit distance** è $\le k$.

### **Algoritmo (passo passo)**

1. **Inizializza** la tabella $D$ sulle prime riga/colonna (trasformare stringa vuota in prefissi e viceversa).
    
2. **Riempi** $D[i,j]$ scegliendo il **minimo** tra le tre mosse (match/sostituzione, inserimento, cancellazione).
    
3. **Rileva i match approssimati**: dove il costo $\le k$ (tipicamente nella riga $i=m$).
    
4. **(Opzionale)** Ricostruisci il cammino per vedere le operazioni esatte.
    

### **Complessità**

La versione base ha **tempo $O(nm)$** e **spazio $O(nm)$** (riducibile a **$O(\min{n,m})$** se serve solo il costo, non il traceback). Varianti ottimizzate (es. con **fascia $k$**) scendono a **$O(k \cdot \max{n,m})$** quando $k$ è piccolo rispetto a $n,m$.

Questa unità ti porterà dalla **formulazione del problema**, alla **regola dinamica** che regge l’algoritmo, fino all’**esecuzione guidata** e all’**analisi della complessità**.

---

