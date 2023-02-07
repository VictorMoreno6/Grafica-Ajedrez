package org.example;

public class Reina extends Pieza {
    /**
     * Método para construir y asignar un color a una Reina.
     *
     * @param color
     */
    Reina(String color) {
        super(color);
    }

    /**
     * Método que recibe unos parametros de movimiento y tablero y comprueba que el movimiento introducido es correcto en el caso de la Reina esta se puede mover tanto en vertical como diagonal como horizontal y tambien debe mirar que no hay piezas entre medias del movimiento deseado.
     *
     * @param mov
     * @param tablero
     * @return Devuelve un booleano de si el movimiento es valido o no.
     */
    @Override
    boolean validoMovimiento(Movimiento mov, Tablero tablero) {
        boolean respuesta = false;
        if ((mov.esDiagonal() || mov.esVertical() || mov.esHorizontal()) && tablero.hayPiezasEntre(mov)) {
            respuesta = true;
        }
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
        if (Reina.super.getColor() == "negro") {
            pieza = "QN";
        } else {
            pieza = "QB";
        }
        return pieza;
    }
}
