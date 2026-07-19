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
→ trovare un valore conoscendo la chiave in tempo `O(1)` **medio** in una
`HashMap` ben dimensionata e con hash ben distribuiti. Non è una garanzia per
ogni caso; collisioni e chiavi patologiche peggiorano il costo.

---

# 🟦 **3. HashMap: l’implementazione più usata**

`HashMap` è l'implementazione generale più comune quando non serve un ordine.

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
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // 1. Creo una HashMap con chiave String e valore Integer
        Map<String, Integer> studenti = new HashMap<>();

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
        for (Map.Entry<String, Integer> coppia : studenti.entrySet()) {
            System.out.println(coppia.getKey() + " → " + coppia.getValue());
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

Recupera il valore in tempo O(1) medio e restituisce `null` se la chiave non è
presente. Poiché `HashMap` ammette anche valori `null`, per distinguere i due
casi si usa `containsKey`.

### ✔ `entrySet()`

Se servono insieme chiave e valore, `entrySet()` evita una seconda ricerca con
`get` per ogni chiave.

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
