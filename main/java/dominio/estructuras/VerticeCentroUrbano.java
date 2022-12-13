package dominio.estructuras;

import dominio.CentroUrbano;

public class VerticeCentroUrbano {

    private CentroUrbano centroUrbano;

    public CentroUrbano getCentroUrbano() {
        return centroUrbano;
    }


    public void setCentroUrbano(CentroUrbano centroUrbano) {
        this.centroUrbano = centroUrbano;
    }

    @Override
    public String toString() {
        return centroUrbano.toString();
    }
}
