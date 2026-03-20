public class UndirectedGraph {

    /*  Esercizio 7 - Undirected graph

        Grafo non orientato rappresentato tramite matrice booleana n x n.
        incidenceMatrix[i][j] == true significa che esiste un arco tra i e j.
        Essendo non orientato, l'arco viene rappresentato in modo simmetrico:
        anche incidenceMatrix[j][i] deve essere true.

        Metodi:
            - addArc(int nodeA, int nodeB)
            - cycle3()

        Richieste JML:
            - costruttore: pre (1..100), post (nessun arco);
            - addArc: pre (arco non esistente), post (numero celle true aumenta al massimo di 2).
    */

    boolean[][] incidenceMatrix;

    //@ requires numberNodes > 0 && numberNodes <= 100;
    /*@ ensures (\forall int i; i >= 0 && i < incidenceMatrix.length;
      @     (\forall int j; j >= 0 && j < incidenceMatrix.length; !incidenceMatrix[i][j]));
      @*/
    public UndirectedGraph(int numberNodes) {
        incidenceMatrix = new boolean[numberNodes][numberNodes];
    }

    //@ requires nodeA >= 0 && nodeA < incidenceMatrix.length && nodeB >= 0 && nodeB < incidenceMatrix.length;
    //@ requires !incidenceMatrix[nodeA][nodeB] && !incidenceMatrix[nodeB][nodeA];
    /*@ ensures ((\sum int i; i >= 0 && i < incidenceMatrix.length;
      @     (\num_of int j; j >= 0 && j < incidenceMatrix.length; incidenceMatrix[i][j])) -
      @    (\sum int i; i >= 0 && i < incidenceMatrix.length;
      @     (\num_of int j; j >= 0 && j < incidenceMatrix.length; \old(incidenceMatrix[i][j])))) <= 2;
      @*/
    public boolean addArc(int nodeA, int nodeB) {
        if (nodeA >= 0 && nodeA < incidenceMatrix.length
                && nodeB >= 0 && nodeB < incidenceMatrix.length) {
            incidenceMatrix[nodeA][nodeB] = true;
            incidenceMatrix[nodeB][nodeA] = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean cycle3() {
        int n = incidenceMatrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!incidenceMatrix[i][j]) {
                    continue;
                }
                for (int k = j + 1; k < n; k++) {
                    if (incidenceMatrix[i][k] && incidenceMatrix[j][k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
