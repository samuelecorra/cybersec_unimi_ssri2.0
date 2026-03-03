# **Lezione 1: Crittografia - Note storiche**

---

### **1. Origine del termine**

La parola **crittografia** deriva dal greco antico:  
**“kryptós” (χρυπτοσ)** = nascosto  
**“graphía” (γραφία)** = scrittura

**Crittografia** significa quindi _“scrittura nascosta”_, ossia l’arte di **rendere oscuro** ciò che si scrive o comunica, in modo che solo chi possiede la chiave possa comprenderlo.

---

### **2. Crittologia: la scienza della comunicazione sicura**

La **crittologia** è la scienza - più ampia - che studia la **comunicazione sicura**, ossia lo scambio di informazioni in forma **segreta o protetta**.  
Essa si divide in due rami fondamentali:

- **Crittografia:** studio e applicazione delle tecniche per **rendere l’informazione inintelligibile** a chi non è autorizzato.
    
- **Crittoanalisi:** arte e scienza di **rompere** un sistema crittografico per **recuperare l’informazione nascosta**.
    

Queste due discipline si sono sviluppate parallelamente: ogni nuovo cifrario ha stimolato la nascita di nuove tecniche di analisi.

---

### **3. Prime applicazioni storiche**

L’uso della crittografia è attestato **fin dalle prime civiltà** per:

- **Comunicazioni private**, spesso di natura politica o militare
    
- **Ambiti religiosi**, dove la scrittura segreta serviva a rappresentare il mistero e il sacro
    
- **Diplomazia e guerra**, per nascondere ordini o piani strategici
    

---

### **4. Scritture segrete e significati simbolici**

Nelle società antiche, la trasformazione del linguaggio non serviva solo a nascondere un messaggio, ma anche a conferirgli **dignità, potere magico o mistero**.

Un esempio celebre è un’incisione funebre nella città di **Menet Khufu** (sulle rive del Nilo, circa 4000 anni fa), dove i **geroglifici** avevano la funzione di **onorare il defunto** con una scrittura sacra e impenetrabile ai profani.

![](imgs/Pasted%20image%2020251124222720.png)

---

### **5. Cifrature nella Bibbia**

Anche nei testi sacri si trovano **tecniche di cifratura**, usate per nascondere nomi o messaggi simbolici.  
Nel libro di Geremia, per esempio, il nome “Babilonia” viene cifrato in “Sesach” tramite un metodo detto **Atbash**, basato sull’**alfabeto invertito**:

- **Atbash:** la prima lettera si sostituisce con l’ultima, la seconda con la penultima, e così via.  
    Esempio:  
    `A ↔ Z`, `B ↔ Y`, `C ↔ X`, …
    

Altre tecniche citate sono:

- **Albam:** l’alfabeto viene diviso in due metà e le lettere della prima metà vengono sostituite con quelle della seconda.
    
- **Atbah:** usa relazioni **numeriche** tra lettere.
    

Per le prime nove:

$$  
\text{lettera sostituente} + \text{lettera sostituita} = 10  
$$

Per le restanti:

$$  
\text{lettera sostituente} + \text{lettera sostituita} = 28  
$$

Questi sistemi mostrano come già le culture antiche avessero intuito il concetto di **sostituzione sistematica** come base della cifratura.

---

### **6. L’India e la cifratura come arte**

In India, la crittografia era considerata **una disciplina raffinata** e diffusa in diversi ambiti:

- **Artha-Sastra:** testo di politica e spionaggio che descrive l’uso dei messaggi cifrati.
    
- **Lalita-Vistara:** esalta il Buddha e menziona scritture disordinate o perpendicolari per nascondere i significati.
    
- **Kama-Sutra:** annovera la _mlecchita-vikalpa_ come la **45ª arte** che una donna deve conoscere — l’arte di scrivere in modo cifrato.
    

Esempio di cifrario indiano moderno (sostituzione letterale):

```
Alfabeto:
A T N I M O R S U C Z E
V Z S G C Q L N E M T U

Messaggio: "Incontriamoci a mezzanotte"
Cifrato: GSMQSZLGVCQMG V CUTTVSQZZU
```

---

### **7. La congiura di Babington**

Un episodio storico famoso che mostra l’importanza della crittografia è la **congiura di Babington** (1586).

![](imgs/Pasted%20image%2020251124223334.png)

La **regina Maria Stuarda di Scozia**, prigioniera della cugina **Elisabetta I d’Inghilterra**, cospirò contro di lei scambiandosi **messaggi cifrati** con **Anthony Babington**.

- I messaggi erano nascosti **nei tappi (zipoli) delle botti di birra** e trasportati da un corriere di nome **Gilbert Gifford**.

![](imgs/Pasted%20image%2020251124223429.png)

    
- Il cifrario utilizzato conteneva:
    
    - **23 simboli** per sostituire le lettere,
        
    - **35 simboli** per intere parole o frasi,
        
    - **4 simboli nulli** e **uno per le doppie**.
        

Tuttavia, Gifford consegnava le lettere a **Sir Francis Walsingham**, segretario di stato, e queste venivano **decifrate da Thomas Phelippes**.  
Il contenuto rivelò la cospirazione, e **Maria fu condannata a morte l’8 febbraio 1587**.

Questo episodio segna una svolta storica: mostra come **la sicurezza di un cifrario** dipenda **non solo dalla complessità del codice**, ma anche dalla **fiducia nel canale di comunicazione**.

---

### **8. Enigma e la nascita della crittografia moderna**

Nel XX secolo, la crittografia compie un salto epocale con la **macchina Enigma**, ideata da **Arthur Scherbius** nel 1918 e utilizzata durante la **Seconda Guerra Mondiale**.

![](imgs/Pasted%20image%2020251124223730.png)

Enigma introduce per la prima volta:

- la **cifratura elettromeccanica**,
    
- l’uso di **chiavi variabili quotidiane**,
    
- e una **combinazione complessa di permutazioni**.
    

La sua decodifica da parte di **Alan Turing** e del team di **Bletchley Park** aprì la strada alla **crittografia matematica e computazionale** moderna.

---

### **9. Le tre fasi della crittografia**

Possiamo distinguere **tre grandi fasi storiche** nello sviluppo della crittografia:

|**Fase**|**Periodo**|**Caratteristiche principali**|
|---|---|---|
|**1. Era manuale**|Dalle civiltà antiche fino al XIX secolo|Algoritmi semplici eseguiti a mano (sostituzioni, permutazioni, trasposizioni).|
|**2. Era meccanica**|Prima metà del XX secolo|Nascita delle **macchine cifranti** come Enigma; maggiore complessità.|
|**3. Era moderna**|Dagli anni ’70 ad oggi|Uso dei **computer** e **fondamenti matematici** (teoria dei numeri, complessità computazionale).|

---

### **10. Età moderna e nuovi campi di applicazione**

Con l’avvento dei computer, la crittografia diventa parte integrante dell’informatica.  
Oggi serve a proteggere:

- **Internet** e le comunicazioni online
    
- **Sistemi distribuiti** e cloud
    
- **Dati sensibili** (bancari, medici, personali)
    

Il suo scopo non è più solo nascondere un messaggio, ma **garantire sicurezza a 360°**: riservatezza, integrità, autenticità e non ripudio.

---

### **11. Dalla scrittura nascosta alla scienza della sicurezza**

La **crittografia moderna** si fonda su basi **matematiche rigorose** e studia non solo come nascondere, ma **come proteggere** l’informazione in ogni fase della sua vita.

È diventata una vera **scienza della sicurezza dell’informazione**, che unisce matematica, informatica e ingegneria per progettare sistemi affidabili in un mondo digitale interconnesso.