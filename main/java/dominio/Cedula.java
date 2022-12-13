package dominio;

import excepciones.CedulaInvalidaException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Cedula {
    private final int ci;
    private final String cedulaStr;
    public Cedula(String c){
        this.ci=convertirCedula(c);
        this.cedulaStr = c;
    }

    public int getCi() {
        return ci;
    }

    public String getCedulaStr() {
        return cedulaStr;
    }


    public static Cedula crearCedula(String ci) throws CedulaInvalidaException {
        if(!validarCedula(ci)) throw new CedulaInvalidaException();
        return new Cedula(ci);

    }

    // Precondicion: la cedula viene validada.
    public static int convertirCedula(String c) {
        // ci N.NNN.NNN-N
        String ci = "";
        for (int i = 0; i < c.length(); i++) {

            if (c.charAt(i) != '.' && c.charAt(i) != '-'){
                ci+=c.charAt(i);
            }
        }

        return parseInt(ci);

    }
    @Override
    public boolean equals(Object o) {
        Cedula cedula = (Cedula)o;
        return this.ci == cedula.getCi();
    }

    public static boolean validarCedula(String ci){

        // Patron para las ci N.NNN.NNN-N
        Pattern patron1 = Pattern.compile("^[1-9][.]\\d{3}[.]\\d{3}-\\d$");

        // Patron para las ci NNN.NNN-N
        Pattern patron2 = Pattern.compile("^[1-9]d{2}[.]\\d{3}-\\d$");


        Matcher m1 = patron1.matcher(ci);
        Matcher m2 = patron2.matcher(ci);

        return m1.matches() || m2.matches();

    }


}
