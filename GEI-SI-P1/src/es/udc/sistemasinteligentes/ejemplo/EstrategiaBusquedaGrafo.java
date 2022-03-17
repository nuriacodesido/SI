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
        ArrayList<Nodo> explorados= new ArrayList<>();
        Queue<Nodo> frontera =  new LinkedList();
        Nodo nodo = new Nodo(null, p.getEstadoInicial(), null);
        boolean modificado = false;

        frontera.addAll(sucesores(nodo,p));//Expandir el nodo inicial.

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + nodo.getEstado());


        while (!p.esMeta(nodo.getEstado())) {
            if (frontera != null) {
                nodo = frontera.element();//Seleccionamos nodo
                 frontera.remove(nodo);//Eliminar de la frontera
                 System.out.println((i++) + " - Eliminamos nodo con estado" + nodo.getEstado());
                 if (!p.esMeta(nodo.getEstado())) {//Si el nodo hoja no contiene estado meta
                    System.out.println((i++) + " - " + nodo.getEstado() + " no es meta");
                    explorados.add(nodo);//Añadimos el nodo al conjunto explorados
                    System.out.println((i++) + " - Añadimos el nodo con estado" + nodo.getEstado() + "a explorados");
                    if ((!explorados.contains(nodo)) || !(frontera.contains(nodo))) {//Si el nodo no está en la frontera o en explorados.
                        System.out.println((i++) + " - " + nodo.getEstado() + " puede no estar explorado o no encontrarse en la frontera");
                        ArrayList<Nodo> sucesores = sucesores(nodo, p);//Expandimos nodo
                        frontera.addAll(sucesores);//Añadimos los nodos resultantes a la frontera
                    }
                } else {//Por el contrario
                    modificado = true;
                    System.out.println((i++) + " - FIN - " + nodo.getEstado());
                    return reconstruye_sol(nodo);//devolvemos la solución
                }

            } else
                throw new Exception("Frontera vacía");
        }

        if (!modificado) throw new Exception("No se ha podido encontrar una solución");

        return reconstruye_sol(nodo);
    }

    public ArrayList<Nodo> sucesores(Nodo nodo,ProblemaBusqueda problemaBusqueda){
        ArrayList<Nodo> sucesores= new ArrayList<>();
        Accion[] accionesDisponibles = problemaBusqueda.acciones(nodo.getEstado());
        for (Accion acc: accionesDisponibles){
            Nodo n = new Nodo(nodo, nodo.getEstado(), acc);
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
