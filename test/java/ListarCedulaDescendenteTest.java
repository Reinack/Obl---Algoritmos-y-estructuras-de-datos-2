import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListarCedulaDescendenteTest {

    Sistema sistema;
    Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);

        sistema.registrarJugador(new String("4.685.375-3"), new String("Juliana"), 16, new String("Liceo 21"), TipoJugador.AVANZADO);
        sistema.registrarJugador(new String("5.135.139-2"), new String("Gaston"), 16, new String("Liceo 21"), TipoJugador.AVANZADO).getResultado();
        sistema.registrarJugador(new String("5.888.365-4"), new String("Alejandra"), 15, new String("Liceo 17"), TipoJugador.MEDIO).getResultado();
        sistema.registrarJugador(new String("5.447.365-1"), new String("Gustavo"), 17, new String("Liceo 15"), TipoJugador.INICIAL).getResultado();
    }

    @Test
    void listarDescendente() {
        retorno = sistema.listarJugadoresPorCedulaDescendente();
        String resEsperado = "5.888.365-4;Alejandra;15;Liceo 17;Medio|5.447.365-1;Gustavo;17;Liceo 15;Inicial|5.135.139-2;Gaston;16;Liceo 21;Avanzado|4.685.375-3;Juliana;16;Liceo 21;Avanzado";
        assertEquals(resEsperado, retorno.getValorString());
    }

    @Test
    void listarDescendenteVacio() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);
        retorno = sistema.listarJugadoresPorCedulaDescendente();
        assertEquals("", retorno.getValorString());
    }
}
