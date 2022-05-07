package com.practica.genericas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DataFile {
	
	String fichero;
	File archivo = null;
	FileReader fr = null;
	BufferedReader br = null;
	String datas[] = null, data = null;
	

	public DataFile(String fichero) {
		this.fichero = fichero;
		
		try {
			this.archivo = new File(fichero);
			this.fr = new FileReader(this.archivo);
			this.br = new BufferedReader(this.fr);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}


	public File getArchivo() {
		return archivo;
	}


	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}




	public FileReader getFr() {
		return fr;
	}




	public void setFr(FileReader fr) {
		this.fr = fr;
	}




	public BufferedReader getBr() {
		return br;
	}




	public void setBr(BufferedReader br) {
		this.br = br;
	}




	public String[] getDatas() {
		return datas;
	}




	public void setDatas(String[] datas) {
		this.datas = datas;
	}




	public String getData() {
		return data;
	}




	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		String cadena = "";
		return cadena;
	}
	
}
