package org.example;

public abstract class Pieza {
    protected String color;
    protected String nombre;

    protected int numMovimientos;

    /**
     * Metodo para revisar el numero de movimientos que ha realizado la pieza. Esto permite acciones como que el peon pueda moverse 2 casillas en su primer movimiento.
     *
     * @return devuelve el numero de movimientos que ha realizado esa pieza.
     */
    public int getNumMovimientos() {
        return numMovimientos;
    }

    /**
     * Método que suma un movimiento a una pieza cada vez que esta se mueve impidiendo asi por ejemplo que el peon se mueva 2 casillas despues de haber realizado ya su primer movimiento.
     */
    public void setNumMovimientos() {
        numMovimientos++;
    }

    /**
     * Método que inizializa la Pieza al color que le introduzcan.
     *
     * @param color
     */
    Pieza(String color) {
        this.color = color;
        numMovimientos = 0;
    }

    /**
     * Método que devuelve el color de la pieza.
     *
     * @return devuelve el color de la pieza.
     */
    public String getColor() {
        return color;
    }

    /**
     * Método que obliga a las clases que tienen esta herencia a implantar este método.
     *
     * @param mov
     * @param tablero
     * @return devuelve un booleano.
     */
    abstract boolean validoMovimiento(Movimiento mov, Tablero tablero);

    /**
     * Método que devuelve el color de la pieza (aunque podria devolver mas parametros).
     *
     * @return ddevuelve un String.
     */
    @Override
    public String toString() {
        return color;
    }
}
