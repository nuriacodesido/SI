package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        //ArrayList<Estado> explorados = new ArrayList<Estado>();
        Nodo[] nExplorados = new Nodo[1000];
        int count = 0;//?????
        //Estado estadoActual = p.getEstadoInicial();
        Nodo nodoActual = new Nodo(null, p.getEstadoInicial(), null);
        //explorados.add(estadoActual);
        //int count = -1;
        nExplorados[0] = nodoActual;//?????
        boolean yaExplorado = false;

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + nodoActual.getEstado());

        while (!p.esMeta(nodoActual.getEstado())){
            System.out.println((i++) + " - " + nodoActual.getEstado() + " no es meta");

            Accion[] accionesDisponibles = p.acciones(nodoActual.getEstado());
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {
                Estado sc = p.result(nodoActual.getEstado(), acc);
                //Nodo nodoActualSig = new Nodo(nodoActual, sc, acc);
                //count++;
                //nExplorados[count] = nodoActual;
                nodoActual = new Nodo(nodoActual, sc, acc);//???
                System.out.println((i++) + " - RESULT(" + nodoActual.getPadre().getEstado() + ","+ acc + ")=" + nodoActual.getEstado());
                for ( int j=0; (j<=count)&&(count>0); j++){//????
                    if (nExplorados[j].getEstado().equals(sc))
                        yaExplorado = true;
                    else
                        yaExplorado = false;
                }
                if (!yaExplorado) {
                    count++;
                    System.out.println((i++) + " - " + nodoActual.getEstado() + " NO explorado");
                    nExplorados[count] = nodoActual;
                    modificado = true;
                    yaExplorado = true; //???
                    System.out.println((i++) + " - Estado actual cambiado a " + nodoActual.getEstado());
                    break;
                }
                else
                    System.out.println((i++) + " - " + nodoActual.getEstado() + " ya explorado");

            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + nodoActual.getEstado());
        reconstruye_sol(nodoActual);//???
        return nExplorados;
    }

    public ArrayList<Nodo> reconstruye_sol(Nodo nodo){
        ArrayList<Nodo> solucion = new ArrayList<>();
        while(nodo!=null){
            solucion.add(nodo);
            nodo = nodo.getPadre();
        }
        /*Nodo actual = nodo;
        while(actual.getAccion()!=null){
            solucion.add(actual);
            actual.setAccion((actual.getPadre()).getAccion());
        }*/
        return solucion;
    }
}
