package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.Heuristica;

public class HeuristicaCuadradoMagico extends Heuristica {


    public Estado estado;
    public HeuristicaCuadradoMagico (Estado estado){
        this.estado = estado;
    }

    @Override
    public float evalua(Estado e) {
        ProblemaCuadradoMagico.Estadocuadrado esCu = (ProblemaCuadradoMagico.Estadocuadrado)e;
        float heuristica=0;

        for(int i=0;i<esCu.dimension;i++){
            for(int j=0;j<esCu.dimension;j++){
                if(esCu.matriz[i][j]==0) {
                   heuristica++;
                }
            }
        }

        return heuristica;
    }
}
