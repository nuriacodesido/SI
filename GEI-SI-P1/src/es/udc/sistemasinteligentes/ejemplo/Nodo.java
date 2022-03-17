package es.udc.sistemasinteligentes.ejemplo;


import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;

public class Nodo {

    private Nodo padre;
    private Accion accion;
    private Estado estado;


    public Nodo(Nodo padre,Estado estado, Accion accion){
        this.estado=estado;
        this.padre=padre;
        this.accion=accion;
    }

    public Nodo(Nodo padre){
        this(padre,null,null);
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
