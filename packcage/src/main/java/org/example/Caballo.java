package org.example;

public class Caballo extends Pieza {
    /**
     * Metodo para construir y asignar un color a un caballo.
     * @param color
     */
    Caballo(String color) {
        super(color);
    }
    /**
     * Metodo para validar si el movimiento introducido por el usuario es valido o no.
     * @param mov
     * @param tablero
     * @return booleano que dice si el movimiento es valido. Es decir que el movimiento es en L.
     */
    @Override
    boolean validoMovimiento(Movimiento mov, Tablero tablero) {
        boolean respuesta = false;
        if ((Math.abs(mov.getPosInicial().getFila()-mov.getPosFinal().getFila()) + Math.abs(mov.getPosInicial().getColumna()-mov.getPosFinal().getColumna())) == 3 && Math.abs(mov.getPosInicial().getFila()-mov.getPosFinal().getFila())>=1 &&  Math.abs(mov.getPosInicial().getColumna()-mov.getPosFinal().getColumna())>=1){
            respuesta = true;
        }
        return respuesta;
    }

    /**
     *Método que devuelve el nombre de la pieza al imprimir el tablero dependiendo del color de esta.
     * @return devuelve lo que se va a escribir en el tablero.
     */
    @Override
    public String toString() {
        String pieza;
        if (Caballo.super.getColor()=="negro"){
            pieza="CN";
        } else {
            pieza="CB";
        }
        return pieza;
    }
}
