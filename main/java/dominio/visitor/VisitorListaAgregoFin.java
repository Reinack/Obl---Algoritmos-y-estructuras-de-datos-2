package dominio.visitor;

import dominio.estructuras.ListaGenerica;

public class VisitorListaAgregoFin<T> implements Visitor<T>{
    private ListaGenerica<T> miListaGenerica = new ListaGenerica<>();
    @Override
    public void aceptar(T dato) {
        miListaGenerica.agregarFin(dato);
    }

    public ListaGenerica<T> getMiListaGenerica() {
        return miListaGenerica;
    }
}
