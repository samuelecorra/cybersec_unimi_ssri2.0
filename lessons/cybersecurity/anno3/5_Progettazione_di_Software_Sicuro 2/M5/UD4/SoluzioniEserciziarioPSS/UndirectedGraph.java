public class UndirectedGraph {
	private /*@ spec_public @*/ boolean[][] incidenceMatrix;

	//il numero di nodi del grafo deve essere compreso tra 1 e 100	
	//@ requires numberNodes > 0 && numberNodes <= 100;
	//il grafo non contiene archi
	//@ ensures (\forall int i; i >= 0 && i < incidenceMatrix.length; (\forall int j; j >= 0 && j < incidenceMatrix.length; !incidenceMatrix[i][j]));
	public UndirectedGraph(int numberNodes) {
		incidenceMatrix = new boolean[numberNodes][numberNodes];
	}

	//non esiste un arco tra nodeA ed nodeB
	//@ requires !incidenceMatrix[nodeA][nodeB] && !incidenceMatrix[nodeB][nodeA];
	//il numero di archi del grafo dopo l'esecuzione del metodo e' uguale o al massimo maggiore
	//di un'unita' al numero di archi del grafo prima dell'esecuzione del metodo
	//(attenzione che ogni arco viene rappresentato due volte)
	/*@ ensures ((\sum int i; i >= 0 && i < incidenceMatrix.length; (\num_of int j; j >= 0 && j < incidenceMatrix.length; incidenceMatrix[i][j])) -
	  @			 (\sum int i; i >= 0 && i < incidenceMatrix.length; (\num_of int j; j >= 0 && j < incidenceMatrix.length; \old(incidenceMatrix[i][j])))) <= 2;
	  @*/
	//versione piu' precisa
	/*@ ensures ((\sum int i; i >= 0 && i < incidenceMatrix.length; (\num_of int j; j >= 0 && j < incidenceMatrix.length; incidenceMatrix[i][j])) -
	  @			 (\sum int i; i >= 0 && i < incidenceMatrix.length; (\num_of int j; j >= 0 && j < incidenceMatrix.length; \old(incidenceMatrix[i][j])))) == 2
	  @			||
	  @			((\sum int i; i >= 0 && i < incidenceMatrix.length; (\num_of int j; j >= 0 && j < incidenceMatrix.length; incidenceMatrix[i][j])) -
	  @			 (\sum int i; i >= 0 && i < incidenceMatrix.length; (\num_of int j; j >= 0 && j < incidenceMatrix.length; \old(incidenceMatrix[i][j])))) == 0;
	  @*/
	public boolean addArc(int nodeA, int nodeB) {
		//System.out.println((nodeA >= 0) + " " + (nodeA <= incidenceMatrix.length) + " " + (nodeB >= 0) + " " + (nodeB <= incidenceMatrix.length));
		if(nodeA >= 0 && nodeA < incidenceMatrix.length && nodeB >= 0 && nodeB < incidenceMatrix.length) {
			incidenceMatrix[nodeA][nodeB] = true;
			incidenceMatrix[nodeB][nodeA] = true;
			return true;
		}
		else {
			return false;
		}
	}

	//dice se la il grafo contiene almeno un ciclo di lunghezza 3
	public boolean cycle3() {
		for(int i = 0; i < incidenceMatrix.length; i++) {
			for(int j = i + 1; j < incidenceMatrix[i].length; j++) {
				if(incidenceMatrix[i][j]) {
					for(int k = 0; k < incidenceMatrix[i].length; k++) {
						if(k!=i && k!=j) {
							if(incidenceMatrix[j][k] && incidenceMatrix[k][i]) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		//viola la precondizione "il numero di nodi del grafo deve essere compreso tra 1 e 100"
		//DirectedGraph dg = new DirectedGraph(101);

		UndirectedGraph dg = new UndirectedGraph(5);
		dg.addArc(1, 4);
		//dg.addArc(1, 4);//viola la precondizione "non esiste un arco tra nodeA ed nodeB"

		//questo non e' un ciclo di lunghezza 3 
		UndirectedGraph u = new UndirectedGraph(3);
		u.addArc(0, 0);
		u.addArc(0, 1);
		u.addArc(0, 2);
		System.out.println(u.cycle3());
	}
}