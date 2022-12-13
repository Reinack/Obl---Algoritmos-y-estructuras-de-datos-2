import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuscarJugadorTest {

    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        sistema.registrarJugador(new String("4.685.375-3"), new String("Juliana"), 16, new String("Liceo 21"), TipoJugador.AVANZADO);
        sistema.registrarJugador(new String("5.135.139-2"), new String("Juliana"), 16, new String("Liceo 21"), TipoJugador.AVANZADO).getResultado();
        sistema.registrarJugador(new String("5.888.365-4"), new String("Alejandra"), 15, new String("Liceo 17"), TipoJugador.MEDIO).getResultado();
        sistema.registrarJugador(new String("5.447.365-1"), new String("Gustavo"), 17, new String("Liceo 15"), TipoJugador.INICIAL).getResultado();
    }

    @Test
    void noDeberiaEncontrarJugadorCIInvalida() {

        retorno = sistema.buscarJugador(".685.375-3");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.buscarJugador("0.685.375-3");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.buscarJugador("4.6.85.375-3");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaEncontrarJugadorInexistente() {

        retorno = sistema.buscarJugador("7.685.375-3");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void deberiaEncontrarJugador() {
        retorno = sistema.buscarJugador("4.685.375-3");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("4.685.375-3;Juliana;16;Liceo 21;Avanzado", retorno.getValorString());
        assertEquals(0, retorno.getValorInteger());

        retorno = sistema.buscarJugador("5.135.139-2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("5.135.139-2;Juliana;16;Liceo 21;Avanzado", retorno.getValorString());
        assertEquals(1, retorno.getValorInteger());

        retorno = sistema.buscarJugador("5.888.365-4");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("5.888.365-4;Alejandra;15;Liceo 17;Medio", retorno.getValorString());
        assertEquals(2, retorno.getValorInteger());

        retorno = sistema.buscarJugador("5.447.365-1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("5.447.365-1;Gustavo;17;Liceo 15;Inicial", retorno.getValorString());
        assertEquals(3, retorno.getValorInteger());
    }

}
