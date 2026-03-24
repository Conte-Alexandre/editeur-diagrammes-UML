package org.example.editeur_diagrammes_uml.model;


import java.util.ArrayList;
import java.util.List;

public class UMLClass {
    private int id;
    private String className;
    private List<Attribute> attributes = new ArrayList<>();
    private List<Method> methods = new ArrayList<>() ;
    private double x;
    private double y;
    private int width;
    private int height;

    public UMLClass(String className, double x, double y) {
        this.className = className;
        this.y = y;
        this.x = x;
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

    public String getClassName() {
        return className;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
