package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        Nodo[] nExplorados = new Nodo[1000];
        int count = 0;//?????
        Nodo nodoActual = new Nodo(null, p.getEstadoInicial(), null);
        explorados.add(nodoActual.getEstado());
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
                System.out.println((i++) + " - RESULT(" + nodoActual.getEstado() + ","+ acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    count++;
                    nodoActual = new Nodo(nodoActual, sc, acc);//???
                    System.out.println((i++) + " - " + nodoActual.getEstado() + " NO explorado");
                    explorados.add(nodoActual.getEstado());
                    nExplorados[count] = nodoActual;
                    modificado = true;
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
