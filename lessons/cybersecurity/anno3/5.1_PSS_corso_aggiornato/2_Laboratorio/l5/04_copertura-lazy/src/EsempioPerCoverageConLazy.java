public class EsempioPerCoverageConLazy {

	public static int foo(int a, boolean b, int c) {
		if (a < 10 && (b || c > 5)) {
			return 1;
		}
		else {
			return 2;
		}
	}
}
