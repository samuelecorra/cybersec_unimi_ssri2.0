import org.junit.Test;
// Si noti che non ci sono assert in quanto il metodo startEating() restituisce void
public class DiningPhilosophersCoperturaCondizioni {

	//"!forkUsed[philo]" a true
	//"!forkUsed[(philo + 1)%1000]" a true
	@Test
	public void testCondizioni1() {
		DiningPhilosophers t = new DiningPhilosophers();
		t.startEating(0);
	}

	//"!forkUsed[philo]" a false
	@Test
	public void testCondizioni2() {
		DiningPhilosophers t = new DiningPhilosophers();
		t.startEating(0);
		t.startEating(1);
	}

	//"!forkUsed[(philo + 1)%1000]" a false
	@Test
	public void testCondizioni3() {
		DiningPhilosophers t = new DiningPhilosophers();
		t.startEating(1);
		t.startEating(0);
	}
}
