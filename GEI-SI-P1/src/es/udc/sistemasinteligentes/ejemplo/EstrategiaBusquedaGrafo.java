package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;

/*public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {


    public EstrategiaBusquedaGrafo() {
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Nodo> explorados= new ArrayList<>();
        ArrayList<Nodo> frontera = new ArrayList<>();
        explorados = null;
        Nodo nodo = new Nodo(null, p.getEstadoInicial(), null);
        frontera.add(nodo);

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + nodo.getEstado());

        do {
            if(frontera!=null) {
                frontera.remove(nodo);
                if(!p.esMeta(nodo.getEstado())){
                    explorados.add(nodo);
                    Accion[] accionesDisponibles = p.acciones(nodo.getEstado());
                    for (Accion acc : accionesDisponibles) {
                        Estado sc = p.result(nodo.getEstado(), acc);
                        nodo = new Nodo(nodo,sc,acc);
                        if((!explorados.contains(nodo))&&!(frontera.contains(nodo)))
                            frontera.add(sucesores(nodo));
                    }

                }else
                    return explorados;

            }else
                throw new Exception("frontera vacía");

        };
        return explorados;
    }

    public Nodo sucesores(Nodo nodo){
        Accion[] accionesDisponibles = nodo.getAccion();

        for (Accion acc : accionesDisponibles) {
            Nodo nodo = new Nodo(res, nodo, acc);
            sucesores.add(nodo);

        }
        return sucesores;
    }


}*/
