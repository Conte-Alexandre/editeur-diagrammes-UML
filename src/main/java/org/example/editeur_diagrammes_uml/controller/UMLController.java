package org.example.editeur_diagrammes_uml.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.example.editeur_diagrammes_uml.model.UMLClass;
import org.example.editeur_diagrammes_uml.view.UmlBoxView;

public class UMLController {
    @FXML private Pane workspace;
    private boolean creationNode = false;
    @FXML
    public void onAddClass(){
        creationNode = true;
        workspace.setCursor(Cursor.CROSSHAIR);
        System.out.println("{debug} click effectuer ");
    }
    public void onWorkspaceClick(MouseEvent event){
        if (creationNode){
            double x = event.getX();
            double y = event.getY();

            TextInputDialog dialog = new TextInputDialog("New class");
            dialog.setTitle("add new class");
            dialog.showAndWait().ifPresent(className->{
                UMLClass umlModel = new UMLClass(className, x,y);
                UmlBoxView boxView = new UmlBoxView(umlModel);

                workspace.getChildren().add(boxView);
            });
            creationNode = false;
            workspace.setCursor(Cursor.DEFAULT);
        }
    }
}
