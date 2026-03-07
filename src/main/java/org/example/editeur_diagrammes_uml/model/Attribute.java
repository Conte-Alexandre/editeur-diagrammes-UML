package org.example.editeur_diagrammes_uml.model;

public class Attribute {
    private String name;
    private String type;
    private Visibility visibility;
    private String multiplicity;

    public Attribute(String name, String type, Visibility visibility, String multiplicity) {
        this.name = name;
        this.type = type;
        this.visibility = visibility;
        this.multiplicity = multiplicity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public void setMultiplicity(String multiplicity) {
        this.multiplicity = multiplicity;
    }
}
