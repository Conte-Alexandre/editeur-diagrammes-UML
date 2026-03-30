package org.example.editeur_diagrammes_uml.view;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.editeur_diagrammes_uml.model.Attribute;
import org.example.editeur_diagrammes_uml.model.UMLClass;
import org.example.editeur_diagrammes_uml.model.Visibility;


public class AttributeDialog {

    public static void show(UMLClass model, Runnable refreshCallback) {
        Dialog<Attribute> dialog = new Dialog<>();
        dialog.setTitle("Add Attribute");

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField typeField = new TextField();
        typeField.setPromptText("Type");

        ToggleGroup visibilityGroup = new ToggleGroup();
        RadioButton publicBtn = new RadioButton("PUBLIC");
        RadioButton privateBtn = new RadioButton("PRIVATE");
        RadioButton protectedBtn = new RadioButton("PROTECTED");
        RadioButton packageBtn = new RadioButton("PACKAGE");

        publicBtn.setToggleGroup(visibilityGroup);
        privateBtn.setToggleGroup(visibilityGroup);
        protectedBtn.setToggleGroup(visibilityGroup);
        packageBtn.setToggleGroup(visibilityGroup);

        privateBtn.setSelected(true);

        VBox visibilityBox = new VBox(5, publicBtn, privateBtn, protectedBtn, packageBtn);

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Type:"), 0, 1);
        grid.add(typeField, 1, 1);
        grid.add(new Label("Visibility:"), 0, 2);
        grid.add(visibilityBox, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                String name = nameField.getText();
                String type = typeField.getText();
                RadioButton selectedBtn = (RadioButton) visibilityGroup.getSelectedToggle();
                Visibility visibility = Visibility.valueOf(selectedBtn.getText());
                return new Attribute(name, type, visibility);
            }
            return null;
        });

        // Ajouter l'attribut au modèle et rafraîchir la vue
        dialog.showAndWait().ifPresent(attribute -> {
            model.addAttribute(attribute);
            refreshCallback.run();
        });
    }
}