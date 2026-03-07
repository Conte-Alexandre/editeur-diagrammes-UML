package org.example.editeur_diagrammes_uml.model;

import java.util.ArrayList;
import java.util.List;

public class Method {
    private String name;
    private Visibility visibility;
    private String type;
    private Boolean isAbstract;
    private Boolean isStatic;
    private List parameters = new ArrayList<Parameter>();

    public Method(String name, Visibility visibility, String type, Boolean isAbstract, Boolean isStatic, List parameters) {
        this.name = name;
        this.visibility = visibility;
        this.type = type;
        this.isAbstract = isAbstract;
        this.isStatic = isStatic;
        this.parameters = parameters;
    }

    public void setName(String name) {
        this.name = name;
    }
}
