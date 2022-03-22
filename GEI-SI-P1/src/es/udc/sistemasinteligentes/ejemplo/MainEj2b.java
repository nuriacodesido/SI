package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.Heuristica;
import es.udc.sistemasinteligentes.ejemplo.EstrategiaBusquedaEstrella;

public class MainEj2b {

    public static void main(String[] args) throws Exception {
        int [][] matriz = new int[][]{{4, 9, 2}, {3, 5, 0}, {0, 1, 0}};
        //int [][] matriz = new int[][]{{2, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        ProblemaCuadradoMagico.Estadocuadrado estadoInicial = new ProblemaCuadradoMagico.Estadocuadrado(matriz,3,ProblemaCuadradoMagico.Estadocuadrado.Check.SEGUIR,0,0);
        ProblemaCuadradoMagico cuadradoMagico = new ProblemaCuadradoMagico(estadoInicial);
        HeuristicaCuadradoMagico heuristicaCuadradoMagico = new HeuristicaCuadradoMagico(estadoInicial);

        System.out.println(" - ESTRATEGIA BUSQUEDA ESTRELLA - ");
        EstrategiaBusquedaEstrella buscador = new EstrategiaBusquedaEstrella();
        System.out.println(buscador.soluciona(cuadradoMagico,heuristicaCuadradoMagico));
    }
}
