module org.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens packagee to javafx.fxml;
    exports packagee;
}