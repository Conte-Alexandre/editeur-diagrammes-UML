package org.example.editeur_diagrammes_uml.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.example.editeur_diagrammes_uml.model.UMLClass;
import org.example.editeur_diagrammes_uml.view.UmlBoxView;

import java.util.ArrayList;
import java.util.List;

public class UMLController implements UMLControllerInterface{
    @FXML private Pane workspace;
    private boolean creationNode = false;
    private List<UMLClass> classesList = new ArrayList<>();
    @FXML
    public void onAddClass(){
        creationNode = true;
        workspace.setCursor(Cursor.CROSSHAIR);
    }
    public void onWorkspaceClick(MouseEvent event){
        if (creationNode){
            double x = event.getX();
            double y = event.getY();

            TextInputDialog dialog = new TextInputDialog("New class");
            dialog.setTitle("add new class");
            dialog.showAndWait().ifPresent(className->{
                UMLClass umlModel = new UMLClass(className, x,y);
                UmlBoxView boxView = new UmlBoxView(umlModel, this);
                classesList.add(umlModel);
                workspace.getChildren().add(boxView);
            });
            creationNode = false;
            workspace.setCursor(Cursor.DEFAULT);
        }
    }
    @Override
    public void deleteClass(UmlBoxView boxView){
        workspace.getChildren().remove(boxView);
        UMLClass modelToDelete = boxView.getModel();
        classesList.remove(modelToDelete);
        System.out.println("{debug} class supprimer, class restante : " + classesList.size());
    }
}
