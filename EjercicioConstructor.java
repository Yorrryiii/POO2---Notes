public class EjercicioConstructor {
    public static void main(String[] args) {
        new EjercicioConstructor();
    }

    EjercicioConstructor() {
        this(1);
        System.out.println("Constructor sin parámetros");
    }

    EjercicioConstructor(int i) {
        this(1, 2);
        System.out.println("Constructor con un parámetro");
    }

    EjercicioConstructor(int i, int j) {
        System.out.println("Constructor con dos parámetros");
    }

    {
        System.out.println("Bloque de inicialización 1");
    }

    {
        System.out.println("Bloque de inicialización 2");
    }

    {
        System.out.println("Bloque de inicialización 3");
    }
}
