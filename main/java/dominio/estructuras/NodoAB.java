package dominio.estructuras;

public class NodoAB<T>{
    private T dato;
    private NodoAB<T> izq;
    private NodoAB<T> der;

    // constructor para hojas
    public NodoAB(T dato) {
        this.dato = dato;
    }
    // constructor para el resto
    public NodoAB(T dato, NodoAB<T> izq, NodoAB<T> der) {
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoAB<T> getIzq() {
        return izq;
    }

    public void setIzq(NodoAB<T> izq) {
        this.izq = izq;
    }

    public NodoAB<T> getDer() {
        return der;
    }

    public void setDer(NodoAB<T> der) {
        this.der = der;
    }
}