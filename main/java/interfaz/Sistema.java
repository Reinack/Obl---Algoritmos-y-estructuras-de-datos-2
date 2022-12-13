package interfaz;

/**
 * Provee una interfaz para interactuar con el sistema
 */
public interface Sistema {
    /**
     * @param maxCentros La cantidad máxima de centros
     * @return Ok
     */
    Retorno inicializarSistema(int maxCentros);

    /**
     * Explora un centro urbano
     *
     * @param correctas las preguntas que respondió correctamente el jugador
     * @param puntajes  los puntajes de cada pregunta
     * @param minimo    el minimo de puntos
     */
    Retorno explorarCentroUrbano(boolean[] correctas, int[] puntajes, int minimo);

    /**
     * Registra el jugador en el sistema
     *
     * @param ci      La cedula del jugador
     * @param nombre  El nombre del jugador
     * @param escuela La escuela del jugador
     * @param tipo    El tipo de jugador
     * @return
     */
    Retorno registrarJugador(String ci, String nombre, int edad, String escuela, TipoJugador tipo);

    /**
     * Nos devuelve los jugadores que cumplen la condicion especificada en la consulta
     *
     * @param consulta La consulta a aplicar
     */
    Retorno
    filtrarJugadores(Consulta consulta);

    /**
     * Busca un jugador en l sistema por cedula
     *
     * @param ci La cedula a buscar
     */
    Retorno buscarJugador(String ci);

    /**
     * @return Los jugadores listados por cedula ascendente
     */
    Retorno listarJugadoresPorCedulaAscendente();

    /**
     * @return Los jugadores listados por cedula descendente
     */
    Retorno listarJugadoresPorCedulaDescendente();

    /**
     * @param unTipo El tipo de jugador a buscar
     * @return Los jugadores de un tipo especifico
     */
    Retorno listarJugadoresPorTipo(TipoJugador unTipo);

    /**
     * @param codigo El codigo del centro urbano a registrar
     * @param nombre El nombre del centro urbano a registrar
     */
    Retorno registrarCentroUrbano(String codigo, String nombre);

    /**
     * Registra un camino en el sistema
     *
     * @param codigoCentroOrigen  El codigo del centro urbano de origen
     * @param codigoCentroDestino El codigo del centro urbano de destino
     * @param costo               El costo en monedas del camino
     * @param tiempo              El tiempo del camino
     * @param kilometros          Los kilometros del camino
     * @param estadoDelCamino     El estado del camino
     */
    Retorno registrarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino);

    /**
     * Actualiza un camino en el sistema
     *
     * @param codigoCentroOrigen  El codigo del centro urbano de origen
     * @param codigoCentroDestino El codigo del centro urbano de destino
     * @param costo               El costo en monedas del camino
     * @param tiempo              El tiempo del camino
     * @param kilometros          Los kilometros del camino
     * @param estadoDelCamino     El estado del camino
     */
    Retorno actualizarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino);

    /**
     * @param codigoCentroOrigen El centro de origen
     * @param cantidad           La cantidad de saltos maxima
     * @return Los centros a los que se puede llegar en cantidad saltos
     */
    Retorno listadoCentrosCantDeSaltos(String codigoCentroOrigen, int cantidad);

    /**
     * @param codigoCentroOrigen  El centro de origen
     * @param codigoCentroDestino El centro de destino
     * @return El camino si lo hay entre el origen y el destino con los menores kilometros posibles
     */
    Retorno viajeCostoMinimoKilometros(String codigoCentroOrigen, String codigoCentroDestino);

    /**
     * @param codigoCentroOrigen  El centro de origen
     * @param codigoCentroDestino El centro de destino
     * @return El camino si lo hay entre el origen y el destino con el menor costo en monedas posible
     */
    Retorno viajeCostoMinimoMonedas(String codigoCentroOrigen, String codigoCentroDestino);

}
