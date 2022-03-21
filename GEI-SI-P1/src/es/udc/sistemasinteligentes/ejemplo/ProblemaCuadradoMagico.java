package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {


    public static class Estadocuadrado extends Estado{

        int fila, columna, estado;

        public enum Check{SEGUIR, FINALIZAR};
        private Check check;
        public Estadocuadrado(int fila, int columna, int estado, ProblemaCuadradoMagico.Estadocuadrado.Check check){
            this.fila = fila;
            this.columna = columna;
            this.estado = estado;
            this.check = check;

        }

        public int getFila() {
            return fila;
        }

        public void setFila(int fila) {
            this.fila = fila;
        }

        public int getColumna() {
            return columna;
        }

        public void setColumna(int columna) {
            this.columna = columna;
        }

        public int getEstado() {
            return estado;
        }

        public void setEstado(int estado) {
            this.estado = estado;
        }

        @Override
        public String toString() {
            return "(" + posicionfila + "," + posicioncolumna + ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Estadocuadrado that = (Estadocuadrado) o;
            return fila == that.fila && columna == that.columna && estado == that.estado && check == that.check;
        }

        @Override
        public int hashCode() {
            return Objects.hash(fila, columna, estado, check);
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
            ProblemaCuadradoMagico.Estadocuadrado esCu = (ProblemaCuadradoMagico.Estadocuadrado) es;
            ProblemaCuadradoMagico.Estadocuadrado.Posicionfila nuevaPosicionFila = esCu.posicionfila;
            ProblemaCuadradoMagico.Estadocuadrado.Posicioncolumna nuevaPosicionColumna = esCu.posicioncolumna;
           esCu.estado = Estadocuadrado.Estado.SEGUIR;
            int n;

            if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.IZQ) && (esCu.posicionfila == Estadocuadrado.Posicionfila.CERO)) {//En caso de que la posicion sea 0 no nos movemos. (fila)
                //No nos movemos
            } else if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.IZQ)) {//Si nos movemos a la izq, restamos a la posición inicial -1 (fila)
                nuevaPosicionFila = ProblemaCuadradoMagico.Estadocuadrado.Posicionfila.values()[esCu.posicionfila.ordinal() - 1];
            } else if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.DER) && (esCu.posicionfila.ordinal() == Estadocuadrado.Posicionfila.values().length)) {//En caso de que corresponda con la última posición (fila)
                //No nos movemos
            } else if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.DER)) {//Si nos movemos a la der, sumamos a la posición inicial +1 (fila)
                nuevaPosicionFila = ProblemaCuadradoMagico.Estadocuadrado.Posicionfila.values()[esCu.posicionfila.ordinal() + 1];
            } else if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.Abajo) && (esCu.posicioncolumna.ordinal() == Estadocuadrado.Posicioncolumna.values().length)){//En caso de que corresponda con la última posición  (columna)
                //No nos movemos
            }else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.Abajo)) {//Si nos movemos, sumamos -1 (columna)
                nuevaPosicionColumna = ProblemaCuadradoMagico.Estadocuadrado.Posicioncolumna.values()[esCu.posicioncolumna.ordinal() + 1];//No nos movemos
            }else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.Arriba)&&(esCu.posicioncolumna == Estadocuadrado.Posicioncolumna.CERO)) {//En caso de que corresponda con la última posición  (columna)
                //No nos movemos
            }else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.Arriba)) {//Si nos movemos, sumamos +1 (columna)
                nuevaPosicionColumna = ProblemaCuadradoMagico.Estadocuadrado.Posicioncolumna.values()[esCu.posicioncolumna.ordinal() - 1];
            }

            n = numAleatorio(esCu,nuevaPosicionFila,nuevaPosicionColumna);
            if(n!=-1)
                esCu.estadoInicialEj1[nuevaPosicionFila.ordinal()][nuevaPosicionColumna.ordinal()] = n;
            else
                esCu.estado = Estadocuadrado.Estado.FIN;

            return new ProblemaCuadradoMagico.Estadocuadrado(nuevaPosicionFila,nuevaPosicionColumna,esCu.estadoInicialEj1,esCu.numCasilla, esCu.casilla,esCu.estado);

        }

        public int numAleatorio( ProblemaCuadradoMagico.Estadocuadrado esCu, ProblemaCuadradoMagico.Estadocuadrado.Posicionfila nuevaPosicionFila,ProblemaCuadradoMagico.Estadocuadrado.Posicioncolumna nuevaPosicionColumna){
            int sumaTotal = ((Estadocuadrado.Posicioncolumna.values().length)*((int)(Math.pow(Estadocuadrado.Posicioncolumna.values().length,2))+1))/2;
            int sumafila=0;
            int sumacolumna=0;
            int casillasVaciasFila = -1;
            int casillasVaciasColumna = -1;
            int numAleatorioCasilla = 0;


            for(int i=0;i<Estadocuadrado.Posicionfila.values().length;i++) {//Recorremos matriz ->solo esa (fila)
                sumafila += esCu.estadoInicialEj1[i][nuevaPosicionColumna.ordinal()];

                //Comprobamos cuantas casillas están vacías
                if(esCu.estadoInicialEj1[i][nuevaPosicionColumna.ordinal()]==0)
                    casillasVaciasFila++;
                else //En caso de que no estén vacías
                    esCu.casilla[esCu.estadoInicialEj1[i][nuevaPosicionColumna.ordinal()]] = 1;//Si utilizamos un número, ponemos ese estado como ocupado
            }

            for (int j = 0;j<Estadocuadrado.Posicioncolumna.values().length; j++) {//Recorremos matriz ->solo esa (columna)
                sumacolumna += esCu.estadoInicialEj1[nuevaPosicionFila.ordinal()][j];

                //Comprobamos cuantas casillas están vacías
                if (esCu.estadoInicialEj1[nuevaPosicionFila.ordinal()][j]==0)
                    casillasVaciasColumna++;
                else//En caso de que no estén vacías
                    esCu.casilla[esCu.estadoInicialEj1[nuevaPosicionFila.ordinal()][j]] = 1;//Si utilizamos un número, ponemos ese estado como ocupado
            }

            //Averiguar que elemento introducimos en la casilla
            if(!((casillasVaciasColumna==-1)&&(casillasVaciasFila==-1))) {

                if (esCu.EstadoInicial[nuevaPosicionFila.ordinal()][nuevaPosicionColumna.ordinal()] == 0) {//Si la casilla está vacía

                    if ((casillasVaciasColumna == 0) && (casillasVaciasFila == 0))//En caso de que solamente (fila&columna) haya una casilla vacía, la que tenemos que llenar
                        esCu.estadoInicialEj1[nuevaPosicionFila.ordinal()][nuevaPosicionColumna.ordinal()] = esCu.numCasilla[(sumaTotal) - sumafila];//Es la suma de todos los nums de la fila/columna (excepto la casilla que tenemos que llenar) junto la resta con la sumaTotal.
                    else if ((casillasVaciasColumna == 0) && (casillasVaciasFila > 1)) {//En caso de que en la fila haya más de una casilla sin rellenar, elegimos cualquier num aleatorio (sin que sume igual a sumaTotal)

                        while ((esCu.casilla[numAleatorioCasilla] != 0) && (sumafila + numAleatorioCasilla < sumaTotal))//Comprobamos si ese número está siendo utilizado y la suma de las casillas que no están vacías más el num aleatorio es mayor o igual a la sumaTotal
                            numAleatorioCasilla = (int) (Math.random() * (esCu.numCasilla.length + 1));//Creamos num aleatorio entre 1 y la longitud max. (1-9)

                        esCu.estadoInicialEj1[nuevaPosicionFila.ordinal()][nuevaPosicionColumna.ordinal()] = esCu.numCasilla[numAleatorioCasilla];//La casilla a llenar con el num aleatorio

                    } else if ((casillasVaciasColumna > 1) && (casillasVaciasFila == 0)) {
                        while ((esCu.casilla[numAleatorioCasilla] != 0) && (sumacolumna + numAleatorioCasilla < sumaTotal))//Comprobamos si ese número está siendo utilizado y la suma de las casillas que no están vacías más el num aleatorio es mayor o igual a la sumaTotal
                            numAleatorioCasilla = (int) (Math.random() * (esCu.numCasilla.length + 1));//Creamos num aleatorio entre 1 y la longitud max. (1-9)

                        esCu.estadoInicialEj1[nuevaPosicionFila.ordinal()][nuevaPosicionColumna.ordinal()] = esCu.numCasilla[numAleatorioCasilla];//La casilla a llenar con el num aleatorio

                    } else {
                        while ((esCu.casilla[numAleatorioCasilla] != 0) && (sumafila + numAleatorioCasilla < sumaTotal) && (sumacolumna + numAleatorioCasilla < sumaTotal))//Comprobamos si ese número está siendo utilizado y la suma de las casillas que no están vacías más el num aleatorio es mayor o igual a la sumaTotal
                            numAleatorioCasilla = (int) (Math.random() * (esCu.numCasilla.length + 1));//Creamos num aleatorio entre 1 y la longitud max. (1-9)

                        esCu.estadoInicialEj1[nuevaPosicionFila.ordinal()][nuevaPosicionColumna.ordinal()] = esCu.numCasilla[numAleatorioCasilla];//La casilla a llenar con el num aleatorio

                    }
                }
                return esCu.estadoInicialEj1[esCu.posicionfila.ordinal()][esCu.posicioncolumna.ordinal()];
            }
            return -1;
        }


    }


    private final Accion[] listaAcciones;

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
        return ((Estadocuadrado) es).estado == Estadocuadrado.Estado.FIN;
    }


    @Override
    public Accion[] acciones(Estado es) {
        return listaAcciones;
    }
}
