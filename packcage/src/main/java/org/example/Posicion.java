package org.example;

public class Posicion {
    private int fila;
    private int columna;

    /**
     * Método que construye una posición y la inicializa a 0,0.
     */
    public Posicion() {
        fila = 0;
        columna = 0;
    }

    /**
     * Método que construye una posición y la inicializa a la fila y columna que se le introducen.
     *
     * @param fila
     * @param columna
     */
    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Método que devuelve la fila de una posición.
     *
     * @return devuelve un int fila.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Método que inicializa una fila al valor del parametro introducido.
     *
     * @param fila
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * Método que devuelve la columna de una posición.
     *
     * @return devuelve un int columna.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Método que inicializa una columna al valor del parametro introducido.
     *
     * @param columna
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    /**
     * Método que devuelve la fila y columna de una posición.
     *
     * @return devuelve una fila y una columna.
     */
    @Override
    public String toString() {
        return "Posicion{" +
                "fila=" + fila +
                ", columna=" + columna +
                '}';
    }
}
