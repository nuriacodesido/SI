package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class EstrategiaBusquedaAmplitud implements EstrategiaBusqueda {

    public EstrategiaBusquedaAmplitud (){
    }


    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Nodo> explorados= new ArrayList<>();
        Queue<Nodo> frontera =  new LinkedList();
        Nodo nodo = new Nodo(null, p.getEstadoInicial(), null);
        ArrayList<Nodo> sucesores = new ArrayList<>();

        frontera.add(nodo);//Inicializar frontera usando el estado inicial


        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda del problema cuadrado magico en " + nodo.getEstado());

        while (!p.esMeta(nodo.getEstado())){
            System.out.println((i++) + " - " + nodo.getEstado() + " no es meta");
            if(frontera!=null){//Si la frontera no está vacía.
                nodo = frontera.element();//??? //Obtenemos el último elemento de frontera
                frontera.remove(nodo);//Eliminamos el último elemento
                if(!p.esMeta(nodo.getEstado())){
                    explorados.add(nodo);
                    sucesores = sucesores(nodo,p);
                    for (Nodo suc: sucesores) { //Para cada sucesor
                        //Si no esta en la frontera o en explorados
                        if (!explorados.contains(suc) && !frontera.contains(suc)) {
                            System.out.println((i++) + " - " + suc.getEstado() + " no esta explorado ni se encuentra en la frontera");
                            //Añadimos ese sucesor a la frontera
                            frontera.add(suc);
                        } else {
                            System.out.println((i++) + " - Nodo " + nodo.getEstado() + " ya explorado");
                        }
                    }
                }else
                    System.out.println((i++) + " - FIN - " + nodo.getEstado());
            }else //Por el contrario si está vacía
                throw new Exception("Frontera vacía");

        }
        System.out.println((i++) + " - FIN - " + nodo.getEstado());
        return reconstruye_sol(nodo);

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
