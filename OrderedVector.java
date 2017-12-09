package uoc.ded.practica;

import java.util.Comparator;

import uoc.ei.tads.ClaveValor;
import uoc.ei.tads.ContenedorAcotado;
import uoc.ei.tads.DiccionarioVectorImpl;
import uoc.ei.tads.Iterador;
import uoc.ei.tads.IteradorVectorImpl;

/**
 * TAD que implementa un vector ordenado. La ordenación del vector se determina
 * con el comparador
 */
public class OrderedVector<E> implements ContenedorAcotado<E>{

	
	private Comparator<E> comparator;
	
	private E[] data;
	private int len;


	public OrderedVector(int max, Comparator<E> comparator) {
		this.comparator = comparator;
		this.data= (E[])new Object[max];
		this.len=0;
	}

	public E elementAt(int i) {
		return this.data[i];
	}
	
	/**
	 * método que indica si un elemento es igual que que el segundo
	 * @param elem1
	 * @param elem2
	 * @return
	 */
	private boolean compare(E elem1, E elem2) {
		boolean res = ((Comparable<E>)elem1).compareTo(elem2)==0;
		return res;

	}
	
	public void rshift(int i) {
		// desplaçament de tots els elements una posició
		int p=this.len-1;
		while (p>=i) {
			this.data[p+1]=this.data[p];
			p--;
		}
	}

	public void lshift(int i) {
		// desplazamiento de todos los elementos una posición
		int p=i;
		while (p<this.len) {
			if(p < this.len - 1)
				this.data[p]=this.data[p+1];
			else
				this.data[p]=null;
			p++;
		}
	}

	
	public void update(E vIn) {
		int i = 0;
		boolean end=false;
		E v = null;
		
		// Si existe el elmentos se borra para volverlo a añadir en su posición
		this.delete(vIn);
		
		if (this.estaLleno()) {
			E pOut = this.last();
			if (comparator.compare(pOut, vIn)<0) {
				this.delete(pOut);
				this.update(vIn);
			} 
		}
		else {
		
			// recorrido para determinar la posición a añadir
			
			while (i<this.len && this.data[i]!=null && this.comparator.compare(this.data[i], vIn)>=0) 
				i++;
			
			// desplazamiento a la derecha de todos los elementos
			rshift(i);
			
			// se añade el elemento en la posición 
			this.data[i]=vIn;
			this.len++;
			}	
		
	}
	
	public void delete (E elem) {
		int i=0;
		boolean found=false;
		
		while (!found && i<this.len) 
			found= (compare(elem, this.data[i++]));		
		
		if (found) {
			lshift(i-1);
			this.len--;
		}
		
	}

	
	@Override
	public Iterador<E> elementos() {
		return (Iterador<E>)new IteradorVectorImpl(this.data, this.len,0);

	}

	@Override
	public boolean estaVacio() {
		return this.len==0;
	}


	@Override
	public int numElems() {
		return this.len;
	}

	@Override
	public boolean estaLleno() {
		return this.len==this.data.length;
	}

	/**
	 * mètode de prova
	 * @param args
	 */
	public static void main(String[] args) {
		Comparator<Integer> cmp = new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0.compareTo(arg1);
			}
			
		};
		OrderedVector<Integer> v = new OrderedVector<Integer>(10, cmp);
		
		v.update(10);
		v.update(20);
		v.update(40);
		v.update(50);
		v.update(60);
		v.update(70);
		v.update(80);
		v.update(90);
		v.update(30);
		v.update(100);
		////
		v.update(80);
		v.update(81);
		v.update(82);
		v.update(83);
		v.update(84);
		v.update(85);
				

		System.out.println("estaPle "+v.estaLleno());
		log(v);
		
		System.out.println("delete 83");
		v.delete(83);
		
		log(v);
		

		System.out.println("post delete 83");
		
		v.update(9);
		v.update(10);
		v.update(11);
		
		log(v);

	
	}
	
	private static void log(OrderedVector v){
		for (Iterador<Integer> it = v.elementos(); it.haySiguiente();) {
			System.out.print(it.siguiente()+" ");
		}		
		System.out.println();
	}

	public E last() {
		// TODO Auto-generated method stub
		return this.data[this.len-1];
	}
	
	
}
