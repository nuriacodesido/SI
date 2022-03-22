package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

public class MainEj2a {

    public static void main(String[] args) throws Exception {
        //int [][] matriz = new int[][]{{4, 9, 2}, {3, 5, 0}, {0, 1, 0}};
        int [][] matriz = new int[][]{{2, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        ProblemaCuadradoMagico.Estadocuadrado estadoInicial = new ProblemaCuadradoMagico.Estadocuadrado(matriz,3,ProblemaCuadradoMagico.Estadocuadrado.Check.SEGUIR,0,0);
        ProblemaCuadradoMagico cuadradoMagico = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusqueda buscador = new EstrategiaBusquedaAmplitud();
        System.out.println(buscador.soluciona(cuadradoMagico));

        /*EstrategiaBusqueda buscador2 = new EstrategiaBusquedaProfundidad();
        System.out.println(buscador2.soluciona(cuadradoMagico));*/
    }

}
