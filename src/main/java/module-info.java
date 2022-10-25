module com.example.projektproo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.projektproo to javafx.fxml;
    exports com.example.projektproo;
}