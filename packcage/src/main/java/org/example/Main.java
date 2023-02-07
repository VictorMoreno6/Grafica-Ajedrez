package org.example;

import org.example.common.Constantes;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Juego juego = new Juego("blanco");
        Tablero tablero=new Tablero();
        tablero.pintarTablero();
        do{
            if (juego.getElTurno()=="blanco"){
                if (tablero.jaqueBlanco())
                    System.out.println(Constantes.ESTAS_EN_JAQUE);
                System.out.println(Constantes.ES_EL_TURNO_DE_LAS_BLANCAS);
            }
            else {
                if (tablero.jaqueNegro())
                    System.out.println(Constantes.ESTAS_EN_JAQUE);
                System.out.println(Constantes.ES_EL_TURNO_DE_LAS_NEGRAS);
            }
            System.out.println(Constantes.INTRODUCE_EL_MOVIMIENTO_QUE_QUIERES_REALIZAR);
            juego.movimientoJugada(sc.nextLine(),tablero);
        }while (!tablero.finJuego() && !tablero.reyAhogadoNegro() && !tablero.reyAhogadoBlanco());
        if (tablero.finJuego()){
            System.out.println(Constantes.JAQUE_MATE_CON_TOMATE);
            if (juego.getElTurno().equalsIgnoreCase("blanco")){
                System.out.println(Constantes.LAS_PIEZAS_NEGRAS_HAN_GANADO);
            } else {
                System.out.println(Constantes.LAS_PIEZAS_BLANCAS_HAN_GANADO);
            }
        } else if (tablero.reyAhogadoBlanco() || tablero.reyAhogadoNegro()){
            System.out.println(Constantes.TABLAS_POR_REY_AHOGADO);
        }

    }

}