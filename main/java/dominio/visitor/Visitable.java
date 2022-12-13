package dominio.visitor;

public interface Visitable <T>{
    void visitar(Visitor<T> visitor);
}
