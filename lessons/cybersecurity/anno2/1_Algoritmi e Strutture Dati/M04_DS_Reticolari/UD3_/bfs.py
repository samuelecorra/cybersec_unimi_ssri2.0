# BFS in python, spiegato easy!

# Breadth-First Search (BFS) è un algoritmo per attraversare un grafo livello per livello, partendo da un nodo sorgente.
# A differenza della Depth-First Search (DFS), che esplora un ramo del grafo fino in fondo prima di tornare indietro, 
# BFS esplora tutti i nodi a una certa distanza dal nodo sorgente prima di passare ai nodi più lontani.

# Useremo una coda FIFO (First In, First Out) per gestire i nodi da visitare.

from collections import deque