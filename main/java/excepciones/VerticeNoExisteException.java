package excepciones;

public class VerticeNoExisteException extends RuntimeException {
    private boolean esOrigen;
    public VerticeNoExisteException(boolean esOrigen) {
        this.esOrigen = esOrigen;
    }

    public boolean isEsOrigen() {
        return esOrigen;
    }
}
