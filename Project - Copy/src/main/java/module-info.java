module org.example.project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;
    requires javafx.web;
    requires java.desktop;

    opens org.example.project to javafx.fxml;
    exports org.example.project;
}