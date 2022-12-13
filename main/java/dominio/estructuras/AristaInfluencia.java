package dominio.estructuras;

import interfaz.EstadoCamino;

public class AristaInfluencia {
    private boolean existe;
    private double km;
    private double tiempo;
    private double costo;

    private EstadoCamino estado;

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public EstadoCamino getEstado() {
        return estado;
    }

    public void setEstado(EstadoCamino estado) {
        this.estado = estado;
    }
}
