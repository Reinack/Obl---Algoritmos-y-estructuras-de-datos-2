import interfaz.EstadoCamino;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActualizarCaminoTest {
    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(6);
    }

    @Test
    void noDeberiaActualizarCaminoParamIgualesACero() {
        retorno = sistema.actualizarCamino(new String("1"),new String("2"),0,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.actualizarCamino(new String("1"),new String("2"),10,0,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.actualizarCamino(new String("1"),new String("2"),10,20,0, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());
    }

    @Test
    void noDeberiaActualizarCaminoParamMenoresACero() {
        retorno = sistema.actualizarCamino(new String("1"),new String("2"),-10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.actualizarCamino(new String("1"),new String("2"),10,-20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.actualizarCamino(new String("1"),new String("2"),10,20,-30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());
    }

    @Test
    void noDeberiaActualizarCaminoParamVacios() {
        retorno = sistema.actualizarCamino(new String(""),new String("2"),10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.actualizarCamino(new String("1"),new String(""),10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());
    }

    @Test
    void noDeberiaActualizarCaminoParamNull() {
        retorno = sistema.actualizarCamino(null,new String("2"),10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.actualizarCamino(new String("1"),null,10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.actualizarCamino(new String("1"),new String("2"),10,20,30, null);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());
    }

    @Test
    void noDeberiaActualizarCaminoParamCentroOrigenNoExiste() {
        retorno = sistema.actualizarCamino(new String("1"),new String("2"),10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_3,retorno.getResultado());
    }

    @Test
    void noDeberiaActualizarCaminoParamCentroDestinoNoExiste() {
        sistema.registrarCentroUrbano(new String("1"), new String("CentroUrbano1"));
        retorno = sistema.actualizarCamino(new String("1"),new String("2"),10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_4,retorno.getResultado());
    }

    @Test
    void noDeberiaActualizarCaminoPorCaminoInexistente(){
        sistema.registrarCentroUrbano(new String("1"), new String("CentroUrbano1"));
        sistema.registrarCentroUrbano(new String("2"), new String("CentroUrbano2"));
        retorno = sistema.actualizarCamino(new String("1"),new String("2"),10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_5,retorno.getResultado());
    }

    @Test
    void deberiaActualizarCamino(){
        sistema.registrarCentroUrbano(new String("1"), new String("CentroUrbano1"));
        sistema.registrarCentroUrbano(new String("2"), new String("CentroUrbano2"));
        sistema.registrarCamino(new String("1"),new String("2"),10,20,30, EstadoCamino.BUENO);
        retorno = sistema.actualizarCamino(new String("1"),new String("2"),15,25,35, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
    }
}
