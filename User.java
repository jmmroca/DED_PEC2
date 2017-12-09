package uoc.ded.practica;

import java.util.Comparator;

/**
 * Clase que modela una entidad de información usuario
 *
 */
public class User {
	
	private String idUser;
	private String name;
	private String surname;
	
	private PausedMovie pausedMovie;
	
	private Movie watchingMovie;
	private ListaEncadenadaOrdenada<WatchedMovie> watchedMovies;
	
	public static Comparator<String> CMP = new Comparator<String>() {

		@Override
		public int compare(String idUser1, String idUser2) {
			int ret = idUser1.compareTo(idUser2);
			return ret;
		}
	};
	
	public User(String idUser, String name, String surname) {
		this.idUser=idUser;
		this.name = name;
		this.surname = surname;
		this.watchingMovie=null;
		this.pausedMovie=null;
		this.watchedMovies = new ListaEncadenadaOrdenada<WatchedMovie>(WatchedMovie.CMP);
	}

	public String getIdUser() {
		return this.idUser;
	}

	public boolean isWatchingMovie() {
		return (this.watchingMovie!=null);
	}

	public Movie watchingMovie() {
		return this.watchingMovie;
	}

	public void addWatchedMovie(WatchedMovie wm) {
		this.watchedMovies.add(wm);
	}

	public PausedMovie pauseMovie(int minute) throws DEDException {
		PausedMovie pm = null;
		Movie m = this.watchingMovie();
		if (minute > m.getDuration()) throw new DEDException(Messages.MOVIE_DURATION_EXCEEDED);
		else {		
			pm = new PausedMovie(m, minute);
			this.pausedMovie = pm;
			this.watchingMovie = null;
		}
		return pm;
	}

	public PausedMovie pausedMovie() {
		return this.pausedMovie;
	}

	public void setWatchingMovie(Movie movie) {
		this.watchingMovie=movie;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("id: ").append(this.idUser).append(" ");
		sb.append("name: ").append(this.name).append(" ");
		sb.append("surname: ").append(this.surname).append(" ");
		if (this.pausedMovie!=null) sb.append(Messages.LS+"paused movie: "+Messages.LS).append(this.pausedMovie.toString("\t")).append(Messages.LS);
		if (this.isWatchingMovie()) sb.append(Messages.LS+"watching movie: "+Messages.LS).append(this.watchingMovie.toString("\t")).append(Messages.LS);
		
		return sb.toString();
	}

	public void resumeMovie() {
		this.pausedMovie=null;
	}

	public void endMovie() {
		this.watchingMovie=null;
	}

	public ListaEncadenadaOrdenada<WatchedMovie> getWatchedMovies() {
		return this.watchedMovies;		
	}

}
