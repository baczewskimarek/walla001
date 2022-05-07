package com.practica.lista;

import com.practica.genericas.Coordenada;
import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

public class ListaContactos {
	private NodoTemporal lista;
	private int size;
	
	/**
	 * Insertamos en la lista de nodos temporales, y a la vez inserto en la lista de nodos de coordenadas. 
	 * En la lista de coordenadas metemos el documento de la persona que está en esa coordenada 
	 * en un instante 
	 */
	@SuppressWarnings("null")
	public void insertarNodoTemporal (PosicionPersona p) {
		NodoTemporal aux = lista, ant=null;
		boolean salir=false,  encontrado = false;
		
		while (aux!=null && !salir) {
			if(aux.getFecha().compareTo(p.getFechaPosicion())==0) {
				encontrado = true;
				salir = true;
				extracted(p, aux);
			}else if(aux.getFecha().compareTo(p.getFechaPosicion())<0) {
				ant = aux;
				aux=aux.getSiguiente();
			}else if(aux.getFecha().compareTo(p.getFechaPosicion())>0) {
				salir=true;
			}
		}

		if(!encontrado) {
			insertarNodoNoEncontrado(p, aux, ant);
			this.size++;
			
		}
	}

	private void insertarNodoNoEncontrado(PosicionPersona p, NodoTemporal aux, NodoTemporal ant) {
		NodoTemporal nuevo = nodoTemporalNoEncontrado(p);
		
		if(ant!=null) {
			nuevo.setSiguiente(aux);
			ant.setSiguiente(nuevo);
		}else {
			nuevo.setSiguiente(lista);
			lista = nuevo;
		}
	}

	private void extracted(PosicionPersona p, NodoTemporal aux) {
		NodoPosicion npActual = aux.getListaCoordenadas();
		NodoPosicion npAnt=null;		
		
		nodoTemporalNuevo(p, aux, npActual, npAnt);
	}

	private NodoTemporal nodoTemporalNoEncontrado(PosicionPersona p) {
		NodoTemporal nuevo = new NodoTemporal();
		nuevo.setFecha(p.getFechaPosicion());

		
		NodoPosicion npActual = nuevo.getListaCoordenadas();
		NodoPosicion npAnt=null;	
		if (!npEncontrado(npActual,npAnt,p.getCoordenada())) {
		
			nodoFuncion1(p, nuevo, npAnt);			
		}
		return nuevo;
	}

	private void nodoTemporalNuevo(PosicionPersona p, NodoTemporal aux, NodoPosicion npActual, NodoPosicion npAnt) {
		boolean npEncontrado = false;
		while (npActual!=null && !npEncontrado) {
			if(npActual.getCoordenada().equals(p.getCoordenada())) {
				npEncontrado=true;
				npActual.setNumPersonas(npActual.getNumPersonas()+1);
			}else {
				npAnt = npActual;
				npActual = npActual.getSiguiente();
			}
		}
		if(!npEncontrado) {
			nodoFuncion1(p, aux, npAnt);			
		}
	}

	private void nodoFuncion1(PosicionPersona p, NodoTemporal nuevo, NodoPosicion npAnt) {
		NodoPosicion npNuevo = new NodoPosicion(p.getCoordenada(),  1, null);				
		if(nuevo.getListaCoordenadas()==null)
			nuevo.setListaCoordenadas(npNuevo);
		else
			npAnt.setSiguiente(npNuevo);
	}
	
	private boolean npEncontrado(NodoPosicion npActual,NodoPosicion npAnt, Coordenada p) {
		boolean npEncontrado = false;
		while (npActual!=null && !npEncontrado) {
			if(npActual.getCoordenada().equals(p)) {
				npEncontrado=true;
				npActual.setNumPersonas(npActual.getNumPersonas()+1);
			}else {
				npAnt = npActual;
				npActual = npActual.getSiguiente();
			}
		}
		return npEncontrado;
	}
	
	private boolean buscarPersona (String documento, NodoPersonas nodo) {
		NodoPersonas aux = nodo;
		while(aux!=null) {
			if(aux.getDocumento().equals(documento)) {
				return true;				
			}else {
				aux = aux.getSiguiente();
			}
		}
		return false;
	}
	
	private void insertarPersona (String documento, NodoPersonas nodo) {
		NodoPersonas aux = nodo, nuevo = new NodoPersonas(documento, null);
		while(aux.getSiguiente()!=null) {				
			aux = aux.getSiguiente();				
		}
		aux.setSiguiente(nuevo);		
	}
	
	public int personasEnCoordenadas () {
		NodoPosicion aux = this.lista.getListaCoordenadas();
		if(aux==null)
			return 0;
		else {
			int cont;
			for(cont=0;aux!=null;) {
				cont += aux.getNumPersonas();
				aux=aux.getSiguiente();
			}
			return cont;
		}
	}
	
	public int tamanioLista () {
		return this.size;
	}

	public String getPrimerNodo() {
		NodoTemporal aux = lista;
		String cadena = aux.getFecha().getFecha().toString();
		cadena+= ";" +  aux.getFecha().getHora().toString();
		return cadena;
	}

	/**
	 * Métodos para comprobar que insertamos de manera correcta en las listas de 
	 * coordenadas, no tienen una utilidad en sí misma, más allá de comprobar que
	 * nuestra lista funciona de manera correcta.
	 */
	public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		if(this.size==0)			return 0;
		NodoTemporal aux = lista;
		int cont = 0;
		while(aux!=null) {
			if(aux.getFecha().compareTo(inicio)>=0 && aux.getFecha().compareTo(fin)<=0) {
				cont = contadorNodo(cont, aux.getListaCoordenadas());				
			}
			aux=aux.getSiguiente();
			
		}
		return cont;
	}

	private int contadorNodo(int cont, NodoPosicion nodo) {
		while(nodo!=null) {
			cont = cont + nodo.getNumPersonas();
			nodo = nodo.getSiguiente();
		}
		return cont;
	}
	
	
	
	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		if(this.size==0)
			return 0;
		NodoTemporal aux = lista;
		int cont = 0;
		while(aux!=null) {
			if(aux.getFecha().compareTo(inicio)>=0 && aux.getFecha().compareTo(fin)<=0) {
				NodoPosicion nodo = aux.getListaCoordenadas();
				while(nodo!=null) {
					cont = cont + 1;
					nodo = nodo.getSiguiente();
				}				
				aux = aux.getSiguiente();
			}else {
				aux=aux.getSiguiente();
			}
		}
		return cont;
	}
	
	
	
	@Override
	public String toString() {
		String cadena="";
		int a,cont;
		cont=0;
		NodoTemporal aux = lista;
		for(cont=1; cont<size; cont++) {
			cadena += aux.getFecha().getFecha().toString();
			cadena += ";" +  aux.getFecha().getHora().toString() + " ";
			aux=aux.getSiguiente();
		}
		cadena += aux.getFecha().getFecha().toString();
		cadena += ";" +  aux.getFecha().getHora().toString();
		return cadena;
	}
	
	
	
}
