package Modelo;

import DAO.*;
import javafx.scene.image.Image;

public class Alfil extends Pieza {
    /**
     * Metodo para construir y asignar un color a un Alfil
     *
     * @param color
     */
    public Alfil(String color) {
        super(color);
    }

    /**
     * Método que recibe unos parametros de Movimiento y tablero y comprueba que el movimiento introducido por el usuario es correcto, es decir que el movimiention es diagonal y no hay piezas que impidan este movimiento.
     *
     * @param mov
     * @param tablero
     * @return Devuelve un booleano de si el movimiento es valido o no.
     */
    @Override
    public boolean validoMovimiento(Movimiento mov, Tablero tablero) {
        boolean respuesta = false;
        if (mov.esDiagonal() && tablero.hayPiezasEntre(mov)) {
            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public Image toImage() {
        Image image= null;
        if (Alfil.super.getColor().equalsIgnoreCase("negro"))
            image = new Image("File:Grafica/src/main/resources/com/example/grafica/imagenes/AlfilNegro.png");
         else
            image = new Image("File:Grafica/src/main/resources/com/example/grafica/imagenes/AlfilBlanco.png");
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
        if (Alfil.super.getColor() == "negro") {
            pieza = "AN";
        } else {
            pieza = "AB";
        }
        return pieza;
    }
}
