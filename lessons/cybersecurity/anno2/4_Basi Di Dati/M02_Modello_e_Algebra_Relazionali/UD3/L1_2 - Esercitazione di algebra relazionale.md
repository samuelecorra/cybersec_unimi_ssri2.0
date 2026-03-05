# **M2 UD3 Lezione 1 - Esercitazione di algebra relazionale - Parte II**

In questa seconda esercitazione vengono applicati i principi dell’algebra relazionale a un nuovo contesto: la gestione dei dati di un insieme di **emittenti radiofoniche** e dei loro **programmi trasmessi**.  
L’obiettivo è imparare a formulare query complesse utilizzando in modo combinato **operatori insiemistici, join, proiezioni, selezioni e ridenominazioni**, con particolare attenzione ai casi di **condizioni multiple**, **cardinalità**, e **differenze tra insiemi**.

Le relazioni di riferimento sono le seguenti:

- **RADIO(Codice, Nome, Frequenza, Luogo)**
    
    - Ogni radio è identificata da un codice univoco e descritta dal nome, dalla frequenza e dal luogo di trasmissione.
    
- **PROGRAMMA(Codice, Nome, Conduttore, FasciaOraria, Durata, Tipo, CodiceRadio)**
    
    - Ogni programma è identificato da un codice e contiene informazioni su nome, conduttore, fascia oraria, durata, tipologia (_musicale_, _quiz_, _talk show_, ecc.) e sulla radio a cui appartiene tramite l’attributo **CodiceRadio**.

### **1. Interrogazione 1**

**Testo:**  
Determinare i nomi delle radio che trasmettono programmi **non musicali** nella fascia del **mattino** e della **sera**, ma **non** nella fascia del **pomeriggio**.

$$  
MAT := \pi_{CodiceRadio}(\sigma_{Tipo \neq 'musicale' \land FasciaOraria='mattina'}(PROGRAMMA))  
$$

$$  
SER := \pi_{CodiceRadio}(\sigma_{Tipo \neq 'musicale' \land FasciaOraria='sera'}(PROGRAMMA))  
$$

$$  
POM := \pi_{CodiceRadio}(\sigma_{Tipo \neq 'musicale' \land FasciaOraria='pomeriggio'}(PROGRAMMA))  
$$

$$  
COD_RIS := (MAT \cap SER) - POM  
$$

$$  
\pi_{Nome}(RADIO \bowtie_{Codice=CodiceRadio} COD_RIS)  
$$

---

### **2. Interrogazione 2**

**Testo:**  
Determinare, per ogni radio, il **codice del programma di durata minima**.

$$  
PROG := \pi_{Codice,Durata,CodiceRadio}(PROGRAMMA)  
$$

$$  
PROG' := \rho_{C',D',CR' \leftarrow Codice,Durata,CodiceRadio}(PROG)  
$$

$$  
\pi_{Codice,CodiceRadio}(PROG) - \pi_{Codice,CodiceRadio}(PROG_{CodiceRadio=CR' \land Durata>D'}(PROG'))  
$$

---

### **3. Interrogazione 3**

**Testo:**  
Determinare i nomi delle radio che trasmettono **almeno due programmi non musicali**.

$$  
NO_MUS := \pi_{Codice,CodiceRadio}(\sigma_{Tipo \neq 'musicale'}(PROGRAMMA))  
$$

$$  
NO_MUS' := \rho_{C',CR' \leftarrow Codice,CodiceRadio}(NO_MUS)  
$$

$$  
COD_RIS := \pi_{CodiceRadio}(NO_MUS_{CodiceRadio=CR' \land Codice \neq C'}(NO_MUS'))  
$$

$$  
\pi_{Nome}(RADIO \bowtie_{Codice=CodiceRadio} COD_RIS)  
$$

---

### **4. Interrogazione 4**

**Testo:**  
Determinare i nomi delle radio che **non hanno alcun programma musicale**.

$$  
MUS := \pi_{CodiceRadio}(\sigma_{Tipo='musicale'}(PROGRAMMA))  
$$

$$  
NO_MUS := \pi_{Codice}(RADIO) - \rho_{Codice \leftarrow CodiceRadio}(MUS)  
$$

$$  
\pi_{Nome}(RADIO \bowtie NO_MUS)  
$$

---

### **5. Interrogazione 5**

**Testo:**  
Determinare i nomi delle radio che hanno **programmi musicali condotti da DjX**.

$$  
COD_RIS := \pi_{CodiceRadio}(\sigma_{Tipo='musicale' \land Conduttore='DjX'}(PROGRAMMA))  
$$

$$  
\pi_{Nome}(RADIO \bowtie_{Codice=CodiceRadio} COD_RIS)  
$$

---

### **6. Interrogazione 6**

**Testo:**  
Determinare i nomi delle radio che trasmettono **programmi musicali o quiz**.

$$  
COD_RIS := \pi_{CodiceRadio}(\sigma_{Tipo='musicale' \lor Tipo='quiz'}(PROGRAMMA))  
$$

$$  
\pi_{Nome}(RADIO \bowtie_{Codice=CodiceRadio} COD_RIS)  
$$

---

### **7. Interrogazione 7**

**Testo:**  
Determinare i nomi delle radio che trasmettono **programmi musicali e quiz**.

$$  
MUS := \pi_{CodiceRadio}(\sigma_{Tipo='musicale'}(PROGRAMMA))  
$$

$$  
QUIZ := \pi_{CodiceRadio}(\sigma_{Tipo='quiz'}(PROGRAMMA))  
$$

$$  
COD_RIS := MUS \cap QUIZ  
$$

$$  
\pi_{Nome}(RADIO \bowtie_{Codice=CodiceRadio} COD_RIS)  
$$

---

### **8. Conclusione**

Questa seconda parte di esercitazione consolida la capacità di **tradurre in forma algebrica** interrogazioni logiche e complesse, con particolare attenzione a **condizioni combinate, differenze insiemistiche, cardinalità e join**, dimostrando come l’algebra relazionale permetta di formalizzare anche query articolate senza ambiguità.