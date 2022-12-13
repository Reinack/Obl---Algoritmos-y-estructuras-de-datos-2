package dominio;

import static java.lang.Integer.parseInt;

public class CentroUrbano implements Comparable<CentroUrbano> {
    private String nombre;
    private String codigo;

    public CentroUrbano(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return codigo+";"+nombre;
    }

    @Override
    public int compareTo(CentroUrbano centro) {
        if (parseInt(centro.codigo)<parseInt(this.codigo)) return 1;
        else if (parseInt(centro.codigo)>parseInt(this.codigo)) return -1;
        else return 0;
    }

}
