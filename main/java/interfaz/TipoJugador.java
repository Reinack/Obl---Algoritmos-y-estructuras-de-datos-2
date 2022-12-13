package interfaz;

public enum TipoJugador {
    AVANZADO(0, "Avanzado"),
    MEDIO(1, "Medio"),
    INICIAL(2, "Inicial"),
    MONITOR(3, "Monitor");

    private final int indice;
    private final String valor;

    TipoJugador(int indice, String valor) {
        this.indice = indice;
        this.valor = valor;
    }

    public int getIndice() {
        return indice;
    }

    public String getValor() {
        return valor;
    }
}
