package org.example;

public class Rey extends Pieza {
    /**
     * Metodo para construir y asignar un color a un Rey.
     *
     * @param color
     */
    Rey(String color) {
        super(color);
        jaque = false;
    }

    private boolean jaque;

    public boolean isJaque() {
        return jaque;
    }

    public void setJaque() {
        if (jaque) {
            jaque = false;
        } else {
            jaque = true;
        }
    }

    /**
     * Metodo  que recibe unos parametros de Movimiento y tablero y verifica que el rey se mueve correctamente, es decir, en cualquier dirección pero solo una casilla.
     *
     * @param mov
     * @param tablero
     * @return Devuelve un booleano de si el movimiento es valido o no.
     */
    @Override
    boolean validoMovimiento(Movimiento mov, Tablero tablero) {
        boolean respuesta = false;
        if (Math.abs(mov.getPosInicial().getFila() - mov.getPosFinal().getFila()) <= 1 && Math.abs(mov.getPosInicial().getColumna() - mov.getPosFinal().getColumna()) <= 1 )
            respuesta = true;
        return respuesta;
    }

    /**
     * Método que devuelve el nombre de la pieza al imprimir el tablero dependiendo del color de esta.
     *
     * @return devuelve lo que se va a escribir en el tablero.
     */
    @Override
    public String toString() {
        String pieza;
        if (Rey.super.getColor() == "negro") {
            pieza = "KN";
        } else {
            pieza = "KB";
        }
        return pieza;
    }
}
