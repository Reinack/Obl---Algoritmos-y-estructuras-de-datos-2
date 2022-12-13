package dominio.estructuras;

import dominio.VisualizarArbolesGraphViz;
import dominio.visitor.Visitable;
import dominio.visitor.Visitor;
import excepciones.YaExisteException;

public class ABB<T extends Comparable<T>> implements Visitable<T> {
    private NodoAB<T> raiz;

    public ABB(NodoAB<T> raiz) {
        this.raiz = raiz;
    }

    public ABB(){

    }

    public void insertar(T elemento) throws YaExisteException {
        if(raiz==null){
            raiz=new NodoAB<T>(elemento);
        }else{
            insertarLargo(raiz,elemento);
        }
    }
    // este metodo inserta de manera ordenada menor a mayor creando un arbol binario de busqueda
    private void insertarLargo(NodoAB<T> nActual, T elemento) throws YaExisteException {
        if(nActual.getDato().equals(elemento)){
            throw new YaExisteException();
        }

        if(nActual.getDato().compareTo(elemento)>0){
            if(nActual.getIzq()!=null){
                insertarLargo(nActual.getIzq(),elemento);
            }else{
                nActual.setIzq(new NodoAB<T>(elemento));
            }
        }else{
            if(nActual.getDer()!=null){
                insertarLargo(nActual.getDer(),elemento);
            }else{
                nActual.setDer(new NodoAB<T>(elemento));
            }
        }
    }

    public T existe(T cascaron){
        return existe(raiz, cascaron);
    }

    private T existe(NodoAB<T> nActual, T cascaron){
        if (nActual == null) return null;
        else if (nActual.getDato().equals(cascaron)) return nActual.getDato();
        else if (nActual.getDato().compareTo(cascaron)>0) return existe(nActual.getIzq(),cascaron);
        else return existe(nActual.getDer(),cascaron);
    }
    public int queNivelEsta(T elemento){
        return queNivelEsta(this.raiz,elemento,0);
    }

    //recibe un T elemento (jugador)
    private int queNivelEsta(NodoAB<T> nActual,T elemento, int lvl) {
        if(nActual==null){
            return -1;
        }else if(nActual.getDato().compareTo(elemento) == 0){
            return lvl;
        } else if(nActual.getIzq()==null&&nActual.getDer()==null){
            return -1;
        }else if(nActual.getDer()==null){
            return queNivelEsta(nActual.getIzq(),elemento,lvl+1);
        }else if(nActual.getIzq()==null){
            return queNivelEsta(nActual.getDer(),elemento,lvl+1);
        }else{
            return Integer.max(queNivelEsta(nActual.getIzq(),elemento,lvl+1) ,queNivelEsta(nActual.getDer(),elemento,lvl+1) );
        }
    }

    public String toUrl(){
        return VisualizarArbolesGraphViz.arbolBinToUrl(raiz, a->a.getDato(), a->a.getIzq(), a->a.getDer());
    }

    @Override
    public void visitar(Visitor<T> visitor) {
        inOrder(visitor);
    }

    private void inOrder(Visitor<T> visitor){
        inOrder(raiz,visitor);
    }

    private void inOrder(NodoAB<T> nActual,Visitor<T> visitor){
         if (nActual!=null){
             inOrder(nActual.getIzq(),visitor);
             visitor.aceptar(nActual.getDato());
             inOrder(nActual.getDer(),visitor);
         }
    }


}
