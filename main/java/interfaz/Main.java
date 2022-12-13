package interfaz;

import sistema.ImplementacionSistema;

public class Main {
    public static void main(String[] args) {

        ImplementacionSistema si = new ImplementacionSistema();
        si.inicializarSistema(200);
        System.out.println(si.registrarJugador("1.234.567-9","Otro", 27, "una escuela", TipoJugador.MEDIO).getValorString());
        System.out.println(si.registrarJugador("1.234.567-8","Julio", 27, "una escuela", TipoJugador.AVANZADO).getValorString());
        System.out.println(si.registrarJugador("1.234.567-1","kjdgfkjs", 27, "una escuela", TipoJugador.MEDIO).getValorString());

        System.out.println(si.buscarJugador("1.234.567-1").getValorInteger());

        System.out.println(si.listarJugadoresPorCedulaAscendente().getValorString());

        System.out.println(si.listarJugadoresPorCedulaDescendente().getValorString());

        System.out.println(si.listarJugadoresPorTipo(TipoJugador.AVANZADO).getValorString());

        System.out.println(si.registrarJugador("1.234.567-7","rob", 11, "una escuela", TipoJugador.MEDIO).getValorString());
        System.out.println(si.registrarJugador("1.234.566-1","pepe", 12, "otra escuela", TipoJugador.AVANZADO).getValorString());
        System.out.println(si.registrarJugador("1.234.562-1","juan", 13, "esa escuela", TipoJugador.MEDIO).getValorString());
        System.out.println(si.registrarJugador("1.234.564-1","pepe", 28, "la otra escuela", TipoJugador.MEDIO).getValorString());

        System.out.println(si.registrarJugador("2.234.564-2","pepe", 55, "la otra escuela", TipoJugador.MEDIO).getValorString());
        System.out.println(si.filtrarJugadores(Consulta.fromString("[[[edad>16] AND [nombre='pepe']] OR [[escuela='una escuela'] AND [nombre='Julio']] OR [edad>42]] OR [[escuela='otra escuela'] OR [nombre='rob']]")).getValorString());
            System.out.println(Consulta.fromString("[[[edad>16] AND [nombre='pepe']] OR [[escuela='una escuela'] AND [nombre='Julio']] OR [edad>42]] OR [[escuela='otra escuela'] OR [nombre='rob']]").toString());

    }
}
