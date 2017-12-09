package uoc.ded.practica;

import java.util.Comparator;

import uoc.ei.tads.ListaEncadenada;
import uoc.ei.tads.Posicion;
import uoc.ei.tads.Recorrido;



/**
 * TAD que implementa una lista encadenada ordenada como una extensión de una 
 * lista encadena 
 *
 */
public class ListaEncadenadaOrdenada<E> extends ListaEncadenada<E> {
	
	Comparator<E> cmp;
	
	public ListaEncadenadaOrdenada(Comparator<E> cmp) {
		super();
		this.cmp= cmp;
	}
	
	
	/**
	 * reimplementación del método para añadir elementos con un criterio
	 * de ordenación definido
	 * @param e
	 */
	public void add(E e) {
		
		if (super.estaVacio()) super.insertarAlFinal(e);
		else {
			Recorrido<E> recorregut = super.posiciones();
			Posicion<E> p = null;
			boolean found=false;
			while (recorregut.haySiguiente() && (!found) ) {
				p = recorregut.siguiente();
				found = this.cmp.compare(p.getElem(), e)<=0;
			} 
			
			if (!found) {
				super.insertarAlFinal(e);
			}
			else {
				super.insertarAntesDe(p,  e);
			}
			
		}		
	}

}
