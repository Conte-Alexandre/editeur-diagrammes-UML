package org.example.editeur_diagrammes_uml.model;

import java.util.ArrayList;
import java.util.List;

public class UMLClass {
    private int id;
    private String className;
    private List<Attribute> attributes;
    private List<Method> methods ;
    private double x;
    private double y;
    private int width;
    private int height;
    public UMLClass(int id, String className, List attribute, List method, double x, double y) {
        this.id = id;
        this.className = className;
        this.attributes = attribute;
        this.methods = method;
        this.x = x;
        this.y = y;
    }

    public void addAttribute(Attribute attribute){
        attributes.add(attribute);
    };
    public boolean removeAttribute(Attribute attribute){
        return attributes.remove(attribute);
    }

    public void renameAttribute(Attribute attribute, String newName){
        if(attributes.contains(attribute)){
            attribute.setName(newName);
        }
    };

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void addMethod(Method method){
        methods.add(method);
    };
    public void removeMethod(Method method){
        methods.remove(method);
    };
    public void renameMethod(Method method, String newName){
        if (methods.contains(method)){
            method.setName(newName);
        }
    };
    public List<Method> getMethods() {
        return methods;
    }

}
