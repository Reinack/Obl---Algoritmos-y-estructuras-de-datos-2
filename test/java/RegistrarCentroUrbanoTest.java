import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrarCentroUrbanoTest {
    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(6);
    }

    @Test
    void noDeberiaRegistrarCentroUrbanoParaVacio() {
        retorno = sistema.registrarCentroUrbano(new String(""), new String("CentroUrbano1"));

        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.registrarCentroUrbano(new String("1"), new String(""));
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarCentroUrbanoParaNull() {
        retorno = sistema.registrarCentroUrbano(null, new String("CentroUrbano1"));
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.registrarCentroUrbano(new String("1"), null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void deberiaRegistrarCentroUrbano() {
        retorno = sistema.registrarCentroUrbano(new String("1"), new String("CentroUrbano1"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarCentroUrbano(new String("2"), new String("CentroUrbano2"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarCentroUrbano(new String("3"), new String("CentroUrbano3"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarCentroUrbano(new String("4"), new String("CentroUrbano4"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarCentroUrbano(new String("5"), new String("CentroUrbano5"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarCentroUrbano(new String("6"), new String("CentroUrbano6"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarCentroUrbanoPorMaxCentros() {
        sistema.registrarCentroUrbano(new String("1"), new String("CentroUrbano1"));
        sistema.registrarCentroUrbano(new String("2"), new String("CentroUrbano2"));
        sistema.registrarCentroUrbano(new String("3"), new String("CentroUrbano3"));
        sistema.registrarCentroUrbano(new String("4"), new String("CentroUrbano4"));
        sistema.registrarCentroUrbano(new String("5"), new String("CentroUrbano5"));
        sistema.registrarCentroUrbano(new String("6"), new String("CentroUrbano6"));
        retorno = sistema.registrarCentroUrbano(new String("7"), new String("CentroUrbano7"));
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }


}
