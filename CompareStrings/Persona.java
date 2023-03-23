public class Persona {
    public String name;
    public int edad;

    public Persona(String name, int edad) {
        super();
        this.name = name;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;

        if(obj instanceof Persona){
            Persona other = (Persona) obj;
            // return this.name.equals(other.name) && this.edad == other.edad;
            if(this.name.equals(other.name) && this.edad == other.edad) return true;
            else return false;
        } else {
            return false;
        }
    }

    private void metodo1(int ... numeros){
        // Te van a llegar número... ¿Cuántos? No lo sé
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + this.edad;
    }
}
