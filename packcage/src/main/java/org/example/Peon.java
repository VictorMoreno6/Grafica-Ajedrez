package org.example;

public class Peon extends Pieza {
    /**
     * Metodo para construir y asignar un color a un peon
     *
     * @param color
     */
    Peon(String color) {
        super(color);
    }

    /**
     * Metodo que recibe unos parametros de movimiento y tablero y valida si el movimiento es valido.Es decir que el peon solo puede moverse 2 al principio y luego de 1 en 1 y solo se puede mover en vertical salvo que tenga una pieza enemiga en diagonal que en ese caso podra comerse dicha pieza y avanzar en diagonal. Ademas los peones solo se pueden mover para adelante.
     *
     * @param mov
     * @param tablero
     * @return Devuelve un booleano de si el movimiento es valido o no.
     */
    @Override
    boolean validoMovimiento(Movimiento mov, Tablero tablero) {
        boolean respuesta = false;
        if (tablero.hayPiezasEntre(mov)) {
            if (mov.esVertical() && mov.saltoVertical() == -1 && color.equalsIgnoreCase("negro") && !tablero.hayPieza(mov.getPosFinal())) {
                respuesta = true;
            } else if (mov.esVertical() && mov.saltoVertical() == -2 && getNumMovimientos() == 0 && color.equalsIgnoreCase("negro") && !tablero.hayPieza(mov.getPosFinal())) {
                respuesta = true;
            } else if (mov.esVertical() && mov.saltoVertical() == 1 && color.equalsIgnoreCase("blanco") && !tablero.hayPieza(mov.getPosFinal())) {
                respuesta = true;
            } else if (mov.esVertical() && mov.saltoVertical() == 2 && getNumMovimientos() == 0 && color.equalsIgnoreCase("blanco") && !tablero.hayPieza(mov.getPosFinal())) {
                respuesta = true;
            } else if (mov.esDiagonal() && tablero.hayPieza(mov.getPosFinal()) && mov.saltoVertical() == -1 && Math.abs(mov.saltoHorizontal()) == 1 && color.equalsIgnoreCase("negro")) {
                respuesta = true;
            } else if (mov.esDiagonal() && tablero.hayPieza(mov.getPosFinal()) && mov.saltoVertical() == 1 && Math.abs(mov.saltoHorizontal()) == 1 && color.equalsIgnoreCase("blanco")) {
                respuesta = true;
            }
        }
        return respuesta;
    }

    /**
     * Método que devuelve el nombre de la pieza al imprimir el tablero dependiendo del color de esta
     *
     * @return devuelve lo que se va a escribir en el tablero
     */
    @Override
    public String toString() {
        String pieza;
        if (Peon.super.getColor() == "negro") {
            pieza = "PN";
        } else {
            pieza = "PB";
        }
        return pieza;
    }
}

