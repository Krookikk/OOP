module org.example.snake {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.snake to javafx.fxml;
    exports org.example.snake;
}