package Universidad;

import java.util.Objects;

public class Alumno {

	@Override
	public int hashCode() {
		
		//Objects.hash(DNI,apellido1);
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
		result = prime * result + ((apellido1 == null) ? 0 : apellido1.hashCode());
		result = prime * result + r;
		result = prime * result + g;
		result = prime * result + b;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		if (apellido1 == null) {
			if (other.apellido1 != null)
				return false;
		} else if (!apellido1.equals(other.apellido1))
			return false;
		if (b != other.b)
			return false;
		if (g != other.g)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (r != other.r)
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

	private String nombre;
	private String apellido1;
	private String telefono;
	private String DNI;
	private int r;
	private int g;
	private int b;
	
	public Alumno(String nombre, String apellido1, String telefono, String dNI, int r, int g,
			int b) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.telefono = telefono;
		DNI = dNI;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	public String toString() {
		return nombre+" "+apellido1;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDNI() {
		return DNI;
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}
	
	
	
}
