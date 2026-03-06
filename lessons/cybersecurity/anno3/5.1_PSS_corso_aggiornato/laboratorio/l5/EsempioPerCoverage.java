public class EsempioPerCoverage {

	public static int foo(int a, boolean b, boolean c, int d, int[] arr) {
		if (a < 10) {
			//System.out.println(b + " " + c + " " + (d < 5));
			if (b & (c | d < 5)) {
				return arr[4];
			}
			else {
				if (b) {
					return arr[3];
				}
				else {
					if (c) {
						return arr[2];
					}
					else {
						return arr[1];
					}
				}
			}
		}
		else {
			return arr[0];
		}
	}
}
