public class Sort {

	/**
	 * Ritorna un nuovo array ordinato.
	 * 
	 * @param arrInput
	 * @return
	 */
	public static int[] selectionSort(int[] arrInput) {
		int minIndex, temp;
		int arr[] = arrInput.clone();
		for(int i = 0; i < arr.length - 1; i++) {
			minIndex = i;
			//ricerca del minimo
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			//scambio
			if(minIndex != i) {
				temp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = temp;
			}
		}
		return arr;
	}

	public static int[] selectionSortWrong(int[] arrInput) {
		int minIndex, temp;
		int arr[] = arrInput.clone();
		for(int i = 0; i < arr.length - 1; i++) {
			minIndex = i;
			//ricerca del minimo
			for(int j = i + 1; j < arr.length - 1; j++) {//errore: la condizione dovrebbe essere j < arr.length 
				if(arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			//scambio
			if(minIndex != i) {
				temp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = temp;
			}
		}
		return arr;
	}

	public static boolean isSortedAsc(int[] arr) {
		for(int i = 0; i < arr.length - 1; i++) {
			if(arr[i] > arr[i+1]) {
				return false;
			}
		}
		return true;
	}
}
