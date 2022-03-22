package es.udc.sistemasinteligentes.gA_41.ejemplo;

import es.udc.sistemasinteligentes.gA_41.EstrategiaBusqueda;

public class MainEj2a {

    public static void main(String[] args) throws Exception {
        int [][] matriz = new int[][]{{4, 9, 2}, {3, 5, 0}, {0, 1, 0}};
        //int [][] matriz = new int[][]{{2, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        ProblemaCuadradoMagico.Estadocuadrado estadoInicial = new ProblemaCuadradoMagico.Estadocuadrado(matriz,3,ProblemaCuadradoMagico.Estadocuadrado.Check.SEGUIR,0,0);
        ProblemaCuadradoMagico cuadradoMagico = new ProblemaCuadradoMagico(estadoInicial);

        System.out.println(" - ESTRATEGIA BUSQUEDA AMPLITUD - ");
        EstrategiaBusqueda buscador = new EstrategiaBusquedaAmplitud();
        System.out.println(buscador.soluciona(cuadradoMagico));

        System.out.println(" - ESTRATEGIA BUSQUEDA PROFUNDIDAD - ");
        EstrategiaBusqueda buscador2 = new EstrategiaBusquedaProfundidad();
        System.out.println(buscador2.soluciona(cuadradoMagico));
    }

}
