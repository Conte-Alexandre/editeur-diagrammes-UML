package org.example.editeur_diagrammes_uml.controller;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.example.editeur_diagrammes_uml.model.Association;
import org.example.editeur_diagrammes_uml.model.UMLClass;
import org.example.editeur_diagrammes_uml.view.AssociationView;
import org.example.editeur_diagrammes_uml.view.UmlBoxView;

import java.util.ArrayList;
import java.util.List;

public class UMLController implements UMLControllerInterface{
    @FXML private Pane workspace;
    private boolean creationNode = false;
    private boolean isSelectionMode = false;
    private UMLClass firstClassSelected = null;
    private String currentRelationType = "";

    private List<UMLClass> classesList = new ArrayList<>();
    private List<Association> associationsList = new ArrayList<>();
    private List<AssociationView> associationViews = new ArrayList<>();

    @FXML
    public void onAddClass(){
        creationNode = true;
        isSelectionMode = false;
        workspace.setCursor(Cursor.CROSSHAIR);
    }
    @FXML
    public void onAddAssociation(){
        isSelectionMode = true;
        creationNode = false;
        currentRelationType = "ASSOCIATION";
        workspace.setCursor(Cursor.CROSSHAIR);
        System.out.println("Mode : Création d'une Association classique");
    }
    @FXML
    public void onAddHeritage(){
        isSelectionMode = true;
        creationNode = false;
        currentRelationType = "HERITAGE"; // On mémorise le choix
        workspace.setCursor(Cursor.CROSSHAIR);
        System.out.println("Mode : Création d'un Héritage");
    }
    @FXML
    public void onAddDependance(){
        isSelectionMode = true;
        creationNode = false;
        currentRelationType = "DEPENDANCE"; // On mémorise le choix
        workspace.setCursor(Cursor.CROSSHAIR);
        System.out.println("Mode : Création d'une Dépendance");
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
        } else if (isSelectionMode) {
            resetSelectionMode();
        }
    }
    @Override
    public void deleteClass(UmlBoxView boxView){
        workspace.getChildren().remove(boxView);
        UMLClass modelToDelete = boxView.getModel();
        classesList.remove(modelToDelete);
        System.out.println("{debug} class supprimer, class restante : " + classesList.size());
    }
    @Override
    public void handleClassClick(UMLClass clickedClass) {
        if (isSelectionMode){
            if (firstClassSelected == null){
                firstClassSelected = clickedClass;
                System.out.println("class 1 : " + clickedClass.getClassName());
            } else {
                if (firstClassSelected != clickedClass){
                    creatAssociation(firstClassSelected, clickedClass);
                }
                resetSelectionMode();
            }
        }
    }
    private void creatAssociation(UMLClass source, UMLClass target){
        System.out.println("Création de relation : " + currentRelationType);
        Association association = new Association(source, target, currentRelationType);
        associationsList.add(association);
        AssociationView view = new AssociationView(association);
        associationViews.add(view);
        workspace.getChildren().add(0, view);
    }
    private void resetSelectionMode(){
        isSelectionMode = false;
        firstClassSelected = null;
        workspace.setCursor(Cursor.DEFAULT);
    }
    @Override
    public void refreshAssociations(){
        for (AssociationView view : associationViews){
            view.updatePositions();
        }
    }
}