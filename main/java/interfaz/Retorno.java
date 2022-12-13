package interfaz;

import java.util.Objects;


public class Retorno {
    public enum Resultado {
        OK,
        ERROR_1,
        ERROR_2,
        ERROR_3,
        ERROR_4,
        ERROR_5,
        ERROR_6,
        ERROR_7,
        NO_IMPLEMENTADA
    }

    private final Resultado resultado;
    private final int valorInteger;
    private final String valorString;

    public Retorno(Resultado resultado, int valorInteger, String valorString) {
        this.resultado = resultado;
        this.valorInteger = valorInteger;
        this.valorString = valorString;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public int getValorInteger() {
        return valorInteger;
    }

    public String getValorString() {
        return valorString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Retorno retorno = (Retorno) o;
        return valorInteger == retorno.valorInteger && resultado == retorno.resultado && Objects.equals(valorString, retorno.valorString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultado, valorInteger, valorString);
    }

    public static Retorno ok() {
        return ok(0, "");
    }

    public static Retorno ok(String valorString) {
        return ok(0, valorString);
    }

    public static Retorno ok(int valorInteger) {
        return ok(valorInteger, "");
    }

    public static Retorno ok(int valorInteger, String valorString) {
        return new Retorno(Resultado.OK, valorInteger, valorString);
    }

    public static Retorno error(Resultado error, String msg) {
        return new Retorno(error, 0, msg);
    }

    public static Retorno error1(String mensaje) {
        return error(Resultado.ERROR_1, mensaje);
    }

    public static Retorno error2(String mensaje) {
        return error(Resultado.ERROR_2, mensaje);
    }

    public static Retorno error3(String mensaje) {
        return error(Resultado.ERROR_3, mensaje);
    }

    public static Retorno error4(String mensaje) {
        return error(Resultado.ERROR_4, mensaje);
    }

    public static Retorno error5(String mensaje) {
        return error(Resultado.ERROR_5, mensaje);
    }

    public static Retorno error6(String mensaje) {
        return error(Resultado.ERROR_6, mensaje);
    }

    public static Retorno error7(String mensaje) {
        return error(Resultado.ERROR_7, mensaje);
    }

    public static Retorno noImplementada() {
        return new Retorno(Resultado.NO_IMPLEMENTADA, 0, "");
    }

    public boolean isOk() {
        return this.resultado == Resultado.OK;
    }
}
