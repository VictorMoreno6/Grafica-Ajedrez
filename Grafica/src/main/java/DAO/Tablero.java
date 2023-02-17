package DAO;

import UI.HelloController;
import common.Constantes;
import Modelo.*;
import javafx.scene.layout.StackPane;

import java.util.Scanner;

public class Tablero {
    private Pieza[][] tablero;

    /**
     * Método que construye un tablero e inicializa los valores de este.
     */
    public Tablero() {
        tablero = new Pieza[8][8];
        tablero[0][0] = new Torre("negro");
        tablero[0][1] = new Caballo("negro");
        tablero[0][2] = new Alfil("negro");
        tablero[0][3] = new Reina("negro");
        tablero[0][4] = new Rey("negro");
        tablero[0][5] = new Alfil("negro");
        tablero[0][6] = new Caballo("negro");
        tablero[0][7] = new Torre("negro");
        for (int i = 0; i < 8; i++) {
            tablero[1][i] = new Peon("negro");
        }
        for (int i = 0; i < 65; i++) {
            StackPane a = new StackPane();
        }

        StackPane row0Col1 = new StackPane();
        StackPane row0Col2 = new StackPane();
        StackPane row0Col3 = new StackPane();


        tablero[7][0] = new Torre("blanco");
        tablero[7][1] = new Caballo("blanco");
        tablero[7][2] = new Alfil("blanco");
        tablero[7][3] = new Reina("blanco");
        tablero[7][4] = new Rey("blanco");
        tablero[7][5] = new Alfil("blanco");
        tablero[7][6] = new Caballo("blanco");
        tablero[7][7] = new Torre("blanco");
        for (int i = 0; i < 8; i++) {
            tablero[6][i] = new Peon("blanco");
        }
    }

    /**
     * Método que pinta el tablero y todas sus piezas.
     */
    public void pintarTablero() {
        char letras[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print(letras[i] + "  ");
        }
        System.out.println();
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(8 - i + " ");
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == null) {
                    System.out.print("[] ");
                } else {
                    System.out.print(tablero[i][j].toString() + " ");
                }
            }
            System.out.println();
        }

    }

    /**
     * Método que comprrueba si hay una pieza en la posicion que se le ha introducido.
     *
     * @param fila
     * @param columna
     * @return devuelve un booleano de true si hay una pieza en esa posición.
     */
    public boolean hayPieza(int fila, int columna) {
        boolean hay = false;
        if (tablero[fila][columna] != null) {
            hay = true;
        }
        return hay;
    }

    /**
     * Método que hace lo mismo que el método anterior de hayPieza pero con la diferencia de que aqui se le introduce un parametro de posición.
     *
     * @param pos
     * @return devuelve un booleano de true hay una pieza en esa posición.
     */
    public boolean hayPieza(Posicion pos) {
        boolean hay = false;
        if (tablero[pos.getFila()][pos.getColumna()] != null) {
            hay = true;
        }
        return hay;
    }

    /**
     * Método que comprueba que no hay piezas entre medias del movimiento que quiere el usuario realizar y que impiden la ejecución de este.
     *
     * @param mov
     * @return devuelve un booleano de true si hay piezas entre y false si no las hay.
     */
    public boolean hayPiezasEntre(Movimiento mov) {
        boolean no = true;
        if (mov.esVertical()) {
            if (mov.getPosInicial().getFila() - mov.getPosFinal().getFila() > 0) {
                for (int i = 1; i < Math.abs(mov.saltoVertical()); i++) {
                    if (hayPieza(mov.getPosInicial().getFila() - i, mov.getPosInicial().getColumna())) {
                        no = false;
                    }
                }
            } else {
                for (int i = 1; i < Math.abs(mov.saltoVertical()); i++) {
                    if (hayPieza(mov.getPosInicial().getFila() + i, mov.getPosInicial().getColumna())) {
                        no = false;
                    }
                }
            }
        }
        if (mov.esHorizontal()) {
            if (mov.getPosInicial().getColumna() - mov.getPosFinal().getColumna() > 0) {
                for (int i = 1; i < Math.abs(mov.saltoHorizontal()); i++) {
                    if (hayPieza(mov.getPosInicial().getFila(), mov.getPosInicial().getColumna() - i)) {
                        no = false;
                    }
                }
            } else {
                for (int i = 1; i < Math.abs(mov.saltoHorizontal()); i++) {
                    if (hayPieza(mov.getPosInicial().getFila(), mov.getPosInicial().getColumna() + i)) {
                        no = false;
                    }
                }
            }
        }
        if (mov.esDiagonal()) {
            int fila = mov.getPosInicial().getFila() - mov.getPosFinal().getFila();
            int columna = mov.getPosInicial().getColumna() - mov.getPosFinal().getColumna();
            if (fila > 0 && columna > 0) {
                for (int i = 1; i < fila; i++) {
                    if (hayPieza(mov.getPosInicial().getFila() - i, mov.getPosInicial().getColumna() - i)) {
                        no = false;
                    }
                }
            }
            if (fila > 0 && columna < 0) {
                for (int i = 1; i < fila; i++) {
                    if (hayPieza(mov.getPosInicial().getFila() - i, mov.getPosInicial().getColumna() + i)) {
                        no = false;
                    }
                }
            }
            if (fila < 0 && columna > 0) {
                for (int i = 1; i < columna; i++) {
                    if (hayPieza(mov.getPosInicial().getFila() + i, mov.getPosInicial().getColumna() - i)) {
                        no = false;
                    }
                }
            }
            if (fila < 0 && columna < 0) {
                for (int i = 1; i < Math.abs(columna); i++) {
                    if (hayPieza(mov.getPosInicial().getFila() + i, mov.getPosInicial().getColumna() + i)) {
                        no = false;
                    }
                }
            }
        }
        return no;
    }

    /**
     * Método que pone una pieza que recibe en la posición que recibe.
     *
     * @param figura
     * @param Pos
     */
    public void ponPieza(Pieza figura, Posicion Pos) {
        tablero[Pos.getFila()][Pos.getColumna()] = figura;
    }

    /**
     * Método que pone una pieza que recibe en la fila y columna que recibe.
     *
     * @param figura
     * @param fila
     * @param columna
     */
    public void ponPieza(Pieza figura, int fila, int columna) {
        tablero[fila][columna] = figura;
    }

    /**
     * Método que iguala a null la fila y columna que recibe.
     *
     * @param fila
     * @param columna
     */
    public void quitaPieza(int fila, int columna) {
        tablero[fila][columna] = null;
    }

    /**
     * Método que iguala a null la fila y columna que recibe.
     *
     * @param Pos
     */
    public void quitaPieza(Posicion Pos) {
        tablero[Pos.getFila()][Pos.getColumna()] = null;
    }

    /**
     * Método que devuelvee una pieza que se situa en una determinada fila y columna.
     *
     * @param fila
     * @param columna
     * @return devuelve una Pieza
     */
    public Pieza devuelvePieza(int fila, int columna) {
        return tablero[fila][columna];
    }

    /**
     * Método que devuelvee una pieza que se situa en una determinada posición.
     *
     * @param pos
     * @return
     */
    public Pieza devuelvePieza(Posicion pos) {
        return tablero[pos.getFila()][pos.getColumna()];
    }

    public boolean jaqueNegro() {
        boolean jaque = false;
        int infilan = 0;
        int incolumn = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (devuelvePieza(i,j)!=null){
                    if (devuelvePieza(i, j).getClass().getSimpleName().equalsIgnoreCase("Rey") && devuelvePieza(i, j).getColor().equalsIgnoreCase("negro")) {
                        infilan = i;
                        incolumn = j;
                    }
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (devuelvePieza(i,j)!=null)
                if (!devuelvePieza(i, j).getClass().getSimpleName().equalsIgnoreCase("Rey") && !devuelvePieza(i, j).getColor().equalsIgnoreCase("negro") && hayPieza(i, j)) {
                    Movimiento mov = new Movimiento(i, j, infilan, incolumn);
                    Pieza x = devuelvePieza(i, j);
                    if (x.validoMovimiento(mov, this)) {
                        jaque = true;
                    }
                }
            }
        }
        return jaque;
    }
    public boolean jaqueBlanco(){
        boolean jaque = false;
        int infilab = 0;
        int incolumb = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (devuelvePieza(i,j)!=null){
                    if (devuelvePieza(i, j).getClass().getSimpleName().equalsIgnoreCase("Rey") && devuelvePieza(i, j).getColor().equalsIgnoreCase("blanco")) {
                        infilab = i;
                        incolumb = j;
                    }
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (devuelvePieza(i,j)!=null)
                if (!devuelvePieza(i, j).getClass().getSimpleName().equalsIgnoreCase("Rey") && !devuelvePieza(i, j).getColor().equalsIgnoreCase("blanco") && hayPieza(i, j)) {
                    Movimiento mov = new Movimiento(i, j, infilab, incolumb);
                    Pieza x = devuelvePieza(i, j);
                    if (x.validoMovimiento(mov, this)) {
                        jaque = true;
                    }
                }
            }
        }
        return jaque;
    }
    public boolean evitaJaque(Pieza x, int filafinal,int columnafinal, int filainicial, int columnainicial){
        boolean hayjaque=false;
        Pieza y=devuelvePieza(filafinal,columnafinal);
        ponPieza(x,filafinal,columnafinal);
        quitaPieza(filainicial,columnainicial);
        if (y!=null && !y.getColor().equals(x.getColor()) || y==null){
            if (x.getColor().equalsIgnoreCase("blanco") && !jaqueBlanco()){
                hayjaque=true;
            } else if (x.getColor().equalsIgnoreCase("negro") && !jaqueNegro()) {
                hayjaque=true;
            }
        }
        quitaPieza(filafinal,columnafinal);
        ponPieza(x,filainicial,columnainicial);
        ponPieza(y,filafinal,columnafinal);
        return hayjaque;
    }
    public boolean provocaJaque(Pieza x, int filafinal,int columnafinal, int filainicial, int columnainicial){
        boolean hayjaque=false;
        if (!jaqueBlanco() && !jaqueNegro()){
            Pieza y=devuelvePieza(filafinal,columnafinal);
            ponPieza(x,filafinal,columnafinal);
            quitaPieza(filainicial,columnainicial);
            if (x.getColor().equalsIgnoreCase("blanco") && jaqueBlanco()){
                hayjaque=true;
            } else if (x.getColor().equalsIgnoreCase("negro") && jaqueNegro()) {
                hayjaque=true;
            }
            quitaPieza(filafinal,columnafinal);
            ponPieza(x,filainicial,columnainicial);
            ponPieza(y,filafinal,columnafinal);
        }

        return hayjaque;
    }

    /**
     * Método que llama al otro metodo de mover pero que tiene menos parametros y desglosa estos para asi facilitar el trabajo a las otras clases y que tengan mas opciones de llamada.
     *
     * @param mov
     * @param x
     * @param elTurno
     */
    public void mover(Movimiento mov, Pieza x, Juego elTurno) {
        mover(mov.getPosInicial().getFila(), mov.getPosFinal().getFila(), mov.getPosInicial().getColumna(), mov.getPosFinal().getColumna(), x, elTurno);
    }

    /**
     * Método que recibe una fila y columna, inicial y final, una Pieza y un turno y realiza un movimiento en el tablero, pinta el tablero y cambia el turno.
     *
     * @param filainicial
     * @param filafinal
     * @param columnainicial
     * @param columnafinal
     * @param x
     * @param elTurno
     */
    public boolean mover(int filainicial, int columnainicial, int filafinal, int columnafinal, Pieza x, Juego elTurno) {
        boolean promo=false;
        Movimiento mov=new Movimiento(filainicial, columnainicial,filafinal,columnafinal);
        if (!enroque(mov,x,elTurno) && !enPasant(mov,x)){
            ponPieza(x, filafinal, columnafinal);
            quitaPieza(filainicial, columnainicial);
            if (promocion(filafinal,x))
                promo=true;
            x.setNumMovimientos();
        } else if (enroque(mov,x,elTurno)) {
            moverEnroque(mov,x);
        } else if (enPasant(mov,x)) {
            menPasant(mov,x);
        }
        pintarTablero();
        elTurno.setElTurno();
        return  promo;
    }

    /*public void vmover(int filainicial, int filafinal, int columnainicial, int columnafinal, Pieza x, Juego elTurno) {
        Movimiento mov=new Movimiento(filainicial, columnainicial,filafinal,columnafinal);
        if (!enroque(mov,x,elTurno)){
            movergrafica(filainicial,filafinal,columnainicial,columnafinal, x);
            if ((x.getClass().getSimpleName().equalsIgnoreCase("Peon") && x.getColor().equalsIgnoreCase("blanco") && filafinal == 0) || (x.getClass().getSimpleName().equalsIgnoreCase("Peon") && x.getColor().equalsIgnoreCase("negro") && filafinal == 7)) {
                promocionarPeon(filafinal, columnafinal, );
            }
            x.setNumMovimientos();
        } else if (enroque(mov,x,elTurno)) {
            moverEnroque(mov,x);
        }
        pintarTablero();
        elTurno.setElTurno();
    }*/
    public void movergrafica(int filainicial, int columnainicial, int filafinal, int columnafinal, Pieza x) {
            ponPieza(x, filafinal, columnafinal);
            quitaPieza(filainicial, columnainicial);
    }

    public boolean promocion( int filafinal, Pieza x){
        boolean promocion=false;
        if ((x.getClass().getSimpleName().equalsIgnoreCase("Peon") && x.getColor().equalsIgnoreCase("blanco") && filafinal == 0) || (x.getClass().getSimpleName().equalsIgnoreCase("Peon") && x.getColor().equalsIgnoreCase("negro") && filafinal == 7)) {
            promocion = true;
        }
        return promocion;
    }

    public boolean enPasant(Movimiento mov, Pieza x){
        boolean hay=false;
        if (x.getClass().getSimpleName().equalsIgnoreCase("Peon") && mov.esDiagonal())
            if (x.getColor().equalsIgnoreCase("blanco") && mov.saltoVertical()==1 && mov.getPosInicial().getFila()==3 || x.getColor().equalsIgnoreCase("negro") && mov.saltoVertical()==-1 && mov.getPosInicial().getFila()==4){
                Pieza y=devuelvePieza(mov.getPosInicial().getFila(),mov.getPosFinal().getColumna());
                if (y!=null && y.getClass().getSimpleName().equalsIgnoreCase("Peon") && y.getNumMovimientos()==1){
                    hay=true;
                }
            }
        return hay;
    }

    public void menPasant(Movimiento mov, Pieza x){
        ponPieza(x,mov.getPosFinal());
        quitaPieza(mov.getPosInicial());
        quitaPieza(mov.getPosInicial().getFila(),mov.getPosFinal().getColumna());
        x.setNumMovimientos();
    }

    /**
     * Método que realiza la promoción del peón y cam,bia este por la pieza que el usuario quiera.
     *
     * @param filafinal
     * @param columnafinal
     */
    public void promocionarPeon(int filafinal, int columnafinal, int opcion) {
            switch (opcion) {
                case 1:
                    ponPieza(new Reina("blanco"), filafinal, columnafinal);
                    break;
                case 2:
                    ponPieza(new Caballo("blanco"), filafinal, columnafinal);
                    break;
                case 3:
                    ponPieza(new Alfil("blanco"), filafinal, columnafinal);
                    break;
                case 4:
                    ponPieza(new Torre("blanco"), filafinal, columnafinal);
                    break;
            }
    }
    public boolean enroque(Movimiento mov,Pieza x,Juego elTurno){
        boolean enroque=false;
        Pieza y=devuelvePieza(mov.getPosFinal());
        if (y!=null)
        if (!jaqueBlanco() && !jaqueNegro()){
            if (x.getClass().getSimpleName().equalsIgnoreCase("Rey") && y.getClass().getSimpleName().equalsIgnoreCase("Torre") && x.getNumMovimientos()==0 && y.getNumMovimientos()==0 && hayPiezasEntre(mov))
                if (mov.getPosInicial().getFila()==0 && x.getColor().equalsIgnoreCase("negro") || mov.getPosInicial().getFila()==7 && x.getColor().equalsIgnoreCase("blanco"))
                enroque=true;
        }
        return enroque;
    }

    public void moverEnroque(Movimiento mov,Pieza x){
        Pieza y=devuelvePieza(mov.getPosFinal());
        if (mov.saltoHorizontal()<0)
            if (mov.getPosInicial().getFila()==0){
                ponPieza(x,0,6);
                ponPieza(y,0,5);
            } else {
                ponPieza(x,7,6);
                ponPieza(y,7,5);
            }
        if (mov.saltoHorizontal()>0){
            if (mov.getPosInicial().getFila()==0){
                ponPieza(x,0,2);
                ponPieza(y,0,3);
            } else {
                ponPieza(x,7,2);
                ponPieza(y,7,3);
            }
        }
        /*if (jaqueBlanco() || jaqueNegro()){
            ponPieza(x,mov.getPosInicial());
            ponPieza(x,mov.getPosFinal());
        }*/
        x.setNumMovimientos();
        y.setNumMovimientos();
        quitaPieza(mov.getPosInicial());
        quitaPieza(mov.getPosFinal());
    }

    public boolean reyAhogadoBlanco(){
        boolean fin=false;
        if (!jaqueBlanco()){
            fin=true;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Pieza x= devuelvePieza(i,j);
                    if (x!=null && x.getColor().equalsIgnoreCase("blanco")){
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                Movimiento mov=new Movimiento(i,j,k,l);
                                if (x.validoMovimiento(mov,this) && !provocaJaque(x,k,l,i,j)){
                                    if (!hayPieza(mov.getPosFinal()))
                                        fin=false;
                                    else if (hayPieza(mov.getPosFinal()) && devuelvePieza(mov.getPosFinal()).getColor().equalsIgnoreCase("negro"))
                                        fin=false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return fin;
    }
    public boolean reyAhogadoNegro(){
        boolean fin=false;
        if (!jaqueNegro()){
            fin=true;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Pieza x= devuelvePieza(i,j);
                    if (x!=null && x.getColor().equalsIgnoreCase("negro")){
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                Movimiento mov=new Movimiento(i,j,k,l);
                                if (x.validoMovimiento(mov,this) && !provocaJaque(x,k,l,i,j)){
                                    if (!hayPieza(mov.getPosFinal()))
                                        fin=false;
                                    else if (hayPieza(mov.getPosFinal()) && devuelvePieza(mov.getPosFinal()).getColor().equalsIgnoreCase("blanco"))
                                        fin=false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return fin;
    }

    public boolean finJuego(){
        boolean fin=false;
        if (jaqueBlanco()){
            fin=true;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (devuelvePieza(i,j)!=null){
                        Pieza x=devuelvePieza(i,j);
                        if (x.getColor().equalsIgnoreCase("blanco")){
                            for (int k = 0; k < 8; k++) {
                                for (int l = 0; l < 8; l++) {
                                    Movimiento mov= new Movimiento(i,j,k,l);
                                    if (evitaJaque(x,k,l,i,j) && x.validoMovimiento(mov,this))
                                        if (!x.getClass().getSimpleName().equalsIgnoreCase("Rey"))
                                            fin=false;
                                        else if (x.getClass().getSimpleName().equalsIgnoreCase("Rey") && hayPieza(k,l) ) {
                                            if (devuelvePieza(k,l).getColor().equalsIgnoreCase("negro"))
                                                fin=false;
                                        }
                                }
                            }
                        }
                    }
                }
            }
        } else if (jaqueNegro()){
            fin=true;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (devuelvePieza(i,j)!=null){
                        Pieza x=devuelvePieza(i,j);
                        if (x.getColor().equalsIgnoreCase("negro")){
                            for (int k = 0; k < 8 ; k++) {
                                for (int l = 0; l < 8 && fin==true; l++) {
                                    Movimiento mov= new Movimiento(i,j,k,l);
                                    if (evitaJaque(x,k,l,i,j) && x.validoMovimiento(mov,this))
                                        fin=false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return fin;
    }
}
