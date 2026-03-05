## **Lezione 13: Massimi e minimi – problema di geometria solida (Maturità 2014)**

### **1. Testo del problema**

Un’azienda commercializza il proprio prodotto in **lattine da 5 litri**, a forma di **parallelepipedo a base quadrata**.

Le lattine devono avere **dimensioni tali da richiedere la minima quantità di latta** per essere realizzate.

Determinare **le dimensioni della lattina**, arrotondate ai millimetri.

---
## **2. Cosa va minimizzato**

La quantità di latta necessaria dipende dalla **superficie totale** della lattina.

Quindi l’oggetto da **minimizzare** è:

$$

S = \text{superficie totale}

$$

---
## **3. Scelta delle variabili**

Indichiamo:

- $x$ = lato della base quadrata
    
- $h$ = altezza della lattina

La lattina è un **parallelepipedo a base quadrata**.

---
## **4. Espressione della superficie totale**

La superficie totale è data da:

- **2 basi** di area $x^2$
    
- **4 facce laterali**, ciascuna di area $x\cdot h$

Quindi:

$$

S(x,h)=2x^2+4xh

$$

Al momento la superficie dipende da **due variabili**, quindi non è ancora adatta allo studio con le derivate.

---
## **5. Uso del vincolo: volume fissato**

Il testo dice che il volume è **5 litri**, cioè:
$$

5\ \text{dm}^3

$$

Il volume del parallelepipedo è:
$$

V=x^2h

$$

Imponendo il vincolo:
$$

x^2h=5

$$

Da cui ricaviamo:
$$

h=\frac{5}{x^2}

$$

---
## **6. Superficie in funzione di una sola variabile**

Sostituiamo $h=\dfrac{5}{x^2}$ nella superficie:

$$

S(x)=2x^2+4x\cdot\frac{5}{x^2}

$$

Semplificando:
$$

S(x)=2x^2+\frac{20}{x}

$$

Dominio naturale:

$$

x>0

$$

---
## **7. Derivata della funzione**

Calcoliamo la derivata prima:

$$

S’(x)=\frac{d}{dx}\left(2x^2+\frac{20}{x}\right)

$$

- derivata di $2x^2$ → $4x$
    
- derivata di $\dfrac{20}{x}$ → $-,\dfrac{20}{x^2}$

Quindi:
$$

S’(x)=4x-\frac{20}{x^2}

$$

Scritta come frazione:
$$

S’(x)=\frac{4x^3-20}{x^2}

$$

---
## **8. Studio del segno**

Nel dominio $x>0$, il denominatore $x^2$ è sempre positivo.

Il segno della derivata dipende solo dal numeratore:

$$

4x^3-20

$$

Poniamo:
$$

4x^3-20=0

$$
$$

x^3=5

$$
$$

x=\sqrt[3]{5}

$$

Segno della derivata:

- $S’(x)<0$ per $x<\sqrt[3]{5}$
    
- $S’(x)>0$ per $x>\sqrt[3]{5}$

Quindi $S(x)$ **decresce** e poi **cresce**: abbiamo un **minimo**.

---
## **9. Dimensioni della lattina**

Il minimo si ha per:
$$

x=\sqrt[3]{5}

$$

Ricaviamo l’altezza:
$$

h=\frac{5}{x^2}

=\frac{5}{(\sqrt[3]{5})^2}

=\sqrt[3]{5}

$$
Quindi:
$$

x=h=\sqrt[3]{5}\ \text{dm}

$$

La lattina ottimale è in realtà un **cubo**.

---
## **10. Conversione in millimetri**

Calcoliamo l’approssimazione numerica:

$$

\sqrt[3]{5}\approx1{,}71\ \text{dm}

$$

Poiché:
$$

1\ \text{dm}=100\ \text{mm}

$$
Otteniamo:
$$

x=h\approx171\ \text{mm}

$$

---
## **11. Risposta finale**

La lattina che utilizza **la minima quantità di latta** è un **cubo** con:

$$

\boxed{

\text{lato della base}=\text{altezza}\approx171\ \text{mm}

}

$$

---
## **12. Osservazione concettuale importante**

Non è un caso che la soluzione sia un solido **molto simmetrico**.

A parità di volume:

- la **sfera** minimizza la superficie
    
- tra i parallelepipedi, quello “più simile” a una sfera è il **cubo**

Nei problemi di massimo e minimo, **la configurazione ottimale è spesso la più simmetrica consentita dai vincoli**.

---
## **13. Strategia generale per problemi analoghi**

1. Individuare la grandezza da massimizzare o minimizzare
    
2. Esprimerla in funzione delle variabili geometriche
    
3. Usare i vincoli per ridurre a **una sola variabile**
    
4. Stabilire il dominio
    
5. Calcolare la derivata
    
6. Studiare il segno della derivata
    
7. Interpretare il risultato nel contesto del problema