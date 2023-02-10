package Modelo;

import DAO.*;
import javafx.scene.image.Image;

public class Caballo extends Pieza {
    /**
     * Metodo para construir y asignar un color a un caballo.
     * @param color
     */
    public Caballo(String color) {
        super(color);
    }
    /**
     * Metodo para validar si el movimiento introducido por el usuario es valido o no.
     * @param mov
     * @param tablero
     * @return booleano que dice si el movimiento es valido. Es decir que el movimiento es en L.
     */
    @Override
    public boolean validoMovimiento(Movimiento mov, Tablero tablero) {
        boolean respuesta = false;
        if ((Math.abs(mov.getPosInicial().getFila()-mov.getPosFinal().getFila()) + Math.abs(mov.getPosInicial().getColumna()-mov.getPosFinal().getColumna())) == 3 && Math.abs(mov.getPosInicial().getFila()-mov.getPosFinal().getFila())>=1 &&  Math.abs(mov.getPosInicial().getColumna()-mov.getPosFinal().getColumna())>=1){
            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public Image toImage() {
        Image image= null;
        if (Caballo.super.getColor().equalsIgnoreCase("negro"))
            image = new Image("File:Grafica/src/main/resources/com/example/grafica/imagenes/CaballoNegro.png");
        else
            image = new Image("File:Grafica/src/main/resources/com/example/grafica/imagenes/CaballoBlanco.png");
        return image;
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
