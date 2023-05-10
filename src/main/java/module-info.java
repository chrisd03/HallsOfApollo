module projects.java.hallsofapollo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens projects.java.hallsofapollo to javafx.fxml;
    exports projects.java.hallsofapollo;
}