package dominio.visitor;

public class VisitorSerializador <T> implements Visitor<T>{
    private StringBuilder sb = new StringBuilder();
    private String separador; // ";" or "|"

    public VisitorSerializador(String caracter) {
        separador = caracter;
    }

    @Override

    public void aceptar(T dato) {
        if(sb.length()>0){
            sb.append(separador);
        }
        sb.append(dato.toString());
    }

    public String getSerializado(){
        return sb.toString();
    }
}
