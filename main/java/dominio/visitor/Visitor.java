package dominio.visitor;

public interface Visitor<T> {
    void aceptar(T dato);
}
