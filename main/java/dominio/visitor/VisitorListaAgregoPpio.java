package dominio.visitor;

import dominio.estructuras.ListaGenerica;

public class VisitorListaAgregoPpio<T> implements Visitor<T>{
    private ListaGenerica<T> miListaGenerica = new ListaGenerica<>();
    @Override
    public void aceptar(T dato) {
        miListaGenerica.agregarInicio(dato);
    }

    public ListaGenerica<T> getMiListaGenerica() {
        return miListaGenerica;
    }
}


