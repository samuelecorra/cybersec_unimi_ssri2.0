public class cassettaPostale {
    
    private int[][] cassetta;

     // Postcondizione: dopo la costruzione di un oggetto, tutte le cassette
     // postali sono vuote; in JML:
    /* @ ensures \forall int i, j; i >= 0 && i < 3 && j >= 0 && j < 5 ==> cassetta[i][j] == 0 */
    // Stessa cosa ma usando gli assert di java:
    /* @ ensures {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                assert cassetta[i][j] == 0 : "La cassetta postale non è vuota dopo la costruzione";
            }
        }
    } */
    public CassettaPostale() {

        cassetta = new int[3][5];

    }

   
    // postcondizione: dopo aver consegnato numBuste in una certa cassetta, nell'intero palazzo
    // esisterà un appartamento che NON ha la cassetta postale PIENA; in JML:
    /* @ ensures \exists int i, j; i >= 0 && i < 3 && j >= 0 && j < 5 ==> cassetta[i][j] < 10 */
    public boolean consegna(int palazzo, int appartamento, int numBuste) {

        if(palazzo >= 0 && palazzo < 3 && appartamento >= 0 && appartamento < 5) {
        cassetta[palazzo][appartamento] = cassetta[palazzo][appartamento] + numBuste;
        return true;
        }
    return false;
    }
}
