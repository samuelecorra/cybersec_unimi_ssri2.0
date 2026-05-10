/**
 * Il centro e' nelle coordinate (0, 0)
 *
 */
public class Punto2D {
	// campi
	private double x;
	private double y;

	// Costruttori
	public Punto2D() {
		x = 0;
		y = 0;
	}

	public Punto2D(double x) {
		this.x = x;
		this.y = x;
		//this(x, x);
	}

	public Punto2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// metodi set e get
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setXY(double x, double y) {
		setX(x);
		setY(y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	// metodi generici
	public double distanza2D(Punto2D p) {
		double d = Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
		return d;
	}

	public Punto2D simmetrico() {
		Punto2D p = new Punto2D();
		p.x = -this.x;
		p.y = -this.y;
		return p;
	}

	/**
	 *  Override del metodo equals della classe Object.
	 */
	@Override
	public boolean equals(Object p) {
		boolean uguale = false;
		Punto2D punto = null;
		if (p instanceof Punto2D) {
			punto = (Punto2D) p;
			if (punto.x == this.x && punto.y == this.y)
				uguale = true;
		}
		return uguale;
	}

	/**
	 * Override del metodo toString della classe Object.
	 */
	@Override
	public String toString() {
		String s;
		s = "(" + x + ", " + y + ")";
		return s;
	}	
}
