package dominio.visitor;

import dominio.estructuras.ListaGenerica;
import dominio.predicados.Predicado;

public class VisitorFiltrador <T> implements Visitor<T>{
    private Predicado<T> predicado;
    private ListaGenerica<T> resultadosFiltrados = new ListaGenerica<>();

    public VisitorFiltrador(Predicado<T> predicado){
        this.predicado=predicado;
    }

    @Override
    public void aceptar(T dato) {
        if (predicado.pasaONo(dato)){
            resultadosFiltrados.agregarFin(dato);
        }
    }

    public ListaGenerica<T> getResultadosFiltrados(){
        return resultadosFiltrados;
    }
}
