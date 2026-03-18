package org.example.editeur_diagrammes_uml.view;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class UmlBoxView extends VBox {
    private double mouseX;
    private double mouseY;
    public UmlBoxView(String className){
        this.setStyle("-fx-border-color:black; -fx-padding:10; -fx-background-color:white;");
        Label title = new Label(className);
        title.setStyle("-fx-font-weight:bold;");
        this.getChildren().addAll(title,new Separator());
        this.setOnMousePressed(event->{
            mouseX = event.getSceneX()-this.getLayoutX();
            mouseY = event.getSceneY()-this.getLayoutY();
        });
        this.setOnMouseDragged(event->{
            this.setLayoutX(event.getSceneX()-mouseX);
            this.setLayoutY(event.getSceneY()-mouseY);
            System.out.println("{début de la classe " + className+" } coordonée : x : " + this.getLayoutX() + " y : "+ this.getLayoutY() );
        });
    }
}
