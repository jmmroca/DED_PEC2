package uoc.ded.practica;

/**
 * Clase que modela una película que se ha pausado
 * se indica el minuto en el que se ha pausado la película
 * y el minuto en el que se realizó la pausa.
 *
 */
public class PausedMovie {
	private Movie m;
	private int minute;
	
	public PausedMovie(Movie m, int minute) {
		this.m = m; 
		this.minute=minute;
	}
	
	public Movie getMovie() {
		return this.m;
	}
	
	public String toString(String prefix) {
		StringBuffer sb = new StringBuffer(prefix+"movie: ").append(this.m.getTitle()).append(", minute: ").append(this.minute);
		return sb.toString();
	}

	public String toString() {
		// TODO Auto-generated method stub
		return toString("");
	}
	

}
