package uoc.ded.practica;

import java.util.Comparator;

/**
 * Clase que modela una entidad de información película
 *
 */
public class Movie implements Comparable<Movie>{

	private String idMovie;
	private String title;
	private int duration;
	private String director;
	private int views;

	public static final Comparator<Movie> CMP =  new Comparator<Movie>() {

		@Override
		public int compare(Movie m1, Movie m2) {
			return m1.views()- m2.views();
		}
	};

	public Movie(String idMovie, String title, int duration, String director) {
		this.idMovie=idMovie;
		this.title = title;
		this.duration=duration;
		this.director = director;
	}
	
	public int views() {
		return this.views;
	}
	
	public void incViews() {
		this.views++;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public String getTitle() {
		return this.title;
	}

	public String toString() {
		return toString("");
		
	}

	public String toString(String prefix) {
		StringBuffer sb = new StringBuffer(prefix).append("idMovie:").append(this.idMovie).append(Messages.LS);
		sb.append(prefix).append("title: ").append(this.title).append(Messages.LS);
		sb.append(prefix).append("director: ").append(this.director).append(Messages.LS);
		sb.append(prefix).append("duration: ").append(this.duration).append(Messages.LS);
		sb.append(prefix).append("views: ").append(this.views()).append(Messages.LS);
		
		return sb.toString();
	}
	
	public int compareTo(Movie m) {
		return this.idMovie.compareTo(m.idMovie);
	}

	public String getIdMovie() {
		return this.idMovie;
	}

}
