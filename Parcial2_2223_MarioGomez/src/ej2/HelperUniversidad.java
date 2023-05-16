package ej2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import universidad_ej1.Alumno;
import universidad_ej1.Asignatura;
import universidad_ej1.Matricula;
import universidad_ej1.Universidad;

public class HelperUniversidad {
    public static Collection<Alumno> createAlumnos(Set<Integer> numeros) {
        Collection<Alumno> alumnos = new ArrayList<>();
        Random random = new Random();

        for (int numero : numeros) {
            String nombre = generarNombreAleatorio(numero);
            String apellido = generarApellidoAleatorio(numero);
            int id = random.nextInt(1000);
            Alumno alumno = new Alumno(nombre, apellido, id);
            alumnos.add(alumno);
        }

        return alumnos;
    }

    private static String generarNombreAleatorio(int numero) {
        int numCaracteres = numero % 10;
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < numCaracteres; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            sb.append(c);
        }

        return sb.toString();
    }

    private static String generarApellidoAleatorio(int numero) {
        int numCaracteres = (numero / 10) % 10;
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < numCaracteres; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            sb.append(c);
        }

        return sb.toString();
    }

    public static Collection<Asignatura> createAsignaturas(int totalCreditos, int minCreditos, int maxCreditos) {
        List<Asignatura> asignaturas = new ArrayList<>();
        Random random = new Random();

        int creditosAsignados = 0;
        int asignaturaIndex = 1;
        while (creditosAsignados < totalCreditos) {
            int creditos = random.nextInt(maxCreditos - minCreditos + 1) + minCreditos;
            if (creditosAsignados + creditos > totalCreditos) {
                creditos = totalCreditos - creditosAsignados; // Ajusto el valor de la ultima asignatura
            }

            Asignatura asignatura = new Asignatura("Asignatura" + asignaturaIndex, creditos);
            asignaturas.add(asignatura);

            creditosAsignados += creditos;
            asignaturaIndex++;
        }

        return asignaturas;
    }

    public static Universidad createUniversidad(Collection<Asignatura> asignaturas, Collection<Alumno> alumnos, int asignaturasMatriculadas) {
        Universidad universidad = new Universidad();

        for (Asignatura asignatura : asignaturas) {
            universidad.agregarAsignatura(asignatura);
        }

        for (Alumno alumno : alumnos) {
            List<Asignatura> asignaturasMatricula = seleccionarAsignaturasMatricula(asignaturas, asignaturasMatriculadas);
            Matricula matricula = new Matricula(alumno);
            for (Asignatura asignatura : asignaturasMatricula) {
                matricula.agregarAsignatura(asignatura);
            }
            universidad.agregarMatricula(matricula);
        }

        return universidad;
    }

    private static List<Asignatura> seleccionarAsignaturasMatricula(Collection<Asignatura> asignaturas, int asignaturasMatriculadas) {
        List<Asignatura> asignaturasSeleccionadas = new ArrayList<>(asignaturas);
        Random random = new Random();

        while (asignaturasSeleccionadas.size() > asignaturasMatriculadas) {
            int indiceEliminar = random.nextInt(asignaturasSeleccionadas.size());
            asignaturasSeleccionadas.remove(indiceEliminar);
        }

        return asignaturasSeleccionadas;
    }

}

