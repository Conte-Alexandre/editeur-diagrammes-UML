package org.example.editeur_diagrammes_uml.view;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import org.example.editeur_diagrammes_uml.model.Method;
import org.example.editeur_diagrammes_uml.model.UMLClass;

import javafx.scene.control.Label;

public class MethodView extends HBox {
    private Method method;
    private UMLClass parentModel;
    private UmlBoxView parentView;
    private Label label;
    public MethodView(Method method,UMLClass parentModel, UmlBoxView parentView){
        this.method = method;
        this.parentModel = parentModel;
        this.parentView = parentView;
        label = new Label(method.getVisibility().getSymbole() + " " + method.getName() + " : " + method.getType() + (method.getAbstract() ? " (abstract)" : "") + (method.getStatic() ? " (static)" : ""));
        this.getChildren().add(label);
        setUpContextMenu();
    }
    private void setUpContextMenu(){
        MenuItem deleteItem = new MenuItem("delete");
        deleteItem.setOnAction(event->{

            parentModel.removeMethod(method);
            parentView.refresh();
        });
        MenuItem renameItem = new MenuItem("rename");
        renameItem.setOnAction(event->{
            TextInputDialog dialog = new TextInputDialog(method.getName());
            dialog.setTitle("rename");
            dialog.showAndWait().ifPresent(name->{
                parentModel.renameMethod(method, name);
                parentView.refresh();
            });

        });
        ContextMenu menu = new ContextMenu(deleteItem, renameItem);
        this.setOnContextMenuRequested(event->{
            event.consume();
            menu.show(this,event.getScreenX(),event.getScreenY());
        });
    }
}
