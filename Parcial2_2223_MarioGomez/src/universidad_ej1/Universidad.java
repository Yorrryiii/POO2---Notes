package universidad_ej1;

import java.util.ArrayList;
import java.util.List;

public class Universidad {
    private List<Asignatura> asignaturas;
    private List<Matricula> matriculas;

    public Universidad() {
        this.asignaturas = new ArrayList<>();
        this.matriculas = new ArrayList<>();
    }

    public void agregarAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
    }

    public void agregarMatricula(Matricula matricula) {
        matriculas.add(matricula);
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Universidad\n");
        sb.append("Asignaturas:\n");
        for (Asignatura asignatura : asignaturas) {
            sb.append(asignatura.getId()).append(" - ").append(asignatura.getNombre()).append(" (").append(asignatura.getCreditosECTS()).append(" ECTS)\n");
        }
        sb.append("\nMatrículas:\n");
        for (Matricula matricula : matriculas) {
            sb.append("Alumno: ").append(matricula.getAlumno().getNombre()).append(" ").append(matricula.getAlumno().getApellido()).append("\n");
            sb.append("Asignaturas matriculadas:\n");
            for (Asignatura asignatura : matricula.getAsignaturas()) {
                sb.append(asignatura.getId()).append(" - ").append(asignatura.getNombre()).append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
