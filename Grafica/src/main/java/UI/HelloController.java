package UI;

import Modelo.*;
import common.Constantes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import DAO.*;

import java.net.URL;
import java.util.ResourceBundle;




public class HelloController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private GridPane mainGrid;
    @FXML
    private Label label2;
    private boolean fin;

    private Tablero tablero;

    private Posicion posOrigen;

    private Posicion posFinal;

    private Juego juego;

    public void click(MouseEvent mouseEvent) throws InterruptedException {
        if (!fin) {
            if (juego.getElTurno().equalsIgnoreCase("blanco"))
                label2.setText(Constantes.ES_EL_TURNO_DE_LAS_BLANCAS);
            if (juego.getElTurno().equalsIgnoreCase("negro"))
                label2.setText(Constantes.ES_EL_TURNO_DE_LAS_NEGRAS);
            int columna = 0;
            int fila = 0;
            Node node = null;
            for (int i = 0; i < mainGrid.getChildren().size(); i++) {
                node = mainGrid.getChildren().get(i);
                if (node.getBoundsInParent().contains(mouseEvent.getSceneX(), mouseEvent.getSceneY())) {
                    columna = GridPane.getColumnIndex(node);
                    fila = GridPane.getRowIndex(node);
                }
            }
            if (posOrigen == null) {
                posOrigen = new Posicion(fila, columna);
            } else if (posFinal == null && posOrigen != null) {
                posFinal = new Posicion(fila, columna);
                Pieza x = tablero.devuelvePieza(posOrigen);
                if (graficamovimientoJugada(posOrigen.getFila(), posOrigen.getColumna(), posFinal.getFila(), posFinal.getColumna(), tablero)) {
                    if (tablero.mover(posOrigen.getFila(), posOrigen.getColumna(), posFinal.getFila(), posFinal.getColumna(), x, juego)) {
                        int opcion = alertaPromocion();
                        tablero.promocionarPeon(posFinal.getFila(), posFinal.getColumna(), opcion);
                    }
                }
                pintarTablero();
                posOrigen = null;
                posFinal = null;
            }
        }
        if (tablero.finJuego()) {
            fin = true;
            if (tablero.finJuego()) {
                label2.setText(Constantes.JAQUE_MATE_CON_TOMATE);
                if (juego.getElTurno().equalsIgnoreCase("blanco")) {
                    label.setText(Constantes.LAS_PIEZAS_NEGRAS_HAN_GANADO);
                } else {
                    label.setText(Constantes.LAS_PIEZAS_BLANCAS_HAN_GANADO);
                }
            }
        }
        if (tablero.reyAhogadoBlanco() || tablero.reyAhogadoBlanco()) {
            fin = true;
            label.setText(Constantes.TABLAS_POR_REY_AHOGADO);
        }
    }

    public int alertaPromocion() {
        int opcion = 0;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(Constantes.PROMOCI??N_DEL_PE??N);
        alert.setHeaderText(Constantes.QUE_PIEZA_DESEA);
        alert.setContentText(Constantes.ELIJA_LA_PIEZA_POR_LA_QUE_DESEE_SUSTITUIR_SU_PEON);

        ChoiceDialog<String> choiceDialog = new ChoiceDialog<String>("Pieza Nueva");
        choiceDialog.getItems().addAll("Reina", "Caballo", "Alfil", "Torre");
        choiceDialog.showAndWait();


        switch (choiceDialog.getSelectedItem()) {
            case "Reina":
                opcion = 1;
                break;
            case "Caballo":
                opcion = 2;
                break;
            case "Alfil":
                opcion = 3;
                break;
            case "Torre": //Para hacerlo as?? quitamos switch case...
                opcion = 4;
                break;
        }
        return opcion;
    }

    public boolean graficamovimientoJugada(int filainicial, int columnainicial, int filafinal, int columnafinal, Tablero tablero) {
        boolean movimiento = false;
        if (tablero.hayPieza(filainicial, columnainicial)) {
            Pieza x = tablero.devuelvePieza(filainicial, columnainicial);
            if (x.getColor().equalsIgnoreCase(juego.getElTurno())) {
                Movimiento mov = new Movimiento(new Posicion(filainicial, columnainicial), new Posicion(filafinal, columnafinal));
                if (x.validoMovimiento(mov, tablero) && !tablero.provocaJaque(x, filafinal, columnafinal, filainicial, columnainicial) || tablero.enroque(mov, x, juego) && !tablero.provocaJaque(x, filafinal, columnafinal, filainicial, columnainicial)) {
                    if ((juego.getElTurno().equalsIgnoreCase("negro")) && !(tablero.jaqueNegro()) || (juego.getElTurno().equalsIgnoreCase("blanco") && !tablero.jaqueBlanco())) {
                        if (tablero.hayPieza(filafinal, columnafinal)) {
                            Pieza y = tablero.devuelvePieza(filafinal, columnafinal);
                            if (y.getColor() == x.getColor() && !tablero.enroque(mov, x, juego)) {
                                label.setText(Constantes.NO_VAYAS_EN_TU_CONTRA_COMETE_LAS_PIEZAS_RIVALES);
                            } else {
                                movimiento = true;
                            }
                        } else {
                            movimiento = true;
                        }
                    } else if (tablero.evitaJaque(x, filafinal, columnafinal, filainicial, columnainicial)) {
                        if (tablero.hayPieza(filafinal, columnafinal)) {
                            Pieza y = tablero.devuelvePieza(filafinal, columnafinal);
                            if (y.getColor() == x.getColor()) {
                                label.setText(Constantes.NO_VAYAS_EN_TU_CONTRA_COMETE_LAS_PIEZAS_RIVALES);
                            } else {
                                movimiento = true;
                            }
                        } else {
                            movimiento = true;
                        }
                    } else {
                        label.setText(Constantes.ESTAS_EN_JAQUE_NO_PUEDES_REALIZAR_ESTE_MOVIMIENTO);
                    }
                } else {
                    label.setText(Constantes.MOVIMIENTO_INVALIDO);
                }
            } else {
                label.setText(Constantes.NO_TE_PRECIPITES_ESPERA_TU_TURNO);
            }
        } else {
            label.setText(Constantes.MOVIMIENTO_NO_VALIDO);
        }
        return movimiento;
    }


    public void pintarTablero() {
        Pane pane;
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                pane = new Pane();
                if (j % 2 == 0 && i % 2 == 0 || j % 2 != 0 && i % 2 != 0) {
                    pane.setStyle("-fx-background-color: #ffffff;");
                } else {
                    pane.setStyle("-fx-background-color: #3a3736");
                }
                if (tablero.hayPieza(i, j)) {
                    //addAll vs add
                    pane.getChildren().addAll(new ImageView(tablero.devuelvePieza(i, j).toImage()));
                }
                mainGrid.add(pane, j, i);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        juego = new Juego("blanco");
        tablero = new Tablero();
        fin = false;
        pintarTablero();
    }

    public void enroque(ActionEvent actionEvent) {
    }
}