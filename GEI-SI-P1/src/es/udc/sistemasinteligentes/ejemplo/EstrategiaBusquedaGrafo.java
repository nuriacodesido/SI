package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {


    public EstrategiaBusquedaGrafo() {
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        //Inicializar conjunto explorados como vacío
        ArrayList<Nodo> explorados= new ArrayList<>();
        //Inicializar frontera usando el estado inicial del problema
        Queue<Nodo> frontera =  new LinkedList();
        Nodo nodo = new Nodo(null, p.getEstadoInicial(), null);
        frontera.add(nodo);//Expandir el nodo inicial.

        int i = 1;
        System.out.println((i++) + " - Empezando búsqueda en " + nodo.getEstado());

        //Bucle hacer
        while (!p.esMeta(nodo.getEstado())) {
            //Si frontera no está vacía
            if (!frontera.isEmpty()) {
                //Escogemos nodo hoja y lo eliminamos de la frontera
                nodo = frontera.element();
                frontera.remove(nodo);
                 System.out.println((i++) + " - Eliminamos nodoHoja " + nodo.getEstado() + " de la frontera");
                 //Si nodo hoja no contiene un estado meta
                 if (!p.esMeta(nodo.getEstado())) {
                    System.out.println((i++) + " - " + nodo.getEstado() + " no es meta");
                    //Añadimos el nodo al conjunto explorados
                    explorados.add(nodo);
                    System.out.println((i++) + " - Añadimos el nodo " + nodo.getEstado() + " a explorados");
                    //SI los sucesores NO estan en la frontera, los añadimos
                     ArrayList<Nodo> sucesores = sucesores(nodo, p); //Expandimos nodoHoja
                     for (Nodo suc: sucesores) { //Para cada sucesor
                         //Si no esta en la frontera o en explorados
                         if (!explorados.contains(suc) || !frontera.contains(suc)) {
                             System.out.println((i++) + " - " + suc.getEstado() + " no esta explorado ni se encuentra en la frontera");
                             //Añadimos ese sucesor a la frontera
                             frontera.add(suc);
                         } else {
                             System.out.println((i++) + " - Nodo " + nodo.getEstado() + " ya explorado");
                         }
                     }
                } else { //Si nodo hoja contiene un estado meta
                    System.out.println((i++) + " - FIN - " + nodo.getEstado());
                    //Devolvemos la solución
                    return reconstruye_sol(nodo);
                }

            } else //Si frontera está vacía
                throw new Exception("La frontera está vacía");
        }
        throw new Exception("No se encontró una meta");
    }

    public ArrayList<Nodo> sucesores(Nodo nodo,ProblemaBusqueda problemaBusqueda){
        ArrayList<Nodo> sucesores= new ArrayList<>();
        Accion[] accionesDisponibles = problemaBusqueda.acciones(nodo.getEstado());
        for (Accion acc: accionesDisponibles){
            Nodo n = new Nodo(nodo, problemaBusqueda.result(nodo.getEstado(),acc), acc);
            sucesores.add(n);
        }
        return sucesores;
    }

    public Nodo[] reconstruye_sol(Nodo nodo){
        Nodo[] node= new Nodo[1000];
        for(int i=0;(i<node.length)&&(nodo!=null);i++) {
            node[i] = nodo;
            nodo = nodo.getPadre();
        }
        return node;
    }


}
