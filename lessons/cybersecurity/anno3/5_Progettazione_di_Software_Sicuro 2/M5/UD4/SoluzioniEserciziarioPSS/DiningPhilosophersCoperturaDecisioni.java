import org.junit.Test;
//Si noti che non ci sono assert in quanto il metodo startEating() restituisce void
public class DiningPhilosophersCoperturaDecisioni {

	//"!forkUsed[philo] && !forkUsed[(philo + 1)%1000]" a true
	@Test
	public void testDecisioni1() {
		DiningPhilosophers t = new DiningPhilosophers();
		t.startEating(0);
	}

	//"!forkUsed[philo] && !forkUsed[(philo + 1)%1000]" a false
	@Test
	public void testDecisioni2() {
		DiningPhilosophers t = new DiningPhilosophers();
		t.startEating(0);
		t.startEating(1);
	}
}
