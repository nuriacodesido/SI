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
        //Queue<Nodo> frontera =  new LinkedList();
        ArrayList<Nodo> frontera= new ArrayList<>();
        Nodo nodo = new Nodo(null, p.getEstadoInicial(), null);
        ArrayList<Nodo> sucesores = new ArrayList<>();

        frontera.add(nodo);//Inicializar frontera usando el estado inicial


        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda del problema cuadrado magico en " + nodo.getEstado());

        while (!p.esMeta(nodo.getEstado())){
            System.out.println((i++) + " - " + nodo.getEstado() + " no es meta");
            if(frontera!=null){//Si la frontera no está vacía.
                nodo = frontera.get(frontera.size()-1);//??? //Obtenemos el último elemento de frontera
                frontera.remove(nodo);//Eliminamos el último elemento
                if(!p.esMeta(nodo.getEstado())){
                    explorados.add(nodo);
                    sucesores = sucesores(nodo,p);
                    for(int j=0;j< sucesores.size();j++){//Recorremos el array sucesores
                        if(!(frontera.contains(sucesores.get(j))&&(explorados.contains(sucesores.get(j))))) {//Realizamos la comprobacion de si no se encuentra en frontera ni en explorados
                            frontera.add(sucesores.get(j));//Insertamos el elemento sucesor
                            System.out.println((i++) + " - Añadimos a la frontera el nodo sucesor con estado- " + nodo.getEstado());
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
