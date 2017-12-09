package uoc.ded.practica;


import java.util.Date;

import uoc.ei.tads.Diccionario;
import uoc.ei.tads.DiccionarioVectorImpl;
import uoc.ei.tads.Iterador;

public class ContentManagerImpl implements ContentManager {
	
	DiccionarioOrderedVector<String, User> users;
	Diccionario<String, Movie> movies;
	
	OrderedVector<Movie> topMovies;
	
	public ContentManagerImpl() {
		this.users = new DiccionarioOrderedVector<String, User> (U, User.CMP);
		this.movies = new DiccionarioVectorImpl<String, Movie>(P);
		this.topMovies=new OrderedVector<Movie>(TOP_MOVIES, Movie.CMP);
	}

	
	@Override
	public void addUser(String idUser, String name, String surname) {
		
		User user = this.users.consultar(idUser);
		if (user == null) {
			this.users.insertar(idUser, new User(idUser, name, surname));
		}
		
	}

	@Override
	public void addMovie(String idMovie, String title, int duration, String director) throws DEDException {
		Movie m = this.movies.consultar(idMovie);
		if (m!=null) throw new DEDException(Messages.MOVIES_ALREADY_EXIST);
		
		m = new Movie(idMovie, title, duration, director);
		
		this.movies.insertar(idMovie, m);
	}

	@Override
	public void watchMovie(String idUser, String idMovie)  {

		User u = this.users.consultar(idUser);
		// @pre
		//if (u==null) ...
		
		
		Movie m = this.movies.consultar(idMovie);
		
		// @pre 
		//if (m==null) throw new DEDException (Messages.MOVIE_NOT_FOUND);
		
		u.setWatchingMovie(m);
		
	}

	@Override
	public void endMovie(String idUser, Date dateTime) throws DEDException {
		User u = this.getUser(idUser);
		if (u.isWatchingMovie()) {
			Movie m = u.watchingMovie();
			m.incViews();
			WatchedMovie wm = new WatchedMovie(m, dateTime);
			u.addWatchedMovie(wm);
			u.endMovie();
			this.topMovies.update(m);
		}
		else throw new DEDException(Messages.USER_WATCHING_NO_MOVIES);
	}

	@Override
	public void pauseMovie(String idUser, int minute) throws DEDException {
		PausedMovie pm = null;
		User u = this.getUser(idUser);
		if (u.isWatchingMovie()) {
			pm = u.pauseMovie(minute);	
		}
		else throw new DEDException(Messages.USER_WATCHING_NO_MOVIES);
		
	}

	@Override
	public void resumeMovie(String idUser) throws DEDException {
		User u = this.getUser(idUser);
		PausedMovie pm = u.pausedMovie();
		if (pm==null) throw new DEDException(Messages.NO_PAUSED_MOVIE);
		
		u.setWatchingMovie(pm.getMovie());	
		u.resumeMovie();
	}

	
	@Override
	public Iterador<WatchedMovie> getUserWatchedMovies(String idUser) throws DEDException {
		User u = this.users.consultar(idUser);
		
		// @pre
		// if (u == null) ...
		
		ListaEncadenadaOrdenada<WatchedMovie> ll = u.getWatchedMovies();	
		if (ll.estaVacio()) throw new DEDException(Messages.NO_WATCHED_MOVIES);
		
		
		return (ll.elementos());
	}


	@Override
	public Iterador<Movie> topMovies() throws DEDException {
		if (this.topMovies.estaVacio()) throw new DEDException(Messages.NO_MOVIES);
		return this.topMovies.elementos();
	}
	
	public User getUser(String idUser) throws DEDException {
		User u = this.users.consultar(idUser);
		if (u==null) throw new DEDException(Messages.USER_NOT_FOUND);
		return u;
	}


	@Override
	public Iterador<User> users() throws DEDException {
		if (this.users.estaVacio()) throw new DEDException(Messages.NO_USERS);
		return this.users.elementos();
	}


	@Override
	public Iterador<Movie> movies() throws DEDException {
		if (this.movies.estaVacio()) throw new DEDException(Messages.NO_MOVIES);
		return this.movies.elementos();
	}




    
}
