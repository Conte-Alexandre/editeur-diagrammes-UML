package org.example.editeur_diagrammes_uml.view;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.example.editeur_diagrammes_uml.controller.UMLControllerInterface;
import org.example.editeur_diagrammes_uml.model.Attribute;
import org.example.editeur_diagrammes_uml.model.Method;
import org.example.editeur_diagrammes_uml.model.UMLClass;
public class UmlBoxView extends VBox {

    private UMLClass model;

    private Label title;
    private double mouseX;
    private double mouseY;
    private VBox attributeBox = new VBox();
    private VBox methodBox = new VBox();
    private UMLControllerInterface controller;
    public UmlBoxView(UMLClass model, UMLControllerInterface controller) {
        this.model = model;
        this.setStyle("-fx-border-color: black; -fx-background-color: white;");
        title = new Label(model.getClassName());
        title.setStyle("-fx-font-weight: bold; -fx-padding: 5; -fx-text-alignment: center;");
        this.getChildren().addAll(title, new Separator(),attributeBox, new Separator(),methodBox);
        this.setLayoutX(model.getX());
        this.setLayoutY(model.getY());
        this.controller = controller;

        initDrag();
        refresh();
        setUpContextMenu();
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
        attributeBox.getChildren().clear();
        methodBox.getChildren().clear();
        for (Attribute attr : model.getAttributes()){
            AttributeView view = new AttributeView(attr,model,this);
            attributeBox.getChildren().add(view);
        }
        for (Method method : model.getMethods()){
            MethodView view = new MethodView(method, model, this);
            methodBox.getChildren().add(view);
        }
    }

    public void setUpContextMenu(){
        // IMPORTANT, regarder comment j'ai nommer les variable de menuItem de cette class et celle de methodView, c pas la meme chose
        MenuItem addAttributeItem = new MenuItem("add attribute");
        addAttributeItem.setOnAction(event -> {
            AttributeDialog.show(model, this::refresh);
        });
        MenuItem addMethodItem = new MenuItem("add method");
        addMethodItem.setOnAction(event->{
            MethodDialog.show(model,this::refresh);
        });
        MenuItem deletItem = new MenuItem("delete this class");
        deletItem.setOnAction(event->{
            controller.deleteClass(this);
        });
        ContextMenu menu = new ContextMenu(addAttributeItem, addMethodItem, deletItem);
        this.setOnContextMenuRequested(event -> {
            menu.show(this,event.getScreenX(),event.getScreenY());
        });
    }
    public UMLClass getModel() {
        return model;
    }
}