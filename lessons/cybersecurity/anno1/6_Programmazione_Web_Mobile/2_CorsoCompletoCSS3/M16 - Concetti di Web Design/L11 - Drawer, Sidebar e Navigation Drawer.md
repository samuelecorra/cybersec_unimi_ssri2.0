## **Lezione 11 – Drawer, Sidebar e Navigation Drawer**

### **1. Cos’è un Drawer**

Il **drawer** (o _navigation drawer_) è un menù laterale che compare “tirandosi fuori” da uno dei lati dello schermo, quasi sempre da sinistra.

È:

- **a tutta altezza**
    
- **nascosto di default**
    
- **mostrato tramite un’icona (hamburger menu)**
    
- **chiudibile cliccando fuori dalla sua area**
    

È tipico del **mobile design**, ma è usatissimo anche sul web moderno perché permette di liberare spazio nella _hero section_ e focalizzare l’attenzione sui contenuti.

---

### **2. Cos’è una Sidebar**

La **sidebar** è molto simile, ma con un comportamento diverso:

- **rimane sempre visibile**
    
- non “scorre fuori” dalla pagina
    
- fa parte del layout in modo permanente
    
- viene usata soprattutto su desktop e tablet in modalità landscape
    

Esempi pratici:

- pannello di navigazione di Google Drive
    
- sidebar di Discord
    
- pannelli laterali degli admin panel (Dashboard, CMS, ecc.)
    

---

## **3. Drawer vs Sidebar: differenze vere**

|Caratteristica|Drawer|Sidebar|
|---|---|---|
|Visibilità|Nascosto di default|Sempre visibile|
|Comportamento|Scivola dentro/fuori|Fisso nel layout|
|Focus contenuti|Massimo spazio quando chiuso|Spazio sempre occupato|
|Mobile|Perfetto per mobile|Poco usata su mobile|
|Desktop|Appare quasi sempre solo su icona|Molto comune|

In breve:  
**Drawer = mobile + comparsa/chiusura**  
**Sidebar = desktop + struttura fissa**

---

## **4. Come li chiamano i framework**

### **Tailwind e librerie (Flowbite, DaisyUI ecc.)**

- chiamano generalmente **Drawer** il menù laterale che _compare_
    
- chiamano **Sidebar** quello FISSO
    
- l’UI moderna tende a preferire drawer su mobile e sidebar su desktop
    

Esempio Flowbite (drawer classico con overlay):

- il menu scorre
    
- lo sfondo diventa scuro
    
- un clic fuori lo chiude
    

È il comportamento che hai anche nel tuo sito.

---

### **Material Design**

Material Design distingue due concetti:

- **Navigation Drawer** → comparsa laterale
    
- **Navigation Rail** / **Side Navigation** → sidebar persistente
    

Esempi:

- _M1-M2 Material Design_ → drawer molto squadrati, 3D
    
- _M3 Material You_ → drawer arrotondati, stile più moderno, overlay più soft
    

---

### **Bootstrap**

Bootstrap non usa esplicitamente il termine _drawer_, ma implementa lo stesso concetto nella **offcanvas**:

- stessa meccanica del drawer
    
- overlay scuro
    
- animazione laterale
    
- chiusura clic esterno
    

È quindi un "drawer con un altro nome".

---

## **5. Il tuo esempio (Corsi – Coderind)**

Hai esattamente un **drawer**:

- attivato dall’icona hamburger
    
- scorre da sinistra
    
- clic esterno per chiuderlo
    
- overlay scuro
    
- comportamento perfettamente _mobile-friendly_
    

Codice o framework a parte, il comportamento è quello definito da Material & Tailwind come _navigation drawer_.

---

## **6. Quando usare Drawer o Sidebar**

### **Drawer – Usalo quando:**

- sei in mobile
    
- vuoi massimizzare lo spazio del contenuto
    
- l’utente non deve vedere sempre il menù
    
- vuoi un design più moderno
    

### **Sidebar – Usala quando:**

- la struttura della pagina è complessa
    
- serve navigazione rapida e continua
    
- sei in modalità desktop
    
- stai creando un pannello strumenti (dashboard, editor, app web avanzata)
    

---

## **7. Morale finale**

Drawer e Sidebar:

- sono **lo stesso concetto architetturale**
    
- differiscono solo nel **comportamento**
    
- possono chiamarsi in modi diversi a seconda del framework
    
- devi solo capire _cosa ti serve_ e poi usare il componente che si comporta così
    

Se vuoi drawer → usa un componente che “entra ed esce”.  
Se vuoi sidebar → usa un componente sempre visibile.

---

## **Prossima lezione**

Passiamo alla **Modale (o Dialog)**: uno dei componenti più usati nel web moderno, fondamentale per UX e interazioni critiche.