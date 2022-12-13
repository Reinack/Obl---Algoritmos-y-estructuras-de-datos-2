import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InicializarSistemaTest {

    Retorno retorno;

    @Test
    void noDeberiaInicializarSistemaMaxCentrosMenorA5() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(4);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.inicializarSistema(0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.inicializarSistema(-1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaInicializarSistemaMaxCentrosIgualA5() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(5);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void deberiaInicializarSistemaMaxCentrosMayorA5() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(6);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
}
