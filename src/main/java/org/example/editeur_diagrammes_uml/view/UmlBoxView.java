package org.example.editeur_diagrammes_uml.view;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import org.example.editeur_diagrammes_uml.model.UMLClass;

public class UmlBoxView extends VBox {

    private UMLClass model;

    private Label title;
    private double mouseX;
    private double mouseY;

    public UmlBoxView(UMLClass model) {
        this.model = model;
        this.setStyle("-fx-border-color: black; -fx-background-color: white;");
        title = new Label(model.getClassName());
        title.setStyle("-fx-font-weight: bold; -fx-padding: 5;");
        this.getChildren().addAll(title, new Separator());
        this.setLayoutX(model.getX());
        this.setLayoutY(model.getY());

        initDrag();
    }
    private void initDrag() {
        this.setOnMousePressed(event -> {
            mouseX = event.getSceneX() - this.getLayoutX();
            mouseY = event.getSceneY() - this.getLayoutY();
        });

        this.setOnMouseDragged(event -> {
            double newX = event.getSceneX() - mouseX;
            double newY = event.getSceneY() - mouseY;
            this.setLayoutX(newX);
            this.setLayoutY(newY);
            model.setX(newX);
            model.setY(newY);
            System.out.println("{debug " + model.getClassName() +"} x=" + newX + " y=" + newY);
        });
    }
    public void refresh() {
        title.setText(model.getClassName());
        this.setLayoutX(model.getX());
        this.setLayoutY(model.getY());
    }
    public UMLClass getModel() {
        return model;
    }
}