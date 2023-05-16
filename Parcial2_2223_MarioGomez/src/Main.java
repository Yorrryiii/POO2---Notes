// EXAMEN REALIZADO POR MARIO GÓMEZ PEÑA (alu.124956@usj.es)

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ej2.HelperUniversidad;
import ej3.AsignaturaNotFoundException;
import ej3.AsignaturaSinAlumnosException;
import ej3.DobleMatriculaException;
import ej3.SGA;
import universidad_ej1.Alumno;
import universidad_ej1.Asignatura;
import universidad_ej1.Matricula;
import universidad_ej1.Universidad;

public class Main {
    // Comprobación del Ejercicio 1 -> descomentar el código para ver el resultado del Ejercicio 1
        // public static void main(String[] args) {
            // Universidad universidad = new Universidad();

            // Asignatura asignatura1 = new Asignatura("Matemáticas", 6);
            // Asignatura asignatura2 = new Asignatura("Física", 4);
            // Asignatura asignatura3 = new Asignatura("Inglés", 3);

            // Alumno alumno1 = new Alumno("Juan", "Perez", 1);
            // Alumno alumno2 = new Alumno("Maria", "Gomez", 2);

            // Matricula matricula1 = new Matricula(alumno1);
            // matricula1.agregarAsignatura(asignatura1);
            // matricula1.agregarAsignatura(asignatura2);

            // Matricula matricula2 = new Matricula(alumno2);
            // matricula2.agregarAsignatura(asignatura1);
            // matricula2.agregarAsignatura(asignatura3);
            // universidad.agregarAsignatura(asignatura1);
            // universidad.agregarAsignatura(asignatura2);
            // universidad.agregarAsignatura(asignatura3);

            // universidad.agregarMatricula(matricula1);
            // universidad.agregarMatricula(matricula2);

            // System.out.println(universidad);
        // }

    // Comprobación del Ejercicio 2 -> descomentar el código para ver el resultado del Ejercicio 2
        // public static void main(String[] args) {
        //     Collection<Alumno> alumnos = HelperUniversidad.createAlumnos(generarIdsAlumnos(10));

        //     List<Asignatura> asignaturas = new ArrayList<>();
        //     asignaturas.addAll(HelperUniversidad.createAsignaturas(240, 3, 9));

        //     Universidad universidad = HelperUniversidad.createUniversidad(asignaturas,
        //     alumnos, 10);

        //     for (Asignatura asignatura : asignaturas) {
        //     System.out.println("Asignatura: " + asignatura.getNombre());
        //     System.out.println("Créditos ECTS: " + asignatura.getCreditosECTS());
        //     System.out.println();
        //     }
        // }

        // private static Set<Integer> generarIdsAlumnos(int cantidad) {
        //     List<Integer> idsAlumnos = new ArrayList<>();
        //     Random random = new Random();

        //     for (int i = 0; i < cantidad; i++) {
        //     int id = random.nextInt(9000) + 1000; // Genera números entre 1000 y 9999
        //     idsAlumnos.add(id);
        //     }

        //     return idsAlumnos;
        // }

    // Comprobación del Ejercicio 3 -> descomentar el código para ver el resultado del Ejercicio 3
        public static void main(String[] args) {
            try {
                // Generar universidad con diferentes escenarios anómalos
                Universidad universidad = generarUniversidadAnomala();
    
                // Generar el archivo de informe de matrícula
                Path carpetaInforme = Paths.get("informe_matricula");
                SGA.informeMatricula(carpetaInforme, universidad);
    
                // Validar los datos
                SGA.validarDatos(universidad);
            } catch (IOException e) {
                System.out.println("Error al generar el archivo de informe de matrícula.");
            } catch (DobleMatriculaException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (AsignaturaNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (AsignaturaSinAlumnosException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    
        private static Universidad generarUniversidadAnomala() {
            // Generar asignaturas
            Asignatura asignatura1 = new Asignatura("Matemáticas", 6);
            Asignatura asignatura2 = new Asignatura("Física", 4);
            Asignatura asignatura3 = new Asignatura("Inglés", 3);
    
            // Generar alumnos
            Alumno alumno1 = new Alumno("Juan", "Perez", 1);
            Alumno alumno2 = new Alumno("Maria", "Gomez", 2);
    
            // Generar matrículas con escenarios anómalos
            Matricula matricula1 = new Matricula(alumno1);
            matricula1.agregarAsignatura(asignatura1);
            matricula1.agregarAsignatura(asignatura1); // Doble matrícula en asignatura1
    
            Matricula matricula2 = new Matricula(alumno2);
            matricula2.agregarAsignatura(asignatura2);
            matricula2.agregarAsignatura(new Asignatura("Historia", 5)); // Asignatura no existente
    
            Matricula matricula3 = new Matricula(alumno2); // AsignaturaSinAlumnosException
    
            Universidad universidad = new Universidad();
            universidad.agregarAsignatura(asignatura1);
            universidad.agregarAsignatura(asignatura2);
            universidad.agregarAsignatura(asignatura3);
            universidad.agregarMatricula(matricula1);
            universidad.agregarMatricula(matricula2);
            universidad.agregarMatricula(matricula3);
    
            return universidad;
        }

}
