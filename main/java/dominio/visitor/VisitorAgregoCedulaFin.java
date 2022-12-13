package dominio.visitor;

import dominio.Jugador;
import dominio.estructuras.ListaGenerica;

public class VisitorAgregoCedulaFin implements Visitor<Jugador>{

    private ListaGenerica<String> miListaGenerica = new ListaGenerica<>();

    public ListaGenerica<String> getMiListaGenerica() {
        return miListaGenerica;
    }
    @Override
    public void aceptar(Jugador jugador) {
        miListaGenerica.agregarFin(jugador.getCedula().getCedulaStr());
    }
}
