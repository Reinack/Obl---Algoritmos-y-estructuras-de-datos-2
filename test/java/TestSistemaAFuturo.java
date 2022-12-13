import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

/**
 * Reemplazar en un futuro por los test provistos por la catedra
 */
public class TestSistemaAFuturo {

    private Sistema sistema = new ImplementacionSistema();

    @Test
    public void testInit() {
        Assertions.assertEquals(Retorno.ok(), sistema.inicializarSistema(15));
    }
}
