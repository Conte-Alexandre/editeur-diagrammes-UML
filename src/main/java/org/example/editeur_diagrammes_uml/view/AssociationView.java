package org.example.editeur_diagrammes_uml.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import org.example.editeur_diagrammes_uml.model.Association;

public class AssociationView extends Group {
    private Association model;

    // Les composants visuels
    private Line line;
    private Polygon arrowHead; // Le triangle pour l'héritage

    public AssociationView(Association model) {
        this.model = model;

        // 1. On initialise la ligne de base
        this.line = new Line();
        this.line.setStrokeWidth(2);
        this.getChildren().add(line); // On ajoute la ligne au groupe

        // 2. Application du style
        if (model.getType() != null) {
            if (model.getType().equals("DEPENDANCE")) {
                // Trait pointillé
                this.line.getStrokeDashArray().addAll(10d, 10d);

            } else if (model.getType().equals("HERITAGE")) {
                // Création du triangle vide (pointe de flèche UML)
                this.arrowHead = new Polygon();
                this.arrowHead.setFill(Color.WHITE);   // Triangle vide à l'intérieur
                this.arrowHead.setStroke(Color.BLACK); // Bordure noire
                this.arrowHead.setStrokeWidth(2);

                this.getChildren().add(arrowHead); // On ajoute le triangle au groupe
            }
        }

        updatePositions();
    }

    public void updatePositions() {
        // CORRECTION ICI : Les vraies dimensions estimées d'une classe vide
        double halfWidth = 40.0;  // Largeur totale d'environ 80px
        double halfHeight = 15.0; // Hauteur totale d'environ 30px

        // 1. Calculer les centres des deux boîtes
        double startX = model.getSource().getX() + halfWidth;
        double startY = model.getSource().getY() + halfHeight;

        // Centre théorique de la cible
        double targetCenterX = model.getTarget().getX() + halfWidth;
        double targetCenterY = model.getTarget().getY() + halfHeight;

        // 2. Calculer l'angle entre les deux centres
        double dx = targetCenterX - startX;
        double dy = targetCenterY - startY;
        double angle = Math.atan2(dy, dx);

        // 3. RECULER LE POINT D'ARRIVÉE
        // Calculer la distance exacte jusqu'au bord du rectangle
        double distanceToEdge = Math.min(
                Math.abs(halfWidth / Math.cos(angle)),
                Math.abs(halfHeight / Math.sin(angle))
        );

        double endX = targetCenterX - distanceToEdge * Math.cos(angle);
        double endY = targetCenterY - distanceToEdge * Math.sin(angle);

        // Position de départ de la ligne
        line.setStartX(startX);
        line.setStartY(startY);

        if (arrowHead != null) {
            // Taille de la flèche
            double arrowLength = 20;
            double arrowWidth = 10;

            // La ligne s'arrête à la base du triangle pour ne pas le traverser
            line.setEndX(endX - arrowLength * Math.cos(angle));
            line.setEndY(endY - arrowLength * Math.sin(angle));

            // Calcul des 3 points du triangle (la pointe est maintenant sur le BORD de la boîte)
            double tipX = endX;
            double tipY = endY;

            double baseLeftX = endX - arrowLength * Math.cos(angle) - arrowWidth * Math.sin(angle);
            double baseLeftY = endY - arrowLength * Math.sin(angle) + arrowWidth * Math.cos(angle);

            double baseRightX = endX - arrowLength * Math.cos(angle) + arrowWidth * Math.sin(angle);
            double baseRightY = endY - arrowLength * Math.sin(angle) - arrowWidth * Math.cos(angle);

            // Mise à jour du dessin
            arrowHead.getPoints().clear();
            arrowHead.getPoints().addAll(
                    tipX, tipY,
                    baseLeftX, baseLeftY,
                    baseRightX, baseRightY
            );

        } else {
            // Même pour une simple association, c'est plus joli si la ligne s'arrête au bord !
            line.setEndX(endX);
            line.setEndY(endY);
        }
    }

    public Association getModel() {
        return model;
    }
}