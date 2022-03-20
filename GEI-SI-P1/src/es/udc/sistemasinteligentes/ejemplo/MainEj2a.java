package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

public class MainEj2a {

    public static void main(String[] args) throws Exception {
        ProblemaCuadradoMagico.EstadoAspiradora estadoInicial = new ProblemaCuadradoMagico.EstadoAspiradora(ProblemaCuadradoMagico.EstadoAspiradora.PosicionRobot.IZQ,
                ProblemaCuadradoMagico.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaCuadradoMagico aspiradora = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusqueda buscador = new EstrategiaBusquedaAmplitud();
        System.out.println(buscador.soluciona(aspiradora));

        EstrategiaBusqueda buscador2 = new EstrategiaBusquedaProfundidad();
        System.out.println(buscador2.soluciona(aspiradora));
    }
}
