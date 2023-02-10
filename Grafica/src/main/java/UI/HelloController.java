package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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

    private Tablero tablero;

    private Juego juego;

    @FXML
    protected void onHelloButtonClick() {
        label.setText("Welcome to JavaFX Application!");
    }

    public void click(MouseEvent mouseEvent) {
        int columnaini = 0;
        int filaini = 0;
        int columnafinal = 0;
        int filafinal = 0;
        Node node = (Node) mouseEvent.getTarget();
        if (node != null && node.getBoundsInParent().contains(mouseEvent.getSceneX(), mouseEvent.getSceneY())) {
            columnaini = GridPane.getColumnIndex(node);
            filaini = GridPane.getRowIndex(node);
            //System.out.println("Row : " + fila + ", Col : " + columna);
        }
        if (tablero.hayPieza(filaini,columnaini)){
            Node node2 = (Node) mouseEvent.getTarget();
            if (node2 != null && node2.getBoundsInParent().contains(mouseEvent.getSceneX(), mouseEvent.getSceneY())) {
                columnaini = GridPane.getColumnIndex(node2);
                filaini = GridPane.getRowIndex(node2);
                //System.out.println("Row : " + fila + ", Col : " + columna);
            }
            Pieza x=
            juego.movimientoJugada();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        juego=new Juego("blanco");
        tablero=new Tablero();
        pintarTablero();
    }

    public void pintarTablero(){
        Pane pane;
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                pane = new Pane();
                if (j % 2 == 0 && i % 2 == 0 || j % 2 != 0 && i % 2 != 0) {
                    pane.setStyle("-fx-background-color: #684714;");
                } else {
                    pane.setStyle("-fx-background-color: #ffe68e");
                }
                if (tablero.hayPieza(i,j)) {
                    //addAll vs add
                    pane.getChildren().addAll(new ImageView(tablero.devuelvePieza(i,j).toImage()));
                }
                mainGrid.add(pane, j, i);
            }
        }
    }

    public void enroque(ActionEvent actionEvent) {
    }
}