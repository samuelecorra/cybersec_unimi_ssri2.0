public class Fila {
	private /*@ spec_public @*/ int[] fila;
	public int prossimoInFila;
	private /*@ spec_public @*/ int prossimoServito;
	
	//prossimoInFila ha un valore tra 0 e 200 inclusi e prossimoServito non e' mai strettamente superiore a prossimoInFila
	//@ public invariant prossimoInFila >=0 && prossimoInFila <=200 &&  !(prossimoServito>prossimoInFila);

	//non ci sono utenti in fila 
	//@ ensures (\forall int i; i >= 0 && i < 200; fila[i] == 0);
	public Fila() {
		fila = new int[200];
		prossimoInFila = 0;
		prossimoServito = 0;
		
	}

	//i minuti di attesa di un utente sono compresi tra 1 e 15 inclusi
	//@ requires minuti >= 1 && minuti <=15;
	//tutti gli elementi dell'array di indice strettamente inferiore a prossimoInFila hanno valore strettamente maggiori di 0
	//@ ensures (\forall int i; i >= 0 && i < prossimoInFila; fila[i]>0);
	//almeno un utente  e' stato inserito in fila
	//@ ensures (\exists int i; i >= 0 && i < 200; fila[i] > 0);
	//nell'array non c'e' mai uno valore a 0 prima di uno strettamente maggiore di 0
	//@ ensures (\forall int i; i >= 0 && i < 199; ((fila[i] == 0) ==> (fila[i+1] == 0)) );
	//oppure
	//@ ensures (\forall int i; i >= 0 && i < 199; !((fila[i] == 0) && (fila[i+1] > 0)) );
	public int ritiroNumero(int minuti) {
	    int numero = -1;
	    if(prossimoInFila<200 && prossimoInFila>=0) {
	    	fila[prossimoInFila] = minuti;
	        numero = prossimoInFila++; }
	    return numero;
	}
	
	
	
	public int chiamataAlloSportello() {
		int numero = -1;
		// basta prossimoServito<prossimoInFila
	    if(prossimoServito<200 && prossimoServito<prossimoInFila) {
	    	numero = prossimoServito++; }
	    return numero;
	}

	//se il numero è già stato chiamato il metodo restituisce -1
	//@ ensures (prossimoServito > numero) ==> \result == -1;
	public int stimaMinuti(int numero){
		int risultato = 0;
	    if(prossimoServito > numero || numero >= prossimoInFila) 
	    	risultato = -1;
	    else{
	    	for(int i=prossimoServito; i < numero; i++)
	    		risultato += fila[i];
	    }  	
	    	
	    return risultato;
	}

	public static void main(String[] args) {
		/*Fila f = new Fila();
		int n0, n1, n2, n3, n4, c0=0, c1=0, r;
		c0= f.chiamataAlloSportello();
		System.out.println(c0);
		n0= f.ritiroNumero(10);
		n1= f.ritiroNumero(3);	
		System.out.println(n0);
		System.out.println(n1);
		n2= f.ritiroNumero(4);	
		n3= f.ritiroNumero(15);		
		n4= f.ritiroNumero(10);	
		c0= f.chiamataAlloSportello();
		c1= f.chiamataAlloSportello();
		System.out.println(c0);
		System.out.println(c1);
		r=f.stimaMinuti(1);
		System.out.println(r);
		r=f.stimaMinuti(4);
		System.out.println(r);*/
		
	}
}