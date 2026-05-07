public class SumProductExamples {

	/*@ requires n > 0;
	  @
	  @ ensures \result == (\sum int x; x >= 1 && x <= n; x); 
	  @*/
	public static int sumN(int n) {
		return n*(n + 1)/2;
	}

	/*@ require n >=0 && n <= 21;
	  @
	  @ ensures \result == (\product int i; i > 0 && i <= n; i); 
	  @*/
	public static long fattoriale(int n) {
		long result = 1l;
		for(int i = 2; i <= n; i++) {
			result = result * i;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(sumN(3));
	}
}
