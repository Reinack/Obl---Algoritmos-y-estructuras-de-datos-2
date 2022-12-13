import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplorarCentroUrbanoTest {

    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(6);
    }

    @Test
    void noDeberiaExplorarCentroUrbanoParamNull() {
        boolean[] correctas = {true, true, true, false, true, true, true, true};
        int[] puntajes = {1, 2, 3, 6, 3, 4, 4, 2};

        retorno = sistema.explorarCentroUrbano(null, puntajes, 5);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.explorarCentroUrbano(correctas, null, 5);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaExplorarCentroUrbanoArrayMenorA3() {
        boolean[] correctas = {true, true};
        int[] puntajes = {1, 2, 3, 6};

        retorno = sistema.explorarCentroUrbano(correctas, puntajes, 5);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        correctas = new boolean[]{true, true, true, false};
        puntajes = new int[]{1, 2};

        retorno = sistema.explorarCentroUrbano(correctas, puntajes, 5);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void noDeberiaExplorarCentroUrbanoArrayDistintosTamanios() {
        boolean[] correctas = {true, true, true, false, true, true, true, true};
        int[] puntajes = {1, 2, 3, 6, 3, 4, 4};

        retorno = sistema.explorarCentroUrbano(correctas, puntajes, 5);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void noDeberiaExplorarCentroUrbanoMinimoIgualACero() {
        boolean[] correctas = {true, true, true, false, true, true, true, true};
        int[] puntajes = {1, 2, 3, 6, 3, 4, 4,2};

        retorno = sistema.explorarCentroUrbano(correctas, puntajes, 0);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    void noDeberiaExplorarCentroUrbanoMinimoMenorACero() {
        boolean[] correctas = {true, true, true, false, true, true, true, true};
        int[] puntajes = {1, 2, 3, 6, 3, 4, 4,2};

        retorno = sistema.explorarCentroUrbano(correctas, puntajes, 0);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    void deberiaExplorarCentroUrbano() {
        boolean[] correctas = {true, true, true, false, true, true, true, true};
        int[] puntajes = {1, 2, 3, 6, 3, 4, 4,2};

        retorno = sistema.explorarCentroUrbano(correctas, puntajes, 20);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("pasa",retorno.getValorString());
        assertEquals(30,retorno.getValorInteger());

        correctas = new boolean[]{true, true, false, false, true, true, false, true};
        puntajes = new int[]{1, 2, 3, 6, 3, 4, 4,2};

        retorno = sistema.explorarCentroUrbano(correctas, puntajes, 20);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("no pasa",retorno.getValorString());
        assertEquals(12,retorno.getValorInteger());

        correctas = new boolean[]{false, false, false, false, false, false, false, false};
        puntajes = new int[]{1, 2, 3, 6, 3, 4, 4,2};

        retorno = sistema.explorarCentroUrbano(correctas, puntajes, 20);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("no pasa",retorno.getValorString());
        assertEquals(0,retorno.getValorInteger());

        correctas = new boolean[]{true, true, true, true, true, true, true, true};
        puntajes = new int[]{1, 2, 3, 6, 3, 4, 4,2};

        retorno = sistema.explorarCentroUrbano(correctas, puntajes, 20);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("pasa",retorno.getValorString());
        assertEquals(65,retorno.getValorInteger());
    }
}
