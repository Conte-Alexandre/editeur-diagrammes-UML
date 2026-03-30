package org.example.editeur_diagrammes_uml.model;

public class Attribute {
    private String name;
    private String type;
    private Visibility visibility;
    private String multiplicity;

    public Attribute(String name, String type, Visibility visibility) {
        this.name = name;
        this.type = type;
        this.visibility = visibility;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setMultiplicity(String multiplicity) {
        this.multiplicity = multiplicity;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Visibility getVisibility() {
        return visibility;
    }
}
