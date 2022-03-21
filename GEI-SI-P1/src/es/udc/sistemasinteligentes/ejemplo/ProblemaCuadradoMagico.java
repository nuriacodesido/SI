package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {


    public static class Estadocuadrado extends Estado{

        int dimension,fila,columna;
        int [][] matriz;

        public enum Check{SEGUIR, FINALIZAR};//?

        private Check check;
        public Estadocuadrado(int [][] matriz, int dimension,ProblemaCuadradoMagico.Estadocuadrado.Check check,int fila,int columna){
            this.matriz = matriz;
            this.dimension = dimension;
            this.check = check;
            this.fila= fila;
            this.columna=columna;
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

        public int getDimension() {
            return dimension;
        }

        public void setDimension(int dimension) {
            this.dimension = dimension;
        }

        public int[][] getMatriz() {
            return matriz;
        }

        public void setMatriz(int[][] matriz) {
            this.matriz = matriz;
        }

        public Check getCheck() {
            return check;
        }

        public void setCheck(Check check) {
            this.check = check;
        }

        @Override
        public String toString() {
            return "(" + fila + "," + columna + ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Estadocuadrado)) return false;
            Estadocuadrado that = (Estadocuadrado) o;
            return getDimension() == that.getDimension() && getFila() == that.getFila() && getColumna() == that.getColumna() && Arrays.equals(getMatriz(), that.getMatriz()) && getCheck() == that.getCheck();
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(getDimension(), getFila(), getColumna(), getCheck());
            result = 31 * result + Arrays.hashCode(getMatriz());
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
            ProblemaCuadradoMagico.Estadocuadrado esCu = (ProblemaCuadradoMagico.Estadocuadrado) es;
            int [][] matriz = esCu.matriz;
            int posicionfila = esCu.fila;
            int posicioncolumna = esCu.columna;
            int n;

            if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.IZQ) && (posicioncolumna == 0)) {//En caso de que la posicion sea 0 no nos movemos. (fila)
                //No nos movemos
            } else if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.IZQ)) {//Si nos movemos a la izq, restamos a la posición inicial -1 (fila)
                posicioncolumna--;
            } else if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.DER) && (posicioncolumna== matriz.length)) {//En caso de que corresponda con la última posición (fila)
                //No nos movemos
            } else if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.DER)) {//Si nos movemos a la der, sumamos a la posición inicial +1 (fila)
                posicioncolumna++;
            } else if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.Abajo) && (posicionfila== matriz.length)){//En caso de que corresponda con la última posición  (columna)
                //No nos movemos
            }else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.Abajo)) {//Si nos movemos, sumamos +1 (columna)
                posicionfila++;
            }else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.Arriba)&&(posicionfila == 0)) {//En caso de que corresponda con la última posición  (columna)
                //No nos movemos
            }else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.Arriba)) {//Si nos movemos, sumamos +1 (columna)
                posicionfila--;
            }

            if(esCu.matriz[posicionfila][posicioncolumna]==0) {//Si la casilla está vacía
                n = numAleatorio(esCu, posicionfila, posicioncolumna);
                if (n != -1)
                    matriz[posicionfila][posicioncolumna] = n;
                else
                    esCu.check = Estadocuadrado.Check.FINALIZAR;
            }

            return new ProblemaCuadradoMagico.Estadocuadrado(matriz,matriz.length,esCu.check,posicionfila,posicioncolumna);

        }

        public int numAleatorio( ProblemaCuadradoMagico.Estadocuadrado esCu, int posicionfila,int posicioncolumna){
            int sumaTotal = ((esCu.matriz.length)*((int)(Math.pow(esCu.matriz.length,2))+1))/2;
            int sumafila=0;
            int sumacolumna=0;
            int casillasVaciasFila = -1;
            int casillasVaciasColumna = -1;
            int numAleatorioCasilla = 0;
            int [] arrayNum = new int[esCu.dimension* esCu.dimension];
            int [] arrayDisp = new int[esCu.dimension* esCu.dimension];

            for(int i=0;i<esCu.matriz.length;i++) {//Recorremos matriz ->solo esa (fila)
                sumafila += esCu.matriz[posicionfila][i];

                //Comprobamos cuantas casillas están vacías
                if(esCu.matriz[posicionfila][i]==0)
                    casillasVaciasFila++;
            }

            for (int j = 0;j<esCu.matriz.length; j++) {//Recorremos matriz ->solo esa (columna)
                sumacolumna += esCu.matriz[j][posicioncolumna];

                //Comprobamos cuantas casillas están vacías
                if (esCu.matriz[j][posicioncolumna]==0)
                    casillasVaciasColumna++;
            }

            //Creamos los arrays
            //1. Contiene todos los numeros que debemos utilizar para rellenar todas las casillas. (1-...)
            //2.Array que nos permite comprobar, que numeros están siendo utilizados y cuales no.
            for(int i=1;i<=(esCu.dimension* esCu.dimension);i++){
                arrayNum[i-1] = i;
                arrayDisp[i-1]=0;
            }

            //rellenamos el arrayDisp (1-posición ocupada) (0-posición vacía)
            for(int i=0;i<esCu.dimension;i++){
                for(int j=0;j<esCu.dimension;j++){
                    if(esCu.matriz[i][j]!=0)
                        arrayDisp[(esCu.matriz[i][j])-1]=1;
                }
            }

            //Averiguar que elemento introducimos en la casilla
            if(!((casillasVaciasColumna==-1)&&(casillasVaciasFila==-1))) {

                if (esCu.matriz[posicionfila][posicioncolumna] == 0) {//Caso de que la casilla esté vacía.

                    if ((casillasVaciasColumna == 0) && (casillasVaciasFila == 0)) {//En caso de que solamente (fila&columna) haya una casilla vacía, la que tenemos que llenar
                        //No hace falta comprobar en ArrayDisp, ya que al tener todas las casillas ocupadas excepto la que tenemos que rellenar,( solamente hay una solución)
                        esCu.matriz[posicionfila][posicioncolumna] = arrayNum[((sumaTotal) - sumafila)-1];//Es la suma de todos los nums de la fila/columna (excepto la casilla que tenemos que llenar) junto la resta con la sumaTotal.

                    } else if ((casillasVaciasColumna == 0) && (casillasVaciasFila >= 1)) {//En caso de que en la fila haya más de una casilla sin rellenar, elegimos cualquier num aleatorio (sin que sume igual a sumaTotal, debido a que aparte de la casilla que debemos rellenar, aún queda otra casilla vacía)
                        numAleatorioCasilla = (int) (Math.random() * ((esCu.dimension*esCu.dimension) + 1));//Creamos num aleatorio entre 1 y la longitud max. (1-9)

                        while ((numAleatorioCasilla==0)||(arrayDisp[numAleatorioCasilla-1] == 1) || (sumafila + numAleatorioCasilla >= sumaTotal)||(sumacolumna+numAleatorioCasilla!=sumaTotal))//Comprobamos si ese número está siendo utilizado y la suma de las casillas que no están vacías más el num aleatorio es mayor o igual a la sumaTotal
                            numAleatorioCasilla = (int) (Math.random() * ((esCu.dimension*esCu.dimension) + 1));//Creamos num aleatorio entre 1 y la longitud max. (1-9)

                        esCu.matriz[posicionfila][posicioncolumna] = arrayNum[numAleatorioCasilla-1];//La casilla a llenar con el num aleatorio

                    } else if ((casillasVaciasColumna >= 1) && (casillasVaciasFila == 0)) {
                        numAleatorioCasilla = (int) (Math.random() * ((esCu.dimension*esCu.dimension) + 1));//Creamos num aleatorio entre 1 y la longitud max. (1-9)

                        while ((numAleatorioCasilla==0)||(arrayDisp[numAleatorioCasilla-1] == 1) || (sumacolumna + numAleatorioCasilla >= sumaTotal)||(sumafila+numAleatorioCasilla!=sumaTotal))//Comprobamos si ese número está siendo utilizado y la suma de las casillas que no están vacías más el num aleatorio es mayor o igual a la sumaTotal
                            numAleatorioCasilla = (int) (Math.random() * ((esCu.dimension*esCu.dimension)+ 1));//Creamos num aleatorio entre 1 y la longitud max. (1-9)

                        esCu.matriz[posicionfila][posicioncolumna] = arrayNum[numAleatorioCasilla-1];//La casilla a llenar con el num aleatorio

                    } else {
                        numAleatorioCasilla = (int) (Math.random() * ((esCu.dimension*esCu.dimension) + 1));//Creamos num aleatorio entre 1 y la longitud max. (1-9)

                        while ((numAleatorioCasilla==0)||(arrayDisp[numAleatorioCasilla-1] == 1) || (sumafila + numAleatorioCasilla >= sumaTotal)||(sumacolumna + numAleatorioCasilla != sumaTotal))//Comprobamos si ese número está siendo utilizado y la suma de las casillas que no están vacías más el num aleatorio es mayor o igual a la sumaTotal
                            numAleatorioCasilla = (int) (Math.random() * ((esCu.dimension*esCu.dimension)+ 1));//Creamos num aleatorio entre 1 y la longitud max. (1-9)

                        esCu.matriz[posicionfila][posicioncolumna] = arrayNum[numAleatorioCasilla-1];//La casilla a llenar con el num aleatorio

                    }
                }
                return esCu.matriz[posicionfila][posicioncolumna];
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
        return ((Estadocuadrado) es).check == Estadocuadrado.Check.FINALIZAR;
    }


    @Override
    public Accion[] acciones(Estado es) {
        return listaAcciones;
    }
}
