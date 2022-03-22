package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;

public class EstrategiaBusquedaEstrella implements EstrategiaBusquedaInformada {

    public EstrategiaBusquedaEstrella(){
    }

    @Override
    public Estado soluciona(ProblemaBusqueda p, Heuristica h) throws Exception {
        ArrayList<Nodo> explorados= new ArrayList<>();
        ArrayList<Nodo> frontera= new ArrayList<>();
        Nodo nodo = new Nodo(null, p.getEstadoInicial(), null);
        ArrayList<Nodo> sucesores = new ArrayList<>();
        int numExplorados=0,numNodosCreados=0;

        frontera.add(nodo);//Inicializar frontera usando el estado inicial


        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda del problema cuadrado magico en " + nodo.getEstado());

        while (!p.esMeta(nodo.getEstado())){
            System.out.println((i++) + " - " + nodo.getEstado() + " no es meta");
            if(frontera!=null){//Si la frontera no está vacía.
                //PR(coste camino + heurística)
                int aux=frontera.get(0).getF();
                for (Nodo value : frontera) {
                    if (aux > (value.getF())) {
                        aux = value.getF();
                        nodo = value;
                    }
                }
                frontera.remove(nodo);//Eliminamos el último elemento
                if(!p.esMeta(nodo.getEstado())) {
                    explorados.add(nodo);
                    numExplorados++;
                    sucesores = sucesores(nodo, p);
                    for (Nodo suc : sucesores) { //Para cada sucesor
                        numNodosCreados++;
                        suc.setCoste((nodo.getPadre()).getCoste() + 1);
                        suc.setF((int) (suc.getF() + h.evalua(nodo.getEstado())));
                        if (!explorados.contains(suc.getEstado())) {
                            if (!frontera.contains(suc.getEstado()))
                                frontera.add(suc);
                            else {
                                if ((suc.getF()) < ((frontera.get(0)).getF())) {
                                    frontera.remove(frontera.get(0));
                                    frontera.add(suc);
                                }
                            }
                        }
                    }
                }

            }else //Por el contrario si está vacía
                throw new Exception("Frontera vacía");

        }
        System.out.println((i++)+ " - Num de nodos expandidos: " + numExplorados);
        System.out.println((i++) +  " - Num de nodos creados: " +numNodosCreados);
        System.out.println((i++) + " - FIN - " + nodo.getEstado());


        return nodo.getEstado();

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

}
