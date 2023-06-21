module projects.java.hallsofapollo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires java.desktop;

    opens projects.java.hallsofapollo to javafx.fxml;
    exports projects.java.hallsofapollo;
    exports projects.java.hallsofapollo.presentation;
    opens projects.java.hallsofapollo.presentation to javafx.fxml;
}