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
            int comprobar=0;

            if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.IZQ) && (posicioncolumna == 0)) {//En caso de que la posicion sea 0
                posicioncolumna = matriz.length-1;
            } else if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.IZQ)) {//Si nos movemos a la izq, restamos a la posición inicial -1
                posicioncolumna--;
            } else if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.DER) && (posicioncolumna== matriz.length-1)) {//En caso de que corresponda con la última posición
                posicioncolumna = 0;
            } else if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.DER)) {//Si nos movemos a la der, sumamos a la posición inicial +1
                posicioncolumna++;
            } else if ((accion == ProblemaCuadradoMagico.AccionCuadrado.Accion.Abajo) && (posicionfila== matriz.length-1)){//En caso de que corresponda con la última posición
                 posicionfila = 0;
            }else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.Abajo)) {//Si nos movemos, sumamos +1
                posicionfila++;
            }else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.Arriba)&&(posicionfila == 0)) {//En caso de que corresponda con la última posición
                 posicionfila = matriz.length-1;
            }else if ((accion== ProblemaCuadradoMagico.AccionCuadrado.Accion.Arriba)) {//Si nos movemos, sumamos +1 (columna)
                posicionfila--;
            }

            if(esCu.matriz[posicionfila][posicioncolumna]==0) {//Si la casilla está vacía
                matriz[posicionfila][posicioncolumna] = numAleatorio(esCu, posicionfila, posicioncolumna);;

            }else{
                for(int i=0;i<esCu.dimension;i++){
                    for(int j=0;j<esCu.dimension;j++){
                        if(esCu.matriz[i][j]!=0)
                            comprobar++;
                    }
                }

                if(comprobar==(esCu.dimension* esCu.dimension))
                    esCu.check = Estadocuadrado.Check.FINALIZAR;
            }

            return new ProblemaCuadradoMagico.Estadocuadrado(matriz,matriz.length,esCu.check,posicionfila,posicioncolumna);

        }

       /* public int numAleatorio( ProblemaCuadradoMagico.Estadocuadrado esCu, int posicionfila,int posicioncolumna){
            int sumaTotal = ((esCu.matriz.length)*((int)(Math.pow(esCu.matriz.length,2))+1))/2;
            int sumafila=0;
            int sumacolumna=0;
            int casillasVaciasFila = -1;
            int casillasVaciasColumna = -1;
            int numAleatorioCasilla;
            int [] arrayNum = new int[esCu.dimension* esCu.dimension];
            int [] arrayDisp = new int[esCu.dimension* esCu.dimension];
            int [] arraynumAleatorio = new int[esCu.dimension* esCu.dimension];

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
                        arrayDisp[(esCu.matriz[i][j]) - 1] = 1;
                }
            }

            //Averiguar que elemento introducimos en la casilla
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
                    numAleatorioCasilla = 0;//Creamos num aleatorio entre 1 y la longitud max. (1-9)

                    for(int i=0;i<(esCu.dimension*esCu.dimension);i++){
                        if(arrayDisp[i]==0) { //Si el num no está siendo utilizado
                            while(pos)
                            while((((sumacolumna+numAleatorioCasilla +(i+1)!=sumaTotal)&&(sumafila+numAleatorioCasilla +(i+1)!=sumaTotal))||(numAleatorioCasilla==0)||(arrayDisp[numAleatorioCasilla-1] == 1))&&(numAleatorioCasilla<(esCu.dimension * esCu.dimension))) {//Comprobamos si puede servir como numAleatorio para introducir en la casilla
                                numAleatorioCasilla++;
                            }
                            if((arrayDisp[numAleatorioCasilla-1] == 1)) {
                                numAleatorioCasilla = 0;
                            }
                            if((((sumacolumna+numAleatorioCasilla +(i+1)==sumaTotal)||(sumafila+numAleatorioCasilla +(i+1)==sumaTotal))&&(numAleatorioCasilla!=0)&&(arrayDisp[numAleatorioCasilla-1] != 1))) {//Comprobamos si puede servir como numAleatorio para introducir en la casilla
                                arraynumAleatorio[i] = numAleatorioCasilla; //Añadimos los diferentes numAleatorios posibles a añadir al array
                            }
                                int j = i;
                                while(!aux) {
                                    //Comprobar si ese numAleatorio es necesario en otra casilla
                                    for (int n = 0; (n < esCu.dimension) && (n != posicionfila); n++) {//filas
                                        if ((esCu.matriz[0][n] + esCu.matriz[0][n] + esCu.matriz[0][n] + numAleatorioCasilla == sumaTotal)) {//En caso de que sea necesario en otra casilla
                                                for (; j < (esCu.dimension * esCu.dimension); j++) {//Volvemos a buscar otro numAleatorio
                                                    if (arrayDisp[i] == 0) { //Si el num no está siendo utilizado
                                                        while ((((sumacolumna + numAleatorioCasilla + (i + 1) != sumaTotal) && (sumafila + numAleatorioCasilla + (i + 1) != sumaTotal)) || (numAleatorioCasilla == 0) || (arrayDisp[numAleatorioCasilla - 1] == 1)) && (numAleatorioCasilla < (esCu.dimension * esCu.dimension))) {//Comprobamos si puede servir como numAleatorio para introducir en la casilla
                                                            numAleatorioCasilla++;
                                                        }
                                                        if ((arrayDisp[numAleatorioCasilla - 1] == 1)) {
                                                            numAleatorioCasilla = 0;
                                                        }
                                                    }
                                                }
                                            } else {
                                                aux = true;
                                                break;
                                            }
                                    }

                                    for (int m = 0; (m < esCu.dimension) && (m != posicioncolumna); m++) {//columnas
                                        if (esCu.matriz[m][0] + esCu.matriz[m][1] + esCu.matriz[m][2] + numAleatorioCasilla == sumaTotal){//En caso de que sea necesario en otra casilla
                                            for (; j < (esCu.dimension * esCu.dimension); j++) {
                                                if (arrayDisp[i] == 0) { //Si el num no está siendo utilizado
                                                    while ((((sumacolumna + numAleatorioCasilla + (i + 1) != sumaTotal) && (sumafila + numAleatorioCasilla + (i + 1) != sumaTotal)) || (numAleatorioCasilla == 0) || (arrayDisp[numAleatorioCasilla - 1] == 1)) && (numAleatorioCasilla < (esCu.dimension * esCu.dimension))) {//Comprobamos si puede servir como numAleatorio para introducir en la casilla
                                                        numAleatorioCasilla++;
                                                    }
                                                    if ((arrayDisp[numAleatorioCasilla - 1] == 1)) {
                                                        numAleatorioCasilla = 0;
                                                    }
                                                }
                                            }
                                        } else {
                                            aux = true;
                                            break;
                                        }
                                    }
                                }

                                break;
                            }
                        }


                    }

                    esCu.matriz[posicionfila][posicioncolumna] = arrayNum[numAleatorioCasilla-1];//La casilla a llenar con el num aleatorio

                }
            }
            return esCu.matriz[posicionfila][posicioncolumna];

        }


    }*/


        public int posicion(ProblemaCuadradoMagico.Estadocuadrado esCu, int num, int diferencia){
            int nuevaPosicion=0;
            for (int i = 0; i < (esCu.dimension); i++) {//fila
                for (int j = 0; j < esCu.dimension; j++) {//columna
                    if (esCu.matriz[i][j] == num + 1) {//Obtener posición del elemento más mayor (+1) del que queremos añadir
                        //Cambiar posición
                        if(diferencia==1) {//fila
                            if (i == 0)
                                nuevaPosicion = esCu.matriz.length - 1;
                            else
                                nuevaPosicion = esCu.fila + (diferencia);

                            if (esCu.matriz[nuevaPosicion][esCu.columna]!=0)
                                nuevaPosicion = esCu.matriz[esCu.fila-1][esCu.columna];

                        }else{//columna
                            if (j == 0)
                                nuevaPosicion = esCu.matriz.length - 1;
                            else
                                nuevaPosicion = esCu.fila + (diferencia);

                            if (esCu.matriz[esCu.fila][nuevaPosicion]!=0)
                                nuevaPosicion = esCu.matriz[esCu.fila-1][esCu.columna];
                        }

                    }
                }
            }
            return nuevaPosicion;
        }

        public int numInsertar(ProblemaCuadradoMagico.Estadocuadrado esCu, int[]arrayDisp,int[] arrayNum){
            int num = 0;
            for (int i = 0; i < (esCu.dimension * esCu.dimension); i++) {//Obtener num más pequeño antes que un elemento utilizado
                if (i < esCu.dimension - 1) {
                    if ((arrayDisp[i + 1] == 1) && (arrayDisp[i] == 0)) {
                        num = arrayNum[i];
                        break;
                    }
                } else {
                    if (arrayDisp[i] == 0) {
                        num = arrayNum[i];
                        break;
                    }
                }
            }
            return num;
        }



        public int numAleatorio( ProblemaCuadradoMagico.Estadocuadrado esCu, int posicionfila,int posicioncolumna){
            int sumaTotal = ((esCu.matriz.length)*((int)(Math.pow(esCu.matriz.length,2))+1))/2;
            int sumafila=0;
            int sumacolumna=0;
            int casillasVaciasFila = -1;
            int casillasVaciasColumna = -1;
            int [] arrayNum = new int[esCu.dimension* esCu.dimension];
            int [] arrayDisp = new int[esCu.dimension* esCu.dimension];
            int nuevaPosicionFila=0;
            int nuevaPosicionColumna=0;
            int [] [] matrizMeta = esCu.matriz;

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
                        arrayDisp[(esCu.matriz[i][j]) - 1] = 1;
                }
            }

            //Averiguar que elemento introducimos en la casilla
            if (esCu.matriz[posicionfila][posicioncolumna] == 0) {//Caso de que la casilla esté vacía.
                if ((casillasVaciasColumna == 0) && (casillasVaciasFila == 0)) {//En caso de que solamente (fila&columna) haya una casilla vacía, la que tenemos que llenar
                    //No hace falta comprobar en ArrayDisp, ya que al tener todas las casillas ocupadas excepto la que tenemos que rellenar,( solamente hay una solución)
                    esCu.matriz[posicionfila][posicioncolumna] = arrayNum[((sumaTotal) - sumafila)-1];//Es la suma de todos los nums de la fila/columna (excepto la casilla que tenemos que llenar) junto la resta con la sumaTotal.

                } else if ((casillasVaciasColumna == 0) && (casillasVaciasFila >= 1)) {//En caso de que en la fila haya más de una casilla sin rellenar, elegimos cualquier num aleatorio (sin que sume igual a sumaTotal, debido a que aparte de la casilla que debemos rellenar, aún queda otra casilla vacía)
                    esCu.matriz[posicionfila][posicioncolumna] = arrayNum[(sumaTotal-sumacolumna)-1];//La casilla a llenar con el num aleatorio
                    //if((arrayDisp[(sumaTotal-sumacolumna)-1] == 1))


                } else if ((casillasVaciasColumna >= 1) && (casillasVaciasFila == 0)) {
                    esCu.matriz[posicionfila][posicioncolumna] = arrayNum[(sumaTotal-sumafila)-1];//La casilla a llenar con el num aleatorio

                } else {
                    int n;
                    int count=0;
                    for(int i=0;i<(esCu.dimension* esCu.dimension);i++){
                        if(arrayDisp[i]==1)
                            count++;
                    }
                    for(int i=0;i< ((esCu.dimension* esCu.dimension)-count);i++){//Rellenamos la matriz meta
                        n = numInsertar(esCu,arrayDisp,arrayNum);
                        nuevaPosicionFila = posicion(esCu,n,1);
                        nuevaPosicionColumna = posicion(esCu,n,-1);
                        matrizMeta[nuevaPosicionFila][nuevaPosicionColumna] = n;
                    }
                }
            }

            return esCu.matriz[posicionfila][posicioncolumna];
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
