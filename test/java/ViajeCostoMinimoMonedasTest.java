import interfaz.EstadoCamino;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViajeCostoMinimoMonedasTest {
    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);
    }

    private void cargarSetCentrosUrbanosYCaminos1() {
        sistema.registrarCentroUrbano(new String("1"), new String("CentroUrbano1"));
        sistema.registrarCentroUrbano(new String("2"), new String("CentroUrbano2"));
        sistema.registrarCentroUrbano(new String("3"), new String("CentroUrbano3"));
        sistema.registrarCentroUrbano(new String("4"), new String("CentroUrbano4"));
        sistema.registrarCentroUrbano(new String("5"), new String("CentroUrbano5"));
        sistema.registrarCentroUrbano(new String("6"), new String("CentroUrbano6"));

        sistema.registrarCamino(new String("1"), new String("2"), 10, 20, 10, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("1"), new String("3"), 10, 20, 2, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("1"), new String("6"), 7, 20, 14, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("2"), new String("4"), 10, 20, 15, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("3"), new String("4"), 25, 20, 15, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("3"), new String("6"), 2, 20, 1, EstadoCamino.BUENO);
        sistema.registrarCamino(new String("3"), new String("5"), 4, 20, 3, EstadoCamino.BUENO);
    }

    @Test
    void noDeberiaDevolverCostoMinimoParamVacio() {
        retorno = sistema.viajeCostoMinimoMonedas(new String(""), new String("4"));
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.viajeCostoMinimoMonedas(new String("1"), new String(""));
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaDevolverCostoMinimoParamNull() {
        retorno = sistema.viajeCostoMinimoMonedas(null, new String("4"));
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.viajeCostoMinimoMonedas(new String("1"), null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaDevolverCostoMinimoNoExisteOrigen() {
        cargarSetCentrosUrbanosYCaminos1();

        retorno = sistema.viajeCostoMinimoMonedas(new String("10"), new String("4"));
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void noDeberiaDevolverCostoMinimoNoExisteDestino() {
        cargarSetCentrosUrbanosYCaminos1();

        retorno = sistema.viajeCostoMinimoMonedas(new String("1"), new String("40"));
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    void deberiaDevolverCostoMinimo() {
        cargarSetCentrosUrbanosYCaminos1();
        retorno = sistema.viajeCostoMinimoMonedas(new String("1"), new String("4"));

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(20, retorno.getValorInteger());
        assertEquals("1;CentroUrbano1|2;CentroUrbano2|4;CentroUrbano4", retorno.getValorString());
    }

    @Test
    void deberiaDevolverCostoMinimo1(){
        cargarSetCentrosUrbanosYCaminos1();
        sistema.registrarCamino(new String("5"), new String("4"), 4, 20, 8, EstadoCamino.BUENO);
        retorno = sistema.viajeCostoMinimoMonedas(new String("1"), new String("4"));

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(18, retorno.getValorInteger());
        assertEquals("1;CentroUrbano1|3;CentroUrbano3|5;CentroUrbano5|4;CentroUrbano4", retorno.getValorString());
    }

    @Test
    void deberiaDevolverCostoMinimo2(){
        cargarSetCentrosUrbanosYCaminos1();
        sistema.registrarCamino(new String("5"), new String("4"), 40, 20, 8, EstadoCamino.MALO);
        retorno = sistema.viajeCostoMinimoMonedas(new String("1"), new String("4"));

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(20, retorno.getValorInteger());
        assertEquals("1;CentroUrbano1|2;CentroUrbano2|4;CentroUrbano4", retorno.getValorString());
    }

    @Test
    void nodeberiaDevolverCostoMinimoNoHayCamino(){
        cargarSetCentrosUrbanosYCaminos1();
        retorno = sistema.viajeCostoMinimoMonedas(new String("5"), new String("6"));

        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
}
