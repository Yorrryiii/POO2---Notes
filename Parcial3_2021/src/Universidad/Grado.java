package Universidad;

import java.util.ArrayList;

public class Grado {

	private String nombre;
	private ArrayList<Curso> cursos;
	
	@Override
	public String toString() {
		return nombre;
	}

	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public Grado(String nombre) {
		this.nombre = nombre;
		cursos = new ArrayList<Curso>();
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	public void addCurso(Curso curso) {
	}
	
}
