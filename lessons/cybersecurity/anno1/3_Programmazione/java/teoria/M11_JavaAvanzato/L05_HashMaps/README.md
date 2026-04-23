# 🟦 **Lezione: Introduzione alle Map e approfondimento sulle HashMap**

## 🚀 **1. Che cos’è una Map in Java (concetto madre)**

In Java, una `Map` è una struttura dati che **associa una chiave (Key) a un valore (Value)**.
È come un dizionario:

* ➤ cerchi una parola (KEY)
* ➤ ottieni la definizione (VALUE)

Formalmente:

```java
Map<KeyType, ValueType>
```

Le Map NON discendono da `Collection`: sono un'interfaccia indipendente.

---

## 🟩 **2. Proprietà fondamentali di una Map**

### **2.1. Le chiavi sono UNICHE**

Non possono esistere due chiavi identiche.

### **2.2. I valori possono essere DUPLICATI**

Puoi avere 10 valori uguali con 10 chiavi diverse.

### **2.3. Accesso ultra-rapido**

Il vantaggio centrale delle Map:
→ trovare un valore conoscendo la chiave in tempo molto rapido (`O(1)` nelle HashMap).

---

# 🟦 **3. HashMap: l’implementazione più usata**

`HashMap` è la Map standard. La userai il **99% delle volte**.

Perché si chiama **Hash**?
Perché usa una *funzione hash* per trasformare la chiave in un numero, sfruttato per trovare rapidamente il valore.

---

## 🟣 **4. Caratteristiche della HashMap**

✔ Chiavi **uniche**
✔ Valori **duplicabili**
✔ Accesso **velocissimo**
✔ **Nessun ordine garantito**
❗ Non è sincronizzata (ma c’è `ConcurrentHashMap` per quello)

Sintassi:

```java
HashMap<Key, Value> mappa = new HashMap<>();
```

---

# 🟦 **5. Esempio base – usare una HashMap**

Ti faccio un esempio *didattico perfetto*, spiegato riga per riga.

### 📝 **Codice completo:**

```java
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        // 1. Creo una HashMap con chiave String e valore Integer
        HashMap<String, Integer> studenti = new HashMap<>();

        // 2. Inserisco elementi (Key, Value)
        studenti.put("Marco", 27);
        studenti.put("Sara", 30);
        studenti.put("Luca", 25);
        studenti.put("Marco", 29); 
        // ATTENZIONE: la chiave "Marco" era già presente → viene sovrascritta

        // 3. Recupero un valore tramite la chiave
        int votoSara = studenti.get("Sara");
        System.out.println("Il voto di Sara è: " + votoSara);

        // 4. Controllo se una chiave esiste
        System.out.println(studenti.containsKey("Luca"));   // true

        // 5. Controllo se un valore esiste
        System.out.println(studenti.containsValue(30));     // true

        // 6. Rimuovo un elemento
        studenti.remove("Luca");

        // 7. Stampo tutti gli elementi
        for (String chiave : studenti.keySet()) {
            Integer valore = studenti.get(chiave);
            System.out.println(chiave + " → " + valore);
        }
    }
}
```

---

# 🟧 **6. Spiegazione ATOMICA del codice**

### ✔ `put(key, value)`

Inserisce la coppia.

### ✔ Sostituzione automatica

Se la chiave esiste → il valore viene aggiornato:

```java
studenti.put("Marco", 27);
studenti.put("Marco", 29); // sovrascrive
```

### ✔ `get(key)`

Recupera il valore in O(1).

### ✔ `keySet()`

Ottieni tutte le chiavi (iterazione tipica nelle Map).

---

# 🟦 **7. HashMap vs altre Map**

Per cultura:

### **HashMap**

* * Più veloce
* – Nessun ordine

### **LinkedHashMap**

* Mantiene l’ordine di inserimento
* Leggermente più lenta

### **TreeMap**

* Ordina automaticamente le chiavi
* Basata su albero rosso-nero (O(log n))

---

# 🟪 **8. Caso pratico – Dizionario di traduzioni**

Le Map sono perfette per associare concetto → valore.

```java
HashMap<String, String> dizionario = new HashMap<>();

dizionario.put("gatto", "cat");
dizionario.put("cane", "dog");
dizionario.put("casa", "house");

System.out.println(dizionario.get("cane")); // dog
```

---

# 🟦 **9. Caso reale Java: contare frequenze**

Problema classico: ottenere quante volte compare ogni parola.

```java
HashMap<String, Integer> freq = new HashMap<>();

String[] parole = {"ciao", "ciao", "java", "ciao"};

for (String p : parole) {
    freq.put(p, freq.getOrDefault(p, 0) + 1);
}

System.out.println(freq);
```

Output:
`{ciao=3, java=1}`

---

# 🟧 **10. Quando NON usare HashMap**

Quando serve **ordine** → usa `LinkedHashMap`.
Quando serve **ordinamento** → usa `TreeMap`.
Quando servono **operazioni concorrenti** → `ConcurrentHashMap`.

---
