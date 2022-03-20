package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {

    public static class Estadocuadrado extends Estado{
        public enum Posicionfila {CERO,UNO,DOS};
        public enum Posicioncolumna {CERO,UNO,DOS};

        int [] numCasilla = {1,2,3,4,5,6,7,8,9};
        int [] casilla = {0,0,0,0,0,0,0,0,0,0};
        int [][] estadoInicialEj1 = {
                {4,9,2},
                {3,5,0},
                {0,1,0},
        };

        private ProblemaCuadradoMagico.Estadocuadrado.Posicionfila posicionfila;
        private ProblemaCuadradoMagico.Estadocuadrado.Posicioncolumna posicioncolumna;

        public Estadocuadrado(ProblemaCuadradoMagico.Estadocuadrado.Posicionfila posicionfila, ProblemaCuadradoMagico.Estadocuadrado.Posicioncolumna posicioncolumna, int[][] estadoInicialEj, int[] numCasilla, int[] casilla) {
            this.posicionfila = posicionfila;
            this.posicioncolumna = posicioncolumna;
            this.estadoInicialEj1 = estadoInicialEj;
            this.numCasilla = numCasilla;
            this.casilla = casilla;
        }


        @Override
        public String toString() {
            return "(" + posicionfila + "," + posicioncolumna + ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Estadocuadrado)) return false;
            Estadocuadrado that = (Estadocuadrado) o;
            return posicionfila == that.posicionfila && posicioncolumna == that.posicioncolumna && Arrays.equals(estadoInicialEj1, that.estadoInicialEj1);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(posicionfila, posicioncolumna);
            result = 31 * result + Arrays.hashCode(estadoInicialEj1);
            return result;
        }
    }

    public static class AccionCuadrado extends Accion {
        public enum Accion {IZQ, DER, Arriba, Abajo};

        private ProblemaCuadradoMagico.AccionCuadrado.Accion accion;

        public AccionCuadrado(ProblemaCuadradoMagico.AccionCuadrado.Accion accion) {
            this.accion = accion;
        }

        @Override
        public String toString() {
            return accion.name();
        }

        @Override
        public boolean esAplicable(Estado es) {
            return true;
        }

        @Override
        public Estado aplicaA(Estado es) {
            ProblemaCuadradoMagico.Estadocuadrado esCu = (ProblemaCuadradoMagico.Estadocuadrado)es;
            ProblemaCuadradoMagico.Estadocuadrado.Posicionfila nuevaPosicionFila=esCu.posicionfila;
            ProblemaCuadradoMagico.Estadocuadrado.Posicioncolumna nuevaPosicionColumna=esCu.posicioncolumna;
            int sumaTotal = ((Estadocuadrado.Posicioncolumna.values().length)*((int)(Math.pow(Estadocuadrado.Posicioncolumna.values().length,2))+1))/2;
            int sumafila=0;
            int sumacolumna=0;
            int casillasVaciasFila = -1;
            int casillasVaciasColumna = -1;
            int numAleatorioCasilla = -1;

            if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.IZQ)&&(esCu.posicionfila== Estadocuadrado.Posicionfila.CERO)) {//En caso de que la posicion sea 0 no nos movemos. (fila)
                nuevaPosicionFila = esCu.posicionfila;
                if(esCu.estadoInicialEj1[nuevaPosicionFila.ordinal()][esCu.posicioncolumna.ordinal()]==0){//Si la casilla está vacía

                    for(int i=0;i<Estadocuadrado.Posicionfila.values().length;i++) {//Recorremos matriz ->solo esa (fila)
                         sumafila += esCu.estadoInicialEj1[i][esCu.posicioncolumna.ordinal()];

                        //Comprobamos cuantas casillas están vacías
                        if(esCu.estadoInicialEj1[i][esCu.posicioncolumna.ordinal()]==0)
                            casillasVaciasFila++;

                        esCu.casilla[esCu.estadoInicialEj1[i][esCu.posicioncolumna.ordinal()]] = 1;//Si utilizamos un número, ponemos ese estado como ocupado
                    }

                    for (int j = 0;j<Estadocuadrado.Posicioncolumna.values().length; j++) {//Recorremos matriz ->solo esa (columna)
                        sumacolumna += esCu.estadoInicialEj1[esCu.posicioncolumna.ordinal()][j];

                        //Comprobamos cuantas casillas están vacías
                        if (esCu.estadoInicialEj1[esCu.posicioncolumna.ordinal()][j]==0)
                            casillasVaciasColumna++;
                        esCu.casilla[esCu.estadoInicialEj1[esCu.posicionfila.ordinal()][j]] = 1;//Si utilizamos un número, ponemos ese estado como ocupado
                    }


                   if((casillasVaciasColumna==0)&&(casillasVaciasFila==0))//En caso de que solamente (fila&columna) haya una casilla vacía, la que tenemos que llenar
                        esCu.estadoInicialEj1[nuevaPosicionFila.ordinal()][esCu.posicioncolumna.ordinal()] = esCu.numCasilla[(sumaTotal)-sumafila];//Es la suma de todos los nums de la fila/columna (excepto la casilla que tenemos que llenar) junto la resta con la sumaTotal.
                   else if((casillasVaciasColumna==0)&&(casillasVaciasFila>1)) {//En caso de que en la fila haya más de una casilla sin rellenar, elegimos cualquier num aleatorio (sin que sume igual a sumaTotal)

                       while((esCu.casilla[numAleatorioCasilla] != 0)&&(sumafila+numAleatorioCasilla>=sumaTotal))//Comprobamos si ese número está siendo utilizado y la suma de las casillas que no están vacías más el num aleatorio es mayor o igual a la sumaTotal
                           numAleatorioCasilla = (int)(Math.random() * (esCu.numCasilla.length + 1));//Creamos num aleatorio entre 1 y la longitud max. (1-9)

                       esCu.estadoInicialEj1[nuevaPosicionFila.ordinal()][esCu.posicioncolumna.ordinal()] = esCu.numCasilla[numAleatorioCasilla];//La casilla a llenar con el num aleatorio
                   }else if((casillasVaciasColumna>1)&&(casillasVaciasFila==0)){
                       while((esCu.casilla[numAleatorioCasilla] != 0)&&(sumacolumna+numAleatorioCasilla>=sumaTotal))//Comprobamos si ese número está siendo utilizado y la suma de las casillas que no están vacías más el num aleatorio es mayor o igual a la sumaTotal
                           numAleatorioCasilla = (int)(Math.random() * (esCu.numCasilla.length + 1));//Creamos num aleatorio entre 1 y la longitud max. (1-9)

                       esCu.estadoInicialEj1[nuevaPosicionFila.ordinal()][esCu.posicioncolumna.ordinal()] = esCu.numCasilla[numAleatorioCasilla];//La casilla a llenar con el num aleatorio

                   }else{
                       while((esCu.casilla[numAleatorioCasilla] != 0)&&(sumafila+numAleatorioCasilla>=sumaTotal)&&(sumacolumna+numAleatorioCasilla>=sumaTotal))//Comprobamos si ese número está siendo utilizado y la suma de las casillas que no están vacías más el num aleatorio es mayor o igual a la sumaTotal
                           numAleatorioCasilla = (int)(Math.random() * (esCu.numCasilla.length + 1));//Creamos num aleatorio entre 1 y la longitud max. (1-9)

                       esCu.estadoInicialEj1[nuevaPosicionFila.ordinal()][esCu.posicioncolumna.ordinal()] = esCu.numCasilla[numAleatorioCasilla];//La casilla a llenar con el num aleatorio

                   }
                }
            }else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.IZQ))//Si nos movemos a la izq, restamos a la posición inicial -1 (fila)
                nuevaPosicionFila = ProblemaCuadradoMagico.Estadocuadrado.Posicionfila.values()[esCu.posicionfila.ordinal()-1];
            else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.DER)&&(esCu.posicionfila.ordinal() == Estadocuadrado.Posicionfila.values().length))//En caso de que corresponda con la última posición (fila)
                nuevaPosicionFila = esCu.posicionfila;//No nos movemos
            else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.DER))//Si nos movemos a la der, sumamos a la posición inicial +1 (fila)
                nuevaPosicionFila = ProblemaCuadradoMagico.Estadocuadrado.Posicionfila.values()[esCu.posicionfila.ordinal()+1];
            else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.Abajo)&&(esCu.posicioncolumna.ordinal() == Estadocuadrado.Posicioncolumna.values().length))//En caso de que corresponda con la última posición  (columna)
                nuevaPosicionColumna = esCu.posicioncolumna;//No nos movemos
            else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.Abajo))//Si nos movemos, sumamos -1 (columna)
                nuevaPosicionColumna = ProblemaCuadradoMagico.Estadocuadrado.Posicioncolumna.values()[esCu.posicioncolumna.ordinal()+1];//No nos movemos
            else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.Arriba)&&(esCu.posicioncolumna == Estadocuadrado.Posicioncolumna.CERO))//En caso de que corresponda con la última posición  (columna)
                nuevaPosicionColumna = esCu.posicioncolumna;//No nos movemos
            else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.Arriba))//Si nos movemos, sumamos +1 (columna)
                nuevaPosicionColumna = ProblemaCuadradoMagico.Estadocuadrado.Posicioncolumna.values()[esCu.posicioncolumna.ordinal()-1];

            return new ProblemaCuadradoMagico.Estadocuadrado(nuevaPosicionFila,nuevaPosicionColumna,nuevoEstado);

        }
    }

    private Accion[] listaAcciones;

    public ProblemaCuadradoMagico(Estado estadoInicial) {
        super(estadoInicial);
        //Inicializamos la lista de acciones
        listaAcciones = new Accion[]{new AccionCuadrado(AccionCuadrado.Accion.Abajo),
                new ProblemaCuadradoMagico.AccionCuadrado(AccionCuadrado.Accion.Arriba),
                new ProblemaCuadradoMagico.AccionCuadrado(AccionCuadrado.Accion.DER),
                new ProblemaCuadradoMagico.AccionCuadrado(AccionCuadrado.Accion.IZQ),};
    }

    @Override
    public boolean esMeta(Estado es) {
        return false; //Cambiar
    }


    @Override
    public Accion[] acciones(Estado es) {
        return listaAcciones;
    }
}