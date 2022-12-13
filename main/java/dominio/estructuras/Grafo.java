package dominio.estructuras;

import dominio.CentroUrbano;
import dominio.VisualizarArbolesGraphViz;
import excepciones.*;
import interfaz.EstadoCamino;

public class Grafo{
    private VerticeCentroUrbano [] vertices;
    private AristaInfluencia[][] aristas;
    private int maxVertices;
    private int largo;

    public Grafo(int maxVertices){
        this.vertices = new VerticeCentroUrbano[maxVertices];
        this.aristas = new AristaInfluencia[maxVertices][maxVertices];
        this.maxVertices = maxVertices;

        for (int fila = 0; fila < maxVertices; fila++) {
            for (int col = 0; col < maxVertices; col++) {
                this.aristas[fila][col] = new AristaInfluencia();
            }
        }
    }

    public void agregarVertice(CentroUrbano dato) throws MaxCentrosException {
        if (largo<maxVertices){
            vertices[largo] = new VerticeCentroUrbano();
            vertices[largo].setCentroUrbano(dato);
            largo++;
            return;
        }else{
            throw new MaxCentrosException();
        }
    }

    public void registroArista(String codigoOrigen, String codigoDestino, double costo, double km, double tiempo, EstadoCamino estadoCamino) throws VerticeNoExisteException, ParametrosNegativosException, YaExisteException {
        try{
            int idxOrigen = buscarIndice(codigoOrigen,true);
            int idxDestino = buscarIndice(codigoDestino, false);
            this.registroArista(idxOrigen,idxDestino,costo,km,tiempo,estadoCamino);

        }catch (VerticeNoExisteException e){
            throw new VerticeNoExisteException(e.isEsOrigen());
        }

    }


    private void registroArista(int idxOrigen, int idxDestino, double costo, double km, double tiempo, EstadoCamino estadoCamino) throws ParametrosNegativosException, YaExisteException{
        AristaInfluencia aristaOrigenDestino =  this.aristas[idxOrigen][idxDestino];
        if (aristaOrigenDestino.isExiste()) throw new YaExisteException();

        aristaOrigenDestino.setExiste(true); // equivale a poner un 1 en la matriz de adyacencias
        aristaOrigenDestino.setCosto(costo);
        aristaOrigenDestino.setKm(km);
        aristaOrigenDestino.setTiempo(tiempo);
        aristaOrigenDestino.setEstado(estadoCamino);
    }

    private int buscarIndice(String codigo, boolean esOrigen){
        for (int i = 0; i < largo; i++) {
            if (vertices[i].getCentroUrbano().getCodigo().equals(codigo)) return i;
        }
        throw new VerticeNoExisteException(esOrigen);
    }
    public boolean isFull(){
        return largo>=maxVertices;
    }

    private boolean esAdyacente(int vExplorar, int v) {
        AristaInfluencia miArista = aristas[vExplorar][v];
        return miArista.isExiste();
    }

    public ABB<CentroUrbano> obtenerCentrosCantSaltos(String codigoCentroOrigen, int cantidad) throws YaExisteException {
        int idCentroOrigen = buscarIndice(codigoCentroOrigen,true);
        return bfsMaxSaltos(idCentroOrigen,cantidad);
    }

    private ABB<CentroUrbano> bfsMaxSaltos(int vOrigen, int cantSaltos) throws YaExisteException {
        ABB<CentroUrbano> arbolCentrosAux = new ABB<>();
        ListaGenerica<Integer> frontera = new ListaGenerica<>();
        boolean[] visitados = new boolean[maxVertices-1];
        frontera.agregarFin(vOrigen);
        int NivActual = 1;
        int NivSiguiente = 0;
        int Nivel = 0;
        while (!frontera.esVacia()){
            int vExplorar = frontera.borrarInicio();
            NivActual--;

            if (!visitados[vExplorar]){

                if (Nivel<=cantSaltos ) arbolCentrosAux.insertar(obtenerCentroUrbano(vExplorar));

                visitados[vExplorar] = true;
                for (int vAdy = 0; vAdy <= maxVertices-1; vAdy++) {
                    if (esAdyacente(vExplorar, vAdy) && cantSaltos> Nivel ){
                        frontera.agregarFin(vAdy);
                        NivSiguiente++;
                    }
                }
                if (NivActual==0){
                    Nivel++;
                    NivActual=NivSiguiente;
                    NivSiguiente=0;
                }
            }
        }
        return arbolCentrosAux;
    }


    public CentroUrbano obtenerCentroUrbano(int indice){
        for (int i = 0; i < maxVertices; i++) {
            if (indice == i) return vertices[i].getCentroUrbano();
        }
        return null;
    }


    public boolean existeCentroUrbano(String codigo) {
        try{
            int indice = buscarIndice(codigo,true);
            return true;
        }catch(VerticeNoExisteException e){
            return false;
        }
    }


    public void actualizarArista(String codigoCentroOrigen, String codigoCentroDestino, double costo, double kilometros, double tiempo, EstadoCamino estadoDelCamino) throws NoExisteException {
        try{
            int idxOrigen = buscarIndice(codigoCentroOrigen,true);
            int idxDestino = buscarIndice(codigoCentroDestino, false);
            this.actualizarArista(idxOrigen,idxDestino,costo,kilometros,tiempo,estadoDelCamino);

        }catch (VerticeNoExisteException e){
            throw new VerticeNoExisteException(e.isEsOrigen());
        }

    }

    private void actualizarArista(int idxOrigen, int idxDestino, double costo, double kilometros, double tiempo, EstadoCamino estadoDelCamino) throws NoExisteException {
        AristaInfluencia aristaOrigenDestino =  this.aristas[idxOrigen][idxDestino];

        if (!aristaOrigenDestino.isExiste()) throw new NoExisteException();

        //aristaOrigenDestino.setExiste(true); // equivale a poner un 1 en la matriz de adyacencias
        aristaOrigenDestino.setCosto(costo);
        aristaOrigenDestino.setKm(kilometros);
        aristaOrigenDestino.setTiempo(tiempo);
        aristaOrigenDestino.setEstado(estadoDelCamino);
    }

    public Tupla<ListaGenerica<VerticeCentroUrbano>,Double> dijkstra(String origen, String destino) throws NoHayCaminoException {
        return this.dijkstra(buscarIndice(origen,true),buscarIndice(destino,false));
    }

    private  Tupla<ListaGenerica<VerticeCentroUrbano>,Double> dijkstra(int vOrigen, int vDestino) throws NoHayCaminoException {
        Tupla<ListaGenerica<VerticeCentroUrbano>,Double> tuplaLista_Kilometros = new Tupla<>();

        double [] distancias = new double[maxVertices];
        boolean [] visitados = new boolean[maxVertices];
        int [] padres=new int[maxVertices];

        for (int i = 0; i < maxVertices; i++) {
            distancias[i]=Double.MAX_VALUE;
            padres[i]=-1;
        }

        padres[vOrigen] = vOrigen;
        distancias[vOrigen] = 0;
        while(!estaTodoVisitado(visitados,padres)){

            int vExplorar=dameElVerticeConMenorDistanciaNoVisitado(visitados,distancias);
            for (int vAdyacente = 0; vAdyacente < maxVertices; vAdyacente++) {
                if(esAdyacente(vExplorar,vAdyacente)){

                    double distanciaPorVAExplorar= distancias[vExplorar]+ aristas[vExplorar][vAdyacente].getKm();

                    if(distanciaPorVAExplorar<distancias[vAdyacente]&&aristas[vExplorar][vAdyacente].getEstado()!=EstadoCamino.MALO) {
                        distancias[vAdyacente]=distanciaPorVAExplorar;
                        padres[vAdyacente]=vExplorar;
                    }

                }
            }
            visitados[vExplorar]=true;

        }

        double contadorkm=distancias[vDestino];
        tuplaLista_Kilometros.setDos(contadorkm);
        tuplaLista_Kilometros.setUno(reconstruirCamino(padres,vOrigen,vDestino));

        return tuplaLista_Kilometros;



    }




    private ListaGenerica<VerticeCentroUrbano> reconstruirCamino(int[] padres, int vOrigen, int vDestino) throws NoHayCaminoException {

        ListaGenerica<VerticeCentroUrbano> camino=new ListaGenerica<>();

        int verticeActual=vDestino;
        //me paro en el ultimo y voy para atras haciendo un agregar incio
        //yendo a traves de sus pares
        while(verticeActual!=vOrigen){

            if (verticeActual==-1) throw new NoHayCaminoException(); //return null;
            //si veo un menos uno es que no habia camino al nodo
            camino.agregarInicio(vertices[verticeActual]);
            verticeActual = padres[verticeActual];
        }
        camino.agregarInicio(vertices[vOrigen]);
        return camino;

    }

    private boolean estaTodoVisitado(boolean [] visitados,int [] padres) {
        //recorro los visitados y veo si todo fue visitado o no
        for (int i = 0; i < visitados.length; i++) {
            if(!visitados[i] && padres[i]>=0) return false;
        }
        return true;
    }

    private int dameElVerticeConMenorDistanciaNoVisitado(boolean[] visitados, double[] distancias) {
        double minDist = Double.MAX_VALUE;
        int minIdx=-1;//la posicion del minimo
        for (int i = 0; i < visitados.length; i++) {
            if (distancias[i]<minDist && !visitados[i]){
                minDist = distancias[i];
                minIdx=i;
            }
        }
        return minIdx;
    }



    public Tupla<ListaGenerica<VerticeCentroUrbano>,Double> dijkstraCoin(String origen, String destino) throws NoHayCaminoException {
        return this.dijkstraCoin(buscarIndice(origen,true),buscarIndice(destino,false));
    }

    private  Tupla<ListaGenerica<VerticeCentroUrbano>,Double> dijkstraCoin(int vOrigen, int vDestino) throws NoHayCaminoException {
        Tupla<ListaGenerica<VerticeCentroUrbano>,Double> tuplaLista_Kilometros = new Tupla<>();

        double [] distancias = new double[maxVertices];
        boolean [] visitados = new boolean[maxVertices];
        int [] padres=new int[maxVertices];

        for (int i = 0; i < maxVertices; i++) {
            distancias[i]=Double.MAX_VALUE;
            padres[i]=-1;
        }

        padres[vOrigen] = vOrigen;
        distancias[vOrigen] = 0;
        while(!estaTodoVisitado(visitados,padres)){

            int vExplorar=dameElVerticeConMenorDistanciaNoVisitado(visitados,distancias);
            for (int vAdyacente = 0; vAdyacente < maxVertices; vAdyacente++) {
                if(esAdyacente(vExplorar,vAdyacente)){

                    double distanciaPorVAExplorar= distancias[vExplorar]+ aristas[vExplorar][vAdyacente].getCosto();

                    if(distanciaPorVAExplorar<distancias[vAdyacente]&&aristas[vExplorar][vAdyacente].getEstado()!=EstadoCamino.MALO) {
                        distancias[vAdyacente]=distanciaPorVAExplorar;
                        padres[vAdyacente]=vExplorar;
                    }

                }
            }
            visitados[vExplorar]=true;

        }

        double contadorkm=distancias[vDestino];
        tuplaLista_Kilometros.setDos(contadorkm);
        tuplaLista_Kilometros.setUno(reconstruirCamino(padres,vOrigen,vDestino));


        return tuplaLista_Kilometros;



    }

    public String toUrl(){
        return VisualizarArbolesGraphViz.grafoToUrl(vertices,aristas, a->a.isExiste(), v->v.getCentroUrbano().getNombre(), a->a.getKm()+"");
    }
    public String toString(){
        return VisualizarArbolesGraphViz.grafoToString(vertices,aristas,a->a.isExiste(),v->v.getCentroUrbano().getNombre(),a->a.getKm()+"");
    }


}
