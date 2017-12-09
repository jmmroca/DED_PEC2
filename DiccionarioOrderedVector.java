package uoc.ded.practica;

import java.util.Comparator;

import uoc.ei.tads.ClaveValor;
import uoc.ei.tads.ContenedorAcotado;
import uoc.ei.tads.DiccionarioVectorImpl;
import uoc.ei.tads.Iterador;

/**
 * TAD que implementa un vector ordenado que puede ser accesible por clave. La ordenación
 * se determina con el comparador
 */
public class DiccionarioOrderedVector<K,V> extends DiccionarioVectorImpl<K,V> implements ContenedorAcotado<V>{
	private static final int KEY_NOT_FOUND = -1;

	
	private Comparator<K> comparator;


	public DiccionarioOrderedVector(int max, Comparator<K> comparator) {
		super(max);
		this.comparator = comparator;
	}


	@Override
	public boolean estaLleno() {
		return (super.n==super.diccionario.length);
	}

	
	/**
	 * Se añade un elemento en la última posición y se reorganiza situándose en su ubicación,
	 * según la relación de orden definida por el comparador 
	 */
	public void insertar(K clau,V obj) {
		super.insertar(clau, obj);

		// add Key-Value  
		int i=this.n-1;
		
		boolean done=false;
		
		ClaveValor kv;
		ClaveValor last=this.diccionario[this.n-1];
		
		while (i>=0 && !done) {
			kv = this.diccionario[i]; 
			
			if (this.comparator.compare((K) kv.getClave(), clau)>0) {
				// swap
				this.diccionario[i] = last;
				this.diccionario[i+1]=kv;
				last = this.diccionario[i];				
			}

			i--;
		}
		
	}
	

	/**
	 * método que consulta un elemento sobre el vector ordenado
	 */
	public V consultar(K clau) {
		int pos = this.binary_search(clau, 0, this.n-1);
		return (pos!=this.KEY_NOT_FOUND?this.diccionario[pos].getValor():null);		
	}

	/**
	 * método que proporciona un elemento y retorna una excepción en el caso que no exista
	 * el elemento
	 * @param clave
	 * @param mensaje
	 * @return
	 * @throws DEDException
	 */
	public V consultar(K clau, String message) throws DEDException {
		V value=this.consultar(clau);
		if (value==null) throw new DEDException(message);
		return value;
	}

	
	/**
	 * método que realiza una búsqueda dicotómica
	 * @param key clave a buscar
	 * @param imin posición mínima
	 * @param imax posición màxima
	 * @return
	 */
	private int binary_search(K key, int imin, int imax)
	{
	  // test if array is empty
	  if (imax < imin)
	    // set is empty, so return value showing not found
	    return KEY_NOT_FOUND;
	  else
	    {
	      // calculate midpoint to cut set in half
	      int imid = midpoint(imin, imax);
	 
	      // three-way comparison
	     // if (this.diccionari[imid] > key)
	      
	     // System.out.println("imin: "+imin+" "+imid+" "+imax);
	      
	      if (this.comparator.compare(this.diccionario[imid].getClave(), key)>0)
	        // key is in lower subset
	        return binary_search(key, imin, imid-1);
	      //else if (this.diccionari[imid] < key)
	      else if (this.comparator.compare(this.diccionario[imid].getClave(), key)<0)
	    	  
	        // key is in upper subset
	        return binary_search(key, imid+1, imax);
	      else
	        // key has been found
	        return imid;
	    }
	}
	
	/**
	 * método que calcula el punto medio 
	 */
	private int midpoint(int imin, int imax) {
		return imin + ((imax - imin) / 2);
	}


	/**
	 * método de prueba
	 * @param args
	 */
	public static void main(String[] args) {
		Comparator<String> cmp = new Comparator<String>() {

			@Override
			public int compare(String arg0, String arg1) {
				// TODO Auto-generated method stub
				return arg0.compareTo(arg1);
			}
			
		};
		DiccionarioOrderedVector<String, Integer> v = new DiccionarioOrderedVector<String,Integer>(10, cmp);
		
		v.insertar("09", 9);
		v.insertar("07", 7);
		v.insertar("02", 2);
		v.insertar("03", 3);
		v.insertar("04", 4);
		v.insertar("05", 5);
		v.insertar("06", 6);
		v.insertar("01", 1);

		
		System.out.println("estaVacio "+v.estaVacio());
		
		for (Iterador<Integer> it = v.elementos(); it.haySiguiente();) {
			System.out.println(it.siguiente());
		}
		
		v.insertar("09", 9);
		v.insertar("10", 10);
		System.out.println("estaVacio "+v.estaVacio());
		v.insertar("11", 11);
		System.out.println("estaVacio "+v.estaVacio());
		

		
		
		for (Iterador<Integer> it = v.elementos(); it.haySiguiente();) {
			System.out.println(it.siguiente());
		}

		
		
		System.out.println("1: "+ v.consultar("01"));
		System.out.println("5: "+ v.consultar("05"));
		
		
		System.out.println("11: "+ v.consultar("11"));

		// not found
		System.out.println("1: "+ v.consultar("1"));
		System.out.println("5: "+ v.consultar("5"));

	}



	
	
	
}
