import interfaz.Consulta;
import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FiltrarJugadoresTest {

    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(6);

        sistema.registrarJugador(new String("4.685.375-3"), new String("Juliana"), 16, new String("Liceo 21"), TipoJugador.AVANZADO);
        sistema.registrarJugador(new String("5.135.139-2"), new String("Gaston"), 16, new String("Liceo 21"), TipoJugador.AVANZADO).getResultado();
        sistema.registrarJugador(new String("5.888.365-4"), new String("Alejandra"), 15, new String("Liceo 17"), TipoJugador.MEDIO).getResultado();
        sistema.registrarJugador(new String("5.447.365-1"), new String("Gustavo"), 17, new String("Liceo 15"), TipoJugador.INICIAL).getResultado();
    }
    @Test
    void deberiaFiltrarJugador() {

        retorno = sistema.filtrarJugadores(Consulta.fromString("edad > 15 OR escuela = 'Felipe'"));
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
        assertEquals("4.685.375-3|5.135.139-2|5.447.365-1",retorno.getValorString());



        retorno = sistema.filtrarJugadores(Consulta.fromString("nombre='Juliana'"));
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
        assertEquals("4.685.375-3",retorno.getValorString());


        retorno = sistema.filtrarJugadores(Consulta.fromString("escuela='Liceo 21'"));
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
        assertEquals("4.685.375-3|5.135.139-2",retorno.getValorString());

        retorno = sistema.filtrarJugadores(Consulta.fromString("[edad >10] AND [escuela ='Felipe' AND escuela='Americas']"));
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
        assertEquals("",retorno.getValorString());
    }
}
