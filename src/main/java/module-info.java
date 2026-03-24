module org.example.editeur_diagrammes_uml {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.editeur_diagrammes_uml to javafx.fxml;
    opens org.example.editeur_diagrammes_uml.controller to javafx.fxml;
    exports org.example.editeur_diagrammes_uml;
}