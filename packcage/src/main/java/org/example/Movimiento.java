package org.example;

public class Movimiento {
    private Posicion posInicial;
    private Posicion posFinal;

    /**
     * Método que devuelve la posición inicial de un movimiento.
     *
     * @return devuelve una posición.
     */
    public Posicion getPosInicial() {
        return posInicial;
    }

    /**
     * Método que cambia la posición inicial a la posición que se le ha introducido.
     *
     * @param posInicial
     */
    public void setPosInicial(Posicion posInicial) {
        this.posInicial = posInicial;
    }

    /**
     * Método que devuelve la posición final de un movimiento.
     *
     * @return devueelve una posición.
     */
    public Posicion getPosFinal() {
        return posFinal;
    }

    /**
     * Método que cambia la posición final a la posición que se le ha introducido.
     *
     * @param posFinal
     */
    public void setPosFinal(Posicion posFinal) {
        this.posFinal = posFinal;
    }

    /**
     * Método que construye un movimiento despues de recibir 4 parametros int de entrada.
     *
     * @param fila
     * @param colum
     * @param filab
     * @param columb
     */
    Movimiento(int fila, int colum, int filab, int columb) {
        posInicial = new Posicion(fila, colum);
        posFinal = new Posicion(filab, columb);
    }

    /**
     * Método que construye un movimiento despues de recibir 2 parametros posición de entrada.
     *
     * @param posInicial
     * @param posFinal
     */
    Movimiento(Posicion posInicial, Posicion posFinal) {
        this.posInicial = posInicial;
        this.posFinal = posFinal;
    }

    /**
     * Método que comprueba si el movimiento es vertical.
     *
     * @return devuelve un booleano de true si es vertical.
     */
    public boolean esVertical() {
        boolean vertical = false;
        if (posInicial.getColumna() == posFinal.getColumna())
            vertical = true;
        return vertical;
    }

    /**
     * Método que comprueba si el movimiento es horizontal.
     *
     * @return devuelve un booleano de true si es horizontal.
     */
    public boolean esHorizontal() {
        boolean horizontal = false;
        if (posInicial.getFila() == posFinal.getFila()) {
            horizontal = true;
        }
        return horizontal;
    }

    /**
     * Método que comprueba si el movimiento es horizontal.
     *
     * @return devuelve un booleano de true si es diagonal.
     */
    public boolean esDiagonal() {
        boolean diagonal = false;
        if (Math.abs(posInicial.getFila() - posFinal.getFila()) == Math.abs(posInicial.getColumna() - posFinal.getColumna()))
            diagonal = true;
        return diagonal;
    }

    /**
     * Método que mira cuantas casillas se mueve la pieza si el movimiento es horizontal.
     *
     * @return devuelve un int que indica la cantidad del movimiento.
     */
    public int saltoHorizontal() {
        int salto = posInicial.getColumna() - posFinal.getColumna();
        return salto;
    }

    /**
     * Método que mira cuantas casillas se mueve la pieza si el movimiento es vertical.
     *
     * @return devuelve el numero de casillas que se mueve verticalmente.
     */
    public int saltoVertical() {
        int salto = posInicial.getFila() - posFinal.getFila();
        return salto;
    }

}
