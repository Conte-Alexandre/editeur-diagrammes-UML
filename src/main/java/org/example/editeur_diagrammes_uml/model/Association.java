package org.example.editeur_diagrammes_uml.model;

public class Association {
    private UMLClass source;
    private UMLClass target;
    private String label; // pas sur de garder celle là, faut voir
    private String type; // pas sur de garder celle là, faut voir

    public Association(UMLClass source, UMLClass target, String type) {
        this.source = source;
        this.target = target;
        this.type = type;
    }

    public UMLClass getSource() {
        return source;
    }

    public UMLClass getTarget() {
        return target;
    }

    public String getType() {
        return type;
    }
}
