import interfaz.EstadoCamino;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListadoCentrosCantDeSaltosTest {

    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);
    }

    @Test
    void noDeberiaListarCantMenorACero() {
        retorno = sistema.listadoCentrosCantDeSaltos(new String("1"), -1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaListarCentroNoRegistrado() {
        retorno = sistema.listadoCentrosCantDeSaltos(new String("10"), 2);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    private void cargarSetCentrosUrbanosYCaminos1() {
        sistema.registrarCentroUrbano(new String("1"), new String("CentroUrbano1"));
        sistema.registrarCentroUrbano(new String("2"), new String("CentroUrbano2"));
        sistema.registrarCentroUrbano(new String("3"), new String("CentroUrbano3"));
        sistema.registrarCentroUrbano(new String("4"), new String("CentroUrbano4"));
        sistema.registrarCentroUrbano(new String("5"), new String("CentroUrbano5"));
        sistema.registrarCentroUrbano(new String("6"), new String("CentroUrbano6"));

        sistema.registrarCamino(new String("1"), new String("2"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("1"), new String("3"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("1"), new String("6"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("2"), new String("4"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("3"), new String("4"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("3"), new String("6"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("3"), new String("5"), 10, 20, 30, EstadoCamino.BUENO);
    }

    private void cargarSetCentrosUrbanosYCaminos2() {
        sistema.registrarCentroUrbano(new String("1"), new String("CentroUrbano1"));
        sistema.registrarCentroUrbano(new String("2"), new String("CentroUrbano2"));
        sistema.registrarCentroUrbano(new String("3"), new String("CentroUrbano3"));
        sistema.registrarCentroUrbano(new String("4"), new String("CentroUrbano4"));
        sistema.registrarCentroUrbano(new String("5"), new String("CentroUrbano5"));
        sistema.registrarCentroUrbano(new String("6"), new String("CentroUrbano6"));
        sistema.registrarCentroUrbano(new String("7"), new String("CentroUrbano7"));
        sistema.registrarCentroUrbano(new String("8"), new String("CentroUrbano8"));

        sistema.registrarCamino(new String("1"), new String("2"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("1"), new String("3"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("1"), new String("6"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("2"), new String("4"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("3"), new String("4"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("3"), new String("6"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("3"), new String("5"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("4"), new String("8"), 10, 20, 30, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("5"), new String("8"), 10, 20, 30, EstadoCamino.BUENO);
    }

    @Test
    void deberiaImprimirSoloCentroOrigen() {
        cargarSetCentrosUrbanosYCaminos1();
        retorno = sistema.listadoCentrosCantDeSaltos(new String("1"), 0);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1;CentroUrbano1", retorno.getValorString());
    }

    @Test
    void deberiaImprimir4Centros() {
        cargarSetCentrosUrbanosYCaminos1();
        retorno = sistema.listadoCentrosCantDeSaltos(new String("1"), 1);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1;CentroUrbano1|2;CentroUrbano2|3;CentroUrbano3|6;CentroUrbano6", retorno.getValorString());
    }

    @Test
    void deberiaImprimir6Centros() {
        cargarSetCentrosUrbanosYCaminos2();
        retorno = sistema.listadoCentrosCantDeSaltos(new String("1"), 2);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1;CentroUrbano1|2;CentroUrbano2|3;CentroUrbano3|4;CentroUrbano4|5;CentroUrbano5|6;CentroUrbano6", retorno.getValorString());
    }

    @Test
    void deberiaImprimir1Centro() {
        cargarSetCentrosUrbanosYCaminos2();
        retorno = sistema.listadoCentrosCantDeSaltos(new String("8"), 2);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("8;CentroUrbano8", retorno.getValorString());
    }

    @Test
    void deberiaImprimir5Centros() {
        cargarSetCentrosUrbanosYCaminos2();
        retorno = sistema.listadoCentrosCantDeSaltos(new String("3"), 10);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("3;CentroUrbano3|4;CentroUrbano4|5;CentroUrbano5|6;CentroUrbano6|8;CentroUrbano8", retorno.getValorString());
    }
}
