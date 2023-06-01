package Universidad;

import java.util.ArrayList;

public class Curso {

	private String nombre;
	private ArrayList<Alumno> alumnos;
	
	public Curso(String nombre) {
		this.nombre = nombre;
		alumnos = new ArrayList<Alumno>();
	}

	public ArrayList<Alumno> getAlumnos() {
		return alumnos;
	}

	@Override
	public String toString() {
		return nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void addAlumno(Alumno nuevoAlumno) {
		alumnos.add(nuevoAlumno);
	}
	
}
