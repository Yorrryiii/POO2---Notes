package test;

public class Test {
    // Comprobar que se calculan correctamente los dígitos de control
    @Test
    public void calcularDigitosControl() {
        String codigoEntidad = "1234";
        String codigoOficina = "5678";
        String numeroCuenta = "9012345678";
        String digitoControl = calcularDigitosControl(codigoEntidad, codigoOficina, numeroCuenta);
        assertEquals("05", digitoControl);
    }
}
