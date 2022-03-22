package es.udc.sistemasinteligentes.gA_41.ejemplo;

import es.udc.sistemasinteligentes.gA_41.Accion;
import es.udc.sistemasinteligentes.gA_41.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.gA_41.ProblemaBusqueda;

import java.util.ArrayList;

public class EstrategiaBusquedaProfundidad implements EstrategiaBusqueda {

    public EstrategiaBusquedaProfundidad (){
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Nodo> explorados= new ArrayList<>();
        ArrayList<Nodo> frontera= new ArrayList<>();
        Nodo nodo = new Nodo(null, p.getEstadoInicial(), null);
        ArrayList<Nodo> sucesores;
        int numExplorados=0,numNodosCreados=0;

        frontera.add(nodo);//Inicializar frontera usando el estado inicial


        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda del problema cuadrado magico en " + nodo.getEstado());

        while (!p.esMeta(nodo.getEstado())){
            System.out.println((i++) + " - " + nodo.getEstado() + " no es meta");
            if(frontera!=null){//Si la frontera no está vacía.
                //LIFO -> primero en entrar, último en salir
                nodo = frontera.get(frontera.size()-1);
                frontera.remove(nodo);//Lo eliminamos de la frontera.
                if(!p.esMeta(nodo.getEstado())){
                    explorados.add(nodo);
                    numExplorados++;
                    sucesores = sucesores(nodo,p);
                       for (Nodo suc: sucesores) { //Para cada sucesor
                           numNodosCreados++;
                        //Si no esta en la frontera o en explorados
                        if ((!explorados.contains(suc)) && (!frontera.contains(suc))) {
                            System.out.println((i++) + " - " + suc.getEstado() + " no esta explorado ni se encuentra en la frontera");
                            //Añadimos ese sucesor a la frontera
                            frontera.add(suc);
                        } else {
                            System.out.println((i++) + " - Nodo " + nodo.getEstado() + " ya explorado");
                        }
                    }
                }else {
                    System.out.println((i++)+ " - Num de nodos expandidos: " + numExplorados);
                    System.out.println((i++) +  " - Num de nodos creados: " +numNodosCreados);
                    System.out.println((i++) + " - FIN - " + nodo.getEstado());
                }
            }else //Por el contrario si está vacía
                throw new Exception("Frontera vacía");

        }
        System.out.println((i++)+ " - Num de nodos expandidos: " + numExplorados);
        System.out.println((i++) +  " - Num de nodos creados: " +numNodosCreados);
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
