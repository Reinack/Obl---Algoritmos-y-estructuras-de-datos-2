import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListarJugadoresPorTipoTest {

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
    void noDeberiaListarJugadoresPorTipoParamNull() {
        retorno = sistema.listarJugadoresPorTipo(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

    }

    @Test
    void deberiaListarJugadoresPorTipo() {
        retorno = sistema.listarJugadoresPorTipo(TipoJugador.AVANZADO);
        final List<String> jugadoresAvanzadosEnRetorno = List.of(retorno.getValorString().split("\\|"));

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2, jugadoresAvanzadosEnRetorno.size());

        final String jugadorAvanzado = "4.685.375-3;Juliana;16;Liceo 21;Avanzado";
        final String jugadorAvanzado2 = "5.135.139-2;Juliana;16;Liceo 21;Avanzado";
        final String mensaje = "Se esperaban los jugadores avanzados " + jugadorAvanzado + " y " + jugadorAvanzado2 + " pero se obtuvieron "
                + jugadoresAvanzadosEnRetorno.get(0) + " " + jugadoresAvanzadosEnRetorno.get(1);

        assertTrue(jugadoresAvanzadosEnRetorno.containsAll(List.of(jugadorAvanzado, jugadorAvanzado2)), mensaje);

        retorno = sistema.listarJugadoresPorTipo(TipoJugador.MEDIO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("5.888.365-4;Alejandra;15;Liceo 17;Medio", retorno.getValorString());

        retorno = sistema.listarJugadoresPorTipo(TipoJugador.INICIAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("5.447.365-1;Gustavo;17;Liceo 15;Inicial", retorno.getValorString());
    }

    @Test
    void deberiaListarJugadoresPorTipoVacio() {
        retorno = sistema.listarJugadoresPorTipo(TipoJugador.MONITOR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

}
