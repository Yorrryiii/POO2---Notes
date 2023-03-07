public class EjercicioEncapsulamiento_Matriz {
    public static void main(String[] args) {

    }

    public class Matriz3x3Array_EjercicioEncapsulamiento {
        private double[] elementos;
    
        public Matriz3x3Array_EjercicioEncapsulamiento() {
            this.elementos = new double[9];
        }
    
        public double get(int fila, int columna) {
            int indice = fila * 3 + columna;
            return elementos[indice];
        }
    
        public void set(int fila, int columna, double valor) {
            int indice = fila * 3 + columna;
            elementos[indice] = valor;
        }
    }

    public class Matriz3x3Array2D_EjercicioEncapsulamiento {
        private double[][] elementos;
    
        public Matriz3x3Array2D_EjercicioEncapsulamiento() {
            this.elementos = new double[3][3];
        }
    
        public double get(int fila, int columna) {
            return elementos[fila][columna];
        }
    
        public void set(int fila, int columna, double valor) {
            elementos[fila][columna] = valor;
        }
    }

    public class Matriz3x3Lista_EjercicioEncapsulamiento {
        private NodoFila primerNodoFila;
    
        public Matriz3x3Lista_EjercicioEncapsulamiento() {
            this.primerNodoFila = null;
        }
    
        public double get(int fila, int columna) {
            NodoFila nodoFila = obtenerNodoFila(fila);
            if (nodoFila == null) {
                return 0.0;
            }
            NodoColumna nodoColumna = obtenerNodoColumna(nodoFila, columna);
            if (nodoColumna == null) {
                return 0.0;
            }
            return nodoColumna.valor;
        }
    
        public void set(int fila, int columna, double valor) {
            NodoFila nodoFila = obtenerNodoFila(fila);
            if (nodoFila == null) {
                nodoFila = new NodoFila();
                nodoFila.indiceFila = fila;
                nodoFila.primerNodoColumna = null;
                nodoFila.siguiente = primerNodoFila;
                primerNodoFila = nodoFila;
            }
            NodoColumna nodoColumna = obtenerNodoColumna(nodoFila, columna);
            if (nodoColumna == null) {
                nodoColumna = new NodoColumna();
                nodoColumna.indiceColumna = columna;
                nodoColumna.valor = valor;
                nodoColumna.siguiente = nodoFila.primerNodoColumna;
                nodoFila.primerNodoColumna = nodoColumna;
            } else {
                nodoColumna.valor = valor;
            }
        }
    
        private NodoFila obtenerNodoFila(int fila) {
            NodoFila nodoFila = primerNodoFila;
            while (nodoFila != null && nodoFila.indiceFila != fila) {
                nodoFila = nodoFila.siguiente;
            }
            return nodoFila;
        }
    
        private NodoColumna obtenerNodoColumna(NodoFila nodoFila, int columna) {
            NodoColumna nodoColumna = nodoFila.primerNodoColumna;
            while (nodoColumna != null && nodoColumna.indiceColumna != columna) {
                nodoColumna = nodoColumna.siguiente;
            }
            return nodoColumna;
        }
    
    }
    
    public class Matriz3x3String_EjercicioEncapsulamiento {
        private String elementos;
    
        public Matriz3x3String_EjercicioEncapsulamiento() {
            this.elementos = "0,0,0\n0,0,0\n0,0,0\n";
        }
    
        public double get(int fila, int columna) {
            int inicio = (fila * 4) + (columna * 2);
            int fin = inicio + 1;
            String elementoStr = elementos.substring(inicio, fin);
            return Double.parseDouble(elementoStr);
        }
    
        public void set(int fila, int columna, double valor) {
            int inicio = (fila * 4) + (columna * 2);
            int fin = inicio + 1;
            String valorStr = String.format("%.2f", valor);
            elementos = elementos.substring(0, inicio) + valorStr + elementos.substring(fin);
        }
    
        public String toString() {
            return elementos;
        }
    }

    public class NodoColumna {
        int indiceColumna;
        double valor;
        NodoColumna siguiente;
    }

    public class NodoFila {
        int indiceFila;
        NodoColumna primerNodoColumna;
        NodoFila siguiente;
    }
   

}
