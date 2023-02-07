package org.example;

public class Torre extends Pieza {
    /**
     * Metodo para construir y asignar un color a una Torre.
     *
     * @param color
     */
    Torre(String color) {
        super(color);
    }

    /**
     * Método que recibe unos parametros de movimiento y tablero y comprueba que el movimiento introducido es correcto es decir que se mueve en vertical u horizontal y no hay piezas en medio.
     *
     * @param mov
     * @param tablero
     * @return Devuelve un booleano de si el movimiento es valido o no.
     */
    @Override
    boolean validoMovimiento(Movimiento mov, Tablero tablero) {
        boolean respuesta = false;
        if ((mov.esVertical() || mov.esHorizontal()) && tablero.hayPiezasEntre(mov))
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
        if (super.getColor().equals("negro")) {
            pieza = "TN";
        } else {
            pieza = "TB";
        }
        return pieza;
    }
}
