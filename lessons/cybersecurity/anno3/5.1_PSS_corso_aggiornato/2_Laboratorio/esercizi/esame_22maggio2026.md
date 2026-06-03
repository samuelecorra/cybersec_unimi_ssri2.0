# Progettazione di Software Sicuro - Esame del 22 Maggio 2026

## Domande di Teoria:

1. Definire le nozioni di stato e transizione in riferimento alla
notazione di UML.

2. Design by Contract: spiegare i suoi elementi peculiari e come si applica
nel mondo dell'Object-Oriented Programming.

3. Elencare e descrivere le qualità esterne di un software sicuro.

4. Nell'ambito del testing, spiegarne le tre dimensioni: accessibilità, aspetti e livello.

5. Esercizio di modellazione con macchina parallela UML: un parcheggio coperto
mediato da un semaforo (inizialmente verde, ovviamente) e da una telecamera d'entrata e d'uscita funziona nel
seguente modo: quando un'auto arriva, la telecamera in ingresso notifica il
controller principale del sistema che l'auto è in arrivo. Il controller, a sua
volta, capisce se il parcheggio ha posti liberi o meno, e in caso affermativo
fa diventare il semaforo verde se non lo è già, e lascia entrare l'auto.
Quando un'auto esce, la telecamera di uscita notifica il controller che l'auto è uscita, e il controller aggiorna lo stato del parcheggio di conseguenza,
decrementando di uno il numero di posti occupati. Se il parcheggio è pieno, il controller fa diventare il semaforo rosso.

6. Definire cosa si intende per test set valido, affidabile ed ideale.

7. State pattern: viene data una FSM e bisogna rappresentarla con questo pattern, spiegando i vantaggi che ne derivano.

8. MCDC per la coverage di condizioni e decisioni per il codice:

```c
if (x<0 || y<0) {
    // codice da eseguire
}
else {
    // codice da eseguire
}
```

---

In arrivo il lab...