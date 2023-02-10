module com.example.grafica {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;


    exports UI;
    opens UI to javafx.fxml;
}
