package ej3;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import universidad_ej1.Asignatura;
import universidad_ej1.Matricula;
import universidad_ej1.Universidad;

public class SGA {
    public static void informeMatricula(Path carpeta, Universidad universidad) {
        if (!Files.exists(carpeta)) {
            try {
                Files.createDirectory(carpeta);
            } catch (IOException e) {
                System.out.println("Error al crear la carpeta: " + carpeta);
                return;
            }
        }

        for (Matricula matricula : universidad.getMatriculas()) {
            int idAlumno = matricula.getAlumno().getId();
            Path carpetaAlumno = Paths.get(carpeta.toString(), String.valueOf(idAlumno));
            try {
                Files.createDirectory(carpetaAlumno);
            } catch (IOException e) {
                System.out.println("Error al crear la carpeta del alumno: " + carpetaAlumno);
                continue;
            }

            for (Asignatura asignatura : matricula.getAsignaturas()) {
                int idAsignatura = asignatura.getId();
                String nombreAsignatura = asignatura.getNombre();
                int creditosAsignatura = asignatura.getCreditosECTS();

                Path ficheroAsignatura = Paths.get(carpetaAlumno.toString(), String.valueOf(idAsignatura) + ".txt");
                try (FileWriter fileWriter = new FileWriter(ficheroAsignatura.toString())) {
                    fileWriter.write("Nombre: " + nombreAsignatura + "\n");
                    fileWriter.write("Créditos: " + creditosAsignatura + "\n");
                } catch (IOException e) {
                    System.out.println("Error al crear el fichero de la asignatura: " + ficheroAsignatura);
                }
            }
        }

        System.out.println("Informe de matrícula generado correctamente.");
    }

    public static void informeProfesores(Universidad universidad) {
        int[] contadorAlumnosPorAsignatura = new int[universidad.getAsignaturas().size()];

        for (Matricula matricula : universidad.getMatriculas()) {
            for (Asignatura asignatura : matricula.getAsignaturas()) {
                int indice = universidad.getAsignaturas().indexOf(asignatura);
                contadorAlumnosPorAsignatura[indice]++;
            }
        }

        System.out.println("Informe de profesores:");
        for (int i = 0; i < universidad.getAsignaturas().size(); i++) {
            Asignatura asignatura = universidad.getAsignaturas().get(i);
            int contadorAlumnos = contadorAlumnosPorAsignatura[i];
            System.out.println("Asignatura: " + asignatura.getId() + " - Alumnos matriculados: " + contadorAlumnos);
        }
    }
    
    public static void validarDatos(Universidad universidad) throws IOException, DobleMatriculaException, AsignaturaNotFoundException, AsignaturaSinAlumnosException {
        validarDobleMatricula(universidad);
        validarExistenciaAsignaturas(universidad);
        validarAsignaturasSinAlumnos(universidad);
    }

    private static void validarDobleMatricula(Universidad universidad) throws DobleMatriculaException {
        Set<Integer> asignaturasMatriculadas = new HashSet<>();
        for (Matricula matricula : universidad.getMatriculas()) {
            for (Asignatura asignatura : matricula.getAsignaturas()) {
                int idAsignatura = asignatura.getId();
                if (asignaturasMatriculadas.contains(idAsignatura)) {
                    throw new DobleMatriculaException("Alumno matriculado dos veces en la misma asignatura: " + idAsignatura);
                }
                asignaturasMatriculadas.add(idAsignatura);
            }
        }
    }

    private static void validarExistenciaAsignaturas(Universidad universidad) throws AsignaturaNotFoundException {
        for (Matricula matricula : universidad.getMatriculas()) {
            for (Asignatura asignatura : matricula.getAsignaturas()) {
                boolean asignaturaEncontrada = false;
                for (Asignatura a : universidad.getAsignaturas()) {
                    if (asignatura.getId() == a.getId()) {
                        asignaturaEncontrada = true;
                        break;
                    }
                }
                if (!asignaturaEncontrada) {
                    throw new AsignaturaNotFoundException("Alumno con matrícula en una asignatura que no existe: " + asignatura.getId());
                }
            }
        }
    }

    private static void validarAsignaturasSinAlumnos(Universidad universidad) throws AsignaturaSinAlumnosException {
        for (Asignatura asignatura : universidad.getAsignaturas()) {
            boolean asignaturaConAlumnos = false;
            for (Matricula matricula : universidad.getMatriculas()) {
                for (Asignatura a : matricula.getAsignaturas()) {
                    if (asignatura.getId() == a.getId()) {
                        asignaturaConAlumnos = true;
                        break;
                    }
                }
                if (asignaturaConAlumnos) {
                    break;
                }
            }
            if (!asignaturaConAlumnos) {
                throw new AsignaturaSinAlumnosException("Una asignatura no tiene alumnos matriculados: " + asignatura.getId());
            }
        }
    }
}
