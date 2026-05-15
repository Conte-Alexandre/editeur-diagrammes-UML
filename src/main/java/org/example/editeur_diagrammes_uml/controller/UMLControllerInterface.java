package org.example.editeur_diagrammes_uml.controller;

import org.example.editeur_diagrammes_uml.model.UMLClass;
import org.example.editeur_diagrammes_uml.view.UmlBoxView;

public interface UMLControllerInterface {
    void deleteClass (UmlBoxView umlBoxView);
    void handleClassClick(UMLClass clickedClass);
    void refreshAssociations();
}
