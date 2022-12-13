import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RegistrarJugadorTest {

    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10);
    }

    @Test
    void noDeberiaRegistrarConParametrosVacios() {
        retorno = sistema.registrarJugador("", new String("Gustavo"), 17, new String("Liceo N° 15"), TipoJugador.INICIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarJugador(new String("5.647.365-1"), "", 17, new String("Liceo 15"), TipoJugador.INICIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarJugador(new String("5.647.365-1"), new String("Fabián"), 17, "", TipoJugador.INICIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarConParametrosNulos() {
        retorno = sistema.registrarJugador(null, new String("Gustavo"), 17, new String("Liceo N° 15"), TipoJugador.INICIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarJugador(new String("5.647.365-1"), null, 17, new String("Liceo 15"), TipoJugador.INICIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarJugador(new String("5.647.365-1"), new String("Gustavo"), 17, null, TipoJugador.INICIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarJugador(new String("5.647.365-1"), new String("Gustavo"), 17, new String("Liceo 15"), null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarConCedulaInvalida() {
        retorno = sistema.registrarJugador(new String(".647.365-1"), new String("Gustavo"), 17, new String("Liceo 15"), TipoJugador.INICIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.registrarJugador(new String("0.647.365-1"), new String("Gustavo"), 17, new String("Liceo 15"), TipoJugador.INICIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.registrarJugador(new String("0.6.47.365-1"), new String("Gustavo"), 17, new String("Liceo 15"), TipoJugador.INICIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.registrarJugador(new String("36473651"), new String("Gustavo"), 17, new String("Liceo 15"), TipoJugador.INICIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarJugadorRepetido() {
        sistema.registrarJugador(new String("5.447.365-1"), new String("Gustavo"), 17, new String("Liceo 15"), TipoJugador.INICIAL);
        retorno = sistema.registrarJugador(new String("5.447.365-1"), new String("Gustavo"), 17, new String("Liceo 151"), TipoJugador.AVANZADO);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void deberiaRegistrarJugador() {
        retorno = sistema.registrarJugador(new String("5.447.365-1"), new String("Gustavo"), 17, new String("Liceo 15"), TipoJugador.INICIAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.registrarJugador(new String("5.888.365-1"), new String("Gustavo"), 17, new String("Liceo 15"), TipoJugador.INICIAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());


    }
}
