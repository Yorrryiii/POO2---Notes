package universidad_ej1;

import java.util.ArrayList;
import java.util.List;

public class Matricula {
    private Alumno alumno;
    private List<Asignatura> asignaturas;

    public Matricula(Alumno alumno) {
        this.alumno = alumno;
        this.asignaturas = new ArrayList<>();
    }

    public void agregarAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
    }

    public int obtenerCantidadAsignaturas() {
        return asignaturas.size();
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }
}
