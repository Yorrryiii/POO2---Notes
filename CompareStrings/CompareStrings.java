public class CompareStrings {
    public static void main(String[] args) {
        
        String uno = "mario";
        String dos = "mario";

        String tres = "mar" + "io";

        String cuatro = "marie";
        String cinco = cuatro.replace('e', 'o');

        if (uno == cinco) System.out.println("Son igual con ==");
        // else {
        //     System.out.println("No son igual con ==");
        //     System.out.println(uno + " != " + cinco);
        // }

        if(uno.equals(cinco)) System.out.println("Son igual con equals");

        // Hay que comparar los String con equals
    }
}
