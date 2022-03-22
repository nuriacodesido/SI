package es.udc.sistemasinteligentes.gA_41.ejemplo;

import es.udc.sistemasinteligentes.gA_41.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.gA_41.ProblemaBusqueda;

public class MainEj1 {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                                                                                                    ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        System.out.println(" - ESTRATEGIA 4 - ");
        EstrategiaBusqueda buscador = new Estrategia4();
        System.out.println(buscador.soluciona(aspiradora));

        System.out.println(" - ESTRATEGIA BUSQUEDA GRAFO - ");
        EstrategiaBusqueda buscador2 = new EstrategiaBusquedaGrafo();
        System.out.println(buscador2.soluciona(aspiradora));
    }
}
