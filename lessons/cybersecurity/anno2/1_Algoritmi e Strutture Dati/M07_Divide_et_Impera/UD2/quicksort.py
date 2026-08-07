# QuickSort è un algoritmo di ordinamento ottimo che utilizza il paradigma divide et impera.
# A differenza del MergeSort, che divide l'array in due metà uguali sempre più piccole, QuickSort
# divide l'array in due sotto-array basandosi su un elemento chiamato pivot. 
# Gli elementi minori del pivot vengono spostati a sinistra del pivot, 
# mentre quelli maggiori vengono spostati a destra.

"""
Supponiamo di partire con l'array [8, 2, 4, 7, 1, 3, 9, 6, 5].


"""

# Per comodità semantica, creiamo una funzione di scambio così nel codice del quicksort scriviamo una riga e non tre
# con la variabile temporanea. In questo modo il codice sarà più leggibile e meno soggetto a errori.
def swap(array, i, j):
    tmp = array[i]
    array[i] = array[j]
    array[j] = tmp

def quicksort(array):
    # Se l'array ha lunghezza 0 o 1, è già ordinato
    if len(array) <= 1:
        return array

    # Settiamo il pivot alla fine dell'array
    pivot = array[-1] # in python gli array partono da 0, quindi l'ultimo elemento ha indice 8
    # oppure possiamo scrivere pivot = array[-1] per prendere l'ultimo elemento dell'array, in quanto
    # in python gli indici negativi ci consentono di contare a ritroso partendo dalla fine dell'array.

    # Ora settiamo due scrolling indexes:

    # i, che parte dalla -1-esima casella, ovvero a sinistra del primo elemento dell'array
    i = -1
    # j, che parte dalla 0-esima casella, ovvero dal primo elemento dell'array
    j = 0

    # Nessun bisogno di dichiarare la variabile tmp che è già inclusa nella sua funzione, e all'infuori di essa
    # non viene usata quindi ok così!

    # Ora inizia il cuore dell'algoritmo, ovvero lo scrolling - ma come apriamo un ciclo in python?
    # Con un while loop, che continuerà finché j sarà minore della lunghezza dell'array meno 1 (perché l'ultimo elemento è il pivot)
    while j < len(array) - 1:
        # Se l'elemento corrente è minore o uguale al pivot, allora dobbiamo spostarlo a sinistra del pivot
        if array[j] <= pivot:
            # Incrementiamo i di 1
            i += 1
            # Scambiamo l'elemento corrente con l'elemento in posizione i
            swap(array, i, j)
            # Stampiamo l'array per vedere come cambia ad ogni scambio
            print(array)
        j += 1 # Incrementiamo j di 1 come operazione finale di scrolling

# Quando arriviamo qui, significa che abbiamo trovato il resting place finale del pivot, ovvero la posizione in cui
# il pivot deve stare per essere ordinato. Quindi lo scambiamo con l'elemento in posizione i+1
    swap(array, i + 1, len(array) - 1)
    # Stampiamo l'array per vedere come cambia ad ogni scambio
    print(array)

    # Ora il pivot è al suo posto, quindi possiamo fare il quicksort ricorsivo sui due sotto-array
    left = quicksort(array[:i + 1]) # il sotto-array a sinistra del pivot
    right = quicksort(array[i + 2:]) # il sotto-array a destra del pivot

    # Infine, ritorniamo l'array ordinato concatenando i due sotto-array e il pivot
    return left + [pivot] + right

# La funzione è ora completa e pronta per testing. 
# Possiamo testarla con un main.py che importi questa funzione e la chiami con un array di esempio.

# Quindi la prima riga dell'altro file