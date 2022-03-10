module pl.kognitywistyka.io {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires xstream;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens pl.kognitywistyka.io to javafx.fxml, xstream;
    opens pl.kognitywistyka.io.model to xstream;

    exports pl.kognitywistyka.io;
}