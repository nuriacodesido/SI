package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

public class MainEj2a {

    public static void main(String[] args) throws Exception {
        ProblemaCuadradoMagico.Estadocuadrado estadoInicial = new ProblemaCuadradoMagico.Estadocuadrado(ProblemaCuadradoMagico.Estadocuadrado.Posicionfila.CERO,
                ProblemaCuadradoMagico.Estadocuadrado.Posicioncolumna.CERO, ProblemaCuadradoMagico.Estadocuadrado.Posicion.CERO);
        ProblemaCuadradoMagico cuadradoMagico = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusqueda buscador = new EstrategiaBusquedaAmplitud();
        System.out.println(buscador.soluciona(cuadradoMagico));

        EstrategiaBusqueda buscador2 = new EstrategiaBusquedaProfundidad();
        System.out.println(buscador2.soluciona(cuadradoMagico));
    }
}
