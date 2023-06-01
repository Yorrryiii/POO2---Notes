package Universidad;

import java.util.Random;

public class HelperEscuela {

	public static Escuela createEscuela(int alumnos, int cursos, int grados, String nombre) {

		Escuela e = new Escuela(nombre);

		for (int grado = 0; grado < grados; grado++) {

			Grado g = new Grado("Grado " + (grado + 1));
			e.getGrados().add(g);
			for (int curso = 0; curso < cursos; curso++) {

				Curso c = new Curso("Curso " + (curso + 1));
				g.getCursos().add(c);
				for (int alumno = 0; alumno < alumnos; alumno++) {

					Alumno a = new Alumno("Nombre " + (alumno + 1), "Apellido " + (alumno + 1),
							(600000000 + alumno) + "", ((10000000) + alumno) + "" + randomLetter(), randomCC(),
							randomCC(), randomCC());
					c.getAlumnos().add(a);

				}
			}

		}

		return e;

	}

	private static char randomLetter() {
		Random r = new Random();
		return (char) (r.nextInt(26) + 'a');

	}

	private static int randomCC() {
		Random r = new Random();
		return r.nextInt(256);
	}

	public static Grado getGradoForCurso(Escuela e, Curso c) {

		for (Grado g : e.getGrados()) {
			if (g.getCursos().contains(c))
				return g;
		}
		return null;
	}

	public static Curso getCursoForAlumno(Escuela e, Alumno a) {

		for (Grado g : e.getGrados()) {
			for (Curso c : g.getCursos()) {
				if (c.getAlumnos().contains(a))
					return c;
			}
		}
		return null;
	}

	public static Escuela createEscuela(String string, int cursos, int grados, int i) {
		return null;
	}
}
