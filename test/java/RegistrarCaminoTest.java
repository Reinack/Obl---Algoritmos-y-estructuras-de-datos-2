import interfaz.EstadoCamino;
import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrarCaminoTest {

    private Sistema sistema;
    private Retorno retorno;
    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(6);
    }

    @Test
    void noDeberiaRegistrarCaminoParaIgualesACero() {
        retorno = sistema.registrarCamino(new String("1"),new String("2"),0,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.registrarCamino(new String("1"),new String("2"),10,0,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.registrarCamino(new String("1"),new String("2"),10,20,0, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarCaminoParaMenoresACero() {
        retorno = sistema.registrarCamino(new String("1"),new String("2"),-10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.registrarCamino(new String("1"),new String("2"),10,-20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());

        retorno = sistema.registrarCamino(new String("1"),new String("2"),10,20,-30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_1,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarCaminoParaParamVacios() {
        retorno = sistema.registrarCamino(new String(""),new String("2"),10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarCamino(new String("1"),new String(""),10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarCaminoParaParamNull() {
        retorno = sistema.registrarCamino(null,new String("2"),10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarCamino(new String("1"),null,10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());

        retorno = sistema.registrarCamino(new String("1"),new String("2"),10,20,30, null);
        assertEquals(Retorno.Resultado.ERROR_2,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarCaminoParaParamCentroOrigenNoExiste() {
        retorno = sistema.registrarCamino(new String("1"),new String("2"),10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_3,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarCaminoParaParamCentroDestinoNoExiste() {
        sistema.registrarCentroUrbano(new String("1"), new String("CentroUrbano1"));
        retorno = sistema.registrarCamino(new String("1"),new String("2"),10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_4,retorno.getResultado());
    }

    @Test
    void deberiaRegistrarCamino(){
        sistema.registrarCentroUrbano(new String("1"), new String("CentroUrbano1"));
        sistema.registrarCentroUrbano(new String("2"), new String("CentroUrbano2"));
        retorno = sistema.registrarCamino(new String("1"),new String("2"),10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.OK,retorno.getResultado());
    }

    @Test
    void noDeberiaRegistrarCaminoPorCaminoExistente(){
        sistema.registrarCentroUrbano(new String("1"), new String("CentroUrbano1"));
        sistema.registrarCentroUrbano(new String("2"), new String("CentroUrbano2"));
        sistema.registrarCamino(new String("1"),new String("2"),10,20,30, EstadoCamino.BUENO);
        retorno = sistema.registrarCamino(new String("1"),new String("2"),10,20,30, EstadoCamino.BUENO);
        assertEquals(Retorno.Resultado.ERROR_5,retorno.getResultado());
    }
}
