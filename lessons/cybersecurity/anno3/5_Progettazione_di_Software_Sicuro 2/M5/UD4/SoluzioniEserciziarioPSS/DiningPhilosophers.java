public class DiningPhilosophers {
	private /*@ spec_public @*/ boolean[] eating;
	private /*@ spec_public @*/ boolean[] forkUsed;

	//tutti i filosofi pensano e tutte le forchette sono libere
	//@ ensures (\forall int i; i >= 0 && i < 1000; !eating[i] && !forkUsed[i]);
	public DiningPhilosophers() {
		eating = new boolean[1000];
		forkUsed = new boolean[1000];
	}

	//l'indice philo e' un indice valido
	//@ requires philo >= 0 && philo < 1000;
	//non esistono due filosofi vicini che stanno mangiando
	//@ ensures !(\exists int i; i >= 0 & i < 1000; eating[i] && eating[(i + 1)%1000]);
	//almeno una tra la forchetta sinistra e la forchetta destra del filosofo philo e' occupata
	//@ ensures forkUsed[philo] || forkUsed[(philo + 1)%1000];
	public void startEating(int philo) {
		//System.out.println("!forkUsed[philo] = " + !forkUsed[philo] + "\t!forkUsed[(philo + 1)%1000] = " + !forkUsed[(philo + 1)%1000]);
		if(!forkUsed[philo] && !forkUsed[(philo + 1)%1000]) {
			eating[philo] = true;
			forkUsed[philo] = true;
			forkUsed[(philo + 1)%1000] = true;
		}
	}

	public void startThinking(int philo) {
		if(eating[philo]) {
			eating[philo] = false;
			forkUsed[philo] = false;
			forkUsed[(philo + 1)%1000] = false;
		}
	}

	public static void main(String[] args) {
		//viola la precondizione "l'indice philo e' un indice valido"
		//DiningPhilosophers dp = new DiningPhilosophers();
		//dp.startEating(1001);
	}
}