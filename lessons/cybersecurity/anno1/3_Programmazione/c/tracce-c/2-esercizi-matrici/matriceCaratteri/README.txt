/*------------------------------------------------------------------------------------

ESERCIZIO: matriceCaratteri

Ricevo in ingresso un array bidimensionale quadrato per gestire i caratteri 
(e qualsiasi altro parametro ritenuto necessario). Si individui il carattere "car" 
che si ripete più volte (si ipotizzi che sia sempre unico)  e visualizzi il contenuto 
dell'array, mostrando uno spazio invece dei caratteri diversi da "car". 
Il sottoprogramma trasmette poi il carattere individuato e il numero di volte che 
compare. La matrice va stampata, ma non restituita dalla funzione, e non va nemmeno 
modificata. Genera un main che testa la funzione.

------------------------------------------------------------------------------------- */

SONO PRESENTI DUE VERSIONI DI QUESTO ESERCIZIO:

- mainV1.c: versione che passa alla funzione un puntatore a puntatore ad int, come
richiede il C17, che viene utilizzato di default da VS Code su Windows. In questo caso
è necessario utilizzare la memoria dinamica per creare la matrice.

- mainV2.c: versione che passa alla funzione un array a lunghezza variabile (VLA),
come consentito dallo standard gnu11. In questo caso non è necessario utilizzare la
memoria dinamica per creare la matrice.

BISOGNA DUNQUE SCEGLIERE QUALE STANDARD USARE NELL'IDE,
IN BASE ALLA VERSIONE CHE SI VUOLE COMPILARE.

/*------------------------------------------------------------------------------------

NOTE SULL'AMBIENTE DI SVILUPPO nel mainV2.c

L'ambiente di sviluppo utilizzato è configurato come segue:

{
    "name": "AmbienteLinux",
    "compilerPath": "C:\\Program Files\\mingw64\\bin\\gcc.exe",
    "cStandard": "gnu11",
    "intelliSenseMode": "linux-gcc-x64"
}

Ciò significa che stiamo simulando un ambiente GNU/Linux, in vista dell'esame di 
laboratorio che si svolge sui computer dell'università, sui quali gira Linux.

È importante ricordare che con lo standard **gnu11**, a differenza del **C17** 
utilizzato di default da VS Code su Windows, è consentito passare alle funzioni 
array a lunghezza variabile (VLA), come ad esempio `char matrice[n][n]`, a condizione 
che prima vengano passati alla funzione i parametri che specificano le dimensioni 
dell'array stesso. Così evitiamo di utilizzare costanti `#define` per le dimensioni
o ancor peggio evitiamo di utilizzare array creati dinamicamente con `malloc()`, che
sono innanzitutto più complessi da gestire e in secondo luogo non sono richiesti dall'esercizio,
che prevede un saggio uso di VLA in un ambiente consono a gestirli.

------------------------------------------------------------------------------------ */