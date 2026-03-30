package org.example.editeur_diagrammes_uml.model;

public enum Visibility {
    PUBLIC("+"),PRIVATE("-"),PROTECTED("#"),PACKAGE("~");
    private final String symbole;
    Visibility(String symbole){
        this.symbole = symbole;
    }
    public String getSymbole(){
        return symbole;
    }
}
