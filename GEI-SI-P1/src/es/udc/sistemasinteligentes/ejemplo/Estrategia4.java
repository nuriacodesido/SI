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
        Estado estadoActual = p.getEstadoInicial();
        Nodo nodoActual = new Nodo(null, estadoActual, null);
        //explorados.add(estadoActual);
        int count = 0;
        nExplorados[0] = nodoActual;
        boolean comprobar = false;

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + nodoActual.getEstado());

        while (!p.esMeta(nodoActual.getEstado())){
            System.out.println((i++) + " - " + nodoActual.getEstado() + " no es meta");
            Accion[] accionesDisponibles = p.acciones(nodoActual.getEstado());
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {
                Estado sc = p.result(nodoActual.getEstado(), acc);
                Nodo nodoActualSig = new Nodo(nodoActual, sc, acc);
                count++;
                nExplorados[count] = nodoActualSig;
                System.out.println((i++) + " - RESULT(" + nodoActualSig.getEstado() + ","+ acc + ")=" + sc);
                for ( int j=0; j<=nExplorados.length; j++){
                    if (nExplorados[j].getEstado().equals(sc))
                        comprobar = true;
                }
                if (!comprobar) {
                    //count++;
                    nodoActualSig.setEstado(sc);
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    //nExplorados[count] = nodoActual;
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + nodoActualSig.getEstado());
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");

                reconstruye_sol(nodoActual);//???
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + nodoActual.getEstado());
        return nExplorados;
    }

    public ArrayList<Nodo> reconstruye_sol(Nodo nodo){
        ArrayList<Nodo> solucion = new ArrayList<Nodo>();
        Nodo actual = nodo;
        while(actual.getAccion()!=null){
            solucion.add(actual);
            actual.setAccion((actual.getPadre()).getAccion());
        }
        return solucion;
    }
}
