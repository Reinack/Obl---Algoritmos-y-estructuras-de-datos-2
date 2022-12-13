package sistema;

import dominio.estructuras.*;
import dominio.visitor.*;
import excepciones.*;
import dominio.*;
import interfaz.Consulta;
import interfaz.EstadoCamino;
import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoJugador;


import excepciones.CedulaInvalidaException;
import excepciones.NoExisteException;
import excepciones.ParametrosNulosException;
import excepciones.YaExisteException;

public class ImplementacionSistema implements Sistema {
    ABB<Jugador> arbolJugadores;
    Grafo grafoCentrosUrbanos;
    ListaGenerica<Jugador>[] arrayDelistasDeJugadoresPorTipo;

    @Override
    public Retorno inicializarSistema(int maxCentros){
        try {
            if (maxCentros>5){
                grafoCentrosUrbanos = new Grafo(maxCentros);
                arbolJugadores = new ABB<>();
                arrayDelistasDeJugadoresPorTipo = new ListaGenerica[4];

                for (int i = 0; i < arrayDelistasDeJugadoresPorTipo.length; i++) {
                    arrayDelistasDeJugadoresPorTipo[i] = new ListaGenerica<>();
                }

                return new Retorno(Retorno.Resultado.OK,0,"");
            }else{
                throw new CantCentrosInvalidException();
            }
        }catch(CantCentrosInvalidException e){
            return new Retorno(Retorno.Resultado.ERROR_1,0,"No se pudo inicializar el sistema");
        }

    }
    @Override
    public Retorno explorarCentroUrbano(boolean[] correctas, int[] puntajes, int minimo) {
        try{
            if(correctas == null || puntajes == null ) throw new ParametrosNulosException();
            else {
                if (correctas.length < 3 || puntajes.length < 3) throw new ArraysMenoresA3Exception();
                else {
                    if (correctas.length != puntajes.length) throw new ArraysDistintoLargoException();
                    else {
                        if (minimo<=0) throw new MinimoMenorCeroException();
                        else {
                            int total=0;
                            int seguidas = 0;
                            for (int i = 0; i < correctas.length ; i++) {
                                if(correctas[i]){ // true
                                    total = total + puntajes[i];
                                    seguidas ++;
                                }else seguidas = 0;
                                if(seguidas ==3) total = total+3;
                                if(seguidas == 4) total = total+5;
                                if(seguidas >= 5)total = total+8;
                            }
                            if(total>=minimo) return new Retorno(Retorno.Resultado.OK,total,"pasa");
                            else return new Retorno(Retorno.Resultado.OK,total,"no pasa");
                        }
                    }
                }
            }
        } catch (ParametrosNulosException e1) {
            return new Retorno(Retorno.Resultado.ERROR_1, 0, "Error 1");
        } catch (ArraysMenoresA3Exception e2) {
            return new Retorno(Retorno.Resultado.ERROR_2, 0, "Error 2");
        } catch (ArraysDistintoLargoException e3) {
            return new Retorno(Retorno.Resultado.ERROR_3, 0, "Error 3");
        } catch (MinimoMenorCeroException e4) {
            return new Retorno(Retorno.Resultado.ERROR_4, 0, "Error 4");
        }
    }

    @Override
    public Retorno registrarJugador(String ci, String nombre, int edad, String escuela, TipoJugador tipo) {
        try {

            if (!esVacio(ci) && !esVacio(nombre) && edad > 0 && !esVacio(escuela) && tipo != null) {
                Cedula c=Cedula.crearCedula(ci);
                Jugador jugadorNuevo = new Jugador(tipo,c,edad,escuela,nombre);
                arbolJugadores.insertar(jugadorNuevo);
                arrayDelistasDeJugadoresPorTipo[tipo.getIndice()].agregarFin(jugadorNuevo);
                /*
                // Otra forma:
                switch (tipo.getIndice()){
                    //avanzado
                    case 0:{
                        arrayDelistasDeJugadoresPorTipo[0].agregarFin(jugadorNuevo);
                        break;
                    }
                    // medio
                    case 1:{
                        arrayDelistasDeJugadoresPorTipo[1].agregarFin(jugadorNuevo);
                        break;
                    }
                    // inicial
                    case 2:{
                        arrayDelistasDeJugadoresPorTipo[2].agregarFin(jugadorNuevo);
                        break;
                    }
                    //monitor
                    case 3:{
                        arrayDelistasDeJugadoresPorTipo[3].agregarFin(jugadorNuevo);
                        break;
                    }
                    default: {
                        System.out.println("Opcion incorrecta");
                    }
                }

                 */

                return new Retorno(Retorno.Resultado.OK,0,"Correcto");
            } else {
                //error 1
                throw new ParametrosNulosException();

            }
        } catch (ParametrosNulosException e1) {
            return new Retorno(Retorno.Resultado.ERROR_1, 0, "Error 1");
        } catch (CedulaInvalidaException e2) {
            return new Retorno(Retorno.Resultado.ERROR_2, 0, "Error 2");
        } catch (YaExisteException e3) {
            return new Retorno(Retorno.Resultado.ERROR_3, 0, "Error 3");
        }

    }

    @Override
    public Retorno filtrarJugadores(Consulta consulta){
        try {
            if (consulta.getRaiz()==null) throw new ParametrosNulosException();
            VisitorListaAgregoFin<Jugador> visitorListaAgregoFin = new VisitorListaAgregoFin<Jugador>();
            arbolJugadores.visitar(visitorListaAgregoFin);

            ListaGenerica<Jugador> lGJugador = consulta.filtrar(visitorListaAgregoFin.getMiListaGenerica());

            VisitorAgregoCedulaFin visitorAgregoCedulaFin = new VisitorAgregoCedulaFin();

            lGJugador.visitar(visitorAgregoCedulaFin);

            ListaGenerica<String> listaStrResultado = visitorAgregoCedulaFin.getMiListaGenerica();
            VisitorSerializador<String> visitorSerializador = new VisitorSerializador<>("|");
            listaStrResultado.visitar(visitorSerializador);

            return new Retorno(Retorno.Resultado.OK,0,visitorSerializador.getSerializado());

        }catch (ParametrosNulosException e){
            return new Retorno(Retorno.Resultado.ERROR_1,0,"Error 1");
        }


    }

    public Retorno buscarJugador(String ci){
        try{
            // en crear cedula se valida la cedula.
            Cedula cedulaAVerificar = Cedula.crearCedula(ci);

            Jugador jugadorBuscado = arbolJugadores.existe(new Jugador(cedulaAVerificar));
            if (jugadorBuscado == null){
                throw new NoExisteException();
            }else{
                int saltos = arbolJugadores.queNivelEsta(jugadorBuscado);

                return new Retorno(Retorno.Resultado.OK,saltos, jugadorBuscado.toString());
            }


        }catch (CedulaInvalidaException e1){
            return new Retorno(Retorno.Resultado.ERROR_1, 0, "Error 1");
        }catch (NoExisteException e2){
            return new Retorno(Retorno.Resultado.ERROR_2, 0, "Error 2");
        }
    }


    @Override
    public Retorno listarJugadoresPorCedulaAscendente() {
        VisitorSerializador<Jugador> visitorSerializador = new VisitorSerializador<>("|");
        arbolJugadores.visitar(visitorSerializador);
        return new Retorno(Retorno.Resultado.OK,0,visitorSerializador.getSerializado());
    }


    @Override
    public Retorno listarJugadoresPorCedulaDescendente() {
        VisitorListaAgregoPpio<Jugador> visitorListaPpio = new VisitorListaAgregoPpio<>();
        arbolJugadores.visitar(visitorListaPpio);
        VisitorSerializador<Jugador> visitorSerializador = new VisitorSerializador<>("|");
        visitorListaPpio.getMiListaGenerica().visitar(visitorSerializador);

        return new Retorno(Retorno.Resultado.OK,0,visitorSerializador.getSerializado());
    }

    @Override
    public Retorno listarJugadoresPorTipo(TipoJugador unTipo) {
        try{
            if (unTipo == null){
                throw new ParametrosNulosException();

            }
            ListaGenerica<Jugador> listaFiltrada = arrayDelistasDeJugadoresPorTipo[unTipo.getIndice()];


            VisitorSerializador<Jugador> visitorSerializador = new VisitorSerializador<>("|");
            listaFiltrada.visitar(visitorSerializador);

            return new Retorno(Retorno.Resultado.OK,0,visitorSerializador.getSerializado());
        }catch (ParametrosNulosException e){
            return new Retorno(Retorno.Resultado.ERROR_1,0,"No se puede ingresar un tipo nulo");
        }


    }
    @Override

    public Retorno registrarCentroUrbano(String codigo, String nombre){

        try {
            if (!grafoCentrosUrbanos.isFull()){
                if (!esVacio(codigo) && !esVacio(nombre)) {
                    CentroUrbano centroAAgregar = new CentroUrbano(nombre,codigo);
                    if (!grafoCentrosUrbanos.existeCentroUrbano(codigo)){
                        grafoCentrosUrbanos.agregarVertice(centroAAgregar);
                        return new Retorno(Retorno.Resultado.OK,0,"Correcto");
                    }else{
                        throw new YaExisteException();
                    }

                } else{
                    //error 1
                    throw new ParametrosNulosException();

                }
            }else{
                throw new MaxCentrosException();
            }

        }catch (MaxCentrosException e1) {
            return new Retorno(Retorno.Resultado.ERROR_1, 0, "Error 1");
        }
        catch (ParametrosNulosException e2) {
            return new Retorno(Retorno.Resultado.ERROR_2, 0, "Error 2");
        }
        catch (YaExisteException e3)
        {
            return new Retorno(Retorno.Resultado.ERROR_3,0,"Error 3");
        }

    }


    @Override
    public Retorno registrarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        try {
            if (kilometros<1 || costo<1 || tiempo<1) throw new ParametrosNegativosException();

            if (!esVacio(codigoCentroOrigen) && !esVacio(codigoCentroDestino) && estadoDelCamino!=null){
                grafoCentrosUrbanos.registroArista(codigoCentroOrigen,codigoCentroDestino,costo,kilometros,tiempo,estadoDelCamino);
                return new Retorno(Retorno.Resultado.OK,0,"Camino registrado exitosamente");
            }else{
                throw new ParametrosNulosException();
            }

        }catch (ParametrosNegativosException e1){
            return new Retorno(Retorno.Resultado.ERROR_1,0,"Error 1");
        }catch (ParametrosNulosException e2){
            return new Retorno(Retorno.Resultado.ERROR_2,0,"Error 2");
        }catch (VerticeNoExisteException e3){
            if (e3.isEsOrigen()) return new Retorno(Retorno.Resultado.ERROR_3,0,"Error 3");
            else return new Retorno(Retorno.Resultado.ERROR_4,0,"Error 4");

        }catch (YaExisteException e5){
            return new Retorno(Retorno.Resultado.ERROR_5,0,"Error 5");
        }

    }

    @Override
    public Retorno actualizarCamino(String codigoCentroOrigen, String codigoCentroDestino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        try {
            if (kilometros<1 || costo<1 || tiempo<1) throw new ParametrosNegativosException();

            if (!esVacio(codigoCentroOrigen) && !esVacio(codigoCentroDestino) && estadoDelCamino!=null){
                grafoCentrosUrbanos.actualizarArista(codigoCentroOrigen,codigoCentroDestino,costo,kilometros,tiempo,estadoDelCamino);

                return new Retorno(Retorno.Resultado.OK,0,"Camino actualizado exitosamente");
            }else{
                throw new ParametrosNulosException();
            }

        }catch (ParametrosNegativosException e1){
            return new Retorno(Retorno.Resultado.ERROR_1,0,"Error 1");
        }catch (ParametrosNulosException e2){
            return new Retorno(Retorno.Resultado.ERROR_2,0,"Error 2");
        }catch (VerticeNoExisteException e3){
            if (e3.isEsOrigen()) return new Retorno(Retorno.Resultado.ERROR_3,0,"Error 3");
            else return new Retorno(Retorno.Resultado.ERROR_4,0,"Error 4");
        }catch (NoExisteException e5){
            return new Retorno(Retorno.Resultado.ERROR_5,0,"Error 5");
        }
    }


    @Override
    public Retorno listadoCentrosCantDeSaltos(String codigoCentroOrigen, int cantidad) {
        try {
            if (cantidad<0) throw new NumeroNegativoException() ;
            else {
                if (!grafoCentrosUrbanos.existeCentroUrbano(codigoCentroOrigen)) throw new NoExisteException();
                else {
                    ABB<CentroUrbano> arbolCentros = grafoCentrosUrbanos.obtenerCentrosCantSaltos(codigoCentroOrigen, cantidad);
                    VisitorSerializador<CentroUrbano> visitorSerializador = new VisitorSerializador<>("|");
                    arbolCentros.visitar(visitorSerializador);
                    return new Retorno(Retorno.Resultado.OK, 0, visitorSerializador.getSerializado());
                }
            }

        }catch (NumeroNegativoException e1){
            return new Retorno(Retorno.Resultado.ERROR_1,0,"Error 1");
        }catch (NoExisteException e2){
            return new Retorno(Retorno.Resultado.ERROR_2,0,"Error 2");
        } catch (YaExisteException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCentroOrigen, String codigoCentroDestino) {

        try{
            if (!esVacio(codigoCentroOrigen) && !esVacio(codigoCentroDestino)){
                Tupla<ListaGenerica<VerticeCentroUrbano>,Double> tuplaCentros = grafoCentrosUrbanos.dijkstra(codigoCentroOrigen,codigoCentroDestino);
                ListaGenerica<VerticeCentroUrbano> listaCentros = tuplaCentros.getUno();
                double kmResultado = tuplaCentros.getDos();
                VisitorSerializador<VerticeCentroUrbano> visitorSerializador = new VisitorSerializador<>("|");
                listaCentros.visitar(visitorSerializador);

                return new Retorno(Retorno.Resultado.OK,(int)kmResultado, visitorSerializador.getSerializado());

            }else{
                throw new ParametrosNulosException();
            }

        }catch(ParametrosNulosException e1){
            return new Retorno(Retorno.Resultado.ERROR_1,0,"Error 1");
        }catch (NoHayCaminoException e2){
            return new Retorno(Retorno.Resultado.ERROR_2,0,"Error 2");
        }catch (VerticeNoExisteException e3){
            if (e3.isEsOrigen()) return new Retorno(Retorno.Resultado.ERROR_3,0,"Error 3");
            else return new Retorno(Retorno.Resultado.ERROR_4,0,"Error 4");
        }
    }

    @Override
    public Retorno viajeCostoMinimoMonedas(String codigoCentroOrigen, String codigoCentroDestino) {

        try{
            if (!esVacio(codigoCentroOrigen) && !esVacio(codigoCentroDestino)){
                Tupla<ListaGenerica<VerticeCentroUrbano>,Double> tuplaCentros = grafoCentrosUrbanos.dijkstraCoin(codigoCentroOrigen,codigoCentroDestino);
                ListaGenerica<VerticeCentroUrbano> listaCentros = tuplaCentros.getUno();
                double kmResultado = tuplaCentros.getDos();
                VisitorSerializador<VerticeCentroUrbano> visitorSerializador = new VisitorSerializador<>("|");
                listaCentros.visitar(visitorSerializador);

                return new Retorno(Retorno.Resultado.OK,(int)kmResultado, visitorSerializador.getSerializado());

            }else{
                throw new ParametrosNulosException();
            }

        }catch(ParametrosNulosException e1){
            return new Retorno(Retorno.Resultado.ERROR_1,0,"Error 1");
        }catch (NoHayCaminoException e2){
            return new Retorno(Retorno.Resultado.ERROR_2,0,"Error 2");
        }catch (VerticeNoExisteException e3){
            if (e3.isEsOrigen()) return new Retorno(Retorno.Resultado.ERROR_3,0,"Error 3");
            else return new Retorno(Retorno.Resultado.ERROR_4,0,"Error 4");
        }
    }


    private boolean esVacio(String cadena){
        if (cadena == null) return true;
        else return cadena.equals("");
    }



}
