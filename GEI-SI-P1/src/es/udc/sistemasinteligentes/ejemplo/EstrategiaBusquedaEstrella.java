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
        long endTime;
        long startTime = System.nanoTime();
        Nodo nodo = new Nodo(null, p.getEstadoInicial(), null,0,(int)(0+h.evalua(p.getEstadoInicial())));
        ArrayList<Nodo> sucesores;
        int coste;

        frontera.add(nodo);//Inicializar frontera usando el estado inicial


        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda del problema cuadrado magico en " + nodo.getEstado());

        while (!p.esMeta(nodo.getEstado())){
            System.out.println((i++) + " - El nodo con estado: " + nodo.getEstado() + " y coste: "+ nodo.getCoste() +" no es meta");
            if(frontera!=null){//Si la frontera no está vacía.
                //PR(coste camino + heurística)
                int aux=frontera.get(0).getF();
                nodo = frontera.get(0);
                for (Nodo value : frontera) {
                    if (aux > (value.getF())) {
                        aux = value.getF();
                        nodo = value;
                    }
                }
                frontera.remove(nodo);//Eliminamos el último elemento
                if(!p.esMeta(nodo.getEstado())) {
                    explorados.add(nodo);
                    System.out.println((i++)+ " - Añadimos  el nodo a explorados, con estado: " + nodo.getEstado() +" , coste: " + nodo.getCoste() + " , heuristica: " + h.evalua(nodo.getEstado()) + " , f(n): " +nodo.getF() +"   -");
                    sucesores = sucesores(nodo, p);
                    for (Nodo suc : sucesores) { //Para cada sucesor
                        if(nodo.getPadre()==null)
                            coste=0;
                        else
                            coste=nodo.getPadre().getCoste();

                        suc.setCoste(coste + 1);//falla
                        suc.setF((int) (suc.getF() + h.evalua(nodo.getEstado())));
                        if (!explorados.contains(suc.getEstado())) {
                            if (!frontera.contains(suc.getEstado())) {
                                frontera.add(suc);
                                System.out.println((i++) + " - Añadimos el nodo a frontera, con estado: " + nodo.getEstado() + " , coste: " + nodo.getCoste() + " , heuristica: " + h.evalua(nodo.getEstado()) + " , f(n): " + nodo.getF() + "  -");

                            } else {
                                if ((suc.getF()) < ((frontera.get(0)).getF())) {
                                    frontera.remove(frontera.get(0));
                                    frontera.add(suc);
                                    System.out.println((i++)+ " - Añadimos el nodo con estado: " + nodo.getEstado() +" , coste: " + nodo.getCoste() + " , heuristica: " + h.evalua(nodo.getEstado()) + " , f(n): " +nodo.getF() + "  -");
                                }
                            }
                        }
                    }
                }

            }else //Por el contrario si está vacía
                throw new Exception("Frontera vacía");

        }
        endTime = System.nanoTime();
        System.out.println((i++)+ " - Tiempo de ejecucion: " + (endTime-startTime)/1e6 + " ms");
        System.out.println((i++)+ " - Coste del camino: " + nodo.getCoste());
        System.out.println((i++) + " - FIN - " + nodo.getEstado());


        return nodo.getEstado();

    }

    public ArrayList<Nodo> sucesores(Nodo nodo,ProblemaBusqueda problemaBusqueda){
        ArrayList<Nodo> sucesores= new ArrayList<>();
        Accion[] accionesDisponibles = problemaBusqueda.acciones(nodo.getEstado());

        for (Accion acc: accionesDisponibles){
            Nodo n = new Nodo(nodo, problemaBusqueda.result(nodo.getEstado(),acc), acc,0,0);
            sucesores.add(n);
        }
        return sucesores;
    }

}
