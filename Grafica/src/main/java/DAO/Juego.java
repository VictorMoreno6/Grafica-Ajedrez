package DAO;

import common.Constantes;
import Modelo.*;

public class Juego {
    private String elTurno;

    /**
     * Método que construye la clase pieza y fija el turno para que empiezen siempre las blancas.
     *
     * @param elTurno
     */
    public Juego(String elTurno) {
        this.elTurno = elTurno;
    }

    /**
     * Método que devuelve un String indicando de quien es el turno.
     *
     * @return devuelve un String
     */
    public String getElTurno() {
        return elTurno;
    }

    /**
     * Método que cambia el turno, si era el turno de las blancas ahora es el de las negras y viceversa.
     */
    public void setElTurno() {
        if (elTurno == "blanco") {
            elTurno = "negro";
        } else {
            elTurno = "blanco";
        }
    }
    /*
    /**
     * Método que recibe el movimiento que ha introducido al usuario (Ej:a6a5) y lo traduce para que los otros metodos lean bien el movimiento introducido en letras y numeros.
     * @param jugada
     */
   public boolean leerJugada(String jugada){
       boolean entradavalida=false;
        jugada=jugada.toUpperCase();
       int filainicial;
       if (jugada.charAt(1) - 56 < 0) {
           filainicial = (int) ((jugada.charAt(1) - 56) * -1);
       } else {
           filainicial = (int) (jugada.charAt(1) - 56);
       }
       int columnainicial = (int) (jugada.charAt(0) - 65);
       int filafinal;
       if (jugada.charAt(3) - 56 < 0) {
           filafinal = (int) ((jugada.charAt(3) - 56) * -1);
       } else {
           filafinal = (int) (jugada.charAt(3) - 56);
       }
       int columnafinal = (int) (jugada.charAt(2) - 65);
        if (filainicial>=0 && filainicial<=7 && columnainicial>=0 && columnainicial<=7 && filafinal>=0 && filafinal<=7 && columnafinal>=0 && columnafinal<=7){
            entradavalida=true;
        }
        return entradavalida;
    }

    /**
     * Método que recibe una jugada (String) y un Tablero y comprueba que la jugada introducida es válida y si es asi llama al método que realiza una jugada
     *
     * @param jugada
     * @param tablero
     */
    public void movimientoJugada(String jugada, Tablero tablero) {
        if (leerJugada(jugada)){
            jugada = jugada.toUpperCase();
            int filainicial;
            if (jugada.charAt(1) - 56 < 0) {
                filainicial = (int) ((jugada.charAt(1) - 56) * -1);
            } else {
                filainicial = (int) (jugada.charAt(1) - 56);
            }
            int columnainicial = (int) (jugada.charAt(0) - 65);
            if (tablero.hayPieza(filainicial, columnainicial)) {
                Pieza x = tablero.devuelvePieza(filainicial, columnainicial);
                if (x.getColor().equalsIgnoreCase(elTurno)) {
                    int filafinal;
                    if (jugada.charAt(3) - 56 < 0) {
                        filafinal = (int) ((jugada.charAt(3) - 56) * -1);
                    } else {
                        filafinal = (int) (jugada.charAt(3) - 56);
                    }
                    int columnafinal = (int) (jugada.charAt(2) - 65);
                    Movimiento mov = new Movimiento(new Posicion(filainicial, columnainicial), new Posicion(filafinal, columnafinal));
                    if (x.validoMovimiento(mov, tablero) && !tablero.provocaJaque(x,filafinal,columnafinal,filainicial,columnainicial) || tablero.enroque(mov,x,this) && !tablero.provocaJaque(x,filafinal,columnafinal,filainicial,columnainicial)) {
                        if ((elTurno.equalsIgnoreCase("negro")) && !(tablero.jaqueNegro()) || (elTurno.equalsIgnoreCase("blanco") && !tablero.jaqueBlanco())){
                            if (tablero.hayPieza(filafinal, columnafinal)) {
                                Pieza y = tablero.devuelvePieza(filafinal, columnafinal);
                                if (y.getColor().equalsIgnoreCase(x.getColor()) && !tablero.enroque(mov,x,this)) {
                                    System.out.println(Constantes.NO_VAYAS_EN_TU_CONTRA_COMETE_LAS_PIEZAS_RIVALES);
                                } else {
                                    tablero.mover(mov, x, Juego.this);
                                }
                            } else {
                                tablero.mover(mov, x, Juego.this);
                            }
                        } else if (tablero.evitaJaque(x,filafinal,columnafinal,filainicial,columnainicial)){
                            if (tablero.hayPieza(filafinal, columnafinal)) {
                                Pieza y = tablero.devuelvePieza(filafinal, columnafinal);
                                if (y.getColor().equalsIgnoreCase(x.getColor())) {
                                    System.out.println(Constantes.NO_VAYAS_EN_TU_CONTRA_COMETE_LAS_PIEZAS_RIVALES);
                                } else {
                                    tablero.mover(filainicial, filafinal, columnainicial, columnafinal, x, Juego.this);
                                }
                            } else {
                                tablero.mover(filainicial, filafinal, columnainicial, columnafinal, x, Juego.this);
                            }
                        } else {
                            System.out.println(Constantes.ESTAS_EN_JAQUE_NO_PUEDES_REALIZAR_ESTE_MOVIMIENTO);
                        }
                    } else {
                        System.out.println(Constantes.MOVIMIENTO_INVALIDO);
                    }
                } else {
                    System.out.println(Constantes.NO_TE_PRECIPITES_ESPERA_TU_TURNO);
                }
            } else {
                System.out.println(Constantes.MOVIMIENTO_NO_VALIDO);
            }
        } else {
            System.out.println(Constantes.MOVIMIENTO_NO_VALIDO);
        }
    }

    public void graficamovimientoJugada(int filainicial,int columnainicial,int filafinal,int columnafinal, Tablero tablero) {
        if (tablero.hayPieza(filainicial, columnainicial)) {
            Pieza x = tablero.devuelvePieza(filainicial, columnainicial);
            if (x.getColor().equalsIgnoreCase(elTurno)) {
                Movimiento mov = new Movimiento(new Posicion(filainicial, columnainicial), new Posicion(filafinal, columnafinal));
                if (x.validoMovimiento(mov, tablero) && !tablero.provocaJaque(x,filafinal,columnafinal,filainicial,columnainicial) || tablero.enroque(mov,x,this) && !tablero.provocaJaque(x,filafinal,columnafinal,filainicial,columnainicial)) {
                    if ((elTurno.equalsIgnoreCase("negro")) && !(tablero.jaqueNegro()) || (elTurno.equalsIgnoreCase("blanco") && !tablero.jaqueBlanco())){
                        if (tablero.hayPieza(filafinal, columnafinal)) {
                            Pieza y = tablero.devuelvePieza(filafinal, columnafinal);
                            if (y.getColor() == x.getColor() && !tablero.enroque(mov,x,this)) {
                                System.out.println(Constantes.NO_VAYAS_EN_TU_CONTRA_COMETE_LAS_PIEZAS_RIVALES);
                            } else {
                                tablero.mover(mov, x, Juego.this);
                            }
                        } else {
                            tablero.mover(mov, x, Juego.this);
                        }
                    } else if (tablero.evitaJaque(x,filafinal,columnafinal,filainicial,columnainicial)){
                        if (tablero.hayPieza(filafinal, columnafinal)) {
                            Pieza y = tablero.devuelvePieza(filafinal, columnafinal);
                            if (y.getColor() == x.getColor()) {
                                System.out.println(Constantes.NO_VAYAS_EN_TU_CONTRA_COMETE_LAS_PIEZAS_RIVALES);
                            } else {
                                tablero.mover(filainicial, filafinal, columnainicial, columnafinal, x, Juego.this);
                            }
                        } else {
                            tablero.mover(filainicial, filafinal, columnainicial, columnafinal, x, Juego.this);
                        }
                    } else {
                        System.out.println(Constantes.ESTAS_EN_JAQUE_NO_PUEDES_REALIZAR_ESTE_MOVIMIENTO);
                    }
                } else {
                    System.out.println(Constantes.MOVIMIENTO_INVALIDO);
                }
            } else {
                System.out.println(Constantes.NO_TE_PRECIPITES_ESPERA_TU_TURNO);
            }
        } else {
            System.out.println(Constantes.MOVIMIENTO_NO_VALIDO);
        }
    }
    /**
     * Método que devuelve un String indicando de quien es el turno.
     *
     * @return devuelve un String.
     */
    @Override
    public String toString() {
        return elTurno;
    }
}
