package Universidad;

import java.util.ArrayList;

public class Escuela {

	private String nombre;
	private ArrayList<Grado> grados;
	
	@Override
	public String toString() {
		return nombre;
	}

	public ArrayList<Grado> getGrados() {
		return grados;
	}

	public void addGrado(Grado g) {
		grados.add(g);
	}
	
	/**
	 * busco el grado con el nombre y si no devuelvo null
	 * @param nombre
	 * @return
	 */
	public Grado getGrado(String nombre) {
		for(int i=0;i<grados.size();i++) {
			if(grados.get(i).getNombre().contentEquals(nombre)){
				return grados.get(i);
			}
		}
		
		return null;
	}
	
	public Escuela(String nombre) {
		this.nombre = nombre;
		grados = new ArrayList<Grado>();
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}
}
