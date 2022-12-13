package dominio.predicados;

// predicado devuelve true or false de acuerdo a un dato

public interface Predicado<T> {
    boolean pasaONo(T valor);
}
