package uoc.ded.practica;

/**
 * Excepción específica del gestor
 *
 * @author   Equipo docente DED de la UOC
 * @version  Tardor 2016
 */
public class DEDException extends Exception
{
 
	private static final long serialVersionUID = -2577150645305791318L;

  /**
    * Constructor con un parámetro.
    * @param msg  mensaje asociado a la excepción
    */
   public DEDException(String msg) { super(msg); }
}
