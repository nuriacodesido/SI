package es.udc.sistemasinteligentes.gA_41.ejemplo;


import es.udc.sistemasinteligentes.gA_41.Accion;
import es.udc.sistemasinteligentes.gA_41.Estado;

public class Nodo {

    private Nodo padre;
    private Accion accion;
    private Estado estado;
    private int coste;
    private int f;


    public Nodo(Nodo padre,Estado estado, Accion accion){
        this.estado=estado;
        this.padre=padre;
        this.accion=accion;
    }

    public Nodo(Nodo padre,Estado estado, Accion accion, int coste, int f){
        this.estado=estado;
        this.padre=padre;
        this.accion=accion;
        this.coste=coste;
        this.f = f;
    }

   /* public Nodo(Nodo padre){
        this(padre,null,null,0,0);
    }*/


    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getCoste(){
        return coste;
    }

    public void setCoste(int coste){
        this.coste=coste;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
