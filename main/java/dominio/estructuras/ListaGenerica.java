package dominio.estructuras;

import dominio.visitor.Visitable;
import dominio.visitor.Visitor;
import dominio.visitor.VisitorListaAgregoFin;

public class ListaGenerica<T> implements Visitable<T> {

    protected NodoLista<T> inicio;
    protected NodoLista<T> fin;

    protected static class NodoLista<U> {
        protected U dato;
        protected NodoLista<U> sig;

        public NodoLista(U dato) {
            this.dato = dato;
        }

        public NodoLista(U dato, NodoLista<U> sig) {
            this.dato = dato;
            this.sig = sig;
        }
    }

    public void agregarInicio(T dato) {
        this.inicio = new NodoLista<>(dato, inicio);
        if (fin == null) {
            fin = inicio;
        }
    }

    public void agregarFin(T dato) {
        if (fin == null)
            agregarInicio(dato);
        else {
            NodoLista<T> nuevoFin = new NodoLista<>(dato);
            this.fin.sig = nuevoFin;
            this.fin = nuevoFin;
        }
    }

    public NodoLista<T> getInicio() {
        return inicio;
    }

    @Override
    public void visitar(Visitor<T> visitor) {
        NodoLista<T> nodoActual = inicio;
        while (nodoActual!=null){
            visitor.aceptar(nodoActual.dato);
            nodoActual = nodoActual.sig;
        }
    }


    public boolean esVacia(){
        return inicio == null;
    }

    public T borrarInicio(){
        T j = inicio.dato;
        this.inicio=inicio.sig;
        if( this.inicio == null)  this.fin=null;

        return j;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        NodoLista<T> actual=this.inicio;
        while(actual!=null){

            sb.append(actual.dato);            sb.append(" ");
            actual=actual.sig;
        }
        return sb.toString();
    }




}
