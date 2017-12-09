package uoc.ded.practica;

/** 
 * Interfaz que define los mensajes necesarios para los mensajes de error
 *
 * @author   Equip docent de Disseny d'Estructura de Dades de la UOC
 * @version  Tardor 2017
 */
public interface Messages {
	
	public static final String LS = System.getProperty("line.separator");
	public static final String PREFIX = "\t";

	public static final String MOVIES_ALREADY_EXIST = "Movies already exist";
	public static final String USER_NOT_FOUND = "User not found";
	public static final String MOVIE_NOT_FOUND = "Movie not found";
	public static final String USER_WATCHING_NO_MOVIES= "User isn't watching any movie";
	public static final String MOVIE_DURATION_EXCEEDED = "Duration exceeded";
	public static final String NO_PAUSED_MOVIE = "No paused movie";
	public static final String NO_MOVIES = "There are no movies";
	public static final String NO_USERS = "There are no users";
	public static final String NO_WATCHED_MOVIES = "There are no whatches movies";


}
