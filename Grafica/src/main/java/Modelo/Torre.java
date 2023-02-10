package Modelo;

import DAO.*;
import javafx.scene.image.Image;

public class Torre extends Pieza {
    /**
     * Metodo para construir y asignar un color a una Torre.
     *
     * @param color
     */
    public Torre(String color) {
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
    public boolean validoMovimiento(Movimiento mov, Tablero tablero) {
        boolean respuesta = false;
        if ((mov.esVertical() || mov.esHorizontal()) && tablero.hayPiezasEntre(mov))
            respuesta = true;
        return respuesta;
    }

    @Override
    public Image toImage() {
        Image image= null;
        if (Torre.super.getColor().equalsIgnoreCase("negro"))
            image = new Image("File:Grafica/src/main/resources/com/example/grafica/imagenes/TorreNegra.png");
        else
            image = new Image("File:Grafica/src/main/resources/com/example/grafica/imagenes/TorreBlanca.png");
        return image;
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
