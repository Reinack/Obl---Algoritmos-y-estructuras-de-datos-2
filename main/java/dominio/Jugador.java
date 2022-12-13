package dominio;

import interfaz.TipoJugador;

public class Jugador implements Comparable<Jugador> {
    private TipoJugador tipoJugador;
    private int edad;
    private Cedula ci;
    String escuela;

    String nombre;

    //Constructor
    public Jugador(TipoJugador tipoJugador, Cedula ci, int edad, String escuela, String nombre) {
        this.tipoJugador = tipoJugador;
        this.ci = ci;
        this.edad = edad;
        this.escuela = escuela;
        this.nombre = nombre;
    }
    // Cascaron
    public Jugador(Cedula ci){
        this.ci = ci;
    }

    // Getters and setters

    public TipoJugador getTipoJugador() {
        return tipoJugador;
    }

    public void setTipoJugador(TipoJugador tipoJugador) {
        this.tipoJugador = tipoJugador;
    }

    public Cedula getCedula() {
        return ci;
    }

    public void setCi(Cedula ci) {
        this.ci = ci;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public boolean equals(Object o) {
        Jugador jugador = (Jugador)o;
        return jugador.getCedula().equals(this.ci);
    }

    @Override
    public String toString() {

        //cedula1;nombre1;edad1;escuela1;tipoJugador1
        return ci.getCedulaStr() + ";" + nombre + ";" + edad + ";" + escuela + ";" + tipoJugador.getValor();

    }

    @Override
    public int compareTo(Jugador jugador) {
        if (jugador.getCedula().getCi()<this.getCedula().getCi()) return 1;
        else if (jugador.getCedula().getCi()>this.getCedula().getCi()) return -1;
        else return 0;
    }

}
