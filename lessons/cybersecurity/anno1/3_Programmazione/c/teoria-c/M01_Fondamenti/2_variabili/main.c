/*

//================================================================================


Le variabili in C sono dei contenitori che permettono di memorizzare dei dati
in memoria, e di manipolarli durante l'esecuzione del programma. Ogni variabile
ha un tipo, che determina la natura del dato che può contenere (intero, reale,
carattere, ecc.), e un nome, che serve per identificarla nel codice.

Il tipo di dato determina non solo la natura del dato, ma anche la quantità di
memoria che la variabile occuperà, e le operazioni che possono essere eseguite
su di essa.

Le variabili possono essere dichiarate in diversi modi, a seconda del tipo di
dato che devono contenere. 


//================================================================================


Ad esempio, per dichiarare una variabile intera si
può scrivere:

int x;


Per dichiarare una variabile di tipo reale (float), si può scrivere:

float y;


Per dichiarare una variabile di tipo carattere (char), si può scrivere:

char z;


Infine, per usare il nome booleano bool, fornito da <stdbool.h> in C17, si può scrivere:

#include <stdbool.h>  // necessario per usare il tipo bool

bool èStudente; // false oppure true; ogni valore assegnato viene convertito a 0 oppure 1


Una volta dichiarate, le variabili possono essere utilizzate nel programma per
memorizzare e manipolare i dati. Ad esempio, si può assegnare un valore a una
variabile utilizzando l'operatore di assegnazione (=):

x = 5;
y = 3.14;
z = 'A';
èStudente = true;

In questo modo, si possono utilizzare le variabili nel programma per eseguire
operazioni e calcoli.


//================================================================================


DICHIARAZIONE MULTIPLA: possiamo ovviamente dichiarare più variabili dello
stesso tipo in una sola riga, separandole con una virgola:

int a, b, c; // tre variabili intere
float pi, raggio, area; // tre variabili float
char iniziale, lettera; // due variabili char
bool èStudente, èLavoratore; // due variabili bool


//================================================================================


Tra i tipi fondamentali del C troviamo:

int (interi)
float (numeri in virgola mobile a precisione singola)
char (singoli caratteri, ma in realtà è anch’esso un intero piccolo)
_Bool (valori logici); in C17 <stdbool.h> espone bool come macro che indica _Bool.

Esiste inoltre void, che rappresenta l'assenza di un valore. Il C non possiede un
tipo stringa fondamentale: le stringhe sono sequenze di char terminate da '\0'.

Su questi si innestano i modificatori signed, unsigned, short e long, combinabili
secondo le regole del linguaggio. I tipi floating-point sono float, double e long double.

⚠️ Nota: le dimensioni esatte possono dipendere dal compilatore e dall’architettura, 
ma lo standard garantisce solo degli intervalli minimi. 
Per precisione, userò i valori tipici di sistemi moderni a 32/64 bit con gcc 
(convenzionali ma non assoluti).

//================================================================================

1. int

Tipo base per gli interi.
Dimensione tipica: 4 byte (32 bit).
Range tipico: –2,147,483,648 a +2,147,483,647 (–2^31 a 2^31–1).

Varianti:

short int o short
Tipicamente 2 byte (16 bit).
Range: –32,768 a +32,767.

long int o long
Tipicamente 4 byte (su 32 bit) o 8 byte (su 64 bit).
Range: almeno –2,147,483,648 a +2,147,483,647, ma spesso molto più grande.

long long int o long long
Almeno 64 bit; tipicamente 8 byte quando un byte è composto da 8 bit.
Range: –9,223,372,036,854,775,808 a +9,223,372,036,854,775,807 (–2^63 a 2^63–1).


Signed/Unsigned:

signed int (default): consente valori negativi e positivi.

unsigned int: consente valori non negativi, incluso 0. A parità di numero di bit,
il massimo rappresentabile è circa il doppio di quello del corrispondente signed.

unsigned int (4 byte): 0 a 4,294,967,295.

unsigned short: 0 a 65,535.

unsigned long long: 0 a 18,446,744,073,709,551,615 (2^64–1).


//================================================================================

👉 Perché servono i modificatori?

unsigned: eliminando i negativi puoi sfruttare tutti i bit per rappresentare numeri
grandi positivi (utile in contatori, bitmask, indirizzi memoria).

short: può ridurre memoria o descrivere formati con ampiezza limitata; resta rilevante
in sistemi embedded, protocolli e interfacce hardware. Per ampiezze esatte si usano,
quando disponibili, i tipi di <stdint.h> come int32_t e uint32_t.

long/long long: gestione di numeri interi molto grandi (es. timestamp Unix a 64 bit).


//================================================================================


2. float

Rappresenta numeri reali in virgola mobile a precisione singola.

Tipicamente 4 byte (32 bit).
Range: circa ±3.4 × 10^38.
Precisione: circa 7 cifre decimali significative.

Varianti:

double (double precision)
Tipicamente 8 byte (64 bit).
Range: circa ±1.7 × 10^308.
Precisione: circa 15–16 cifre significative.

long double (extended precision, implementazione dipendente)
10, 12 o 16 byte, a seconda del compilatore.
Precisione estesa, fino a 18–19 cifre significative o più.


//================================================================================



👉 Perché servono i sottotipi?

float: occupa spesso meno memoria ed è utile, per esempio, in grafica e grandi array.
Non è però universalmente più veloce di double: dipende dall'hardware e dal compilatore.

double: standard per la maggior parte delle applicazioni scientifiche e di calcolo.

long double: per calcoli scientifici di altissima precisione (matematica numerica,
fisica computazionale).


//================================================================================


3. char

Memorizza un singolo carattere ed è un tipo intero grande esattamente 1 byte.
Lo standard garantisce almeno 8 bit per byte (CHAR_BIT in <limits.h>), non esattamente 8.
Range dipende dal compilatore: può essere signed o unsigned.

TIPICAMENTE:
signed char: –128 a +127.
unsigned char: 0 a 255.

Usato per rappresentare caratteri della tabella ASCII o byte grezzi.

👉 Perché ha senso che char sia un intero?

Ogni carattere è memorizzato come un codice numerico (es. 'A' = 65).
Questo permette di usare char sia come lettera sia come contenitore di dati binari (byte).

Varianti:

signed char → utile se vuoi gestire numeri piccoli con segno.

unsigned char → perfetto per byte raw (file binari, immagini, buffer).

//================================================================================


4. bool

Introdotto in C99 via #include <stdbool.h>.

Rappresenta valori logici: true e false.

bool è una macro per _Bool nelle versioni del C che usano <stdbool.h>.
sizeof(_Bool) vale 1 byte C, ma un byte C non è necessariamente composto da 8 bit.

false vale 0 e true vale 1. Assegnando a _Bool un valore diverso da zero si memorizza 1.

👉 Perché è stato introdotto tardi?
Storicamente il C usava interi (0 = falso, !=0 = vero). 
Per chiarezza semantica e leggibilità del codice, è stato introdotto un tipo dedicato.


//================================================================================


🔹 Tabella riassuntiva (tipica architettura a 64 bit)
Tipo		        Byte	Range principale

char		        1	    –128 … 127 / 0 … 255 (se unsigned)
short		        2	    –32,768 … 32,767
unsigned short	    2	    0 … 65,535
int		            4	    –2,147,483,648 … 2,147,483,647
unsigned int	    4	    0 … 4,294,967,295
long		        4 o 8	min. ±2 miliardi (32 bit) / ±9×10^18 (64 bit)
unsigned long	    4 o 8	fino a 4 miliardi / 18×10^18
long long	        8	    ±9×10^18
unsigned long long	8	    0 … 18×10^18
float		        4	    ±3.4 × 10^38 (≈7 cifre di precisione)
double		        8	    ±1.7 × 10^308 (≈15 cifre di precisione)
long double	        10–16	Dipende dall’implementazione (precisione estesa)
bool (_Bool)	    1	    true / false


//================================================================================


📌 In sintesi:

char, i tipi interi, i tipi floating-point, _Bool e void sono le fondamenta.

I modificatori short, long, signed e unsigned, insieme alla scelta fra float,
double e long double, permettono di bilanciare intervallo, memoria e precisione.

In epoca storica servivano a risparmiare byte (macchine a 16 bit). 
Oggi restano per compatibilità, ma hanno ancora senso in contesti specifici 
(bitmask, buffer, contatori, calcoli scientifici). */

#include <stdbool.h>
#include <stdio.h>

int main(void) {
    int età = 21;
    float altezza = 1.77f;
    double distanza = 149597870.7;
    char iniziale = 'S';
    bool èStudente = true;

    printf("Età: %d, altezza: %.2f, distanza: %.1f, iniziale: %c, studente: %d\n",
           età, altezza, distanza, iniziale, èStudente);
    printf("Dimensioni su questa implementazione: int=%zu, float=%zu, double=%zu, char=%zu, _Bool=%zu byte\n",
           sizeof(int), sizeof(float), sizeof(double), sizeof(char), sizeof(_Bool));

    return 0;
}
