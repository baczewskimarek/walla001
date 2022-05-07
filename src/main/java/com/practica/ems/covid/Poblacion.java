package com.practica.ems.covid;


import java.util.Iterator;
import java.util.LinkedList;

import com.practica.excecption.EmsDuplicatePersonException;
import com.practica.excecption.EmsPersonNotFoundException;
import com.practica.genericas.FechaHora;
import com.practica.genericas.Persona;

public class Poblacion {
	LinkedList<Persona> lista ;

	public Poblacion() {
		super();
		this.lista = new LinkedList<Persona>();
	}
	
	public LinkedList<Persona> getLista() {
		return lista;
	}

	public void setLista(LinkedList<Persona> lista) {
		this.lista = lista;
	}

	public void addPersona (Persona persona) throws EmsDuplicatePersonException {
		try {
			findPersona(persona.getDocumento());
			throw new EmsDuplicatePersonException();
		} catch (EmsPersonNotFoundException e) {
			lista.add(persona);
		} 
	}
	
	public void delPersona(String documento) throws EmsPersonNotFoundException {
		int pos=-1;
		try {
			pos = findPersona(documento);
		} catch (EmsPersonNotFoundException e) {
			throw new EmsPersonNotFoundException();
		}
		lista.remove(pos);		
	}
	
	public int findPersona (String documento) throws EmsPersonNotFoundException {
		int cont=0;
		Iterator<Persona> it = lista.iterator();
		while (it.hasNext() ) {
			Persona persona = it.next();
			cont++;
			if(persona.getDocumento().equals(documento)) {
				return cont;
			}
		}		
		throw new EmsPersonNotFoundException();
	}
	
	public void printPoblacion() {   
	    for(int i = 0; i < lista.size(); i++) {	    	
	    	FechaHora fecha = lista.get(i).getFechaNacimiento();
	        System.out.printf("%d;%s;", i, lista.get(i).getDocumento());	              
	        System.out.printf("%s,%s;",lista.get(i).getApellidos(), lista.get(i).getNombre());	        
	        System.out.printf("%s;", lista.get(i).getEmail());
	        System.out.printf("%s,%s;", lista.get(i).getDireccion(), lista.get(i).getCp());	        
	        System.out.printf("%02d/%02d/%04d\n", fecha.getFecha().getDia(), fecha.getFecha().getMes(), fecha.getFecha().getAnio());	        
	    }
	}

	@Override
	public String toString() {
		String cadena = "";
		for(int i = 0; i < lista.size(); i++) {
			FechaHora fecha = lista.get(i).getFechaNacimiento();    	    	
	        cadena+=String.format("%s;", lista.get(i).getDocumento());	              
	        cadena+=String.format("%s,%s;",lista.get(i).getApellidos(), lista.get(i).getNombre());	        
	        cadena+=String.format("%s;", lista.get(i).getEmail());
	        cadena+=String.format("%s,%s;", lista.get(i).getDireccion(), lista.get(i).getCp());	        
	        cadena+=String.format("%02d/%02d/%04d\n", fecha.getFecha().getDia(), 
	        		fecha.getFecha().getMes(), 
	        		fecha.getFecha().getAnio());
		}
		return cadena;
	}
	
	
	
}
