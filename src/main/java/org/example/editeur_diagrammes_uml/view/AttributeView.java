package org.example.editeur_diagrammes_uml.view;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import org.example.editeur_diagrammes_uml.model.Attribute;
import org.example.editeur_diagrammes_uml.model.UMLClass;
import javafx.scene.control.Label;

public class AttributeView extends HBox {
    private Attribute attribute;
    private UMLClass parentModel;
    private UmlBoxView parentView;
    private Label label;
    public AttributeView(Attribute attribute, UMLClass parentModel, UmlBoxView parentView){
        this.attribute = attribute;
        this.parentModel = parentModel;
        this.parentView = parentView;
        label = new Label(attribute.getVisibility().getSymbole() + " " + attribute.getName() + " : "+attribute.getType());
        this.getChildren().add(label);
        setUpContextMenu();

    }

    private void setUpContextMenu(){ // changer en anglais ce que j'ai mis en français et supprimer ce commentaire
        // trouver de meillleur nom pour les variable, plus cohérent
        MenuItem deleteItem = new MenuItem("supprimer");
        deleteItem.setOnAction(event->{
            parentModel.removeAttribute(attribute);
            parentView.refresh();
        });
        MenuItem renameItem = new MenuItem("renomer");
        renameItem.setOnAction(event->{
            TextInputDialog dialog = new TextInputDialog(attribute.getName());
            dialog.setTitle("Renomer");
            dialog.showAndWait().ifPresent(name->{
                parentModel.renameAttribute(attribute, name);
                parentView.refresh();
            });
        });
        ContextMenu menu = new ContextMenu(deleteItem, renameItem);
        this.setOnContextMenuRequested(event ->{
            event.consume();
            menu.show(this, event.getScreenX(), event.getScreenY());
        });

    }

}
